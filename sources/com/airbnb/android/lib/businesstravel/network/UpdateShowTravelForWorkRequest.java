package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class UpdateShowTravelForWorkRequest extends BaseRequestV2<Object> {
    private final String updateKey = "has_seen_travel_for_work";
    private final long userId;

    public static UpdateShowTravelForWorkRequest forUserId(long userId2) {
        return new UpdateShowTravelForWorkRequest(userId2);
    }

    private UpdateShowTravelForWorkRequest(long userId2) {
        this.userId = userId2;
    }

    public Type successResponseType() {
        return Object.class;
    }

    public String getPath() {
        return "users/" + this.userId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return Strap.make().mo11640kv("has_seen_travel_for_work", true);
    }
}
