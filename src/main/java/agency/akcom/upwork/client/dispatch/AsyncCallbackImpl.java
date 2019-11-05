package agency.akcom.upwork.client.dispatch;

import agency.akcom.upwork.client.BusyUtil;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

public abstract class AsyncCallbackImpl<T> implements AsyncCallback<T> {
    private final List<Widget> widgets;

    public AsyncCallbackImpl() {
        this.widgets = null;
    }

    public AsyncCallbackImpl(List<Widget> widgets) {
        this.widgets = widgets;
        if (this.widgets != null)
            for (Widget widget : this.widgets)
                BusyUtil.markBusy(widget);
    }

    public void onFailure(Throwable caught) {
        if (this.widgets != null)
            for (Widget widget : this.widgets)
                BusyUtil.clearBusy(widget);
        Window.alert(caught.toString());
    }

    public void onSuccess(T result) {
        if (this.widgets != null)
            for (Widget widget : this.widgets)
                BusyUtil.clearBusy(widget);
        onCustomSuccess(result);
    }

    /** Overwrite to do something with result */
    protected abstract void onCustomSuccess(T result);


}
