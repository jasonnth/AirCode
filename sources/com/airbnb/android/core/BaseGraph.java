package com.airbnb.android.core;

import com.airbnb.airrequest.SingleFireRequestExecutor;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.erf.ExperimentAssignments;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.net.LowBandwidthManager;
import com.airbnb.android.core.net.NetworkMonitor;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.geocoder.GeocoderBaseUrl;
import com.airbnb.p027n2.internal.ComponentManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import dagger.Lazy;
import okhttp3.OkHttpClient;

public interface BaseGraph {
    AirbnbAccountManager accountManager();

    AffiliateInfo affiliateInfo();

    AirbnbApi airbnbApi();

    AirbnbEventLogger airbnbEventLogger();

    AirbnbPreferences airbnbPreferences();

    AnalyticsRegistry analyticsRegistry();

    BottomBarController bottomBarController();

    Bus bus();

    Lazy<ComponentManager> componentManager();

    CurrencyFormatter currencyHelper();

    DebugSettings debugSettings();

    Lazy<DynamicStringsResources> dynamicStringsResources();

    ExperimentAssignments experimentAssigments();

    ExperimentsProvider experimentsProvider();

    GeocoderBaseUrl geocoderBaseUrl();

    LocalPushNotificationManager localPushNotificationManager();

    LoggingContextFactory loggingContextFactory();

    LowBandwidthManager lowBandwidthUtils();

    MemoryUtils memoryUtils();

    NavigationLogging navigationAnalytics();

    NetworkMonitor networkMonitor();

    ObjectMapper objectMapper();

    OkHttpClient okHttp();

    PerformanceLogger performanceLogger();

    ResourceManager resourceManager();

    SharedPrefsHelper sharedPrefsHelper();

    SingleFireRequestExecutor singleFireRequestExecutor();

    ViewBreadcrumbManager viewBreadcrumbManager();
}
