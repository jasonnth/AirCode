package com.airbnb.android.explore.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.utils.ParcelableStringMap;
import java.lang.reflect.Type;
import org.json.JSONObject;

public class ExploreOptInNotificationRequest extends BaseRequestV2<BaseResponse> {
    private final ParcelableStringMap data;

    public ExploreOptInNotificationRequest(ParcelableStringMap data2) {
        this.data = data2;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public String getPath() {
        return "collection_articles";
    }

    public Object getBody() {
        return new JSONObject(this.data);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
