package com.airbnb.android.hostcalendar.adapters;

import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CalendarAgendaAdapter_MembersInjector implements MembersInjector<CalendarAgendaAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CalendarAgendaAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CalendarJitneyLogger> jitneyLoggerProvider;

    public CalendarAgendaAdapter_MembersInjector(Provider<CalendarJitneyLogger> jitneyLoggerProvider2) {
        if ($assertionsDisabled || jitneyLoggerProvider2 != null) {
            this.jitneyLoggerProvider = jitneyLoggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<CalendarAgendaAdapter> create(Provider<CalendarJitneyLogger> jitneyLoggerProvider2) {
        return new CalendarAgendaAdapter_MembersInjector(jitneyLoggerProvider2);
    }

    public void injectMembers(CalendarAgendaAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.jitneyLogger = (CalendarJitneyLogger) this.jitneyLoggerProvider.get();
    }

    public static void injectJitneyLogger(CalendarAgendaAdapter instance, Provider<CalendarJitneyLogger> jitneyLoggerProvider2) {
        instance.jitneyLogger = (CalendarJitneyLogger) jitneyLoggerProvider2.get();
    }
}
