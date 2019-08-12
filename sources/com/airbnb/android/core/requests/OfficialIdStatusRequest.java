package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.android.core.responses.OfficialIdStatusResponse;
import java.lang.reflect.Type;

public class OfficialIdStatusRequest extends BaseRequest<OfficialIdStatusResponse> {

    /* renamed from: id */
    private final String f8489id;

    public OfficialIdStatusRequest(String id, BaseRequestListener<OfficialIdStatusResponse> listener) {
        withListener(listener);
        this.f8489id = id;
    }

    public String getPath() {
        return "official_id/status/" + this.f8489id;
    }

    public Type successResponseType() {
        return OfficialIdStatusResponse.class;
    }
}
