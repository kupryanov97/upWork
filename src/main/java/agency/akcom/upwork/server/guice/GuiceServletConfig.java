package agency.akcom.upwork.server.guice;

import com.gargoylesoftware.htmlunit.WebClient;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServerModule(), new DispatchServletModule());
    }

    @Singleton
    @Provides
    WebClient getWebClient() {
        return new WebClient();
    }
}
