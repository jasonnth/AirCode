package com.airbnb.android.lib.coldstart.graph;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.DebugSettings;

public interface EntryActivityPreloadGraph extends BaseGraph {
    AppLaunchAnalytics appLaunchAnalitycs();

    AppLaunchUtils appLaunchUtils();

    DebugSettings debugSettings();
}
