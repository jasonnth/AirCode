package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseCardBuilder<T> extends PaymentMethodBuilder<T> implements Parcelable {
    protected static final String BILLING_ADDRESS_KEY = "billingAddress";
    protected static final String CARDHOLDER_NAME_KEY = "cardholderName";
    protected static final String COUNTRY_NAME_KEY = "countryName";
    protected static final String CREDIT_CARD_KEY = "creditCard";
    protected static final String CVV_KEY = "cvv";
    protected static final String EXPIRATION_DATE_KEY = "expirationDate";
    protected static final String EXPIRATION_MONTH_KEY = "expirationMonth";
    protected static final String EXPIRATION_YEAR_KEY = "expirationYear";
    protected static final String FIRST_NAME_KEY = "firstName";
    protected static final String LAST_NAME_KEY = "lastName";
    protected static final String LOCALITY_KEY = "locality";
    protected static final String NUMBER_KEY = "number";
    protected static final String POSTAL_CODE_KEY = "postalCode";
    protected static final String REGION_KEY = "region";
    protected static final String STREET_ADDRESS_KEY = "streetAddress";
    protected String mBillingAddress;
    protected String mCardholderName;
    protected String mCardnumber;
    protected String mCountryName;
    protected String mCvv;
    protected String mExpirationDate;
    protected String mExpirationMonth;
    protected String mExpirationYear;
    protected String mFirstName;
    protected String mLastName;
    protected String mLocality;
    protected String mPostalCode;
    protected String mRegion;
    protected String mStreetAddress;

    public BaseCardBuilder() {
    }

    public T cardNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            this.mCardnumber = null;
        } else {
            this.mCardnumber = number;
        }
        return this;
    }

    public T cvv(String cvv) {
        if (TextUtils.isEmpty(cvv)) {
            this.mCvv = null;
        } else {
            this.mCvv = cvv;
        }
        return this;
    }

    public T expirationMonth(String expirationMonth) {
        if (TextUtils.isEmpty(expirationMonth)) {
            this.mExpirationMonth = null;
        } else {
            this.mExpirationMonth = expirationMonth;
        }
        return this;
    }

    public T expirationYear(String expirationYear) {
        if (TextUtils.isEmpty(expirationYear)) {
            this.mExpirationYear = null;
        } else {
            this.mExpirationYear = expirationYear;
        }
        return this;
    }

    public T expirationDate(String expirationDate) {
        if (TextUtils.isEmpty(expirationDate)) {
            this.mExpirationDate = null;
        } else {
            this.mExpirationDate = expirationDate;
        }
        return this;
    }

    public T cardholderName(String cardholderName) {
        if (TextUtils.isEmpty(cardholderName)) {
            this.mCardholderName = null;
        } else {
            this.mCardholderName = cardholderName;
        }
        return this;
    }

    public T firstName(String firstName) {
        if (TextUtils.isEmpty(firstName)) {
            this.mFirstName = null;
        } else {
            this.mFirstName = firstName;
        }
        return this;
    }

    public T lastName(String lastName) {
        if (TextUtils.isEmpty(lastName)) {
            this.mLastName = null;
        } else {
            this.mLastName = lastName;
        }
        return this;
    }

    public T countryName(String countryName) {
        if (TextUtils.isEmpty(countryName)) {
            this.mCountryName = null;
        } else {
            this.mCountryName = countryName;
        }
        return this;
    }

    public T locality(String locality) {
        if (TextUtils.isEmpty(locality)) {
            this.mLocality = null;
        } else {
            this.mLocality = locality;
        }
        return this;
    }

    public T postalCode(String postalCode) {
        if (TextUtils.isEmpty(postalCode)) {
            this.mPostalCode = null;
        } else {
            this.mPostalCode = postalCode;
        }
        return this;
    }

    public T region(String region) {
        if (TextUtils.isEmpty(region)) {
            this.mRegion = null;
        } else {
            this.mRegion = region;
        }
        return this;
    }

    public T streetAddress(String streetAddress) {
        if (TextUtils.isEmpty(streetAddress)) {
            this.mStreetAddress = null;
        } else {
            this.mStreetAddress = streetAddress;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void build(JSONObject json, JSONObject paymentMethodNonceJson) throws JSONException {
        paymentMethodNonceJson.put(NUMBER_KEY, this.mCardnumber);
        paymentMethodNonceJson.put(CVV_KEY, this.mCvv);
        paymentMethodNonceJson.put(EXPIRATION_MONTH_KEY, this.mExpirationMonth);
        paymentMethodNonceJson.put(EXPIRATION_YEAR_KEY, this.mExpirationYear);
        paymentMethodNonceJson.put(EXPIRATION_DATE_KEY, this.mExpirationDate);
        paymentMethodNonceJson.put(CARDHOLDER_NAME_KEY, this.mCardholderName);
        JSONObject billingAddressJson = new JSONObject();
        billingAddressJson.put(FIRST_NAME_KEY, this.mFirstName);
        billingAddressJson.put(LAST_NAME_KEY, this.mLastName);
        billingAddressJson.put(COUNTRY_NAME_KEY, this.mCountryName);
        billingAddressJson.put(LOCALITY_KEY, this.mLocality);
        billingAddressJson.put("postalCode", this.mPostalCode);
        billingAddressJson.put("region", this.mRegion);
        billingAddressJson.put(STREET_ADDRESS_KEY, this.mStreetAddress);
        if (billingAddressJson.length() > 0) {
            paymentMethodNonceJson.put(BILLING_ADDRESS_KEY, billingAddressJson);
        }
        json.put(CREDIT_CARD_KEY, paymentMethodNonceJson);
    }

    public String getApiPath() {
        return "credit_cards";
    }

    public String getResponsePaymentMethodType() {
        return "CreditCard";
    }

    public int describeContents() {
        return 0;
    }

    protected BaseCardBuilder(Parcel in) {
        super(in);
        this.mCardnumber = in.readString();
        this.mCvv = in.readString();
        this.mExpirationMonth = in.readString();
        this.mExpirationYear = in.readString();
        this.mExpirationDate = in.readString();
        this.mCardholderName = in.readString();
        this.mBillingAddress = in.readString();
        this.mFirstName = in.readString();
        this.mLastName = in.readString();
        this.mCountryName = in.readString();
        this.mLocality = in.readString();
        this.mPostalCode = in.readString();
        this.mRegion = in.readString();
        this.mStreetAddress = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mCardnumber);
        dest.writeString(this.mCvv);
        dest.writeString(this.mExpirationMonth);
        dest.writeString(this.mExpirationYear);
        dest.writeString(this.mExpirationDate);
        dest.writeString(this.mCardholderName);
        dest.writeString(this.mBillingAddress);
        dest.writeString(this.mFirstName);
        dest.writeString(this.mLastName);
        dest.writeString(this.mCountryName);
        dest.writeString(this.mLocality);
        dest.writeString(this.mPostalCode);
        dest.writeString(this.mRegion);
        dest.writeString(this.mStreetAddress);
    }
}
