package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DebugLogView$LogDebugViewAdapter$$Lambda$1 implements OnClickListener {
    private final LogDebugViewAdapter arg$1;
    private final int arg$2;

    private DebugLogView$LogDebugViewAdapter$$Lambda$1(LogDebugViewAdapter logDebugViewAdapter, int i) {
        this.arg$1 = logDebugViewAdapter;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(LogDebugViewAdapter logDebugViewAdapter, int i) {
        return new DebugLogView$LogDebugViewAdapter$$Lambda$1(logDebugViewAdapter, i);
    }

    public void onClick(View view) {
        this.arg$1.handleClick(this.arg$2);
    }
}
