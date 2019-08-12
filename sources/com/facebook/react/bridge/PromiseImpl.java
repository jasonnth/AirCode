package com.facebook.react.bridge;

public class PromiseImpl implements Promise {
    private static final String DEFAULT_ERROR = "EUNSPECIFIED";
    private Callback mReject;
    private Callback mResolve;

    public PromiseImpl(Callback resolve, Callback reject) {
        this.mResolve = resolve;
        this.mReject = reject;
    }

    public void resolve(Object value) {
        if (this.mResolve != null) {
            this.mResolve.invoke(value);
        }
    }

    public void reject(String code, String message) {
        reject(code, message, null);
    }

    @Deprecated
    public void reject(String message) {
        reject(DEFAULT_ERROR, message, null);
    }

    public void reject(String code, Throwable e) {
        reject(code, e.getMessage(), e);
    }

    public void reject(Throwable e) {
        reject(DEFAULT_ERROR, e.getMessage(), e);
    }

    public void reject(String code, String message, Throwable e) {
        if (this.mReject != null) {
            if (code == null) {
                code = DEFAULT_ERROR;
            }
            WritableNativeMap errorInfo = new WritableNativeMap();
            errorInfo.putString("code", code);
            errorInfo.putString("message", message);
            this.mReject.invoke(errorInfo);
        }
    }
}
