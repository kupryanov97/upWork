package agency.akcom.upwork.server.guice;


import agency.akcom.upwork.server.dao.objectify.OfyService;
import agency.akcom.upwork.server.dispatch.MyHandlerModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ServerModule extends AbstractModule {
    @Override
    protected void configure() {
        requestStaticInjection(OfyService.class);
        install(new MyHandlerModule());
    }
}
