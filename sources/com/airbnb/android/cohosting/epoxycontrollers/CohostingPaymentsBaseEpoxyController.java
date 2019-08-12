package com.airbnb.android.cohosting.epoxycontrollers;

import android.os.Bundle;
import com.airbnb.android.cohosting.shared.CohostingPaymentSettings;
import com.airbnb.android.cohosting.shared.CohostingPaymentsSection.Listener;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import icepick.State;

public abstract class CohostingPaymentsBaseEpoxyController extends AirEpoxyController implements Listener {
    @State
    CohostingPaymentSettings cohostingPaymentSettings;

    public abstract void choosePaymentType();

    protected CohostingPaymentsBaseEpoxyController(Bundle savedInstanceState) {
        onRestoreInstanceState(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void initializeCohostingPaymentSettings(String currency) {
        this.cohostingPaymentSettings = CohostingPaymentSettings.builder().setAmountCurrency(currency).build();
    }

    /* access modifiers changed from: protected */
    public void setFeeType(FeeType feeType) {
        this.cohostingPaymentSettings = this.cohostingPaymentSettings.toBuilder().setFeeType(feeType).build();
        requestModelBuild();
    }

    public boolean hasChanged() {
        return this.cohostingPaymentSettings.hasSetPayout();
    }

    public void onShareEarningsToggled(boolean isChecked) {
        this.cohostingPaymentSettings = this.cohostingPaymentSettings.toBuilder().setShareEarnings(isChecked).build();
        requestModelBuild();
    }

    public void changePercent(Integer amount) {
        this.cohostingPaymentSettings = this.cohostingPaymentSettings.toBuilder().setPercentage(amount == null ? 0 : amount.intValue()).build();
        requestModelBuild();
    }

    public void changeMinimumFee(Integer amount) {
        this.cohostingPaymentSettings = this.cohostingPaymentSettings.toBuilder().setMinimumFee(amount == null ? 0 : amount.intValue()).build();
        requestModelBuild();
    }

    public void changeFixedAmount(Integer amount) {
        this.cohostingPaymentSettings = this.cohostingPaymentSettings.toBuilder().setFixedAmount(amount == null ? 0 : amount.intValue()).build();
        requestModelBuild();
    }

    public void enableCleaningFee(boolean isChecked) {
        this.cohostingPaymentSettings = this.cohostingPaymentSettings.toBuilder().setIncludeCleaningFee(isChecked).build();
        requestModelBuild();
    }

    public CohostingPaymentSettings getCohostingPaymentSettings() {
        return this.cohostingPaymentSettings;
    }
}
