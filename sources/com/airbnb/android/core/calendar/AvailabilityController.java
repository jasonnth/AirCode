package com.airbnb.android.core.calendar;

import android.content.res.Resources;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.DayOfWeek;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.AvailabilityCondition;
import com.airbnb.android.core.models.AvailabilityConditionRange;
import com.airbnb.android.core.models.CalendarMonth;
import com.airbnb.android.core.models.SimpleCalendarDay;
import com.airbnb.android.core.views.calendar.VerticalCalendarAdapter.RangeLimitType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvailabilityController {
    private final Resources resources;
    private final List<AirDate> unavailableDates = new ArrayList();
    private final Map<String, List<AvailabilityConditionRange>> validity = new HashMap();

    public enum UnavailabilityType {
        DoesntSatisfyMinNights,
        DoesntSatisfyMaxNights,
        CantSatisfyMinNights,
        ClosedToArrival,
        ClosedToDeparture,
        SpecificCheckInDate,
        UnavailableForCheckIn,
        UnavailableForCheckOut,
        ContainsUnavailableDates
    }

    public AvailabilityController(Resources resources2, List<CalendarMonth> availability) {
        this.resources = resources2;
        initAvailableRanges(availability);
    }

    public String getUnavailabilityMessageIfNotValid(AirDate date, RangeLimitType type, String hostName) {
        return getMessageForUnavailability(getUnavailabilityForDay(date, type), date, hostName);
    }

    public String getUnavailabilityMessageIfNotValid(AirDate startDate, AirDate endDate, String hostName) {
        return getMessageForUnavailability(getUnavailabilityForRange(startDate, endDate), startDate, hostName);
    }

    public String getMessageForUnavailability(UnavailabilityType unavailabilityType, AirDate startDate, String hostName) {
        if (unavailabilityType == null) {
            return null;
        }
        AvailabilityCondition condition = getTargetCondition(startDate);
        if (condition == null || hostName == null) {
            return null;
        }
        switch (unavailabilityType) {
            case CantSatisfyMinNights:
            case DoesntSatisfyMinNights:
                return this.resources.getQuantityString(C0716R.plurals.calendar_host_less_than_min_nights, condition.getMinNights(), new Object[]{hostName, Integer.valueOf(condition.getMinNights())});
            case DoesntSatisfyMaxNights:
                return this.resources.getQuantityString(C0716R.plurals.calendar_host_more_than_max_nights, condition.getMaxNights(), new Object[]{hostName, Integer.valueOf(condition.getMaxNights())});
            case UnavailableForCheckOut:
            case ClosedToDeparture:
                return this.resources.getString(C0716R.string.calendar_host_blocked_check_out_day, new Object[]{hostName});
            case UnavailableForCheckIn:
            case ClosedToArrival:
                return this.resources.getString(C0716R.string.calendar_host_blocked_check_in_day, new Object[]{hostName});
            case SpecificCheckInDate:
                DayOfWeek allowedDay = getAllowedDayForCheckIn(condition);
                return this.resources.getString(C0716R.string.calendar_host_required_check_in_day, new Object[]{hostName, this.resources.getString(allowedDay.getLocalizedDayOfWeek())});
            case ContainsUnavailableDates:
                return this.resources.getString(C0716R.string.calendar_contains_unavailable_day);
            default:
                BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Unknown error for UnavailabilityType " + unavailabilityType));
                return null;
        }
    }

    public UnavailabilityType getUnavailabilityForDay(AirDate date, RangeLimitType type) {
        AvailabilityCondition condition = getTargetCondition(date);
        if (condition == null || type == null) {
            return null;
        }
        if (type == RangeLimitType.Start) {
            return getCheckInUnavailabilityIfNotValid(date, condition);
        }
        if (type == RangeLimitType.End) {
            return getCheckOutUnavailabilityIfNotValid(date, condition);
        }
        return null;
    }

    private UnavailabilityType getCheckInUnavailabilityIfNotValid(AirDate checkInDate, AvailabilityCondition conditionForDay) {
        if (conditionForDay.isClosedToArrival()) {
            return UnavailabilityType.ClosedToArrival;
        }
        if (conditionForDay.getStartDayOfWeek() != null && !checkInDate.getDayOfWeek().equals(getAllowedDayForCheckIn(conditionForDay))) {
            return UnavailabilityType.SpecificCheckInDate;
        }
        if (this.unavailableDates.contains(checkInDate)) {
            return UnavailabilityType.UnavailableForCheckIn;
        }
        if (conditionForDay.getMinNights() <= 1 || !hasUnavailableDayInRange(checkInDate, checkInDate.plusDays(conditionForDay.getMinNights()))) {
            return null;
        }
        return UnavailabilityType.CantSatisfyMinNights;
    }

    private UnavailabilityType getCheckOutUnavailabilityIfNotValid(AirDate selectedCheckOutDate, AvailabilityCondition conditionForDay) {
        if (conditionForDay.isClosedToDeparture()) {
            return UnavailabilityType.ClosedToDeparture;
        }
        if (!this.unavailableDates.contains(selectedCheckOutDate) || !this.unavailableDates.contains(selectedCheckOutDate.plusDays(-1))) {
            return null;
        }
        return UnavailabilityType.UnavailableForCheckOut;
    }

    public UnavailabilityType getUnavailabilityForRange(AirDate checkInDate, AirDate checkOutDate) {
        if (hasUnavailableDayInRange(checkInDate, checkOutDate)) {
            return UnavailabilityType.ContainsUnavailableDates;
        }
        UnavailabilityType checkInUnavailability = getUnavailabilityForDay(checkInDate, RangeLimitType.Start);
        if (checkInUnavailability != null) {
            return checkInUnavailability;
        }
        UnavailabilityType checkOutUnavailability = getUnavailabilityForDay(checkOutDate, RangeLimitType.End);
        if (checkOutUnavailability != null) {
            return checkOutUnavailability;
        }
        AvailabilityCondition condition = getTargetCondition(checkInDate);
        if (condition == null) {
            return null;
        }
        int numberOfNights = checkInDate.getDaysUntil(checkOutDate);
        if (!meetsMinNightRules(numberOfNights, condition)) {
            return UnavailabilityType.DoesntSatisfyMinNights;
        }
        if (!meetsMaxNightRule(numberOfNights, condition)) {
            return UnavailabilityType.DoesntSatisfyMaxNights;
        }
        return null;
    }

    private boolean meetsMinNightRules(int numberOfNights, AvailabilityCondition condition) {
        if (condition.getMinNights() != 0) {
            if (condition.getMinNights() <= numberOfNights) {
                return true;
            }
            return false;
        } else if (numberOfNights < 1) {
            return false;
        } else {
            return true;
        }
    }

    private boolean meetsMaxNightRule(int numberOfNights, AvailabilityCondition condition) {
        return condition.getMaxNights() == 0 || condition.getMaxNights() >= numberOfNights;
    }

    public boolean hasUnavailableDayInRange(AirDate checkInDate, AirDate checkOutDate) {
        if (checkInDate == null || checkOutDate == null) {
            return false;
        }
        for (AirDate date : this.unavailableDates) {
            if (date.equals(checkInDate) || (date.isAfter(checkInDate) && date.isBefore(checkOutDate))) {
                return true;
            }
        }
        return false;
    }

    private AvailabilityCondition getTargetCondition(AirDate targetDate) {
        if (targetDate == null) {
            return null;
        }
        String key = getMonthOfYearHashKey(targetDate);
        if (!this.validity.containsKey(key)) {
            return null;
        }
        for (AvailabilityConditionRange range : (List) this.validity.get(key)) {
            if (targetDate.isSameDayOrAfter(range.getStartDate()) && targetDate.isSameDayOrBefore(range.getEndDate())) {
                return range.getConditions();
            }
        }
        return null;
    }

    private void initAvailableRanges(List<CalendarMonth> availability) {
        if (!availability.isEmpty()) {
            AirDate today = AirDate.today();
            for (CalendarMonth calendarMonth : availability) {
                for (SimpleCalendarDay day : calendarMonth.getDays()) {
                    if (!day.isAvailable() && !this.unavailableDates.contains(day.getDate()) && day.getDate().isSameDayOrAfter(today)) {
                        this.unavailableDates.add(day.getDate());
                    }
                }
                for (AvailabilityConditionRange range : calendarMonth.getConditionRanges()) {
                    String key = getMonthOfYearHashKey(range.getStartDate());
                    if (!this.validity.containsKey(key)) {
                        this.validity.put(key, new ArrayList());
                    }
                    ((List) this.validity.get(key)).add(range);
                }
            }
        }
    }

    private DayOfWeek getAllowedDayForCheckIn(AvailabilityCondition condition) {
        return condition.getStartDayOfWeek().intValue() == 0 ? DayOfWeek.Sunday : DayOfWeek.getDayOfWeek(condition.getStartDayOfWeek().intValue());
    }

    private String getMonthOfYearHashKey(AirDate date) {
        return String.valueOf(date.getYear()) + String.valueOf(date.getMonthOfYear());
    }
}
