package com.airbnb.android.lib.adapters;

import android.widget.Filter;
import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class DebugTrebuchetAdapter$$Lambda$1 implements StringTextWatcher {
    private final Filter arg$1;

    private DebugTrebuchetAdapter$$Lambda$1(Filter filter) {
        this.arg$1 = filter;
    }

    public static StringTextWatcher lambdaFactory$(Filter filter) {
        return new DebugTrebuchetAdapter$$Lambda$1(filter);
    }

    public void textUpdated(String str) {
        this.arg$1.filter(str);
    }
}
