package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.presenters.CountryCodeItem;

final class AutoValue_AirPhone extends C$AutoValue_AirPhone {
    public static final Creator<AutoValue_AirPhone> CREATOR = new Creator<AutoValue_AirPhone>() {
        public AutoValue_AirPhone createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3;
            String str4 = null;
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
            }
            return new AutoValue_AirPhone(str, str2, str3, str4, (CountryCodeItem) in.readParcelable(CountryCodeItem.class.getClassLoader()));
        }

        public AutoValue_AirPhone[] newArray(int size) {
            return new AutoValue_AirPhone[size];
        }
    };

    AutoValue_AirPhone(String formattedPhone, String phoneInputText, String phoneDisplayText, String phoneSMSCode, CountryCodeItem countryCodeItem) {
        super(formattedPhone, phoneInputText, phoneDisplayText, phoneSMSCode, countryCodeItem);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (formattedPhone() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(formattedPhone());
        }
        if (phoneInputText() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(phoneInputText());
        }
        if (phoneDisplayText() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(phoneDisplayText());
        }
        if (phoneSMSCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(phoneSMSCode());
        }
        dest.writeParcelable(countryCodeItem(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
