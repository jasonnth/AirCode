package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.BookingGraph;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.booking.p336n2.PaymentMethodSelectionView;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.enums.PaymentMethod;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;

public class SelectPaymentMethodFragment extends AirFragment {
    private static final String ARG_COUNTRY_CODE = "arg_country_code";
    private static final String ARG_HIDE_ALIPAY_DIRECT = "hide_alipay_direct";
    @State
    String countryCode;
    @State
    boolean hideAlipayDirect;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    QuickPayJitneyLogger quickPayJitneyLogger;
    @BindView
    PaymentMethodSelectionView selectionSheetPresenter;

    public static SelectPaymentMethodFragment instanceForCountry(String countryCode2, boolean hideAlipayDirect2) {
        return (SelectPaymentMethodFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SelectPaymentMethodFragment()).putString("arg_country_code", countryCode2)).putBoolean(ARG_HIDE_ALIPAY_DIRECT, hideAlipayDirect2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BookingGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_select_payment_method, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.hideAlipayDirect = getArguments().getBoolean(ARG_HIDE_ALIPAY_DIRECT, false);
        }
        if (this.countryCode == null) {
            this.countryCode = getArguments().getString("arg_country_code");
        }
        initializeSelectionSheetPresenter();
        return view;
    }

    public void displayLoader() {
        this.nextButton.setState(AirButton.State.Loading);
    }

    public void hideLoader() {
        this.nextButton.setState(AirButton.State.Normal);
    }

    private void initializeSelectionSheetPresenter() {
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>(Arrays.asList(PaymentMethod.values()));
        LegacyAddPaymentMethodActivity addPaymentMethodActivity = (LegacyAddPaymentMethodActivity) getActivity();
        if (!addPaymentMethodActivity.getPaymentManagerFragment().isPayPalEnabled() || AppLaunchUtils.isIndiaRegion() || addPaymentMethodActivity.isLaunchedFromGiftCard()) {
            paymentMethods.remove(PaymentMethod.PayPal);
        }
        if (this.hideAlipayDirect || !this.countryCode.equals(AirbnbConstants.COUNTRY_CODE_CHINA) || !Experiments.isAlipayDirectEnabled() || addPaymentMethodActivity.isLaunchedFromGiftCard()) {
            paymentMethods.remove(PaymentMethod.Alipay);
        }
        this.selectionSheetPresenter.setPaymentMethods(paymentMethods);
        this.selectionSheetPresenter.setSelectionSheetOnItemClickedListener(SelectPaymentMethodFragment$$Lambda$1.lambdaFactory$(this, addPaymentMethodActivity));
    }

    static /* synthetic */ void lambda$initializeSelectionSheetPresenter$0(SelectPaymentMethodFragment selectPaymentMethodFragment, LegacyAddPaymentMethodActivity addPaymentMethodActivity, SimpleSelectionViewItem item) {
        if (selectPaymentMethodFragment.selectionSheetPresenter.hasSelectedItems()) {
            selectPaymentMethodFragment.nextButton.setEnabled(true);
            PaymentMethod selectedPaymentMethod = selectPaymentMethodFragment.selectionSheetPresenter.getSelectedPaymentMethod();
            selectPaymentMethodFragment.selectPaymentMethodLogging(selectedPaymentMethod);
            addPaymentMethodActivity.doneWithSelectPaymentMethod(selectedPaymentMethod);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        if (this.selectionSheetPresenter.hasSelectedItems()) {
            LegacyAddPaymentMethodActivity addPaymentMethodActivity = (LegacyAddPaymentMethodActivity) getActivity();
            PaymentMethod selectedPaymentMethod = this.selectionSheetPresenter.getSelectedPaymentMethod();
            selectPaymentMethodLogging(selectedPaymentMethod);
            addPaymentMethodActivity.doneWithSelectPaymentMethod(selectedPaymentMethod);
        }
    }

    private void selectPaymentMethodLogging(PaymentMethod paymentMethod) {
        if (((LegacyAddPaymentMethodActivity) getActivity()).isQuickPay()) {
            this.quickPayJitneyLogger.selectPaymentMethod(paymentMethod);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.AddPaymentMethod;
    }
}
