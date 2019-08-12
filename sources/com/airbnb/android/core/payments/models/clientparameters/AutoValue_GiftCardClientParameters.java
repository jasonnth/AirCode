package com.airbnb.android.core.payments.models.clientparameters;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.payments.models.BillProductType;

final class AutoValue_GiftCardClientParameters extends C$AutoValue_GiftCardClientParameters {
    public static final Creator<AutoValue_GiftCardClientParameters> CREATOR = new Creator<AutoValue_GiftCardClientParameters>() {
        public AutoValue_GiftCardClientParameters createFromParcel(Parcel in) {
            return new AutoValue_GiftCardClientParameters(BillProductType.valueOf(in.readString()), in.readString(), in.readString(), in.readString(), (CurrencyAmount) in.readParcelable(CurrencyAmount.class.getClassLoader()), in.readString(), in.readString(), in.readLong(), in.readLong());
        }

        public AutoValue_GiftCardClientParameters[] newArray(int size) {
            return new AutoValue_GiftCardClientParameters[size];
        }
    };

    AutoValue_GiftCardClientParameters(BillProductType productType, String recipientMessage, String recipientEmail, String recipientName, CurrencyAmount giftCreditCurrencyAmount, String categoryType, String locale, long overlayId, long videoId) {
        super(productType, recipientMessage, recipientEmail, recipientName, giftCreditCurrencyAmount, categoryType, locale, overlayId, videoId);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productType().name());
        dest.writeString(recipientMessage());
        dest.writeString(recipientEmail());
        dest.writeString(recipientName());
        dest.writeParcelable(giftCreditCurrencyAmount(), flags);
        dest.writeString(categoryType());
        dest.writeString(locale());
        dest.writeLong(overlayId());
        dest.writeLong(videoId());
    }

    public int describeContents() {
        return 0;
    }
}
