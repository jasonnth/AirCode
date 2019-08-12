package com.airbnb.android.payout.create.fragments;

import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class AddPayoutAddressFragment$$Lambda$1 implements OnInputChangedListener {
    private final AddPayoutAddressFragment arg$1;
    private final InlineInputRow arg$2;

    private AddPayoutAddressFragment$$Lambda$1(AddPayoutAddressFragment addPayoutAddressFragment, InlineInputRow inlineInputRow) {
        this.arg$1 = addPayoutAddressFragment;
        this.arg$2 = inlineInputRow;
    }

    public static OnInputChangedListener lambdaFactory$(AddPayoutAddressFragment addPayoutAddressFragment, InlineInputRow inlineInputRow) {
        return new AddPayoutAddressFragment$$Lambda$1(addPayoutAddressFragment, inlineInputRow);
    }

    public void onInputChanged(String str) {
        this.arg$1.removeError(this.arg$2);
    }
}
