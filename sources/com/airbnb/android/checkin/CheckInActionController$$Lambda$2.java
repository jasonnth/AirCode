package com.airbnb.android.checkin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CheckInActionController$$Lambda$2 implements OnClickListener {
    private final CheckInActionController arg$1;

    private CheckInActionController$$Lambda$2(CheckInActionController checkInActionController) {
        this.arg$1 = checkInActionController;
    }

    public static OnClickListener lambdaFactory$(CheckInActionController checkInActionController) {
        return new CheckInActionController$$Lambda$2(checkInActionController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onWifiClicked(this.arg$1.wirelessInfo);
    }
}
