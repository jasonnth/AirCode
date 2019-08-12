package com.airbnb.android.lib.fragments.unlist;

import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class UnlistTrustQuestionsFragment$$Lambda$1 implements LinkOnClickListener {
    private final UnlistTrustQuestionsFragment arg$1;

    private UnlistTrustQuestionsFragment$$Lambda$1(UnlistTrustQuestionsFragment unlistTrustQuestionsFragment) {
        this.arg$1 = unlistTrustQuestionsFragment;
    }

    public static LinkOnClickListener lambdaFactory$(UnlistTrustQuestionsFragment unlistTrustQuestionsFragment) {
        return new UnlistTrustQuestionsFragment$$Lambda$1(unlistTrustQuestionsFragment);
    }

    public void onClickLink(int i) {
        UnlistTrustQuestionsFragment.lambda$initializeHostGuaranteeLink$0(this.arg$1, i);
    }
}
