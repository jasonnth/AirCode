package com.airbnb.erf;

import com.airbnb.erf.annotations.Treatment;

public abstract class ExperimentConfig {
    public abstract boolean isEnabled();

    public boolean isPhoneOnly() {
        return false;
    }

    public boolean shouldForceTreatment() {
        return false;
    }

    public String treatmentToForce() {
        Treatment treatment = (Treatment) getClass().getAnnotation(Treatment.class);
        if (treatment != null) {
            return treatment.name();
        }
        throw new RuntimeException("Treatment for testing is not defined.");
    }

    public boolean shouldUseStickyAssignments() {
        return false;
    }
}
