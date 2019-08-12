package com.airbnb.android.lib.payments.quickpay.fragments;

import android.content.Intent;
import com.airbnb.android.booking.activities.CreditCardActivity;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.payments.models.Bill;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.MagicalTripsQuickPayClickListener;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.react.ReactNativeActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class MagicalTripsQuickPayFragment extends QuickPayFragment implements MagicalTripsQuickPayClickListener {
    private static final String TAG = MagicalTripsQuickPayFragment.class.getSimpleName();

    public static MagicalTripsQuickPayFragment instanceForCartItem(CartItem cartItem, QuickPayClientType clientType) {
        return (MagicalTripsQuickPayFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new MagicalTripsQuickPayFragment()).putParcelable("arg_cart_item", cartItem)).putSerializable("arg_quick_pay_client_type", clientType)).build();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11002 && resultCode == -1) {
            this.postalCode = data.getStringExtra(CreditCardActivity.RESULT_EXTRA_POSTAL_CODE);
            showLoadingState(true);
            fetchBillPriceQuote(this.shouldIncludeAirbnbCredit, this.settlementCurrency);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: protected */
    public void onBillRequestCompleted(Bill bill) {
        confirmAndPayLogging(true, null);
        getActivity().setResult(-1, ReactNativeActivity.intentWithDismissFlag());
        startActivity(ReactNativeIntents.intentForCityHostsBookingConfirmed(getActivity()));
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    public void setPayButtonPrice(String price) {
        if (PaymentUtils.isValidPaymentOption(this.selectedPaymentOption)) {
            this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_button_text_confirm_booking, price));
            return;
        }
        this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_add_payment));
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

    /* access modifiers changed from: protected */
    public void executeBillPriceQuoteRequest(boolean shouldIncludeAirbnbCredit, String displayCurrency) {
        this.billPriceQuoteApi.fetchBillPriceQuoteV2(BillPriceQuoteRequestParams.builder().clientType(this.clientType).paymentOption(this.selectedPaymentOption).includeAirbnbCredit(shouldIncludeAirbnbCredit).quickPayParameters(this.cartItem.quickPayParameters()).userAgreedToCurrencyMismatch(this.userAgreedToCurrencyMismatch).displayCurrency(displayCurrency).zipRetry(this.postalCode).build());
    }
}
