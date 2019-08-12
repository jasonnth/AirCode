package com.airbnb.android.hostcalendar.adapters;

import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CalendarDetailAdapter_MembersInjector implements MembersInjector<CalendarDetailAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CalendarDetailAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CalendarJitneyLogger> jitneyLoggerProvider;

    public CalendarDetailAdapter_MembersInjector(Provider<CalendarJitneyLogger> jitneyLoggerProvider2) {
        if ($assertionsDisabled || jitneyLoggerProvider2 != null) {
            this.jitneyLoggerProvider = jitneyLoggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<CalendarDetailAdapter> create(Provider<CalendarJitneyLogger> jitneyLoggerProvider2) {
        return new CalendarDetailAdapter_MembersInjector(jitneyLoggerProvider2);
    }

    public void injectMembers(CalendarDetailAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.jitneyLogger = (CalendarJitneyLogger) this.jitneyLoggerProvider.get();
    }

    public static void injectJitneyLogger(CalendarDetailAdapter instance, Provider<CalendarJitneyLogger> jitneyLoggerProvider2) {
        instance.jitneyLogger = (CalendarJitneyLogger) jitneyLoggerProvider2.get();
    }
}
