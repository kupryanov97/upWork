package agency.akcom.upwork.server.dispatch;


import agency.akcom.upwork.server.dispatch.auth.GetCurrentUserHandler;
import agency.akcom.upwork.server.dispatch.common.*;
import agency.akcom.upwork.shared.action.*;
import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class MyHandlerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {

        // Bind Action to Action Handler
        bindHandler(GetUsersAction.class, GetUsersHandler.class);
        bindHandler(DeleteUserAction.class, DeleteUserHandler.class);
        bindHandler(GetTableUsersAction.class, GetTableHandler.class);
        bindHandler(GetTokenUpWorkAction.class, GetTokenUpWorkHandler.class);
        bindHandler(GetCurrentUserAction.class, GetCurrentUserHandler.class);
        bindHandler(SetAdminAction.class, SetAdminHandler.class);
        bindHandler(SearchWorkAction.class, SearchWorkHandler.class);
        bindHandler(SaveJobQueryAction.class, SaveJobQueryHandler.class);
        bindHandler(GetInfoUserAction.class, GetInfoUserHandler.class);
    }
}
