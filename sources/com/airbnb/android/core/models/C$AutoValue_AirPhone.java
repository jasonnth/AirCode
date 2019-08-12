package com.airbnb.android.core.models;

import com.airbnb.android.core.presenters.CountryCodeItem;

/* renamed from: com.airbnb.android.core.models.$AutoValue_AirPhone reason: invalid class name */
abstract class C$AutoValue_AirPhone extends AirPhone {
    private final CountryCodeItem countryCodeItem;
    private final String formattedPhone;
    private final String phoneDisplayText;
    private final String phoneInputText;
    private final String phoneSMSCode;

    /* renamed from: com.airbnb.android.core.models.$AutoValue_AirPhone$Builder */
    static final class Builder extends com.airbnb.android.core.models.AirPhone.Builder {
        private CountryCodeItem countryCodeItem;
        private String formattedPhone;
        private String phoneDisplayText;
        private String phoneInputText;
        private String phoneSMSCode;

        Builder() {
        }

        private Builder(AirPhone source) {
            this.formattedPhone = source.formattedPhone();
            this.phoneInputText = source.phoneInputText();
            this.phoneDisplayText = source.phoneDisplayText();
            this.phoneSMSCode = source.phoneSMSCode();
            this.countryCodeItem = source.countryCodeItem();
        }

        public com.airbnb.android.core.models.AirPhone.Builder formattedPhone(String formattedPhone2) {
            this.formattedPhone = formattedPhone2;
            return this;
        }

        public com.airbnb.android.core.models.AirPhone.Builder phoneInputText(String phoneInputText2) {
            this.phoneInputText = phoneInputText2;
            return this;
        }

        public com.airbnb.android.core.models.AirPhone.Builder phoneDisplayText(String phoneDisplayText2) {
            this.phoneDisplayText = phoneDisplayText2;
            return this;
        }

        public com.airbnb.android.core.models.AirPhone.Builder phoneSMSCode(String phoneSMSCode2) {
            this.phoneSMSCode = phoneSMSCode2;
            return this;
        }

        public com.airbnb.android.core.models.AirPhone.Builder countryCodeItem(CountryCodeItem countryCodeItem2) {
            this.countryCodeItem = countryCodeItem2;
            return this;
        }

        public AirPhone build() {
            return new AutoValue_AirPhone(this.formattedPhone, this.phoneInputText, this.phoneDisplayText, this.phoneSMSCode, this.countryCodeItem);
        }
    }

    C$AutoValue_AirPhone(String formattedPhone2, String phoneInputText2, String phoneDisplayText2, String phoneSMSCode2, CountryCodeItem countryCodeItem2) {
        this.formattedPhone = formattedPhone2;
        this.phoneInputText = phoneInputText2;
        this.phoneDisplayText = phoneDisplayText2;
        this.phoneSMSCode = phoneSMSCode2;
        this.countryCodeItem = countryCodeItem2;
    }

    public String formattedPhone() {
        return this.formattedPhone;
    }

    public String phoneInputText() {
        return this.phoneInputText;
    }

    public String phoneDisplayText() {
        return this.phoneDisplayText;
    }

    public String phoneSMSCode() {
        return this.phoneSMSCode;
    }

    public CountryCodeItem countryCodeItem() {
        return this.countryCodeItem;
    }

    public String toString() {
        return "AirPhone{formattedPhone=" + this.formattedPhone + ", phoneInputText=" + this.phoneInputText + ", phoneDisplayText=" + this.phoneDisplayText + ", phoneSMSCode=" + this.phoneSMSCode + ", countryCodeItem=" + this.countryCodeItem + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AirPhone)) {
            return false;
        }
        AirPhone that = (AirPhone) o;
        if (this.formattedPhone != null ? this.formattedPhone.equals(that.formattedPhone()) : that.formattedPhone() == null) {
            if (this.phoneInputText != null ? this.phoneInputText.equals(that.phoneInputText()) : that.phoneInputText() == null) {
                if (this.phoneDisplayText != null ? this.phoneDisplayText.equals(that.phoneDisplayText()) : that.phoneDisplayText() == null) {
                    if (this.phoneSMSCode != null ? this.phoneSMSCode.equals(that.phoneSMSCode()) : that.phoneSMSCode() == null) {
                        if (this.countryCodeItem == null) {
                            if (that.countryCodeItem() == null) {
                                return true;
                            }
                        } else if (this.countryCodeItem.equals(that.countryCodeItem())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((1 * 1000003) ^ (this.formattedPhone == null ? 0 : this.formattedPhone.hashCode())) * 1000003) ^ (this.phoneInputText == null ? 0 : this.phoneInputText.hashCode())) * 1000003) ^ (this.phoneDisplayText == null ? 0 : this.phoneDisplayText.hashCode())) * 1000003) ^ (this.phoneSMSCode == null ? 0 : this.phoneSMSCode.hashCode())) * 1000003;
        if (this.countryCodeItem != null) {
            i = this.countryCodeItem.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.core.models.AirPhone.Builder toBuilder() {
        return new Builder(this);
    }
}
