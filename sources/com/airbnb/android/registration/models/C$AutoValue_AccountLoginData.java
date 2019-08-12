package com.airbnb.android.registration.models;

import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.AirPhone;

/* renamed from: com.airbnb.android.registration.models.$AutoValue_AccountLoginData reason: invalid class name */
abstract class C$AutoValue_AccountLoginData extends AccountLoginData {
    private final AccountSource accountSource;
    private final AirPhone airPhone;
    private final String authToken;
    private final String email;
    private final String firstName;
    private final String mowebAccessToken;
    private final String mowebAuthId;
    private final String password;
    private final String phone;
    private final String profilePicture;

    /* renamed from: com.airbnb.android.registration.models.$AutoValue_AccountLoginData$Builder */
    static final class Builder extends com.airbnb.android.registration.models.AccountLoginData.Builder {
        private AccountSource accountSource;
        private AirPhone airPhone;
        private String authToken;
        private String email;
        private String firstName;
        private String mowebAccessToken;
        private String mowebAuthId;
        private String password;
        private String phone;
        private String profilePicture;

        Builder() {
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder accountSource(AccountSource accountSource2) {
            if (accountSource2 == null) {
                throw new NullPointerException("Null accountSource");
            }
            this.accountSource = accountSource2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder phone(String phone2) {
            this.phone = phone2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder airPhone(AirPhone airPhone2) {
            this.airPhone = airPhone2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder email(String email2) {
            this.email = email2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder password(String password2) {
            this.password = password2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder authToken(String authToken2) {
            this.authToken = authToken2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder mowebAuthId(String mowebAuthId2) {
            this.mowebAuthId = mowebAuthId2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder mowebAccessToken(String mowebAccessToken2) {
            this.mowebAccessToken = mowebAccessToken2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder firstName(String firstName2) {
            this.firstName = firstName2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountLoginData.Builder profilePicture(String profilePicture2) {
            this.profilePicture = profilePicture2;
            return this;
        }

        public AccountLoginData build() {
            String missing = "";
            if (this.accountSource == null) {
                missing = missing + " accountSource";
            }
            if (missing.isEmpty()) {
                return new AutoValue_AccountLoginData(this.accountSource, this.phone, this.airPhone, this.email, this.password, this.authToken, this.mowebAuthId, this.mowebAccessToken, this.firstName, this.profilePicture);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_AccountLoginData(AccountSource accountSource2, String phone2, AirPhone airPhone2, String email2, String password2, String authToken2, String mowebAuthId2, String mowebAccessToken2, String firstName2, String profilePicture2) {
        if (accountSource2 == null) {
            throw new NullPointerException("Null accountSource");
        }
        this.accountSource = accountSource2;
        this.phone = phone2;
        this.airPhone = airPhone2;
        this.email = email2;
        this.password = password2;
        this.authToken = authToken2;
        this.mowebAuthId = mowebAuthId2;
        this.mowebAccessToken = mowebAccessToken2;
        this.firstName = firstName2;
        this.profilePicture = profilePicture2;
    }

    public AccountSource accountSource() {
        return this.accountSource;
    }

    @Deprecated
    public String phone() {
        return this.phone;
    }

    public AirPhone airPhone() {
        return this.airPhone;
    }

    public String email() {
        return this.email;
    }

    public String password() {
        return this.password;
    }

    public String authToken() {
        return this.authToken;
    }

    public String mowebAuthId() {
        return this.mowebAuthId;
    }

    public String mowebAccessToken() {
        return this.mowebAccessToken;
    }

    public String firstName() {
        return this.firstName;
    }

    public String profilePicture() {
        return this.profilePicture;
    }

    public String toString() {
        return "AccountLoginData{accountSource=" + this.accountSource + ", phone=" + this.phone + ", airPhone=" + this.airPhone + ", email=" + this.email + ", password=" + this.password + ", authToken=" + this.authToken + ", mowebAuthId=" + this.mowebAuthId + ", mowebAccessToken=" + this.mowebAccessToken + ", firstName=" + this.firstName + ", profilePicture=" + this.profilePicture + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountLoginData)) {
            return false;
        }
        AccountLoginData that = (AccountLoginData) o;
        if (this.accountSource.equals(that.accountSource()) && (this.phone != null ? this.phone.equals(that.phone()) : that.phone() == null) && (this.airPhone != null ? this.airPhone.equals(that.airPhone()) : that.airPhone() == null) && (this.email != null ? this.email.equals(that.email()) : that.email() == null) && (this.password != null ? this.password.equals(that.password()) : that.password() == null) && (this.authToken != null ? this.authToken.equals(that.authToken()) : that.authToken() == null) && (this.mowebAuthId != null ? this.mowebAuthId.equals(that.mowebAuthId()) : that.mowebAuthId() == null) && (this.mowebAccessToken != null ? this.mowebAccessToken.equals(that.mowebAccessToken()) : that.mowebAccessToken() == null) && (this.firstName != null ? this.firstName.equals(that.firstName()) : that.firstName() == null)) {
            if (this.profilePicture == null) {
                if (that.profilePicture() == null) {
                    return true;
                }
            } else if (this.profilePicture.equals(that.profilePicture())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((1 * 1000003) ^ this.accountSource.hashCode()) * 1000003) ^ (this.phone == null ? 0 : this.phone.hashCode())) * 1000003) ^ (this.airPhone == null ? 0 : this.airPhone.hashCode())) * 1000003) ^ (this.email == null ? 0 : this.email.hashCode())) * 1000003) ^ (this.password == null ? 0 : this.password.hashCode())) * 1000003) ^ (this.authToken == null ? 0 : this.authToken.hashCode())) * 1000003) ^ (this.mowebAuthId == null ? 0 : this.mowebAuthId.hashCode())) * 1000003) ^ (this.mowebAccessToken == null ? 0 : this.mowebAccessToken.hashCode())) * 1000003) ^ (this.firstName == null ? 0 : this.firstName.hashCode())) * 1000003;
        if (this.profilePicture != null) {
            i = this.profilePicture.hashCode();
        }
        return h ^ i;
    }
}
