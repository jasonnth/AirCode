package com.airbnb.android.core.p009p3;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.SectionedListingDescription;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.utils.BundleBuilder;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.android.core.p3.P3State */
public abstract class P3State implements Parcelable {

    /* renamed from: com.airbnb.android.core.p3.P3State$Builder */
    public static abstract class Builder {
        /* access modifiers changed from: 0000 */
        public abstract P3State autoBuild();

        public abstract Builder cancellationPolicyLabel(String str);

        public abstract Builder checkInDate(AirDate airDate);

        public abstract Builder checkOutDate(AirDate airDate);

        public abstract Builder firstVerificationStep(String str);

        public abstract Builder from(String str);

        public abstract Builder guestDetails(GuestDetails guestDetails);

        public abstract Builder inquiryMessage(String str);

        public abstract Builder isCurrentUserListingHost(boolean z);

        public abstract Builder isFetchingReportedListingStatus(boolean z);

        public abstract Builder isHostPreview(boolean z);

        /* access modifiers changed from: 0000 */
        public abstract Listing listing();

        public abstract Builder listing(Listing listing);

        public abstract Builder listingId(long j);

        public abstract Builder phoneVerificationCode(String str);

        public abstract Builder pricingQuote(PricingQuote pricingQuote);

        public abstract Builder searchSessionId(String str);

        public abstract Builder sectionedListingDescription(SectionedListingDescription sectionedListingDescription);

        public abstract Builder showTranslatedSections(boolean z);

        public abstract Builder similarListings(List<SimilarListing> list);

        public abstract Builder translatedSectionedListingDescription(SectionedListingDescription sectionedListingDescription);

        public abstract Builder tripPurpose(TripPurpose tripPurpose);

        public P3State build() {
            if (listing() != null) {
                listingId(listing().getId());
            }
            P3State state = autoBuild();
            if (state.showTranslatedSections() && !state.hasFetchedTranslations()) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Show translation needs translation fetched first"));
            }
            return state;
        }
    }

    public abstract String cancellationPolicyLabel();

    public abstract AirDate checkInDate();

    public abstract AirDate checkOutDate();

    public abstract String firstVerificationStep();

    public abstract String from();

    public abstract GuestDetails guestDetails();

    public abstract String inquiryMessage();

    public abstract boolean isCurrentUserListingHost();

    public abstract boolean isFetchingReportedListingStatus();

    public abstract boolean isHostPreview();

    public abstract Listing listing();

    public abstract long listingId();

    public abstract String phoneVerificationCode();

    public abstract PricingQuote pricingQuote();

    public abstract String searchSessionId();

    public abstract SectionedListingDescription sectionedListingDescription();

    public abstract boolean showTranslatedSections();

    public abstract List<SimilarListing> similarListings();

    public abstract Builder toBuilder();

    public abstract SectionedListingDescription translatedSectionedListingDescription();

    public abstract TripPurpose tripPurpose();

    public Bundle toBundle() {
        return ((BundleBuilder) new BundleBuilder().putParcelable(getClass().getSimpleName(), this)).toBundle();
    }

    public boolean hasDates() {
        return (checkInDate() == null || checkOutDate() == null) ? false : true;
    }

    public static Builder builder() {
        return new Builder().listingId(-1).showTranslatedSections(false).isFetchingReportedListingStatus(false).isHostPreview(false).isCurrentUserListingHost(false).guestDetails(new GuestDetails()).similarListings(new ArrayList());
    }

    public static P3State fromArguments(P3Arguments arguments) {
        return builder().listingId(arguments.listingId()).guestDetails(arguments.guestDetails()).from(arguments.from()).isHostPreview(P3Arguments.FROM_MANAGE_LISTING_OR_LYS.equals(arguments.from())).listing(arguments.listing()).pricingQuote(arguments.pricingQuote()).checkInDate(arguments.checkInDate()).checkOutDate(arguments.checkOutDate()).tripPurpose(arguments.tripPurpose()).searchSessionId(arguments.searchSessionId()).firstVerificationStep(arguments.firstVerificationStep()).phoneVerificationCode(arguments.phoneVerificationCode()).build();
    }

    public boolean hasFetchedTranslations() {
        return translatedSectionedListingDescription() != null;
    }
}
