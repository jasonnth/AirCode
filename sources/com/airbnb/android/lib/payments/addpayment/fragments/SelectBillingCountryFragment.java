package com.airbnb.android.lib.payments.addpayment.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsApi;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsDelegate;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsDelegate.PaymentOptionsDelegateListener;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.BaseSelectionView.Style;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.List;
import java.util.Locale;

public class SelectBillingCountryFragment extends AirFragment implements PaymentOptionsDelegateListener {
    private static final String ARG_COUNTRY_CODE = "arg_country_code";
    private static final String ARG_PRODUCT_TYPE = "arg_product_type";
    @State
    BillProductType billProductType;
    @BindView
    AirButton continueButton;
    @State
    String countryCode;
    private CountrySelectedListener listener;
    private PaymentOptionsApi paymentOptionsApi;
    @BindView
    CountryCodeSelectionView selectionSheetPresenter;
    @BindView
    AirToolbar toolbar;

    public interface CountrySelectedListener {
        void onBillingCountryUpdated(List<PaymentOption> list, String str);
    }

    public static SelectBillingCountryFragment newInstance(BillProductType productType, String countryCode2) {
        return (SelectBillingCountryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SelectBillingCountryFragment()).putSerializable(ARG_PRODUCT_TYPE, productType)).putString("arg_country_code", countryCode2)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (CountrySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement CountrySelectedListener interface");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.billProductType = (BillProductType) getArguments().getSerializable(ARG_PRODUCT_TYPE);
            this.countryCode = getArguments().getString("arg_country_code");
        }
        this.paymentOptionsApi = new PaymentOptionsDelegate(this.requestManager, this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_select_billing_country, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.selectionSheetPresenter.setSelectedCountryCode(this.countryCode);
        this.selectionSheetPresenter.setStyle(Style.WHITE);
        return view;
    }

    @OnClick
    public void onContinueClicked() {
        this.countryCode = this.selectionSheetPresenter.getSelectedCountryCode();
        if (this.countryCode != null) {
            if (!PaymentUtils.isAddPaymentFlowEnabledFor(this.billProductType, this.countryCode)) {
                String displayCountry = new Locale("", this.countryCode).getDisplayCountry();
                ErrorUtils.showErrorUsingSnackbar(getView(), getString(C0880R.string.quick_pay_add_payment_blocked_country_text, displayCountry));
                return;
            }
            this.continueButton.setState(AirButton.State.Loading);
            requestPaymentOptions(this.countryCode);
        }
    }

    public void onPaymentOptionsRequestSuccess(List<PaymentOption> paymentOptions) {
        this.listener.onBillingCountryUpdated(paymentOptions, this.countryCode);
        this.continueButton.setState(AirButton.State.Normal);
    }

    public void onPaymentOptionsRequestError(NetworkException networkException) {
        this.continueButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), networkException);
    }

    private void requestPaymentOptions(String countryCode2) {
        this.paymentOptionsApi.getPaymentOptions(this.billProductType != null ? this.billProductType.getServerKey() : null, countryCode2, false);
    }
}
