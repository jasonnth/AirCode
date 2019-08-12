package com.airbnb.android.listyourspacedls;

import android.os.Bundle;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.LYSDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LYSDataController$$Icepick<T extends LYSDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9809H = new Helper("com.airbnb.android.listyourspacedls.LYSDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.maxStepReached = (LYSStep) f9809H.getSerializable(state, "maxStepReached");
            target.loading = f9809H.getBoolean(state, "loading");
            target.listing = (Listing) f9809H.getParcelable(state, "listing");
            target.sessionId = f9809H.getString(state, "sessionId");
            target.accountVerifications = f9809H.getParcelableArrayList(state, "accountVerifications");
            target.accountVerificationCompletedOnClient = f9809H.getBoolean(state, "accountVerificationCompletedOnClient");
            target.identityCompletedOnClient = f9809H.getBoolean(state, "identityCompletedOnClient");
            target.pricingSettings = (DynamicPricingControl) f9809H.getParcelable(state, "pricingSettings");
            target.checkInTimeOptions = (ListingCheckInTimeOptions) f9809H.getParcelable(state, "checkInTimeOptions");
            target.calendarRule = (CalendarRule) f9809H.getParcelable(state, "calendarRule");
            target.listingRegistrationProcess = (ListingRegistrationProcess) f9809H.getParcelable(state, "listingRegistrationProcess");
            target.guestControls = (GuestControls) f9809H.getParcelable(state, "guestControls");
            target.currencyCode = f9809H.getString(state, "currencyCode");
            target.averagePrices = (ListingLongTermDiscountValues) f9809H.getParcelable(state, "averagePrices");
            target.bedDetails = f9809H.getParcelableArrayList(state, "bedDetails");
            target.newHostPromoEnabled = f9809H.getBoxedBoolean(state, "newHostPromoEnabled");
            target.shouldReloadCalendar = f9809H.getBoolean(state, "shouldReloadCalendar");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9809H.putSerializable(state, "maxStepReached", target.maxStepReached);
        f9809H.putBoolean(state, "loading", target.loading);
        f9809H.putParcelable(state, "listing", target.listing);
        f9809H.putString(state, "sessionId", target.sessionId);
        f9809H.putParcelableArrayList(state, "accountVerifications", target.accountVerifications);
        f9809H.putBoolean(state, "accountVerificationCompletedOnClient", target.accountVerificationCompletedOnClient);
        f9809H.putBoolean(state, "identityCompletedOnClient", target.identityCompletedOnClient);
        f9809H.putParcelable(state, "pricingSettings", target.pricingSettings);
        f9809H.putParcelable(state, "checkInTimeOptions", target.checkInTimeOptions);
        f9809H.putParcelable(state, "calendarRule", target.calendarRule);
        f9809H.putParcelable(state, "listingRegistrationProcess", target.listingRegistrationProcess);
        f9809H.putParcelable(state, "guestControls", target.guestControls);
        f9809H.putString(state, "currencyCode", target.currencyCode);
        f9809H.putParcelable(state, "averagePrices", target.averagePrices);
        f9809H.putParcelableArrayList(state, "bedDetails", target.bedDetails);
        f9809H.putBoxedBoolean(state, "newHostPromoEnabled", target.newHostPromoEnabled);
        f9809H.putBoolean(state, "shouldReloadCalendar", target.shouldReloadCalendar);
    }
}
