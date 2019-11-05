package agency.akcom.upwork.client.application.stats;

import agency.akcom.upwork.client.application.main.MainPresenter;
import agency.akcom.upwork.shared.dto.UserDto;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;
import java.util.Date;


public class StatsView extends ViewWithUiHandlers<StatsUiHandlers> implements StatsPresenter.MyView {
    interface Binder extends UiBinder<Widget, agency.akcom.upwork.client.application.stats.StatsView> {

    }

    @UiField
    Button GoogleButton;

    @Inject
    StatsView(Binder uiBinder) {
        Window.alert("kekekekek");
        initWidget(uiBinder.createAndBindUi(this));
    }
    @UiHandler("GoogleButton")
    public void GoogleButton(ClickEvent eventfirst) {
        getUiHandlers().GoogleButton();
    }


    public void isLogin(Boolean a) { //Проверка что может видить пользователь на начальной странице
        if(a){
            GoogleButton.setVisible(false);
            //getUiHandlers().regButton();
        }
    }

}

