package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

final class AutoValue_BillPriceQuote_CancellationInfo extends C$AutoValue_BillPriceQuote_CancellationInfo {
    public static final Creator<AutoValue_BillPriceQuote_CancellationInfo> CREATOR = new Creator<AutoValue_BillPriceQuote_CancellationInfo>() {
        public AutoValue_BillPriceQuote_CancellationInfo createFromParcel(Parcel in) {
            return new AutoValue_BillPriceQuote_CancellationInfo(in.readString(), in.readArrayList(String.class.getClassLoader()));
        }

        public AutoValue_BillPriceQuote_CancellationInfo[] newArray(int size) {
            return new AutoValue_BillPriceQuote_CancellationInfo[size];
        }
    };

    AutoValue_BillPriceQuote_CancellationInfo(String title, List<String> subtitles) {
        super(title, subtitles);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title());
        dest.writeList(subtitles());
    }

    public int describeContents() {
        return 0;
    }
}
