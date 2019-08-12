package com.airbnb.android.core.cancellation.host;

import android.os.Parcelable;

public abstract class HostCancellationParams implements Parcelable {

    public static abstract class Builder {
        public abstract Builder additionalInfo(String str);

        public abstract HostCancellationParams build();

        public abstract Builder message(String str);

        public abstract Builder reason(String str);

        public abstract Builder subReason(String str);
    }

    public abstract String additionalInfo();

    public abstract String message();

    public abstract String reason();

    public abstract String subReason();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }
}
