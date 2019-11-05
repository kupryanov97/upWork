package agency.akcom.upwork.server.dispatch.auth;

import agency.akcom.upwork.server.auth.CurrentAppUserProvider;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.shared.action.GetCurrentUserAction;
import agency.akcom.upwork.shared.action.GetCurrentUserResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetCurrentUserHandler extends AbstractAuthHandler<GetCurrentUserAction, GetCurrentUserResult> {
    @Inject
    public GetCurrentUserHandler(CurrentAppUserProvider currentAppUserProvider) {
        super(GetCurrentUserAction.class, currentAppUserProvider);
    }

    @Override
    public GetCurrentUserResult execute(GetCurrentUserAction action, ExecutionContext context) throws ActionException {
        return new GetCurrentUserResult(getCurrentUserDto());
    }

}