package com.airbnb.android.lib.payments.networking.createbill.requester;

import com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters;
import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.ProfilingResult;

final /* synthetic */ class CreateBillDelegate$$Lambda$3 implements EndNotifier {
    private final CreateBillDelegate arg$1;
    private final CreateBillParameters arg$2;

    private CreateBillDelegate$$Lambda$3(CreateBillDelegate createBillDelegate, CreateBillParameters createBillParameters) {
        this.arg$1 = createBillDelegate;
        this.arg$2 = createBillParameters;
    }

    public static EndNotifier lambdaFactory$(CreateBillDelegate createBillDelegate, CreateBillParameters createBillParameters) {
        return new CreateBillDelegate$$Lambda$3(createBillDelegate, createBillParameters);
    }

    public void complete(ProfilingResult profilingResult) {
        CreateBillDelegate.lambda$createBill$0(this.arg$1, this.arg$2, profilingResult);
    }
}
