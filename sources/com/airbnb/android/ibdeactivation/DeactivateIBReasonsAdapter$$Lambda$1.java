package com.airbnb.android.ibdeactivation;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.DeactivateIBReason;
import com.airbnb.android.ibdeactivation.DeactivateIBLandingFragment.DeactivateIBNavigator;

final /* synthetic */ class DeactivateIBReasonsAdapter$$Lambda$1 implements OnClickListener {
    private final DeactivateIBNavigator arg$1;
    private final DeactivateIBReason arg$2;

    private DeactivateIBReasonsAdapter$$Lambda$1(DeactivateIBNavigator deactivateIBNavigator, DeactivateIBReason deactivateIBReason) {
        this.arg$1 = deactivateIBNavigator;
        this.arg$2 = deactivateIBReason;
    }

    public static OnClickListener lambdaFactory$(DeactivateIBNavigator deactivateIBNavigator, DeactivateIBReason deactivateIBReason) {
        return new DeactivateIBReasonsAdapter$$Lambda$1(deactivateIBNavigator, deactivateIBReason);
    }

    public void onClick(View view) {
        this.arg$1.onDeactivateReasonSelected(this.arg$2);
    }
}
