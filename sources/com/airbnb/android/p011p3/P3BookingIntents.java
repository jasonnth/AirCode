package com.airbnb.android.p011p3;

import android.app.Activity;
import android.content.Intent;
import com.airbnb.android.core.intents.BookingActivityIntents;

/* renamed from: com.airbnb.android.p3.P3BookingIntents */
public class P3BookingIntents {
    private P3BookingIntents() {
    }

    public static Intent intentToBooking(ListingDetailsController controller, Activity activity) {
        return BookingActivityIntents.intentForBooking(activity, controller.getState().listing(), controller.getState().checkInDate(), controller.getState().checkOutDate(), controller.getState().guestDetails(), controller.getState().tripPurpose(), controller.getState().searchSessionId(), controller.getState().firstVerificationStep());
    }

    public static Intent intentToBookingWithHostMessage(ListingDetailsController controller, Activity activity, String hostMessage) {
        Intent bookingIntent = intentToBooking(controller, activity);
        bookingIntent.putExtra(BookingActivityIntents.EXTRA_HOST_MESSAGE, hostMessage);
        return bookingIntent;
    }
}
