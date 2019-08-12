package com.airbnb.android.core.presenters;

import android.content.Context;
import android.content.res.Resources;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.wishlists.WishListableData;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.List;

public class WishListPresenter {
    private WishListPresenter() {
    }

    public static String formatListingCount(Context context, WishList wishList) {
        return formatListingCount(context, wishList.getListingsCount());
    }

    public static String formatListingCount(Context context, int listingCount) {
        return context.getResources().getQuantityString(C0716R.plurals.x_homes, listingCount, new Object[]{Integer.valueOf(listingCount)});
    }

    public static String formatAvailableListingsCount(Context context, int count) {
        return context.getResources().getQuantityString(C0716R.plurals.x_homes_available, count, new Object[]{Integer.valueOf(count)});
    }

    public static String formatUnavailableListingsCount(Context context, int count) {
        return context.getResources().getQuantityString(C0716R.plurals.x_homes_unavailable, count, new Object[]{Integer.valueOf(count)});
    }

    public static String formatAvailableExperiencesCount(Context context, int count) {
        return context.getResources().getQuantityString(C0716R.plurals.x_experiences_available, count, new Object[]{Integer.valueOf(count)});
    }

    public static String formatAvailableImmersionsCount(Context context, int count) {
        return context.getResources().getQuantityString(C0716R.plurals.x_immersions_available, count, new Object[]{Integer.valueOf(count)});
    }

    public static String formatUnavailableExperiencesCount(Context context, int count) {
        return context.getResources().getQuantityString(C0716R.plurals.x_experiences_unavailable, count, new Object[]{Integer.valueOf(count)});
    }

    public static String formatPlacesCount(Context context, int count) {
        return context.getResources().getQuantityString(C0716R.plurals.x_places, count, new Object[]{Integer.valueOf(count)});
    }

    public static String formatStoriesCount(Context context, int count) {
        return context.getResources().getQuantityString(C0716R.plurals.x_stories, count, new Object[]{Integer.valueOf(count)});
    }

    public static String formatItemCounts(Context context, WishList wishList) {
        Resources r = context.getResources();
        List<String> parts = new ArrayList<>();
        Joiner joiner = Joiner.m1896on(" · ");
        int listingsCount = wishList.getListingsCount();
        if (listingsCount > 0) {
            parts.add(r.getQuantityString(C0716R.plurals.x_homes_capitalized, listingsCount, new Object[]{Integer.valueOf(listingsCount)}));
        }
        int tripsCount = wishList.getTripsCount();
        if (tripsCount > 0) {
            parts.add(r.getQuantityString(C0716R.plurals.x_experiences_capitalized, tripsCount, new Object[]{Integer.valueOf(tripsCount)}));
        }
        int placesContentCount = wishList.getPlacesCount() + wishList.getPlaceActivitiesCount();
        if (placesContentCount > 0) {
            parts.add(r.getQuantityString(C0716R.plurals.x_places_capitalized, placesContentCount, new Object[]{Integer.valueOf(placesContentCount)}));
        }
        int storiesCount = wishList.getArticlesCount();
        if (storiesCount > 0) {
            parts.add(r.getQuantityString(C0716R.plurals.x_stories, storiesCount, new Object[]{Integer.valueOf(storiesCount)}));
        }
        if (parts.isEmpty()) {
            return r.getString(C0716R.string.wish_list_nothing_saved_yet);
        }
        return joiner.join((Iterable<?>) parts);
    }

    public static String formatDateSpanOrAnyTime(Context context, WishList wishList) {
        return formatDateSpanOrAnyTime(context, wishList.getCheckin(), wishList.getCheckout());
    }

    public static String formatDateSpanOrAnyTime(Context context, AirDate checkIn, AirDate checkOut) {
        if (checkIn == null || checkOut == null) {
            return context.getString(C0716R.string.explore_anytime);
        }
        return checkIn.getDateSpanString(context, checkOut);
    }

    public static String formatDatesGuestCountPrivacyExperimental(Context context, WishList wishList) {
        String dateSpan = formatDateSpanOrAnyTime(context, wishList);
        String guestCount = formatGuestCount(context, wishList);
        String result = context.getString(C0716R.string.bullet_with_space_parameterized, new Object[]{dateSpan, guestCount});
        if (!wishList.isPrivateWishList()) {
            return result;
        }
        String inviteOnlyText = context.getResources().getString(C0716R.string.wishlist_invite_only);
        return context.getString(C0716R.string.bullet_with_space_parameterized, new Object[]{result, inviteOnlyText});
    }

    public static String formatDatesAndGuestCount(Context context, WishListableData data) {
        String dateSpan = formatDateSpanOrAnyTime(context, data.checkIn(), data.checkOut());
        if (!data.hasGuests()) {
            return dateSpan;
        }
        String guestCount = formatGuestCount(context, data.guestDetails());
        return context.getString(C0716R.string.bullet_with_space_parameterized, new Object[]{dateSpan, guestCount});
    }

    private static String formatGuestCount(Context context, WishList wishList) {
        return formatGuestCount(context, wishList.getGuestDetails());
    }

    private static String formatGuestCount(Context context, GuestDetails guestDetails) {
        int totalGuestCount = guestDetails.totalGuestCount();
        return context.getResources().getQuantityString(C0716R.plurals.x_guests, totalGuestCount, new Object[]{Integer.valueOf(totalGuestCount)});
    }
}
