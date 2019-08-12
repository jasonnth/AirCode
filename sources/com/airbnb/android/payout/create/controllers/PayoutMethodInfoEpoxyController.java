package com.airbnb.android.payout.create.controllers;

import android.content.Context;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.payout.create.AddPayoutMethodUtils;
import com.airbnb.android.payout.models.PayoutInfoForm;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class PayoutMethodInfoEpoxyController extends AirEpoxyController {
    private final Context context;
    DocumentMarqueeEpoxyModel_ documentMarqueeModel;
    private PayoutInfoForm payoutInfoForm;
    BasicRowEpoxyModel_ payoutMethodFeeRowModel;
    SimpleTextRowEpoxyModel_ timingAndFeeRowModel;

    public PayoutMethodInfoEpoxyController(Context context2, PayoutInfoForm payoutInfoForm2) {
        this.context = context2;
        this.payoutInfoForm = payoutInfoForm2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.documentMarqueeModel.titleText((CharSequence) this.payoutInfoForm.displayName());
        this.timingAndFeeRowModel.large().text(AddPayoutMethodUtils.getPayoutMethodTimelinessText(this.context, this.payoutInfoForm));
        this.payoutMethodFeeRowModel.showDivider(true).titleText(this.payoutInfoForm.transactionFeeInfo());
    }
}
