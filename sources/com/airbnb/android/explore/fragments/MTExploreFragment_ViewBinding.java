package com.airbnb.android.explore.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.OptionalSwipingViewPager;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.NavigationPill;

public class MTExploreFragment_ViewBinding implements Unbinder {
    private MTExploreFragment target;

    public MTExploreFragment_ViewBinding(MTExploreFragment target2, View source) {
        this.target = target2;
        target2.viewPager = (OptionalSwipingViewPager) Utils.findRequiredViewAsType(source, C0857R.C0859id.viewpager, "field 'viewPager'", OptionalSwipingViewPager.class);
        target2.navigationPill = (NavigationPill) Utils.findRequiredViewAsType(source, C0857R.C0859id.trips_toggle_pill, "field 'navigationPill'", NavigationPill.class);
        target2.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C0857R.C0859id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    }

    public void unbind() {
        MTExploreFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.viewPager = null;
        target2.navigationPill = null;
        target2.coordinatorLayout = null;
    }
}
