package com.airbnb.android.lib.activities.booking;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.LoadingView;

public class BookingV2Activity_ViewBinding implements Unbinder {
    private BookingV2Activity target;

    public BookingV2Activity_ViewBinding(BookingV2Activity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public BookingV2Activity_ViewBinding(BookingV2Activity target2, View source) {
        this.target = target2;
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C0880R.C0882id.loading_view, "field 'loadingView'", LoadingView.class);
    }

    public void unbind() {
        BookingV2Activity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loadingView = null;
    }
}
