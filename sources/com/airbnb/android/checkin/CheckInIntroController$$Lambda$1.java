package com.airbnb.android.checkin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CheckInIntroController$$Lambda$1 implements OnClickListener {
    private final CheckInIntroController arg$1;

    private CheckInIntroController$$Lambda$1(CheckInIntroController checkInIntroController) {
        this.arg$1 = checkInIntroController;
    }

    public static OnClickListener lambdaFactory$(CheckInIntroController checkInIntroController) {
        return new CheckInIntroController$$Lambda$1(checkInIntroController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onAddressSelected(this.arg$1.address);
    }
}
