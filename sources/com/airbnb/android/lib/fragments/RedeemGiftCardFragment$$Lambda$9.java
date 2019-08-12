package com.airbnb.android.lib.fragments;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.intents.HelpCenterIntents;

final /* synthetic */ class RedeemGiftCardFragment$$Lambda$9 implements OnMenuItemClickListener {
    private final RedeemGiftCardFragment arg$1;

    private RedeemGiftCardFragment$$Lambda$9(RedeemGiftCardFragment redeemGiftCardFragment) {
        this.arg$1 = redeemGiftCardFragment;
    }

    public static OnMenuItemClickListener lambdaFactory$(RedeemGiftCardFragment redeemGiftCardFragment) {
        return new RedeemGiftCardFragment$$Lambda$9(redeemGiftCardFragment);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.arg$1.startActivity(HelpCenterIntents.intentForHelpCenterArticle(this.arg$1.getActivity(), HelpCenterArticle.GIFT_CREDIT).toIntent());
    }
}
