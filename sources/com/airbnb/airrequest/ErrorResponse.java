package com.airbnb.airrequest;

import android.support.annotation.Keep;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

@Keep
public class ErrorResponse {
    public static final String ERROR = "error";
    public static final String ERROR_CODE = "error_code";
    public static final String ERROR_DETAILS = "error_details";
    public static final String ERROR_ID = "error_id";
    public static final String ERROR_MESSAGE = "error_message";
    public static final String ERROR_TYPE = "error_type";
    private static final String ERROR_TYPE_CLIENT_ERROR_INFO = "client_error_info";
    public static final String ERROR_TYPE_VALIDATION = "validation";
    @JsonProperty("client_error_info")
    public Object clientErrorResponse;
    @JsonProperty("error")
    public String error;
    @JsonProperty("error_code")
    public Integer errorCode;
    @JsonProperty("error_details")
    public String errorDetails;
    @JsonProperty("error_id")
    public String errorId;
    @JsonProperty("error_message")
    public String errorMessage;
    @JsonProperty("error_type")
    public String errorType;

    public Map<String, String> toMap() {
        return ImmutableMap.builder().put("error", nonNull(this.error)).put("error_code", String.valueOf(this.errorCode)).put(ERROR_DETAILS, nonNull(this.errorDetails)).put(ERROR_ID, nonNull(this.errorId)).put("error_message", nonNull(this.errorMessage)).put("error_type", nonNull(this.errorType)).build();
    }

    private String nonNull(String val) {
        return (String) Optional.fromNullable(val).mo41059or("");
    }

    public boolean isValidationError() {
        return ERROR_TYPE_VALIDATION.equals(this.errorType);
    }

    public String errorDetails() {
        if (this.errorDetails != null) {
            return this.errorDetails;
        }
        return this.errorMessage;
    }
}
