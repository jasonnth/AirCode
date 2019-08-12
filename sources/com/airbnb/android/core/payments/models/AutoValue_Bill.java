package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.payments.models.Bill.BillItem;
import java.util.List;

final class AutoValue_Bill extends C$AutoValue_Bill {
    public static final Creator<AutoValue_Bill> CREATOR = new Creator<AutoValue_Bill>() {
        public AutoValue_Bill createFromParcel(Parcel in) {
            return new AutoValue_Bill(in.readArrayList(BillItem.class.getClassLoader()), in.readArrayList(BookingResult.class.getClassLoader()), in.readLong(), in.readString(), in.readLong(), (RedirectSettings) in.readParcelable(RedirectSettings.class.getClassLoader()));
        }

        public AutoValue_Bill[] newArray(int size) {
            return new AutoValue_Bill[size];
        }
    };

    AutoValue_Bill(List<BillItem> billItems, List<BookingResult> bookingResults, long status, String token, long userId, RedirectSettings redirectSettings) {
        super(billItems, bookingResults, status, token, userId, redirectSettings);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(billItems());
        dest.writeList(bookingResults());
        dest.writeLong(status());
        dest.writeString(token());
        dest.writeLong(userId());
        dest.writeParcelable(redirectSettings(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
