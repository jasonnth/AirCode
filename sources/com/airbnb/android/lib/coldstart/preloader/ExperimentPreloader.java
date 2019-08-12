package com.airbnb.android.lib.coldstart.preloader;

import com.airbnb.android.lib.coldstart.ComponentPreloader;
import com.airbnb.android.lib.coldstart.graph.ExperimentPreloadGraph;

public class ExperimentPreloader extends ComponentPreloader<ExperimentPreloadGraph> {
    public ExperimentPreloader(ExperimentPreloadGraph experimentPreloadGraph) {
        super(experimentPreloadGraph);
    }

    public void preload() {
        ((ExperimentPreloadGraph) this.graph).experimentAssignments();
        ((ExperimentPreloadGraph) this.graph).experimentsProvider();
    }
}
