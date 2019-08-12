package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class UnionPayCapabilities implements Parcelable {
    public static final Creator<UnionPayCapabilities> CREATOR = new Creator<UnionPayCapabilities>() {
        public UnionPayCapabilities createFromParcel(Parcel in) {
            return new UnionPayCapabilities(in);
        }

        public UnionPayCapabilities[] newArray(int size) {
            return new UnionPayCapabilities[size];
        }
    };
    private static final String IS_DEBIT_KEY = "isDebit";
    private static final String IS_SUPPORTED_KEY = "isSupported";
    private static final String IS_UNIONPAY_KEY = "isUnionPay";
    private static final String SUPPORTS_TWO_STEP_AUTH_AND_CAPTURE_KEY = "supportsTwoStepAuthAndCapture";
    private static final String UNIONPAY_KEY = "unionPay";
    private boolean mIsDebit;
    private boolean mIsSupported;
    private boolean mIsUnionPay;
    private boolean mSupportsTwoStepAuthAndCapture;

    public static UnionPayCapabilities fromJson(String jsonString) {
        UnionPayCapabilities unionPayCapabilities = new UnionPayCapabilities();
        try {
            JSONObject json = new JSONObject(jsonString);
            unionPayCapabilities.mIsUnionPay = json.optBoolean(IS_UNIONPAY_KEY);
            unionPayCapabilities.mIsDebit = json.optBoolean(IS_DEBIT_KEY);
            if (json.has(UNIONPAY_KEY)) {
                JSONObject unionPay = json.getJSONObject(UNIONPAY_KEY);
                unionPayCapabilities.mSupportsTwoStepAuthAndCapture = unionPay.optBoolean(SUPPORTS_TWO_STEP_AUTH_AND_CAPTURE_KEY);
                unionPayCapabilities.mIsSupported = unionPay.optBoolean(IS_SUPPORTED_KEY);
            }
        } catch (JSONException e) {
        }
        return unionPayCapabilities;
    }

    public boolean isUnionPay() {
        return this.mIsUnionPay;
    }

    public boolean isDebit() {
        return this.mIsDebit;
    }

    public boolean supportsTwoStepAuthAndCapture() {
        return this.mSupportsTwoStepAuthAndCapture;
    }

    public boolean isSupported() {
        return this.mIsSupported;
    }

    private UnionPayCapabilities() {
    }

    public int describeContents() {
        return 0;
    }

    public UnionPayCapabilities(Parcel in) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (in.readByte() > 0) {
            z = true;
        } else {
            z = false;
        }
        this.mIsUnionPay = z;
        if (in.readByte() > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.mIsDebit = z2;
        if (in.readByte() > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.mSupportsTwoStepAuthAndCapture = z3;
        if (in.readByte() <= 0) {
            z4 = false;
        }
        this.mIsSupported = z4;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2;
        byte b3;
        byte b4 = 1;
        if (this.mIsUnionPay) {
            b = 1;
        } else {
            b = 0;
        }
        dest.writeByte(b);
        if (this.mIsDebit) {
            b2 = 1;
        } else {
            b2 = 0;
        }
        dest.writeByte(b2);
        if (this.mSupportsTwoStepAuthAndCapture) {
            b3 = 1;
        } else {
            b3 = 0;
        }
        dest.writeByte(b3);
        if (!this.mIsSupported) {
            b4 = 0;
        }
        dest.writeByte(b4);
    }
}
