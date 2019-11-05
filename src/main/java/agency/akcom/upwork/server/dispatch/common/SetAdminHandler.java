package agency.akcom.upwork.server.dispatch.common;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.server.ServerUtils;
import agency.akcom.upwork.server.dao.AppUserDao;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.shared.action.SetAdminAction;
import agency.akcom.upwork.shared.action.SetAdminResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

public class SetAdminHandler  extends MyAbstractActionHandler<SetAdminAction, SetAdminResult> {
    @Inject
    public SetAdminHandler() {
        super(SetAdminAction.class);
    }

    @Override
    public SetAdminResult execute(SetAdminAction action, ExecutionContext context) throws ActionException {

        AppUserDao appUserDao = new AppUserDao();

        AppUser appUser = appUserDao.get(action.getUserId());

        appUser.setAdmin(action.getValue());

        return new SetAdminResult(ServerUtils.createUserDto(true, appUserDao.saveAndReturn(appUser)));

    }

}
