package com.airbnb.android.lib.payments.quickpay.fragments;

import android.view.View.OnClickListener;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class ResyQuickPayFragment extends QuickPayFragment {
    public static ResyQuickPayFragment instanceForCartItem(CartItem cartItem, QuickPayClientType clientType) {
        return (ResyQuickPayFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ResyQuickPayFragment()).putParcelable("arg_cart_item", cartItem)).putSerializable("arg_quick_pay_client_type", clientType)).build();
    }

    /* access modifiers changed from: protected */
    public void setPayButtonPrice(String price) {
        if (isFreeReservation()) {
            this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_button_text_resy_free));
            return;
        }
        this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_button_text_resy, price));
    }

    /* access modifiers changed from: protected */
    public OnClickListener payButtonClickListener() {
        if (isFreeReservation()) {
            return ResyQuickPayFragment$$Lambda$1.lambdaFactory$(this);
        }
        return super.payButtonClickListener();
    }

    private boolean isFreeReservation() {
        return this.billPriceQuote != null && this.billPriceQuote.getPrice().getTotal().isZero();
    }
}
