package agency.akcom.upwork.client.application.stats;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class StatsModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(StatsPresenter.class, StatsPresenter.MyView.class, StatsView.class,
                StatsPresenter.MyProxy.class);
    }
}
