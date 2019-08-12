package com.airbnb.android.core.arguments;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class P3Arguments extends Arguments {
    public static final String FROM_CONTENT_FRAMEWORK = "content_framework";
    public static final String FROM_DEEP_LINK = "deep_link";
    public static final String FROM_EXPLORE = "explore";
    public static final String FROM_MANAGE_LISTING_OR_LYS = "manage_listing";
    public static final String FROM_MAP = "map";
    public static final String FROM_MESSAGE_THREAD = "message_thread";
    public static final String FROM_P1 = "p1";
    public static final String FROM_P2 = "p2";
    public static final String FROM_RO = "reservation_object";
    public static final String FROM_SIMILAR_LISTINGS = "similar_listings";
    public static final String FROM_SIMILAR_LISTINGS_IN_GUEST_RECOVERY = "guest_recovery";
    public static final String FROM_WISHLIST = "wishlist";

    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract P3Arguments autoBuild();

        public abstract Builder checkInDate(AirDate airDate);

        public abstract Builder checkOutDate(AirDate airDate);

        public abstract Builder firstVerificationStep(String str);

        public abstract Builder from(String str);

        public abstract Builder guestDetails(GuestDetails guestDetails);

        public abstract Builder listing(Listing listing);

        /* access modifiers changed from: 0000 */
        public abstract Listing listing();

        public abstract Builder listingId(long j);

        public abstract Builder phoneVerificationCode(String str);

        public abstract Builder pricingQuote(PricingQuote pricingQuote);

        public abstract Builder searchSessionId(String str);

        public abstract Builder tripPurpose(TripPurpose tripPurpose);

        public P3Arguments build() {
            if (listing() != null) {
                listingId(listing().getId());
            }
            return autoBuild();
        }

        public Intent toIntent(Context context) {
            return build().toIntent(context);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EntryPoint {
    }

    public abstract AirDate checkInDate();

    public abstract AirDate checkOutDate();

    public abstract String firstVerificationStep();

    public abstract String from();

    public abstract GuestDetails guestDetails();

    public abstract Listing listing();

    public abstract long listingId();

    public abstract String phoneVerificationCode();

    public abstract PricingQuote pricingQuote();

    public abstract String searchSessionId();

    public abstract Builder toBuilder();

    public abstract TripPurpose tripPurpose();

    public boolean hasValidId() {
        return listingId() != -1;
    }

    public Class<?> getIntentClass() {
        return Activities.m1188p3();
    }

    public static Builder builder() {
        return new Builder().listingId(-1).guestDetails(new GuestDetails());
    }
}
