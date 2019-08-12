package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.CouponRequest;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class CouponCodeFragment extends BookingBaseFragment {
    private static final String ARG_COUPON_CODE = "arg_coupon_code";
    @BindView
    AirButton applyButton;
    @State
    String couponCode;
    @BindView
    SheetInputText couponCodeInput;
    final RequestListener<ReservationResponse> couponListener = new C0699RL().onResponse(CouponCodeFragment$$Lambda$1.lambdaFactory$(this)).onError(CouponCodeFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    JellyfishView jellyfishView;
    private Snackbar snackbar;
    @BindView
    AirToolbar toolbar;

    public static CouponCodeFragment newInstance(String couponCode2) {
        return (CouponCodeFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CouponCodeFragment()).putString(ARG_COUPON_CODE, couponCode2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_coupon_code, container, false);
        bindViews(view);
        getAirActivity().setSupportActionBar(this.toolbar);
        setHasOptionsMenu(true);
        if (this.couponCode == null) {
            this.couponCode = getArguments().getString(ARG_COUPON_CODE);
        }
        setUpFields();
        setUpTextWatcher();
        return view;
    }

    public void onDestroyView() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        KeyboardUtils.dismissSoftKeyboard(getActivity(), getView());
        super.onDestroyView();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0704R.C0707menu.coupon, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0704R.C0706id.menu_clear) {
            return super.onOptionsItemSelected(item);
        }
        showClearCouponConfirmation();
        return true;
    }

    private void setUpFields() {
        this.couponCodeInput.setText(this.couponCode);
        this.applyButton.setEnabled(!TextUtils.isEmpty(this.couponCode));
    }

    private void setUpTextWatcher() {
        this.couponCodeInput.addTextChangedListener(new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                CouponCodeFragment.this.applyButton.setEnabled(!TextUtils.isEmpty(s) && s.length() > 2);
            }
        });
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void applyCoupon() {
        this.applyButton.setState(AirButton.State.Loading);
        this.couponCode = this.couponCodeInput.getText().toString();
        KonaBookingAnalytics.trackClick("coupon_code", "apply_code", getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()).mo9946kv("coupon_code", this.couponCode));
        CouponRequest.forApply(getReservation().getId(), this.couponCode).withListener((Observer) this.couponListener).execute(this.requestManager);
    }

    private void showClearCouponConfirmation() {
        this.snackbar = new SnackbarWrapper().view(getView()).body(getString(C0704R.string.p4_confirm_coupon_clear)).action(getString(C0704R.string.p4_remove), CouponCodeFragment$$Lambda$3.lambdaFactory$(this)).buildAndShow();
    }

    /* access modifiers changed from: 0000 */
    public void deleteCoupon() {
        KonaBookingAnalytics.trackClick("coupon_code", "clear_code", getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        CouponRequest.forDelete(getReservation().getId()).withListener((Observer) this.couponListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$2(CouponCodeFragment couponCodeFragment, AirRequestNetworkException e) {
        couponCodeFragment.applyButton.setState(AirButton.State.Normal);
        couponCodeFragment.snackbar = new SnackbarWrapper().view(couponCodeFragment.getView()).body(NetworkUtil.errorMessage(e)).duration(0).buildAndShow();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CouponCode;
    }
}
