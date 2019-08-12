package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import java.lang.reflect.Type;

public class RemoveWorkEmailRequest extends BaseRequestV2<Object> {
    private final long businessTravelEmployeeId;

    public static RemoveWorkEmailRequest forBTEmployeeId(long businessTravelEmployeeId2) {
        return new RemoveWorkEmailRequest(businessTravelEmployeeId2);
    }

    private RemoveWorkEmailRequest(long businessTravelEmployeeId2) {
        this.businessTravelEmployeeId = businessTravelEmployeeId2;
    }

    public Type successResponseType() {
        return Object.class;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public String getPath() {
        return "business_travel_employees/" + this.businessTravelEmployeeId;
    }
}
