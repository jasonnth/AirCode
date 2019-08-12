package com.airbnb.erf;

import com.airbnb.android.utils.AirbnbConstants;
import java.util.HashMap;
import java.util.Map;

public class ExperimentBuilder {
    private final Erf erf;
    private final String experimentName;
    private final Map<String, Treatment> treatments = new HashMap(2);

    public interface Treatment {
        void apply();
    }

    ExperimentBuilder(Erf erf2, String experimentName2) {
        this.erf = erf2;
        this.experimentName = experimentName2;
        if (experimentName2.isEmpty()) {
            throw new IllegalArgumentException("Experiment name cannot be empty");
        }
    }

    /* access modifiers changed from: 0000 */
    public Treatment getUnknownTreatment() {
        return getTreatment("treatment_unknown");
    }

    /* access modifiers changed from: 0000 */
    public Treatment getNotInExperimentTreatment() {
        return getTreatment(AirbnbConstants.NOT_IN_EXPERIMENT_KEY);
    }

    /* access modifiers changed from: 0000 */
    public String getExperimentName() {
        return this.experimentName;
    }

    /* access modifiers changed from: 0000 */
    public Treatment getTreatment(String name) {
        return (Treatment) this.treatments.get(name.toLowerCase());
    }

    public ExperimentBuilder treatment(String treatmentName, Treatment treatment) {
        if (treatment == null) {
            throw new ErfException(this, "Treatment cannot be null: " + treatmentName);
        }
        this.treatments.put(treatmentName.toLowerCase(), treatment);
        return this;
    }

    public ExperimentBuilder unknownTreatment(Treatment treatment) {
        return treatment("treatment_unknown", treatment);
    }

    public ExperimentBuilder unknownTreatment(String treatmentName) {
        return unknownTreatment(getTreatment(treatmentName));
    }

    public ExperimentBuilder notInExperiment(Treatment treatment) {
        return treatment(AirbnbConstants.NOT_IN_EXPERIMENT_KEY, treatment);
    }

    public ExperimentBuilder notInExperiment(String treatmentName) {
        return notInExperiment(getTreatment(treatmentName));
    }

    public ExperimentBuilder notInExperimentOrUnknownTreatment(String treatmentName) {
        return notInExperiment(treatmentName).unknownTreatment(treatmentName);
    }

    public ExperimentBuilder notInExperimentOrUnknownTreatment(Treatment treatment) {
        return notInExperiment(treatment).unknownTreatment(treatment);
    }

    public String deliver() {
        if (getUnknownTreatment() != null) {
            return this.erf.deliverExperiment(this);
        }
        throw new ErfException(this, "Unknown Treatment must be specified.");
    }
}
