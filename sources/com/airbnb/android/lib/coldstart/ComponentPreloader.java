package com.airbnb.android.lib.coldstart;

import com.airbnb.android.core.BaseGraph;

public abstract class ComponentPreloader<Graph extends BaseGraph> implements Preloader {
    protected final Graph graph;

    public ComponentPreloader(Graph graph2) {
        this.graph = graph2;
    }
}
