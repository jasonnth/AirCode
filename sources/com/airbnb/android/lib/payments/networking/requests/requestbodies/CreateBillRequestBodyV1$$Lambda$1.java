package com.airbnb.android.lib.payments.networking.requests.requestbodies;

import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBodyV1.Builder;
import com.google.common.base.Function;

final /* synthetic */ class CreateBillRequestBodyV1$$Lambda$1 implements Function {
    private final CreateBillRequestBodyV1 arg$1;
    private final Builder arg$2;

    private CreateBillRequestBodyV1$$Lambda$1(CreateBillRequestBodyV1 createBillRequestBodyV1, Builder builder) {
        this.arg$1 = createBillRequestBodyV1;
        this.arg$2 = builder;
    }

    public static Function lambdaFactory$(CreateBillRequestBodyV1 createBillRequestBodyV1, Builder builder) {
        return new CreateBillRequestBodyV1$$Lambda$1(createBillRequestBodyV1, builder);
    }

    public Object apply(Object obj) {
        return this.arg$1.quickPayParameterParameterToProduct(this.arg$2, (QuickPayParameters) obj);
    }
}
