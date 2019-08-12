package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;

public class CityRegistrationIntents {
    public static final String INTENT_EXTRA_IS_LYS = "is_lys";
    public static final String INTENT_EXTRA_LISTING = "listing";
    public static final String INTENT_EXTRA_LISTING_REGISTRATION_PROCESS = "listing_registration_process";
    public static final String INTENT_EXTRA_PROGRESS = "progress";
    public static final int RESULT_SAVE_AND_EXIT = 100;

    public static Intent intent(Context context, Listing listing, ListingRegistrationProcess listingRegistrationProcess, Boolean isLYS, Float progress) {
        if (listingRegistrationProcess == null || !listingRegistrationProcess.isRegulatoryBodySupported()) {
            return null;
        }
        return new Intent(context, Activities.cityRegistration()).putExtra("listing", listing).putExtra(INTENT_EXTRA_LISTING_REGISTRATION_PROCESS, listingRegistrationProcess).putExtra(INTENT_EXTRA_IS_LYS, isLYS).putExtra("progress", progress);
    }
}
