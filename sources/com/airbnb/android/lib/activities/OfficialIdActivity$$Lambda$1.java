package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class OfficialIdActivity$$Lambda$1 implements OnClickListener {
    private final OfficialIdActivity arg$1;

    private OfficialIdActivity$$Lambda$1(OfficialIdActivity officialIdActivity) {
        this.arg$1 = officialIdActivity;
    }

    public static OnClickListener lambdaFactory$(OfficialIdActivity officialIdActivity) {
        return new OfficialIdActivity$$Lambda$1(officialIdActivity);
    }

    public void onClick(View view) {
        OfficialIdActivity.lambda$setupConfirmationButtons$0(this.arg$1, view);
    }
}
