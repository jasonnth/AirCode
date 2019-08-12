package com.airbnb.android.booking.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.booking.activities.CreditCardActivity;
import com.airbnb.android.booking.activities.CreditCardActivity.Flow;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.fragments.CardNumberFragment;
import com.airbnb.android.booking.fragments.ExpirationDateFragment;
import com.airbnb.android.booking.fragments.PostalCodeFragment;
import com.airbnb.android.booking.fragments.SecurityCodeFragment;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.controllers.NavigationController;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ParcelStrap;
import icepick.State;
import java.util.EnumSet;

public class CreditCardNavigationController extends NavigationController {
    @State
    Flow flow;

    public CreditCardNavigationController(Activity activity, FragmentManager fragmentManager, Bundle savedState) {
        super(activity, fragmentManager);
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void initializeFlow(Flow flow2) {
        this.flow = flow2;
        switch (flow2) {
            case AddForBooking:
                KonaBookingAnalytics.trackView("payment_options", "payment_cc_number", getAnalyticsData());
                transitionTo(CardNumberFragment.newInstance());
                return;
            case PostalCodeRetry:
                KonaBookingAnalytics.trackView("payment_options", "payment_cc_zip", getAnalyticsData());
                transitionTo(PostalCodeFragment.newInstance());
                return;
            case AddForQuickPay:
            case AddForGiftCardRedeem:
                transitionTo(CardNumberFragment.newInstance());
                return;
            default:
                return;
        }
    }

    public void doneWithCardNumber() {
        if (!EnumSet.of(Flow.AddForQuickPay, Flow.AddForGiftCardRedeem).contains(this.flow)) {
            KonaBookingAnalytics.trackView("payment_options", "payment_cc_expiration", getAnalyticsData());
        }
        transitionTo(ExpirationDateFragment.newInstance());
    }

    public void doneWithExpirationDate() {
        if (!EnumSet.of(Flow.AddForQuickPay, Flow.AddForGiftCardRedeem).contains(this.flow)) {
            KonaBookingAnalytics.trackView("payment_options", "payment_cc_cvv", getAnalyticsData());
        }
        transitionTo(SecurityCodeFragment.newInstance());
    }

    public void doneWithSecurityCode() {
        if (!EnumSet.of(Flow.AddForQuickPay, Flow.AddForGiftCardRedeem).contains(this.flow)) {
            KonaBookingAnalytics.trackView("payment_options", "payment_cc_zip", getAnalyticsData());
        }
        transitionTo(PostalCodeFragment.newInstance());
    }

    public void doneWithPostalCode() {
        this.activity.finish();
    }

    private ParcelStrap getAnalyticsData() {
        Check.state(this.activity instanceof CreditCardActivity);
        return ((CreditCardActivity) this.activity).getAnalyticsData();
    }
}
