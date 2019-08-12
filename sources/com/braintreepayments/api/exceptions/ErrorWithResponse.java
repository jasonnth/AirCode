package com.braintreepayments.api.exceptions;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorWithResponse extends Exception implements Parcelable {
    public static final Creator<ErrorWithResponse> CREATOR = new Creator<ErrorWithResponse>() {
        public ErrorWithResponse createFromParcel(Parcel source) {
            return new ErrorWithResponse(source);
        }

        public ErrorWithResponse[] newArray(int size) {
            return new ErrorWithResponse[size];
        }
    };
    private static final String ERROR_KEY = "error";
    private static final String FIELD_ERRORS_KEY = "fieldErrors";
    private static final String MESSAGE_KEY = "message";
    private List<BraintreeError> mFieldErrors;
    private String mMessage;
    private String mOriginalResponse;
    private int mStatusCode;

    public ErrorWithResponse(int statusCode, String jsonString) {
        this.mStatusCode = statusCode;
        this.mOriginalResponse = jsonString;
        try {
            parseJson(jsonString);
        } catch (JSONException e) {
            this.mMessage = "Parsing error response failed";
            this.mFieldErrors = new ArrayList();
        }
    }

    private ErrorWithResponse() {
    }

    public static ErrorWithResponse fromJson(String json) throws JSONException {
        ErrorWithResponse errorWithResponse = new ErrorWithResponse();
        errorWithResponse.mOriginalResponse = json;
        errorWithResponse.parseJson(json);
        return errorWithResponse;
    }

    private void parseJson(String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
        this.mMessage = json.getJSONObject("error").getString("message");
        this.mFieldErrors = BraintreeError.fromJsonArray(json.optJSONArray(FIELD_ERRORS_KEY));
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getErrorResponse() {
        return this.mOriginalResponse;
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
        return "ErrorWithResponse (" + this.mStatusCode + "): " + this.mMessage + "\n" + this.mFieldErrors.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mStatusCode);
        dest.writeString(this.mMessage);
        dest.writeString(this.mOriginalResponse);
        dest.writeTypedList(this.mFieldErrors);
    }

    protected ErrorWithResponse(Parcel in) {
        this.mStatusCode = in.readInt();
        this.mMessage = in.readString();
        this.mOriginalResponse = in.readString();
        this.mFieldErrors = in.createTypedArrayList(BraintreeError.CREATOR);
    }
}
