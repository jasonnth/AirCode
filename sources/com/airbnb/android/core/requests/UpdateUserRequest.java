package com.airbnb.android.core.requests;

import android.util.Log;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;

public class UpdateUserRequest extends BaseRequestV2<UserResponse> {
    private final String requestBody;

    public static UpdateUserRequest forReactivate(BaseRequestListener<UserResponse> requestListener) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("suspended", false);
        } catch (JSONException e) {
            if (BuildHelper.isDevelopmentBuild()) {
                throw new RuntimeException("Error constructing body for " + UpdateUserRequest.class.getSimpleName());
            }
            Log.w(UpdateUserRequest.class.getSimpleName(), "error creating JSON", e);
        }
        UpdateUserRequest request = new UpdateUserRequest(jsonObject.toString());
        request.withListener((Observer) requestListener);
        return request;
    }

    public static UpdateUserRequest forSetDefaultPayout(long paymentInstrumentId) {
        return new UpdateUserRequest(Strap.make().mo11638kv("default_payout_gibraltar_instrument_id", paymentInstrumentId).toJsonString());
    }

    public static UpdateUserRequest forVerifyEmail(String code) {
        return new UpdateUserRequest(Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "for_mobile_confirm_email").mo11640kv("confirm_email", true).mo11639kv("code", code).toJsonString());
    }

    private UpdateUserRequest(String requestBody2) {
        this.requestBody = requestBody2;
    }

    public String getPath() {
        return "users/me";
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return UserResponse.class;
    }
}
