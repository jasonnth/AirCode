package com.airbnb.android.core.data;

import android.os.Parcelable;

public abstract class AddressParts implements Parcelable {

    public static abstract class Builder {
        public abstract AddressParts build();

        public abstract Builder city(String str);

        public abstract Builder countryCode(String str);

        public abstract Builder state(String str);

        public abstract Builder street1(String str);

        public abstract Builder street2(String str);

        public abstract Builder zipCode(String str);
    }

    public abstract String city();

    public abstract String countryCode();

    public abstract String state();

    public abstract String street1();

    public abstract String street2();

    public abstract Builder toBuilder();

    public abstract String zipCode();

    public static Builder builder() {
        return new Builder();
    }
}
