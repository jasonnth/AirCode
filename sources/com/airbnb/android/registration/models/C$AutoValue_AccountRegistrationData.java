package com.airbnb.android.registration.models;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.AirPhone;

/* renamed from: com.airbnb.android.registration.models.$AutoValue_AccountRegistrationData reason: invalid class name */
abstract class C$AutoValue_AccountRegistrationData extends AccountRegistrationData {
    private final AccountSource accountSource;
    private final AirPhone airPhone;
    private final String authToken;
    private final String birthDateString;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String phone;
    private final String phoneSMSCode;
    private final boolean promoOptIn;

    /* renamed from: com.airbnb.android.registration.models.$AutoValue_AccountRegistrationData$Builder */
    static final class Builder extends com.airbnb.android.registration.models.AccountRegistrationData.Builder {
        private AccountSource accountSource;
        private AirPhone airPhone;
        private String authToken;
        private String birthDateString;
        private String email;
        private String firstName;
        private String lastName;
        private String password;
        private String phone;
        private String phoneSMSCode;
        private Boolean promoOptIn;

        Builder() {
        }

        private Builder(AccountRegistrationData source) {
            this.accountSource = source.accountSource();
            this.email = source.email();
            this.promoOptIn = Boolean.valueOf(source.promoOptIn());
            this.phone = source.phone();
            this.phoneSMSCode = source.phoneSMSCode();
            this.airPhone = source.airPhone();
            this.password = source.password();
            this.firstName = source.firstName();
            this.lastName = source.lastName();
            this.birthDateString = source.birthDateString();
            this.authToken = source.authToken();
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder accountSource(AccountSource accountSource2) {
            this.accountSource = accountSource2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder email(String email2) {
            this.email = email2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder promoOptIn(boolean promoOptIn2) {
            this.promoOptIn = Boolean.valueOf(promoOptIn2);
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder phone(String phone2) {
            this.phone = phone2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder phoneSMSCode(String phoneSMSCode2) {
            this.phoneSMSCode = phoneSMSCode2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder airPhone(AirPhone airPhone2) {
            this.airPhone = airPhone2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder password(String password2) {
            this.password = password2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder firstName(String firstName2) {
            this.firstName = firstName2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder lastName(String lastName2) {
            this.lastName = lastName2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder birthDateString(String birthDateString2) {
            this.birthDateString = birthDateString2;
            return this;
        }

        public com.airbnb.android.registration.models.AccountRegistrationData.Builder authToken(String authToken2) {
            this.authToken = authToken2;
            return this;
        }

        public AccountRegistrationData build() {
            String missing = "";
            if (this.promoOptIn == null) {
                missing = missing + " promoOptIn";
            }
            if (missing.isEmpty()) {
                return new AutoValue_AccountRegistrationData(this.accountSource, this.email, this.promoOptIn.booleanValue(), this.phone, this.phoneSMSCode, this.airPhone, this.password, this.firstName, this.lastName, this.birthDateString, this.authToken);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_AccountRegistrationData(AccountSource accountSource2, String email2, boolean promoOptIn2, String phone2, String phoneSMSCode2, AirPhone airPhone2, String password2, String firstName2, String lastName2, String birthDateString2, String authToken2) {
        this.accountSource = accountSource2;
        this.email = email2;
        this.promoOptIn = promoOptIn2;
        this.phone = phone2;
        this.phoneSMSCode = phoneSMSCode2;
        this.airPhone = airPhone2;
        this.password = password2;
        this.firstName = firstName2;
        this.lastName = lastName2;
        this.birthDateString = birthDateString2;
        this.authToken = authToken2;
    }

    public AccountSource accountSource() {
        return this.accountSource;
    }

    public String email() {
        return this.email;
    }

    public boolean promoOptIn() {
        return this.promoOptIn;
    }

    @Deprecated
    public String phone() {
        return this.phone;
    }

    @Deprecated
    public String phoneSMSCode() {
        return this.phoneSMSCode;
    }

    public AirPhone airPhone() {
        return this.airPhone;
    }

    public String password() {
        return this.password;
    }

    public String firstName() {
        return this.firstName;
    }

    public String lastName() {
        return this.lastName;
    }

    public String birthDateString() {
        return this.birthDateString;
    }

    public String authToken() {
        return this.authToken;
    }

    public String toString() {
        return "AccountRegistrationData{accountSource=" + this.accountSource + ", email=" + this.email + ", promoOptIn=" + this.promoOptIn + ", phone=" + this.phone + ", phoneSMSCode=" + this.phoneSMSCode + ", airPhone=" + this.airPhone + ", password=" + this.password + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", birthDateString=" + this.birthDateString + ", authToken=" + this.authToken + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountRegistrationData)) {
            return false;
        }
        AccountRegistrationData that = (AccountRegistrationData) o;
        if (this.accountSource != null ? this.accountSource.equals(that.accountSource()) : that.accountSource() == null) {
            if (this.email != null ? this.email.equals(that.email()) : that.email() == null) {
                if (this.promoOptIn == that.promoOptIn() && (this.phone != null ? this.phone.equals(that.phone()) : that.phone() == null) && (this.phoneSMSCode != null ? this.phoneSMSCode.equals(that.phoneSMSCode()) : that.phoneSMSCode() == null) && (this.airPhone != null ? this.airPhone.equals(that.airPhone()) : that.airPhone() == null) && (this.password != null ? this.password.equals(that.password()) : that.password() == null) && (this.firstName != null ? this.firstName.equals(that.firstName()) : that.firstName() == null) && (this.lastName != null ? this.lastName.equals(that.lastName()) : that.lastName() == null) && (this.birthDateString != null ? this.birthDateString.equals(that.birthDateString()) : that.birthDateString() == null)) {
                    if (this.authToken == null) {
                        if (that.authToken() == null) {
                            return true;
                        }
                    } else if (this.authToken.equals(that.authToken())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((((1 * 1000003) ^ (this.accountSource == null ? 0 : this.accountSource.hashCode())) * 1000003) ^ (this.email == null ? 0 : this.email.hashCode())) * 1000003) ^ (this.promoOptIn ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE)) * 1000003) ^ (this.phone == null ? 0 : this.phone.hashCode())) * 1000003) ^ (this.phoneSMSCode == null ? 0 : this.phoneSMSCode.hashCode())) * 1000003) ^ (this.airPhone == null ? 0 : this.airPhone.hashCode())) * 1000003) ^ (this.password == null ? 0 : this.password.hashCode())) * 1000003) ^ (this.firstName == null ? 0 : this.firstName.hashCode())) * 1000003) ^ (this.lastName == null ? 0 : this.lastName.hashCode())) * 1000003) ^ (this.birthDateString == null ? 0 : this.birthDateString.hashCode())) * 1000003;
        if (this.authToken != null) {
            i = this.authToken.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.registration.models.AccountRegistrationData.Builder toBuilder() {
        return new Builder(this);
    }
}
