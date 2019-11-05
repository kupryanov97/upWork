package agency.akcom.upwork.client.application.stats;

import agency.akcom.upwork.client.application.ApplicationPresenter;
import agency.akcom.upwork.client.application.home.HomePresenter;
import agency.akcom.upwork.client.security.IsAdminGatekeeper;
import agency.akcom.upwork.client.security.IsCustomerGatekeeper;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import agency.akcom.upwork.client.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class StatsPresenter extends Presenter<StatsPresenter.MyView, StatsPresenter.MyProxy> implements StatsUiHandlers {
    interface MyView extends View {
        void isLogin(Boolean a); //Проверка что может видить пользователь на начальной странице
    }

    @ProxyStandard
    @NameToken(NameTokens.STATS)
    interface MyProxy extends ProxyPlace<StatsPresenter> {
    }

    private final PlaceManager placeManager;
    private final IsAdminGatekeeper gatekeeper;

    @Inject
    StatsPresenter(
            EventBus eventBus,
            StatsPresenter.MyView view,
            StatsPresenter.MyProxy proxy,
            PlaceManager placeManager,
            IsAdminGatekeeper gatekeeper) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.placeManager = placeManager;
        this.gatekeeper = gatekeeper;

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
