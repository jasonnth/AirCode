package com.airbnb.android.listyourspacedls.controllers;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.listing.adapters.InputAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import icepick.State;

public class LYSNewHostDiscountController extends AirEpoxyController implements InputAdapter {
    private final Context context;
    ToggleActionRowEpoxyModel_ discountRow;
    DocumentMarqueeEpoxyModel_ header;
    @State
    Boolean isEnabled;
    ToggleActionRowEpoxyModel_ noDiscountRow;
    private final int percentage;
    @State
    boolean radioRowsEnabled = true;

    public LYSNewHostDiscountController(Context context2, Boolean isEnabled2, int percentage2, Bundle savedInstanceState) {
        this.context = context2;
        this.isEnabled = isEnabled2;
        this.percentage = percentage2;
        onRestoreInstanceState(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        boolean z = true;
        this.header.titleRes(C7251R.string.lys_dls_new_host_discount_title).addTo(this);
        this.discountRow.title(this.context.getString(C7251R.string.lys_dls_new_host_discount_give_offer_header, new Object[]{PercentageUtils.localizePercentage(this.percentage)})).subtitleRes(C7251R.string.lys_dls_new_host_discount_give_offer_body).checked(this.isEnabled != null && this.isEnabled.booleanValue()).enabled(this.radioRowsEnabled).checkedChangedlistener(LYSNewHostDiscountController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        ToggleActionRowEpoxyModel_ subtitleRes = this.noDiscountRow.titleRes(C7251R.string.lys_dls_new_host_discount_dont_give_offer_header).subtitleRes(C7251R.string.lys_dls_new_host_discount_dont_give_offer_body);
        if (this.isEnabled == null || this.isEnabled.booleanValue()) {
            z = false;
        }
        subtitleRes.checked(z).checkedChangedlistener(LYSNewHostDiscountController$$Lambda$2.lambdaFactory$(this)).enabled(this.radioRowsEnabled).addTo(this);
    }

    static /* synthetic */ void lambda$buildModels$0(LYSNewHostDiscountController lYSNewHostDiscountController, ToggleActionRow view, boolean checked) {
        lYSNewHostDiscountController.isEnabled = Boolean.valueOf(true);
        lYSNewHostDiscountController.requestModelBuild();
    }

    static /* synthetic */ void lambda$buildModels$1(LYSNewHostDiscountController lYSNewHostDiscountController, ToggleActionRow view, boolean checked) {
        lYSNewHostDiscountController.isEnabled = Boolean.valueOf(false);
        lYSNewHostDiscountController.requestModelBuild();
    }

    public Boolean isEnabled() {
        return this.isEnabled;
    }

    public void setInputEnabled(boolean enabled) {
        this.radioRowsEnabled = enabled;
        requestModelBuild();
    }
}
