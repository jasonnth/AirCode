package com.airbnb.android.explore.fragments;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class MTLocationChinaFragment$$Lambda$7 implements OnEditorActionListener {
    private final MTLocationChinaFragment arg$1;

    private MTLocationChinaFragment$$Lambda$7(MTLocationChinaFragment mTLocationChinaFragment) {
        this.arg$1 = mTLocationChinaFragment;
    }

    public static OnEditorActionListener lambdaFactory$(MTLocationChinaFragment mTLocationChinaFragment) {
        return new MTLocationChinaFragment$$Lambda$7(mTLocationChinaFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return MTLocationChinaFragment.lambda$onCreateView$0(this.arg$1, textView, i, keyEvent);
    }
}
