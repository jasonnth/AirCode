package com.airbnb.android.places.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;

public class PlaceActivityPDPFragment_ViewBinding implements Unbinder {
    private PlaceActivityPDPFragment target;
    private View view2131755527;

    public PlaceActivityPDPFragment_ViewBinding(final PlaceActivityPDPFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7627R.C7629id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7627R.C7629id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C7627R.C7629id.primary_button, "field 'actionFooter' and method 'onAddToItineraryClick'");
        target2.actionFooter = (FixedActionFooter) Utils.castView(view, C7627R.C7629id.primary_button, "field 'actionFooter'", FixedActionFooter.class);
        this.view2131755527 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onAddToItineraryClick();
            }
        });
        target2.flowActionFooter = (FixedFlowActionFooter) Utils.findRequiredViewAsType(source, C7627R.C7629id.make_reservation_button, "field 'flowActionFooter'", FixedFlowActionFooter.class);
        target2.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C7627R.C7629id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    }

    public void unbind() {
        PlaceActivityPDPFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.actionFooter = null;
        target2.flowActionFooter = null;
        target2.coordinatorLayout = null;
        this.view2131755527.setOnClickListener(null);
        this.view2131755527 = null;
    }
}
