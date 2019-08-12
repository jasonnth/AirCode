package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountPageAdapter$$Lambda$14 implements OnClickListener {
    private final AccountPageAdapter arg$1;

    private AccountPageAdapter$$Lambda$14(AccountPageAdapter accountPageAdapter) {
        this.arg$1 = accountPageAdapter;
    }

    public static OnClickListener lambdaFactory$(AccountPageAdapter accountPageAdapter) {
        return new AccountPageAdapter$$Lambda$14(accountPageAdapter);
    }

    public void onClick(View view) {
        AccountPageAdapter.lambda$initModels$13(this.arg$1, view);
    }
}
