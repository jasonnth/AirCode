package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

/* renamed from: com.airbnb.android.p3.models.AutoValue_HomeLayout */
final class AutoValue_HomeLayout extends C$AutoValue_HomeLayout {
    public static final Creator<AutoValue_HomeLayout> CREATOR = new Creator<AutoValue_HomeLayout>() {
        public AutoValue_HomeLayout createFromParcel(Parcel in) {
            return new AutoValue_HomeLayout(in.readArrayList(Floor.class.getClassLoader()));
        }

        public AutoValue_HomeLayout[] newArray(int size) {
            return new AutoValue_HomeLayout[size];
        }
    };

    AutoValue_HomeLayout(List<Floor> floors) {
        super(floors);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(floors());
    }

    public int describeContents() {
        return 0;
    }
}
