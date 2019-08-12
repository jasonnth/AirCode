package com.airbnb.android.internal.bugreporter;

import com.google.common.base.Predicate;

final /* synthetic */ class DebugDumps$$Lambda$1 implements Predicate {
    private static final DebugDumps$$Lambda$1 instance = new DebugDumps$$Lambda$1();

    private DebugDumps$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return DebugDumps.lambda$createFiles$0((String) obj);
    }
}
