package com.airbnb.android.places;

import android.content.Context;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.clientparameters.ResyClientParameters;
import java.util.ArrayList;
import java.util.List;

public abstract class ResyState implements Parcelable {

    public static abstract class Builder {
        public abstract Builder activityId(long j);

        public abstract ResyState build();

        public abstract Builder coverImage(Photo photo);

        public abstract Builder date(AirDate airDate);

        public abstract Builder guests(int i);

        public abstract Builder isLoading(boolean z);

        public abstract Builder placeName(String str);

        public abstract Builder selectedTime(RestaurantAvailability restaurantAvailability);

        public abstract Builder showResy(boolean z);

        public abstract Builder timeSlots(List<RestaurantAvailability> list);
    }

    public abstract long activityId();

    public abstract Photo coverImage();

    public abstract AirDate date();

    public abstract int guests();

    public abstract boolean isLoading();

    public abstract String placeName();

    public abstract RestaurantAvailability selectedTime();

    public abstract boolean showResy();

    public abstract List<RestaurantAvailability> timeSlots();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder().date(AirDate.today()).guests(2).isLoading(true).showResy(false).timeSlots(new ArrayList());
    }

    public CharSequence getDateForDisplay(Context context, boolean shortVersion) {
        AirDate date = date();
        return new StringBuilder(AirDate.isToday(date) ? date.getRelativeDateStringFromNow(context) : date.getDayOfWeekString(context, shortVersion)).append(context.getString(C7627R.string.attributes_separator)).append(date.getDateString(context));
    }

    public CharSequence getDateForDisplay(Context context) {
        return getDateForDisplay(context, false);
    }

    public String getTableForGuestsString(Context context) {
        return context.getResources().getString(C7627R.string.resy_guest_count, new Object[]{Integer.valueOf(guests())});
    }

    public String getGuestsString(Context context) {
        return context.getResources().getQuantityString(C7627R.plurals.x_guests, guests(), new Object[]{Integer.valueOf(guests())});
    }

    public String getGuestsAndDateTimeForDisplay(Context context) {
        return new StringBuilder(getDateForDisplay(context, false)).append(context.getString(C7627R.string.bullet_with_space)).append(selectedTime().getTimeString(context)).append("\n").append(getGuestsString(context)).toString();
    }

    public CartItem makeCartItem(Context context, RestaurantAvailability timeSlot) {
        long id = (long) timeSlot.getId();
        Photo coverImage = coverImage();
        return CartItem.builder().thumbnailUrl(coverImage != null ? coverImage.getLargeUrl() : "").title(placeName()).description(getGuestsAndDateTimeForDisplay(context)).quickPayParameters(ResyClientParameters.builder().reservationId(id).numberOfGuests(guests()).activityId(activityId()).build()).build();
    }
}
