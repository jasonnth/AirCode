package com.airbnb.android.core.data;

/* renamed from: com.airbnb.android.core.data.$AutoValue_AddressParts reason: invalid class name */
abstract class C$AutoValue_AddressParts extends AddressParts {
    private final String city;
    private final String countryCode;
    private final String state;
    private final String street1;
    private final String street2;
    private final String zipCode;

    /* renamed from: com.airbnb.android.core.data.$AutoValue_AddressParts$Builder */
    static final class Builder extends com.airbnb.android.core.data.AddressParts.Builder {
        private String city;
        private String countryCode;
        private String state;
        private String street1;
        private String street2;
        private String zipCode;

        Builder() {
        }

        private Builder(AddressParts source) {
            this.street1 = source.street1();
            this.street2 = source.street2();
            this.city = source.city();
            this.state = source.state();
            this.zipCode = source.zipCode();
            this.countryCode = source.countryCode();
        }

        public com.airbnb.android.core.data.AddressParts.Builder street1(String street12) {
            if (street12 == null) {
                throw new NullPointerException("Null street1");
            }
            this.street1 = street12;
            return this;
        }

        public com.airbnb.android.core.data.AddressParts.Builder street2(String street22) {
            if (street22 == null) {
                throw new NullPointerException("Null street2");
            }
            this.street2 = street22;
            return this;
        }

        public com.airbnb.android.core.data.AddressParts.Builder city(String city2) {
            if (city2 == null) {
                throw new NullPointerException("Null city");
            }
            this.city = city2;
            return this;
        }

        public com.airbnb.android.core.data.AddressParts.Builder state(String state2) {
            if (state2 == null) {
                throw new NullPointerException("Null state");
            }
            this.state = state2;
            return this;
        }

        public com.airbnb.android.core.data.AddressParts.Builder zipCode(String zipCode2) {
            if (zipCode2 == null) {
                throw new NullPointerException("Null zipCode");
            }
            this.zipCode = zipCode2;
            return this;
        }

        public com.airbnb.android.core.data.AddressParts.Builder countryCode(String countryCode2) {
            if (countryCode2 == null) {
                throw new NullPointerException("Null countryCode");
            }
            this.countryCode = countryCode2;
            return this;
        }

        public AddressParts build() {
            String missing = "";
            if (this.street1 == null) {
                missing = missing + " street1";
            }
            if (this.street2 == null) {
                missing = missing + " street2";
            }
            if (this.city == null) {
                missing = missing + " city";
            }
            if (this.state == null) {
                missing = missing + " state";
            }
            if (this.zipCode == null) {
                missing = missing + " zipCode";
            }
            if (this.countryCode == null) {
                missing = missing + " countryCode";
            }
            if (missing.isEmpty()) {
                return new AutoValue_AddressParts(this.street1, this.street2, this.city, this.state, this.zipCode, this.countryCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_AddressParts(String street12, String street22, String city2, String state2, String zipCode2, String countryCode2) {
        if (street12 == null) {
            throw new NullPointerException("Null street1");
        }
        this.street1 = street12;
        if (street22 == null) {
            throw new NullPointerException("Null street2");
        }
        this.street2 = street22;
        if (city2 == null) {
            throw new NullPointerException("Null city");
        }
        this.city = city2;
        if (state2 == null) {
            throw new NullPointerException("Null state");
        }
        this.state = state2;
        if (zipCode2 == null) {
            throw new NullPointerException("Null zipCode");
        }
        this.zipCode = zipCode2;
        if (countryCode2 == null) {
            throw new NullPointerException("Null countryCode");
        }
        this.countryCode = countryCode2;
    }

    public String street1() {
        return this.street1;
    }

    public String street2() {
        return this.street2;
    }

    public String city() {
        return this.city;
    }

    public String state() {
        return this.state;
    }

    public String zipCode() {
        return this.zipCode;
    }

    public String countryCode() {
        return this.countryCode;
    }

    public String toString() {
        return "AddressParts{street1=" + this.street1 + ", street2=" + this.street2 + ", city=" + this.city + ", state=" + this.state + ", zipCode=" + this.zipCode + ", countryCode=" + this.countryCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddressParts)) {
            return false;
        }
        AddressParts that = (AddressParts) o;
        if (!this.street1.equals(that.street1()) || !this.street2.equals(that.street2()) || !this.city.equals(that.city()) || !this.state.equals(that.state()) || !this.zipCode.equals(that.zipCode()) || !this.countryCode.equals(that.countryCode())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((1 * 1000003) ^ this.street1.hashCode()) * 1000003) ^ this.street2.hashCode()) * 1000003) ^ this.city.hashCode()) * 1000003) ^ this.state.hashCode()) * 1000003) ^ this.zipCode.hashCode()) * 1000003) ^ this.countryCode.hashCode();
    }

    public com.airbnb.android.core.data.AddressParts.Builder toBuilder() {
        return new Builder(this);
    }
}
