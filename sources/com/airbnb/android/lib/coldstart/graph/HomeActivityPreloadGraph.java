package com.airbnb.android.lib.coldstart.graph;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.controllers.ExperimentConfigController;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.profile_completion.ProfileCompletionManager;

public interface HomeActivityPreloadGraph extends BaseGraph {
    BottomBarController bottomBarController();

    ExperimentConfigController experimentConfigController();

    IdentityJitneyLogger identityJitneyLogger();

    ItineraryManager itineraryManager();

    LocalPushNotificationManager localPushNotificationManager();

    LocationClientFacade locationHelper();

    ProfileCompletionManager profileCompletionManager();
}
