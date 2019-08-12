package com.airbnb.android.payout.models;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.payout.create.PayoutFormRuleType;

/* renamed from: com.airbnb.android.payout.models.$AutoValue_PayoutFormFieldInputWrapper reason: invalid class name */
abstract class C$AutoValue_PayoutFormFieldInputWrapper extends PayoutFormFieldInputWrapper {
    private final boolean hasValidationError;
    private final String inputValue;
    private final PayoutFormField payoutFormField;
    private final PayoutFormRuleType validationErrorType;

    /* renamed from: com.airbnb.android.payout.models.$AutoValue_PayoutFormFieldInputWrapper$Builder */
    static final class Builder extends com.airbnb.android.payout.models.PayoutFormFieldInputWrapper.Builder {
        private Boolean hasValidationError;
        private String inputValue;
        private PayoutFormField payoutFormField;
        private PayoutFormRuleType validationErrorType;

        Builder() {
        }

        private Builder(PayoutFormFieldInputWrapper source) {
            this.payoutFormField = source.payoutFormField();
            this.hasValidationError = Boolean.valueOf(source.hasValidationError());
            this.inputValue = source.inputValue();
            this.validationErrorType = source.validationErrorType();
        }

        public com.airbnb.android.payout.models.PayoutFormFieldInputWrapper.Builder payoutFormField(PayoutFormField payoutFormField2) {
            if (payoutFormField2 == null) {
                throw new NullPointerException("Null payoutFormField");
            }
            this.payoutFormField = payoutFormField2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormFieldInputWrapper.Builder hasValidationError(boolean hasValidationError2) {
            this.hasValidationError = Boolean.valueOf(hasValidationError2);
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormFieldInputWrapper.Builder inputValue(String inputValue2) {
            this.inputValue = inputValue2;
            return this;
        }

        public com.airbnb.android.payout.models.PayoutFormFieldInputWrapper.Builder validationErrorType(PayoutFormRuleType validationErrorType2) {
            this.validationErrorType = validationErrorType2;
            return this;
        }

        public PayoutFormFieldInputWrapper build() {
            String missing = "";
            if (this.payoutFormField == null) {
                missing = missing + " payoutFormField";
            }
            if (this.hasValidationError == null) {
                missing = missing + " hasValidationError";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PayoutFormFieldInputWrapper(this.payoutFormField, this.hasValidationError.booleanValue(), this.inputValue, this.validationErrorType);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_PayoutFormFieldInputWrapper(PayoutFormField payoutFormField2, boolean hasValidationError2, String inputValue2, PayoutFormRuleType validationErrorType2) {
        if (payoutFormField2 == null) {
            throw new NullPointerException("Null payoutFormField");
        }
        this.payoutFormField = payoutFormField2;
        this.hasValidationError = hasValidationError2;
        this.inputValue = inputValue2;
        this.validationErrorType = validationErrorType2;
    }

    public PayoutFormField payoutFormField() {
        return this.payoutFormField;
    }

    public boolean hasValidationError() {
        return this.hasValidationError;
    }

    public String inputValue() {
        return this.inputValue;
    }

    public PayoutFormRuleType validationErrorType() {
        return this.validationErrorType;
    }

    public String toString() {
        return "PayoutFormFieldInputWrapper{payoutFormField=" + this.payoutFormField + ", hasValidationError=" + this.hasValidationError + ", inputValue=" + this.inputValue + ", validationErrorType=" + this.validationErrorType + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PayoutFormFieldInputWrapper)) {
            return false;
        }
        PayoutFormFieldInputWrapper that = (PayoutFormFieldInputWrapper) o;
        if (this.payoutFormField.equals(that.payoutFormField()) && this.hasValidationError == that.hasValidationError() && (this.inputValue != null ? this.inputValue.equals(that.inputValue()) : that.inputValue() == null)) {
            if (this.validationErrorType == null) {
                if (that.validationErrorType() == null) {
                    return true;
                }
            } else if (this.validationErrorType.equals(that.validationErrorType())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((1 * 1000003) ^ this.payoutFormField.hashCode()) * 1000003) ^ (this.hasValidationError ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE)) * 1000003) ^ (this.inputValue == null ? 0 : this.inputValue.hashCode())) * 1000003;
        if (this.validationErrorType != null) {
            i = this.validationErrorType.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.payout.models.PayoutFormFieldInputWrapper.Builder toBuilder() {
        return new Builder(this);
    }
}
