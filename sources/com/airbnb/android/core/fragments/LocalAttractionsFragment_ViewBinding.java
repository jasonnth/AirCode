package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.android.core.views.LoopingViewPager;

public class LocalAttractionsFragment_ViewBinding implements Unbinder {
    private LocalAttractionsFragment target;

    public LocalAttractionsFragment_ViewBinding(LocalAttractionsFragment target2, View source) {
        this.target = target2;
        target2.mLoopingViewPager = (LoopingViewPager) Utils.findRequiredViewAsType(source, C0716R.C0718id.attractions_looping_view_pager, "field 'mLoopingViewPager'", LoopingViewPager.class);
        target2.mAirMapView = (AirbnbMapView) Utils.findRequiredViewAsType(source, C0716R.C0718id.map_view, "field 'mAirMapView'", AirbnbMapView.class);
    }

    public void unbind() {
        LocalAttractionsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mLoopingViewPager = null;
        target2.mAirMapView = null;
    }
}
