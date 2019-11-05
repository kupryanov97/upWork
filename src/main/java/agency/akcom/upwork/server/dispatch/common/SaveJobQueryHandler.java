package agency.akcom.upwork.server.dispatch.common;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.domain.UpworkUserSettings;
import agency.akcom.upwork.server.dao.AppUserDao;
import agency.akcom.upwork.server.dao.UpworkUserSettingsDao;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.server.upwork.request.job.request.JobQuery;
import agency.akcom.upwork.shared.action.SaveJobQueryAction;
import agency.akcom.upwork.shared.action.SaveJobQueryResult;
import com.googlecode.objectify.Ref;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


public class SaveJobQueryHandler extends MyAbstractActionHandler<SaveJobQueryAction, SaveJobQueryResult> {
    @Inject
    public SaveJobQueryHandler() {
        super(SaveJobQueryAction.class);
    }

    @Override
    public SaveJobQueryResult execute(SaveJobQueryAction action, ExecutionContext context) throws ActionException {

        AppUserDao appUserDao = new AppUserDao();
        AppUser appUser = appUserDao.get(action.getUserId());

        JobQuery jobQuery = new JobQuery();
        jobQuery.setHashMap(action.getJob().getQueryParam());

        UpworkUserSettingsDao upworkUserSettingsDao = new UpworkUserSettingsDao();
        UpworkUserSettings upworkUserSettings = new UpworkUserSettings();
        List<JobQuery> jobQueryList = new ArrayList<JobQuery>();

        if(upworkUserSettingsDao.getByAncestor(appUser)==null) {

            Ref<AppUser> driver = Ref.create(appUser); //Заполняет Ref<AppUser> ссылку , при этом в Datastore она будет не видна
            upworkUserSettings.setUserDtoRef(driver);

            jobQueryList.add(jobQuery);
            upworkUserSettings.setQueriesList(jobQueryList);
            upworkUserSettingsDao.saveAndReturn(upworkUserSettings);

        } else {
            upworkUserSettings = upworkUserSettingsDao.getByAncestor(appUser);
            jobQueryList.add(jobQuery);
            upworkUserSettings.setQueriesList(jobQueryList);
            upworkUserSettingsDao.saveAndReturn(upworkUserSettings);
        }


        return new SaveJobQueryResult();
    }

}
