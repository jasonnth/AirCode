package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class PostalAddress implements Parcelable {
    public static final String COUNTRY_CODE_ALPHA_2_KEY = "country";
    public static final String COUNTRY_CODE_KEY = "countryCode";
    public static final String COUNTRY_CODE_UNDERSCORE_KEY = "country_code";
    public static final Creator<PostalAddress> CREATOR = new Creator<PostalAddress>() {
        public PostalAddress createFromParcel(Parcel source) {
            return new PostalAddress(source);
        }

        public PostalAddress[] newArray(int size) {
            return new PostalAddress[size];
        }
    };
    public static final String EXTENDED_ADDRESS_KEY = "street2";
    public static final String LINE_1_KEY = "line1";
    public static final String LINE_2_KEY = "line2";
    public static final String LOCALITY_KEY = "city";
    public static final String POSTAL_CODE_KEY = "postalCode";
    public static final String POSTAL_CODE_UNDERSCORE_KEY = "postal_code";
    public static final String RECIPIENT_NAME_KEY = "recipientName";
    public static final String RECIPIENT_NAME_UNDERSCORE_KEY = "recipient_name";
    public static final String REGION_KEY = "state";
    public static final String STREET_ADDRESS_KEY = "street1";
    private String mCountryCodeAlpha2;
    private String mExtendedAddress;
    private String mLocality;
    private String mPostalCode;
    private String mRecipientName;
    private String mRegion;
    private String mStreetAddress;

    public PostalAddress() {
    }

    public static PostalAddress fromJson(JSONObject accountAddress) {
        if (accountAddress == null) {
            return new PostalAddress();
        }
        String streetAddress = Json.optString(accountAddress, STREET_ADDRESS_KEY, null);
        String extendedAddress = Json.optString(accountAddress, EXTENDED_ADDRESS_KEY, null);
        String countryCodeAlpha2 = Json.optString(accountAddress, "country", null);
        if (streetAddress == null) {
            streetAddress = Json.optString(accountAddress, LINE_1_KEY, null);
        }
        if (extendedAddress == null) {
            extendedAddress = Json.optString(accountAddress, LINE_2_KEY, null);
        }
        if (countryCodeAlpha2 == null) {
            countryCodeAlpha2 = Json.optString(accountAddress, COUNTRY_CODE_KEY, null);
        }
        return new PostalAddress().recipientName(Json.optString(accountAddress, RECIPIENT_NAME_KEY, null)).streetAddress(streetAddress).extendedAddress(extendedAddress).locality(Json.optString(accountAddress, "city", null)).region(Json.optString(accountAddress, "state", null)).postalCode(Json.optString(accountAddress, POSTAL_CODE_KEY, null)).countryCodeAlpha2(countryCodeAlpha2);
    }

    public PostalAddress recipientName(String name) {
        this.mRecipientName = name;
        return this;
    }

    public PostalAddress streetAddress(String streetAddress) {
        this.mStreetAddress = streetAddress;
        return this;
    }

    public PostalAddress extendedAddress(String extendedAddress) {
        this.mExtendedAddress = extendedAddress;
        return this;
    }

    public PostalAddress locality(String locality) {
        this.mLocality = locality;
        return this;
    }

    public PostalAddress region(String region) {
        this.mRegion = region;
        return this;
    }

    public PostalAddress postalCode(String postalCode) {
        this.mPostalCode = postalCode;
        return this;
    }

    public PostalAddress countryCodeAlpha2(String countryCodeAlpha2) {
        this.mCountryCodeAlpha2 = countryCodeAlpha2;
        return this;
    }

    public String getRecipientName() {
        return this.mRecipientName;
    }

    public String getStreetAddress() {
        return this.mStreetAddress;
    }

    public String getExtendedAddress() {
        return this.mExtendedAddress;
    }

    public String getLocality() {
        return this.mLocality;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public String getCountryCodeAlpha2() {
        return this.mCountryCodeAlpha2;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.mCountryCodeAlpha2);
    }

    public String toString() {
        return String.format("%s\n%s\n%s\n%s, %s\n%s %s", new Object[]{this.mRecipientName, this.mStreetAddress, this.mExtendedAddress, this.mLocality, this.mRegion, this.mPostalCode, this.mCountryCodeAlpha2});
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mStreetAddress);
        dest.writeString(this.mExtendedAddress);
        dest.writeString(this.mLocality);
        dest.writeString(this.mRegion);
        dest.writeString(this.mPostalCode);
        dest.writeString(this.mCountryCodeAlpha2);
        dest.writeString(this.mRecipientName);
    }

    private PostalAddress(Parcel in) {
        this.mStreetAddress = in.readString();
        this.mExtendedAddress = in.readString();
        this.mLocality = in.readString();
        this.mRegion = in.readString();
        this.mPostalCode = in.readString();
        this.mCountryCodeAlpha2 = in.readString();
        this.mRecipientName = in.readString();
    }
}
