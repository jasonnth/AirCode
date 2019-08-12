package com.airbnb.android.lib.payments.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.PriceQuote;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestFactory;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestParams;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.PaymentInputLayout;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import p032rx.Observer;

public class AddCouponCodeFragment extends AirFragment {
    private static final String ARG_CLIENT_TYPE = "arg_client_type";
    private static final String ARG_PAYMENT_OPTION = "arg_payment_option";
    private static final String ARG_QUICK_PAY_PARAMETERS = "arg_quick_pay_parameters";
    private static final String ARG_SHOULD_INCLUDE_AIRBNB_CREDIT = "arg_should_include_airbnb_credit";
    private static final String ARG_USER_AGREED_TO_CURRENCY_MISMATCH = "arg_user_agreed_to_currency_mismatch";
    @BindView
    AirButton applyButton;
    @State
    QuickPayClientType clientType;
    @BindView
    PaymentInputLayout couponCodeInputLayout;
    private AddCouponCodeListener couponCodeListener;
    @State
    boolean isCreditApplied;
    @BindView
    DocumentMarquee marquee;
    final RequestListener<PriceQuote> priceQuoteRequestListener = new C0699RL().onResponse(AddCouponCodeFragment$$Lambda$1.lambdaFactory$(this)).onError(AddCouponCodeFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    QuickPayParameters quickPayParameters;
    BillPriceQuoteRequestFactory requestsFactory;
    @State
    PaymentOption selectedPaymentOption;
    private Snackbar snackbar;
    @BindView
    AirToolbar toolbar;
    @State
    boolean userAgreedToCurrencyMismatch;

    public interface AddCouponCodeListener {
        void onCouponCodeApplied(String str, BillPriceQuote billPriceQuote);
    }

    public static AddCouponCodeFragment newInstance(QuickPayClientType clientType2, PaymentOption paymentOption, QuickPayParameters quickPayParameters2, boolean includeAirbnbCredit, boolean userAgreedToCurrencyMismatch2) {
        return (AddCouponCodeFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AddCouponCodeFragment()).putSerializable(ARG_CLIENT_TYPE, clientType2)).putParcelable(ARG_PAYMENT_OPTION, paymentOption)).putParcelable(ARG_QUICK_PAY_PARAMETERS, quickPayParameters2)).putBoolean(ARG_SHOULD_INCLUDE_AIRBNB_CREDIT, includeAirbnbCredit)).putBoolean(ARG_USER_AGREED_TO_CURRENCY_MISMATCH, userAgreedToCurrencyMismatch2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            Bundle arguments = getArguments();
            this.clientType = (QuickPayClientType) arguments.getSerializable(ARG_CLIENT_TYPE);
            this.selectedPaymentOption = (PaymentOption) arguments.getParcelable(ARG_PAYMENT_OPTION);
            this.quickPayParameters = (QuickPayParameters) arguments.getParcelable(ARG_QUICK_PAY_PARAMETERS);
            this.isCreditApplied = arguments.getBoolean(ARG_SHOULD_INCLUDE_AIRBNB_CREDIT);
            this.userAgreedToCurrencyMismatch = arguments.getBoolean(ARG_USER_AGREED_TO_CURRENCY_MISMATCH);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_add_coupon_code, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.marquee.setTitle((CharSequence) getString(C0880R.string.quick_pay_add_coupon_text));
        this.couponCodeInputLayout.setTitle(C0880R.string.quick_pay_add_coupon_title);
        this.couponCodeInputLayout.setHint(C0880R.string.quick_pay_add_coupon_hint);
        this.couponCodeInputLayout.setInputTypeToText();
        this.couponCodeInputLayout.addTextChangedListener(new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                AddCouponCodeFragment.this.applyButton.setEnabled(!s.toString().isEmpty());
            }
        });
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.couponCodeListener = (AddCouponCodeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddCouponCodeListener interface.");
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
    }

    @OnClick
    public void onApplyClicked() {
        this.applyButton.setState(AirButton.State.Loading);
        this.requestsFactory.createBillPriceQuoteRequestV2(BillPriceQuoteRequestParams.builder().clientType(this.clientType).paymentOption(this.selectedPaymentOption).includeAirbnbCredit(this.isCreditApplied).quickPayParameters(this.quickPayParameters).displayCurrency(this.mCurrencyHelper.getLocalCurrencyString()).couponCode(this.couponCodeInputLayout.getText().toString()).userAgreedToCurrencyMismatch(this.userAgreedToCurrencyMismatch).build()).withListener((Observer) this.priceQuoteRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void onCouponCodeApplied(BillPriceQuote billPriceQuote) {
        KeyboardUtils.dismissSoftKeyboard(getView());
        this.couponCodeListener.onCouponCodeApplied(this.couponCodeInputLayout.getText().toString(), billPriceQuote);
    }

    /* access modifiers changed from: private */
    public void onCouponCodeError(NetworkException networkException) {
        this.applyButton.setState(AirButton.State.Normal);
        this.couponCodeInputLayout.showError();
        this.snackbar = ErrorUtils.showErrorUsingSnackbar(getView(), NetworkUtil.errorMessage(networkException), 0);
    }
}
