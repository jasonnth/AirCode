package com.airbnb.android.p011p3.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.android.p3.models.AutoValue_ReviewSummaryItem */
final class AutoValue_ReviewSummaryItem extends C$AutoValue_ReviewSummaryItem {
    public static final Creator<AutoValue_ReviewSummaryItem> CREATOR = new Creator<AutoValue_ReviewSummaryItem>() {
        public AutoValue_ReviewSummaryItem createFromParcel(Parcel in) {
            return new AutoValue_ReviewSummaryItem(in.readString(), in.readInt());
        }

        public AutoValue_ReviewSummaryItem[] newArray(int size) {
            return new AutoValue_ReviewSummaryItem[size];
        }
    };

    AutoValue_ReviewSummaryItem(String label, int value) {
        super(label, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label());
        dest.writeInt(value());
    }

    public int describeContents() {
        return 0;
    }
}
