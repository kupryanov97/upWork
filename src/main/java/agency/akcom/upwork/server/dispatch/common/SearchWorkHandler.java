package agency.akcom.upwork.server.dispatch.common;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.domain.UpworkUserSettings;

import agency.akcom.upwork.server.dao.AppUserDao;
import agency.akcom.upwork.server.dao.UpworkUserSettingsDao;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.shared.action.SearchWorkAction;
import agency.akcom.upwork.shared.action.SearchWorkResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class SearchWorkHandler  extends MyAbstractActionHandler<SearchWorkAction, SearchWorkResult> {
    @Inject
    public SearchWorkHandler() {
        super(SearchWorkAction.class);
    }

    @Override
    public SearchWorkResult execute(SearchWorkAction action, ExecutionContext context) throws ActionException {


        UpworkUserSettingsDao upworkUserSettingsDao = new UpworkUserSettingsDao();
        AppUserDao appUserDao = new AppUserDao();
        AppUser appUser = appUserDao.get(action.getUserId());

        UpworkUserSettings upworkUserSettings = upworkUserSettingsDao.getByAncestor(appUser);

        if(upworkUserSettings == null)
            return new SearchWorkResult("Сохраненных данных нет");

        return new SearchWorkResult(upworkUserSettings.getQueriesList().get(0).getQuery());
    }

}

