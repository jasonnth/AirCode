package com.airbnb.android.listing.utils;

import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.AirAddress.Builder;

public enum AddressFieldType {
    Country,
    Street,
    Apt,
    City,
    State,
    Zipcode;

    public static String getAirAddressValue(AddressFieldType field, AirAddress address) {
        switch (field) {
            case Country:
                return address.country();
            case Street:
                return address.streetAddressOne();
            case Apt:
                return address.streetAddressTwo();
            case City:
                return address.city();
            case State:
                return address.state();
            case Zipcode:
                return address.postalCode();
            default:
                throw new UnhandledStateException(field);
        }
    }

    public static void setAirAddressValue(Builder addressBuilder, AddressFieldType field, String value) {
        switch (field) {
            case Country:
                addressBuilder.country(value);
                return;
            case Street:
                addressBuilder.streetAddressOne(value);
                return;
            case Apt:
                addressBuilder.streetAddressTwo(value);
                return;
            case City:
                addressBuilder.city(value);
                return;
            case State:
                addressBuilder.state(value);
                return;
            case Zipcode:
                addressBuilder.postalCode(value);
                return;
            default:
                throw new UnhandledStateException(field);
        }
    }
}
