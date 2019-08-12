package com.airbnb.android.itinerary;

import android.content.Context;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.itinerary.controllers.ItineraryDataController;
import com.airbnb.android.itinerary.controllers.ItineraryManagerImpl;
import com.airbnb.android.itinerary.controllers.ItineraryPerformanceAnalytics;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;

public class ItineraryModule {
    public ItineraryTableOpenHelper provideItineraryTableOpenHelper(Context context) {
        return new ItineraryTableOpenHelper(context);
    }

    public ItineraryManager provideItineraryManager(ItineraryTableOpenHelper openHelper, PerformanceLogger performanceLogger, SharedPrefsHelper sharedPrefsHelper) {
        return new ItineraryManagerImpl(openHelper, new ItineraryDataController(openHelper, NetworkUtil.singleFireExecutor(), new ItineraryPerformanceAnalytics(performanceLogger), sharedPrefsHelper));
    }
}
