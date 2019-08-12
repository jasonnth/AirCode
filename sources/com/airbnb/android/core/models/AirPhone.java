package com.airbnb.android.core.models;

import android.os.Parcelable;
import com.airbnb.android.core.presenters.CountryCodeItem;

public abstract class AirPhone implements Parcelable {

    public static abstract class Builder {
        public abstract AirPhone build();

        public abstract Builder countryCodeItem(CountryCodeItem countryCodeItem);

        public abstract Builder formattedPhone(String str);

        public abstract Builder phoneDisplayText(String str);

        public abstract Builder phoneInputText(String str);

        public abstract Builder phoneSMSCode(String str);
    }

    public abstract CountryCodeItem countryCodeItem();

    public abstract String formattedPhone();

    public abstract String phoneDisplayText();

    public abstract String phoneInputText();

    public abstract String phoneSMSCode();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }

    public static AirPhone withSMSCode(AirPhone airPhone, String smsCode) {
        if (airPhone != null) {
            return airPhone.toBuilder().phoneSMSCode(smsCode).build();
        }
        return builder().phoneSMSCode(smsCode).build();
    }

    public static AirPhone withCountryCodeItem(AirPhone airPhone, CountryCodeItem item) {
        if (airPhone != null) {
            return airPhone.toBuilder().countryCodeItem(item).build();
        }
        return builder().countryCodeItem(item).build();
    }
}
