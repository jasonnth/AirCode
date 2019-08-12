package com.airbnb.android.lib.paidamenities.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.lib.paidamenities.enums.PaidAmenityStatus;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class UpdatePaidAmenityRequest extends BaseRequestV2<PaidAmenityResponse> {
    private final long paidAmenityId;
    private final PaidAmenityStatus paidAmenityStatus;

    private static class RequestBody {
        @JsonProperty("status")
        final int statusCode;

        private RequestBody(int statusCode2) {
            this.statusCode = statusCode2;
        }
    }

    public static UpdatePaidAmenityRequest forPaidAmenity(long paidAmenityId2, PaidAmenityStatus paidAmenityStatus2) {
        return new UpdatePaidAmenityRequest(paidAmenityId2, paidAmenityStatus2);
    }

    private UpdatePaidAmenityRequest(long paidAmenityId2, PaidAmenityStatus paidAmenityStatus2) {
        this.paidAmenityId = paidAmenityId2;
        this.paidAmenityStatus = paidAmenityStatus2;
    }

    public Type successResponseType() {
        return PaidAmenityResponse.class;
    }

    public String getPath() {
        return "paid_amenities/" + this.paidAmenityId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public RequestBody getBody() {
        return new RequestBody(this.paidAmenityStatus.getStatusCode());
    }
}
