package com.airbnb.android.payout.create.controllers;

import android.os.Bundle;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.models.BankDepositAccountType;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class ChooseAccountTypeEpoxyController extends AirEpoxyController {
    ToggleActionRowEpoxyModel_ checkingAccountRowModel;
    DocumentMarqueeEpoxyModel_ documentMarqueeModel;
    private final Listener listener;
    ToggleActionRowEpoxyModel_ savingsAccountRowModel;
    private BankDepositAccountType selectedAccountType;

    public interface Listener {
        void onAccountTypeUpdated(BankDepositAccountType bankDepositAccountType);
    }

    public ChooseAccountTypeEpoxyController(Listener listener2, Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.documentMarqueeModel.titleRes(C7552R.string.choose_bank_deposit_account_type);
        this.checkingAccountRowModel.titleRes(C7552R.string.bank_deposit_account_type_checking).radio(true).checked(BankDepositAccountType.Checking.equals(this.selectedAccountType)).checkedChangedlistener(ChooseAccountTypeEpoxyController$$Lambda$1.lambdaFactory$(this));
        this.savingsAccountRowModel.titleRes(C7552R.string.bank_deposit_account_type_savings).radio(true).checked(BankDepositAccountType.Savings.equals(this.selectedAccountType)).checkedChangedlistener(ChooseAccountTypeEpoxyController$$Lambda$2.lambdaFactory$(this));
    }

    public void setAccountType(BankDepositAccountType bankDepositAccountType) {
        this.selectedAccountType = bankDepositAccountType;
        requestModelBuild();
    }

    /* access modifiers changed from: private */
    public void updateSelectedAccountType(BankDepositAccountType bankDepositAccountType) {
        setAccountType(bankDepositAccountType);
        this.listener.onAccountTypeUpdated(bankDepositAccountType);
    }
}
