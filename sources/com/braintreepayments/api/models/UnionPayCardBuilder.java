package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class UnionPayCardBuilder extends BaseCardBuilder<UnionPayCardBuilder> implements Parcelable {
    public static final Creator<UnionPayCardBuilder> CREATOR = new Creator<UnionPayCardBuilder>() {
        public UnionPayCardBuilder createFromParcel(Parcel in) {
            return new UnionPayCardBuilder(in);
        }

        public UnionPayCardBuilder[] newArray(int size) {
            return new UnionPayCardBuilder[size];
        }
    };
    private static final String ENROLLMENT_ID_KEY = "id";
    private static final String MOBILE_COUNTRY_CODE_KEY = "mobileCountryCode";
    private static final String MOBILE_PHONE_NUMBER_KEY = "mobileNumber";
    private static final String SMS_CODE_KEY = "smsCode";
    private static final String UNIONPAY_ENROLLMENT_KEY = "unionPayEnrollment";
    private static final String UNIONPAY_KEY = "creditCard";
    private String mEnrollmentId;
    private String mMobileCountryCode;
    private String mMobilePhoneNumber;
    private String mSmsCode;

    public UnionPayCardBuilder() {
    }

    public UnionPayCardBuilder mobileCountryCode(String mobileCountryCode) {
        if (TextUtils.isEmpty(mobileCountryCode)) {
            this.mMobileCountryCode = null;
        } else {
            this.mMobileCountryCode = mobileCountryCode;
        }
        return this;
    }

    public UnionPayCardBuilder mobilePhoneNumber(String mobilePhoneNumber) {
        if (TextUtils.isEmpty(mobilePhoneNumber)) {
            this.mMobilePhoneNumber = null;
        } else {
            this.mMobilePhoneNumber = mobilePhoneNumber;
        }
        return this;
    }

    public UnionPayCardBuilder smsCode(String smsCode) {
        if (TextUtils.isEmpty(smsCode)) {
            this.mSmsCode = null;
        } else {
            this.mSmsCode = smsCode;
        }
        return this;
    }

    public UnionPayCardBuilder enrollmentId(String enrollmentId) {
        if (TextUtils.isEmpty(enrollmentId)) {
            this.mEnrollmentId = null;
        } else {
            this.mEnrollmentId = enrollmentId;
        }
        return this;
    }

    @Deprecated
    public UnionPayCardBuilder validate(boolean validate) {
        return this;
    }

    public JSONObject buildEnrollment() throws JSONException {
        JSONObject unionPayEnrollment = new JSONObject();
        unionPayEnrollment.put("number", this.mCardnumber);
        unionPayEnrollment.put("expirationMonth", this.mExpirationMonth);
        unionPayEnrollment.put("expirationYear", this.mExpirationYear);
        unionPayEnrollment.put("expirationDate", this.mExpirationDate);
        unionPayEnrollment.put(MOBILE_COUNTRY_CODE_KEY, this.mMobileCountryCode);
        unionPayEnrollment.put(MOBILE_PHONE_NUMBER_KEY, this.mMobilePhoneNumber);
        JSONObject payload = new JSONObject();
        payload.put(UNIONPAY_ENROLLMENT_KEY, unionPayEnrollment);
        return payload;
    }

    /* access modifiers changed from: protected */
    public void build(JSONObject json, JSONObject paymentMethodNonceJson) throws JSONException {
        super.build(json, paymentMethodNonceJson);
        JSONObject options = paymentMethodNonceJson.optJSONObject("options");
        if (options == null) {
            options = new JSONObject();
            paymentMethodNonceJson.put("options", options);
        }
        JSONObject unionPayEnrollment = new JSONObject();
        unionPayEnrollment.put(SMS_CODE_KEY, this.mSmsCode);
        unionPayEnrollment.put("id", this.mEnrollmentId);
        options.put(UNIONPAY_ENROLLMENT_KEY, unionPayEnrollment);
        json.put(UNIONPAY_KEY, paymentMethodNonceJson);
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mMobileCountryCode);
        dest.writeString(this.mMobilePhoneNumber);
        dest.writeString(this.mSmsCode);
        dest.writeString(this.mEnrollmentId);
    }

    protected UnionPayCardBuilder(Parcel in) {
        super(in);
        this.mMobileCountryCode = in.readString();
        this.mMobilePhoneNumber = in.readString();
        this.mSmsCode = in.readString();
        this.mEnrollmentId = in.readString();
    }
}
