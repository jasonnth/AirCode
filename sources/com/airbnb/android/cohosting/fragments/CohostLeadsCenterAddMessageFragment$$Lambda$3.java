package com.airbnb.android.cohosting.fragments;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

final /* synthetic */ class CohostLeadsCenterAddMessageFragment$$Lambda$3 implements OnMenuItemClickListener {
    private final CohostLeadsCenterAddMessageFragment arg$1;

    private CohostLeadsCenterAddMessageFragment$$Lambda$3(CohostLeadsCenterAddMessageFragment cohostLeadsCenterAddMessageFragment) {
        this.arg$1 = cohostLeadsCenterAddMessageFragment;
    }

    public static OnMenuItemClickListener lambdaFactory$(CohostLeadsCenterAddMessageFragment cohostLeadsCenterAddMessageFragment) {
        return new CohostLeadsCenterAddMessageFragment$$Lambda$3(cohostLeadsCenterAddMessageFragment);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.arg$1.getActivity().finish();
    }
}
