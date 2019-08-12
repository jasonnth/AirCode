package com.airbnb.android.core.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;

public class CreateReservationUserRequest extends BaseRequestV2<Object> {
    private final String confirmationCode;
    private final String email;
    private final boolean includePrice;
    private final String name;
    private final String optionalMessage;

    public CreateReservationUserRequest(String confirmationCode2, String email2) {
        this.confirmationCode = confirmationCode2;
        this.email = email2;
        this.name = null;
        this.includePrice = true;
        this.optionalMessage = null;
    }

    public CreateReservationUserRequest(String name2, String email2, String confirmationCode2, boolean includePrice2, String optionalMessage2, BaseRequestListener<Object> listener) {
        withListener((Observer) listener);
        this.name = name2;
        this.email = email2;
        this.confirmationCode = confirmationCode2;
        this.includePrice = includePrice2;
        this.optionalMessage = optionalMessage2;
    }

    public String getPath() {
        return "reservation_users";
    }

    public String getBody() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", this.email);
            jsonObject.put("confirmation_code", this.confirmationCode);
            jsonObject.put("include_price", this.includePrice);
            jsonObject.put("role", 0);
            if (!TextUtils.isEmpty(this.name)) {
                jsonObject.put("name", this.name);
            }
            if (!TextUtils.isEmpty(this.optionalMessage)) {
                jsonObject.put("custom_message", this.optionalMessage);
            }
            return jsonObject.toString();
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
            return "";
        }
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
