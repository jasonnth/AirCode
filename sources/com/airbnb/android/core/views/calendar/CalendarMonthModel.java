package com.airbnb.android.core.views.calendar;

import android.content.Context;
import android.text.format.DateUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.AvailabilityController;
import com.airbnb.android.core.calendar.AvailabilityController.UnavailabilityType;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.core.views.calendar.CalendarDayModel.Type;
import com.airbnb.android.core.views.calendar.VerticalCalendarAdapter.RangeLimitType;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableInstant;

public class CalendarMonthModel {
    private AvailabilityController availabilityController;
    private final Calendar calendar;
    private final List<CalendarDayModel> days;
    private final int month;
    private DateRangeModel selectedDateRange;
    private final int startDay;
    private final int startDayOffset;
    private final int year;

    public CalendarMonthModel(int year2, int month2, int startDay2, AvailabilityController availabilityController2) {
        this.calendar = Calendar.getInstance(Locale.US);
        this.year = year2;
        this.month = month2;
        this.startDay = startDay2;
        this.availabilityController = availabilityController2;
        initCalendar();
        this.startDayOffset = findDayOffset();
        int numDaysInMonth = AirDate.getFirstDayOfMonth(year2, month2).getDaysInMonth();
        List<CalendarDayModel> dayModels = new ArrayList<>(numDaysInMonth);
        AirDate today = AirDate.today();
        for (int i = startDay2; i <= numDaysInMonth; i++) {
            CalendarDayModel day = new CalendarDayModel(i);
            day.isToday = today.getYear() == year2 && today.getMonthOfYear() == month2 && day.day == today.getDayOfMonth();
            dayModels.add(day);
        }
        this.days = Collections.unmodifiableList(dayModels);
        updateDayModels(null);
    }

    public CalendarMonthModel(int year2, int month2, AvailabilityController availabilityController2) {
        this(year2, month2, 1, availabilityController2);
    }

    private void initCalendar() {
        this.calendar.set(2, this.month - 1);
        this.calendar.set(1, this.year);
        this.calendar.set(5, this.startDay);
    }

    public void setAvailabilityController(AvailabilityController availabilityController2) {
        this.availabilityController = availabilityController2;
        updateDayModels(null);
    }

    public void updateSelectedState(DateRangeModel selectedDateRange2, CalendarDayModel previousSelection) {
        this.selectedDateRange = selectedDateRange2;
        updateDayModels(previousSelection);
    }

    private void updateDayModels(CalendarDayModel previousSelection) {
        AirDate today = AirDate.today();
        MutableDateTime dateTime = DateHelper.forDate(this.year, this.month, ((CalendarDayModel) this.days.get(0)).day);
        AirDate airDate = new AirDate(this.year, this.month, ((CalendarDayModel) this.days.get(0)).day);
        for (CalendarDayModel dayModel : this.days) {
            dayModel.reset();
            if (airDate.isBefore(today)) {
                dayModel.type = Type.Past;
            }
            if (dayModel.type == null && this.selectedDateRange != null) {
                if (airDate.equals(this.selectedDateRange.getCheckInDate())) {
                    dayModel.type = Type.SelectedCheckIn;
                } else if (airDate.equals(this.selectedDateRange.getCheckOutDate())) {
                    dayModel.type = Type.SelectedCheckOut;
                    dayModel.unavailabilityType = this.availabilityController.getUnavailabilityForDay(airDate, RangeLimitType.Start);
                } else if (this.selectedDateRange.getDaysInBetween() != null && this.selectedDateRange.getDaysInBetween().contains((ReadableInstant) dateTime)) {
                    updateDayModelForDayWithinSelection(dayModel, airDate);
                }
            }
            if (dayModel.type == null) {
                if (this.selectedDateRange == null || this.selectedDateRange.hasntSetStartOrEnd()) {
                    updateDayModelForNoRange(dayModel, airDate);
                } else if (this.selectedDateRange.hasSetOnlyStart()) {
                    updateDayModelWhenOnlyStartSet(dayModel, airDate);
                } else if (this.selectedDateRange.hasSetStartAndEnd()) {
                    updateDayModelWhenStartAndEndSet(dayModel, airDate);
                }
            }
            if (dayModel.type == null) {
                dayModel.type = Type.CheckIn;
            }
            if (dayModel == previousSelection && dayModel.type == Type.Unavailable) {
                UnavailabilityType type = dayModel.unavailabilityType;
                if (type == UnavailabilityType.CantSatisfyMinNights || type == UnavailabilityType.DoesntSatisfyMinNights) {
                    dayModel.type = Type.SelectedUnavailable;
                }
            }
            dateTime.addDays(1);
            airDate = airDate.plusDays(1);
        }
    }

    private void updateDayModelForDayWithinSelection(CalendarDayModel dayModel, AirDate airDate) {
        Check.notNull(this.selectedDateRange);
        UnavailabilityType checkInUnavailability = this.availabilityController.getUnavailabilityForDay(airDate, RangeLimitType.Start);
        if (checkInUnavailability == null) {
            dayModel.type = Type.SelectedMiddleDayAvailable;
            return;
        }
        dayModel.type = Type.SelectedMiddleDayUnavailable;
        dayModel.unavailabilityType = checkInUnavailability;
    }

    private void updateDayModelForNoRange(CalendarDayModel dayModel, AirDate airDate) {
        UnavailabilityType checkInUnavailability = this.availabilityController.getUnavailabilityForDay(airDate, RangeLimitType.Start);
        if (checkInUnavailability == null) {
            dayModel.type = Type.CheckIn;
            return;
        }
        dayModel.type = Type.Unavailable;
        dayModel.unavailabilityType = checkInUnavailability;
    }

    private void updateDayModelWhenOnlyStartSet(CalendarDayModel dayModel, AirDate airDate) {
        Check.notNull(this.selectedDateRange);
        Check.notNull(this.selectedDateRange.getCheckInDate());
        if (airDate.isBefore(this.selectedDateRange.getCheckInDate())) {
            UnavailabilityType checkInUnavailability = this.availabilityController.getUnavailabilityForDay(airDate, RangeLimitType.Start);
            if (checkInUnavailability == null) {
                dayModel.type = Type.CheckIn;
                return;
            }
            dayModel.type = Type.Unavailable;
            dayModel.unavailabilityType = checkInUnavailability;
        } else if (airDate.equals(this.selectedDateRange.getCheckInDate())) {
            dayModel.type = Type.CheckIn;
        } else if (this.availabilityController.getUnavailabilityForRange(this.selectedDateRange.getCheckInDate(), airDate) == null) {
            dayModel.type = Type.CheckOut;
        } else {
            UnavailabilityType checkOutUnavailability = this.availabilityController.getUnavailabilityForDay(airDate, RangeLimitType.End);
            if (checkOutUnavailability == null) {
                checkOutUnavailability = this.availabilityController.getUnavailabilityForRange(this.selectedDateRange.getCheckInDate(), airDate);
            }
            if (checkOutUnavailability == null) {
                dayModel.type = Type.CheckOut;
                return;
            }
            dayModel.type = Type.Unavailable;
            dayModel.unavailabilityType = checkOutUnavailability;
        }
    }

    private void updateDayModelWhenStartAndEndSet(CalendarDayModel dayModel, AirDate airDate) {
        Check.notNull(this.selectedDateRange);
        Check.notNull(this.selectedDateRange.getCheckInDate());
        Check.notNull(this.selectedDateRange.getCheckOutDate());
        UnavailabilityType checkInUnavailability = this.availabilityController.getUnavailabilityForDay(airDate, RangeLimitType.Start);
        if (checkInUnavailability == null || checkInUnavailability == UnavailabilityType.ContainsUnavailableDates) {
            dayModel.type = Type.CheckIn;
            return;
        }
        dayModel.type = Type.Unavailable;
        dayModel.unavailabilityType = checkInUnavailability;
    }

    public int getStartDayOffset() {
        return this.startDayOffset;
    }

    public int getStartDay() {
        return this.startDay;
    }

    public int getCurrentYear() {
        return this.year;
    }

    public int getCurrentMonth() {
        return this.month;
    }

    public List<CalendarDayModel> getDays() {
        return this.days;
    }

    public int getNumberDaysInMonth() {
        return this.days.size();
    }

    public boolean hasSelectedStartAndEnd() {
        return this.selectedDateRange != null && this.selectedDateRange.hasSetStartAndEnd();
    }

    public boolean doesSelectionStartInPreviousMonths() {
        if (!hasSelectedStartAndEnd() || this.days.isEmpty() || !((CalendarDayModel) this.days.get(0)).isSelectedMiddleOrCheckOut()) {
            return false;
        }
        return true;
    }

    public boolean doesSelectionEndInUpcomingMonths() {
        if (!hasSelectedStartAndEnd() || this.days.isEmpty()) {
            return false;
        }
        return ((CalendarDayModel) this.days.get(this.days.size() - 1)).isSelectedCheckInOrMiddle();
    }

    public CalendarDayModel getDayModel(int dayOfMonth) {
        if (dayOfMonth <= 0 || dayOfMonth > getNumberDaysInMonth()) {
            return null;
        }
        return (CalendarDayModel) getDays().get(dayOfMonth - 1);
    }

    /* access modifiers changed from: protected */
    public String getMonthString(Context context, boolean showYear) {
        int flags = 48;
        if (showYear) {
            flags = 48 | 4;
        }
        return TextUtil.titleCase(DateUtils.formatDateTime(context, this.calendar.getTimeInMillis(), flags).toLowerCase());
    }

    private int findDayOffset() {
        int dayOfWeekStart = this.calendar.get(7);
        int weekStart = this.calendar.getFirstDayOfWeek();
        if (dayOfWeekStart < weekStart) {
            dayOfWeekStart += 7;
        }
        return dayOfWeekStart - weekStart;
    }
}
