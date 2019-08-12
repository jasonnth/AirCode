package com.airbnb.android.lib.payments.quickpay.fragments;

import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.PaidAmenityQuickPayClickListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class PaidAmenityQuickPayFragment extends QuickPayFragment implements PaidAmenityQuickPayClickListener {
    public static PaidAmenityQuickPayFragment instanceForCartItem(CartItem cartItem, QuickPayClientType clientType) {
        return (PaidAmenityQuickPayFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PaidAmenityQuickPayFragment()).putParcelable("arg_cart_item", cartItem)).putSerializable("arg_quick_pay_client_type", clientType)).build();
    }

    public void onGiftCreditToggled() {
        boolean z = true;
        showLoadingState(true);
        if (this.billPriceQuote.getPrice().hasGiftCredit()) {
            z = false;
        }
        this.shouldIncludeAirbnbCredit = z;
        fetchBillPriceQuote(this.shouldIncludeAirbnbCredit, this.settlementCurrency);
    }
}
