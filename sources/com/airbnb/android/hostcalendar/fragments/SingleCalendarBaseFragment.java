package com.airbnb.android.hostcalendar.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.util.LongSparseArray;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarStoreListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.NightCount;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.hostcalendar.CalendarDateRange;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragment.SingleCalendarListenerFragment;
import com.airbnb.android.sharedcalendar.listeners.InfiniteScrollListener;
import com.airbnb.android.sharedcalendar.listeners.SingleCalendarListener;
import icepick.State;
import java.text.MessageFormat;
import java.util.Collections;

public abstract class SingleCalendarBaseFragment extends AirFragment implements SingleCalendarListenerFragment {
    protected static final String ARG_CALENDAR_RULE = "calendar_rule";
    protected static final String ARG_DATE_RANGE = "date_range";
    protected static final String ARG_LISTING_ID = "listing_id";
    private static final int SCROLL_INCREMENT_MONTHS = 3;
    private CalendarDays allCalendarDays;
    CalendarRule calendarRule;
    CalendarStore calendarStore;
    protected CalendarStoreListener calendarStoreListener = new CalendarStoreListener() {
        public void onResponse(LongSparseArray<CalendarDays> calendarDaysByListingId, LongSparseArray<NightCount> nightCountsByListingId, AirDate startDate, AirDate endDate) {
            SingleCalendarBaseFragment.this.showScrollLoaderIsLoading(false, true);
            CalendarDays newCalendarDays = (CalendarDays) calendarDaysByListingId.get(SingleCalendarBaseFragment.this.listingId);
            if (newCalendarDays != null && !newCalendarDays.isEmpty()) {
                SingleCalendarBaseFragment.this.onCalendarDaysLoaded(newCalendarDays);
            }
            if (endDate.isBefore(SingleCalendarBaseFragment.this.calendarStore.getMaxDate())) {
                SingleCalendarBaseFragment.this.showScrollLoaderIsLoading(true, false);
            }
            SingleCalendarBaseFragment.this.onNightsCountLoaded((NightCount) nightCountsByListingId.get(SingleCalendarBaseFragment.this.listingId));
            PerformanceLoggerTimeline.logTimeToInteraction(Event.HOST_CALENDAR_SINGLE);
        }

        public void onError(NetworkException e) {
            SingleCalendarBaseFragment.this.showScrollLoaderIsLoading(false, false);
            if (SingleCalendarBaseFragment.this.lastScrollEndDate == null) {
                SingleCalendarBaseFragment.this.lastScrollEndDate = SingleCalendarBaseFragment.this.startDate;
            }
            NetworkUtil.tryShowRetryableErrorWithSnackbar(SingleCalendarBaseFragment.this.getView(), e, SingleCalendarBaseFragment$1$$Lambda$1.lambdaFactory$(this));
        }
    };
    @State
    AirDate endDate;
    private final Handler handler = new Handler();
    @State
    boolean hasDoneInitialScroll;
    protected final InfiniteScrollListener infiniteScrollListener = SingleCalendarBaseFragment$$Lambda$1.lambdaFactory$(this);
    CalendarJitneyLogger jitneyLogger;
    AirDate lastScrollEndDate;
    @State
    long listingId;
    private AirDate maxDate;
    protected SingleCalendarListener singleCalendarListener;
    @State
    AirDate startDate;
    @State
    AirDate targetScrollDate;

    /* access modifiers changed from: protected */
    public abstract void bindSingleCalendarListener(SingleCalendarListener singleCalendarListener2);

    /* access modifiers changed from: protected */
    public abstract void logScrollForward();

    /* access modifiers changed from: protected */
    public abstract boolean scrollToTargetDate(AirDate airDate);

    /* access modifiers changed from: protected */
    public abstract void showScrollLoaderIsLoading(boolean z, boolean z2);

    /* access modifiers changed from: protected */
    public abstract void updateAdapter(CalendarDays calendarDays, AirDate airDate, AirDate airDate2);

    static /* synthetic */ void lambda$null$0(SingleCalendarBaseFragment singleCalendarBaseFragment, AirDate fromDate) {
        if (singleCalendarBaseFragment.endDate.isBefore(singleCalendarBaseFragment.maxDate)) {
            if (fromDate == null) {
                singleCalendarBaseFragment.lastScrollEndDate = singleCalendarBaseFragment.endDate;
                BugsnagWrapper.throwOrNotify(new RuntimeException(MessageFormat.format("SingleCalendarBaseFragment.infiniteScrollListener:  Unexpected null fromDate for listingId={0} {1}", new Object[]{Long.valueOf(singleCalendarBaseFragment.listingId), singleCalendarBaseFragment.endDate.getIsoDateString()})));
            } else {
                singleCalendarBaseFragment.lastScrollEndDate = fromDate;
            }
            singleCalendarBaseFragment.endDate = singleCalendarBaseFragment.lastScrollEndDate.plusMonths(3).getLastDayOfMonth();
            if (singleCalendarBaseFragment.endDate.isAfter(singleCalendarBaseFragment.maxDate)) {
                singleCalendarBaseFragment.endDate = singleCalendarBaseFragment.maxDate;
            }
            singleCalendarBaseFragment.calendarStore.getDaysForListingIds(Collections.singleton(Long.valueOf(singleCalendarBaseFragment.listingId)), singleCalendarBaseFragment.lastScrollEndDate, singleCalendarBaseFragment.endDate, singleCalendarBaseFragment.calendarStoreListener);
            singleCalendarBaseFragment.logScrollForward();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((HostCalendarGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Check.notNull(this.singleCalendarListener, "SingleCalendarListenerFragment requires a listener be set");
        if (savedInstanceState == null) {
            this.listingId = getArguments().getLong("listing_id");
            CalendarDateRange initialDateRange = (CalendarDateRange) getArguments().getParcelable(ARG_DATE_RANGE);
            this.startDate = initialDateRange.getStartDate();
            this.endDate = initialDateRange.getEndDate();
            this.targetScrollDate = initialDateRange.getScrollTargetDate();
            this.hasDoneInitialScroll = false;
            this.calendarRule = (CalendarRule) getArguments().getParcelable("calendar_rule");
        }
        this.maxDate = this.calendarStore.getMaxDate();
    }

    public void onResume() {
        super.onResume();
        this.calendarStoreListener.setEnabled(true);
        reloadCalendar(this.startDate);
    }

    public void onPause() {
        super.onPause();
        this.calendarStoreListener.setEnabled(false);
        this.handler.removeCallbacksAndMessages(null);
    }

    public void setListener(SingleCalendarListener listener) {
        this.singleCalendarListener = listener;
    }

    public void onDetach() {
        super.onDetach();
        this.singleCalendarListener = null;
        bindSingleCalendarListener(null);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            bindSingleCalendarListener(this.singleCalendarListener);
        } else {
            bindSingleCalendarListener(null);
        }
    }

    /* access modifiers changed from: private */
    public void reloadCalendar(AirDate fromStartDate) {
        showScrollLoaderIsLoading(true, true);
        this.calendarStore.getDaysForListingIds(Collections.singleton(Long.valueOf(this.listingId)), fromStartDate, this.endDate, this.calendarStoreListener);
    }

    /* access modifiers changed from: private */
    public void onCalendarDaysLoaded(CalendarDays newCalendarDays) {
        if (this.allCalendarDays == null) {
            this.allCalendarDays = newCalendarDays;
        } else {
            this.allCalendarDays.mergeIntoRange(newCalendarDays, this.startDate, this.endDate);
        }
        updateAdapter(this.allCalendarDays, newCalendarDays.getMinDate(), newCalendarDays.getMaxDate());
        if (!this.hasDoneInitialScroll && this.targetScrollDate != null) {
            this.hasDoneInitialScroll = scrollToTargetDate(this.targetScrollDate);
        }
    }

    /* access modifiers changed from: private */
    public void onNightsCountLoaded(NightCount count) {
        if (count != null) {
            showWarningIfBookedNightsExceedsLimit(count);
        }
    }

    /* access modifiers changed from: protected */
    public void showWarningIfBookedNightsExceedsLimit(NightCount count) {
    }
}
