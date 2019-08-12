package com.airbnb.android.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.core.models.$AutoValue_TripSecondaryGuest reason: invalid class name */
abstract class C$AutoValue_TripSecondaryGuest extends TripSecondaryGuest {
    private final String email;
    private final String firstName;
    private final String lastName;

    /* renamed from: com.airbnb.android.core.models.$AutoValue_TripSecondaryGuest$Builder */
    static final class Builder extends com.airbnb.android.core.models.TripSecondaryGuest.Builder {
        private String email;
        private String firstName;
        private String lastName;

        Builder() {
        }

        public com.airbnb.android.core.models.TripSecondaryGuest.Builder firstName(String firstName2) {
            if (firstName2 == null) {
                throw new NullPointerException("Null firstName");
            }
            this.firstName = firstName2;
            return this;
        }

        public com.airbnb.android.core.models.TripSecondaryGuest.Builder lastName(String lastName2) {
            if (lastName2 == null) {
                throw new NullPointerException("Null lastName");
            }
            this.lastName = lastName2;
            return this;
        }

        public com.airbnb.android.core.models.TripSecondaryGuest.Builder email(String email2) {
            if (email2 == null) {
                throw new NullPointerException("Null email");
            }
            this.email = email2;
            return this;
        }

        public TripSecondaryGuest build() {
            String missing = "";
            if (this.firstName == null) {
                missing = missing + " firstName";
            }
            if (this.lastName == null) {
                missing = missing + " lastName";
            }
            if (this.email == null) {
                missing = missing + " email";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TripSecondaryGuest(this.firstName, this.lastName, this.email);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_TripSecondaryGuest(String firstName2, String lastName2, String email2) {
        if (firstName2 == null) {
            throw new NullPointerException("Null firstName");
        }
        this.firstName = firstName2;
        if (lastName2 == null) {
            throw new NullPointerException("Null lastName");
        }
        this.lastName = lastName2;
        if (email2 == null) {
            throw new NullPointerException("Null email");
        }
        this.email = email2;
    }

    @JsonProperty("first_name")
    public String firstName() {
        return this.firstName;
    }

    @JsonProperty("last_name")
    public String lastName() {
        return this.lastName;
    }

    @JsonProperty("email")
    public String email() {
        return this.email;
    }

    public String toString() {
        return "TripSecondaryGuest{firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TripSecondaryGuest)) {
            return false;
        }
        TripSecondaryGuest that = (TripSecondaryGuest) o;
        if (!this.firstName.equals(that.firstName()) || !this.lastName.equals(that.lastName()) || !this.email.equals(that.email())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.firstName.hashCode()) * 1000003) ^ this.lastName.hashCode()) * 1000003) ^ this.email.hashCode();
    }
}
