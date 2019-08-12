package com.airbnb.android.places.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;

public class ResyFragment_ViewBinding implements Unbinder {
    private ResyFragment target;

    public ResyFragment_ViewBinding(ResyFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7627R.C7629id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C7627R.C7629id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7627R.C7629id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.primaryButton = (FixedFlowActionFooter) Utils.findRequiredViewAsType(source, C7627R.C7629id.primary_button, "field 'primaryButton'", FixedFlowActionFooter.class);
        target2.primaryButtonHeight = source.getContext().getResources().getDimension(C7627R.dimen.n2_fixed_action_footer_height);
    }

    public void unbind() {
        ResyFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.coordinatorLayout = null;
        target2.toolbar = null;
        target2.primaryButton = null;
    }
}
