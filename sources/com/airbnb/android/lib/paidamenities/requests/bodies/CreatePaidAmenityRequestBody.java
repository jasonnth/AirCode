package com.airbnb.android.lib.paidamenities.requests.bodies;

import com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public abstract class CreatePaidAmenityRequestBody {
    @JsonUnwrapped
    public abstract PaidAmenityDetails paidAmenityDetails();

    public static CreatePaidAmenityRequestBody create(PaidAmenityDetails paidAmenityDetails) {
        return new AutoValue_CreatePaidAmenityRequestBody(paidAmenityDetails);
    }
}
