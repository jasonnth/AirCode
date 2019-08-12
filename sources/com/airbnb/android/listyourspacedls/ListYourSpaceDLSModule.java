package com.airbnb.android.listyourspacedls;

import com.airbnb.android.core.LoggingContextFactory;

public class ListYourSpaceDLSModule {
    public static LYSJitneyLogger lysJitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new LYSJitneyLogger(loggingContextFactory);
    }
}
