package com.airbnb.android.lib.adapters;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final /* synthetic */ class CurrencyAdapter$$Lambda$1 implements OnTouchListener {
    private final ViewHolder arg$1;

    private CurrencyAdapter$$Lambda$1(ViewHolder viewHolder) {
        this.arg$1 = viewHolder;
    }

    public static OnTouchListener lambdaFactory$(ViewHolder viewHolder) {
        return new CurrencyAdapter$$Lambda$1(viewHolder);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.arg$1.radioButton.dispatchTouchEvent(motionEvent);
    }
}
