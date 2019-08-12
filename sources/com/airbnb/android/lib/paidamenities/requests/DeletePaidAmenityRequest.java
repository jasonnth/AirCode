package com.airbnb.android.lib.paidamenities.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityResponse;
import java.lang.reflect.Type;

public class DeletePaidAmenityRequest extends BaseRequestV2<PaidAmenityResponse> {
    private final long paidAmenityId;

    public static DeletePaidAmenityRequest forPaidAmenityId(long paidAmenityId2) {
        return new DeletePaidAmenityRequest(paidAmenityId2);
    }

    private DeletePaidAmenityRequest(long paidAmenityId2) {
        this.paidAmenityId = paidAmenityId2;
    }

    public Type successResponseType() {
        return PaidAmenityResponse.class;
    }

    public String getPath() {
        return "paid_amenities/" + this.paidAmenityId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }
}
