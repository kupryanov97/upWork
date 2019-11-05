package agency.akcom.upwork.client.application.home;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Button;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    Button GoogleButton;


    @Inject
    HomeView(Binder uiBinder) {
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
