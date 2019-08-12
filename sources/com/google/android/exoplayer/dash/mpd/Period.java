package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;

public class Period {
    public final List<AdaptationSet> adaptationSets;

    /* renamed from: id */
    public final String f3150id;
    public final long startMs;

    public Period(String id, long start, List<AdaptationSet> adaptationSets2) {
        this.f3150id = id;
        this.startMs = start;
        this.adaptationSets = Collections.unmodifiableList(adaptationSets2);
    }
}
