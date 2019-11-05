
package agency.akcom.upwork.client.application.Search;

import agency.akcom.upwork.client.application.ApplicationPresenter;
import agency.akcom.upwork.client.dispatch.AsyncCallbackImpl;
import agency.akcom.upwork.client.place.NameTokens;
import agency.akcom.upwork.client.security.IsCustomerGatekeeper;
import agency.akcom.upwork.shared.action.*;
import agency.akcom.upwork.shared.domain.JobQueryDto;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import java.util.HashMap;
import java.util.List;

public class SearchPresenter extends Presenter<SearchPresenter.MyView, SearchPresenter.MyProxy> implements SearchUiHandlers {
    interface MyView extends View , HasUiHandlers<SearchUiHandlers>{
        void HideMy();
        void initRadioButton(List<String> a);
    }

    //@ProxyCodeSplit
    @ProxyStandard
    @UseGatekeeper(IsCustomerGatekeeper.class)
    @NameToken(NameTokens.SEARCH)
    interface MyProxy extends ProxyPlace<SearchPresenter> {
    }

    @Inject
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;
    private final IsCustomerGatekeeper gatekeeper;

    private JobQueryDto test;

    @Inject
    SearchPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy,
            DispatchAsync dispatcher,
            PlaceManager placeManager,
            IsCustomerGatekeeper gatekeeper
            ) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
        this.dispatcher = dispatcher;
        this.placeManager = placeManager;
        this.gatekeeper = gatekeeper;

        getView().setUiHandlers(this);//для замены страницы которую показываем пользователю
    }

    @Override
    protected void onReveal() {
        getView().HideMy();
        super.onReveal();
    }

    @Override
    public void setSetting(HashMap<String, String> hashMap) {
        JobQueryDto test = new JobQueryDto();
        test.setQueryParam(hashMap);

        dispatcher.execute(new SaveJobQueryAction(test,gatekeeper.getCurrentUser().getId() ), new AsyncCallbackImpl<SaveJobQueryResult>() {
            @Override
            public void onCustomSuccess(SaveJobQueryResult result) {
                Window.alert("Успешно созданно");
            }
        });
    }

    @Override
    public void getSetting() {
//        dispatcher.execute(new GetSettingAction(gatekeeper.getCurrentUser().getId()), new AsyncCallbackImpl<GetSettingResult>() {
//            @Override
//            public void onCustomSuccess(GetSettingResult result) {
//                getView().initRadioButton(result.getSetting());
//            }
//        });
    }

    @Override
    public void searchWork() {
        dispatcher.execute(new SearchWorkAction(gatekeeper.getCurrentUser().getId()), new AsyncCallbackImpl<SearchWorkResult>() {
            @Override
            public void onCustomSuccess(SearchWorkResult result) {
                Window.alert(result.getOut());
            }
        });
    }

}
