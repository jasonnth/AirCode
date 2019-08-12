package com.airbnb.android.lib.payments.errors;

import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.responses.QuickPayErrorResponse;
import com.airbnb.android.core.utils.NetworkUtil;

public class QuickPayError {
    static final String ERROR_IDEMPOTENCE_KEY_CONFLICT = "idempotence_key_conflict";
    static final String ERROR_IDEMPOTENCE_KEY_EXPIRED = "idempotence_key_expired";
    static final String ERROR_POSTAL_CODE_MISMATCH = "cc_zip_retry";
    static final String ERROR_TYPE_CURRENCY_MISMATCH = "payment_method_bill_currency_mismatch";
    static final String ERROR_TYPE_CURRENCY_MISMATCH_HOMES = "settlement_currency_change";
    private final AirlockErrorHandler airlockErrorHandler;
    private final NetworkException networkException;

    public enum QuickPayErrorType {
        CURRENCY_MISMATCH,
        IDEMPOTENCE_KEY_EXPIRED,
        IDEMPOTENCE_KEY_CONFLICT,
        POSTAL_CODE_MISMATCH,
        AIRLOCK_ERROR,
        GENERIC_ERROR
    }

    public QuickPayError(NetworkException networkException2, AirlockErrorHandler airlockErrorHandler2) {
        this.networkException = networkException2;
        this.airlockErrorHandler = airlockErrorHandler2;
    }

    public QuickPayErrorType getErrorType() {
        if (isCurrencyMismatchError()) {
            return QuickPayErrorType.CURRENCY_MISMATCH;
        }
        if (isIdempotenceKeyExpired()) {
            return QuickPayErrorType.IDEMPOTENCE_KEY_EXPIRED;
        }
        if (isIdempotenceKeyConflict()) {
            return QuickPayErrorType.IDEMPOTENCE_KEY_CONFLICT;
        }
        if (isPostalCodeMismatch()) {
            return QuickPayErrorType.POSTAL_CODE_MISMATCH;
        }
        if (isAirlockError()) {
            return QuickPayErrorType.AIRLOCK_ERROR;
        }
        return QuickPayErrorType.GENERIC_ERROR;
    }

    public String getError() {
        return ((ErrorResponse) this.networkException.errorResponse()).error;
    }

    public String getErrorMessage() {
        return NetworkUtil.errorMessage(this.networkException);
    }

    public String getSettlementCurrency() {
        return ((QuickPayErrorResponse) this.networkException.errorResponse()).settlementCurrency;
    }

    public NetworkException getNetworkException() {
        return this.networkException;
    }

    public boolean shouldNullOutCvvState() {
        return !isPostalCodeMismatch();
    }

    /* access modifiers changed from: 0000 */
    public boolean isPostalCodeMismatch() {
        ErrorResponse errorResponse = (ErrorResponse) this.networkException.errorResponse();
        return errorResponse != null && ERROR_POSTAL_CODE_MISMATCH.equals(errorResponse.errorDetails);
    }

    /* access modifiers changed from: 0000 */
    public boolean isCurrencyMismatchError() {
        ErrorResponse errorResponse = (ErrorResponse) this.networkException.errorResponse();
        return errorResponse != null && ("payment_method_bill_currency_mismatch".equals(errorResponse.errorDetails) || ERROR_TYPE_CURRENCY_MISMATCH_HOMES.equals(errorResponse.errorDetails));
    }

    /* access modifiers changed from: 0000 */
    public boolean isIdempotenceKeyExpired() {
        ErrorResponse errorResponse = (ErrorResponse) this.networkException.errorResponse();
        return errorResponse != null && ERROR_IDEMPOTENCE_KEY_EXPIRED.equals(errorResponse.errorDetails);
    }

    /* access modifiers changed from: 0000 */
    public boolean isIdempotenceKeyConflict() {
        ErrorResponse errorResponse = (ErrorResponse) this.networkException.errorResponse();
        return errorResponse != null && ERROR_IDEMPOTENCE_KEY_CONFLICT.equals(errorResponse.errorDetails);
    }

    /* access modifiers changed from: 0000 */
    public boolean isAirlockError() {
        return this.airlockErrorHandler.isAirlockError(this.networkException);
    }
}
