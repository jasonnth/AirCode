package com.airbnb.android.payout.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.payout.create.PayoutFormRuleType;

final class AutoValue_PayoutFormFieldInputWrapper extends C$AutoValue_PayoutFormFieldInputWrapper {
    public static final Creator<AutoValue_PayoutFormFieldInputWrapper> CREATOR = new Creator<AutoValue_PayoutFormFieldInputWrapper>() {
        public AutoValue_PayoutFormFieldInputWrapper createFromParcel(Parcel in) {
            String str;
            PayoutFormRuleType payoutFormRuleType = null;
            boolean z = true;
            PayoutFormField payoutFormField = (PayoutFormField) in.readParcelable(PayoutFormField.class.getClassLoader());
            if (in.readInt() != 1) {
                z = false;
            }
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                payoutFormRuleType = PayoutFormRuleType.valueOf(in.readString());
            }
            return new AutoValue_PayoutFormFieldInputWrapper(payoutFormField, z, str, payoutFormRuleType);
        }

        public AutoValue_PayoutFormFieldInputWrapper[] newArray(int size) {
            return new AutoValue_PayoutFormFieldInputWrapper[size];
        }
    };

    AutoValue_PayoutFormFieldInputWrapper(PayoutFormField payoutFormField, boolean hasValidationError, String inputValue, PayoutFormRuleType validationErrorType) {
        super(payoutFormField, hasValidationError, inputValue, validationErrorType);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(payoutFormField(), flags);
        dest.writeInt(hasValidationError() ? 1 : 0);
        if (inputValue() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(inputValue());
        }
        if (validationErrorType() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(validationErrorType().name());
    }

    public int describeContents() {
        return 0;
    }
}
