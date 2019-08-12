package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class ReservationPickerFragment_ViewBinding implements Unbinder {
    private ReservationPickerFragment target;

    public ReservationPickerFragment_ViewBinding(ReservationPickerFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.loader = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.full_loader, "field 'loader'", FrameLayout.class);
    }

    public void unbind() {
        ReservationPickerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.loader = null;
    }
}
