package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class PendingAmenityOrderDetailFragment_ViewBinding implements Unbinder {
    private PendingAmenityOrderDetailFragment target;

    public PendingAmenityOrderDetailFragment_ViewBinding(PendingAmenityOrderDetailFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.acceptButton = (AirButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.accept_button, "field 'acceptButton'", AirButton.class);
        target2.declineButton = (AirButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.decline_button, "field 'declineButton'", AirButton.class);
        target2.buttonContainer = (RelativeLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.bottom_button_container, "field 'buttonContainer'", RelativeLayout.class);
    }

    public void unbind() {
        PendingAmenityOrderDetailFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.acceptButton = null;
        target2.declineButton = null;
        target2.buttonContainer = null;
    }
}
