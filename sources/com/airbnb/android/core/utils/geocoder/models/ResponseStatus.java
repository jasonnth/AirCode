package com.airbnb.android.core.utils.geocoder.models;

import com.airbnb.android.core.responses.OfficialIdStatusResponse;

enum ResponseStatus {
    Ok(OfficialIdStatusResponse.f1090OK),
    ZeroResults("ZERO_RESULTS"),
    OverQueryLimit("OVER_QUERY_LIMIT"),
    RequestDenied("REQUEST_DENIED"),
    InvalidRequest("INVALID_REQUEST"),
    UnknownError("UNKNOWN_ERROR");
    
    private final String errorKey;

    private ResponseStatus(String errorKey2) {
        this.errorKey = errorKey2;
    }

    public static ResponseStatus getCodeFromKey(String errorKey2) {
        ResponseStatus[] values;
        for (ResponseStatus code : values()) {
            if (code.errorKey.equals(errorKey2)) {
                return code;
            }
        }
        return null;
    }
}
