package com.airbnb.android.payout.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.payout.models.$AutoValue_ExtraPayoutAttribute reason: invalid class name */
abstract class C$AutoValue_ExtraPayoutAttribute extends ExtraPayoutAttribute {
    private final String key;
    private final String value;

    /* renamed from: com.airbnb.android.payout.models.$AutoValue_ExtraPayoutAttribute$Builder */
    static final class Builder extends com.airbnb.android.payout.models.ExtraPayoutAttribute.Builder {
        private String key;
        private String value;

        Builder() {
        }

        public com.airbnb.android.payout.models.ExtraPayoutAttribute.Builder key(String key2) {
            if (key2 == null) {
                throw new NullPointerException("Null key");
            }
            this.key = key2;
            return this;
        }

        public com.airbnb.android.payout.models.ExtraPayoutAttribute.Builder value(String value2) {
            if (value2 == null) {
                throw new NullPointerException("Null value");
            }
            this.value = value2;
            return this;
        }

        public ExtraPayoutAttribute build() {
            String missing = "";
            if (this.key == null) {
                missing = missing + " key";
            }
            if (this.value == null) {
                missing = missing + " value";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ExtraPayoutAttribute(this.key, this.value);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ExtraPayoutAttribute(String key2, String value2) {
        if (key2 == null) {
            throw new NullPointerException("Null key");
        }
        this.key = key2;
        if (value2 == null) {
            throw new NullPointerException("Null value");
        }
        this.value = value2;
    }

    @JsonProperty("key")
    public String key() {
        return this.key;
    }

    @JsonProperty("value")
    public String value() {
        return this.value;
    }

    public String toString() {
        return "ExtraPayoutAttribute{key=" + this.key + ", value=" + this.value + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExtraPayoutAttribute)) {
            return false;
        }
        ExtraPayoutAttribute that = (ExtraPayoutAttribute) o;
        if (!this.key.equals(that.key()) || !this.value.equals(that.value())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((1 * 1000003) ^ this.key.hashCode()) * 1000003) ^ this.value.hashCode();
    }
}
