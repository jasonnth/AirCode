package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class SesameCreditVerificationRequest extends BaseRequestV2<SesameCreditVerificationResponse> {
    private final String fullName;
    private final String govID;

    public SesameCreditVerificationRequest(String fullName2, String govID2) {
        this.fullName = fullName2;
        this.govID = govID2;
    }

    public String getPath() {
        return "sesames";
    }

    public Strap getBody() {
        return Strap.make().mo11639kv("name", this.fullName).mo11639kv("nationalId", this.govID);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return SesameCreditVerificationResponse.class;
    }
}
