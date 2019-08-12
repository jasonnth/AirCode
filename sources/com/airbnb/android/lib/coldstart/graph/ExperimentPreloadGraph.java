package com.airbnb.android.lib.coldstart.graph;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.erf.ExperimentAssignments;

public interface ExperimentPreloadGraph extends BaseGraph {
    ExperimentAssignments experimentAssignments();
}
