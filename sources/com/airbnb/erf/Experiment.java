package com.airbnb.erf;

import com.airbnb.android.utils.AirbnbConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Experiment {
    private static final String SUBJECT_BEV = "bev";
    private static final String SUBJECT_USER = "user";
    private String assignedTreatment;
    private final String holdoutExperimentName;
    private final String name;
    private final String subject;
    private final long timestamp;
    private final List<String> treatments;
    private final long version;

    public Experiment(String name2, String assignedTreatment2, long version2, long timestamp2, String holdoutExperimentName2) {
        this(name2, assignedTreatment2, Collections.emptyList(), version2, timestamp2, holdoutExperimentName2);
    }

    public Experiment(String name2, String assignedTreatment2, long version2, long timestamp2) {
        this(name2, assignedTreatment2, Collections.emptyList(), version2, timestamp2, "");
    }

    public Experiment(String name2, String assignedTreatment2, List<String> treatments2, long version2, long timestamp2, String holdoutExperimentName2) {
        this(name2, assignedTreatment2, treatments2, SUBJECT_BEV, version2, timestamp2, holdoutExperimentName2);
    }

    public Experiment(String name2, String assignedTreatment2, List<String> treatments2, long version2, long timestamp2) {
        this(name2, assignedTreatment2, treatments2, SUBJECT_BEV, version2, timestamp2, "");
    }

    public Experiment(String name2, String assignedTreatment2, List<String> treatments2, String subject2, long version2, long timestamp2, String holdoutExperimentName2) {
        this.name = name2;
        this.assignedTreatment = assignedTreatment2;
        this.version = version2;
        this.timestamp = timestamp2;
        Set<String> treatmentsSet = new HashSet<>(treatments2);
        treatmentsSet.add(assignedTreatment2);
        this.treatments = new ArrayList(treatmentsSet);
        this.subject = subject2;
        this.holdoutExperimentName = holdoutExperimentName2;
    }

    public List<String> getTreatments() {
        return this.treatments;
    }

    public String getName() {
        return this.name;
    }

    public String getAssignedTreatment() {
        return this.assignedTreatment;
    }

    public void setAssignedTreatment(String assignedTreatment2) {
        this.assignedTreatment = assignedTreatment2;
    }

    public void addTreatment(String treatment) {
        if (!this.treatments.contains(treatment)) {
            this.treatments.add(treatment);
        }
    }

    public String getSubject() {
        return this.subject;
    }

    public boolean isUserInExperiment() {
        return !AirbnbConstants.NOT_IN_EXPERIMENT_KEY.equalsIgnoreCase(this.assignedTreatment);
    }

    public boolean isSubjectUser() {
        return "user".equalsIgnoreCase(this.subject);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!this.name.equals(((Experiment) o).name)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public long version() {
        return this.version;
    }

    public long timestamp() {
        return this.timestamp;
    }

    public String holdoutExperimentName() {
        return this.holdoutExperimentName;
    }

    public boolean hasHoldoutExperiment() {
        return holdoutExperimentName() != null && !holdoutExperimentName().isEmpty();
    }
}
