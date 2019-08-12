package com.airbnb.android.superhero;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_SuperHeroPostResponse extends C$AutoValue_SuperHeroPostResponse {
    public static final Creator<AutoValue_SuperHeroPostResponse> CREATOR = new Creator<AutoValue_SuperHeroPostResponse>() {
        public AutoValue_SuperHeroPostResponse createFromParcel(Parcel in) {
            return new AutoValue_SuperHeroPostResponse(in.readInt() == 0 ? in.readString() : null, in.readLong(), (SuperHeroMessage) in.readParcelable(SuperHeroMessage.class.getClassLoader()));
        }

        public AutoValue_SuperHeroPostResponse[] newArray(int size) {
            return new AutoValue_SuperHeroPostResponse[size];
        }
    };

    AutoValue_SuperHeroPostResponse(String destination, long destination_type, SuperHeroMessage next_message) {
        super(destination, destination_type, next_message);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (destination() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(destination());
        }
        dest.writeLong(destination_type());
        dest.writeParcelable(next_message(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
