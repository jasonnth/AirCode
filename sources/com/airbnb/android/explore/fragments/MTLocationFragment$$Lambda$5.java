package com.airbnb.android.explore.fragments;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class MTLocationFragment$$Lambda$5 implements OnEditorActionListener {
    private final MTLocationFragment arg$1;

    private MTLocationFragment$$Lambda$5(MTLocationFragment mTLocationFragment) {
        this.arg$1 = mTLocationFragment;
    }

    public static OnEditorActionListener lambdaFactory$(MTLocationFragment mTLocationFragment) {
        return new MTLocationFragment$$Lambda$5(mTLocationFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return MTLocationFragment.lambda$onCreateView$0(this.arg$1, textView, i, keyEvent);
    }
}
