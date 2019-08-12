package com.airbnb.android.ibdeactivation;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DeactivateIBLandingFragment$$Lambda$1 implements OnClickListener {
    private final DeactivateIBLandingFragment arg$1;

    private DeactivateIBLandingFragment$$Lambda$1(DeactivateIBLandingFragment deactivateIBLandingFragment) {
        this.arg$1 = deactivateIBLandingFragment;
    }

    public static OnClickListener lambdaFactory$(DeactivateIBLandingFragment deactivateIBLandingFragment) {
        return new DeactivateIBLandingFragment$$Lambda$1(deactivateIBLandingFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().getSupportFragmentManager().popBackStack();
    }
}
