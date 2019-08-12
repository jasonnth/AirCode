package com.airbnb.android.core.views.calendar;

import com.airbnb.android.core.analytics.AvailabilityCalendarJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class VerticalCalendarAdapter_MembersInjector implements MembersInjector<VerticalCalendarAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!VerticalCalendarAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AvailabilityCalendarJitneyLogger> availabilityCalendarJitneyLoggerProvider;

    public VerticalCalendarAdapter_MembersInjector(Provider<AvailabilityCalendarJitneyLogger> availabilityCalendarJitneyLoggerProvider2) {
        if ($assertionsDisabled || availabilityCalendarJitneyLoggerProvider2 != null) {
            this.availabilityCalendarJitneyLoggerProvider = availabilityCalendarJitneyLoggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<VerticalCalendarAdapter> create(Provider<AvailabilityCalendarJitneyLogger> availabilityCalendarJitneyLoggerProvider2) {
        return new VerticalCalendarAdapter_MembersInjector(availabilityCalendarJitneyLoggerProvider2);
    }

    public void injectMembers(VerticalCalendarAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.availabilityCalendarJitneyLogger = (AvailabilityCalendarJitneyLogger) this.availabilityCalendarJitneyLoggerProvider.get();
    }

    public static void injectAvailabilityCalendarJitneyLogger(VerticalCalendarAdapter instance, Provider<AvailabilityCalendarJitneyLogger> availabilityCalendarJitneyLoggerProvider2) {
        instance.availabilityCalendarJitneyLogger = (AvailabilityCalendarJitneyLogger) availabilityCalendarJitneyLoggerProvider2.get();
    }
}
