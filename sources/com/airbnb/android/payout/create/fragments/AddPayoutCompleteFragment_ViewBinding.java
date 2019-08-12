package com.airbnb.android.payout.create.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.payout.C7552R;
import com.airbnb.p027n2.primitives.AirButton;

public class AddPayoutCompleteFragment_ViewBinding implements Unbinder {
    private AddPayoutCompleteFragment target;

    public AddPayoutCompleteFragment_ViewBinding(AddPayoutCompleteFragment target2, View source) {
        this.target = target2;
        target2.managePaymentsButton = (AirButton) Utils.findRequiredViewAsType(source, C7552R.C7554id.manage_payments_button, "field 'managePaymentsButton'", AirButton.class);
        target2.addMorePayoutButton = (AirButton) Utils.findRequiredViewAsType(source, C7552R.C7554id.add_another_button, "field 'addMorePayoutButton'", AirButton.class);
    }

    public void unbind() {
        AddPayoutCompleteFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.managePaymentsButton = null;
        target2.addMorePayoutButton = null;
    }
}
