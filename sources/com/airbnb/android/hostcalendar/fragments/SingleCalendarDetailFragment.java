package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindDimen;
import butterknife.BindView;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HostCalendarIntents.ArgumentConstants;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.CalendarDateRange;
import com.airbnb.android.hostcalendar.adapters.CalendarDetailAdapter;
import com.airbnb.android.hostcalendar.adapters.CalendarDetailAdapter.CalendarDetailRow;
import com.airbnb.android.hostcalendar.views.CalendarDetailHeaderRow;
import com.airbnb.android.sharedcalendar.listeners.SingleCalendarListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import java.util.Set;

public class SingleCalendarDetailFragment extends SingleCalendarBaseFragment {
    private CalendarDetailAdapter adapter;
    @BindView
    CalendarDetailHeaderRow headerRow;
    /* access modifiers changed from: private */
    public LinearLayoutManager layoutManager;
    @BindDimen
    int paddingFromOverlappingReservation;
    @BindView
    RecyclerView recyclerView;

    public static SingleCalendarDetailFragment calendarForDates(long listingId, CalendarDateRange calendarDateRange, CalendarRule calendarRule) {
        return (SingleCalendarDetailFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SingleCalendarDetailFragment()).putLong("listing_id", listingId)).putParcelable("date_range", calendarDateRange)).putParcelable(ArgumentConstants.ARG_CALENDAR_RULE, calendarRule)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6418R.layout.fragment_host_calendar_detail, container, false);
        bindViews(view);
        this.headerRow.setTodayClickableOnClickListener(SingleCalendarDetailFragment$$Lambda$1.lambdaFactory$(this));
        this.adapter = new CalendarDetailAdapter(getContext(), this.listingId, this.calendarRule);
        this.adapter.setInfiniteScrollListener(this.infiniteScrollListener);
        bindSingleCalendarListener(this.singleCalendarListener);
        this.recyclerView.setAdapter(this.adapter);
        this.layoutManager = (LinearLayoutManager) this.recyclerView.getLayoutManager();
        this.recyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                boolean z = false;
                AirDate firstDate = SingleCalendarDetailFragment.this.getDateFromPosition(SingleCalendarDetailFragment.this.layoutManager.findFirstVisibleItemPosition());
                if (firstDate != null) {
                    SingleCalendarDetailFragment.this.headerRow.setMonthText(firstDate, false);
                    AirDate lastDate = SingleCalendarDetailFragment.this.getDateFromPosition(SingleCalendarDetailFragment.this.layoutManager.findLastVisibleItemPosition());
                    if (lastDate != null) {
                        CalendarDetailHeaderRow calendarDetailHeaderRow = SingleCalendarDetailFragment.this.headerRow;
                        if (!AirDate.today().isBetweenInclusive(firstDate, lastDate)) {
                            z = true;
                        }
                        calendarDetailHeaderRow.setTodayClickableVisibility(z);
                    }
                }
            }
        });
        return view;
    }

    /* access modifiers changed from: 0000 */
    public AirDate getDateFromPosition(int position) {
        View visibleItem = this.layoutManager.findViewByPosition(position);
        if (visibleItem == null || !(visibleItem instanceof CalendarDetailRow)) {
            return null;
        }
        return ((CalendarDetailRow) visibleItem).getDateForScrolling();
    }

    /* access modifiers changed from: protected */
    public void bindSingleCalendarListener(SingleCalendarListener singleCalendarListener) {
        if (this.adapter != null) {
            this.adapter.setSingleCalendarListener(singleCalendarListener);
        }
    }

    /* access modifiers changed from: protected */
    public void updateAdapter(CalendarDays calendarDays, AirDate minDate, AirDate maxDate) {
        this.adapter.setCalendarData(calendarDays);
    }

    /* access modifiers changed from: protected */
    public boolean scrollToTargetDate(AirDate targetDate) {
        int position = this.adapter.getPositionOfDate(targetDate);
        if (position == -1) {
            return false;
        }
        this.recyclerView.smoothScrollToPosition(position);
        if (this.adapter.isPositionAfterReservation(targetDate)) {
            this.layoutManager.scrollToPositionWithOffset(position, this.paddingFromOverlappingReservation);
        }
        this.headerRow.setMonthText(targetDate, false);
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Ignore;
    }

    public void clearEditMode(Set<AirDate> dates) {
        this.adapter.clearEditMode(dates);
    }

    /* access modifiers changed from: protected */
    public void logScrollForward() {
        this.jitneyLogger.singleListingAgendaLoadMore(this.listingId);
    }

    /* access modifiers changed from: protected */
    public void showScrollLoaderIsLoading(boolean show, boolean isLoading) {
        this.adapter.showScrollLoaderIsLoading(show, isLoading);
    }
}
