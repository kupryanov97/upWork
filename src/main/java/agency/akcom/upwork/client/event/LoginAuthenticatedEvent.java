package agency.akcom.upwork.client.event;

import agency.akcom.upwork.shared.dto.UserDto;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class LoginAuthenticatedEvent extends GwtEvent<LoginAuthenticatedEvent.LoginAuthenticatedHandler> {

    public static Type<LoginAuthenticatedHandler> TYPE = new Type<LoginAuthenticatedHandler>();

    private final UserDto userDto;

    public interface LoginAuthenticatedHandler extends EventHandler {
        void onLoginAuthenticated(LoginAuthenticatedEvent event);
    }

    public LoginAuthenticatedEvent(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    protected void dispatch(LoginAuthenticatedHandler handler) {
        handler.onLoginAuthenticated(this);
    }

    @Override
    public Type<LoginAuthenticatedHandler> getAssociatedType() {
        return TYPE;
    }

    public static Type<LoginAuthenticatedHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, UserDto userDto) {
        source.fireEvent(new LoginAuthenticatedEvent(userDto));
    }

    public UserDto getCurrentUser() {
        return userDto;
    }
}