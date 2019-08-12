package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class OfflineIdChildFragment$$Lambda$1 implements LinkOnClickListener {
    private final OfflineIdChildFragment arg$1;

    private OfflineIdChildFragment$$Lambda$1(OfflineIdChildFragment offlineIdChildFragment) {
        this.arg$1 = offlineIdChildFragment;
    }

    public static LinkOnClickListener lambdaFactory$(OfflineIdChildFragment offlineIdChildFragment) {
        return new OfflineIdChildFragment$$Lambda$1(offlineIdChildFragment);
    }

    public void onClickLink(int i) {
        this.arg$1.startActivity(HelpCenterIntents.intentForHelpCenterArticle(this.arg$1.getContext(), 450).title(C0880R.string.verified_id_learn_more).toIntent());
    }
}
