package com.paypal.android.sdk.onetouch.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.paypal.android.sdk.onetouch.core.enums.ResponseType;
import com.paypal.android.sdk.onetouch.core.enums.ResultType;
import org.json.JSONException;
import org.json.JSONObject;

public final class Result implements Parcelable {
    public static final Creator<Result> CREATOR = new Creator<Result>() {
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
    private static final String TAG = Result.class.getSimpleName();
    private final String mEnvironment;
    private final Throwable mError;
    private final JSONObject mResponse;
    private final ResponseType mResponseType;
    private final ResultType mResultType;
    private final String mUserEmail;

    public Result(String environment, ResponseType responseType, JSONObject response, String userEmail) {
        this(ResultType.Success, environment, responseType, response, userEmail, null);
    }

    public Result(Throwable error) {
        this(ResultType.Error, null, null, null, null, error);
    }

    public Result() {
        this(ResultType.Cancel, null, null, null, null, null);
    }

    private Result(ResultType resultType, String environment, ResponseType responseType, JSONObject response, String userEmail, Throwable error) {
        this.mEnvironment = environment;
        this.mResultType = resultType;
        this.mResponseType = responseType;
        this.mResponse = response;
        this.mUserEmail = userEmail;
        this.mError = error;
    }

    public ResultType getResultType() {
        return this.mResultType;
    }

    public JSONObject getResponse() {
        try {
            JSONObject client = new JSONObject();
            client.put("environment", this.mEnvironment);
            JSONObject response = new JSONObject();
            response.put("client", client);
            if (this.mResponse != null) {
                response.put("response", this.mResponse);
            }
            if (this.mResponseType != null) {
                response.put(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, this.mResponseType.name());
            }
            if (this.mUserEmail == null) {
                return response;
            }
            JSONObject user = new JSONObject();
            user.put("display_string", this.mUserEmail);
            response.put("user", user);
            return response;
        } catch (JSONException e) {
            Log.e(TAG, "Error encoding JSON", e);
            return null;
        }
    }

    public Throwable getError() {
        return this.mError;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mEnvironment);
        dest.writeValue(this.mResultType);
        dest.writeValue(this.mResponseType);
        if (this.mResponse != null) {
            dest.writeValue(this.mResponse.toString());
        } else {
            dest.writeValue(null);
        }
        dest.writeValue(this.mUserEmail);
        dest.writeValue(this.mError);
    }

    private Result(Parcel in) {
        this.mEnvironment = (String) in.readValue(null);
        this.mResultType = (ResultType) in.readValue(ResultType.class.getClassLoader());
        this.mResponseType = (ResponseType) in.readValue(ResponseType.class.getClassLoader());
        JSONObject jsonResponse = null;
        try {
            String jsonString = (String) in.readValue(null);
            if (jsonString != null) {
                jsonResponse = new JSONObject(jsonString);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to read parceled JSON for mResponse", e);
        }
        this.mResponse = jsonResponse;
        this.mUserEmail = (String) in.readValue(null);
        this.mError = (Throwable) in.readValue(null);
    }
}
