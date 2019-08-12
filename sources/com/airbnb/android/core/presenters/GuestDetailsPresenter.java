package com.airbnb.android.core.presenters;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.utils.Check;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.List;

public final class GuestDetailsPresenter {
    private GuestDetailsPresenter() {
    }

    public static String formatGuestCountLabel(Context context, GuestDetails guestData) {
        List<String> guestStrings = new ArrayList<>();
        Resources r = context.getResources();
        int guestsCount = guestData.totalGuestCount();
        if (guestsCount <= 0) {
            return r.getString(C0716R.string.default_group_size);
        }
        guestStrings.add(r.getQuantityString(C0716R.plurals.x_guests, guestsCount, new Object[]{Integer.valueOf(guestsCount)}));
        int infantsCount = guestData.infantsCount();
        if (infantsCount > 0) {
            guestStrings.add(r.getQuantityString(C0716R.plurals.x_infants, infantsCount, new Object[]{Integer.valueOf(infantsCount)}));
        }
        if (guestData.isBringingPets()) {
            guestStrings.add(r.getString(C0716R.string.pet));
        }
        return Joiner.m1896on(", ").join((Iterable<?>) guestStrings);
    }

    public static String formatGuestCountLabelWithoutPets(Context context, GuestDetails guestData) {
        return formatGuestCountLabel(context, new GuestDetails().adultsCount(guestData.adultsCount()).childrenCount(guestData.childrenCount()).infantsCount(guestData.infantsCount()).isBringingPets(false));
    }

    public static String formatGuestCountLabel(Context context, GuestDetails details, int numGuests) {
        if (details != null && details.isValid() && details.totalGuestCount() == numGuests) {
            return formatGuestCountLabel(context, details);
        }
        return context.getResources().getQuantityString(C0716R.plurals.x_guests, numGuests, new Object[]{Integer.valueOf(numGuests)});
    }

    public static String formatGuestsString(Context context, GuestDetails details, int numGuests) {
        String guestDetailsString = "";
        if (details == null || !details.isValid() || details.totalGuestCount() != numGuests) {
            if (numGuests > 0) {
                guestDetailsString = context.getResources().getQuantityString(C0716R.plurals.x_guests, numGuests, new Object[]{Integer.valueOf(numGuests)});
            }
            return guestDetailsString;
        }
        Resources r = context.getResources();
        String guests = r.getQuantityString(C0716R.plurals.x_guests, details.totalGuestCount(), new Object[]{Integer.valueOf(details.totalGuestCount())});
        String infants = null;
        if (details.infantsCount() > 0) {
            infants = r.getQuantityString(C0716R.plurals.x_infants, details.infantsCount(), new Object[]{Integer.valueOf(details.infantsCount())});
        }
        if (TextUtils.isEmpty(infants)) {
            return guests;
        }
        return r.getString(C0716R.string.guests_details_desc, new Object[]{guests, infants});
    }

    public static String formatDetailedGuestsString(Context context, GuestDetails details, int numGuests) {
        String guestDetailsString = "";
        if (details != null && details.isValid() && details.totalGuestCount() == numGuests) {
            return formatDetailedGuestCountLabel(context, details);
        }
        if (numGuests <= 0) {
            return guestDetailsString;
        }
        return context.getResources().getQuantityString(C0716R.plurals.x_guests, numGuests, new Object[]{Integer.valueOf(numGuests)});
    }

    private static String formatDetailedGuestCountLabel(Context context, GuestDetails guestData) {
        Check.notNull(guestData);
        List<String> guestStrings = new ArrayList<>();
        Resources r = context.getResources();
        int adultsCount = guestData.adultsCount();
        int childrenCount = guestData.childrenCount();
        int infantsCount = guestData.infantsCount();
        guestStrings.add(r.getQuantityString(C0716R.plurals.x_adults, adultsCount, new Object[]{Integer.valueOf(adultsCount)}));
        if (childrenCount > 0) {
            guestStrings.add(r.getQuantityString(C0716R.plurals.x_children, childrenCount, new Object[]{Integer.valueOf(childrenCount)}));
        }
        if (infantsCount > 0) {
            guestStrings.add(r.getQuantityString(C0716R.plurals.x_infants, infantsCount, new Object[]{Integer.valueOf(infantsCount)}));
        }
        if (guestData.isBringingPets()) {
            guestStrings.add(r.getString(C0716R.string.pet));
        }
        return Joiner.m1896on(", ").join((Iterable<?>) guestStrings);
    }
}
