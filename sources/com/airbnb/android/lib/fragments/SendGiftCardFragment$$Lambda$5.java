package com.airbnb.android.lib.fragments;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.intents.HelpCenterIntents;

final /* synthetic */ class SendGiftCardFragment$$Lambda$5 implements OnMenuItemClickListener {
    private final SendGiftCardFragment arg$1;

    private SendGiftCardFragment$$Lambda$5(SendGiftCardFragment sendGiftCardFragment) {
        this.arg$1 = sendGiftCardFragment;
    }

    public static OnMenuItemClickListener lambdaFactory$(SendGiftCardFragment sendGiftCardFragment) {
        return new SendGiftCardFragment$$Lambda$5(sendGiftCardFragment);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.arg$1.startActivity(HelpCenterIntents.intentForHelpCenterArticle(this.arg$1.getActivity(), HelpCenterArticle.GIFT_CREDIT).toIntent());
    }
}
