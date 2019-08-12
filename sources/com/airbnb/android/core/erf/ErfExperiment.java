package com.airbnb.android.core.erf;

import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.airbnb.erf.Experiment;
import java.util.Collections;
import java.util.List;

public class ErfExperiment extends Experiment implements ErfExperimentsModel {
    public ErfExperiment(String name, String assignedTreatment, List<String> treatments, String subject, long version, long timestamp, String holdoutName) {
        super(name, assignedTreatment, treatments, subject, version, timestamp, holdoutName);
    }

    public ErfExperiment(String name, String assignedTreatment, List<String> treatments, String subject, long version, long timestamp) {
        this(name, assignedTreatment, treatments, subject, version, timestamp, "");
    }

    public ErfExperiment(String experimentName, String assignedTreatment, List<String> treatments, String user, int version) {
        this(experimentName, assignedTreatment, treatments, user, (long) version, 0);
    }

    public ErfExperiment(String experimentName, String assignedTreatment, List<String> treatments, String user) {
        this(experimentName, assignedTreatment, treatments, user, 0);
    }

    public ErfExperiment(String experimentName, String assignedTreatment, List<String> treatments) {
        this(experimentName, assignedTreatment, treatments, "bev");
    }

    public ErfExperiment(String experimentName, String assignedTreatment) {
        this(experimentName, assignedTreatment, Collections.emptyList());
    }

    public String experimentName() {
        return getName();
    }

    public String assignedTreatment() {
        return getAssignedTreatment();
    }

    public String subject() {
        return getSubject();
    }

    public String treatments() {
        throw new IllegalStateException("This method should not be called. Use getTreatments() instead.");
    }
}
