package com.airbnb.android.core.requests;

import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.requests.ErfExperimentsRequest.TransformerFactory;
import p032rx.Observable;

final /* synthetic */ class ErfExperimentsRequest$TransformerFactory$$Lambda$2 implements Transformer {
    private final TransformerFactory arg$1;
    private final ErfExperimentsRequest arg$2;

    private ErfExperimentsRequest$TransformerFactory$$Lambda$2(TransformerFactory transformerFactory, ErfExperimentsRequest erfExperimentsRequest) {
        this.arg$1 = transformerFactory;
        this.arg$2 = erfExperimentsRequest;
    }

    public static Transformer lambdaFactory$(TransformerFactory transformerFactory, ErfExperimentsRequest erfExperimentsRequest) {
        return new ErfExperimentsRequest$TransformerFactory$$Lambda$2(transformerFactory, erfExperimentsRequest);
    }

    public Object call(Object obj) {
        return ((Observable) obj).doOnNext(ErfExperimentsRequest$TransformerFactory$$Lambda$3.lambdaFactory$(this.arg$1, this.arg$2));
    }
}
