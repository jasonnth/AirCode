package com.airbnb.android.payout.manage.controllers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EditPayoutEpoxyController$$Lambda$1 implements OnClickListener {
    private final EditPayoutEpoxyController arg$1;

    private EditPayoutEpoxyController$$Lambda$1(EditPayoutEpoxyController editPayoutEpoxyController) {
        this.arg$1 = editPayoutEpoxyController;
    }

    public static OnClickListener lambdaFactory$(EditPayoutEpoxyController editPayoutEpoxyController) {
        return new EditPayoutEpoxyController$$Lambda$1(editPayoutEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onClickAddNewPayoutMethod();
    }
}
