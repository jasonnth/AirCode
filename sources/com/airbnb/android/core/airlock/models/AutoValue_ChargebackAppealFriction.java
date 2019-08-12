package com.airbnb.android.core.airlock.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.airlock.enums.AirlockStatus;
import java.util.List;

final class AutoValue_ChargebackAppealFriction extends C$AutoValue_ChargebackAppealFriction {
    public static final Creator<AutoValue_ChargebackAppealFriction> CREATOR = new Creator<AutoValue_ChargebackAppealFriction>() {
        public AutoValue_ChargebackAppealFriction createFromParcel(Parcel in) {
            return new AutoValue_ChargebackAppealFriction(in.readDouble(), AirlockStatus.valueOf(in.readString()), in.readString(), in.readString(), in.readArrayList(String.class.getClassLoader()), in.readLong(), in.readString(), in.readLong(), in.readLong());
        }

        public AutoValue_ChargebackAppealFriction[] newArray(int size) {
            return new AutoValue_ChargebackAppealFriction[size];
        }
    };

    AutoValue_ChargebackAppealFriction(double version, AirlockStatus airlockStatus, String ccLastFourDigits, String ccType, List<String> emails, long hostingId, String hostingName, long paymentInstrumentId, long reservationId) {
        super(version, airlockStatus, ccLastFourDigits, ccType, emails, hostingId, hostingName, paymentInstrumentId, reservationId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(version());
        dest.writeString(airlockStatus().name());
        dest.writeString(ccLastFourDigits());
        dest.writeString(ccType());
        dest.writeList(emails());
        dest.writeLong(hostingId());
        dest.writeString(hostingName());
        dest.writeLong(paymentInstrumentId());
        dest.writeLong(reservationId());
    }

    public int describeContents() {
        return 0;
    }
}
