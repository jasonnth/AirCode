package com.airbnb.android.lib.fragments.unlist;

import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class UnlistTrustQuestionsFragment$$Lambda$2 implements LinkOnClickListener {
    private final UnlistTrustQuestionsFragment arg$1;

    private UnlistTrustQuestionsFragment$$Lambda$2(UnlistTrustQuestionsFragment unlistTrustQuestionsFragment) {
        this.arg$1 = unlistTrustQuestionsFragment;
    }

    public static LinkOnClickListener lambdaFactory$(UnlistTrustQuestionsFragment unlistTrustQuestionsFragment) {
        return new UnlistTrustQuestionsFragment$$Lambda$2(unlistTrustQuestionsFragment);
    }

    public void onClickLink(int i) {
        UnlistTrustQuestionsFragment.lambda$initializeReservationRequirementLinks$1(this.arg$1, i);
    }
}
