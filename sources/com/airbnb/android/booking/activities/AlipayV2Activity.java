package com.airbnb.android.booking.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.fragments.alipayv2.AlipayV2AuthorizationFragment;
import com.airbnb.android.booking.fragments.alipayv2.AlipayV2RetryFragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.requests.payments.LegacyPaymentOptionRequest;
import com.airbnb.android.core.responses.LegacyPaymentOptionResponse;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.core.utils.ParcelStrap;
import icepick.State;
import p032rx.Observer;

public class AlipayV2Activity extends AirActivity implements AlipayV2Facade {
    private static final String EXTRA_ANALYTICS_DATA = "analytics_data";
    private static final String EXTRA_QUICK_PAY = "extra_quick_pay";
    public static final String RESULT_EXTRA_ALIPAY_PAYMENT_INSTRUMENT = "result_code_alipay_payment_instrument";
    @State
    ParcelStrap analyticsData;
    @State
    boolean isQuickPay;
    @State
    PaymentInstrument paymentInstrument;
    final RequestListener<LegacyPaymentOptionResponse> queryPaymentOptionListener = new RequestListener<LegacyPaymentOptionResponse>() {
        public void onResponse(LegacyPaymentOptionResponse data) {
            if (AlipayV2Activity.this.isQuickPay) {
                AlipayV2Activity.this.finishWithPaymentInstrument(data.paymentOption.toPaymentInstrumentWithGibraltarId());
            } else {
                AlipayV2Activity.this.finishWithPaymentInstrument(data.paymentOption.toLegacyPaymentInstrument());
            }
            KonaBookingAnalytics.trackView("payment_options", "alipay_query_verification_success", AlipayV2Activity.this.getAnalyticsData());
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            AlipayV2Activity.this.onVerificationTimeout();
        }
    };

    public static Intent intentForAuthorization(Context context, ParcelStrap analyticsData2) {
        return new Intent(context, AlipayV2Activity.class).putExtra(EXTRA_ANALYTICS_DATA, analyticsData2);
    }

    public static Intent intentForQuickPay(Context context) {
        return new Intent(context, AlipayV2Activity.class).putExtra(EXTRA_QUICK_PAY, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0704R.layout.activity_alipay_v2);
        if (savedInstanceState == null) {
            onAuthorizationStart();
            this.isQuickPay = getIntent().hasExtra(EXTRA_QUICK_PAY);
        }
    }

    public ParcelStrap getAnalyticsData() {
        if (this.isQuickPay) {
            this.analyticsData = ParcelStrap.make().mo9947kv("is_quickpay", true);
        } else if (this.analyticsData == null) {
            this.analyticsData = (ParcelStrap) getIntent().getParcelableExtra(EXTRA_ANALYTICS_DATA);
            if (this.analyticsData == null) {
                this.analyticsData = ParcelStrap.make();
            }
        }
        return this.analyticsData;
    }

    public void setPaymentInstrument(PaymentInstrument paymentInstrument2) {
        this.paymentInstrument = paymentInstrument2;
    }

    public PaymentInstrument getPaymentInstrument() {
        return this.paymentInstrument;
    }

    public void onAuthorizationStart() {
        switchToFragment(AlipayV2AuthorizationFragment.forAuth(), FragmentTransitionType.FadeInAndOut);
    }

    public void onAuthorizationSuccess() {
        LegacyPaymentOptionRequest.forGibraltarId(this.paymentInstrument.getId()).withListener((Observer) this.queryPaymentOptionListener).execute(this.requestManager);
    }

    public void onAuthorizationFail() {
        finishWithFailureToast();
    }

    public void onVerificationTimeout() {
        KonaBookingAnalytics.trackView("payment_options", "alipay_query_verification_fail", getAnalyticsData());
        switchToFragment(AlipayV2RetryFragment.newInstance(), FragmentTransitionType.FadeInAndOut);
    }

    public void onVerificationRetry() {
        switchToFragment(AlipayV2AuthorizationFragment.forRetry(), FragmentTransitionType.FadeInAndOut);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    private void switchToFragment(Fragment newFragment, FragmentTransitionType transitionType) {
        NavigationUtils.showFragment(getSupportFragmentManager(), this, newFragment, C0704R.C0706id.content_container, transitionType, false);
    }

    private void finishWithFailureToast() {
        setResult(0);
        Toast.makeText(this, C0704R.string.alipay_v2_failure_message, 0).show();
        finish();
    }

    /* access modifiers changed from: private */
    public void finishWithPaymentInstrument(OldPaymentInstrument paymentInstrument2) {
        Intent intent = new Intent();
        intent.putExtra("result_code_alipay_payment_instrument", paymentInstrument2);
        setResult(-1, intent);
        finish();
    }
}
