package agency.akcom.upwork.server.dispatch.common;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.server.ServerUtils;
import agency.akcom.upwork.server.dao.AppUserDao;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.shared.action.GetUsersAction;
import agency.akcom.upwork.shared.action.GetUsersResult;
import agency.akcom.upwork.shared.dto.UserDto;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetUsersHandler extends MyAbstractActionHandler<GetUsersAction, GetUsersResult> {
    @Inject
    public GetUsersHandler() {
        super(GetUsersAction.class);
    }

    @Override
    public GetUsersResult execute(GetUsersAction action, ExecutionContext context) throws ActionException {

        List<AppUser> appUsers = new AppUserDao().listAll();

        List<UserDto> userDtos = new ArrayList<UserDto>(appUsers.size());

        for (AppUser appUser : appUsers) {
            userDtos.add(ServerUtils.createUserDto(true, appUser));
        }
        return new GetUsersResult(userDtos);
    }

}
