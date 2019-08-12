package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_BillPriceQuote_Link extends C$AutoValue_BillPriceQuote_Link {
    public static final Creator<AutoValue_BillPriceQuote_Link> CREATOR = new Creator<AutoValue_BillPriceQuote_Link>() {
        public AutoValue_BillPriceQuote_Link createFromParcel(Parcel in) {
            return new AutoValue_BillPriceQuote_Link(in.readString(), in.readString());
        }

        public AutoValue_BillPriceQuote_Link[] newArray(int size) {
            return new AutoValue_BillPriceQuote_Link[size];
        }
    };

    AutoValue_BillPriceQuote_Link(String text, String url) {
        super(text, url);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text());
        dest.writeString(url());
    }

    public int describeContents() {
        return 0;
    }
}
