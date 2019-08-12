package com.airbnb.android.core.erf;

import com.airbnb.android.core.models.Experiment;
import com.airbnb.android.core.responses.ErfExperimentsResponse;
import com.google.common.base.Function;

final /* synthetic */ class ExperimentsProvider$$Lambda$3 implements Function {
    private final ErfExperimentsResponse arg$1;

    private ExperimentsProvider$$Lambda$3(ErfExperimentsResponse erfExperimentsResponse) {
        this.arg$1 = erfExperimentsResponse;
    }

    public static Function lambdaFactory$(ErfExperimentsResponse erfExperimentsResponse) {
        return new ExperimentsProvider$$Lambda$3(erfExperimentsResponse);
    }

    public Object apply(Object obj) {
        return ((Experiment) obj).toErfExperiment(this.arg$1.erfMetadata.timestamp);
    }
}
