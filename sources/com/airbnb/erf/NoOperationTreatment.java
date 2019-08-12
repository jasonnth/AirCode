package com.airbnb.erf;

import com.airbnb.erf.ExperimentBuilder.Treatment;

public class NoOperationTreatment implements Treatment {
    public static final NoOperationTreatment INSTANCE = new NoOperationTreatment();

    private NoOperationTreatment() {
    }

    public void apply() {
    }
}
