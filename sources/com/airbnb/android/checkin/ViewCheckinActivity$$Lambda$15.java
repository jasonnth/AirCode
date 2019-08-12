package com.airbnb.android.checkin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ViewCheckinActivity$$Lambda$15 implements OnClickListener {
    private final ViewCheckinActivity arg$1;

    private ViewCheckinActivity$$Lambda$15(ViewCheckinActivity viewCheckinActivity) {
        this.arg$1 = viewCheckinActivity;
    }

    public static OnClickListener lambdaFactory$(ViewCheckinActivity viewCheckinActivity) {
        return new ViewCheckinActivity$$Lambda$15(viewCheckinActivity);
    }

    public void onClick(View view) {
        this.arg$1.fetchGuide();
    }
}
