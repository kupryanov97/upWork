package agency.akcom.upwork.server.dispatch.common;

import agency.akcom.upwork.server.dao.AppUserDao;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.shared.action.DeleteUserAction;
import agency.akcom.upwork.shared.action.DeleteUserResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;

public class DeleteUserHandler extends MyAbstractActionHandler<DeleteUserAction, DeleteUserResult> {
    @Inject
    public DeleteUserHandler() {
        super(DeleteUserAction.class);
    }

    @Override
    public DeleteUserResult execute(DeleteUserAction action, ExecutionContext context) throws ActionException {

        new AppUserDao().delete(action.getUserId());
        return new DeleteUserResult();
    }

}
