package com.airbnb.android.cohosting.shared;

import android.os.Parcelable;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;

public abstract class CohostingPaymentSettings implements Parcelable {

    public static abstract class Builder {
        public abstract CohostingPaymentSettings build();

        public abstract Builder setAmountCurrency(String str);

        public abstract Builder setFeeType(FeeType feeType);

        public abstract Builder setFixedAmount(int i);

        public abstract Builder setIncludeCleaningFee(boolean z);

        public abstract Builder setMinimumFee(int i);

        public abstract Builder setPercentage(int i);

        public abstract Builder setShareEarnings(boolean z);
    }

    public abstract String amountCurrency();

    public abstract FeeType feeType();

    public abstract int fixedAmount();

    public abstract boolean includeCleaningFee();

    public abstract int minimumFee();

    public abstract int percentage();

    public abstract boolean shareEarnings();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder().setShareEarnings(false).setFeeType(FeeType.Percent).setPercentage(0).setMinimumFee(0).setFixedAmount(0).setIncludeCleaningFee(false);
    }

    public int actualPercentage() {
        if (hasSetPercentage()) {
            return percentage();
        }
        return 0;
    }

    public int actualMinimumFee() {
        if (hasSetPercentage()) {
            return minimumFee();
        }
        return 0;
    }

    public int actualFixedAmount() {
        if (hasSetFixedFee()) {
            return fixedAmount();
        }
        return 0;
    }

    public boolean hasSetPayout() {
        return hasSetPercentage() || hasSetFixedFee() || includeCleaningFee();
    }

    private boolean hasSetPercentage() {
        return shareEarnings() && feeType() == FeeType.Percent && percentage() > 0;
    }

    private boolean hasSetFixedFee() {
        return shareEarnings() && feeType() == FeeType.FixedAmount && fixedAmount() > 0;
    }
}
