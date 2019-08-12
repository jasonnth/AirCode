package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.lib.contentproviders.PlacesSearchSuggestionProvider;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class VisaCheckoutAddress implements Parcelable {
    public static final Creator<VisaCheckoutAddress> CREATOR = new Creator<VisaCheckoutAddress>() {
        public VisaCheckoutAddress createFromParcel(Parcel in) {
            return new VisaCheckoutAddress(in);
        }

        public VisaCheckoutAddress[] newArray(int size) {
            return new VisaCheckoutAddress[size];
        }
    };
    private String mCountryCode;
    private String mExtendedAddress;
    private String mFirstName;
    private String mLastName;
    private String mLocality;
    private String mPhoneNumber;
    private String mPostalCode;
    private String mRegion;
    private String mStreetAddress;

    public static VisaCheckoutAddress fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        VisaCheckoutAddress visaCheckoutAddress = new VisaCheckoutAddress();
        visaCheckoutAddress.mFirstName = Json.optString(json, "firstName", "");
        visaCheckoutAddress.mLastName = Json.optString(json, "lastName", "");
        visaCheckoutAddress.mStreetAddress = Json.optString(json, "streetAddress", "");
        visaCheckoutAddress.mExtendedAddress = Json.optString(json, "extendedAddress", "");
        visaCheckoutAddress.mLocality = Json.optString(json, "locality", "");
        visaCheckoutAddress.mRegion = Json.optString(json, PlacesSearchSuggestionProvider.DISPLAY_REGION, "");
        visaCheckoutAddress.mPostalCode = Json.optString(json, PostalAddress.POSTAL_CODE_KEY, "");
        visaCheckoutAddress.mCountryCode = Json.optString(json, PostalAddress.COUNTRY_CODE_KEY, "");
        visaCheckoutAddress.mPhoneNumber = Json.optString(json, "phoneNumber", "");
        return visaCheckoutAddress;
    }

    public VisaCheckoutAddress() {
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getLastName() {
        return this.mLastName;
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

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public VisaCheckoutAddress(Parcel in) {
        this.mFirstName = in.readString();
        this.mLastName = in.readString();
        this.mStreetAddress = in.readString();
        this.mExtendedAddress = in.readString();
        this.mLocality = in.readString();
        this.mRegion = in.readString();
        this.mPostalCode = in.readString();
        this.mCountryCode = in.readString();
        this.mPhoneNumber = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mFirstName);
        dest.writeString(this.mLastName);
        dest.writeString(this.mStreetAddress);
        dest.writeString(this.mExtendedAddress);
        dest.writeString(this.mLocality);
        dest.writeString(this.mRegion);
        dest.writeString(this.mPostalCode);
        dest.writeString(this.mCountryCode);
        dest.writeString(this.mPhoneNumber);
    }

    public int describeContents() {
        return 0;
    }
}
