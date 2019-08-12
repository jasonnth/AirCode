package com.airbnb.android.booking.fragments.alipayv2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.events.AlipayDeeplinkResult;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.QueryPaymentInstrumentRequest;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.rxgroups.RequestSubscription;
import com.squareup.otto.Subscribe;
import icepick.State;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;

public class AlipayV2AuthorizationFragment extends BaseAlipayV2Fragment {
    private static final String EXTRA_RETRY = "extra_retry";
    private static final int FAILED = 4;
    private static final int FINISHED = 3;
    private static final int INIT = 0;
    private static final long POLLING_INTERVAL = 1000;
    private static final long POLLING_TIMEOUT = 10000;
    private static final int STARTED = 1;
    private static final int STOPPED = 2;
    final RequestListener<PaymentInstrumentResponse> fetchDeeplinkUrlListener = new RequestListener<PaymentInstrumentResponse>() {
        public void onResponse(PaymentInstrumentResponse data) {
            AlipayV2AuthorizationFragment.this.getAlipayV2Facade().setPaymentInstrument(data.paymentInstrument);
            AlipayV2AuthorizationFragment.this.launchAlipayForAuthorization(data.paymentInstrument);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            AlipayV2AuthorizationFragment.this.getAlipayV2Facade().onAuthorizationFail();
        }
    };
    @State
    int pollingState = 0;
    private RequestSubscription queryAuthStateSubscription;
    private final NonResubscribableRequestListener<PaymentInstrumentResponse> queryAuthStatusListener = new NonResubscribableRequestListener<PaymentInstrumentResponse>() {
        public void onResponse(PaymentInstrumentResponse data) {
            if (data.paymentInstrument == null || !data.paymentInstrument.isVerified()) {
                AlipayV2AuthorizationFragment.this.queryAuthStatusDelay();
                return;
            }
            AlipayV2AuthorizationFragment.this.setPollingState(3);
            AlipayV2AuthorizationFragment.this.getAlipayV2Facade().onAuthorizationSuccess();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            AlipayV2AuthorizationFragment.this.queryAuthStatusDelay();
        }
    };
    @State
    boolean queryEventFired;
    private Subscription timeoutSubscription;
    private Subscription timerSubscription;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PollingState {
    }

    public static AlipayV2AuthorizationFragment forAuth() {
        return newInstance(false);
    }

    public static AlipayV2AuthorizationFragment forRetry() {
        return newInstance(true);
    }

    private static AlipayV2AuthorizationFragment newInstance(boolean retry) {
        return (AlipayV2AuthorizationFragment) ((FragmentBundleBuilder) FragmentBundler.make(new AlipayV2AuthorizationFragment()).putBoolean(EXTRA_RETRY, retry)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(C0704R.layout.fragment_alipay_v2_authorization, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null && getAlipayV2Facade().getPaymentInstrument() == null) {
            CreatePaymentInstrumentRequest.forAlipayV2().withListener((Observer) this.fetchDeeplinkUrlListener).execute(this.requestManager);
            KonaBookingAnalytics.trackView("payment_options", "alipay_deeplink_loading", getAnalyticsData());
        }
    }

    @Subscribe
    public final void onDeeplinkResult(AlipayDeeplinkResult deeplinkResult) {
        handleDeeplinkResult(deeplinkResult);
    }

    public void onResume() {
        super.onResume();
        if (getAlipayV2Facade().getPaymentInstrument() != null) {
            setPollingState(1);
        }
    }

    public void onPause() {
        super.onPause();
        setPollingState(2);
    }

    /* access modifiers changed from: protected */
    public void onAuthFail() {
        getAlipayV2Facade().onAuthorizationFail();
        KonaBookingAnalytics.trackView("payment_options", "alipay_query_verification_fail", getAnalyticsData());
        setPollingState(4);
    }

    /* access modifiers changed from: private */
    public void setPollingState(int state) {
        if (this.pollingState != state && this.pollingState != 4 && this.pollingState != 3) {
            switch (state) {
                case 1:
                    startPolling();
                    break;
                case 2:
                case 3:
                case 4:
                    stopPolling();
                    break;
            }
            this.pollingState = state;
        }
    }

    private void startPolling() {
        this.timeoutSubscription = Observable.timer(POLLING_TIMEOUT, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(AlipayV2AuthorizationFragment$$Lambda$1.lambdaFactory$(this));
        queryAuthStatus();
        if (!this.queryEventFired) {
            this.queryEventFired = true;
            KonaBookingAnalytics.trackView("payment_options", "alipay_query_verification_waiting", getAnalyticsData());
        }
    }

    static /* synthetic */ void lambda$startPolling$0(AlipayV2AuthorizationFragment alipayV2AuthorizationFragment, Long aLong) {
        alipayV2AuthorizationFragment.getAlipayV2Facade().onVerificationTimeout();
        alipayV2AuthorizationFragment.setPollingState(3);
    }

    private void stopPolling() {
        if (this.timeoutSubscription != null) {
            this.timeoutSubscription.unsubscribe();
        }
        if (this.timerSubscription != null) {
            this.timerSubscription.unsubscribe();
        }
        if (this.queryAuthStateSubscription != null) {
            this.queryAuthStateSubscription.unsubscribe();
        }
    }

    /* access modifiers changed from: private */
    public void launchAlipayForAuthorization(PaymentInstrument paymentInstrument) {
        String deeplinkUrl = paymentInstrument.getAlipayDetails().getDeeplinkUrl();
        if (TextUtils.isEmpty(deeplinkUrl)) {
            getAlipayV2Facade().onAuthorizationFail();
            KonaBookingAnalytics.trackView("payment_options", "alipay_deeplink_open_fail", getAnalyticsData());
            return;
        }
        Intent alipayIntent = new Intent("android.intent.action.VIEW");
        alipayIntent.setData(Uri.parse(deeplinkUrl));
        try {
            startActivity(alipayIntent);
            KonaBookingAnalytics.trackView("payment_options", "alipay_deeplink_open_success", getAnalyticsData());
        } catch (ActivityNotFoundException e) {
            getAlipayV2Facade().onAuthorizationFail();
            KonaBookingAnalytics.trackView("payment_options", "alipay_deeplink_open_fail", getAnalyticsData());
        }
    }

    /* access modifiers changed from: private */
    public void queryAuthStatus() {
        this.queryAuthStateSubscription = QueryPaymentInstrumentRequest.forAlipayQuery(getAlipayV2Facade().getPaymentInstrument().getId()).withListener((Observer) this.queryAuthStatusListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void queryAuthStatusDelay() {
        this.timerSubscription = Observable.timer(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(AlipayV2AuthorizationFragment$$Lambda$2.lambdaFactory$(this));
    }
}
