package com.airbnb.android.core.requests;

import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.requests.ErfExperimentsRequest.TransformerFactory;
import p032rx.Observable;

final /* synthetic */ class ErfExperimentsRequest$TransformerFactory$$Lambda$1 implements Transformer {
    private final TransformerFactory arg$1;
    private final AirBatchRequest arg$2;

    private ErfExperimentsRequest$TransformerFactory$$Lambda$1(TransformerFactory transformerFactory, AirBatchRequest airBatchRequest) {
        this.arg$1 = transformerFactory;
        this.arg$2 = airBatchRequest;
    }

    public static Transformer lambdaFactory$(TransformerFactory transformerFactory, AirBatchRequest airBatchRequest) {
        return new ErfExperimentsRequest$TransformerFactory$$Lambda$1(transformerFactory, airBatchRequest);
    }

    public Object call(Object obj) {
        return ((Observable) obj).doOnNext(ErfExperimentsRequest$TransformerFactory$$Lambda$4.lambdaFactory$(this.arg$1, this.arg$2));
    }
}
