package com.airbnb.android.core.utils;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Pair;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.listing.ListedStatus;
import com.airbnb.android.core.utils.listing.ListingDisplayUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.LanguageUtils;
import com.airbnb.android.utils.NumberUtils;

public final class ListingUtils {
    private ListingUtils() {
    }

    public static Pair<String, Integer> getListingStatusAndColor(Context context, Listing listing) {
        Check.notNull(listing, "Listing object can't be null");
        ListedStatus status = ListedStatus.calculate(listing);
        return new Pair<>(context.getString(ListingDisplayUtils.getStatusString(status)), Integer.valueOf(ContextCompat.getColor(context, getStatusColorResource(status))));
    }

    private static int getStatusColorResource(ListedStatus status) {
        switch (status) {
            case Listed:
                return C0716R.color.c_lima;
            case Snoozed:
                return C0716R.color.c_beach;
            case Incomplete:
                return C0716R.color.c_ebisu;
            case Unlisted:
                return C0716R.color.c_foggy;
            default:
                throw new UnhandledStateException(status);
        }
    }

    public static String getBathroomCountAsString(float bathroomCount) {
        return ((double) (bathroomCount % 1.0f)) < 0.01d ? String.valueOf((int) bathroomCount) : String.valueOf(bathroomCount);
    }

    public static boolean needsTranslation(Listing listing) {
        String lang;
        if (listing.getDescriptionLocale() == null) {
            return false;
        }
        String lang2 = listing.getDescriptionLocale().toLowerCase();
        if (!lang2.startsWith(AirbnbConstants.LANGUAGE_CODE_CHINESE)) {
            lang = lang2.substring(0, Math.min(lang2.length(), 2));
        } else if (lang2.contains("tw") || lang2.contains("hk")) {
            lang = "zh-tw";
        } else {
            lang = AirbnbConstants.LANGUAGE_CODE_CHINESE;
        }
        if (TextUtils.isEmpty(lang) || lang.equalsIgnoreCase(LanguageUtils.getLanguage())) {
            return false;
        }
        return true;
    }

    public static String getCheckinDescription(Context context, Listing listing) {
        String checkOutPart;
        boolean hasCheckInTime = false;
        boolean hasLatestCheckInTime = false;
        String checkInPart = null;
        if (!TextUtils.isEmpty(listing.getCheckInTimeStart())) {
            if (NumberUtils.tryParseInt(listing.getCheckInTimeStart(), -1) < 0 || TextUtils.isEmpty(listing.getLocalizedCheckInTimeWindow())) {
                hasCheckInTime = false;
            } else {
                hasCheckInTime = true;
            }
        }
        if (hasCheckInTime) {
            if (!TextUtils.isEmpty(listing.getCheckInTimeEnd())) {
                if (NumberUtils.tryParseInt(listing.getCheckInTimeEnd(), -1) >= 0) {
                    hasLatestCheckInTime = true;
                } else {
                    hasLatestCheckInTime = false;
                }
            }
            if (hasLatestCheckInTime) {
                checkInPart = context.getString(C0716R.string.ml_check_in_between_desc, new Object[]{listing.getLocalizedCheckInTimeWindow().toLowerCase()});
            } else {
                checkInPart = context.getString(C0716R.string.ml_check_in_after_desc, new Object[]{listing.getLocalizedCheckInTimeWindow().toLowerCase()});
            }
        }
        if (TextUtils.isEmpty(listing.getLocalizedCheckOutTime())) {
            return checkInPart;
        }
        if (hasCheckInTime) {
            checkOutPart = context.getString(C0716R.string.ml_check_out_desc_lowercase, new Object[]{listing.getLocalizedCheckOutTime()});
        } else {
            checkOutPart = context.getString(C0716R.string.ml_check_out_desc, new Object[]{listing.getLocalizedCheckOutTime()});
        }
        if (hasCheckInTime) {
            checkOutPart = context.getString(C0716R.string.ml_check_in_and_check_out_desc, new Object[]{checkInPart, checkOutPart});
        }
        return checkOutPart;
    }
}
