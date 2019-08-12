package com.airbnb.android.lib.payments.quickpay.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.activities.CreditCardActivity;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger.QuickPayConfirmAndPayParams;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.IdentityClient;
import com.airbnb.android.core.identity.IdentityClient.IdentityClientListener;
import com.airbnb.android.core.intents.QuickPayActivityIntents;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.payments.models.Bill;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.BookingResult;
import com.airbnb.android.core.payments.models.BookingResult.Error;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.activities.AddCouponCodeActivity;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.HomesQuickPayClickListener;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import icepick.State;
import java.util.List;
import p032rx.Observer;

public class HomesQuickPayFragment extends QuickPayFragment implements IdentityClientListener, HomesQuickPayClickListener {
    public static final String ARG_RESERVATION = "key_reservation";
    private static final int REQUEST_CODE_ADD_COUPON = 2000;
    private BillPriceQuoteListener billPriceQuoteListener;
    @State
    String couponCode;
    IdentityClient identityClient;
    @State
    Reservation reservation;
    final RequestListener<ReservationResponse> reservationListener = new C0699RL().onResponse(HomesQuickPayFragment$$Lambda$1.lambdaFactory$(this)).onError(HomesQuickPayFragment$$Lambda$2.lambdaFactory$(this)).build();

    public interface BillPriceQuoteListener {
        void onBillPriceQuoteChanged(BillPriceQuote billPriceQuote);
    }

    public static HomesQuickPayFragment instanceForCartItem(CartItem cartItem, QuickPayClientType clientType, Reservation reservation2) {
        return (HomesQuickPayFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new HomesQuickPayFragment()).putParcelable("arg_cart_item", cartItem)).putSerializable("arg_quick_pay_client_type", clientType)).putParcelable(ARG_RESERVATION, reservation2)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.billPriceQuoteListener = (BillPriceQuoteListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement BillPriceQuoteListener interface");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getArguments().getParcelable(ARG_RESERVATION);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2000 && resultCode == -1) {
            this.couponCode = data.getStringExtra(AddCouponCodeActivity.EXTRA_COUPON_CODE);
            this.billPriceQuote = (BillPriceQuote) data.getParcelableExtra(AddCouponCodeActivity.EXTRA_BILL_PRICE_QUOTE);
            this.quickPayAdapter.setPriceQuote(this.billPriceQuote);
        } else if (requestCode == 11002 && resultCode == -1) {
            this.postalCode = data.getStringExtra(CreditCardActivity.RESULT_EXTRA_POSTAL_CODE);
            showLoadingState(true);
            fetchBillPriceQuote(this.shouldIncludeAirbnbCredit, this.settlementCurrency);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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

    public void onAddCouponClicked() {
        if (isCouponCodeApplied()) {
            showLoadingState(true);
            this.couponCode = null;
            fetchBillPriceQuote(this.shouldIncludeAirbnbCredit, this.settlementCurrency);
            return;
        }
        launchAddCouponFlow();
    }

    /* access modifiers changed from: protected */
    public void requestPaymentOptions() {
        showLoadingState(true);
        this.paymentOptionsApi.getPaymentOptions(this.cartItem.quickPayParameters().productType().getServerKey(), this.mAccountManager.getCurrentUser().getDefaultCountryOfResidence(), ((HomesClientParameters) this.cartItem.quickPayParameters()).isBusinessTrip().booleanValue());
    }

    /* access modifiers changed from: protected */
    public void setDefaultPaymentOption(List<PaymentOption> paymentOptions) {
        if (((HomesClientParameters) this.cartItem.quickPayParameters()).isBusinessTrip().booleanValue()) {
            PaymentOption businessPaymentOption = PaymentUtils.getBusinessPaymentOption(paymentOptions);
            if (businessPaymentOption == null) {
                businessPaymentOption = PaymentUtils.getDefaultPaymentOption(paymentOptions);
            }
            this.selectedPaymentOption = businessPaymentOption;
            this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
            return;
        }
        super.setDefaultPaymentOption(paymentOptions);
    }

    /* access modifiers changed from: protected */
    public void sendBill() {
        disablePayButton();
        setPayButtonToLoading();
        this.identityClient.doIdentityCheck(getActivity(), this.requestManager, this.reservation, this);
    }

    /* access modifiers changed from: protected */
    public void executeBillPriceQuoteRequest(boolean shouldIncludeAirbnbCredit, String displayCurrency) {
        this.billPriceQuoteApi.fetchBillPriceQuoteV2(BillPriceQuoteRequestParams.builder().clientType(this.clientType).paymentOption(this.selectedPaymentOption).includeAirbnbCredit(shouldIncludeAirbnbCredit).quickPayParameters(this.cartItem.quickPayParameters()).userAgreedToCurrencyMismatch(this.userAgreedToCurrencyMismatch).displayCurrency(displayCurrency).couponCode(this.couponCode).zipRetry(this.postalCode).build());
    }

    /* access modifiers changed from: protected */
    public void onBillRequestCompleted(Bill bill) {
        BookingResult bookingResult = null;
        confirmAndPayLogging(true, null);
        if (bill.bookingResults() != null) {
            bookingResult = (BookingResult) bill.bookingResults().get(0);
        }
        if (bookingResult == null || !bookingResult.success()) {
            ErrorUtils.showErrorUsingSnackbar(getView(), getBookingResultErrorMessage(bookingResult.errors()));
            setPayButtonToNormal();
            return;
        }
        refreshReservation();
    }

    /* access modifiers changed from: protected */
    public void onPriceQuoteChanged(BillPriceQuote priceQuote) {
        super.onPriceQuoteChanged(priceQuote);
        this.billPriceQuoteListener.onBillPriceQuoteChanged(priceQuote);
    }

    /* access modifiers changed from: protected */
    public boolean shouldApplyCreditByDefault() {
        this.shouldIncludeAirbnbCredit = !((HomesClientParameters) this.cartItem.quickPayParameters()).isBusinessTrip().booleanValue();
        return this.shouldIncludeAirbnbCredit;
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(true);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.BookingQuickpay;
    }

    /* access modifiers changed from: protected */
    public void setPayButtonPrice(String price) {
        String payButtonText;
        if (!PaymentUtils.isValidPaymentOption(this.selectedPaymentOption)) {
            payButtonText = getString(C0880R.string.quick_pay_add_payment);
        } else if (this.billPriceQuote.getInstallments().size() > 1 && PricingFeatureToggles.showInstallmentsRow()) {
            payButtonText = getString(C0880R.string.quick_pay_button_text_confirm_payment);
        } else if (this.reservation.isInstantBookable()) {
            payButtonText = getString(C0880R.string.quick_pay_button_text_confirm_booking, price);
        } else {
            payButtonText = getString(C0880R.string.quick_pay_button_text_homes_request_to_book, price);
        }
        this.payButton.setButtonText((CharSequence) payButtonText);
    }

    /* access modifiers changed from: protected */
    public QuickPayConfirmAndPayParams getConfirmAndPayJitneyParams(long totalPrice, BillProductType productType) {
        return new QuickPayConfirmAndPayParams(this.selectedPaymentOption, this.settlementCurrency, this.reservation.getConfirmationCode(), totalPrice, productType);
    }

    public void identityCheckSuccess() {
        createBill(createBillParameters(this.billProductType).build());
    }

    public void showErrorMessage(String title, String subtitle, String actionText) {
        ErrorUtils.showErrorUsingSnackbar(getView(), title, subtitle, actionText, HomesQuickPayFragment$$Lambda$3.lambdaFactory$(this), -2);
    }

    public void showPendingMessage(String title, String subtitle) {
        ErrorUtils.showErrorUsingSnackbar(getView(), title, subtitle, -2);
    }

    public void identityCheckError(NetworkException networkException) {
        NetworkUtil.tryShowErrorWithSnackbar(getView(), networkException);
    }

    /* access modifiers changed from: private */
    public void finishWithIdentityError() {
        getActivity().setResult(-100);
        getActivity().finish();
    }

    private void launchAddCouponFlow() {
        startActivityForResult(AddCouponCodeActivity.intentForAddCouponCode(getActivity(), this.clientType, this.selectedPaymentOption, this.cartItem.quickPayParameters(), this.billPriceQuote.getPrice().hasGiftCredit(), this.userAgreedToCurrencyMismatch), 2000);
    }

    private boolean isCouponCodeApplied() {
        return this.billPriceQuote.getPrice().hasCouponCode();
    }

    /* access modifiers changed from: private */
    public void refreshReservation() {
        ReservationRequest.forReservationId(this.reservation.getId(), Format.Guest).withListener((Observer) this.reservationListener).skipCache().execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void returnReservationResult(ReservationResponse response) {
        Intent intent = new Intent();
        intent.putExtra(QuickPayActivityIntents.RESULT_EXTRA_RESERVATION, response.reservation);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void showReservationError(NetworkException networkException) {
        ErrorResponse errorResponse = (ErrorResponse) networkException.errorResponse();
        ErrorUtils.showErrorUsingSnackbar(getView(), getString(C0880R.string.error), errorResponse == null ? getString(C0880R.string.error_request) : errorResponse.errorMessage, getString(C0880R.string.retry), HomesQuickPayFragment$$Lambda$4.lambdaFactory$(this), -2);
    }

    private String getBookingResultErrorMessage(List<Error> errors) {
        if (errors.isEmpty()) {
            return getString(C0880R.string.quick_pay_homes_booking_result_error);
        }
        return ((Error) errors.get(0)).msg();
    }
}
