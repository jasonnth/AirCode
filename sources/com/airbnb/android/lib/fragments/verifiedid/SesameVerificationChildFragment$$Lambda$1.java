package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class SesameVerificationChildFragment$$Lambda$1 implements LinkOnClickListener {
    private final SesameVerificationChildFragment arg$1;

    private SesameVerificationChildFragment$$Lambda$1(SesameVerificationChildFragment sesameVerificationChildFragment) {
        this.arg$1 = sesameVerificationChildFragment;
    }

    public static LinkOnClickListener lambdaFactory$(SesameVerificationChildFragment sesameVerificationChildFragment) {
        return new SesameVerificationChildFragment$$Lambda$1(sesameVerificationChildFragment);
    }

    public void onClickLink(int i) {
        this.arg$1.startActivity(HelpCenterIntents.intentForHelpCenterArticle(this.arg$1.getContext(), HelpCenterArticle.SESAME_CREDIT).title(C0880R.string.verified_id_learn_more).toIntent());
    }
}
