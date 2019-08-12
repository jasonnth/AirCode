package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class UpdateMemoryRequest extends BaseRequestV2<Object> {
    private static final int TYPE_CYA_CHOSE_BOOKING_MODEL = 212;
    private static final int TYPE_USE_IDENTITY_FLOW_FOR_VERIFIED_ID = 346;
    private final Strap params;

    public UpdateMemoryRequest(Strap params2) {
        this.params = params2;
    }

    public static UpdateMemoryRequest forInstantBookingVisibilityChange(long listingId) {
        return new UpdateMemoryRequest(Strap.make().mo11637kv("memory_type", (int) TYPE_CYA_CHOSE_BOOKING_MODEL).mo11638kv("reference_id", listingId));
    }

    public static UpdateMemoryRequest forVerifiedIDReplacement() {
        return new UpdateMemoryRequest(Strap.make().mo11637kv("memory_type", (int) TYPE_USE_IDENTITY_FLOW_FOR_VERIFIED_ID));
    }

    public String getPath() {
        return "memories";
    }

    public Strap getBody() {
        return this.params;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
