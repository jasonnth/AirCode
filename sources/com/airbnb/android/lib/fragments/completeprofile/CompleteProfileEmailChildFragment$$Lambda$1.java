package com.airbnb.android.lib.fragments.completeprofile;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class CompleteProfileEmailChildFragment$$Lambda$1 implements OnEditorActionListener {
    private final CompleteProfileEmailChildFragment arg$1;

    private CompleteProfileEmailChildFragment$$Lambda$1(CompleteProfileEmailChildFragment completeProfileEmailChildFragment) {
        this.arg$1 = completeProfileEmailChildFragment;
    }

    public static OnEditorActionListener lambdaFactory$(CompleteProfileEmailChildFragment completeProfileEmailChildFragment) {
        return new CompleteProfileEmailChildFragment$$Lambda$1(completeProfileEmailChildFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return CompleteProfileEmailChildFragment.lambda$onCreateView$0(this.arg$1, textView, i, keyEvent);
    }
}
