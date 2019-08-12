package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class PayPalCreditFinancingAmount implements Parcelable {
    public static final Creator<PayPalCreditFinancingAmount> CREATOR = new Creator<PayPalCreditFinancingAmount>() {
        public PayPalCreditFinancingAmount createFromParcel(Parcel source) {
            return new PayPalCreditFinancingAmount(source);
        }

        public PayPalCreditFinancingAmount[] newArray(int size) {
            return new PayPalCreditFinancingAmount[size];
        }
    };
    private static final String CURRENCY_KEY = "currency";
    private static final String VALUE_KEY = "value";
    private String mCurrency;
    private String mValue;

    private PayPalCreditFinancingAmount() {
    }

    public static PayPalCreditFinancingAmount fromJson(JSONObject amount) {
        PayPalCreditFinancingAmount result = new PayPalCreditFinancingAmount();
        if (amount != null) {
            result.mCurrency = Json.optString(amount, "currency", null);
            result.mValue = Json.optString(amount, "value", null);
        }
        return result;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    public String getValue() {
        return this.mValue;
    }

    public String toString() {
        return String.format("%s %s", new Object[]{this.mValue, this.mCurrency});
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mCurrency);
        dest.writeString(this.mValue);
    }

    private PayPalCreditFinancingAmount(Parcel in) {
        this.mCurrency = in.readString();
        this.mValue = in.readString();
    }
}
