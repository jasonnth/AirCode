package com.jumio.sdk.exception;

public class JumioException extends Exception {
    private int detailedErrorCase;
    private JumioErrorCase mErrorCase;

    @Deprecated
    public JumioException(String message) {
        super(message);
    }

    @Deprecated
    public JumioException(Exception e) {
        super(e.getMessage());
        setStackTrace(e.getStackTrace());
    }

    public JumioException(JumioErrorCase errorCase) {
        this.mErrorCase = errorCase;
        this.detailedErrorCase = 0;
    }

    public JumioException(JumioErrorCase errorCase, int detailedErrorCase2) {
        this.mErrorCase = errorCase;
        this.detailedErrorCase = detailedErrorCase2;
    }

    public JumioErrorCase getErrorCase() {
        return this.mErrorCase;
    }

    public int getDetailedErrorCase() {
        return this.detailedErrorCase;
    }
}
