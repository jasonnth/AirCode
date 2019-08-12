package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.fragments.AccountPageFragment.AccountPageItem;

final /* synthetic */ class AccountPageAdapter$$Lambda$6 implements OnClickListener {
    private final AccountPageAdapter arg$1;

    private AccountPageAdapter$$Lambda$6(AccountPageAdapter accountPageAdapter) {
        this.arg$1 = accountPageAdapter;
    }

    public static OnClickListener lambdaFactory$(AccountPageAdapter accountPageAdapter) {
        return new AccountPageAdapter$$Lambda$6(accountPageAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.itemSelected(AccountPageItem.Gifts);
    }
}
