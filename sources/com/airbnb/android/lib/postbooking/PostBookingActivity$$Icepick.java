package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion;
import com.airbnb.android.lib.postbooking.PostBookingActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PostBookingActivity$$Icepick<T extends PostBookingActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9651H = new Helper("com.airbnb.android.lib.postbooking.PostBookingActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f9651H.getParcelable(state, "reservation");
            target.currentState = (PostBookingState) f9651H.getSerializable(state, "currentState");
            target.isFetchingPostBookingData = f9651H.getBoolean(state, "isFetchingPostBookingData");
            target.isBlockedByBatchRequest = f9651H.getBoolean(state, "isBlockedByBatchRequest");
            target.shouldSendIntentPredictionRequest = f9651H.getBoolean(state, "shouldSendIntentPredictionRequest");
            target.canShowMTPostHomeBookingPage = f9651H.getBoolean(state, "canShowMTPostHomeBookingPage");
            target.btMobileSignupPromotion = (BTMobileSignupPromotion) f9651H.getParcelable(state, "btMobileSignupPromotion");
            target.tripSuggestions = f9651H.getParcelableArrayList(state, "tripSuggestions");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9651H.putParcelable(state, "reservation", target.reservation);
        f9651H.putSerializable(state, "currentState", target.currentState);
        f9651H.putBoolean(state, "isFetchingPostBookingData", target.isFetchingPostBookingData);
        f9651H.putBoolean(state, "isBlockedByBatchRequest", target.isBlockedByBatchRequest);
        f9651H.putBoolean(state, "shouldSendIntentPredictionRequest", target.shouldSendIntentPredictionRequest);
        f9651H.putBoolean(state, "canShowMTPostHomeBookingPage", target.canShowMTPostHomeBookingPage);
        f9651H.putParcelable(state, "btMobileSignupPromotion", target.btMobileSignupPromotion);
        f9651H.putParcelableArrayList(state, "tripSuggestions", target.tripSuggestions);
    }
}
