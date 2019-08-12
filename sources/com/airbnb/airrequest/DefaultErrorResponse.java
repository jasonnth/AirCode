package com.airbnb.airrequest;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

public class DefaultErrorResponse {
    public static final int STATUS_CODE_AIRLOCK = 420;
    public static final int STATUS_CODE_UNAUTHORIZED = 403;
    public static final int STATUS_CODE_UNAVAILABLE_REGIONS = 451;
    private final Optional<ErrorResponse> errorResponse;
    private final NetworkException exception;

    public DefaultErrorResponse(NetworkException exception2) {
        this.exception = (NetworkException) Preconditions.checkNotNull(exception2, "exception == null");
        this.errorResponse = Optional.fromNullable(exception2.errorResponse());
    }

    public String errorDetails() {
        return (String) transformToNullable(new Function<ErrorResponse, String>() {
            public String apply(ErrorResponse e) {
                return e.errorDetails;
            }
        });
    }

    public String errorMessage() {
        return (String) transformToNullable(new Function<ErrorResponse, String>() {
            public String apply(ErrorResponse e) {
                return e.errorMessage;
            }
        });
    }

    public Integer errorCode() {
        return (Integer) transformToNullable(new Function<ErrorResponse, Integer>() {
            public Integer apply(ErrorResponse e) {
                return e.errorCode;
            }
        });
    }

    public String errorId() {
        return (String) transformToNullable(new Function<ErrorResponse, String>() {
            public String apply(ErrorResponse e) {
                return e.errorId;
            }
        });
    }

    public String error() {
        return (String) transformToNullable(new Function<ErrorResponse, String>() {
            public String apply(ErrorResponse e) {
                return e.error;
            }
        });
    }

    public boolean isUnavailableRegionsError() {
        return this.exception.statusCode() == 451;
    }

    public boolean isExpiredOauthError() {
        return this.exception.statusCode() == 401 && "authentication_required".equals(error());
    }

    private <T> T transformToNullable(Function<ErrorResponse, T> function) {
        if (this.errorResponse.isPresent()) {
            return function.apply(this.errorResponse.get());
        }
        return null;
    }

    public boolean isUserUnauthorized() {
        return this.exception.statusCode() == 403;
    }

    public boolean isUserAirlocked() {
        return this.exception.statusCode() == 420;
    }
}
