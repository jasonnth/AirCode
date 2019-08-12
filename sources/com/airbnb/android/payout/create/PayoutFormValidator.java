package com.airbnb.android.payout.create;

import com.airbnb.android.core.utils.Check;
import com.airbnb.android.payout.models.PayoutFormField;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class PayoutFormValidator {
    private List<PayoutFormFieldInputWrapper> formInputs;

    public static boolean isPayoutFormValid(List<PayoutFormFieldInputWrapper> formInputs2) {
        return new PayoutFormValidator(formInputs2).validate();
    }

    private PayoutFormValidator(List<PayoutFormFieldInputWrapper> formInputs2) {
        this.formInputs = formInputs2;
    }

    private boolean validate() {
        boolean isValid = true;
        for (PayoutFormFieldInputWrapper wrapper : this.formInputs) {
            Iterator it = getRules(wrapper.payoutFormField()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                PayoutFormRuleType ruleType = (PayoutFormRuleType) it.next();
                if (!isValidInput(wrapper, ruleType)) {
                    isValid = false;
                    this.formInputs.set(this.formInputs.indexOf(wrapper), wrapper.toBuilder().hasValidationError(true).validationErrorType(ruleType).build());
                    break;
                }
            }
        }
        return isValid;
    }

    private boolean isValidInput(PayoutFormFieldInputWrapper fieldInputWrapper, PayoutFormRuleType ruleType) {
        switch (ruleType) {
            case REQUIRE_CONFIRMATION:
                return hasIdenticalValue(getPreviousField(fieldInputWrapper), fieldInputWrapper);
            default:
                return ruleType.getRule().isValidInput(fieldInputWrapper.payoutFormField(), fieldInputWrapper.inputValue());
        }
    }

    private PayoutFormFieldInputWrapper getPreviousField(PayoutFormFieldInputWrapper field) {
        Check.state(this.formInputs.indexOf(field) > 0);
        return (PayoutFormFieldInputWrapper) this.formInputs.get(this.formInputs.indexOf(field) - 1);
    }

    private boolean hasIdenticalValue(PayoutFormFieldInputWrapper field1, PayoutFormFieldInputWrapper field2) {
        if (field1 == null || field2 == null || !field1.inputValue().equals(field2.inputValue())) {
            return false;
        }
        return true;
    }

    private List<PayoutFormRuleType> getRules(PayoutFormField field) {
        List<PayoutFormRuleType> ruleTypes = Lists.newArrayList();
        if (field.required()) {
            ruleTypes.add(PayoutFormRuleType.REQUIRED);
        }
        if (field.minLength() != null && field.minLength().intValue() > 0) {
            ruleTypes.add(PayoutFormRuleType.MIN_LENGTH);
        }
        if (field.maxLength() != null && field.maxLength().intValue() > 0) {
            ruleTypes.add(PayoutFormRuleType.MAX_LENGTH);
        }
        if (field.regex() != null) {
            ruleTypes.add(PayoutFormRuleType.REGEX);
        }
        if (field.confirmField()) {
            ruleTypes.add(PayoutFormRuleType.REQUIRE_CONFIRMATION);
        }
        return ruleTypes;
    }
}
