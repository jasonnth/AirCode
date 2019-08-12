package com.airbnb.android.insights.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.insights.C6552R;
import com.airbnb.p027n2.components.AirToolbar;

public class PricingDisclaimerFragment_ViewBinding implements Unbinder {
    private PricingDisclaimerFragment target;

    public PricingDisclaimerFragment_ViewBinding(PricingDisclaimerFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6552R.C6554id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        PricingDisclaimerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
    }
}
