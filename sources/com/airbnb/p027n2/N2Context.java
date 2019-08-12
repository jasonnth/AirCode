package com.airbnb.p027n2;

/* renamed from: com.airbnb.n2.N2Context */
public class N2Context {
    private static N2Context INSTANCE;
    private final N2Graph graph;

    private N2Context(N2Graph graph2) {
        this.graph = graph2;
    }

    public static N2Context create(N2Graph graph2) {
        if (INSTANCE == null) {
            INSTANCE = new N2Context(graph2);
        }
        return INSTANCE;
    }

    public static N2Context instance() {
        return INSTANCE;
    }

    public N2Graph graph() {
        return this.graph;
    }
}
