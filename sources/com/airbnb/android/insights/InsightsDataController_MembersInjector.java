package com.airbnb.android.insights;

import com.airbnb.android.core.calendar.CalendarStore;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class InsightsDataController_MembersInjector implements MembersInjector<InsightsDataController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!InsightsDataController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CalendarStore> calendarStoreProvider;

    public InsightsDataController_MembersInjector(Provider<CalendarStore> calendarStoreProvider2) {
        if ($assertionsDisabled || calendarStoreProvider2 != null) {
            this.calendarStoreProvider = calendarStoreProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<InsightsDataController> create(Provider<CalendarStore> calendarStoreProvider2) {
        return new InsightsDataController_MembersInjector(calendarStoreProvider2);
    }

    public void injectMembers(InsightsDataController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.calendarStore = (CalendarStore) this.calendarStoreProvider.get();
    }

    public static void injectCalendarStore(InsightsDataController instance, Provider<CalendarStore> calendarStoreProvider2) {
        instance.calendarStore = (CalendarStore) calendarStoreProvider2.get();
    }
}
