package com.airbnb.android.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.core.models.$AutoValue_AirAddress reason: invalid class name */
abstract class C$AutoValue_AirAddress extends AirAddress {
    private final String city;
    private final String country;
    private final String countryCode;
    private final Double latitude;
    private final Double longitude;
    private final String postalCode;
    private final String state;
    private final String streetAddressOne;
    private final String streetAddressTwo;

    /* renamed from: com.airbnb.android.core.models.$AutoValue_AirAddress$Builder */
    static final class Builder extends com.airbnb.android.core.models.AirAddress.Builder {
        private String city;
        private String country;
        private String countryCode;
        private Double latitude;
        private Double longitude;
        private String postalCode;
        private String state;
        private String streetAddressOne;
        private String streetAddressTwo;

        Builder() {
        }

        private Builder(AirAddress source) {
            this.streetAddressOne = source.streetAddressOne();
            this.streetAddressTwo = source.streetAddressTwo();
            this.city = source.city();
            this.state = source.state();
            this.postalCode = source.postalCode();
            this.country = source.country();
            this.countryCode = source.countryCode();
            this.latitude = source.latitude();
            this.longitude = source.longitude();
        }

        public com.airbnb.android.core.models.AirAddress.Builder streetAddressOne(String streetAddressOne2) {
            this.streetAddressOne = streetAddressOne2;
            return this;
        }

        public com.airbnb.android.core.models.AirAddress.Builder streetAddressTwo(String streetAddressTwo2) {
            this.streetAddressTwo = streetAddressTwo2;
            return this;
        }

        public com.airbnb.android.core.models.AirAddress.Builder city(String city2) {
            this.city = city2;
            return this;
        }

        public com.airbnb.android.core.models.AirAddress.Builder state(String state2) {
            this.state = state2;
            return this;
        }

        public com.airbnb.android.core.models.AirAddress.Builder postalCode(String postalCode2) {
            this.postalCode = postalCode2;
            return this;
        }

        public com.airbnb.android.core.models.AirAddress.Builder country(String country2) {
            this.country = country2;
            return this;
        }

        public com.airbnb.android.core.models.AirAddress.Builder countryCode(String countryCode2) {
            this.countryCode = countryCode2;
            return this;
        }

        public com.airbnb.android.core.models.AirAddress.Builder latitude(Double latitude2) {
            this.latitude = latitude2;
            return this;
        }

        public com.airbnb.android.core.models.AirAddress.Builder longitude(Double longitude2) {
            this.longitude = longitude2;
            return this;
        }

        public AirAddress build() {
            return new AutoValue_AirAddress(this.streetAddressOne, this.streetAddressTwo, this.city, this.state, this.postalCode, this.country, this.countryCode, this.latitude, this.longitude);
        }
    }

    C$AutoValue_AirAddress(String streetAddressOne2, String streetAddressTwo2, String city2, String state2, String postalCode2, String country2, String countryCode2, Double latitude2, Double longitude2) {
        this.streetAddressOne = streetAddressOne2;
        this.streetAddressTwo = streetAddressTwo2;
        this.city = city2;
        this.state = state2;
        this.postalCode = postalCode2;
        this.country = country2;
        this.countryCode = countryCode2;
        this.latitude = latitude2;
        this.longitude = longitude2;
    }

    @JsonProperty("street_address1")
    public String streetAddressOne() {
        return this.streetAddressOne;
    }

    @JsonProperty("street_address2")
    public String streetAddressTwo() {
        return this.streetAddressTwo;
    }

    @JsonProperty("locality")
    public String city() {
        return this.city;
    }

    @JsonProperty("region")
    public String state() {
        return this.state;
    }

    @JsonProperty("postal_code")
    public String postalCode() {
        return this.postalCode;
    }

    @JsonProperty("country")
    public String country() {
        return this.country;
    }

    public String countryCode() {
        return this.countryCode;
    }

    public Double latitude() {
        return this.latitude;
    }

    public Double longitude() {
        return this.longitude;
    }

    public String toString() {
        return "AirAddress{streetAddressOne=" + this.streetAddressOne + ", streetAddressTwo=" + this.streetAddressTwo + ", city=" + this.city + ", state=" + this.state + ", postalCode=" + this.postalCode + ", country=" + this.country + ", countryCode=" + this.countryCode + ", latitude=" + this.latitude + ", longitude=" + this.longitude + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AirAddress)) {
            return false;
        }
        AirAddress that = (AirAddress) o;
        if (this.streetAddressOne != null ? this.streetAddressOne.equals(that.streetAddressOne()) : that.streetAddressOne() == null) {
            if (this.streetAddressTwo != null ? this.streetAddressTwo.equals(that.streetAddressTwo()) : that.streetAddressTwo() == null) {
                if (this.city != null ? this.city.equals(that.city()) : that.city() == null) {
                    if (this.state != null ? this.state.equals(that.state()) : that.state() == null) {
                        if (this.postalCode != null ? this.postalCode.equals(that.postalCode()) : that.postalCode() == null) {
                            if (this.country != null ? this.country.equals(that.country()) : that.country() == null) {
                                if (this.countryCode != null ? this.countryCode.equals(that.countryCode()) : that.countryCode() == null) {
                                    if (this.latitude != null ? this.latitude.equals(that.latitude()) : that.latitude() == null) {
                                        if (this.longitude == null) {
                                            if (that.longitude() == null) {
                                                return true;
                                            }
                                        } else if (this.longitude.equals(that.longitude())) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((1 * 1000003) ^ (this.streetAddressOne == null ? 0 : this.streetAddressOne.hashCode())) * 1000003) ^ (this.streetAddressTwo == null ? 0 : this.streetAddressTwo.hashCode())) * 1000003) ^ (this.city == null ? 0 : this.city.hashCode())) * 1000003) ^ (this.state == null ? 0 : this.state.hashCode())) * 1000003) ^ (this.postalCode == null ? 0 : this.postalCode.hashCode())) * 1000003) ^ (this.country == null ? 0 : this.country.hashCode())) * 1000003) ^ (this.countryCode == null ? 0 : this.countryCode.hashCode())) * 1000003) ^ (this.latitude == null ? 0 : this.latitude.hashCode())) * 1000003;
        if (this.longitude != null) {
            i = this.longitude.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.core.models.AirAddress.Builder toBuilder() {
        return new Builder(this);
    }
}
