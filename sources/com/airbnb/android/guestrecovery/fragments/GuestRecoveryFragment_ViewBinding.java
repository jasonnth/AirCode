package com.airbnb.android.guestrecovery.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.guestrecovery.C6392R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;

public class GuestRecoveryFragment_ViewBinding implements Unbinder {
    private GuestRecoveryFragment target;
    private View view2131755481;

    public GuestRecoveryFragment_ViewBinding(final GuestRecoveryFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C6392R.C6394id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C6392R.C6394id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6392R.C6394id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C6392R.C6394id.browse, "method 'browseMoreHomes'");
        this.view2131755481 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.browseMoreHomes();
            }
        });
    }

    public void unbind() {
        GuestRecoveryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.coordinatorLayout = null;
        target2.toolbar = null;
        this.view2131755481.setOnClickListener(null);
        this.view2131755481 = null;
    }
}
