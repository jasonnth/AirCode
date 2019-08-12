package com.airbnb.android.core.enums;

import com.google.common.base.Predicate;

final /* synthetic */ class ReadyForSelectStatus$$Lambda$1 implements Predicate {
    private final int arg$1;

    private ReadyForSelectStatus$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new ReadyForSelectStatus$$Lambda$1(i);
    }

    public boolean apply(Object obj) {
        return ReadyForSelectStatus.lambda$fromKeyWithDefault$0(this.arg$1, (ReadyForSelectStatus) obj);
    }
}
