package com.airbnb.android.lib.adapters;

import com.google.common.base.Predicate;

final /* synthetic */ class DebugTrebuchetAdapter$1$$Lambda$1 implements Predicate {
    private final CharSequence arg$1;

    private DebugTrebuchetAdapter$1$$Lambda$1(CharSequence charSequence) {
        this.arg$1 = charSequence;
    }

    public static Predicate lambdaFactory$(CharSequence charSequence) {
        return new DebugTrebuchetAdapter$1$$Lambda$1(charSequence);
    }

    public boolean apply(Object obj) {
        return ((String) obj).toLowerCase().contains(this.arg$1.toString().toLowerCase());
    }
}
