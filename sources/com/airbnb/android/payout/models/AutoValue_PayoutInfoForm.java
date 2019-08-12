package com.airbnb.android.payout.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

final class AutoValue_PayoutInfoForm extends C$AutoValue_PayoutInfoForm {
    public static final Creator<AutoValue_PayoutInfoForm> CREATOR = new Creator<AutoValue_PayoutInfoForm>() {
        public AutoValue_PayoutInfoForm createFromParcel(Parcel in) {
            return new AutoValue_PayoutInfoForm(in.readString(), in.readString(), in.readString(), in.readArrayList(String.class.getClassLoader()), in.readArrayList(ExtraPayoutAttribute.class.getClassLoader()), in.readArrayList(PayoutFormField.class.getClassLoader()), PayoutInfoFormType.valueOf(in.readString()));
        }

        public AutoValue_PayoutInfoForm[] newArray(int size) {
            return new AutoValue_PayoutInfoForm[size];
        }
    };

    AutoValue_PayoutInfoForm(String displayName, String timelinessInfo, String transactionFeeInfo, List<String> currencies, List<ExtraPayoutAttribute> extraPayoutAttributes, List<PayoutFormField> payoutFormFields, PayoutInfoFormType payoutMethodType) {
        super(displayName, timelinessInfo, transactionFeeInfo, currencies, extraPayoutAttributes, payoutFormFields, payoutMethodType);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayName());
        dest.writeString(timelinessInfo());
        dest.writeString(transactionFeeInfo());
        dest.writeList(currencies());
        dest.writeList(extraPayoutAttributes());
        dest.writeList(payoutFormFields());
        dest.writeString(payoutMethodType().name());
    }

    public int describeContents() {
        return 0;
    }
}
