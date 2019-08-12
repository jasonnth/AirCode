package com.facebook.accountkit.internal;

import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;

final class AccountKitRequestError {
    public static final int INVALID_ERROR_CODE = -1;
    public static final int INVALID_HTTP_STATUS_CODE = -1;
    private final int errorCode;
    private final String errorMessage;
    private final String errorType;
    private final AccountKitException exception;
    private final int requestStatusCode;
    private final int subErrorCode;
    private final String userErrorMessage;

    public AccountKitRequestError(int requestStatusCode2, int errorCode2, int subErrorCode2, String errorType2, String errorMessage2, String userErrorMessage2, AccountKitException exception2) {
        this.requestStatusCode = requestStatusCode2;
        this.errorCode = errorCode2;
        this.errorType = errorType2;
        this.errorMessage = errorMessage2;
        this.subErrorCode = subErrorCode2;
        this.userErrorMessage = userErrorMessage2;
        if (exception2 != null) {
            this.exception = new AccountKitServiceException(this, exception2);
        } else {
            this.exception = new AccountKitServiceException(this, Type.SERVER_ERROR, new InternalAccountKitError(errorCode2, errorMessage2));
        }
    }

    public AccountKitRequestError(AccountKitException exception2) {
        this(-1, exception2.getError().getDetailErrorCode(), -1, null, null, null, exception2);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        if (this.errorMessage != null) {
            return this.errorMessage;
        }
        return this.exception.getLocalizedMessage();
    }

    public String getErrorType() {
        return this.errorType;
    }

    public AccountKitException getException() {
        return this.exception;
    }

    public int getRequestStatusCode() {
        return this.requestStatusCode;
    }

    public int getSubErrorCode() {
        return this.subErrorCode;
    }

    public String getUserErrorMessage() {
        return this.userErrorMessage;
    }

    public String toString() {
        return "{HttpStatus: " + this.requestStatusCode + ", errorCode: " + this.errorCode + ", errorType: " + this.errorType + ", errorMessage: " + getErrorMessage() + "}";
    }
}
