package com.facebook.accountkit.internal;

import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;

final class AccountKitServiceException extends AccountKitException {
    private static final long serialVersionUID = 1;
    private final AccountKitRequestError error;

    public AccountKitServiceException(AccountKitRequestError error2, Type accountKitErrorType, InternalAccountKitError internalError) {
        super(accountKitErrorType, internalError);
        this.error = error2;
    }

    public AccountKitServiceException(AccountKitRequestError error2, AccountKitException ex) {
        super(ex.getError());
        this.error = error2;
    }

    public final AccountKitRequestError getRequestError() {
        return this.error;
    }

    public final String toString() {
        return "{AccountKitServiceException: httpResponseCode: " + this.error.getRequestStatusCode() + ", errorCode: " + this.error.getErrorCode() + ", errorType: " + this.error.getErrorType() + ", message: " + this.error.getErrorMessage() + "}";
    }
}
