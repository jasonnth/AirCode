package com.airbnb.android.hostcalendar;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.hostcalendar.adapters.CalendarAgendaAdapter;
import com.airbnb.android.hostcalendar.adapters.CalendarDetailAdapter;
import com.airbnb.android.hostcalendar.fragments.AgendaCalendarFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarNestedBusyDayFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarUpdateNotesFragment;
import com.airbnb.android.hostcalendar.fragments.CalendarWithPriceTipsUpdateFragment;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarBaseFragment;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragment;

public interface HostCalendarGraph extends BaseGraph {
    void inject(CalendarAgendaAdapter calendarAgendaAdapter);

    void inject(CalendarDetailAdapter calendarDetailAdapter);

    void inject(AgendaCalendarFragment agendaCalendarFragment);

    void inject(CalendarFragment calendarFragment);

    void inject(CalendarNestedBusyDayFragment calendarNestedBusyDayFragment);

    void inject(CalendarUpdateNotesFragment calendarUpdateNotesFragment);

    void inject(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment);

    void inject(SingleCalendarBaseFragment singleCalendarBaseFragment);

    void inject(SingleCalendarFragment singleCalendarFragment);
}
