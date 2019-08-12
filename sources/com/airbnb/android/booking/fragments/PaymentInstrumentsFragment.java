package com.airbnb.android.booking.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.fragments.androidpay.AndroidPayApiLegacy;
import com.airbnb.android.booking.p336n2.PaymentInstrumentSelectionView;
import com.airbnb.android.booking.p336n2.PaymentInstrumentSelectionViewItem;
import com.airbnb.android.booking.utils.PaymentUtils;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.payments.AndroidPayInstrument;
import com.airbnb.android.core.models.payments.BusinessTravelPaymentInstruments;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.List;

public class PaymentInstrumentsFragment extends BookingBaseFragment {
    private static final String ARG_ALLOW_ALIPAY_REDIRECT = "allow_alipay_redirect";
    private static final String ARG_SHOW_BUSINESS_CENTRAL_PAY = "business_central_pay";
    private static final int REQUEST_CODE_ADD_PAYMENT_METHOD = 10001;
    private static final String TAG = PaymentInstrumentsFragment.class.getSimpleName();
    @BindView
    AirButton addPaymentButton;
    @State
    boolean allowAlipayRedirect;
    private AndroidPayApiLegacy androidPayApi;
    @BindView
    AirButton continueButton;
    @State
    boolean didCancelUpdateAndroidPay;
    @State
    boolean didFailAndroidPay;
    @State
    boolean didUpdateAndroidPay;
    private Menu editMenu;
    @BindView
    JellyfishView jellyfishView;
    private final SelectionSheetOnItemClickedListener<PaymentInstrumentSelectionViewItem> selectionItemListener = PaymentInstrumentsFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    PaymentInstrumentSelectionView selectionSheetPresenter;
    @State
    boolean showBusinessCentralPay;
    private Snackbar snackbar;
    @BindView
    AirToolbar toolbar;

    public static PaymentInstrumentsFragment newInstance(boolean showBusinessCentralPay2, boolean allowAlipayRedirect2) {
        return (PaymentInstrumentsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PaymentInstrumentsFragment()).putBoolean(ARG_SHOW_BUSINESS_CENTRAL_PAY, showBusinessCentralPay2)).putBoolean(ARG_ALLOW_ALIPAY_REDIRECT, allowAlipayRedirect2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_payment_instruments, container, false);
        bindViews(view);
        this.showBusinessCentralPay = getArguments().getBoolean(ARG_SHOW_BUSINESS_CENTRAL_PAY);
        if (savedInstanceState == null) {
            this.allowAlipayRedirect = getArguments().getBoolean(ARG_ALLOW_ALIPAY_REDIRECT);
        }
        getAirActivity().setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        List<OldPaymentInstrument> paymentInstruments = getPaymentInstruments();
        if (!this.showBusinessCentralPay) {
            paymentInstruments = FluentIterable.from((Iterable<E>) paymentInstruments).filter(PaymentInstrumentsFragment$$Lambda$2.lambdaFactory$()).toList();
        }
        this.selectionSheetPresenter.setExistingPaymentInstruments(paymentInstruments);
        this.selectionSheetPresenter.setSelectionSheetOnItemClickedListener(this.selectionItemListener);
        if (!paymentInstruments.isEmpty()) {
            setDefaultInstrument(paymentInstruments);
        }
        ViewUtils.setGoneIf(this.addPaymentButton, PaymentUtils.isOtherPaymentEligible());
        this.androidPayApi = getPaymentManagerFragment();
        return view;
    }

    static /* synthetic */ boolean lambda$onCreateView$0(OldPaymentInstrument instrument) {
        return !(instrument instanceof BusinessTravelPaymentInstruments);
    }

    public void onDestroyView() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        if (this.didUpdateAndroidPay) {
            this.didUpdateAndroidPay = false;
            setAndroidPayAsActiveInstrument();
        } else if (this.didCancelUpdateAndroidPay) {
            this.didCancelUpdateAndroidPay = false;
            this.continueButton.setState(AirButton.State.Normal);
            this.continueButton.setEnabled(this.androidPayApi.isAndroidPayInitialized());
        } else if (this.didFailAndroidPay) {
            this.didFailAndroidPay = false;
            handleAndroidPayError();
        }
    }

    static /* synthetic */ void lambda$new$1(PaymentInstrumentsFragment paymentInstrumentsFragment, PaymentInstrumentSelectionViewItem instrumentView) {
        OldPaymentInstrument instrument = instrumentView.getPaymentInstrument();
        paymentInstrumentsFragment.selectionSheetPresenter.setSelectedPaymentInstrument(instrument);
        if (!(instrument instanceof AndroidPayInstrument) || paymentInstrumentsFragment.continueButton.getState() == AirButton.State.Loading) {
            paymentInstrumentsFragment.editMenu.findItem(C0704R.C0706id.edit_payment).setVisible(false);
            paymentInstrumentsFragment.continueButton.setEnabled(true);
            return;
        }
        paymentInstrumentsFragment.onSelectAndroidPay((AndroidPayInstrument) instrument);
    }

    private void onSelectAndroidPay(AndroidPayInstrument androidPayInstrument) {
        if (this.androidPayApi.isAndroidPayInitialized()) {
            setAndroidPayAsActiveInstrument();
            return;
        }
        this.continueButton.setState(AirButton.State.Loading);
        getPaymentManagerFragment().loadMaskedAndroidPayWallet(androidPayInstrument, getReservationDetails().totalPrice().intValue());
    }

    private void setAndroidPayAsActiveInstrument() {
        if (this.editMenu != null) {
            this.editMenu.findItem(C0704R.C0706id.edit_payment).setVisible(true);
        }
        this.continueButton.setState(AirButton.State.Normal);
        this.continueButton.setEnabled(true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 600 || requestCode == 601) {
            if (resultCode == -1) {
                this.androidPayApi.onMaskedWalletLoaded((MaskedWallet) data.getParcelableExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET"));
                this.didUpdateAndroidPay = true;
            } else if (resultCode == 0) {
                this.didCancelUpdateAndroidPay = true;
            } else {
                this.didFailAndroidPay = true;
            }
        } else if (requestCode != REQUEST_CODE_ADD_PAYMENT_METHOD) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == -1) {
            OldPaymentInstrument instrument = (OldPaymentInstrument) data.getSerializableExtra("result_extra_payment_instrument");
            getPaymentInstruments().add(0, instrument);
            getBookingActivity().doneWithPaymentSelection(instrument);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0704R.C0707menu.payment, menu);
        OldPaymentInstrument paymentInstrument = this.selectionSheetPresenter.getSelectedPaymentInstrument();
        this.editMenu = menu;
        this.editMenu.findItem(C0704R.C0706id.edit_payment).setVisible(paymentInstrument != null && (paymentInstrument instanceof AndroidPayInstrument));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0704R.C0706id.edit_payment) {
            return super.onOptionsItemSelected(item);
        }
        editAndroidPay();
        return true;
    }

    private void editAndroidPay() {
        this.androidPayApi.changeMaskedWallet();
    }

    private void handleAndroidPayError() {
        this.snackbar = new SnackbarWrapper().view(getView()).body(getString(C0704R.string.error_paying_with_android_pay)).duration(0).buildAndShow();
    }

    private void setDefaultInstrument(List<OldPaymentInstrument> instruments) {
        OldPaymentInstrument currentInstrument = getReservationDetails().paymentInstrument();
        if (currentInstrument != null) {
            this.selectionSheetPresenter.setSelectedPaymentInstrument(currentInstrument);
            this.continueButton.setEnabled(true);
        } else if (!(((OldPaymentInstrument) instruments.get(0)) instanceof AndroidPayInstrument)) {
            this.selectionSheetPresenter.setSelectedPaymentInstrument((OldPaymentInstrument) instruments.get(0));
            this.continueButton.setEnabled(true);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSelectPaymentInstrument() {
        OldPaymentInstrument instrument = this.selectionSheetPresenter.getSelectedPaymentInstrument();
        if (instrument == null) {
            this.continueButton.setEnabled(false);
            return;
        }
        KonaBookingAnalytics.trackPaymentOptionSelection(instrument.getDisplayName(getActivity()), instrument, getReservationDetails(), getMobileSearchSessionId());
        getBookingActivity().doneWithPaymentSelection(instrument);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onAddPaymentButtonClicked() {
        startActivityForResult(LegacyAddPaymentMethodActivity.intentForAuthorizationString(getActivity(), getPaymentManagerFragment().getBraintreeAuthorization(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()), this.allowAlipayRedirect), REQUEST_CODE_ADD_PAYMENT_METHOD);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.PaymentSelect;
    }
}
