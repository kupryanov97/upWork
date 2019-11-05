package agency.akcom.upwork.client.widget;


import agency.akcom.upwork.shared.dto.UserDto;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.SimplePager;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.TableType;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

//При помощи интерфейса CellTable.Resources можно переопределить CSS стиль.
public class MyCellTable extends CellTable<UserDto> {



    public MyCellTable(){
        //setRowCount(4);

        //seEmptyTableWidget(new Label(" No Records Found"));
    }

    public SimplePager LimitTable(int height){ //Ограничеваем таблицу числом строк

        SimplePager pager = new SimplePager();
        pager.setDisplay(this);
        pager.setPage(10);
        pager.setPageSize(height);
        return pager;

    }


}
