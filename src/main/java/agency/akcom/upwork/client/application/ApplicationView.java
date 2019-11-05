package agency.akcom.upwork.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Navbar;

public class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    SimplePanel main;

    @UiField
    Navbar bar = new Navbar();

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bar.setVisible(false);
        bindSlot(ApplicationPresenter.SLOT_MAIN, main);
    }

    public void isLogin(Boolean a) { //Проверка что может видить пользователь на начальной странице
       if(a) {
           bar.setVisible(true);
       }else{

       }
    }

}
