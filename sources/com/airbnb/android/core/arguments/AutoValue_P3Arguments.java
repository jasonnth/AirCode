package com.airbnb.android.core.arguments;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;

final class AutoValue_P3Arguments extends C$AutoValue_P3Arguments {
    public static final Creator<AutoValue_P3Arguments> CREATOR = new Creator<AutoValue_P3Arguments>() {
        public AutoValue_P3Arguments createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3;
            String str4;
            long readLong = in.readLong();
            GuestDetails guestDetails = (GuestDetails) in.readParcelable(GuestDetails.class.getClassLoader());
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            Listing listing = (Listing) in.readParcelable(Listing.class.getClassLoader());
            PricingQuote pricingQuote = (PricingQuote) in.readParcelable(PricingQuote.class.getClassLoader());
            AirDate airDate = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            AirDate airDate2 = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            TripPurpose tripPurpose = (TripPurpose) in.readParcelable(TripPurpose.class.getClassLoader());
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            } else {
                str3 = null;
            }
            if (in.readInt() == 0) {
                str4 = in.readString();
            } else {
                str4 = null;
            }
            return new AutoValue_P3Arguments(readLong, guestDetails, str, listing, pricingQuote, airDate, airDate2, tripPurpose, str2, str3, str4);
        }

        public AutoValue_P3Arguments[] newArray(int size) {
            return new AutoValue_P3Arguments[size];
        }
    };

    AutoValue_P3Arguments(long listingId, GuestDetails guestDetails, String from, Listing listing, PricingQuote pricingQuote, AirDate checkInDate, AirDate checkOutDate, TripPurpose tripPurpose, String searchSessionId, String firstVerificationStep, String phoneVerificationCode) {
        super(listingId, guestDetails, from, listing, pricingQuote, checkInDate, checkOutDate, tripPurpose, searchSessionId, firstVerificationStep, phoneVerificationCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(listingId());
        dest.writeParcelable(guestDetails(), flags);
        if (from() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(from());
        }
        dest.writeParcelable(listing(), flags);
        dest.writeParcelable(pricingQuote(), flags);
        dest.writeParcelable(checkInDate(), flags);
        dest.writeParcelable(checkOutDate(), flags);
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
