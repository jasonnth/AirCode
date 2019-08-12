package com.airbnb.android.payout.create;

import com.airbnb.android.payout.create.PayoutFormRule.MaxLengthRule;
import com.airbnb.android.payout.create.PayoutFormRule.MinLengthRule;
import com.airbnb.android.payout.create.PayoutFormRule.RegexRule;
import com.airbnb.android.payout.create.PayoutFormRule.RequireConfirmationRule;
import com.airbnb.android.payout.create.PayoutFormRule.RequiredRule;

public enum PayoutFormRuleType {
    MIN_LENGTH,
    MAX_LENGTH,
    REQUIRED,
    REQUIRE_CONFIRMATION,
    REGEX;

    public PayoutFormRule getRule() {
        switch (this) {
            case MAX_LENGTH:
                return MaxLengthRule.instance;
            case MIN_LENGTH:
                return MinLengthRule.instance;
            case REGEX:
                return RegexRule.instance;
            case REQUIRED:
                return RequiredRule.instance;
            case REQUIRE_CONFIRMATION:
                return RequireConfirmationRule.instance;
            default:
                return PayoutFormRule.defaultInstance;
        }
    }
}
