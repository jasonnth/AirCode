package com.airbnb.android.lib.identity.psb;

import com.airbnb.android.core.interfaces.GuestIdentity;
import com.google.common.base.Function;

final /* synthetic */ class KonaSelectGuestProfileFragment$$Lambda$1 implements Function {
    private final KonaSelectGuestProfileFragment arg$1;

    private KonaSelectGuestProfileFragment$$Lambda$1(KonaSelectGuestProfileFragment konaSelectGuestProfileFragment) {
        this.arg$1 = konaSelectGuestProfileFragment;
    }

    public static Function lambdaFactory$(KonaSelectGuestProfileFragment konaSelectGuestProfileFragment) {
        return new KonaSelectGuestProfileFragment$$Lambda$1(konaSelectGuestProfileFragment);
    }

    public Object apply(Object obj) {
        return this.arg$1.updateIdentificationSelectedState((GuestIdentity) obj);
    }
}
