package com.airbnb.android.core.responses;

import com.airbnb.airrequest.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public class AirBatchErrorResponse extends ErrorResponse {
    @JsonProperty("operations")
    public List<BatchOperation> operations;

    public boolean isValidationError() {
        for (BatchOperation op : this.operations) {
            JsonNode errorType = op.response().get("error_type");
            if (errorType != null && ErrorResponse.ERROR_TYPE_VALIDATION.equals(errorType.asText())) {
                return true;
            }
        }
        return super.isValidationError();
    }

    public String errorDetails() {
        for (BatchOperation op : this.operations) {
            JsonNode errorDetails = op.response().get(ErrorResponse.ERROR_DETAILS);
            if (errorDetails != null) {
                return errorDetails.asText();
            }
        }
        return null;
    }
}
