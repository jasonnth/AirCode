package com.airbnb.android.core.erf;

import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.airbnb.android.erf.p010db.ErfExperimentsModel.Creator;
import com.fasterxml.jackson.databind.ObjectMapper;

final /* synthetic */ class ErfExperimentFactory$$Lambda$1 implements Creator {
    private final ObjectMapper arg$1;

    private ErfExperimentFactory$$Lambda$1(ObjectMapper objectMapper) {
        this.arg$1 = objectMapper;
    }

    public static Creator lambdaFactory$(ObjectMapper objectMapper) {
        return new ErfExperimentFactory$$Lambda$1(objectMapper);
    }

    public ErfExperimentsModel create(String str, String str2, String str3, long j, String str4, long j2, String str5) {
        return ErfExperimentFactory.lambda$new$0(this.arg$1, str, str2, str3, j, str4, j2, str5);
    }
}
