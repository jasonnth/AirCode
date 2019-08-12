package com.airbnb.android.explore.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.views.MTExploreMarquee;

public class MTExploreParentFragment_ViewBinding implements Unbinder {
    private MTExploreParentFragment target;

    public MTExploreParentFragment_ViewBinding(MTExploreParentFragment target2, View source) {
        this.target = target2;
        target2.exploreMarquee = (MTExploreMarquee) Utils.findRequiredViewAsType(source, C0857R.C0859id.explore_marquee, "field 'exploreMarquee'", MTExploreMarquee.class);
        target2.loadingView = Utils.findRequiredView(source, C0857R.C0859id.loading_view, "field 'loadingView'");
        target2.container = Utils.findRequiredView(source, C0857R.C0859id.container, "field 'container'");
        target2.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C0857R.C0859id.coordinator_root, "field 'coordinatorLayout'", CoordinatorLayout.class);
    }

    public void unbind() {
        MTExploreParentFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.exploreMarquee = null;
        target2.loadingView = null;
        target2.container = null;
        target2.coordinatorLayout = null;
    }
}
