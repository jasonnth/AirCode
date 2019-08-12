package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.android.p3.models.AutoValue_ListingAmenity */
final class AutoValue_ListingAmenity extends C$AutoValue_ListingAmenity {
    public static final Creator<AutoValue_ListingAmenity> CREATOR = new Creator<AutoValue_ListingAmenity>() {
        public AutoValue_ListingAmenity createFromParcel(Parcel in) {
            return new AutoValue_ListingAmenity(in.readLong(), in.readString(), in.readInt() == 0 ? in.readString() : null);
        }

        public AutoValue_ListingAmenity[] newArray(int size) {
            return new AutoValue_ListingAmenity[size];
        }
    };

    AutoValue_ListingAmenity(long id, String name, String description) {
        super(id, name, description);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mo11025id());
        dest.writeString(name());
        if (description() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(description());
    }

    public int describeContents() {
        return 0;
    }
}
