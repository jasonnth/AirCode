package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.HeroMarquee;

public class PurchaseAmenitySuccessFragment_ViewBinding implements Unbinder {
    private PurchaseAmenitySuccessFragment target;

    public PurchaseAmenitySuccessFragment_ViewBinding(PurchaseAmenitySuccessFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.hero_marquee, "field 'heroMarquee'", HeroMarquee.class);
    }

    public void unbind() {
        PurchaseAmenitySuccessFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.heroMarquee = null;
    }
}
