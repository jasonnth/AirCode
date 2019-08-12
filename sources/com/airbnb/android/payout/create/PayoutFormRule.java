package com.airbnb.android.payout.create;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.models.PayoutFormField;

public abstract class PayoutFormRule {
    public static PayoutFormRule defaultInstance = new PayoutFormRule() {
        public boolean isValidInput(PayoutFormField formField, String inputValue) {
            return true;
        }
    };

    public static class MaxLengthRule extends PayoutFormRule {
        public static MaxLengthRule instance = new MaxLengthRule();

        public boolean isValidInput(PayoutFormField formField, String inputValue) {
            if (inputValue == null || inputValue.length() <= formField.maxLength().intValue()) {
                return true;
            }
            return false;
        }

        public String getErrorMessage(Context context, PayoutFormField formField) {
            if (formField == null) {
                return null;
            }
            return context.getResources().getString(C7552R.string.add_payout_info_max_length_error, new Object[]{formField.maxLength(), formField.label()});
        }
    }

    public static class MinLengthRule extends PayoutFormRule {
        public static MinLengthRule instance = new MinLengthRule();

        public boolean isValidInput(PayoutFormField formField, String inputValue) {
            if (inputValue == null || inputValue.length() >= formField.minLength().intValue()) {
                return true;
            }
            return false;
        }

        public String getErrorMessage(Context context, PayoutFormField formField) {
            if (formField == null) {
                return null;
            }
            return context.getResources().getString(C7552R.string.add_payout_info_min_length_error, new Object[]{formField.minLength(), formField.label()});
        }
    }

    public static class RegexRule extends PayoutFormRule {
        public static RegexRule instance = new RegexRule();

        public boolean isValidInput(PayoutFormField formField, String inputValue) {
            boolean z = false;
            if (!formField.required()) {
                if (TextUtils.isEmpty(inputValue) || inputValue.matches(formField.regex())) {
                    z = true;
                }
                return z;
            } else if (TextUtils.isEmpty(inputValue) || !inputValue.matches(formField.regex())) {
                return false;
            } else {
                return true;
            }
        }

        public String getErrorMessage(Context context, PayoutFormField formField) {
            if (formField != null) {
                return formField.errorText();
            }
            return null;
        }
    }

    public static class RequireConfirmationRule extends PayoutFormRule {
        public static RequireConfirmationRule instance = new RequireConfirmationRule();

        public boolean isValidInput(PayoutFormField formField, String inputValue) {
            return true;
        }

        public String getErrorMessage(Context context, PayoutFormField formField) {
            if (formField != null) {
                return formField.errorText();
            }
            return null;
        }
    }

    public static class RequiredRule extends PayoutFormRule {
        public static RequiredRule instance = new RequiredRule();

        public boolean isValidInput(PayoutFormField formField, String inputValue) {
            if (!formField.required() || !TextUtils.isEmpty(inputValue)) {
                return true;
            }
            return false;
        }

        public String getErrorMessage(Context context, PayoutFormField formField) {
            return context.getResources().getString(C7552R.string.add_payout_info_field_required_error, new Object[]{formField.label()});
        }
    }

    public abstract boolean isValidInput(PayoutFormField payoutFormField, String str);

    public String getErrorMessage(Context context, PayoutFormField formField) {
        return null;
    }
}
