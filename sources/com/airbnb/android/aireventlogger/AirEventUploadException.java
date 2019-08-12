package com.airbnb.android.aireventlogger;

class AirEventUploadException extends Exception {
    public final int status;

    AirEventUploadException(int status2, Throwable e) {
        super(e);
        this.status = status2;
    }

    AirEventUploadException(int status2) {
        this.status = status2;
    }
}
