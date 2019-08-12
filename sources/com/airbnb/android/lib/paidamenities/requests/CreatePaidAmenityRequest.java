package com.airbnb.android.lib.paidamenities.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.lib.paidamenities.fragments.purchase.RequestAmenityFragment;
import com.airbnb.android.lib.paidamenities.requests.bodies.CreatePaidAmenityRequestBody;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityResponse;
import java.lang.reflect.Type;

public class CreatePaidAmenityRequest extends BaseRequestV2<PaidAmenityResponse> {
    private final CreatePaidAmenityRequestBody body;

    public static CreatePaidAmenityRequest forBody(CreatePaidAmenityRequestBody body2) {
        return new CreatePaidAmenityRequest(body2);
    }

    private CreatePaidAmenityRequest(CreatePaidAmenityRequestBody body2) {
        this.body = body2;
    }

    public Type successResponseType() {
        return PaidAmenityResponse.class;
    }

    public String getPath() {
        return RequestAmenityFragment.ARG_PAID_AMENITIES;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.body;
    }
}
