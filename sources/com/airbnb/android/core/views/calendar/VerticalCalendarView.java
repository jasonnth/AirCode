package com.airbnb.android.core.views.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.UnboundedViewPool;
import com.airbnb.android.core.views.calendar.MonthView.MonthViewStyle;

public class VerticalCalendarView extends RecyclerView {
    protected VerticalCalendarAdapter adapter;
    private VerticalCalendarCallbacks calendarCallbacks;
    private boolean isReadOnly;
    private MonthViewStyle monthViewStyle;

    public VerticalCalendarView(Context context) {
        this(context, null);
    }

    public VerticalCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalCalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            init(context, attrs);
        }
    }

    public void init(Context paramContext, AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0716R.styleable.VerticalCalendar, 0, 0);
        this.isReadOnly = a.getBoolean(C0716R.styleable.VerticalCalendar_isReadOnly, false);
        a.recycle();
        setLayoutManager(new LinearLayoutManager(paramContext));
        setRecycledViewPool(new UnboundedViewPool());
        setUpListView();
    }

    public void setupCalendar(VerticalCalendarCallbacks callbacks, MonthViewStyle monthViewStyle2) {
        this.calendarCallbacks = callbacks;
        setUpAdapter(monthViewStyle2);
    }

    private void setUpAdapter(MonthViewStyle monthViewStyle2) {
        if (this.monthViewStyle != monthViewStyle2) {
            if (this.adapter == null || this.monthViewStyle != monthViewStyle2) {
                this.monthViewStyle = monthViewStyle2;
                AirDate calendarStart = AirDate.today();
                this.adapter = new VerticalCalendarAdapter(getContext(), this.calendarCallbacks, calendarStart, calendarStart.plusYears(1), this.isReadOnly, monthViewStyle2);
            }
            setAdapter(this.adapter);
        }
    }

    private void setUpListView() {
        setVerticalScrollBarEnabled(false);
        setFadingEdgeLength(0);
    }

    public void setSelectedState(AirDate startDate, AirDate endDate, boolean scroll) {
        this.adapter.setSelectedState(startDate, endDate);
        if (scroll) {
            scrollToSelectedMonth(startDate);
        }
    }

    public void scrollToSelectedMonth(AirDate startDate) {
        if (startDate != null) {
            ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(startDate.getMonthOfYear() - AirDate.today().getMonthOfYear(), 0);
        }
    }

    public void enableSingleDayMode() {
        this.adapter.enableSingleDayMode();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() || motionEvent.getAction() != 2) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }
}
