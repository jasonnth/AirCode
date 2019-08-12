package com.airbnb.android.lib.paidamenities.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityTypesResponse;
import java.lang.reflect.Type;

public class GetPaidAmenityTypesRequest extends BaseRequestV2<PaidAmenityTypesResponse> {
    public static GetPaidAmenityTypesRequest forDefault() {
        return new GetPaidAmenityTypesRequest();
    }

    public String getPath() {
        return "paid_amenity_types";
    }

    public Type successResponseType() {
        return PaidAmenityTypesResponse.class;
    }
}
