package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class VisaCheckoutUserData implements Parcelable {
    public static final Creator<VisaCheckoutUserData> CREATOR = new Creator<VisaCheckoutUserData>() {
        public VisaCheckoutUserData createFromParcel(Parcel in) {
            return new VisaCheckoutUserData(in);
        }

        public VisaCheckoutUserData[] newArray(int size) {
            return new VisaCheckoutUserData[size];
        }
    };
    private String mUserEmail;
    private String mUserFirstName;
    private String mUserFullName;
    private String mUserLastName;
    private String mUsername;

    public static VisaCheckoutUserData fromJson(JSONObject json) {
        if (json == null) {
            json = new JSONObject();
        }
        VisaCheckoutUserData visaCheckoutUserData = new VisaCheckoutUserData();
        visaCheckoutUserData.mUserFirstName = Json.optString(json, "userFirstName", "");
        visaCheckoutUserData.mUserLastName = Json.optString(json, "userLastName", "");
        visaCheckoutUserData.mUserFullName = Json.optString(json, "userFullName", "");
        visaCheckoutUserData.mUsername = Json.optString(json, "userName", "");
        visaCheckoutUserData.mUserEmail = Json.optString(json, "userEmail", "");
        return visaCheckoutUserData;
    }

    public VisaCheckoutUserData() {
    }

    public String getUserFirstName() {
        return this.mUserFirstName;
    }

    public String getUserLastName() {
        return this.mUserLastName;
    }

    public String getUserFullName() {
        return this.mUserFullName;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public String getUserEmail() {
        return this.mUserEmail;
    }

    public VisaCheckoutUserData(Parcel in) {
        this.mUserFirstName = in.readString();
        this.mUserLastName = in.readString();
        this.mUserFullName = in.readString();
        this.mUsername = in.readString();
        this.mUserEmail = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUserFirstName);
        dest.writeString(this.mUserLastName);
        dest.writeString(this.mUserFullName);
        dest.writeString(this.mUsername);
        dest.writeString(this.mUserEmail);
    }

    public int describeContents() {
        return 0;
    }
}
