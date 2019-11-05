package agency.akcom.upwork.server.guice;

import agency.akcom.upwork.server.CronServlet;
import agency.akcom.upwork.server.auth.Auth2callback;
import agency.akcom.upwork.server.auth.AuthServlet;
import agency.akcom.upwork.server.auth.GetServlet;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import com.gwtplatform.dispatch.rpc.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;

public class DispatchServletModule extends ServletModule {
    @Override
    protected void configureServlets() {

        serve("/" + ActionImpl.DEFAULT_SERVICE_NAME  + "*").with(DispatchServiceImpl.class);

        bind(AuthServlet.class).in(Singleton.class);
        bind(Auth2callback.class).in(Singleton.class); //регестрирую сервлет
        bind(GetServlet.class).in(Singleton.class); //регестрирую сервлет
        serve("/AuthServlet").with(AuthServlet.class);//поменка какой класс будет обрабатывать переход на определенный url адрес
        serve("/oauth2callback").with(Auth2callback.class);
        serve("/main2").with(GetServlet.class);


        bind(ObjectifyFilter.class).in(Singleton.class);
        filter("/*").through(ObjectifyFilter.class);

        bind(CronServlet.class).in(javax.inject.Singleton.class);
        serve("/cron").with(CronServlet.class);
    }
}

