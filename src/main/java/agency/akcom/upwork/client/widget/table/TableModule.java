package agency.akcom.upwork.client.widget.table;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TableModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(TablePresenter.class, TablePresenter.MyView.class, TableView.class);
    }
}
