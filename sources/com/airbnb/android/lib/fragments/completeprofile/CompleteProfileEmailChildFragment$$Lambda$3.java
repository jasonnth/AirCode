package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CompleteProfileEmailChildFragment$$Lambda$3 implements OnClickListener {
    private final CompleteProfileEmailChildFragment arg$1;

    private CompleteProfileEmailChildFragment$$Lambda$3(CompleteProfileEmailChildFragment completeProfileEmailChildFragment) {
        this.arg$1 = completeProfileEmailChildFragment;
    }

    public static OnClickListener lambdaFactory$(CompleteProfileEmailChildFragment completeProfileEmailChildFragment) {
        return new CompleteProfileEmailChildFragment$$Lambda$3(completeProfileEmailChildFragment);
    }

    public void onClick(View view) {
        CompleteProfileEmailChildFragment.lambda$onCreateView$2(this.arg$1, view);
    }
}
