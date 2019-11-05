package agency.akcom.upwork.client.application.main;

import agency.akcom.upwork.client.application.ApplicationPresenter;
import agency.akcom.upwork.client.dispatch.AsyncCallbackImpl;
import agency.akcom.upwork.client.place.NameTokens;
import agency.akcom.upwork.client.security.IsAdminGatekeeper;
import agency.akcom.upwork.shared.action.GetInfoUserAction;
import agency.akcom.upwork.shared.action.GetInfoUserResult;
import agency.akcom.upwork.shared.action.GetTokenUpWorkAction;
import agency.akcom.upwork.shared.action.GetTokenUpWorkResult;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;


public class MainPresenter extends Presenter<MainPresenter.MyView, MainPresenter.MyProxy> implements MainUiHandlers {
    interface MyView extends View, HasUiHandlers<MainUiHandlers> {
    }


    @ProxyStandard
    @UseGatekeeper(IsAdminGatekeeper.class)
    @NameToken(NameTokens.MAIN)
    interface MyProxy extends ProxyPlace<MainPresenter> {

    }

    @Inject
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;
    private final IsAdminGatekeeper gatekeeper;

    @Inject
    MainPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            DispatchAsync dispatcher,
            PlaceManager placeManager,
            IsAdminGatekeeper gatekeeper) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.dispatcher = dispatcher;
        this.placeManager = placeManager;
        this.gatekeeper = gatekeeper;
        getView().setUiHandlers(this);
    }

    @Override
    public void AuthUpWork(){
        Window.Location.replace("/UpWorkAuthServlet");
    }

    @Override
    public void GetToken() {

        dispatcher.execute(new GetTokenUpWorkAction(gatekeeper.getCurrentUser().getId()), new AsyncCallbackImpl<GetTokenUpWorkResult>() {
            @Override
            public void onCustomSuccess(GetTokenUpWorkResult result) {
                Window.Location.replace(result.getToken());
            }
        });
    }

    @Override
    public void GetInfo() {

        dispatcher.execute(new GetInfoUserAction(gatekeeper.getCurrentUser().getId()), new AsyncCallbackImpl<GetInfoUserResult>() {
            @Override
            public void onCustomSuccess(GetInfoUserResult result) {
                Window.alert(result.getInfo());
            }
        });
    }

}
