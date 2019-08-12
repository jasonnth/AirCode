package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenBookingRequestOperation;

public class BookingRequestOperation extends GenBookingRequestOperation {
    public static final Creator<BookingRequestOperation> CREATOR = new Creator<BookingRequestOperation>() {
        public BookingRequestOperation[] newArray(int size) {
            return new BookingRequestOperation[size];
        }

        public BookingRequestOperation createFromParcel(Parcel source) {
            BookingRequestOperation object = new BookingRequestOperation();
            object.readFromParcel(source);
            return object;
        }
    };
}
