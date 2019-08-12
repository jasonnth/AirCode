package com.airbnb.android.payout;

import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.payout.create.PayoutFormValidator;
import com.airbnb.android.payout.models.PayoutFormField;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PayoutFormManager {
    private List<PayoutFormField> fields;
    @State
    ArrayList<PayoutFormFieldInputWrapper> formInputs;
    private Listener listener;

    public interface Listener {
        void onPayoutFormValidationError();
    }

    public PayoutFormManager(List<PayoutFormField> fields2, Bundle savedInstanceState) {
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        this.fields = fields2;
        this.formInputs = Lists.newArrayList((Iterable<? extends E>) FluentIterable.from((Iterable<E>) fields2).transform(PayoutFormManager$$Lambda$1.lambdaFactory$()));
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public boolean isPayoutFormValid() {
        if (PayoutFormValidator.isPayoutFormValid(this.formInputs)) {
            return true;
        }
        Check.notNull(this.listener);
        this.listener.onPayoutFormValidationError();
        return false;
    }

    public void updateAccountInfo(PayoutFormField field, String value) {
        PayoutFormFieldInputWrapper wrapper = getPayoutFormInputWrapperFromField(field);
        this.formInputs.set(this.formInputs.indexOf(wrapper), wrapper.toBuilder().inputValue(value).build());
    }

    public List<PayoutFormFieldInputWrapper> getPayoutInput() {
        return ImmutableList.copyOf((Collection<? extends E>) this.formInputs);
    }

    public List<PayoutFormField> getPayoutFormFields() {
        return this.fields;
    }

    public boolean hasValidationError(PayoutFormField field) {
        return getPayoutFormInputWrapperFromField(field).hasValidationError();
    }

    public void clearValidationError(PayoutFormField field) {
        PayoutFormFieldInputWrapper wrapper = getPayoutFormInputWrapperFromField(field);
        this.formInputs.set(this.formInputs.indexOf(wrapper), wrapper.toBuilder().hasValidationError(false).validationErrorType(null).build());
    }

    private PayoutFormFieldInputWrapper getPayoutFormInputWrapperFromField(PayoutFormField formField) {
        PayoutFormFieldInputWrapper wrapper = (PayoutFormFieldInputWrapper) FluentIterable.from((Iterable<E>) this.formInputs).firstMatch(PayoutFormManager$$Lambda$2.lambdaFactory$(formField)).orNull();
        if (wrapper == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown payout form field"));
        }
        return wrapper;
    }
}
