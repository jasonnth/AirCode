package com.airbnb.android.managelisting;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.managelisting.analytics.CheckInJitneyLogger;

public class ManageListingModule {
    public static CheckInJitneyLogger checkInJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new CheckInJitneyLogger(loggingContextFactory);
    }
}
