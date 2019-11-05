package agency.akcom.upwork.client.application.home;

import agency.akcom.upwork.client.application.ApplicationPresenter;
import agency.akcom.upwork.client.place.NameTokens;
import agency.akcom.upwork.client.security.IsAdminGatekeeper;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import org.apache.xpath.operations.Bool;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeUiHandlers {
    interface MyView extends View , HasUiHandlers<HomeUiHandlers> {
        void isLogin(Boolean a); //Проверка что может видить пользователь на начальной странице
    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }

    private final PlaceManager placeManager;
    private final IsAdminGatekeeper gatekeeper;

    @Inject
    HomePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            PlaceManager placeManager,
            IsAdminGatekeeper gatekeeper) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.placeManager = placeManager;
        this.gatekeeper = gatekeeper;
        getView().setUiHandlers(this);//для замены страницы которую показываем пользователю
    }

    @Override
    public void GoogleButton(){
        Window.Location.replace("/AuthServlet");
    }


    @Override
    public void regButton() { //меняем страницу показываемую пользователю
        Window.alert("gnplf");
      /*  PlaceRequest placeRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.MAIN)
                .build();
        placeManager.revealPlace(placeRequest, true);*/
    }

    @Override
    protected void onReveal() {//Проверка что может видить пользователь на начальной странице
        getView().isLogin(gatekeeper.canReveal());
        super.onReveal();
    }
}


