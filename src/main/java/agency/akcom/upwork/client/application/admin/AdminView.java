package agency.akcom.upwork.client.application.admin;

import agency.akcom.upwork.client.widget.MyCellTable;
import agency.akcom.upwork.shared.dto.UserDto;
import com.google.gwt.cell.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.extras.toggleswitch.client.ui.ToggleSwitch;


import javax.inject.Inject;
import java.util.List;


public class AdminView extends ViewWithUiHandlers<AdminUiHandlers> implements AdminPresenter.MyView {
    interface Binder extends UiBinder<Widget, AdminView> {

    }


    //@UiField
    //CellTable<UserDto> userTable = new CellTable<UserDto>(10);

    @UiField
    MyCellTable userTable = new MyCellTable();


    @UiField
    Pagination userTablePagination;

    @UiField
    Button button;

    @UiField
    ToggleSwitch submitOnEnterToggle = new ToggleSwitch();


    private SimplePager userTablePager = new SimplePager();

    private ListDataProvider<UserDto> userTableProvider = new ListDataProvider<UserDto>();


    @Inject
    AdminView(AdminView.Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        initTable();
        submitOnEnterToggle.setOnText("Yes");
        submitOnEnterToggle.setOffText("No");
    }


    private void initTable() {
        //Вывод аватарки
        final Column<UserDto, String> userImageColumn = new Column<UserDto, String>(
                new ImageCell() ) {

            @Override
            public String getValue(UserDto userDto) {
                return userDto.getPictureURL() + "?sz=50"; //userDto.getPictureURL() + "?sz=50"
            }
        };
        userTable.addColumn(userImageColumn, "Image");

        //Вывод времени
        final TextColumn<UserDto> TimeColumn = new TextColumn<UserDto>() {
            @Override
            public String getValue(final UserDto userDto) {
                return userDto.getTime();
            }
        };
        userTable.addColumn(TimeColumn, "Entry time");

        //Вывод времени обновления
        final TextColumn<UserDto> TimeColumn2 = new TextColumn<UserDto>() {
            @Override
            public String getValue(final UserDto userDto) {
                return userDto.getTimeModified();
            }
        };
        userTable.addColumn(TimeColumn2, "Refresh time");

        //Вывод имени
        final TextColumn<UserDto> nameTextColumn = new TextColumn<UserDto>() {
            @Override
            public String getValue(final UserDto userDto) {
                return userDto.getLogin();
            }
        };
        userTable.addColumn(nameTextColumn, "User Name");

        //Вывод @mail
        final TextColumn<UserDto> emailTextColumn = new TextColumn<UserDto>() {
            @Override
            public String getValue(final UserDto userDto) {
                return userDto.getEmail();
            }
        };
        userTable.addColumn(emailTextColumn, "Email");

        ///Нужно починить
        Column<UserDto, Boolean> isAdminCheckColumn = new Column<UserDto, Boolean>(new CheckboxCell(true, false)) {
            @Override
            public Boolean getValue(UserDto userDto) {
                return userDto.isAdmin();
            }
        };

        isAdminCheckColumn.setFieldUpdater(new FieldUpdater<UserDto, Boolean>() {

            @Override
            public void update(int index, UserDto userDto, Boolean value) {
                getUiHandlers().onIsAdminUpdate(userDto, value);
            }
        });
        userTable.addColumn(isAdminCheckColumn, "Is Admin ?");

        // Кнопка удаление юзера
        final Column<UserDto, String> actionButtonsColumn = new Column<UserDto, String>(new ButtonCell(
                ButtonType.DANGER, IconType.TRASH)) {
            @Override
            public String getValue(UserDto object) {
                return "";
            }
        };
        actionButtonsColumn.setFieldUpdater(new FieldUpdater<UserDto, String>() {
            @Override
            public void update(int index, UserDto userDto, String value) {
                getUiHandlers().onUserDeleteUpdate(userDto);
            }
        });
        userTable.addColumn(actionButtonsColumn);


        userTable.addRangeChangeHandler(new RangeChangeEvent.Handler() {

            @Override
            public void onRangeChange(final RangeChangeEvent event) {
                userTablePagination.rebuild(userTablePager);
            }

        });


        userTablePagination.rebuild(userTable.LimitTable(10));
        userTablePager.setDisplay(userTable);
        userTablePagination.clear();
        userTableProvider.addDataDisplay(userTable);

    }

    @UiHandler("button")
    public void Settings(ClickEvent eventfirst) {
        getUiHandlers().GetTable();
    }

    @Override
    public void removeUser(UserDto userDto) {

        userTableProvider.getList().remove(userDto);
        userTableProvider.flush();
        userTablePagination.rebuild(userTablePager);

    }



    @Override
    public void displayUsers(List<UserDto> userList) {

        userTableProvider.getList().clear();
        userTableProvider.getList().addAll(userList);
        userTableProvider.flush();
        userTablePagination.rebuild(userTablePager);
    }

    @Override
    public void updateUser(UserDto userDto, UserDto updatedUserDto) {
        int index = userTableProvider.getList().indexOf(userDto);
        userTableProvider.getList().set(index, updatedUserDto);
        userTableProvider.flush();
        // userTablePagination.rebuild(userTablePager);
    }

}
