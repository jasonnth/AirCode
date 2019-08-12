package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.primitives.AirButton;

public class GiftCardRedemptionAddCreditCardFragment_ViewBinding implements Unbinder {
    private GiftCardRedemptionAddCreditCardFragment target;
    private View view2131755474;

    public GiftCardRedemptionAddCreditCardFragment_ViewBinding(final GiftCardRedemptionAddCreditCardFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0704R.C0706id.next_button, "field 'nextButton' and method 'onClickNextButton'");
        target2.nextButton = (AirButton) Utils.castView(view, C0704R.C0706id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131755474 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNextButton();
            }
        });
    }

    public void unbind() {
        GiftCardRedemptionAddCreditCardFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.nextButton = null;
        this.view2131755474.setOnClickListener(null);
        this.view2131755474 = null;
    }
}
