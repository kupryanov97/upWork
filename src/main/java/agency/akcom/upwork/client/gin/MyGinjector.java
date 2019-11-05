package agency.akcom.upwork.client.gin;

import agency.akcom.upwork.client.widget.table.TablePresenter;
import com.google.gwt.inject.client.Ginjector;

public interface MyGinjector extends Ginjector {
    TablePresenter getTablePresenter();
}
