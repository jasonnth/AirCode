package com.airbnb.android.lib.views;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

final /* synthetic */ class BounceFtueView$$Lambda$1 implements OnKeyListener {
    private final BounceFtueView arg$1;

    private BounceFtueView$$Lambda$1(BounceFtueView bounceFtueView) {
        this.arg$1 = bounceFtueView;
    }

    public static OnKeyListener lambdaFactory$(BounceFtueView bounceFtueView) {
        return new BounceFtueView$$Lambda$1(bounceFtueView);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return BounceFtueView.lambda$setVisibility$0(this.arg$1, view, i, keyEvent);
    }
}
