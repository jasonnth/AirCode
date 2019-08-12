package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.SecurityCheck;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class PostSecurityCheckRequest extends BaseRequestV2<Object> {
    private final long phoneNumberId;
    private final int verificationType;

    public PostSecurityCheckRequest(long phoneNumberId2, int verificationType2) {
        this.phoneNumberId = phoneNumberId2;
        this.verificationType = verificationType2;
    }

    public String getPath() {
        return "security_checks";
    }

    public String getBody() {
        try {
            JSONObject json = new JSONObject();
            json.put("activity_type", "mobile");
            json.put("strategy", SecurityCheck.STRATEGY_PHONE_VERIFICATION);
            JSONObject data = new JSONObject();
            data.put("phone_number_id", this.phoneNumberId);
            data.put("verification_method", this.verificationType);
            json.put("data", data);
            return json.toString();
        } catch (JSONException e) {
            return null;
        }
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
