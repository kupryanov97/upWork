package agency.akcom.upwork.client.application.stats;

import agency.akcom.upwork.client.application.main.MainPresenter;
import agency.akcom.upwork.client.widget.MyCellTable;
import agency.akcom.upwork.shared.dto.UserDto;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.toggleswitch.client.ui.ToggleSwitch;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;


public class StatsView extends ViewWithUiHandlers<StatsUiHandlers> implements StatsPresenter.MyView {
    interface Binder extends UiBinder<Widget, agency.akcom.upwork.client.application.stats.StatsView> {

    }
    @UiField
    MyCellTable userTable = new MyCellTable();
    @UiField
    Modal modal;

    @UiField
    Pagination userTablePagination;

    @UiField
    Button button;

    @UiField
    ToggleSwitch submitOnEnterToggle = new ToggleSwitch();


    private SimplePager userTablePager = new SimplePager();

    private ListDataProvider<UserDto> userTableProvider = new ListDataProvider<UserDto>();


    @Inject
    StatsView(Binder uiBinder) {
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
                return userDto.getPictureURL() + "?sz=20"; //userDto.getPictureURL() + "?sz=50"
            }
        };
        userTable.addColumn(userImageColumn, "Фото");

        //Вывод времени
        final TextColumn<UserDto> TimeColumn = new TextColumn<UserDto>() {
            @Override
            public String getValue(final UserDto userDto) {
                return "16 часов 32 минут";
            }
        };
        userTable.addColumn(TimeColumn, "Общее время работы за неделю");

        //Вывод времени обновления
        final TextColumn<UserDto> TimeColumn2 = new TextColumn<UserDto>() {
            @Override
            public String getValue(final UserDto userDto) {
                return "Пришёл в 12.13";
            }
        };
        userTable.addColumn(TimeColumn2, "Текущий статус");

        //Вывод имени
        final TextColumn<UserDto> nameTextColumn = new TextColumn<UserDto>() {
            @Override
            public String getValue(final UserDto userDto) {
                return userDto.getLogin();
            }
        };
        userTable.addColumn(nameTextColumn, "Имя");

        //Вывод @mail
        final TextColumn<UserDto> emailTextColumn = new TextColumn<UserDto>() {
            @Override
            public String getValue(final UserDto userDto) {
                return userDto.getEmail();
            }
        };
        userTable.addColumn(emailTextColumn, "Email");

        // Кнопка удаление юзера
        final Column<UserDto, String> actionButtonsColumn = new Column<UserDto, String>(new ButtonCell(
                ButtonType.DANGER, IconType.INFO_CIRCLE)) {
            @Override
            public String getValue(UserDto object) {
                return "";
            }
        };
        actionButtonsColumn.setFieldUpdater(new FieldUpdater<UserDto, String>() {
            @Override
            public void update(int index, UserDto userDto, String value) {
                modal.show();
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

