package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class CallPhoneVerificationRequest extends BaseRequest<Object> {
    private final String phoneNumber;

    public CallPhoneVerificationRequest(String phoneNumber2) {
        this.phoneNumber = phoneNumber2;
    }

    @Deprecated
    public CallPhoneVerificationRequest(String phoneNumber2, BaseRequestListener<Object> listener) {
        withListener(listener);
        this.phoneNumber = phoneNumber2;
    }

    public String getPath() {
        return "account/update";
    }

    public String getBody() {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("phone_number", this.phoneNumber);
            jsonBody.put("phone_number_verification_method", "call");
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
        }
        return jsonBody.toString();
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
