package agency.akcom.upwork.client.security;

import agency.akcom.upwork.client.event.LoginAuthenticatedEvent;
import agency.akcom.upwork.client.event.LoginResetEvent;
import agency.akcom.upwork.shared.dto.UserDto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalFooter;
import org.gwtbootstrap3.client.ui.constants.ModalBackdrop;
import org.gwtbootstrap3.client.ui.html.Span;

import javax.inject.Inject;

abstract public class IsLoggedInGatekeeper implements Gatekeeper {
    private final EventBus eventBus;
    private UserDto userDto;

    @Inject
    public IsLoggedInGatekeeper(final EventBus eventBus) {
        this.eventBus = eventBus;

        this.eventBus.addHandler(LoginAuthenticatedEvent.getType(),
                event -> userDto = event.getCurrentUser());

        this.eventBus.addHandler(LoginResetEvent.getType(), event -> userDto = null);

    }

    @Override
    public boolean canReveal() {
        boolean loggedIn = false;

        if (userDto != null) {
            loggedIn = userDto.isLoggedIn();
        }

        return loggedIn;
    }

    public UserDto getCurrentUser() {
        return userDto;
    }



}