package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.SelectRoomDescriptionsResponse;
import java.lang.reflect.Type;

public class GetSelectRoomDescriptionsRequest extends BaseRequestV2<SelectRoomDescriptionsResponse> {
    public String getPath() {
        return "select_room_descriptions";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return SelectRoomDescriptionsResponse.class;
    }
}
