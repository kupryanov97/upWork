package agency.akcom.upwork.server.dao.objectify;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.domain.UpworkUserSettings;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.googlecode.objectify.ObjectifyFactory;

public class OfyFactory extends ObjectifyFactory{
    private Injector injector;

    @Inject
    public OfyFactory(Injector injector) {
        this.injector = injector;

        long time = System.currentTimeMillis();

        this.register(AppUser.class);
        this.register(UpworkUserSettings.class);

        long mills = System.currentTimeMillis() - time;
        System.out.println("Registration took " + mills + " millis");
    }

    @Override
    public <T> T construct(Class<T> type) {
        return injector.getInstance(type);
    }
}
