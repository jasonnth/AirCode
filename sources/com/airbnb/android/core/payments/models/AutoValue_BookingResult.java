package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.payments.models.BookingResult.Error;
import java.util.ArrayList;
import java.util.List;

final class AutoValue_BookingResult extends C$AutoValue_BookingResult {
    public static final Creator<AutoValue_BookingResult> CREATOR = new Creator<AutoValue_BookingResult>() {
        public AutoValue_BookingResult createFromParcel(Parcel in) {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            boolean z5;
            boolean z6 = true;
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            ArrayList readArrayList = in.readArrayList(Error.class.getClassLoader());
            if (in.readInt() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (in.readInt() == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (in.readInt() == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (in.readInt() == 1) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (in.readInt() != 1) {
                z6 = false;
            }
            return new AutoValue_BookingResult(z, readArrayList, z2, z3, z4, z5, z6, in.readString());
        }

        public AutoValue_BookingResult[] newArray(int size) {
            return new AutoValue_BookingResult[size];
        }
    };

    AutoValue_BookingResult(boolean success, List<Error> errors, boolean settlementCurrencyChange, boolean setHardFallback, boolean requireZipCodeExistingCc, boolean setFirstTimeBookingCookie, boolean setReservationRequestedCookie, String reservationConfirmationCode) {
        super(success, errors, settlementCurrencyChange, setHardFallback, requireZipCodeExistingCc, setFirstTimeBookingCookie, setReservationRequestedCookie, reservationConfirmationCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        dest.writeInt(success() ? 1 : 0);
        dest.writeList(errors());
        if (settlementCurrencyChange()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (setHardFallback()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        dest.writeInt(i2);
        if (requireZipCodeExistingCc()) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        dest.writeInt(i3);
        if (setFirstTimeBookingCookie()) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        dest.writeInt(i4);
        if (!setReservationRequestedCookie()) {
            i5 = 0;
        }
        dest.writeInt(i5);
        dest.writeString(reservationConfirmationCode());
    }

    public int describeContents() {
        return 0;
    }
}
