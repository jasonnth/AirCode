package com.airbnb.android.ibdeactivation;

import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface IBDeactivationComponent extends IBDeactivationGraph {

    public interface Builder {
        IBDeactivationComponent build();
    }
}
