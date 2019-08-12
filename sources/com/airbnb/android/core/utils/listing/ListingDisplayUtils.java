package com.airbnb.android.core.utils.listing;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;

public class ListingDisplayUtils {
    public static int getStatusString(ListedStatus status) {
        switch (status) {
            case Listed:
                return C0716R.string.ml_spaces_listed;
            case Snoozed:
                return C0716R.string.ml_spaces_snoozed;
            case Incomplete:
                return C0716R.string.incomplete;
            case Unlisted:
                return C0716R.string.ml_spaces_unlisted;
            default:
                throw new UnhandledStateException(status);
        }
    }

    public static String getNameOrPlaceholder(Context context, Listing listing) {
        if (!TextUtils.isEmpty(listing.getName())) {
            return listing.getName();
        }
        return context.getString(C0716R.string.spaces_room_type_in_city, new Object[]{listing.getRoomType(context), listing.getCity()});
    }

    public static boolean showCityRegistration(ListingRegistrationProcess registrationProcess) {
        return FeatureToggles.isListingRegistrationEnabled() && registrationProcess != null && registrationProcess.isRegulatoryBodySupported();
    }

    public static double getPercentCompleted(int stepsCompleted, int stepsTotal) {
        return (100.0d * ((double) stepsCompleted)) / ((double) stepsTotal);
    }
}
