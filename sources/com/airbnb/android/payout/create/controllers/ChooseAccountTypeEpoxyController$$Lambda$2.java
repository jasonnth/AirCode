package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.payout.models.BankDepositAccountType;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;

final /* synthetic */ class ChooseAccountTypeEpoxyController$$Lambda$2 implements OnCheckedChangeListener {
    private final ChooseAccountTypeEpoxyController arg$1;

    private ChooseAccountTypeEpoxyController$$Lambda$2(ChooseAccountTypeEpoxyController chooseAccountTypeEpoxyController) {
        this.arg$1 = chooseAccountTypeEpoxyController;
    }

    public static OnCheckedChangeListener lambdaFactory$(ChooseAccountTypeEpoxyController chooseAccountTypeEpoxyController) {
        return new ChooseAccountTypeEpoxyController$$Lambda$2(chooseAccountTypeEpoxyController);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z) {
        this.arg$1.updateSelectedAccountType(BankDepositAccountType.Savings);
    }
}
