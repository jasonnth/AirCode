package com.airbnb.erf;

import com.airbnb.erf.ExperimentBuilder.Treatment;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Erf {
    private static final Logger LOGGER = Logger.getLogger(Erf.class.getName());
    private final Callbacks callbacks;
    private final ExperimentsProvider experimentsProvider;

    public interface Callbacks {
        void onExperimentDelivered(String str, String str2);

        void onExperimentDeliveryFailed(ErfException erfException);

        void onExperimentValidation(Experiment experiment);
    }

    public Erf(ExperimentsProvider experimentsProvider2, Callbacks callbacks2) {
        this.experimentsProvider = experimentsProvider2;
        this.callbacks = callbacks2;
    }

    private Experiment getExperiment(String experimentName) {
        return this.experimentsProvider.getExperiment(experimentName);
    }

    public ExperimentBuilder buildExperiment(String experimentName) {
        return new ExperimentBuilder(this, experimentName);
    }

    public Map<String, String> getHoldoutExperimentMap() {
        List<Experiment> experimentList = this.experimentsProvider.getExperimentsWithHoldout();
        Map<String, String> holdoutExperimentMap = new HashMap<>(experimentList.size());
        for (Experiment e : experimentList) {
            holdoutExperimentMap.put(e.getName(), e.holdoutExperimentName());
        }
        return holdoutExperimentMap;
    }

    public String deliverExperiment(String name) {
        ExperimentBuilder builder = buildExperiment(name);
        for (String treatmentName : getTreatmentsForExperiment(name)) {
            builder.treatment(treatmentName, NoOperationTreatment.INSTANCE);
        }
        builder.notInExperimentOrUnknownTreatment((Treatment) NoOperationTreatment.INSTANCE);
        return builder.deliver();
    }

    private List<String> getTreatmentsForExperiment(String name) {
        Experiment experiment = getExperiment(name);
        if (experiment == null) {
            return Collections.emptyList();
        }
        return experiment.getTreatments();
    }

    /* access modifiers changed from: 0000 */
    public String deliverExperiment(ExperimentBuilder builder) {
        try {
            return deliverExperimentOrThrow(builder);
        } catch (ErfException e) {
            builder.getUnknownTreatment().apply();
            LOGGER.log(Level.WARNING, "Erf exception: " + e.getMessage());
            LOGGER.log(Level.WARNING, "Delivering unknown treatment for experiment '" + builder.getExperimentName() + "'");
            this.callbacks.onExperimentDeliveryFailed(e);
            return "treatment_unknown";
        }
    }

    private String deliverExperimentOrThrow(ExperimentBuilder builder) {
        Experiment experiment = getExperiment(builder.getExperimentName());
        if (experiment == null) {
            throw new ErfException(builder, "Experiment does not exist");
        } else if (experiment.isUserInExperiment() || builder.getNotInExperimentTreatment() != null) {
            this.callbacks.onExperimentValidation(experiment);
            return deliver(builder, experiment.getAssignedTreatment());
        } else {
            throw new ErfException(builder, "Must set a Not In Experiment option when using percent_exposed.");
        }
    }

    private String deliver(ExperimentBuilder builder, String treatmentName) {
        Treatment treatmentToApply = builder.getTreatment(treatmentName);
        if (treatmentToApply == null) {
            throw new ErfException(builder, "The user was assigned an undefined treatment: " + treatmentName);
        }
        treatmentToApply.apply();
        this.callbacks.onExperimentDelivered(builder.getExperimentName(), treatmentName);
        return treatmentName;
    }
}
