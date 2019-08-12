package com.airbnb.android.core.models.payments;

import java.io.Serializable;

public class PostalAddress implements Serializable {
    private final String countryCodeAlpha2;
    private final String extendedAddress;
    private final String locality;
    private final String postalCode;
    private final String recipientName;
    private final String region;
    private final String streetAddress;

    public PostalAddress(com.braintreepayments.api.models.PostalAddress address) {
        this.recipientName = address.getRecipientName();
        this.streetAddress = address.getStreetAddress();
        this.extendedAddress = address.getExtendedAddress();
        this.locality = address.getLocality();
        this.region = address.getRegion();
        this.postalCode = address.getPostalCode();
        this.countryCodeAlpha2 = address.getCountryCodeAlpha2();
    }

    public String getRecipientName() {
        return this.recipientName;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public String getExtendedAddress() {
        return this.extendedAddress;
    }

    public String getLocality() {
        return this.locality;
    }

    public String getRegion() {
        return this.region;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getCountryCodeAlpha2() {
        return this.countryCodeAlpha2;
    }
}
