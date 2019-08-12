package com.airbnb.p027n2.utils;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.n2.utils.AutoValue_AccessibleDrawableResource */
final class AutoValue_AccessibleDrawableResource extends C$AutoValue_AccessibleDrawableResource {
    public static final Creator<AutoValue_AccessibleDrawableResource> CREATOR = new Creator<AutoValue_AccessibleDrawableResource>() {
        public AutoValue_AccessibleDrawableResource createFromParcel(Parcel in) {
            return new AutoValue_AccessibleDrawableResource(in.readInt(), in.readString());
        }

        public AutoValue_AccessibleDrawableResource[] newArray(int size) {
            return new AutoValue_AccessibleDrawableResource[size];
        }
    };

    AutoValue_AccessibleDrawableResource(int drawableResource, String contentDescription) {
        super(drawableResource, contentDescription);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(drawableResource());
        dest.writeString(contentDescription());
    }

    public int describeContents() {
        return 0;
    }
}
