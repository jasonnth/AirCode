package com.airbnb.android.lib.coldstart.preloader;

import com.airbnb.android.lib.coldstart.ComponentPreloader;
import com.airbnb.android.lib.coldstart.graph.EntryActivityPreloadGraph;

public class EntryActivityPreloader extends ComponentPreloader<EntryActivityPreloadGraph> {
    public EntryActivityPreloader(EntryActivityPreloadGraph entryActivityPreloadGraph) {
        super(entryActivityPreloadGraph);
    }

    public void preload() {
        ((EntryActivityPreloadGraph) this.graph).appLaunchUtils();
        ((EntryActivityPreloadGraph) this.graph).appLaunchAnalitycs();
        ((EntryActivityPreloadGraph) this.graph).debugSettings();
    }
}
