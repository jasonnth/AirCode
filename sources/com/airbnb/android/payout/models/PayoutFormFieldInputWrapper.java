package com.airbnb.android.payout.models;

import android.os.Parcelable;
import com.airbnb.android.payout.create.PayoutFormRuleType;

public abstract class PayoutFormFieldInputWrapper implements Parcelable {

    public static abstract class Builder {
        public abstract PayoutFormFieldInputWrapper build();

        public abstract Builder hasValidationError(boolean z);

        public abstract Builder inputValue(String str);

        public abstract Builder payoutFormField(PayoutFormField payoutFormField);

        public abstract Builder validationErrorType(PayoutFormRuleType payoutFormRuleType);
    }

    public abstract boolean hasValidationError();

    public abstract String inputValue();

    public abstract PayoutFormField payoutFormField();

    public abstract Builder toBuilder();

    public abstract PayoutFormRuleType validationErrorType();

    public static Builder builder() {
        return new Builder();
    }
}
