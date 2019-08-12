package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CohostingPaymentTypeEpoxyController extends AirEpoxyController {
    private final Context context;
    private FeeType feeType;
    ToggleActionRowEpoxyModel_ fixedFeeRow;
    DocumentMarqueeEpoxyModel_ headerRow;
    private final String managerFirstName;
    ToggleActionRowEpoxyModel_ percentageRow;

    public CohostingPaymentTypeEpoxyController(Context context2, FeeType feeType2, String managerFirstName2) {
        this.context = context2;
        this.managerFirstName = managerFirstName2;
        this.feeType = feeType2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        String string;
        String string2;
        boolean z;
        String string3;
        boolean z2 = true;
        DocumentMarqueeEpoxyModel_ documentMarqueeEpoxyModel_ = this.headerRow;
        if (this.managerFirstName == null) {
            string = this.context.getString(C5658R.string.cohosting_payment_type_title);
        } else {
            string = this.context.getString(C5658R.string.cohosting_payment_type_title_for_manager, new Object[]{this.managerFirstName});
        }
        documentMarqueeEpoxyModel_.titleText((CharSequence) string).addTo(this);
        ToggleActionRowEpoxyModel_ titleRes = this.percentageRow.titleRes(C5658R.string.cohosting_payment_type_percentage_title);
        if (this.managerFirstName == null) {
            string2 = this.context.getString(C5658R.string.cohosting_payment_type_percentage_subtitle);
        } else {
            string2 = this.context.getString(C5658R.string.cohosting_payment_type_percentage_subtitle_for_manager, new Object[]{this.managerFirstName});
        }
        ToggleActionRowEpoxyModel_ radio = titleRes.subtitle(string2).radio(true);
        if (this.feeType == FeeType.Percent) {
            z = true;
        } else {
            z = false;
        }
        radio.checked(z).checkedChangedlistener(CohostingPaymentTypeEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        ToggleActionRowEpoxyModel_ titleRes2 = this.fixedFeeRow.titleRes(C5658R.string.cohosting_payment_type_fixed_amount_title);
        if (this.managerFirstName == null) {
            string3 = this.context.getString(C5658R.string.cohosting_payment_type_fixed_amount_subtitle);
        } else {
            string3 = this.context.getString(C5658R.string.cohosting_payment_type_fixed_amount_subtitle_for_manager, new Object[]{this.managerFirstName});
        }
        ToggleActionRowEpoxyModel_ radio2 = titleRes2.subtitle(string3).radio(true);
        if (this.feeType != FeeType.FixedAmount) {
            z2 = false;
        }
        radio2.checked(z2).checkedChangedlistener(CohostingPaymentTypeEpoxyController$$Lambda$2.lambdaFactory$(this)).addTo(this);
    }

    /* access modifiers changed from: private */
    public void setPaymentType(FeeType feeType2) {
        this.feeType = feeType2;
        requestModelBuild();
    }

    public FeeType getFeeType() {
        return this.feeType;
    }
}
