package com.airbnb.android.checkin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CheckInStepController$$Lambda$2 implements OnClickListener {
    private final CheckInStepController arg$1;

    private CheckInStepController$$Lambda$2(CheckInStepController checkInStepController) {
        this.arg$1 = checkInStepController;
    }

    public static OnClickListener lambdaFactory$(CheckInStepController checkInStepController) {
        return new CheckInStepController$$Lambda$2(checkInStepController);
    }

    public void onClick(View view) {
        this.arg$1.toggleTranslation();
    }
}
