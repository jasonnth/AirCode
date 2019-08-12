package com.airbnb.android.lib.payments.networking.createbill.requester;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CreateBillDelegate$$Lambda$2 implements Action1 {
    private final CreateBillDelegate arg$1;

    private CreateBillDelegate$$Lambda$2(CreateBillDelegate createBillDelegate) {
        this.arg$1 = createBillDelegate;
    }

    public static Action1 lambdaFactory$(CreateBillDelegate createBillDelegate) {
        return new CreateBillDelegate$$Lambda$2(createBillDelegate);
    }

    public void call(Object obj) {
        this.arg$1.getDelegateListener().onCreateBillFailure((AirRequestNetworkException) obj);
    }
}
