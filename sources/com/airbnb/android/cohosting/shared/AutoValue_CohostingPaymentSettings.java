package com.airbnb.android.cohosting.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;

final class AutoValue_CohostingPaymentSettings extends C$AutoValue_CohostingPaymentSettings {
    public static final Creator<AutoValue_CohostingPaymentSettings> CREATOR = new Creator<AutoValue_CohostingPaymentSettings>() {
        public AutoValue_CohostingPaymentSettings createFromParcel(Parcel in) {
            boolean z;
            boolean z2 = true;
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            FeeType valueOf = FeeType.valueOf(in.readString());
            int readInt = in.readInt();
            int readInt2 = in.readInt();
            int readInt3 = in.readInt();
            String readString = in.readString();
            if (in.readInt() != 1) {
                z2 = false;
            }
            return new AutoValue_CohostingPaymentSettings(z, valueOf, readInt, readInt2, readInt3, readString, z2);
        }

        public AutoValue_CohostingPaymentSettings[] newArray(int size) {
            return new AutoValue_CohostingPaymentSettings[size];
        }
    };

    AutoValue_CohostingPaymentSettings(boolean shareEarnings, FeeType feeType, int percentage, int minimumFee, int fixedAmount, String amountCurrency, boolean includeCleaningFee) {
        super(shareEarnings, feeType, percentage, minimumFee, fixedAmount, amountCurrency, includeCleaningFee);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeInt(shareEarnings() ? 1 : 0);
        dest.writeString(feeType().name());
        dest.writeInt(percentage());
        dest.writeInt(minimumFee());
        dest.writeInt(fixedAmount());
        dest.writeString(amountCurrency());
        if (!includeCleaningFee()) {
            i = 0;
        }
        dest.writeInt(i);
    }

    public int describeContents() {
        return 0;
    }
}
