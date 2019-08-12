package com.airbnb.android.lib.paidamenities.requests.bodies;

import com.airbnb.android.lib.paidamenities.models.PaidAmenityDetails;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

final class AutoValue_CreatePaidAmenityRequestBody extends CreatePaidAmenityRequestBody {
    private final PaidAmenityDetails paidAmenityDetails;

    AutoValue_CreatePaidAmenityRequestBody(PaidAmenityDetails paidAmenityDetails2) {
        if (paidAmenityDetails2 == null) {
            throw new NullPointerException("Null paidAmenityDetails");
        }
        this.paidAmenityDetails = paidAmenityDetails2;
    }

    @JsonUnwrapped
    public PaidAmenityDetails paidAmenityDetails() {
        return this.paidAmenityDetails;
    }

    public String toString() {
        return "CreatePaidAmenityRequestBody{paidAmenityDetails=" + this.paidAmenityDetails + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreatePaidAmenityRequestBody)) {
            return false;
        }
        return this.paidAmenityDetails.equals(((CreatePaidAmenityRequestBody) o).paidAmenityDetails());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.paidAmenityDetails.hashCode();
    }
}
