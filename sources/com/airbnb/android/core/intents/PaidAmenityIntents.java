package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Thread;

public class PaidAmenityIntents {
    public static final int ACTIVITY_HOST_AMENITY_LIST = 888;
    public static final String EXTRA_CONFIRMATION_CODE = "confirmation_code";
    public static final String EXTRA_HAS_ZERO_PAID_AMENITY = "has_zero_paid_amenity";
    public static final String EXTRA_LISTING_ID = "listing_id";
    public static final String EXTRA_THREAD_ID = "thread_id";

    public static Intent hostAmenitiesIntent(Context context, long listingId) {
        return new Intent(context, Activities.hostAmenities()).putExtra("listing_id", listingId);
    }

    public static Intent createAmenitiesIntent(Context context) {
        return new Intent(context, Activities.createAmenities());
    }

    public static Intent createAmenitiesWithListingIdIntent(Context context, long listingId) {
        return new Intent(context, Activities.createAmenities()).putExtra("listing_id", listingId);
    }

    public static Intent purchaseAmenities(Context context, String confirmationCode, long threadId, long listingId) {
        return new Intent(context, Activities.purchaseAmenities()).putExtra("listing_id", listingId).putExtra("thread_id", threadId).putExtra("confirmation_code", confirmationCode);
    }

    public static Intent purchaseAmenitiesWithThread(Context context, Thread thread) {
        return purchaseAmenities(context, thread.getReservationConfirmationCode(), thread.getId(), thread.getListing().getId());
    }
}
