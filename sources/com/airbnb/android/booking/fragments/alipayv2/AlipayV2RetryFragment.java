package com.airbnb.android.booking.fragments.alipayv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.events.AlipayDeeplinkResult;
import com.airbnb.p027n2.components.HeroMarquee;
import com.squareup.otto.Subscribe;

public class AlipayV2RetryFragment extends BaseAlipayV2Fragment {
    @BindView
    HeroMarquee heroMarquee;

    public static AlipayV2RetryFragment newInstance() {
        return new AlipayV2RetryFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_alipay_v2_retry, container, false);
        bindViews(view);
        this.heroMarquee.setFirstButtonClickListener(AlipayV2RetryFragment$$Lambda$1.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonClickListener(AlipayV2RetryFragment$$Lambda$2.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(AlipayV2RetryFragment alipayV2RetryFragment, View v) {
        alipayV2RetryFragment.getAlipayV2Facade().onVerificationRetry();
        KonaBookingAnalytics.trackClick("payment_options", "alipay_reload", alipayV2RetryFragment.getAnalyticsData());
    }

    static /* synthetic */ void lambda$onCreateView$1(AlipayV2RetryFragment alipayV2RetryFragment, View v) {
        alipayV2RetryFragment.getAlipayV2Facade().onAuthorizationFail();
        KonaBookingAnalytics.trackClick("payment_options", "alipay_reload_cancel", alipayV2RetryFragment.getAnalyticsData());
    }

    @Subscribe
    public final void onDeeplinkResult(AlipayDeeplinkResult deeplinkResult) {
        handleDeeplinkResult(deeplinkResult);
    }

    /* access modifiers changed from: protected */
    public void onAuthSuccess() {
        getAlipayV2Facade().onVerificationRetry();
    }

    /* access modifiers changed from: protected */
    public void onAuthFail() {
        getAlipayV2Facade().onAuthorizationFail();
        KonaBookingAnalytics.trackView("payment_options", "alipay_query_verification_fail", getAnalyticsData());
    }
}
