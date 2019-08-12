package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPaymentInstrument;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentInstrument extends GenPaymentInstrument {
    public static final Creator<PaymentInstrument> CREATOR = new Creator<PaymentInstrument>() {
        public PaymentInstrument[] newArray(int size) {
            return new PaymentInstrument[size];
        }

        public PaymentInstrument createFromParcel(Parcel source) {
            PaymentInstrument object = new PaymentInstrument();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("type")
    public void setType(String type) {
        this.mType = C6200PaymentInstrumentType.findByServerKey(type);
    }

    public boolean isDefaultPayout() {
        return getUser() != null && getId() == getUser().getDefaultPayoutGibraltarInstrumentId();
    }

    public boolean isPendingPayout() {
        return !isVerified() && !isUsable() && !hasError();
    }
}
