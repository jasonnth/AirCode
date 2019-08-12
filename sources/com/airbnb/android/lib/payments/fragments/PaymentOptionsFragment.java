package com.airbnb.android.lib.payments.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.payments.AndroidPayInstrument;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.AndroidPayBody;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.addpayment.activities.AddPaymentMethodActivity;
import com.airbnb.android.lib.payments.braintree.AndroidPayApi;
import com.airbnb.android.lib.payments.braintree.BraintreeCreditCardApi;
import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapter;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapter.PaymentOptionsAdapterListener;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapterFactory;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.QuickpayWalletSection.p218v1.C2599QuickpayWalletSection;
import com.airbnb.p027n2.components.AirToolbar;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.AndroidPayCardNonce;
import com.braintreepayments.api.models.CardNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class PaymentOptionsFragment extends AirFragment implements PaymentOptionsAdapterListener {
    private static final String ARG_CLIENT_TYPE = "arg_client_type";
    private static final String ARG_PAYMENT_OPTIONS = "arg_payment_options";
    private static final String ARG_SELECTED_PAYMENT_OPTION = "arg_payment_option";
    private static final String ARG_TOTAL_PRICE = "arg_total_price";
    private static final int REQUEST_CODE_ADD_PAYMENT = 15021;
    public static final int REQUEST_CODE_CHANGE_MASKED_WALLET = 601;
    public static final int REQUEST_CODE_FULL_WALLET = 602;
    public static final int REQUEST_CODE_MASKED_WALLET = 600;
    private PaymentOptionsAdapter adapter;
    private AndroidPayApi androidPayApi;
    BraintreeFactory braintreeFactory;
    private BraintreeFragment braintreeFragment;
    @State
    QuickPayClientType clientType;
    final RequestListener<PaymentInstrumentResponse> createAndroidPayInstrumentListener = new C0699RL().onResponse(PaymentOptionsFragment$$Lambda$1.lambdaFactory$(this)).onError(PaymentOptionsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    BraintreeCreditCard creditCard;
    private BraintreeCreditCardApi creditCardApi;
    @State
    boolean didCancelUpdateAndroidPay;
    @State
    boolean didFailAndroidPay;
    @State
    boolean didUpdateAndroidPay;
    /* access modifiers changed from: private */
    public GoogleApiClient googleApiClient;
    @State
    FullWallet googleFullWallet;
    @State
    MaskedWallet googleMaskedWallet;
    QuickPayJitneyLogger jitneyLogger;
    private final PaymentMethodNonceCreatedListener nonceCreatedListener = new PaymentMethodNonceCreatedListener() {
        public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
            if (paymentMethodNonce instanceof CardNonce) {
                BraintreeCreditCard paymentInstrument = (BraintreeCreditCard) PaymentOptionsFragment.this.selectedPaymentOption.toLegacyPaymentInstrument();
                paymentInstrument.setNonce(paymentMethodNonce.getNonce());
                PaymentOptionsFragment.this.paymentOptionsListener.onPaymentOptionSelected(PaymentOptionsFragment.this.paymentOptions, PaymentOptionsFragment.this.selectedPaymentOption, paymentInstrument.getNonce());
            } else if (paymentMethodNonce instanceof AndroidPayCardNonce) {
                AndroidPayInstrument paymentInstrument2 = (AndroidPayInstrument) PaymentOptionsFragment.this.selectedPaymentOption.toLegacyPaymentInstrument();
                paymentInstrument2.setNonce(paymentMethodNonce.getNonce());
                paymentInstrument2.setCountryCode(PaymentOptionsFragment.this.googleFullWallet.getBuyerBillingAddress().getCountryCode());
                paymentInstrument2.setPostalCode(PaymentOptionsFragment.this.googleFullWallet.getBuyerBillingAddress().getPostalCode());
                PaymentOptionsFragment.this.createAndroidPayInstrument(paymentInstrument2);
            }
        }
    };
    PaymentOptionFactory paymentOptionFactory;
    @State
    ArrayList<PaymentOption> paymentOptions;
    PaymentOptionsAdapterFactory paymentOptionsAdapterFactory;
    /* access modifiers changed from: private */
    public PaymentOptionsListener paymentOptionsListener;
    @BindView
    RecyclerView recyclerView;
    @State
    PaymentOption selectedPaymentOption;
    private Snackbar snackbar;
    @BindView
    AirToolbar toolbar;
    @State
    Price totalPrice;

    public interface PaymentOptionsListener {
        void onPaymentOptionSelected(List<PaymentOption> list, PaymentOption paymentOption, String str);
    }

    public static PaymentOptionsFragment instanceForPaymentOptions(ArrayList<PaymentOption> paymentOptions2, PaymentOption selectedPaymentOption2, QuickPayClientType clientType2, Price totalPrice2) {
        return (PaymentOptionsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PaymentOptionsFragment()).putParcelableArrayList(ARG_PAYMENT_OPTIONS, paymentOptions2)).putParcelable(ARG_SELECTED_PAYMENT_OPTION, selectedPaymentOption2)).putSerializable(ARG_CLIENT_TYPE, clientType2)).putParcelable(ARG_TOTAL_PRICE, totalPrice2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payment_options, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (savedInstanceState == null) {
            this.paymentOptions = getArguments().getParcelableArrayList(ARG_PAYMENT_OPTIONS);
            this.selectedPaymentOption = (PaymentOption) getArguments().getParcelable(ARG_SELECTED_PAYMENT_OPTION);
            this.clientType = (QuickPayClientType) getArguments().getSerializable(ARG_CLIENT_TYPE);
            this.totalPrice = (Price) getArguments().getParcelable(ARG_TOTAL_PRICE);
        }
        setupAdapter(this.paymentOptions, this.selectedPaymentOption);
        this.braintreeFragment = this.braintreeFactory.createBraintreeFragment(getActivity());
        this.braintreeFragment.addListener(this.nonceCreatedListener);
        this.creditCardApi = this.braintreeFactory.createCreditCardApi(this.braintreeFragment);
        this.androidPayApi = this.braintreeFactory.createAndroidPayApi(getActivity(), this.braintreeFragment, this.mCurrencyHelper);
        this.androidPayApi.createGoogleApiClient(PaymentOptionsFragment$$Lambda$3.lambdaFactory$(this));
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.paymentOptionsListener = (PaymentOptionsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement PaymentOptionsListener interface.");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 600 || requestCode == 601) {
            onMaskedWalletResult(resultCode, data);
        } else if (requestCode == 602) {
            onFullWalletResult(resultCode, data);
        } else if (requestCode == REQUEST_CODE_ADD_PAYMENT) {
            onAddPaymentResult(resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onPaymentOptionSelected(PaymentOption paymentOption) {
        this.adapter.setSelectedPaymentOption(paymentOption);
        this.selectedPaymentOption = paymentOption;
        this.jitneyLogger.paymentOptionsEvent(C2599QuickpayWalletSection.SelectExisting, BillProductType.getProductTypeFromClientType(this.clientType), this.selectedPaymentOption);
        if (paymentOption.isAndroidPay()) {
            this.androidPayApi.loadMaskedAndroidPayWallet(600, this.totalPrice.getTotal().getAmount().intValue(), this.googleApiClient);
        } else {
            this.paymentOptionsListener.onPaymentOptionSelected(this.paymentOptions, paymentOption, null);
        }
    }

    public void onAddPaymentMethodSelected() {
        BillProductType billProductType = BillProductType.getProductTypeFromClientType(this.clientType);
        this.jitneyLogger.paymentOptionsEvent(C2599QuickpayWalletSection.Add, billProductType);
        if (PaymentUtils.shouldShowNewAddPaymentMethodFlow()) {
            startActivityForResult(AddPaymentMethodActivity.intentForAddPayment(getActivity(), billProductType, this.paymentOptions), REQUEST_CODE_ADD_PAYMENT);
        } else {
            startActivityForResult(LegacyAddPaymentMethodActivity.intentForQuickPayWithClientType(getActivity(), this.clientType), REQUEST_CODE_ADD_PAYMENT);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.didUpdateAndroidPay) {
            this.didUpdateAndroidPay = false;
        } else if (this.didCancelUpdateAndroidPay) {
            this.didCancelUpdateAndroidPay = false;
        } else if (this.didFailAndroidPay) {
            this.didFailAndroidPay = false;
            handleAndroidPayError();
        }
    }

    public void onDestroyView() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        super.onDestroyView();
        this.braintreeFragment.removeListener(this.nonceCreatedListener);
    }

    private void onAddPaymentResult(int resultCode, Intent data) {
        PaymentOption newPaymentOption;
        if (resultCode == -1) {
            if (PaymentUtils.shouldShowNewAddPaymentMethodFlow()) {
                OldPaymentInstrument paymentInstrument = (OldPaymentInstrument) data.getSerializableExtra("result_extra_payment_instrument");
                String cvvPayload = data.getStringExtra("result_extra_cvv_payload");
                newPaymentOption = this.paymentOptionFactory.forNewInstrument(paymentInstrument);
                newPaymentOption.setCvvPayload(cvvPayload);
                this.paymentOptions.add(0, newPaymentOption);
                this.paymentOptionsListener.onPaymentOptionSelected(this.paymentOptions, newPaymentOption, cvvPayload);
            } else {
                newPaymentOption = this.paymentOptionFactory.forNewInstrument((OldPaymentInstrument) data.getSerializableExtra("result_extra_payment_instrument"));
                this.paymentOptions.add(0, newPaymentOption);
                this.selectedPaymentOption = newPaymentOption;
                swapAdapter(this.paymentOptions, newPaymentOption);
                this.creditCard = (BraintreeCreditCard) data.getSerializableExtra("result_extra_credit_card");
                if (this.clientType != QuickPayClientType.GiftCard || this.creditCard == null) {
                    this.paymentOptionsListener.onPaymentOptionSelected(this.paymentOptions, this.selectedPaymentOption, null);
                } else {
                    this.creditCardApi.tokenize(new BraintreeCreditCard(this.creditCard.getSecurityCode()));
                    this.adapter.toggleLoadingState(true);
                }
            }
            this.jitneyLogger.paymentOptionsEvent(C2599QuickpayWalletSection.SuccessfulVault, BillProductType.getProductTypeFromClientType(this.clientType), newPaymentOption);
        }
    }

    private void onFullWalletResult(int resultCode, Intent data) {
        if (resultCode == -1) {
            this.googleFullWallet = (FullWallet) data.getParcelableExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET");
            this.androidPayApi.tokenizeAndroidPay(this.googleFullWallet);
        }
    }

    private void onMaskedWalletResult(int resultCode, Intent data) {
        if (resultCode == -1) {
            this.googleMaskedWallet = (MaskedWallet) data.getParcelableExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET");
            this.androidPayApi.loadFullAndroidPayWallet(602, this.totalPrice.getTotal().getAmount().intValue(), this.mCurrencyHelper.getLocalCurrencyString(), this.googleMaskedWallet, this.googleApiClient);
            this.didUpdateAndroidPay = true;
        } else if (resultCode == 0) {
            this.didCancelUpdateAndroidPay = true;
        } else {
            this.didFailAndroidPay = true;
        }
    }

    private void setupAdapter(List<PaymentOption> paymentOptions2, PaymentOption selectedPaymentOption2) {
        this.adapter = this.paymentOptionsAdapterFactory.createAdapter(paymentOptions2, selectedPaymentOption2, this);
        this.adapter.setSelectedPaymentOption(selectedPaymentOption2);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
    }

    private void swapAdapter(List<PaymentOption> paymentOptions2, PaymentOption selectedPaymentOption2) {
        this.adapter = this.paymentOptionsAdapterFactory.createAdapter(paymentOptions2, selectedPaymentOption2, this);
        this.adapter.setSelectedPaymentOption(selectedPaymentOption2);
        this.recyclerView.swapAdapter(this.adapter, true);
    }

    private void handleAndroidPayError() {
        this.snackbar = ErrorUtils.showErrorUsingSnackbar(getView(), getString(C0880R.string.error_paying_with_android_pay), 0);
    }

    /* access modifiers changed from: private */
    public void createAndroidPayInstrument(AndroidPayInstrument paymentInstrument) {
        CreatePaymentInstrumentRequest.forAndroidPay(AndroidPayBody.make().paymentMethodNonce(paymentInstrument.getNonce()).postalCode(paymentInstrument.getPostalCode()).country(paymentInstrument.getCountryCode()).build()).withListener((Observer) this.createAndroidPayInstrumentListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$1(PaymentOptionsFragment paymentOptionsFragment, PaymentInstrumentResponse response) {
        PaymentOption androidPay = PaymentUtils.getPaymentOptionByPaymentMethodType(paymentOptionsFragment.paymentOptions, PaymentMethodType.AndroidPay);
        paymentOptionsFragment.paymentOptions.remove(androidPay);
        androidPay.setGibraltarInstrumentId(response.paymentInstrument.getId());
        androidPay.setIsExistingInstrument(true);
        paymentOptionsFragment.paymentOptions.add(androidPay);
        paymentOptionsFragment.selectedPaymentOption = androidPay;
        paymentOptionsFragment.jitneyLogger.paymentOptionsEvent(C2599QuickpayWalletSection.SuccessfulVault, BillProductType.getProductTypeFromClientType(paymentOptionsFragment.clientType), paymentOptionsFragment.selectedPaymentOption);
        paymentOptionsFragment.paymentOptionsListener.onPaymentOptionSelected(paymentOptionsFragment.paymentOptions, paymentOptionsFragment.selectedPaymentOption, null);
    }
}
