package com.airbnb.android.checkin;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class CheckinStepFragment_ViewBinding implements Unbinder {
    private CheckinStepFragment target;

    public CheckinStepFragment_ViewBinding(CheckinStepFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5618R.C5620id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        CheckinStepFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
    }
}
