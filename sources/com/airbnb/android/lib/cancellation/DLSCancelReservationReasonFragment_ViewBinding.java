package com.airbnb.android.lib.cancellation;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;

public class DLSCancelReservationReasonFragment_ViewBinding implements Unbinder {
    private DLSCancelReservationReasonFragment target;

    public DLSCancelReservationReasonFragment_ViewBinding(DLSCancelReservationReasonFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C0880R.C0882id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        DLSCancelReservationReasonFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.loadingView = null;
        target2.toolbar = null;
    }
}
