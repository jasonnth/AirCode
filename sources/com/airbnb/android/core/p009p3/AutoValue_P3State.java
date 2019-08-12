package com.airbnb.android.core.p009p3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.SectionedListingDescription;
import com.airbnb.android.core.models.SimilarListing;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.android.core.p3.AutoValue_P3State */
final class AutoValue_P3State extends C$AutoValue_P3State {
    public static final Creator<AutoValue_P3State> CREATOR = new Creator<AutoValue_P3State>() {
        public AutoValue_P3State createFromParcel(Parcel in) {
            String str;
            boolean z = in.readInt() == 1;
            boolean z2 = in.readInt() == 1;
            boolean z3 = in.readInt() == 1;
            boolean z4 = in.readInt() == 1;
            ArrayList readArrayList = in.readArrayList(SimilarListing.class.getClassLoader());
            GuestDetails guestDetails = (GuestDetails) in.readParcelable(GuestDetails.class.getClassLoader());
            long readLong = in.readLong();
            SectionedListingDescription sectionedListingDescription = (SectionedListingDescription) in.readParcelable(SectionedListingDescription.class.getClassLoader());
            SectionedListingDescription sectionedListingDescription2 = (SectionedListingDescription) in.readParcelable(SectionedListingDescription.class.getClassLoader());
            Listing listing = (Listing) in.readParcelable(Listing.class.getClassLoader());
            PricingQuote pricingQuote = (PricingQuote) in.readParcelable(PricingQuote.class.getClassLoader());
            AirDate airDate = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            AirDate airDate2 = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            String str2 = in.readInt() == 0 ? in.readString() : null;
            String str3 = in.readInt() == 0 ? in.readString() : null;
            String str4 = in.readInt() == 0 ? in.readString() : null;
            TripPurpose tripPurpose = (TripPurpose) in.readParcelable(TripPurpose.class.getClassLoader());
            String str5 = in.readInt() == 0 ? in.readString() : null;
            String str6 = in.readInt() == 0 ? in.readString() : null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            return new AutoValue_P3State(z, z2, z3, z4, readArrayList, guestDetails, readLong, sectionedListingDescription, sectionedListingDescription2, listing, pricingQuote, airDate, airDate2, str2, str3, str4, tripPurpose, str5, str6, str);
        }

        public AutoValue_P3State[] newArray(int size) {
            return new AutoValue_P3State[size];
        }
    };

    AutoValue_P3State(boolean showTranslatedSections, boolean isFetchingReportedListingStatus, boolean isHostPreview, boolean isCurrentUserListingHost, List<SimilarListing> similarListings, GuestDetails guestDetails, long listingId, SectionedListingDescription sectionedListingDescription, SectionedListingDescription translatedSectionedListingDescription, Listing listing, PricingQuote pricingQuote, AirDate checkInDate, AirDate checkOutDate, String inquiryMessage, String cancellationPolicyLabel, String from, TripPurpose tripPurpose, String searchSessionId, String firstVerificationStep, String phoneVerificationCode) {
        super(showTranslatedSections, isFetchingReportedListingStatus, isHostPreview, isCurrentUserListingHost, similarListings, guestDetails, listingId, sectionedListingDescription, translatedSectionedListingDescription, listing, pricingQuote, checkInDate, checkOutDate, inquiryMessage, cancellationPolicyLabel, from, tripPurpose, searchSessionId, firstVerificationStep, phoneVerificationCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2;
        int i3;
        dest.writeInt(showTranslatedSections() ? 1 : 0);
        if (isFetchingReportedListingStatus()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (isHostPreview()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        dest.writeInt(i2);
        if (isCurrentUserListingHost()) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        dest.writeInt(i3);
        dest.writeList(similarListings());
        dest.writeParcelable(guestDetails(), flags);
        dest.writeLong(listingId());
        dest.writeParcelable(sectionedListingDescription(), flags);
        dest.writeParcelable(translatedSectionedListingDescription(), flags);
        dest.writeParcelable(listing(), flags);
        dest.writeParcelable(pricingQuote(), flags);
        dest.writeParcelable(checkInDate(), flags);
        dest.writeParcelable(checkOutDate(), flags);
        if (inquiryMessage() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(inquiryMessage());
        }
        if (cancellationPolicyLabel() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(cancellationPolicyLabel());
        }
        if (from() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(from());
        }
        dest.writeParcelable(tripPurpose(), flags);
        if (searchSessionId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(searchSessionId());
        }
        if (firstVerificationStep() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(firstVerificationStep());
        }
        if (phoneVerificationCode() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(phoneVerificationCode());
    }

    public int describeContents() {
        return 0;
    }
}
