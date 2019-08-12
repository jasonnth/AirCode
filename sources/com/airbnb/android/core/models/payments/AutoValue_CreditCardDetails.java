package com.airbnb.android.core.models.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_CreditCardDetails extends C$AutoValue_CreditCardDetails {
    public static final Creator<AutoValue_CreditCardDetails> CREATOR = new Creator<AutoValue_CreditCardDetails>() {
        public AutoValue_CreditCardDetails createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6 = null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            } else {
                str3 = null;
            }
            if (in.readInt() == 0) {
                str4 = in.readString();
            } else {
                str4 = null;
            }
            if (in.readInt() == 0) {
                str5 = in.readString();
            } else {
                str5 = null;
            }
            if (in.readInt() == 0) {
                str6 = in.readString();
            }
            return new AutoValue_CreditCardDetails(str, str2, str3, str4, str5, str6);
        }

        public AutoValue_CreditCardDetails[] newArray(int size) {
            return new AutoValue_CreditCardDetails[size];
        }
    };

    AutoValue_CreditCardDetails(String countryCode, String cardNumber, String expirationMonth, String expirationYear, String cvv, String postalCode) {
        super(countryCode, cardNumber, expirationMonth, expirationYear, cvv, postalCode);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (countryCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(countryCode());
        }
        if (cardNumber() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(cardNumber());
        }
        if (expirationMonth() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(expirationMonth());
        }
        if (expirationYear() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(expirationYear());
        }
        if (cvv() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(cvv());
        }
        if (postalCode() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(postalCode());
    }

    public int describeContents() {
        return 0;
    }
}
