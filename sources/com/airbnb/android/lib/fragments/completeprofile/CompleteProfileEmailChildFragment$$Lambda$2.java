package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class CompleteProfileEmailChildFragment$$Lambda$2 implements OnFocusChangeListener {
    private final CompleteProfileEmailChildFragment arg$1;

    private CompleteProfileEmailChildFragment$$Lambda$2(CompleteProfileEmailChildFragment completeProfileEmailChildFragment) {
        this.arg$1 = completeProfileEmailChildFragment;
    }

    public static OnFocusChangeListener lambdaFactory$(CompleteProfileEmailChildFragment completeProfileEmailChildFragment) {
        return new CompleteProfileEmailChildFragment$$Lambda$2(completeProfileEmailChildFragment);
    }

    public void onFocusChange(View view, boolean z) {
        CompleteProfileEmailChildFragment.lambda$onCreateView$1(this.arg$1, view, z);
    }
}
