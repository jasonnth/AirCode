package com.nytimes.android.external.cache;

public class ExecutionError extends Error {
    public ExecutionError(Error cause) {
        super(cause);
    }
}
