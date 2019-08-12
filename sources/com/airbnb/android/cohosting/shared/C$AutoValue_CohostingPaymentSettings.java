package com.airbnb.android.cohosting.shared;

import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;

/* renamed from: com.airbnb.android.cohosting.shared.$AutoValue_CohostingPaymentSettings reason: invalid class name */
abstract class C$AutoValue_CohostingPaymentSettings extends CohostingPaymentSettings {
    private final String amountCurrency;
    private final FeeType feeType;
    private final int fixedAmount;
    private final boolean includeCleaningFee;
    private final int minimumFee;
    private final int percentage;
    private final boolean shareEarnings;

    /* renamed from: com.airbnb.android.cohosting.shared.$AutoValue_CohostingPaymentSettings$Builder */
    static final class Builder extends com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder {
        private String amountCurrency;
        private FeeType feeType;
        private Integer fixedAmount;
        private Boolean includeCleaningFee;
        private Integer minimumFee;
        private Integer percentage;
        private Boolean shareEarnings;

        Builder() {
        }

        private Builder(CohostingPaymentSettings source) {
            this.shareEarnings = Boolean.valueOf(source.shareEarnings());
            this.feeType = source.feeType();
            this.percentage = Integer.valueOf(source.percentage());
            this.minimumFee = Integer.valueOf(source.minimumFee());
            this.fixedAmount = Integer.valueOf(source.fixedAmount());
            this.amountCurrency = source.amountCurrency();
            this.includeCleaningFee = Boolean.valueOf(source.includeCleaningFee());
        }

        public com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder setShareEarnings(boolean shareEarnings2) {
            this.shareEarnings = Boolean.valueOf(shareEarnings2);
            return this;
        }

        public com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder setFeeType(FeeType feeType2) {
            if (feeType2 == null) {
                throw new NullPointerException("Null feeType");
            }
            this.feeType = feeType2;
            return this;
        }

        public com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder setPercentage(int percentage2) {
            this.percentage = Integer.valueOf(percentage2);
            return this;
        }

        public com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder setMinimumFee(int minimumFee2) {
            this.minimumFee = Integer.valueOf(minimumFee2);
            return this;
        }

        public com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder setFixedAmount(int fixedAmount2) {
            this.fixedAmount = Integer.valueOf(fixedAmount2);
            return this;
        }

        public com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder setAmountCurrency(String amountCurrency2) {
            if (amountCurrency2 == null) {
                throw new NullPointerException("Null amountCurrency");
            }
            this.amountCurrency = amountCurrency2;
            return this;
        }

        public com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder setIncludeCleaningFee(boolean includeCleaningFee2) {
            this.includeCleaningFee = Boolean.valueOf(includeCleaningFee2);
            return this;
        }

        public CohostingPaymentSettings build() {
            String missing = "";
            if (this.shareEarnings == null) {
                missing = missing + " shareEarnings";
            }
            if (this.feeType == null) {
                missing = missing + " feeType";
            }
            if (this.percentage == null) {
                missing = missing + " percentage";
            }
            if (this.minimumFee == null) {
                missing = missing + " minimumFee";
            }
            if (this.fixedAmount == null) {
                missing = missing + " fixedAmount";
            }
            if (this.amountCurrency == null) {
                missing = missing + " amountCurrency";
            }
            if (this.includeCleaningFee == null) {
                missing = missing + " includeCleaningFee";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CohostingPaymentSettings(this.shareEarnings.booleanValue(), this.feeType, this.percentage.intValue(), this.minimumFee.intValue(), this.fixedAmount.intValue(), this.amountCurrency, this.includeCleaningFee.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_CohostingPaymentSettings(boolean shareEarnings2, FeeType feeType2, int percentage2, int minimumFee2, int fixedAmount2, String amountCurrency2, boolean includeCleaningFee2) {
        this.shareEarnings = shareEarnings2;
        if (feeType2 == null) {
            throw new NullPointerException("Null feeType");
        }
        this.feeType = feeType2;
        this.percentage = percentage2;
        this.minimumFee = minimumFee2;
        this.fixedAmount = fixedAmount2;
        if (amountCurrency2 == null) {
            throw new NullPointerException("Null amountCurrency");
        }
        this.amountCurrency = amountCurrency2;
        this.includeCleaningFee = includeCleaningFee2;
    }

    public boolean shareEarnings() {
        return this.shareEarnings;
    }

    public FeeType feeType() {
        return this.feeType;
    }

    public int percentage() {
        return this.percentage;
    }

    public int minimumFee() {
        return this.minimumFee;
    }

    public int fixedAmount() {
        return this.fixedAmount;
    }

    public String amountCurrency() {
        return this.amountCurrency;
    }

    public boolean includeCleaningFee() {
        return this.includeCleaningFee;
    }

    public String toString() {
        return "CohostingPaymentSettings{shareEarnings=" + this.shareEarnings + ", feeType=" + this.feeType + ", percentage=" + this.percentage + ", minimumFee=" + this.minimumFee + ", fixedAmount=" + this.fixedAmount + ", amountCurrency=" + this.amountCurrency + ", includeCleaningFee=" + this.includeCleaningFee + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CohostingPaymentSettings)) {
            return false;
        }
        CohostingPaymentSettings that = (CohostingPaymentSettings) o;
        if (this.shareEarnings == that.shareEarnings() && this.feeType.equals(that.feeType()) && this.percentage == that.percentage() && this.minimumFee == that.minimumFee() && this.fixedAmount == that.fixedAmount() && this.amountCurrency.equals(that.amountCurrency()) && this.includeCleaningFee == that.includeCleaningFee()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int h = ((((((((((((1 * 1000003) ^ (this.shareEarnings ? 1231 : 1237)) * 1000003) ^ this.feeType.hashCode()) * 1000003) ^ this.percentage) * 1000003) ^ this.minimumFee) * 1000003) ^ this.fixedAmount) * 1000003) ^ this.amountCurrency.hashCode()) * 1000003;
        if (!this.includeCleaningFee) {
            i = 1237;
        }
        return h ^ i;
    }

    public com.airbnb.android.cohosting.shared.CohostingPaymentSettings.Builder toBuilder() {
        return new Builder(this);
    }
}
