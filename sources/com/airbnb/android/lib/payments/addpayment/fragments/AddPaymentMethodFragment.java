package com.airbnb.android.lib.payments.addpayment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.booking.activities.AlipayActivity;
import com.airbnb.android.booking.activities.AlipayV2Activity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.models.payments.OldPaymentInstrument.InstrumentType;
import com.airbnb.android.core.models.payments.OtherPaymentInstrument;
import com.airbnb.android.core.models.payments.PayPalInstrument;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.activities.CreditCardDetailsActivity;
import com.airbnb.android.lib.payments.addpayment.activities.SelectBillingCountryActivity;
import com.airbnb.android.lib.payments.addpayment.adapters.AddPaymentMethodEpoxyController;
import com.airbnb.android.lib.payments.addpayment.clicklisteners.AddPaymentMethodListener;
import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import com.airbnb.android.lib.payments.braintree.PayPalApi;
import com.airbnb.android.lib.payments.braintree.PayPalTokenizer.PayPalListener;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsApi;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsDelegate;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsDelegate.PaymentOptionsDelegateListener;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.epoxy.EpoxyControllerAdapter;
import com.airbnb.p027n2.components.AirToolbar;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.interfaces.BraintreeCancelListener;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AddPaymentMethodFragment extends AirFragment implements AddPaymentMethodListener, PayPalListener, PaymentOptionsDelegateListener {
    private static final String ARG_PAYMENT_OPTIONS = "arg_payment_options";
    private static final String ARG_PRODUCT_TYPE = "arg_product_type";
    private static final int ERROR_CODE_BRAINTREE_ERROR = 302;
    private static final int REQUEST_CODE_ADD_CREDIT_CARD = 101;
    private static final int REQUEST_CODE_ALIPAY = 102;
    private static final int REQUEST_CODE_SELECT_COUNTRY = 100;
    public static final String RESULT_EXTRA_CVV_PAYLOAD = "result_extra_cvv_payload";
    public static final String RESULT_EXTRA_PAYMENT_INSTRUMENT = "result_extra_payment_instrument";
    @State
    BillProductType billProductType;
    private final BraintreeCancelListener braintreeCancelListener = AddPaymentMethodFragment$$Lambda$2.lambdaFactory$(this);
    private final BraintreeErrorListener braintreeErrorListener = AddPaymentMethodFragment$$Lambda$3.lambdaFactory$(this);
    BraintreeFactory braintreeFactory;
    private BraintreeFragment braintreeFragment;
    private final BraintreeResponseListener<String> braintreeResponseListener = new BraintreeResponseListener<String>() {
        public void onResponse(String deviceData) {
            ((PayPalInstrument) AddPaymentMethodFragment.this.paymentInstrument).setDeviceData(deviceData);
            AddPaymentMethodFragment.this.payPalApi.vaultPayPalInstrument((PayPalInstrument) AddPaymentMethodFragment.this.paymentInstrument);
        }
    };
    private AddPaymentMethodEpoxyController controller;
    @State
    String countryCode;
    @State
    boolean isLoading;
    private final PaymentMethodNonceCreatedListener nonceCreatedListener = AddPaymentMethodFragment$$Lambda$1.lambdaFactory$(this);
    /* access modifiers changed from: private */
    public PayPalApi payPalApi;
    @State
    OldPaymentInstrument paymentInstrument;
    @State
    ArrayList<PaymentOption> paymentOptions;
    private PaymentOptionsApi paymentOptionsApi;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static AddPaymentMethodFragment newInstance(BillProductType productType, ArrayList<PaymentOption> paymentOptions2) {
        return (AddPaymentMethodFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AddPaymentMethodFragment()).putSerializable(ARG_PRODUCT_TYPE, productType)).putParcelableArrayList(ARG_PAYMENT_OPTIONS, paymentOptions2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.billProductType = (BillProductType) getArguments().getSerializable(ARG_PRODUCT_TYPE);
            this.countryCode = this.mAccountManager.getCurrentUser().getDefaultCountryOfResidence();
            this.paymentOptions = getArguments().getParcelableArrayList(ARG_PAYMENT_OPTIONS);
        }
        this.braintreeFragment = this.braintreeFactory.createBraintreeFragment(getActivity());
        this.braintreeFragment.addListener(this.nonceCreatedListener);
        this.braintreeFragment.addListener(this.braintreeErrorListener);
        this.braintreeFragment.addListener(this.braintreeCancelListener);
        this.paymentOptionsApi = new PaymentOptionsDelegate(this.requestManager, this);
        this.payPalApi = this.braintreeFactory.createPayPalApi(this.braintreeFragment, this.requestManager, this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_add_payment_method, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        createAndSetAdapter();
        if (this.paymentOptions == null) {
            loadPaymentOptions();
        }
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 100:
                    this.paymentOptions = data.getParcelableArrayListExtra("result_extra_payment_options");
                    this.countryCode = data.getStringExtra(SelectBillingCountryActivity.RESULT_EXTRA_COUNTRY_CODE);
                    onBillingCountrySelected();
                    return;
                case 101:
                    onCreditCardAdded(data);
                    return;
                case 102:
                    onAliPayAdded(data);
                    return;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    return;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.braintreeFragment.removeListener(this.nonceCreatedListener);
        this.braintreeFragment.removeListener(this.braintreeErrorListener);
        this.braintreeFragment.removeListener(this.braintreeCancelListener);
    }

    public void onBillingCountryClicked() {
        if (this.billProductType != BillProductType.GiftCredit) {
            startActivityForResult(SelectBillingCountryActivity.intentForCountryCode(getActivity(), this.billProductType, this.countryCode), 100);
        }
    }

    public void onPaymentMethodSelected(PaymentMethodType paymentMethod) {
        if (!PaymentUtils.isAddPaymentFlowEnabledFor(this.billProductType, this.countryCode)) {
            String displayCountry = new Locale("", this.countryCode).getDisplayCountry();
            ErrorUtils.showErrorUsingSnackbar(getView(), getString(C0880R.string.quick_pay_add_payment_blocked_country_text, displayCountry));
            return;
        }
        switch (paymentMethod) {
            case CreditCard:
            case DigitalRiverCreditCard:
                startActivityForResult(CreditCardDetailsActivity.intentForAddCreditCard(getActivity(), paymentMethod, this.countryCode), 101);
                return;
            case PayPal:
                showLoading(true);
                this.payPalApi.tokenize(Collections.singletonList(PayPal.SCOPE_ADDRESS));
                return;
            case Alipay:
                if (shouldLaunchNewAlipayFlow()) {
                    startActivityForResult(AlipayV2Activity.intentForQuickPay(getActivity()), 102);
                    return;
                } else {
                    startActivityForResult(AlipayActivity.intentForQuickPay(getActivity(), this.countryCode), 102);
                    return;
                }
            case iDEAL:
            case PayU:
            case Sofort:
                onPaymentWithRedirectSelected(paymentMethod);
                return;
            default:
                return;
        }
    }

    public void onPayPalVaulted(PaymentInstrument instrument) {
        this.paymentInstrument.setId(instrument.getId());
        ((PayPalInstrument) this.paymentInstrument).setEmail(instrument.getDetailText());
        Intent intent = new Intent();
        intent.putExtra("result_extra_payment_instrument", this.paymentInstrument);
        finish(intent);
    }

    public void onPayPalVaultError(NetworkException networkException) {
        NetworkUtil.tryShowErrorWithSnackbar(getView(), networkException);
    }

    private void createAndSetAdapter() {
        this.controller = new AddPaymentMethodEpoxyController(this.paymentOptions, this, new Locale("", this.countryCode).getDisplayCountry());
        setAdapter(this.controller.getAdapter());
        showLoading(this.isLoading);
    }

    private void setAdapter(EpoxyControllerAdapter adapter) {
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setHasFixedSize(true);
    }

    private void swapAdapter(EpoxyControllerAdapter adapter) {
        this.recyclerView.swapAdapter(adapter, true);
        this.recyclerView.setHasFixedSize(true);
    }

    /* access modifiers changed from: private */
    public void showLoading(boolean isLoading2) {
        this.isLoading = isLoading2;
        this.controller.setLoading(isLoading2);
    }

    private void onBillingCountrySelected() {
        this.controller = new AddPaymentMethodEpoxyController(this.paymentOptions, this, new Locale("", this.countryCode).getDisplayCountry());
        swapAdapter(this.controller.getAdapter());
    }

    private void onCreditCardAdded(Intent data) {
        OldPaymentInstrument creditCard = (OldPaymentInstrument) data.getSerializableExtra("result_extra_payment_instrument");
        String cvvPayload = data.getStringExtra("result_extra_cvv_payload");
        Intent resultData = new Intent();
        resultData.putExtra("result_extra_payment_instrument", creditCard);
        resultData.putExtra("result_extra_cvv_payload", cvvPayload);
        finish(resultData);
    }

    private void onAliPayAdded(Intent data) {
        OldPaymentInstrument paymentInstrument2;
        if (shouldLaunchNewAlipayFlow()) {
            paymentInstrument2 = (OldPaymentInstrument) data.getSerializableExtra("result_code_alipay_payment_instrument");
        } else {
            paymentInstrument2 = (OldPaymentInstrument) data.getSerializableExtra("result_code_alipay_payment_instrument");
        }
        Intent resultData = new Intent();
        resultData.putExtra("result_extra_payment_instrument", paymentInstrument2);
        finish(resultData);
    }

    private void onPaymentWithRedirectSelected(PaymentMethodType paymentMethodType) {
        OtherPaymentInstrument instrument = new OtherPaymentInstrument();
        instrument.setName(getString(C0880R.string.payment_type_credit_or_debit_card));
        switch (paymentMethodType) {
            case iDEAL:
                instrument.setType(InstrumentType.iDEAL);
                break;
            case PayU:
                instrument.setType(InstrumentType.PayU);
                break;
            case Sofort:
                instrument.setType(InstrumentType.Sofort);
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("result_extra_payment_instrument", instrument);
        finish(intent);
    }

    private boolean shouldLaunchNewAlipayFlow() {
        return ChinaUtils.isAlipayInstalled(getActivity());
    }

    private void finish(Intent resultData) {
        getActivity().setResult(-1, resultData);
        getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void loadPaymentOptions() {
        showLoading(true);
        this.paymentOptionsApi.getPaymentOptions(this.billProductType != null ? this.billProductType.getServerKey() : null, this.countryCode != null ? this.countryCode : this.mAccountManager.getCurrentUser().getDefaultCountryOfResidence(), false);
    }

    public void onPaymentOptionsRequestSuccess(List<PaymentOption> options) {
        this.paymentOptions = Lists.newArrayList((Iterable<? extends E>) options);
        this.controller.setData(this.paymentOptions);
        showLoading(false);
    }

    public void onPaymentOptionsRequestError(NetworkException networkException) {
        ErrorUtils.showErrorUsingSnackbarWithAction(getView(), getString(C0880R.string.error), NetworkUtil.errorMessage(networkException), getString(C0880R.string.ro_try_again), AddPaymentMethodFragment$$Lambda$4.lambdaFactory$(this), -2);
        showLoading(false);
    }

    static /* synthetic */ void lambda$new$1(AddPaymentMethodFragment addPaymentMethodFragment, PaymentMethodNonce paymentMethodNonce) {
        if (paymentMethodNonce instanceof PayPalAccountNonce) {
            addPaymentMethodFragment.paymentInstrument = new PayPalInstrument((PayPalAccountNonce) paymentMethodNonce);
            DataCollector.collectDeviceData(addPaymentMethodFragment.braintreeFragment, addPaymentMethodFragment.braintreeResponseListener);
        }
    }

    static /* synthetic */ void lambda$new$3(AddPaymentMethodFragment addPaymentMethodFragment, Exception error) {
        ErrorUtils.showErrorUsingSnackbar(addPaymentMethodFragment.getView(), addPaymentMethodFragment.getString(C0880R.string.error_braintree), -2);
        AirbnbEventLogger.event().name("p4_mobile_error").mo8236kv("errorCode", 302).mo8238kv("errorMessage", error.getMessage());
    }
}
