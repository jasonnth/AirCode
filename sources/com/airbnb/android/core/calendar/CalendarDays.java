package com.airbnb.android.core.calendar;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.util.ArrayMap;
import android.util.SparseArray;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirMonth;
import com.airbnb.android.airdate.DayOfWeek;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.ParcelableUtils;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CalendarDays implements Parcelable {
    public static final Creator<CalendarDays> CREATOR = new Creator<CalendarDays>() {
        public CalendarDays createFromParcel(Parcel in) {
            return new CalendarDays(in);
        }

        public CalendarDays[] newArray(int size) {
            return new CalendarDays[size];
        }
    };
    private final ArrayMap<AirDate, CalendarDay> calendarDaysByDate;
    private final SparseArray<OnChangeListener> listeners;
    private final long listingId;
    private AirDate maxDate;
    private AirDate minDate;

    public interface OnChangeListener {
        void onChange(int i);
    }

    public CalendarDays(long listingId2) {
        this.listeners = new SparseArray<>();
        this.listingId = listingId2;
        this.calendarDaysByDate = new ArrayMap<>();
    }

    public CalendarDays(long listingId2, int initialCapacity) {
        this.listeners = new SparseArray<>();
        this.listingId = listingId2;
        this.calendarDaysByDate = new ArrayMap<>(initialCapacity);
    }

    protected CalendarDays(Parcel in) {
        this(in.readLong(), in.readInt());
        ParcelableUtils.readParcelableIntoMap(in, this.calendarDaysByDate, AirDate.class, CalendarDay.class);
        if (size() > 0) {
            this.minDate = (AirDate) AirDate.CREATOR.createFromParcel(in);
            this.maxDate = (AirDate) AirDate.CREATOR.createFromParcel(in);
        }
    }

    public boolean add(List<CalendarDay> calendarDays) {
        boolean anyChanges = false;
        for (CalendarDay day : calendarDays) {
            if (add(day)) {
                anyChanges = true;
            }
        }
        return anyChanges;
    }

    public boolean add(CalendarDay day) {
        boolean anyChanges = !CalendarDay.equals(get(day.getDate()), day);
        int wasSize = this.calendarDaysByDate.size();
        put(day);
        if (this.minDate == null || day.getDate().isBefore(this.minDate)) {
            this.minDate = day.getDate();
        }
        if (this.maxDate == null || day.getDate().isAfter(this.maxDate)) {
            this.maxDate = day.getDate();
        }
        notifyOnChanged(wasSize);
        return anyChanges;
    }

    public void remove(CalendarDay day) {
        int wasSize = this.calendarDaysByDate.size();
        this.calendarDaysByDate.remove(day.getDate());
        notifyOnChanged(wasSize);
    }

    public void toggle(CalendarDay calendarDay) {
        if (this.calendarDaysByDate.containsKey(calendarDay.getDate())) {
            remove(calendarDay);
        } else {
            add(calendarDay);
        }
    }

    public Set<AirDate> getDates() {
        return this.calendarDaysByDate.keySet();
    }

    public Collection<AirDate> getDays() {
        return this.calendarDaysByDate.keySet();
    }

    public Collection<CalendarDay> getCalendarDays() {
        return this.calendarDaysByDate.values();
    }

    public void clear() {
        int wasSize = this.calendarDaysByDate.size();
        this.calendarDaysByDate.clear();
        this.minDate = null;
        this.maxDate = null;
        notifyOnChanged(wasSize);
    }

    public void addChangeListener(OnChangeListener listener) {
        this.listeners.put(listener.hashCode(), listener);
    }

    public void removeChangeListener(OnChangeListener listener) {
        this.listeners.remove(listener.hashCode());
    }

    private void notifyOnChanged(int wasSize) {
        int currentSize = size();
        if (currentSize != wasSize) {
            for (int i = 0; i < this.listeners.size(); i++) {
                ((OnChangeListener) this.listeners.valueAt(i)).onChange(currentSize);
            }
        }
    }

    public void mergeIntoRange(CalendarDays newCalendarDays, AirDate firstDay, AirDate lastDay) {
        AirDate newFirstDay = (AirDate) Check.notNull(newCalendarDays.getMinDate());
        if (!((AirDate) Check.notNull(newCalendarDays.getMaxDate())).isBefore(firstDay) && !newFirstDay.isAfter(lastDay)) {
            AirDate date = newFirstDay;
            if (date.isBefore(firstDay)) {
                date = firstDay;
            }
            CalendarDay calendarDay = newCalendarDays.get(date);
            while (calendarDay != null && date.isSameDayOrBefore(lastDay)) {
                add(calendarDay);
                date = date.plusDays(1);
                calendarDay = newCalendarDays.get(date);
            }
        }
    }

    public CalendarDay get(AirDate date) {
        return (CalendarDay) this.calendarDaysByDate.get(date);
    }

    private void put(CalendarDay day) {
        this.calendarDaysByDate.put(day.getDate(), day);
    }

    public boolean has(AirDate date) {
        return this.calendarDaysByDate.containsKey(date);
    }

    public long getListingId() {
        return this.listingId;
    }

    public AirDate getMinDate() {
        return this.minDate;
    }

    public AirDate getMaxDate() {
        return this.maxDate;
    }

    public int size() {
        return this.calendarDaysByDate.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public AirMonth getFirstMonth() {
        AirDate firstDay = (AirDate) Check.notNull(getMinDate());
        AirMonth firstMonth = new AirMonth(firstDay);
        if (!firstDay.isSameDay(firstMonth.firstDayOfMonth())) {
            return firstMonth.plusMonths(1);
        }
        return firstMonth;
    }

    public AirMonth getLastMonth() {
        AirDate lastDay = (AirDate) Check.notNull(getMaxDate());
        AirMonth lastMonth = new AirMonth(lastDay);
        if (!lastDay.isSameDay(lastMonth.lastDayOfMonth())) {
            return lastMonth.plusMonths(-1);
        }
        return lastMonth;
    }

    public AirDate getFirstAvailableDateFrom(AirDate startDate) {
        for (AirDate date = startDate; date.isSameDayOrBefore(this.maxDate); date = date.plusDays(1)) {
            CalendarDay calendarDay = (CalendarDay) this.calendarDaysByDate.get(date);
            if (calendarDay != null && calendarDay.isAvailable()) {
                return date;
            }
        }
        return null;
    }

    public static String[] initDayOfWeekInitials(DayOfWeek firstDayOfWeek, Context context) {
        String[] dayOfWeekInitials = new String[7];
        AirDate someFirstDayOfWeek = AirDate.fromLastDayOfWeek(firstDayOfWeek);
        for (int i = 0; i < 7; i++) {
            dayOfWeekInitials[i] = someFirstDayOfWeek.plusDays(i).getShortDayOfWeek(context);
        }
        return dayOfWeekInitials;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.listingId);
        int size = this.calendarDaysByDate.size();
        dest.writeInt(size);
        ParcelableUtils.writeParcelableMap(dest, this.calendarDaysByDate);
        if (size > 0) {
            this.minDate.writeToParcel(dest, flags);
            this.maxDate.writeToParcel(dest, flags);
        }
    }
}
