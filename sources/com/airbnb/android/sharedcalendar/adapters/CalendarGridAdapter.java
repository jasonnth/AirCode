package com.airbnb.android.sharedcalendar.adapters;

import android.content.Context;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirMonth;
import com.airbnb.android.airdate.DayOfWeek;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.sharedcalendar.C1576R;
import com.airbnb.android.sharedcalendar.listeners.CalendarGridTapListener;
import com.airbnb.android.sharedcalendar.listeners.OnboardingOverlayListener;
import com.airbnb.android.sharedcalendar.models.CalendarGridDayModel;
import com.airbnb.android.sharedcalendar.viewmodels.CalendarGridMonthEpoxyModel;
import com.airbnb.epoxy.EpoxyModel;
import java.util.Set;

public class CalendarGridAdapter extends SingleCalendarBaseAdapter {
    private final CalendarGridTapListener calendarGridTapListener = new CalendarGridTapListener() {
        public void onDayClick(CalendarGridDayModel dayModel) {
            CalendarGridAdapter.super.onDayClick(dayModel.getCalendarDay());
            int positionOfMonth = CalendarGridAdapter.this.getPositionOfMonth(dayModel.getCalendarDay());
            if (positionOfMonth != -1) {
                ((CalendarGridMonthEpoxyModel) CalendarGridAdapter.this.models.get(positionOfMonth)).selectedDates(CalendarGridAdapter.this.getSelectedDates());
                CalendarGridAdapter.this.notifyItemChanged(positionOfMonth);
            }
        }

        public void onReservationClick(String reservationCode) {
            CalendarGridAdapter.this.goToReservation(reservationCode);
        }
    };
    private final String[] dayOfWeekInitials;
    private final DayOfWeek firstDayOfWeek = AirDate.getDayOfWeekFromCalendar();
    private final Mode mode;
    private OnboardingOverlayListener onboardingOverlayListener;

    public enum Mode {
        ListYourSpace,
        SingleCanlendarMonth
    }

    public CalendarGridAdapter(Context context, Mode mode2) {
        this.dayOfWeekInitials = CalendarDays.initDayOfWeekInitials(this.firstDayOfWeek, context);
        this.mode = mode2;
    }

    public void setOnboardingOverlayListener(OnboardingOverlayListener onboardingOverlayListener2) {
        this.onboardingOverlayListener = onboardingOverlayListener2;
    }

    public void setCalendarData(CalendarDays calendarDays, AirDate updateStart, AirDate updateEnd) {
        addHeaderIfNeeded(C1576R.string.lys_airbnb_calendar_title, C1576R.string.lys_airbnb_calendar_subtitle, this.mode == Mode.ListYourSpace);
        AirMonth firstUpdatedMonth = new AirMonth(updateStart);
        AirMonth lastUpdatedMonth = new AirMonth(updateEnd);
        AirMonth firstMonthWithData = calendarDays.getFirstMonth();
        AirMonth lastMonthWithData = calendarDays.getLastMonth();
        AirMonth lastMonth = AirMonth.getEarlierMonth(lastMonthWithData, lastUpdatedMonth);
        for (AirMonth month = AirMonth.getLaterMonth(firstMonthWithData, firstUpdatedMonth); !month.isAfter(lastMonth); month = month.plusMonths(1)) {
            addOrUpdateMonth(calendarDays, month);
            updateLastLoadedDate(month.lastDayOfMonth());
        }
    }

    public int getPositionOfMonth(AirMonth month) {
        return positionForModelId(getIdForDate(month), false);
    }

    public int getPositionOfMonth(CalendarDay calendarDay) {
        return getPositionOfMonth(new AirMonth(calendarDay.getDate()));
    }

    private void addHeaderIfNeeded(int titleRes, int captionRes, boolean shoudlShow) {
        this.models.add(new DocumentMarqueeEpoxyModel_().titleRes(titleRes).captionRes(captionRes).show(shoudlShow));
    }

    private void addOrUpdateMonth(CalendarDays calendarDays, AirMonth month) {
        long currentId = getIdForDate(month);
        int currentPosition = positionForModelId(currentId, true);
        if (currentPosition < 0 || currentPosition >= this.models.size()) {
            this.models.add(makeMonth(calendarDays, month).mo11716id(currentId));
            notifyItemChanged(this.models.size() - 1);
        } else if (((EpoxyModel) this.models.get(currentPosition)).mo11715id() > currentId) {
            this.models.add(currentPosition, makeMonth(calendarDays, month).mo11716id(currentId));
            notifyItemInserted(currentPosition);
        } else {
            notifyItemChanged(currentPosition);
        }
    }

    private long getIdForDate(AirMonth month) {
        return (long) ((month.getYear() * 100) + month.getMonth());
    }

    private int positionForModelId(long id, boolean returnLastPositionIfNotFound) {
        int lastPosition = this.models.size();
        for (int i = 0; i < lastPosition; i++) {
            if (((EpoxyModel) this.models.get(i)).mo11715id() >= id) {
                return i;
            }
        }
        if (!returnLastPositionIfNotFound) {
            lastPosition = -1;
        }
        return lastPosition;
    }

    public void clearEditMode(Set<AirDate> set) {
        notifyItemRangeChanged(0, this.models.size());
    }

    private CalendarGridMonthEpoxyModel makeMonth(CalendarDays calendarDays, AirMonth month) {
        CalendarGridMonthEpoxyModel model = new CalendarGridMonthEpoxyModel().calendarDays(calendarDays).month(month).selectedDates(getSelectedDates()).firstDayOfWeek(this.firstDayOfWeek).dayOfWeekInitials(this.dayOfWeekInitials).clickListener(this.calendarGridTapListener);
        if (this.onboardingOverlayListener != null && month.equals(calendarDays.getFirstMonth().plusMonths(1))) {
            model.onboardingOverlayListener(this.onboardingOverlayListener);
        }
        return model;
    }
}
