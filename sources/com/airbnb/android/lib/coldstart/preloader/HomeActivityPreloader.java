package com.airbnb.android.lib.coldstart.preloader;

import com.airbnb.android.lib.coldstart.ComponentPreloader;
import com.airbnb.android.lib.coldstart.graph.HomeActivityPreloadGraph;

public class HomeActivityPreloader extends ComponentPreloader<HomeActivityPreloadGraph> {
    public HomeActivityPreloader(HomeActivityPreloadGraph graph) {
        super(graph);
    }

    public void preload() {
        ((HomeActivityPreloadGraph) this.graph).experimentConfigController();
        ((HomeActivityPreloadGraph) this.graph).locationHelper();
        ((HomeActivityPreloadGraph) this.graph).localPushNotificationManager();
        ((HomeActivityPreloadGraph) this.graph).bottomBarController();
        ((HomeActivityPreloadGraph) this.graph).profileCompletionManager();
        ((HomeActivityPreloadGraph) this.graph).itineraryManager();
        ((HomeActivityPreloadGraph) this.graph).identityJitneyLogger();
    }
}
