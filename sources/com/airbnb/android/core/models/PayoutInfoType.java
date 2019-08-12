package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.generated.GenPayoutInfoType;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayoutInfoType extends GenPayoutInfoType {
    public static final Creator<PayoutInfoType> CREATOR = new Creator<PayoutInfoType>() {
        public PayoutInfoType[] newArray(int size) {
            return new PayoutInfoType[size];
        }

        public PayoutInfoType createFromParcel(Parcel source) {
            PayoutInfoType object = new PayoutInfoType();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("info_type")
    public void setInfoType(String serverKey) {
        this.mInfoType = C6200PaymentInstrumentType.findByServerKey(serverKey);
    }

    public String getCombinedDetails(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(getDetailsText());
        if (!TextUtils.isEmpty(getAdditionalFeesText())) {
            sb.append("\n");
            sb.append(context.getString(C0716R.string.additional_fees));
            sb.append(": ");
            sb.append(getAdditionalFeesText());
        }
        if (!TextUtils.isEmpty(getProcessingTimeText())) {
            sb.append("\n");
            sb.append(context.getString(C0716R.string.processing_time));
            sb.append(": ");
            sb.append(getProcessingTimeText());
        }
        sb.append("\n");
        sb.append(context.getString(C0716R.string.ml_currency));
        sb.append(": ");
        sb.append(getCurrencies().toString());
        return sb.toString();
    }
}
