package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;

public class PayPalPayoutFragment_ViewBinding implements Unbinder {
    private PayPalPayoutFragment target;
    private View view2131756071;

    public PayPalPayoutFragment_ViewBinding(final PayPalPayoutFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.emailSheetInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.email_sheetInput, "field 'emailSheetInput'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.submit_button, "field 'submitButton' and method 'onSubmitButtonClick'");
        target2.submitButton = (AirButton) Utils.castView(view, C0880R.C0882id.submit_button, "field 'submitButton'", AirButton.class);
        this.view2131756071 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSubmitButtonClick();
            }
        });
    }

    public void unbind() {
        PayPalPayoutFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.emailSheetInput = null;
        target2.submitButton = null;
        this.view2131756071.setOnClickListener(null);
        this.view2131756071 = null;
    }
}
