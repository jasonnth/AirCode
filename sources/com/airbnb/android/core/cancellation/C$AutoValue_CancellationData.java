package com.airbnb.android.core.cancellation;

import com.airbnb.android.core.enums.CancellationReason;

/* renamed from: com.airbnb.android.core.cancellation.$AutoValue_CancellationData reason: invalid class name */
abstract class C$AutoValue_CancellationData extends CancellationData {
    private final CancellationReason cancellationReason;
    private final String confirmationCode;
    private final boolean isHost;
    private final boolean isPositiveRefund;
    private final boolean isRetracting;
    private final String message;
    private final String otherReason;
    private final String paymentAccountPostfix;
    private final String paymentProvider;
    private final String policyKey;
    private final String refundAmount;

    /* renamed from: com.airbnb.android.core.cancellation.$AutoValue_CancellationData$Builder */
    static final class Builder extends com.airbnb.android.core.cancellation.CancellationData.Builder {
        private CancellationReason cancellationReason;
        private String confirmationCode;
        private Boolean isHost;
        private Boolean isPositiveRefund;
        private Boolean isRetracting;
        private String message;
        private String otherReason;
        private String paymentAccountPostfix;
        private String paymentProvider;
        private String policyKey;
        private String refundAmount;

        Builder() {
        }

        private Builder(CancellationData source) {
            this.confirmationCode = source.confirmationCode();
            this.policyKey = source.policyKey();
            this.isHost = Boolean.valueOf(source.isHost());
            this.isRetracting = Boolean.valueOf(source.isRetracting());
            this.cancellationReason = source.cancellationReason();
            this.otherReason = source.otherReason();
            this.message = source.message();
            this.refundAmount = source.refundAmount();
            this.isPositiveRefund = Boolean.valueOf(source.isPositiveRefund());
            this.paymentProvider = source.paymentProvider();
            this.paymentAccountPostfix = source.paymentAccountPostfix();
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder confirmationCode(String confirmationCode2) {
            this.confirmationCode = confirmationCode2;
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder policyKey(String policyKey2) {
            this.policyKey = policyKey2;
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder isHost(boolean isHost2) {
            this.isHost = Boolean.valueOf(isHost2);
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder isRetracting(boolean isRetracting2) {
            this.isRetracting = Boolean.valueOf(isRetracting2);
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder cancellationReason(CancellationReason cancellationReason2) {
            this.cancellationReason = cancellationReason2;
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder otherReason(String otherReason2) {
            this.otherReason = otherReason2;
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder message(String message2) {
            this.message = message2;
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder refundAmount(String refundAmount2) {
            this.refundAmount = refundAmount2;
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder isPositiveRefund(boolean isPositiveRefund2) {
            this.isPositiveRefund = Boolean.valueOf(isPositiveRefund2);
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder paymentProvider(String paymentProvider2) {
            this.paymentProvider = paymentProvider2;
            return this;
        }

        public com.airbnb.android.core.cancellation.CancellationData.Builder paymentAccountPostfix(String paymentAccountPostfix2) {
            this.paymentAccountPostfix = paymentAccountPostfix2;
            return this;
        }

        public CancellationData build() {
            String missing = "";
            if (this.isHost == null) {
                missing = missing + " isHost";
            }
            if (this.isRetracting == null) {
                missing = missing + " isRetracting";
            }
            if (this.isPositiveRefund == null) {
                missing = missing + " isPositiveRefund";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CancellationData(this.confirmationCode, this.policyKey, this.isHost.booleanValue(), this.isRetracting.booleanValue(), this.cancellationReason, this.otherReason, this.message, this.refundAmount, this.isPositiveRefund.booleanValue(), this.paymentProvider, this.paymentAccountPostfix);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_CancellationData(String confirmationCode2, String policyKey2, boolean isHost2, boolean isRetracting2, CancellationReason cancellationReason2, String otherReason2, String message2, String refundAmount2, boolean isPositiveRefund2, String paymentProvider2, String paymentAccountPostfix2) {
        this.confirmationCode = confirmationCode2;
        this.policyKey = policyKey2;
        this.isHost = isHost2;
        this.isRetracting = isRetracting2;
        this.cancellationReason = cancellationReason2;
        this.otherReason = otherReason2;
        this.message = message2;
        this.refundAmount = refundAmount2;
        this.isPositiveRefund = isPositiveRefund2;
        this.paymentProvider = paymentProvider2;
        this.paymentAccountPostfix = paymentAccountPostfix2;
    }

    public String confirmationCode() {
        return this.confirmationCode;
    }

    public String policyKey() {
        return this.policyKey;
    }

    public boolean isHost() {
        return this.isHost;
    }

    public boolean isRetracting() {
        return this.isRetracting;
    }

    public CancellationReason cancellationReason() {
        return this.cancellationReason;
    }

    public String otherReason() {
        return this.otherReason;
    }

    public String message() {
        return this.message;
    }

    public String refundAmount() {
        return this.refundAmount;
    }

    public boolean isPositiveRefund() {
        return this.isPositiveRefund;
    }

    public String paymentProvider() {
        return this.paymentProvider;
    }

    public String paymentAccountPostfix() {
        return this.paymentAccountPostfix;
    }

    public String toString() {
        return "CancellationData{confirmationCode=" + this.confirmationCode + ", policyKey=" + this.policyKey + ", isHost=" + this.isHost + ", isRetracting=" + this.isRetracting + ", cancellationReason=" + this.cancellationReason + ", otherReason=" + this.otherReason + ", message=" + this.message + ", refundAmount=" + this.refundAmount + ", isPositiveRefund=" + this.isPositiveRefund + ", paymentProvider=" + this.paymentProvider + ", paymentAccountPostfix=" + this.paymentAccountPostfix + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CancellationData)) {
            return false;
        }
        CancellationData that = (CancellationData) o;
        if (this.confirmationCode != null ? this.confirmationCode.equals(that.confirmationCode()) : that.confirmationCode() == null) {
            if (this.policyKey != null ? this.policyKey.equals(that.policyKey()) : that.policyKey() == null) {
                if (this.isHost == that.isHost() && this.isRetracting == that.isRetracting() && (this.cancellationReason != null ? this.cancellationReason.equals(that.cancellationReason()) : that.cancellationReason() == null) && (this.otherReason != null ? this.otherReason.equals(that.otherReason()) : that.otherReason() == null) && (this.message != null ? this.message.equals(that.message()) : that.message() == null) && (this.refundAmount != null ? this.refundAmount.equals(that.refundAmount()) : that.refundAmount() == null) && this.isPositiveRefund == that.isPositiveRefund() && (this.paymentProvider != null ? this.paymentProvider.equals(that.paymentProvider()) : that.paymentProvider() == null)) {
                    if (this.paymentAccountPostfix == null) {
                        if (that.paymentAccountPostfix() == null) {
                            return true;
                        }
                    } else if (this.paymentAccountPostfix.equals(that.paymentAccountPostfix())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3 = 1231;
        int i4 = 0;
        int h = ((((1 * 1000003) ^ (this.confirmationCode == null ? 0 : this.confirmationCode.hashCode())) * 1000003) ^ (this.policyKey == null ? 0 : this.policyKey.hashCode())) * 1000003;
        if (this.isHost) {
            i = 1231;
        } else {
            i = 1237;
        }
        int h2 = (h ^ i) * 1000003;
        if (this.isRetracting) {
            i2 = 1231;
        } else {
            i2 = 1237;
        }
        int h3 = (((((((((h2 ^ i2) * 1000003) ^ (this.cancellationReason == null ? 0 : this.cancellationReason.hashCode())) * 1000003) ^ (this.otherReason == null ? 0 : this.otherReason.hashCode())) * 1000003) ^ (this.message == null ? 0 : this.message.hashCode())) * 1000003) ^ (this.refundAmount == null ? 0 : this.refundAmount.hashCode())) * 1000003;
        if (!this.isPositiveRefund) {
            i3 = 1237;
        }
        int h4 = (((h3 ^ i3) * 1000003) ^ (this.paymentProvider == null ? 0 : this.paymentProvider.hashCode())) * 1000003;
        if (this.paymentAccountPostfix != null) {
            i4 = this.paymentAccountPostfix.hashCode();
        }
        return h4 ^ i4;
    }

    public com.airbnb.android.core.cancellation.CancellationData.Builder toBuilder() {
        return new Builder(this);
    }
}
