package agency.akcom.upwork.client.widget.table;


import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import javax.inject.Inject;

public class TablePresenter extends PresenterWidget<TablePresenter.MyView> implements TableUiHandlers {
    interface MyView extends View, HasUiHandlers<TableUiHandlers> {
    }


    @Inject
    TablePresenter(
            EventBus eventBus,
            MyView view) {
        super(eventBus, view);
        getView().setUiHandlers(this);
    }

}
