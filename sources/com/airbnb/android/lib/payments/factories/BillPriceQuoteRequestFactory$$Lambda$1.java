package com.airbnb.android.lib.payments.factories;

import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentity;
import com.google.common.base.Function;

final /* synthetic */ class BillPriceQuoteRequestFactory$$Lambda$1 implements Function {
    private static final BillPriceQuoteRequestFactory$$Lambda$1 instance = new BillPriceQuoteRequestFactory$$Lambda$1();

    private BillPriceQuoteRequestFactory$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return HomesGuestIdentity.fromGuestIdentity((GuestIdentity) obj);
    }
}
