package com.airbnb.android.lib.payments.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.PaymentInputLayout;
import com.airbnb.p027n2.primitives.AirButton;

public class AddCouponCodeFragment_ViewBinding implements Unbinder {
    private AddCouponCodeFragment target;
    private View view2131756040;

    public AddCouponCodeFragment_ViewBinding(final AddCouponCodeFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'marquee'", DocumentMarquee.class);
        target2.couponCodeInputLayout = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.coupon_code_layout, "field 'couponCodeInputLayout'", PaymentInputLayout.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.apply_button, "field 'applyButton' and method 'onApplyClicked'");
        target2.applyButton = (AirButton) Utils.castView(view, C0880R.C0882id.apply_button, "field 'applyButton'", AirButton.class);
        this.view2131756040 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onApplyClicked();
            }
        });
    }

    public void unbind() {
        AddCouponCodeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.couponCodeInputLayout = null;
        target2.applyButton = null;
        this.view2131756040.setOnClickListener(null);
        this.view2131756040 = null;
    }
}
