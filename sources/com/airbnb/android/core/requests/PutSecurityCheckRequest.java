package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.SecurityCheck;
import com.airbnb.android.core.responses.PutSecurityCheckResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;

public class PutSecurityCheckRequest extends BaseRequestV2<PutSecurityCheckResponse> {
    private final String phoneCode;
    private final long phoneNumberId;

    public PutSecurityCheckRequest(long phoneNumberId2, String phoneCode2, BaseRequestListener<PutSecurityCheckResponse> listener) {
        withListener((Observer) listener);
        this.phoneNumberId = phoneNumberId2;
        this.phoneCode = phoneCode2;
    }

    public String getBody() {
        try {
            JSONObject json = new JSONObject();
            json.put("activity_type", "mobile");
            json.put("strategy", SecurityCheck.STRATEGY_PHONE_VERIFICATION);
            JSONObject data = new JSONObject();
            data.put("phone_number_id", this.phoneNumberId);
            data.put("code", this.phoneCode);
            json.put("data", data);
            return json.toString();
        } catch (JSONException e) {
            return null;
        }
    }

    public String getPath() {
        return "security_checks/" + AirbnbAccountManager.currentUserId();
    }

    public Strap getHeaders() {
        return Strap.make().mix(super.getHeaders()).mo11639kv("X-HTTP-METHOD-OVERRIDE", "PUT");
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return PutSecurityCheckResponse.class;
    }
}
