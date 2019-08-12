package com.airbnb.android.checkin.data;

import android.content.Context;
import com.airbnb.android.checkin.GuestCheckInJitneyLogger;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.explore.ChildScope;

public class CheckInModule {
    @ChildScope
    public CheckInDataTableOpenHelper provideCheckInDataTableOpenHelper(Context context) {
        return new CheckInDataTableOpenHelper(context);
    }

    @ChildScope
    public static GuestCheckInJitneyLogger jitneyLogger(LoggingContextFactory loggingContextFactory) {
        return new GuestCheckInJitneyLogger(loggingContextFactory);
    }
}
