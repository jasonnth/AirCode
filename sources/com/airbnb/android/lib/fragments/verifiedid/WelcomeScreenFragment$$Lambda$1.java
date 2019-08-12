package com.airbnb.android.lib.fragments.verifiedid;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.lib.C0880R;

final /* synthetic */ class WelcomeScreenFragment$$Lambda$1 implements OnClickListener {
    private final WelcomeScreenFragment arg$1;

    private WelcomeScreenFragment$$Lambda$1(WelcomeScreenFragment welcomeScreenFragment) {
        this.arg$1 = welcomeScreenFragment;
    }

    public static OnClickListener lambdaFactory$(WelcomeScreenFragment welcomeScreenFragment) {
        return new WelcomeScreenFragment$$Lambda$1(welcomeScreenFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(HelpCenterIntents.intentForHelpCenterArticle(this.arg$1.getContext(), 450).title(C0880R.string.verified_id_learn_more).toIntent());
    }
}
