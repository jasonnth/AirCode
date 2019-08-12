package com.airbnb.android.checkin;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.AirRecyclerView;

public class CheckInActionFragment_ViewBinding implements Unbinder {
    private CheckInActionFragment target;

    public CheckInActionFragment_ViewBinding(CheckInActionFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C5618R.C5620id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
    }

    public void unbind() {
        CheckInActionFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
    }
}
