package com.airbnb.android.ibdeactivation;

import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentListener;

final /* synthetic */ class DeactivateIBActivity$$Lambda$1 implements EditTextFragmentListener {
    private final DeactivateIBActivity arg$1;

    private DeactivateIBActivity$$Lambda$1(DeactivateIBActivity deactivateIBActivity) {
        this.arg$1 = deactivateIBActivity;
    }

    public static EditTextFragmentListener lambdaFactory$(DeactivateIBActivity deactivateIBActivity) {
        return new DeactivateIBActivity$$Lambda$1(deactivateIBActivity);
    }

    public void onMessageSaved(String str) {
        this.arg$1.showReviewAllRequestsFragment(str);
    }
}
