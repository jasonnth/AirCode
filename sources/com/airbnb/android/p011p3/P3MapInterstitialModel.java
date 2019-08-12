package com.airbnb.android.p011p3;

import com.airbnb.android.core.viewcomponents.models.MapInterstitialEpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.MapInterstitial;

/* renamed from: com.airbnb.android.p3.P3MapInterstitialModel */
public abstract class P3MapInterstitialModel extends MapInterstitialEpoxyModel {
    public void bind(MapInterstitial view) {
        super.bind(view);
        if (Experiments.showExactLocationDisclaimerOnP3Map()) {
            view.setSubtitle(view.getContext().getString(C7532R.string.p3_map_exact_location_disclaimer));
        }
    }
}
