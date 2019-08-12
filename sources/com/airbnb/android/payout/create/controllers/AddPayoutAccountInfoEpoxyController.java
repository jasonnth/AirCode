package com.airbnb.android.payout.create.controllers;

import android.content.Context;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.models.PayoutFormField;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.List;

public class AddPayoutAccountInfoEpoxyController extends TypedAirEpoxyController<List<PayoutFormFieldInputWrapper>> {
    private final Context context;
    DocumentMarqueeEpoxyModel_ documentMarqueeModel;
    private final Listener listener;

    public interface Listener {
        void onInputChanged(PayoutFormField payoutFormField, String str);
    }

    public AddPayoutAccountInfoEpoxyController(Context context2, Listener listener2) {
        this.context = context2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<PayoutFormFieldInputWrapper> payoutFormFieldInputWrappers) {
        this.documentMarqueeModel.titleText((CharSequence) this.context.getResources().getString(C7552R.string.add_account_info_marquee_title));
        for (PayoutFormFieldInputWrapper wrapper : payoutFormFieldInputWrappers) {
            PayoutFormField formField = wrapper.payoutFormField();
            new InlineInputRowEpoxyModel_().m4882id((long) formField.hashCode()).title(formField.label()).input(wrapper.inputValue()).hint(formField.hintText()).showError(wrapper.hasValidationError()).error(getValidationError(wrapper)).updateModelData(false).inputValueChangedListener(AddPayoutAccountInfoEpoxyController$$Lambda$1.lambdaFactory$(this, formField)).addTo(this);
        }
    }

    private String getValidationError(PayoutFormFieldInputWrapper wrapper) {
        if (wrapper.hasValidationError()) {
            return wrapper.validationErrorType().getRule().getErrorMessage(this.context, wrapper.payoutFormField());
        }
        return null;
    }
}
