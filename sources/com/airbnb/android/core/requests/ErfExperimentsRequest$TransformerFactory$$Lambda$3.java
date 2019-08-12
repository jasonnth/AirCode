package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.android.core.requests.ErfExperimentsRequest.TransformerFactory;
import com.airbnb.android.core.responses.ErfExperimentsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ErfExperimentsRequest$TransformerFactory$$Lambda$3 implements Action1 {
    private final TransformerFactory arg$1;
    private final ErfExperimentsRequest arg$2;

    private ErfExperimentsRequest$TransformerFactory$$Lambda$3(TransformerFactory transformerFactory, ErfExperimentsRequest erfExperimentsRequest) {
        this.arg$1 = transformerFactory;
        this.arg$2 = erfExperimentsRequest;
    }

    public static Action1 lambdaFactory$(TransformerFactory transformerFactory, ErfExperimentsRequest erfExperimentsRequest) {
        return new ErfExperimentsRequest$TransformerFactory$$Lambda$3(transformerFactory, erfExperimentsRequest);
    }

    public void call(Object obj) {
        this.arg$1.experimentsProvider.resetExperiments(this.arg$2, (ErfExperimentsResponse) ((AirResponse) obj).body());
    }
}
