package com.airbnb.android.lib.adapters;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class DebugTrebuchetAdapter$$Lambda$2 implements OnEditorActionListener {
    private static final DebugTrebuchetAdapter$$Lambda$2 instance = new DebugTrebuchetAdapter$$Lambda$2();

    private DebugTrebuchetAdapter$$Lambda$2() {
    }

    public static OnEditorActionListener lambdaFactory$() {
        return instance;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return DebugTrebuchetAdapter.lambda$new$0(textView, i, keyEvent);
    }
}
