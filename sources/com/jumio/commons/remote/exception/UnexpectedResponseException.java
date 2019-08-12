package com.jumio.commons.remote.exception;

import java.io.IOException;

public class UnexpectedResponseException extends IOException {
    public static final int STATUS_CODE_OK = 200;
    public static final int STATUS_CODE_UNAUTHORIZED = 401;
    private static final long serialVersionUID = 8800114572597147148L;
    private String message = "";
    private int statusCode = 0;

    public UnexpectedResponseException(int statusCode2, String message2) {
        this.statusCode = statusCode2;
        this.message = message2;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public boolean isHttpUnauthorized() {
        return this.statusCode == 401;
    }

    public boolean isHttpOk() {
        return this.statusCode == 200;
    }

    public String getMessage() {
        return this.message;
    }
}
