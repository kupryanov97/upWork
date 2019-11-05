package agency.akcom.upwork.server.dispatch.common;


import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.server.dao.AppUserDao;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.server.upwork.UpworkAuthClient;
import agency.akcom.upwork.shared.action.GetInfoUserAction;
import agency.akcom.upwork.shared.action.GetInfoUserResult;
import com.Upwork.api.Routers.Auth;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

public class GetInfoUserHandler extends MyAbstractActionHandler<GetInfoUserAction, GetInfoUserResult> {


    @Inject
    public GetInfoUserHandler() {
        super(GetInfoUserAction.class);
    }

    @Override
    public GetInfoUserResult execute(GetInfoUserAction action, ExecutionContext context) throws ActionException {
        String a = null;
        try {
            AppUserDao appUserDao = new AppUserDao();
            AppUser appUser = appUserDao.get(action.getId());

            UpworkAuthClient upworkClient = new UpworkAuthClient();
            upworkClient.getOAuthClient().setTokenWithSecret(appUser.getToken(), appUser.getSecret());

            Auth auth = new Auth(upworkClient.getOAuthClient());
            a = auth.getUserInfo().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new GetInfoUserResult(a);
    }

}