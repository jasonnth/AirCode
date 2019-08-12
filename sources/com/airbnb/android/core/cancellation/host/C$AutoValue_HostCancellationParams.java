package com.airbnb.android.core.cancellation.host;

/* renamed from: com.airbnb.android.core.cancellation.host.$AutoValue_HostCancellationParams reason: invalid class name */
abstract class C$AutoValue_HostCancellationParams extends HostCancellationParams {
    private final String additionalInfo;
    private final String message;
    private final String reason;
    private final String subReason;

    /* renamed from: com.airbnb.android.core.cancellation.host.$AutoValue_HostCancellationParams$Builder */
    static final class Builder extends com.airbnb.android.core.cancellation.host.HostCancellationParams.Builder {
        private String additionalInfo;
        private String message;
        private String reason;
        private String subReason;

        Builder() {
        }

        private Builder(HostCancellationParams source) {
            this.reason = source.reason();
            this.subReason = source.subReason();
            this.message = source.message();
            this.additionalInfo = source.additionalInfo();
        }

        public com.airbnb.android.core.cancellation.host.HostCancellationParams.Builder reason(String reason2) {
            this.reason = reason2;
            return this;
        }

        public com.airbnb.android.core.cancellation.host.HostCancellationParams.Builder subReason(String subReason2) {
            this.subReason = subReason2;
            return this;
        }

        public com.airbnb.android.core.cancellation.host.HostCancellationParams.Builder message(String message2) {
            this.message = message2;
            return this;
        }

        public com.airbnb.android.core.cancellation.host.HostCancellationParams.Builder additionalInfo(String additionalInfo2) {
            this.additionalInfo = additionalInfo2;
            return this;
        }

        public HostCancellationParams build() {
            return new AutoValue_HostCancellationParams(this.reason, this.subReason, this.message, this.additionalInfo);
        }
    }

    C$AutoValue_HostCancellationParams(String reason2, String subReason2, String message2, String additionalInfo2) {
        this.reason = reason2;
        this.subReason = subReason2;
        this.message = message2;
        this.additionalInfo = additionalInfo2;
    }

    public String reason() {
        return this.reason;
    }

    public String subReason() {
        return this.subReason;
    }

    public String message() {
        return this.message;
    }

    public String additionalInfo() {
        return this.additionalInfo;
    }

    public String toString() {
        return "HostCancellationParams{reason=" + this.reason + ", subReason=" + this.subReason + ", message=" + this.message + ", additionalInfo=" + this.additionalInfo + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HostCancellationParams)) {
            return false;
        }
        HostCancellationParams that = (HostCancellationParams) o;
        if (this.reason != null ? this.reason.equals(that.reason()) : that.reason() == null) {
            if (this.subReason != null ? this.subReason.equals(that.subReason()) : that.subReason() == null) {
                if (this.message != null ? this.message.equals(that.message()) : that.message() == null) {
                    if (this.additionalInfo == null) {
                        if (that.additionalInfo() == null) {
                            return true;
                        }
                    } else if (this.additionalInfo.equals(that.additionalInfo())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((1 * 1000003) ^ (this.reason == null ? 0 : this.reason.hashCode())) * 1000003) ^ (this.subReason == null ? 0 : this.subReason.hashCode())) * 1000003) ^ (this.message == null ? 0 : this.message.hashCode())) * 1000003;
        if (this.additionalInfo != null) {
            i = this.additionalInfo.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.core.cancellation.host.HostCancellationParams.Builder toBuilder() {
        return new Builder(this);
    }
}
