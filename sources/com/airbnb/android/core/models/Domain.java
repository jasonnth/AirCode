package com.airbnb.android.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Domain {
    @JsonProperty("currency")
    public String mCurrency;
    @JsonProperty("domain")
    public String mDomainName;
    @JsonProperty("locale")
    public String mLocale;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Domain)) {
            return false;
        }
        Domain domain = (Domain) o;
        if (this.mCurrency == null ? domain.mCurrency != null : !this.mCurrency.equals(domain.mCurrency)) {
            return false;
        }
        if (this.mDomainName == null ? domain.mDomainName != null : !this.mDomainName.equals(domain.mDomainName)) {
            return false;
        }
        if (this.mLocale != null) {
            if (this.mLocale.equals(domain.mLocale)) {
                return true;
            }
        } else if (domain.mLocale == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result;
        int i;
        int i2 = 0;
        if (this.mDomainName != null) {
            result = this.mDomainName.hashCode();
        } else {
            result = 0;
        }
        int i3 = result * 31;
        if (this.mCurrency != null) {
            i = this.mCurrency.hashCode();
        } else {
            i = 0;
        }
        int i4 = (i3 + i) * 31;
        if (this.mLocale != null) {
            i2 = this.mLocale.hashCode();
        }
        return i4 + i2;
    }
}
