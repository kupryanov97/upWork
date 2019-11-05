package agency.akcom.upwork.server.dispatch.common;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.server.ServerUtils;
import agency.akcom.upwork.server.dao.AppUserDao;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.server.upwork.UpworkAuthClient;
import agency.akcom.upwork.shared.action.GetTokenUpWorkAction;
import agency.akcom.upwork.shared.action.GetTokenUpWorkResult;
import com.Upwork.api.Config;
import com.Upwork.api.OAuthClient;
import com.Upwork.api.Routers.Freelancers.Search;
import com.Upwork.api.Routers.Organization.Users;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class GetTokenUpWorkHandler extends MyAbstractActionHandler<GetTokenUpWorkAction, GetTokenUpWorkResult> {



    public static HashMap<String,UpworkAuthClient> hash = new HashMap<String,UpworkAuthClient>();

    @Inject
    public GetTokenUpWorkHandler() {
        super(GetTokenUpWorkAction.class);
    }


    @Override
    public GetTokenUpWorkResult execute(GetTokenUpWorkAction action, ExecutionContext context) throws ActionException {

        String authzUrl = null;
        try {

            UpworkAuthClient upworkClient = new UpworkAuthClient();
            authzUrl = upworkClient.getAuthorizationUrl();

            System.out.println(authzUrl);
            //FIX THIS
            URL test = new URL(authzUrl);
            AppUserDao appUserDao = new AppUserDao();
            AppUser appUser = appUserDao.get(action.getUserId());

            appUser.setOauth_token(test.getQuery().substring(12));

            ServerUtils.createUserDto(true, appUserDao.saveAndReturn(appUser)); //
            hash.put(action.getUserId().toString(),upworkClient); //Fix This



        }catch (Exception e){
            e.printStackTrace();
            return new GetTokenUpWorkResult(null);
        }

        return new GetTokenUpWorkResult(authzUrl);
    }


}

