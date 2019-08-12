package com.airbnb.android.aireventlogger;

abstract class AirEventUpload {
    final String userAgent;

    static class Factory {
        Factory() {
        }

        static AirEventUpload create(String userAgent) {
            if (Utils.hasOkHttpOnClasspath()) {
                return new OkHttpEventUpload(userAgent);
            }
            return new URLConnectionEventUpload(userAgent);
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void uploadData(PendingEvents pendingEvents) throws AirEventUploadException;

    AirEventUpload(String userAgent2) {
        this.userAgent = userAgent2;
    }
}
