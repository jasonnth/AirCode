package com.airbnb.android.cohosting.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CohostLeadsCenterAddMessageFragment$$Lambda$2 implements OnClickListener {
    private final CohostLeadsCenterAddMessageFragment arg$1;

    private CohostLeadsCenterAddMessageFragment$$Lambda$2(CohostLeadsCenterAddMessageFragment cohostLeadsCenterAddMessageFragment) {
        this.arg$1 = cohostLeadsCenterAddMessageFragment;
    }

    public static OnClickListener lambdaFactory$(CohostLeadsCenterAddMessageFragment cohostLeadsCenterAddMessageFragment) {
        return new CohostLeadsCenterAddMessageFragment$$Lambda$2(cohostLeadsCenterAddMessageFragment);
    }

    public void onClick(View view) {
        this.arg$1.getFragmentManager().popBackStack();
    }
}
