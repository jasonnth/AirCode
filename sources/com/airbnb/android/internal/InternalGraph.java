package com.airbnb.android.internal;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.internal.bugreporter.InternalBugReportFragment;

public interface InternalGraph extends BaseGraph {
    void inject(InternalBugReportFragment internalBugReportFragment);
}
