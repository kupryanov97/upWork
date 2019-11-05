package agency.akcom.upwork.client.widget;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import org.gwtbootstrap3.client.ui.CheckBox;
import org.gwtbootstrap3.extras.toggleswitch.client.ui.ToggleSwitch;

public class MyToggleSwitch extends AbstractEditableCell<ToggleSwitch,Boolean> {


    @Override
    public boolean isEditing(Context context, Element parent, ToggleSwitch value) {
        return true;
    }

    @Override
    public void render(Context context, ToggleSwitch value, SafeHtmlBuilder sb) {

            ToggleSwitch widget = new ToggleSwitch();
            sb.appendHtmlConstant(widget.getElement().getString());

    }
}
