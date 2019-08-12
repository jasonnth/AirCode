package com.airbnb.android.core.requests.booking.requestbodies;

import com.airbnb.android.core.interfaces.GuestIdentity;
import com.google.common.base.Function;

final /* synthetic */ class BookingRequestBody$Builder$$Lambda$1 implements Function {
    private static final BookingRequestBody$Builder$$Lambda$1 instance = new BookingRequestBody$Builder$$Lambda$1();

    private BookingRequestBody$Builder$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new GuestIdentification((GuestIdentity) obj);
    }
}
