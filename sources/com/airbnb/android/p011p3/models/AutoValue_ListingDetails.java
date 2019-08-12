package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.SectionedListingDescription;
import com.airbnb.android.core.models.User;
import java.util.List;

/* renamed from: com.airbnb.android.p3.models.AutoValue_ListingDetails */
final class AutoValue_ListingDetails extends C$AutoValue_ListingDetails {
    public static final Creator<AutoValue_ListingDetails> CREATOR = new Creator<AutoValue_ListingDetails>() {
        public AutoValue_ListingDetails createFromParcel(Parcel in) {
            return new AutoValue_ListingDetails(in.readString(), in.readString(), in.readString(), in.readString(), in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 1, in.readInt() == 1, in.readArrayList(ListingAmenity.class.getClassLoader()), in.readArrayList(Photo.class.getClassLoader()), in.readInt() == 0 ? Float.valueOf(in.readFloat()) : null, in.readInt() == 0 ? Integer.valueOf(in.readInt()) : null, in.readInt() == 0 ? Double.valueOf(in.readDouble()) : null, in.readInt() == 0 ? Double.valueOf(in.readDouble()) : null, (User) in.readParcelable(User.class.getClassLoader()), (GuestControls) in.readParcelable(GuestControls.class.getClassLoader()), (SectionedListingDescription) in.readParcelable(SectionedListingDescription.class.getClassLoader()), (ListingReviewDetails) in.readParcelable(ListingReviewDetails.class.getClassLoader()));
        }

        public AutoValue_ListingDetails[] newArray(int size) {
            return new AutoValue_ListingDetails[size];
        }
    };

    AutoValue_ListingDetails(String p3SummaryTitle, String p3SummaryAddress, String roomTypeCategory, String descriptionLocale, String bathroomLabel, String initialDescriptionAuthorType, String roomAndPropertyType, String localizedCheckInTimeWindow, String localizedCheckOutTime, String city, String countryCode, String country, String state, String license, boolean isHostedBySuperhost, boolean isBusinessTravelReady, List<ListingAmenity> listingAmenities, List<Photo> photos, Float starRating, Integer minNights, Double lat, Double lng, User primaryHost, GuestControls guestControls, SectionedListingDescription sectionedDescription, ListingReviewDetails reviewDetailsInterface) {
        super(p3SummaryTitle, p3SummaryAddress, roomTypeCategory, descriptionLocale, bathroomLabel, initialDescriptionAuthorType, roomAndPropertyType, localizedCheckInTimeWindow, localizedCheckOutTime, city, countryCode, country, state, license, isHostedBySuperhost, isBusinessTravelReady, listingAmenities, photos, starRating, minNights, lat, lng, primaryHost, guestControls, sectionedDescription, reviewDetailsInterface);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2;
        dest.writeString(p3SummaryTitle());
        dest.writeString(p3SummaryAddress());
        dest.writeString(roomTypeCategory());
        dest.writeString(descriptionLocale());
        if (bathroomLabel() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(bathroomLabel());
        }
        if (initialDescriptionAuthorType() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(initialDescriptionAuthorType());
        }
        if (roomAndPropertyType() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(roomAndPropertyType());
        }
        if (localizedCheckInTimeWindow() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(localizedCheckInTimeWindow());
        }
        if (localizedCheckOutTime() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(localizedCheckOutTime());
        }
        if (city() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(city());
        }
        if (countryCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(countryCode());
        }
        if (country() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(country());
        }
        if (state() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(state());
        }
        if (license() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(license());
        }
        if (isHostedBySuperhost()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (isBusinessTravelReady()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        dest.writeInt(i2);
        dest.writeList(listingAmenities());
        dest.writeList(photos());
        if (starRating() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeFloat(starRating().floatValue());
        }
        if (minNights() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(minNights().intValue());
        }
        if (lat() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeDouble(lat().doubleValue());
        }
        if (lng() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeDouble(lng().doubleValue());
        }
        dest.writeParcelable(primaryHost(), flags);
        dest.writeParcelable(guestControls(), flags);
        dest.writeParcelable(sectionedDescription(), flags);
        dest.writeParcelable(reviewDetailsInterface(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
