package agency.akcom.upwork.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.html.Div;


public class BusyUtil {

    private static final String BUSY_SPINNER_STYLE = "BUSY_SPINNER_STYLE";

    private static boolean ensureNotBusy(final UIObject o) {
        Element first = o.getElement().getFirstChildElement();
        if (first != null && first.removeClassName(BUSY_SPINNER_STYLE)) {
            first.removeFromParent();
            return true;
        }

        return false;
    }

    public static void markBusy(final Widget w) {
        if (w instanceof FocusWidget)
            ((FocusWidget) w).setEnabled(false);
        UIObject o = w.getParent();
        ensureNotBusy(o);
        // NOTE: Don't add style to the component as we don't want 'spinner' to be
        // disabled.
        Div div = new Div();
        div.addStyleName(BUSY_SPINNER_STYLE);
        o.getElement().insertFirst(div.getElement());
    }

    /**
     * Enables the given component and removes the spinner (if any).
     */
    public static void clearBusy(final Widget w) {
        if (w instanceof FocusWidget)
            ((FocusWidget) w).setEnabled(true);
        UIObject o = w.getParent();
        if (!ensureNotBusy(o)) {
            GWT.log("No busy spinner to remove");
        }
    }
}