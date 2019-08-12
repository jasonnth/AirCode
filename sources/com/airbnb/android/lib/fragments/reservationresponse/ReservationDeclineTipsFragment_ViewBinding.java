package com.airbnb.android.lib.fragments.reservationresponse;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class ReservationDeclineTipsFragment_ViewBinding implements Unbinder {
    private ReservationDeclineTipsFragment target;

    public ReservationDeclineTipsFragment_ViewBinding(ReservationDeclineTipsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.loaderView = Utils.findRequiredView(source, C0880R.C0882id.full_loader, "field 'loaderView'");
    }

    public void unbind() {
        ReservationDeclineTipsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.loaderView = null;
    }
}
