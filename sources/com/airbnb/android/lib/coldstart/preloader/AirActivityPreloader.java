package com.airbnb.android.lib.coldstart.preloader;

import com.airbnb.android.lib.coldstart.ComponentPreloader;
import com.airbnb.android.lib.coldstart.graph.AirActivityPreloadGraph;

public class AirActivityPreloader extends ComponentPreloader<AirActivityPreloadGraph> {
    public AirActivityPreloader(AirActivityPreloadGraph airActivityPreloadGraph) {
        super(airActivityPreloadGraph);
    }

    public void preload() {
        ((AirActivityPreloadGraph) this.graph).airbnbApi();
        ((AirActivityPreloadGraph) this.graph).superHeroManager();
    }
}
