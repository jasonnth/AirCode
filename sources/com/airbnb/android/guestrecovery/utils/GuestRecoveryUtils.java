package com.airbnb.android.guestrecovery.utils;

import android.text.TextUtils;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.models.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuestRecoveryUtils {
    public static long getUserId(AirbnbAccountManager accountManager) {
        User user = accountManager != null ? accountManager.getCurrentUser() : null;
        if (user != null) {
            return user.getId();
        }
        return -1;
    }

    public static long getListingId(Reservation reservation) {
        if (reservation == null || reservation.getListing() == null) {
            return new Long(-1).longValue();
        }
        return reservation.getListing().getId();
    }

    public static Set<String> getSimilarListingIds(List<SimilarListing> similarListings) {
        Set<String> listingSet = new HashSet<>();
        for (SimilarListing similarListing : similarListings) {
            if (similarListing.getListing() != null) {
                listingSet.add(String.valueOf(similarListing.getListing().getId()));
            }
        }
        return listingSet;
    }

    public static String getReservationCode(Reservation reservation) {
        if (reservation == null || TextUtils.isEmpty(reservation.getConfirmationCode())) {
            return "";
        }
        return reservation.getConfirmationCode();
    }
}
