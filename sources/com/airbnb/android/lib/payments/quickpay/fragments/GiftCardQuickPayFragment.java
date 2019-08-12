package com.airbnb.android.lib.payments.quickpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.payments.models.Bill;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.activities.AddCvvActivity;
import com.airbnb.android.lib.payments.braintree.BraintreeCreditCardApi;
import com.airbnb.android.lib.payments.errors.QuickPayError;
import com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.GiftCardQuickPayClickListener;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.erf.Experiments;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.CardNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;
import icepick.State;

public class GiftCardQuickPayFragment extends QuickPayFragment implements GiftCardQuickPayClickListener {
    private BraintreeCreditCardApi creditCardApi;
    @State
    String cvvNonce;
    private final PaymentMethodNonceCreatedListener nonceCreatedListener = GiftCardQuickPayFragment$$Lambda$1.lambdaFactory$(this);

    static /* synthetic */ void lambda$new$0(GiftCardQuickPayFragment giftCardQuickPayFragment, PaymentMethodNonce paymentMethodNonce) {
        if (paymentMethodNonce instanceof CardNonce) {
            giftCardQuickPayFragment.cvvNonce = paymentMethodNonce.getNonce();
            giftCardQuickPayFragment.setPayButtonToNormal();
        }
    }

    public static GiftCardQuickPayFragment instanceForCartItem(CartItem cartItem, QuickPayClientType clientType) {
        return (GiftCardQuickPayFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new GiftCardQuickPayFragment()).putParcelable("arg_cart_item", cartItem)).putSerializable("arg_quick_pay_client_type", clientType)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BraintreeFragment braintreeFragment = this.braintreeFactory.createBraintreeFragment(getActivity());
        braintreeFragment.addListener(this.nonceCreatedListener);
        this.creditCardApi = this.braintreeFactory.createCreditCardApi(braintreeFragment);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 && resultCode == -1) {
            this.paymentOptions = data.getParcelableArrayListExtra("result_extra_payment_options");
            this.selectedPaymentOption = (PaymentOption) data.getParcelableExtra("result_extra_payment_option");
            handleCvvNonce(data);
            this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
            fetchBillPriceQuote(this.shouldIncludeAirbnbCredit, this.settlementCurrency);
        } else if (requestCode == 1001 && resultCode == -1) {
            onCvvNonceTokenized(data);
        } else if (requestCode == 1002 && resultCode == -1) {
            onPaymentMethodAdded(data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /* access modifiers changed from: protected */
    public Builder createBillParameters(BillProductType billProductType) {
        return super.createBillParameters(billProductType).cvvNonce(this.cvvNonce);
    }

    /* access modifiers changed from: protected */
    public void onBillRequestCompleted(Bill bill) {
        confirmAndPayLogging(true, null);
        getActivity().setResult(-1);
        startActivity(ReactNativeIntents.intentForGiftCardsSent(getActivity(), ((GiftCardClientParameters) this.cartItem.quickPayParameters()).recipientName(), Double.valueOf(this.billPriceQuote.getPrice().getTotal().getAmount().doubleValue()), this.billPriceQuote.getPrice().getTotal().getCurrency()));
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    public void onBillRequestError(NetworkException networkException) {
        super.onBillRequestError(networkException);
        if (new QuickPayError(networkException, this.airlockErrorHandler).shouldNullOutCvvState()) {
            this.cvvNonce = null;
            this.selectedPaymentOption.setCvvVerified(false);
            this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
        }
    }

    public void onAddSecurityCodeClicked() {
        launchAddSecurityCodeFlow();
    }

    /* access modifiers changed from: protected */
    public OnClickListener payButtonClickListener() {
        return GiftCardQuickPayFragment$$Lambda$2.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$payButtonClickListener$1(GiftCardQuickPayFragment giftCardQuickPayFragment, View v) {
        if (!PaymentUtils.isValidPaymentOption(giftCardQuickPayFragment.selectedPaymentOption)) {
            giftCardQuickPayFragment.showAddPaymentMethods();
        } else if (!giftCardQuickPayFragment.selectedPaymentOption.isCvvVerified()) {
            giftCardQuickPayFragment.launchAddSecurityCodeFlow();
        } else {
            giftCardQuickPayFragment.sendBill();
        }
    }

    /* access modifiers changed from: protected */
    public void setPayButtonPrice(String price) {
        if (!PaymentUtils.isValidPaymentOption(this.selectedPaymentOption)) {
            this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_add_payment));
        } else if (!this.selectedPaymentOption.isCvvVerified()) {
            this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_verify_security_code));
        } else {
            this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_button_text, price));
        }
    }

    /* access modifiers changed from: protected */
    public void onPaymentMethodAdded(Intent data) {
        super.onPaymentMethodAdded(data);
        this.selectedPaymentOption.setCvvVerified(true);
        this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
        setPayButtonPrice(this.billPriceQuote.getPrice().getTotal().formattedForDisplay());
        if (!shouldShowNewAddPaymentMethodFlow()) {
            tokenizeCvv((BraintreeCreditCard) data.getSerializableExtra("result_extra_credit_card"));
        }
    }

    private void launchAddSecurityCodeFlow() {
        getActivity().startActivityForResult(AddCvvActivity.intentForSecurityCode(getActivity(), this.paymentOptions, this.selectedPaymentOption, QuickPayClientType.GiftCard, this.billPriceQuote.getPrice()), 1001);
    }

    private void handleCvvNonce(Intent data) {
        this.cvvNonce = data.getStringExtra("result_extra_cvv_payload");
        if (this.cvvNonce != null) {
            this.selectedPaymentOption.setCvvVerified(true);
        }
    }

    private void onCvvNonceTokenized(Intent data) {
        this.cvvNonce = data.getStringExtra(AddCvvActivity.RESULT_EXTRA_CVV_NONCE);
        this.selectedPaymentOption = (PaymentOption) data.getParcelableExtra("result_extra_payment_option");
        this.selectedPaymentOption.setCvvVerified(true);
        this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
        setPayButtonPrice(this.billPriceQuote.getPrice().getTotal().formattedForDisplay());
    }

    private void tokenizeCvv(BraintreeCreditCard creditCard) {
        setPayButtonToLoading();
        this.creditCardApi.tokenize(new BraintreeCreditCard(creditCard.getSecurityCode()));
    }

    private boolean shouldShowNewAddPaymentMethodFlow() {
        return Experiments.launchNewAddPaymentMethodFlow();
    }
}
