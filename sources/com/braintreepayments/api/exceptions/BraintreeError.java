package com.braintreepayments.api.exceptions;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.braintreepayments.api.Json;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BraintreeError implements Parcelable {
    public static final Creator<BraintreeError> CREATOR = new Creator<BraintreeError>() {
        public BraintreeError createFromParcel(Parcel source) {
            return new BraintreeError(source);
        }

        public BraintreeError[] newArray(int size) {
            return new BraintreeError[size];
        }
    };
    private static final String FIELD_ERRORS_KEY = "fieldErrors";
    private static final String FIELD_KEY = "field";
    private static final String MESSAGE_KEY = "message";
    private String mField;
    private List<BraintreeError> mFieldErrors;
    private String mMessage;

    public static List<BraintreeError> fromJsonArray(JSONArray json) {
        if (json == null) {
            json = new JSONArray();
        }
        List<BraintreeError> errors = new ArrayList<>();
        for (int i = 0; i < json.length(); i++) {
            try {
                errors.add(fromJson(json.getJSONObject(i)));
            } catch (JSONException e) {
            }
        }
        return errors;
    }

    public static BraintreeError fromJson(JSONObject json) {
        BraintreeError error = new BraintreeError();
        error.mField = Json.optString(json, FIELD_KEY, null);
        error.mMessage = Json.optString(json, "message", null);
        error.mFieldErrors = fromJsonArray(json.optJSONArray(FIELD_ERRORS_KEY));
        return error;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getField() {
        return this.mField;
    }

    public List<BraintreeError> getFieldErrors() {
        return this.mFieldErrors;
    }

    public BraintreeError errorFor(String field) {
        if (this.mFieldErrors != null) {
            for (BraintreeError error : this.mFieldErrors) {
                if (error.getField().equals(field)) {
                    return error;
                }
                if (error.getFieldErrors() != null) {
                    BraintreeError returnError = error.errorFor(field);
                    if (returnError != null) {
                        return returnError;
                    }
                }
            }
        }
        return null;
    }

    public String toString() {
        return "BraintreeError for " + this.mField + ": " + this.mMessage + " -> " + (this.mFieldErrors != null ? this.mFieldErrors.toString() : "");
    }

    public BraintreeError() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mField);
        dest.writeString(this.mMessage);
        dest.writeTypedList(this.mFieldErrors);
    }

    protected BraintreeError(Parcel in) {
        this.mField = in.readString();
        this.mMessage = in.readString();
        this.mFieldErrors = in.createTypedArrayList(CREATOR);
    }
}
