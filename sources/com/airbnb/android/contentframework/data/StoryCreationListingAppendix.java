package com.airbnb.android.contentframework.data;

import android.os.Parcelable;
import com.airbnb.android.core.models.Reservation;

public abstract class StoryCreationListingAppendix implements Parcelable {
    public abstract long listingId();

    public abstract float rating();

    public abstract String subtitle();

    public abstract String thumbnailUrl();

    public abstract String title();

    public static StoryCreationListingAppendix fromReservation(Reservation reservation) {
        return new AutoValue_StoryCreationListingAppendix(reservation.getListing().getThumbnailUrl(), reservation.getListing().getName(), reservation.getListing().getRoomType(), reservation.getListing().getStarRating(), reservation.getListing().getId());
    }
}
