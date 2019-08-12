package com.airbnb.android.contentframework.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_StoryCreationListingAppendix extends C$AutoValue_StoryCreationListingAppendix {
    public static final Creator<AutoValue_StoryCreationListingAppendix> CREATOR = new Creator<AutoValue_StoryCreationListingAppendix>() {
        public AutoValue_StoryCreationListingAppendix createFromParcel(Parcel in) {
            return new AutoValue_StoryCreationListingAppendix(in.readString(), in.readString(), in.readString(), in.readFloat(), in.readLong());
        }

        public AutoValue_StoryCreationListingAppendix[] newArray(int size) {
            return new AutoValue_StoryCreationListingAppendix[size];
        }
    };

    AutoValue_StoryCreationListingAppendix(String thumbnailUrl, String title, String subtitle, float rating, long listingId) {
        super(thumbnailUrl, title, subtitle, rating, listingId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thumbnailUrl());
        dest.writeString(title());
        dest.writeString(subtitle());
        dest.writeFloat(rating());
        dest.writeLong(listingId());
    }

    public int describeContents() {
        return 0;
    }
}
