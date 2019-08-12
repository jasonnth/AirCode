package com.airbnb.android.core.cancellation;

import android.os.Parcelable;
import com.airbnb.android.core.enums.CancellationReason;

public abstract class CancellationData implements Parcelable {

    public static abstract class Builder {
        public abstract CancellationData build();

        public abstract Builder cancellationReason(CancellationReason cancellationReason);

        public abstract Builder confirmationCode(String str);

        public abstract Builder isHost(boolean z);

        public abstract Builder isPositiveRefund(boolean z);

        public abstract Builder isRetracting(boolean z);

        public abstract Builder message(String str);

        public abstract Builder otherReason(String str);

        public abstract Builder paymentAccountPostfix(String str);

        public abstract Builder paymentProvider(String str);

        public abstract Builder policyKey(String str);

        public abstract Builder refundAmount(String str);
    }

    public abstract CancellationReason cancellationReason();

    public abstract String confirmationCode();

    public abstract boolean isHost();

    public abstract boolean isPositiveRefund();

    public abstract boolean isRetracting();

    public abstract String message();

    public abstract String otherReason();

    public abstract String paymentAccountPostfix();

    public abstract String paymentProvider();

    public abstract String policyKey();

    public abstract String refundAmount();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }
}
