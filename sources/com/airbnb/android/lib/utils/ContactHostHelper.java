package com.airbnb.android.lib.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.lib.C0880R;
import java.util.Map;

public class ContactHostHelper {
    private static final String CONTACT_HOST_CONTEXT_BUSINESS = "business";
    private static final String CONTACT_HOST_CONTEXT_PERSONAL = "personal";
    private static final String CONTACT_HOST_RESPONSE_MESSAGE = "message";
    private static final String CONTACT_HOST_RESPONSE_PAYLOAD = "payload";
    public static final int REQUEST_CODE_MESSAGE_TO_HOST = 993;

    public static String getMessageFromRNContactHostResponse(Intent data) {
        if (data == null) {
            return null;
        }
        Bundle bundle = data.getExtras();
        Map<String, String> payload = (Map) bundle.get(CONTACT_HOST_RESPONSE_PAYLOAD);
        if (payload != null && payload.containsKey("message")) {
            return (String) payload.get("message");
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Invalid response from react native contact host activity with bundle " + bundle + " and payload " + payload));
        return null;
    }

    public static void launchRNContactHostForBooking(Activity activity, ReservationDetails reservationDetails, Reservation reservation, String message) {
        TripType tripType = reservationDetails.tripType();
        String tripContext = (tripType == null || tripType == TripType.PersonalUnverified || tripType == TripType.PersonalVerified) ? CONTACT_HOST_CONTEXT_PERSONAL : CONTACT_HOST_CONTEXT_BUSINESS;
        Listing listing = reservation.getListing();
        activity.startActivityForResult(ReactNativeIntents.intentForContactHostEditText(activity, reservation.getHost(), reservationDetails, message, activity.getString(C0880R.string.contact_host_prompt_title), activity.getString(C0880R.string.contact_host_prompt_subtitle, new Object[]{listing.getPrimaryHost().getFirstName()}), activity.getString(C0880R.string.p4_tell_host_about_trip_hint), true, activity.getString(C0880R.string.p4_message_default_suggestion, new Object[]{listing.getPrimaryHost().getFirstName()}), tripContext), REQUEST_CODE_MESSAGE_TO_HOST);
    }
}
