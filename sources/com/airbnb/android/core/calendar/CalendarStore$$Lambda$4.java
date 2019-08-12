package com.airbnb.android.core.calendar;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.responses.AirBatchResponse;
import java.util.Set;
import p032rx.functions.Action1;

final /* synthetic */ class CalendarStore$$Lambda$4 implements Action1 {
    private final CalendarStore arg$1;
    private final Set arg$2;
    private final AirDate arg$3;
    private final AirDate arg$4;
    private final CalendarStoreListener arg$5;

    private CalendarStore$$Lambda$4(CalendarStore calendarStore, Set set, AirDate airDate, AirDate airDate2, CalendarStoreListener calendarStoreListener) {
        this.arg$1 = calendarStore;
        this.arg$2 = set;
        this.arg$3 = airDate;
        this.arg$4 = airDate2;
        this.arg$5 = calendarStoreListener;
    }

    public static Action1 lambdaFactory$(CalendarStore calendarStore, Set set, AirDate airDate, AirDate airDate2, CalendarStoreListener calendarStoreListener) {
        return new CalendarStore$$Lambda$4(calendarStore, set, airDate, airDate2, calendarStoreListener);
    }

    public void call(Object obj) {
        this.arg$1.onSuccessResponse(this.arg$2, this.arg$3, this.arg$4, this.arg$5, (AirBatchResponse) obj);
    }
}
