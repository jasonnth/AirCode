package com.airbnb.android.lib.coldstart.preloader;

import com.airbnb.android.lib.coldstart.ComponentPreloader;
import com.airbnb.android.lib.coldstart.graph.AirActivityPreloadGraph;

public class AirActivityWishListPreloader extends ComponentPreloader<AirActivityPreloadGraph> {
    public AirActivityWishListPreloader(AirActivityPreloadGraph airActivityPreloadGraph) {
        super(airActivityPreloadGraph);
    }

    public void preload() {
        ((AirActivityPreloadGraph) this.graph).wishListManager();
    }
}
