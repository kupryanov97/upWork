package agency.akcom.upwork.client.widget.table;

import agency.akcom.upwork.client.widget.MyCellTable;
import agency.akcom.upwork.shared.dto.UserDto;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.RangeChangeEvent;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import javax.inject.Inject;
import java.util.List;


public class TableView extends ViewWithUiHandlers<TableUiHandlers> implements TablePresenter.MyView {
    interface Binder extends UiBinder<Widget, TableView> {
    }

    @UiField
    CellTable<String> userTable = new CellTable<String>();

    @UiField
    Pagination userTablePagination;

    @Inject
    TableView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        initTable();
    }

    private SimplePager userTablePager = new SimplePager();

    private ListDataProvider<String> userTableProvider = new ListDataProvider<String>();

    private void initTable() {

        //Вывод времени
        final TextColumn<String> TimeColumn = new TextColumn<String>() {
            @Override
            public String getValue(final String string) {
                return "test";
            }
        };

        userTable.addColumn(TimeColumn, "Entry time");

        userTablePagination.rebuild(LimitTable(10,userTable));
        userTablePager.setDisplay(userTable);
        userTablePagination.clear();
        userTableProvider.addDataDisplay(userTable);

    }

    public SimplePager LimitTable(int height, CellTable<String> a){ //Ограничеваем таблицу числом строк

        SimplePager pager = new SimplePager();
        pager.setDisplay(a);
        pager.setPage(10);
        pager.setPageSize(height);
        return pager;

    }

}
