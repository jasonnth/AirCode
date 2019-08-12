package com.airbnb.android.lib.postbooking;

import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion;
import java.util.ArrayList;

public interface PostBookingFlowController {
    BTMobileSignupPromotion getBTMobileSignupPromotion();

    Reservation getReservation();

    ArrayList<TripTemplate> getTripSuggestions();

    boolean isNextStepReady();

    void onCurrentStateFinished();
}
