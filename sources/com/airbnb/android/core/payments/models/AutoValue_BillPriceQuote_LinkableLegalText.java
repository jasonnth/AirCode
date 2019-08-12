package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.payments.models.BillPriceQuote.Link;
import java.util.List;

final class AutoValue_BillPriceQuote_LinkableLegalText extends C$AutoValue_BillPriceQuote_LinkableLegalText {
    public static final Creator<AutoValue_BillPriceQuote_LinkableLegalText> CREATOR = new Creator<AutoValue_BillPriceQuote_LinkableLegalText>() {
        public AutoValue_BillPriceQuote_LinkableLegalText createFromParcel(Parcel in) {
            return new AutoValue_BillPriceQuote_LinkableLegalText(in.readString(), in.readString(), in.readArrayList(Link.class.getClassLoader()));
        }

        public AutoValue_BillPriceQuote_LinkableLegalText[] newArray(int size) {
            return new AutoValue_BillPriceQuote_LinkableLegalText[size];
        }
    };

    AutoValue_BillPriceQuote_LinkableLegalText(String title, String text, List<Link> links) {
        super(title, text, links);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title());
        dest.writeString(text());
        dest.writeList(links());
    }

    public int describeContents() {
        return 0;
    }
}
