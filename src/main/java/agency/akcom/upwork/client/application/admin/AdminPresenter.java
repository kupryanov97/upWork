package agency.akcom.upwork.client.application.admin;

import agency.akcom.upwork.client.application.ApplicationPresenter;
import agency.akcom.upwork.client.dispatch.AsyncCallbackImpl;
import agency.akcom.upwork.client.place.NameTokens;
import agency.akcom.upwork.client.security.IsAdminGatekeeper;
import agency.akcom.upwork.shared.action.*;
import agency.akcom.upwork.shared.dto.UserDto;
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

import java.util.List;


public class AdminPresenter extends Presenter<AdminPresenter.MyView, AdminPresenter.MyProxy> implements AdminUiHandlers {
    interface MyView extends View, HasUiHandlers<AdminUiHandlers> {
        void displayUsers(List<UserDto> userList);
        void removeUser(UserDto userDto);
        void updateUser(UserDto userDto, UserDto updatedUserDto);
    }


    @ProxyStandard
    @UseGatekeeper(IsAdminGatekeeper.class)
    @NameToken(NameTokens.ADMIN)
    interface MyProxy extends ProxyPlace<AdminPresenter> {

    }

    @Inject
    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;
    private final IsAdminGatekeeper gatekeeper;

    @Inject
    AdminPresenter(
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
    protected void onReset() {
        super.onReset();
        dispatcher.execute(new GetUsersAction(), new AsyncCallbackImpl<GetUsersResult>() {

            @Override
            public void onCustomSuccess(GetUsersResult result) {
                getView().displayUsers(result.getUsers());
            }

        });
    }

    @Override
    public void GetTable() {
        dispatcher.execute(new GetTableUsersAction(gatekeeper.getCurrentUser().getGoogleId()), new AsyncCallbackImpl<GetTableUsersResult>() {
            @Override
            public void onCustomSuccess(GetTableUsersResult result) {
                if(result.getABoolean()){
                    Window.alert("Таблица создана");
                }else{
                    Window.alert("При создании таблицы произошла ошибка");
                }

            }
        });
    }


    @Override
    public void onUserDeleteUpdate(final UserDto userDto) {
        dispatcher.execute(new DeleteUserAction(userDto.getId()), new AsyncCallbackImpl<DeleteUserResult>() {

            @Override
            public void onCustomSuccess(DeleteUserResult result) {
                getView().removeUser(userDto);
            }
        });

    }



    @Override
    public void onIsAdminUpdate(final UserDto userDto, Boolean value) {
        dispatcher.execute(new SetAdminAction(userDto.getId(), value), new AsyncCallbackImpl<SetAdminResult>() {

            @Override
            public void onCustomSuccess(SetAdminResult result) {
                // TODO is it really has sense to update other user attributes from server?
                getView().updateUser(userDto, result.getUserDto());

            }
        });

    }



}
