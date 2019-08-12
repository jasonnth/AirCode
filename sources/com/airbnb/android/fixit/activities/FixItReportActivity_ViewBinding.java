package com.airbnb.android.fixit.activities;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.fixit.C6380R;

public class FixItReportActivity_ViewBinding implements Unbinder {
    private FixItReportActivity target;

    public FixItReportActivity_ViewBinding(FixItReportActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public FixItReportActivity_ViewBinding(FixItReportActivity target2, View source) {
        this.target = target2;
        target2.rootContainer = (FrameLayout) Utils.findRequiredViewAsType(source, C6380R.C6382id.root_container, "field 'rootContainer'", FrameLayout.class);
    }

    public void unbind() {
        FixItReportActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rootContainer = null;
    }
}
