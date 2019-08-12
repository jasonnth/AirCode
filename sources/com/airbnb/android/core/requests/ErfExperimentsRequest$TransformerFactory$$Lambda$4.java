package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.android.core.requests.ErfExperimentsRequest.TransformerFactory;
import p032rx.functions.Action1;

final /* synthetic */ class ErfExperimentsRequest$TransformerFactory$$Lambda$4 implements Action1 {
    private final TransformerFactory arg$1;
    private final AirBatchRequest arg$2;

    private ErfExperimentsRequest$TransformerFactory$$Lambda$4(TransformerFactory transformerFactory, AirBatchRequest airBatchRequest) {
        this.arg$1 = transformerFactory;
        this.arg$2 = airBatchRequest;
    }

    public static Action1 lambdaFactory$(TransformerFactory transformerFactory, AirBatchRequest airBatchRequest) {
        return new ErfExperimentsRequest$TransformerFactory$$Lambda$4(transformerFactory, airBatchRequest);
    }

    public void call(Object obj) {
        TransformerFactory.lambda$null$0(this.arg$1, this.arg$2, (AirResponse) obj);
    }
}
