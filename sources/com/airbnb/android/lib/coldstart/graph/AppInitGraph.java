package com.airbnb.android.lib.coldstart.graph;

import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.PostInteractiveInitializer;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.analytics.AppForegroundAnalytics;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.utils.ClientSessionValidator;
import dagger.Lazy;
import java.util.Set;

public interface AppInitGraph extends BaseGraph {
    Lazy<AppForegroundAnalytics> appForegroundAnalytics();

    AppForegroundDetector appForegroundDetector();

    Lazy<AppLaunchAnalytics> appLaunchAnalytics();

    Lazy<ClientSessionValidator> clientSessionValidator();

    Lazy<Set<PostApplicationCreatedInitializer>> needsPostApplicationCreatedInitialization();

    Lazy<Set<PostInteractiveInitializer>> needsPostInteractiveInitialization();

    ViewBreadcrumbManager viewBreadcrumbManager();
}
