package com.airbnb.erf;

import java.util.List;

public interface ExperimentsProvider {
    Experiment getExperiment(String str);

    List<Experiment> getExperimentsWithHoldout();
}
