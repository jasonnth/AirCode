package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class UpdateCurrencyRequest extends BaseRequestV2<UserResponse> {
    private final String currencyCode;

    public UpdateCurrencyRequest(String currencyCode2) {
        this.currencyCode = currencyCode2;
    }

    public String getPath() {
        return "users/me";
    }

    public Object getBody() {
        return Strap.make().mo11639kv(AirbnbConstants.PREFS_CURRENCY, this.currencyCode);
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
