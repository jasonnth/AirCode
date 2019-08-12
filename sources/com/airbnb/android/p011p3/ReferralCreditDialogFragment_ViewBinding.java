package com.airbnb.android.p011p3;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;

/* renamed from: com.airbnb.android.p3.ReferralCreditDialogFragment_ViewBinding */
public class ReferralCreditDialogFragment_ViewBinding implements Unbinder {
    private ReferralCreditDialogFragment target;

    public ReferralCreditDialogFragment_ViewBinding(ReferralCreditDialogFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.couponMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7532R.C7534id.referral_credit_marquee, "field 'couponMarquee'", DocumentMarquee.class);
    }

    public void unbind() {
        ReferralCreditDialogFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.couponMarquee = null;
    }
}
