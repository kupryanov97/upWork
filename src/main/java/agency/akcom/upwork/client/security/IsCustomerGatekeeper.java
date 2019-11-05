package agency.akcom.upwork.client.security;


import com.google.web.bindery.event.shared.EventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class IsCustomerGatekeeper extends IsLoggedInGatekeeper {

    @Inject
    public IsCustomerGatekeeper(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    public boolean canReveal() {
        return super.canReveal(); //TODO add some additional validation to differ from original IsLoggedInGatekeeper
    }

}

