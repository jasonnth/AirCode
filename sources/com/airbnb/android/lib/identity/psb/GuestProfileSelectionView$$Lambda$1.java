package com.airbnb.android.lib.identity.psb;

import com.airbnb.android.core.interfaces.GuestIdentity;
import com.google.common.base.Predicate;

final /* synthetic */ class GuestProfileSelectionView$$Lambda$1 implements Predicate {
    private static final GuestProfileSelectionView$$Lambda$1 instance = new GuestProfileSelectionView$$Lambda$1();

    private GuestProfileSelectionView$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return GuestProfileSelectionView.lambda$areAllIdentitiesSelected$0((GuestIdentity) obj);
    }
}
