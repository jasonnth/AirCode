package com.airbnb.android.explore;

import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface ExploreComponent extends ExploreGraph {

    public interface Builder {
        ExploreComponent build();
    }
}
