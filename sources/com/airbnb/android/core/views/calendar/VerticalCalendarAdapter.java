package com.airbnb.android.core.views.calendar;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.DayOfWeek;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.analytics.AvailabilityCalendarJitneyLogger;
import com.airbnb.android.core.calendar.AvailabilityController;
import com.airbnb.android.core.views.calendar.CalendarDayModel.Type;
import com.airbnb.android.core.views.calendar.MonthView.MonthViewStyle;
import com.airbnb.android.core.views.calendar.MonthView.OnDayClickListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class VerticalCalendarAdapter extends Adapter<ViewHolder> implements OnDayClickListener {
    protected static final int MONTHS_IN_YEAR = 12;
    AvailabilityCalendarJitneyLogger availabilityCalendarJitneyLogger;
    private AvailabilityController availabilityController;
    private final ArrayList<CalendarMonthModel> calendarModels = new ArrayList<>();
    private final AirDate calendarStart;
    private final Context context;
    private final VerticalCalendarCallbacks controller;
    private final DateRangeModel dateRangeModel;
    private final AvailabilityController defaultAvailabilityController;
    private final Integer firstMonth;
    private final boolean isReadOnly;
    private final int monthCount;
    private final MonthViewStyle monthViewStyle;
    private boolean singleDayMode;

    public enum RangeLimitType {
        Start,
        End
    }

    public static class ViewHolder extends android.support.p002v7.widget.RecyclerView.ViewHolder {
        final MonthView monthView;

        public ViewHolder(View itemView, OnDayClickListener onDayClickListener) {
            super(itemView);
            this.monthView = (MonthView) itemView;
            this.monthView.setLayoutParams(new LayoutParams(-1, -1));
            this.monthView.setClickable(true);
            this.monthView.setOnDayClickListener(onDayClickListener);
        }
    }

    public VerticalCalendarAdapter(Context context2, VerticalCalendarCallbacks calendarCallbacks, AirDate calendarStartDate, AirDate calendarEnd, boolean isReadOnly2, MonthViewStyle monthViewStyle2) {
        ((CoreGraph) CoreApplication.instance(context2).component()).inject(this);
        this.calendarStart = calendarStartDate;
        this.firstMonth = Integer.valueOf(this.calendarStart.getMonthOfYear());
        this.dateRangeModel = new DateRangeModel();
        this.context = context2;
        this.isReadOnly = isReadOnly2;
        this.monthViewStyle = monthViewStyle2;
        this.controller = calendarCallbacks;
        this.monthCount = this.calendarStart.getMonthsUntil(calendarEnd);
        this.defaultAvailabilityController = new AvailabilityController(context2.getResources(), Collections.emptyList());
        setAvailabilityController(this.defaultAvailabilityController);
        refreshModels();
    }

    private void refreshModels() {
        this.calendarModels.clear();
        this.calendarModels.ensureCapacity(this.monthCount);
        for (int i = 0; i < this.monthCount; i++) {
            int month = getMonthForPosition(i);
            int year = getYearForPosition(i);
            if (i == 0) {
                AirDate today = AirDate.today();
                this.calendarModels.add(new CalendarMonthModel(year, month, Math.max(1, (today.getDayOfMonth() - today.plusWeeks(-1).getDaysFromDayOfWeek(DayOfWeek.Sunday)) - 7), this.defaultAvailabilityController));
            } else {
                this.calendarModels.add(new CalendarMonthModel(year, month, this.defaultAvailabilityController));
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MonthView monthView = (MonthView) LayoutInflater.from(this.context).inflate(C0716R.layout.month_view, viewGroup, false);
        monthView.setStyle(this.monthViewStyle);
        return new ViewHolder(monthView, this);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.monthView.bindMonthData((CalendarMonthModel) this.calendarModels.get(position));
    }

    private int getMonthForPosition(int position) {
        int unbound = this.firstMonth.intValue() + (position % 12);
        return unbound + -12 > 0 ? unbound - 12 : unbound;
    }

    private int getYearForPosition(int position) {
        return (this.firstMonth.intValue() + position) + -12 > 0 ? this.calendarStart.getYear() + 1 : this.calendarStart.getYear();
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getItemCount() {
        return this.monthCount;
    }

    public void enableSingleDayMode() {
        this.singleDayMode = true;
    }

    public void onDayClick(MonthView monthView, CalendarMonthModel monthModel, CalendarDayModel dayModel, int[] dayLocation) {
        if (!this.isReadOnly) {
            AirDate airDate = new AirDate(monthModel.getCurrentYear(), monthModel.getCurrentMonth(), dayModel.day);
            if (this.singleDayMode) {
                dayModel.type = Type.CheckIn;
            }
            boolean popUpShown = false;
            switch (dayModel.type) {
                case SelectedMiddleDayAvailable:
                case CheckIn:
                case SelectedCheckIn:
                    selectCheckIn(airDate);
                    break;
                case CheckOut:
                    this.dateRangeModel.setCheckOutDate(airDate);
                    this.controller.onEndDateClicked(airDate);
                    break;
                case SelectedCheckOut:
                    if (dayModel.unavailabilityType == null) {
                        selectCheckIn(airDate);
                        break;
                    }
                case SelectedMiddleDayUnavailable:
                case Unavailable:
                    popUpShown = this.controller.onUnavailableDateClicked(dayModel.unavailabilityType, airDate, dayLocation);
                    if (this.dateRangeModel.hasSetStartAndEnd() && popUpShown) {
                        this.dateRangeModel.reset();
                        break;
                    }
            }
            this.controller.onDateRangeSelected(this.dateRangeModel);
            trackDayClick(dayModel, popUpShown);
            propagateState(dayModel);
            notifyDataSetChanged();
        }
    }

    private void selectCheckIn(AirDate airDate) {
        this.dateRangeModel.reset();
        this.dateRangeModel.setCheckInDate(airDate);
        this.controller.onStartDateClicked(airDate);
    }

    private void trackDayClick(CalendarDayModel dayModel, boolean popUpShown) {
        if (this.dateRangeModel.getCheckInDate() == null || this.dateRangeModel.getCheckOutDate() != null) {
            this.availabilityCalendarJitneyLogger.trackUnavailableCheckInDateClicked(dayModel.unavailabilityType, popUpShown);
        } else {
            this.availabilityCalendarJitneyLogger.trackUnavailableCheckOutDateClicked(dayModel.unavailabilityType, popUpShown);
        }
    }

    private void propagateState(CalendarDayModel previousSelection) {
        Iterator it = this.calendarModels.iterator();
        while (it.hasNext()) {
            ((CalendarMonthModel) it.next()).updateSelectedState(this.dateRangeModel, previousSelection);
        }
    }

    public void setSelectedState(AirDate startDate, AirDate endDate) {
        this.dateRangeModel.setCheckInDate(startDate);
        this.dateRangeModel.setCheckOutDate(endDate);
        if (this.controller != null) {
            this.controller.onDateRangeSelected(this.dateRangeModel);
        }
        propagateState(null);
        notifyDataSetChanged();
    }

    public void setAvailabilityController(AvailabilityController availabilityController2) {
        this.availabilityController = availabilityController2;
        Iterator it = this.calendarModels.iterator();
        while (it.hasNext()) {
            ((CalendarMonthModel) it.next()).setAvailabilityController(availabilityController2);
        }
        propagateState(null);
        notifyDataSetChanged();
    }
}
