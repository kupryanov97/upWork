package agency.akcom.upwork.client.application;

import agency.akcom.upwork.client.application.Search.SearchModule;
import agency.akcom.upwork.client.application.admin.AdminModule;
import agency.akcom.upwork.client.application.home.HomeModule;
import agency.akcom.upwork.client.application.main.MainModule;
import agency.akcom.upwork.client.application.stats.StatsModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
        install(new StatsModule());
        install(new MainModule());
        install(new SearchModule());
        install(new AdminModule());
        install(new HomeModule());

    }
}
