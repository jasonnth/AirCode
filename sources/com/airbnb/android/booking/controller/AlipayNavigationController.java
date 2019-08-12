package com.airbnb.android.booking.controller;

import android.app.Activity;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.fragments.alipay.AlipayIdFragment;
import com.airbnb.android.booking.fragments.alipay.AlipayNationalIdFragment;
import com.airbnb.android.booking.fragments.alipay.AlipayPhoneFragment;
import com.airbnb.android.booking.fragments.alipay.AlipayVerificationFragment;
import com.airbnb.android.core.controllers.NavigationController;
import com.airbnb.android.core.utils.ParcelStrap;

public class AlipayNavigationController extends NavigationController {
    private final ParcelStrap analyticsData;

    public AlipayNavigationController(Activity activity, FragmentManager fragmentManager, ParcelStrap analyticsData2) {
        super(activity, fragmentManager);
        this.analyticsData = analyticsData2;
    }

    public void initializeFlow() {
        KonaBookingAnalytics.trackView("payment_options", "alipay_id", this.analyticsData);
        transitionTo(AlipayIdFragment.newInstance());
    }

    public void doneWithAlipayId(boolean didEnterPhone) {
        if (didEnterPhone) {
            KonaBookingAnalytics.trackView("payment_options", "alipay_national_id", this.analyticsData);
            transitionTo(AlipayNationalIdFragment.newInstance());
            return;
        }
        KonaBookingAnalytics.trackView("payment_options", "alipay_phone_number", this.analyticsData);
        transitionTo(AlipayPhoneFragment.newInstance());
    }

    public void doneWithNationalId() {
        KonaBookingAnalytics.trackView("payment_options", "alipay_verification", this.analyticsData);
        transitionTo(AlipayVerificationFragment.newInstance());
    }

    public void doneWithPhone() {
        KonaBookingAnalytics.trackView("payment_options", "alipay_verification", this.analyticsData);
        transitionTo(AlipayVerificationFragment.newInstance());
    }

    public void doneWithVerification() {
        this.activity.finish();
    }
}
