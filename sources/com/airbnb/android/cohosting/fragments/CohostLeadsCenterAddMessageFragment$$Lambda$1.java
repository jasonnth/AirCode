package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.utils.TextWatcherUtils.IsEmptyTextWatcher;

final /* synthetic */ class CohostLeadsCenterAddMessageFragment$$Lambda$1 implements IsEmptyTextWatcher {
    private final CohostLeadsCenterAddMessageFragment arg$1;

    private CohostLeadsCenterAddMessageFragment$$Lambda$1(CohostLeadsCenterAddMessageFragment cohostLeadsCenterAddMessageFragment) {
        this.arg$1 = cohostLeadsCenterAddMessageFragment;
    }

    public static IsEmptyTextWatcher lambdaFactory$(CohostLeadsCenterAddMessageFragment cohostLeadsCenterAddMessageFragment) {
        return new CohostLeadsCenterAddMessageFragment$$Lambda$1(cohostLeadsCenterAddMessageFragment);
    }

    public void textUpdated(boolean z) {
        CohostLeadsCenterAddMessageFragment.lambda$new$0(this.arg$1, z);
    }
}
