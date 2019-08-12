package com.airbnb.android.p011p3.models;

import android.os.Parcelable;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.SectionedListingDescription;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(builder = Builder.class)
/* renamed from: com.airbnb.android.p3.models.ListingDetails */
public abstract class ListingDetails implements Parcelable {

    /* renamed from: com.airbnb.android.p3.models.ListingDetails$Builder */
    public static abstract class Builder {
        @JsonProperty
        public abstract Builder bathroomLabel(String str);

        public abstract ListingDetails build();

        @JsonProperty
        public abstract Builder city(String str);

        @JsonProperty
        public abstract Builder country(String str);

        @JsonProperty
        public abstract Builder countryCode(String str);

        @JsonProperty
        public abstract Builder descriptionLocale(String str);

        @JsonProperty
        public abstract Builder guestControls(GuestControls guestControls);

        @JsonProperty
        public abstract Builder initialDescriptionAuthorType(String str);

        @JsonProperty
        public abstract Builder isBusinessTravelReady(boolean z);

        @JsonProperty
        public abstract Builder isHostedBySuperhost(boolean z);

        @JsonProperty
        public abstract Builder lat(Double d);

        @JsonProperty
        public abstract Builder license(String str);

        @JsonProperty
        public abstract Builder listingAmenities(List<ListingAmenity> list);

        @JsonProperty
        public abstract Builder lng(Double d);

        @JsonProperty
        public abstract Builder localizedCheckInTimeWindow(String str);

        @JsonProperty
        public abstract Builder localizedCheckOutTime(String str);

        @JsonProperty
        public abstract Builder minNights(Integer num);

        @JsonProperty
        public abstract Builder p3SummaryAddress(String str);

        @JsonProperty
        public abstract Builder p3SummaryTitle(String str);

        @JsonProperty
        public abstract Builder photos(List<Photo> list);

        @JsonProperty
        public abstract Builder primaryHost(User user);

        @JsonProperty
        public abstract Builder reviewDetailsInterface(ListingReviewDetails listingReviewDetails);

        @JsonProperty
        public abstract Builder roomAndPropertyType(String str);

        @JsonProperty
        public abstract Builder roomTypeCategory(String str);

        @JsonProperty
        public abstract Builder sectionedDescription(SectionedListingDescription sectionedListingDescription);

        @JsonProperty
        public abstract Builder starRating(Float f);

        @JsonProperty
        public abstract Builder state(String str);
    }

    public abstract String bathroomLabel();

    public abstract String city();

    public abstract String country();

    public abstract String countryCode();

    public abstract String descriptionLocale();

    public abstract GuestControls guestControls();

    public abstract String initialDescriptionAuthorType();

    public abstract boolean isBusinessTravelReady();

    public abstract boolean isHostedBySuperhost();

    public abstract Double lat();

    public abstract String license();

    public abstract List<ListingAmenity> listingAmenities();

    public abstract Double lng();

    public abstract String localizedCheckInTimeWindow();

    public abstract String localizedCheckOutTime();

    public abstract Integer minNights();

    public abstract String p3SummaryAddress();

    public abstract String p3SummaryTitle();

    public abstract List<Photo> photos();

    public abstract User primaryHost();

    public abstract ListingReviewDetails reviewDetailsInterface();

    public abstract String roomAndPropertyType();

    public abstract String roomTypeCategory();

    public abstract SectionedListingDescription sectionedDescription();

    public abstract Float starRating();

    public abstract String state();

    public static Builder builder() {
        return new Builder();
    }
}
