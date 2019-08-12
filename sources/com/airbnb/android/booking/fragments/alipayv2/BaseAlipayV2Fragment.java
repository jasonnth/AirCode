package com.airbnb.android.booking.fragments.alipayv2;

import android.os.Bundle;
import com.airbnb.android.booking.activities.AlipayV2Facade;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.events.AlipayDeeplinkResult;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ParcelStrap;

public abstract class BaseAlipayV2Fragment extends AirFragment {
    /* access modifiers changed from: protected */
    public final AlipayV2Facade getAlipayV2Facade() {
        Check.state(getActivity() instanceof AlipayV2Facade);
        return (AlipayV2Facade) getActivity();
    }

    /* access modifiers changed from: protected */
    public final ParcelStrap getAnalyticsData() {
        return getAlipayV2Facade().getAnalyticsData();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBus.register(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    /* access modifiers changed from: protected */
    public final void handleDeeplinkResult(AlipayDeeplinkResult deeplinkResult) {
        if (isAdded()) {
            if (deeplinkResult.isSuccess) {
                KonaBookingAnalytics.trackView("payment_options", "alipay_deeplink_success", getAnalyticsData());
                onAuthSuccess();
                return;
            }
            KonaBookingAnalytics.trackView("payment_options", "alipay_deeplink_fail", getAnalyticsData());
            onAuthFail();
        }
    }

    /* access modifiers changed from: protected */
    public void onAuthSuccess() {
    }

    /* access modifiers changed from: protected */
    public void onAuthFail() {
    }
}
