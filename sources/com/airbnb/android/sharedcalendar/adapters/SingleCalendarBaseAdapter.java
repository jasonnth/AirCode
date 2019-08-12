package com.airbnb.android.sharedcalendar.adapters;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays.OnChangeListener;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.sharedcalendar.listeners.InfiniteScrollListener;
import com.airbnb.android.sharedcalendar.listeners.OnGridDayClickListener;
import com.airbnb.android.sharedcalendar.listeners.SingleCalendarListener;
import com.airbnb.epoxy.EpoxyViewHolder;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class SingleCalendarBaseAdapter extends AirEpoxyAdapter {
    private InfiniteScrollListener infiniteScrollListener;
    private boolean isLoading;
    private AirDate lastLoadedDate;
    private final LoadingRowEpoxyModel loadingRowEpoxyModel = new LoadingRowEpoxyModel_().m5110id(Long.MAX_VALUE);
    private OnGridDayClickListener onGridDayClickListener;
    private final OnChangeListener selectedDaysChangedListener = new OnChangeListener() {
        public void onChange(int currentSize) {
            if (currentSize == 0 || currentSize == 1) {
                SingleCalendarBaseAdapter.this.notifyItemRangeChanged(0, SingleCalendarBaseAdapter.this.models.size());
            }
        }
    };
    protected SingleCalendarListener singleCalendarListener;

    public void setInfiniteScrollListener(InfiniteScrollListener infiniteScrollListener2) {
        this.infiniteScrollListener = infiniteScrollListener2;
    }

    public void setOnGridDayClickListener(OnGridDayClickListener onGridDayClickListener2) {
        this.onGridDayClickListener = onGridDayClickListener2;
    }

    public void setSingleCalendarListener(SingleCalendarListener singleCalendarListener2) {
        if (this.singleCalendarListener != null && this.singleCalendarListener != singleCalendarListener2) {
            this.singleCalendarListener.removeSelectedDaysChangeListener(this.selectedDaysChangedListener);
        } else if (singleCalendarListener2 != null) {
            singleCalendarListener2.addSelectedDaysChangeListener(this.selectedDaysChangedListener);
        }
        this.singleCalendarListener = singleCalendarListener2;
    }

    /* access modifiers changed from: protected */
    public Set<AirDate> getSelectedDates() {
        if (this.singleCalendarListener != null) {
            return this.singleCalendarListener.getSelectedDates();
        }
        return Collections.emptySet();
    }

    /* access modifiers changed from: protected */
    public void onDayClick(CalendarDay calendarDay) {
        if (this.singleCalendarListener != null) {
            this.singleCalendarListener.onDayClick(calendarDay);
        }
        if (this.onGridDayClickListener != null) {
            this.onGridDayClickListener.onDayClicked(calendarDay);
        }
    }

    /* access modifiers changed from: protected */
    public void goToReservation(String reservationCode) {
        if (this.singleCalendarListener != null) {
            this.singleCalendarListener.goToReservation(reservationCode);
        }
    }

    /* access modifiers changed from: protected */
    public void goToMessageThread(long threadId) {
        if (this.singleCalendarListener != null) {
            this.singleCalendarListener.goToMessageThread(threadId);
        }
    }

    public void onBindViewHolder(EpoxyViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (!this.isLoading && this.infiniteScrollListener != null && (holder.getModel() instanceof LoadingRowEpoxyModel)) {
            this.isLoading = true;
            this.infiniteScrollListener.scrollForward(this.lastLoadedDate);
        }
    }

    /* access modifiers changed from: protected */
    public void updateLastLoadedDate(AirDate date) {
        if (this.lastLoadedDate == null || date.isAfter(this.lastLoadedDate)) {
            this.lastLoadedDate = date;
        }
    }

    public void showScrollLoaderIsLoading(boolean show, boolean isLoading2) {
        int loaderIndex = findLoaderIndex();
        if (show && loaderIndex < 0) {
            this.models.add(this.loadingRowEpoxyModel);
            notifyItemChanged(this.models.size() - 1);
        } else if (!show && loaderIndex > -1) {
            this.models.remove(loaderIndex);
            notifyItemRemoved(loaderIndex);
        }
        this.isLoading = isLoading2;
    }

    private int findLoaderIndex() {
        for (int i = 0; i < this.models.size(); i++) {
            if (this.models.get(i) == this.loadingRowEpoxyModel) {
                return i;
            }
        }
        return -1;
    }
}
