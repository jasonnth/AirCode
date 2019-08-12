package com.airbnb.android.lib.identity.psb;

import com.airbnb.android.core.interfaces.GuestIdentity;
import com.google.common.base.Predicate;

final /* synthetic */ class GuestIdentificationAdapter$$Lambda$1 implements Predicate {
    private static final GuestIdentificationAdapter$$Lambda$1 instance = new GuestIdentificationAdapter$$Lambda$1();

    private GuestIdentificationAdapter$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return GuestIdentificationAdapter.lambda$setIdentifications$0((GuestIdentity) obj);
    }
}
