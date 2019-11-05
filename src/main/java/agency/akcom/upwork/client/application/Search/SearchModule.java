
package agency.akcom.upwork.client.application.Search;


import agency.akcom.upwork.client.widget.table.TableModule;
import agency.akcom.upwork.client.widget.table.TablePresenter;
import agency.akcom.upwork.client.widget.table.TableView;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class SearchModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(SearchPresenter.class, SearchPresenter.MyView.class, SearchView.class,
                SearchPresenter.MyProxy.class);
        install(new TableModule());
    }
}
