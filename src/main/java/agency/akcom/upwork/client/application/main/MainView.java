package agency.akcom.upwork.client.application.main;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Button;


import javax.inject.Inject;


public class MainView extends ViewWithUiHandlers<MainUiHandlers> implements MainPresenter.MyView {
    interface Binder extends UiBinder<Widget, MainView> {

    }

    @UiField
    Button UpWork;

    @UiField
    Button GetInfo;


    @Inject
    MainView(MainView.Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

    }


    @UiHandler("UpWork")
    public void Settings(ClickEvent eventfirst) {
        getUiHandlers().GetToken();
    }

    @UiHandler("GetInfo")
    public void GetInfo(ClickEvent eventfirst) {
        getUiHandlers().GetInfo();
    }

}
