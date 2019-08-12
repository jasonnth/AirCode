package com.airbnb.android.ibdeactivation;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;

public class DeactivateIBLandingFragment_ViewBinding implements Unbinder {
    private DeactivateIBLandingFragment target;
    private View view2131755415;
    private View view2131755476;

    public DeactivateIBLandingFragment_ViewBinding(final DeactivateIBLandingFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6454R.C6456id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6454R.C6456id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.containerView = Utils.findRequiredView(source, C6454R.C6456id.action_buttons_container, "field 'containerView'");
        View view = Utils.findRequiredView(source, C6454R.C6456id.cancel_button, "method 'clickCancel'");
        this.view2131755415 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickCancel();
            }
        });
        View view2 = Utils.findRequiredView(source, C6454R.C6456id.deactivate_button, "method 'onDeactivateClicked'");
        this.view2131755476 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDeactivateClicked();
            }
        });
    }

    public void unbind() {
        DeactivateIBLandingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.containerView = null;
        this.view2131755415.setOnClickListener(null);
        this.view2131755415 = null;
        this.view2131755476.setOnClickListener(null);
        this.view2131755476 = null;
    }
}
