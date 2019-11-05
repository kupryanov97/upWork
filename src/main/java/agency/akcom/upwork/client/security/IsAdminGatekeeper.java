package agency.akcom.upwork.client.security;

import com.google.web.bindery.event.shared.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class IsAdminGatekeeper extends IsLoggedInGatekeeper {

    @Inject
    public IsAdminGatekeeper(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    public boolean canReveal() {

        return super.canReveal() && getCurrentUser().isAdmin();

    }

}
