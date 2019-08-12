package com.airbnb.android.hostcalendar.adapters;

import android.content.Context;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.hostcalendar.viewmodels.CalendarDetailDayRowEpoxyModel;
import com.airbnb.android.hostcalendar.viewmodels.CalendarDetailMonthRowEpoxyModel;
import com.airbnb.android.hostcalendar.viewmodels.CalendarDetailReservationRowEpoxyModel;
import com.airbnb.android.hostcalendar.views.CalendarDetailDayRow;
import com.airbnb.android.hostcalendar.views.CalendarDetailDayRow.CalendarDetailDayClickListener;
import com.airbnb.android.hostcalendar.views.CalendarDetailReservationRow;
import com.airbnb.android.hostcalendar.views.CalendarDetailReservationRow.CalendarDetailReservationClickListener;
import com.airbnb.android.sharedcalendar.adapters.SingleCalendarBaseAdapter;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import java.util.Collection;
import java.util.Set;

public class CalendarDetailAdapter extends SingleCalendarBaseAdapter {
    private static final int MONTH_ROW_ID_OFFSET = 1;
    private static final int RESERVATION_ROW_ID_OFFSET = 2;
    private final CalendarRule calendarRule;
    CalendarJitneyLogger jitneyLogger;
    private final CalendarDetailDayClickListener onClickDayListener = CalendarDetailAdapter$$Lambda$1.lambdaFactory$(this);
    private final CalendarDetailReservationClickListener onClickReservationListener = new CalendarDetailReservationClickListener() {
        public void onReservationClick(CalendarDetailReservationRow row) {
            CalendarDetailAdapter.this.goToReservation(row.getConfirmationCode());
        }

        public void onMessageClick(CalendarDetailReservationRow row) {
            CalendarDetailAdapter.this.goToMessageThread(row.getThreadId());
        }
    };
    private final AirDate today;

    public interface CalendarDetailRow {
        AirDate getDateForScrolling();
    }

    public CalendarDetailAdapter(Context context, long listingId, CalendarRule calendarRule2) {
        ((HostCalendarGraph) CoreApplication.instance(context).component()).inject(this);
        this.today = AirDate.today();
        this.calendarRule = calendarRule2;
    }

    static /* synthetic */ void lambda$new$0(CalendarDetailAdapter calendarDetailAdapter, CalendarDetailDayRow row) {
        calendarDetailAdapter.setSelected(row.getCalendarDay().getDate(), !row.isSelected());
        calendarDetailAdapter.onDayClick(row.getCalendarDay());
    }

    public void setCalendarData(CalendarDays calendarDays) {
        this.models.clear();
        AirDate day = (AirDate) Check.notNull(calendarDays.getMinDate());
        CalendarDay calendarDay = calendarDays.get(day);
        while (calendarDay != null) {
            Reservation reservation = calendarDay.getReservation();
            if (reservation == null || reservation.isExpired()) {
                this.models.add(makeDayRow(calendarDay));
                day = day.plusDays(1);
                if (day.getDayOfMonth() == 1) {
                    this.models.add(makeMonthRow(day));
                }
            } else {
                day = reservation.getEndDate();
                this.models.add(makeReservationRow(reservation, isDayNotReservationButBusy(day, calendarDays)));
            }
            updateLastLoadedDate(day.plusDays(-1));
            calendarDay = calendarDays.get(day);
        }
        notifyDataSetChanged();
    }

    private boolean isDayNotReservationButBusy(AirDate date, CalendarDays calendarDays) {
        CalendarDay calendarDay = calendarDays.get(date);
        return calendarDay != null && calendarDay.getReservation() == null && !calendarDay.isAvailable();
    }

    public int getPositionOfDate(AirDate date) {
        long id = getIdForDate(date);
        int lastPosition = this.models.size() - 1;
        for (int i = 0; i < lastPosition; i++) {
            if (this.models.get(i) instanceof CalendarDetailReservationRowEpoxyModel) {
                if (((CalendarDetailReservationRowEpoxyModel) this.models.get(i)).overlaps(date)) {
                    return i;
                }
            } else if (((EpoxyModel) this.models.get(i)).mo11715id() == id) {
                return i;
            }
        }
        return -1;
    }

    public boolean isPositionAfterReservation(AirDate date) {
        int position = getPositionOfDate(date);
        return position > 0 && (this.models.get(position + -1) instanceof CalendarDetailReservationRowEpoxyModel);
    }

    public void clearEditMode(Set<AirDate> dates) {
        for (AirDate date : dates) {
            setSelected(date, false);
        }
    }

    private void setSelected(AirDate date, boolean selected) {
        int modelIndex = getPositionOfDate(date);
        if (modelIndex >= 0 && (this.models.get(modelIndex) instanceof CalendarDetailDayRowEpoxyModel)) {
            ((CalendarDetailDayRowEpoxyModel) this.models.get(modelIndex)).setSelected(selected);
            notifyItemChanged(modelIndex);
        }
    }

    private EpoxyModel<?> makeReservationRow(Reservation reservation, boolean isNextDayBusy) {
        return new CalendarDetailReservationRowEpoxyModel().showTopSpace(!isAfterReservation()).reservation(reservation).isNextDayBusy(isNextDayBusy).clickListener(this.onClickReservationListener).mo11716id(reservation.getStartDate().getTimeInMillisAtStartOfDay() + 2);
    }

    private EpoxyModel<?> makeDayRow(CalendarDay calendarDay) {
        boolean isUnclickable;
        boolean isSelected;
        boolean z = true;
        if ((calendarDay.getDate().isBefore(this.today) || calendarDay.isBlockedForExceedingMaxDayCap()) && ListUtils.isEmpty((Collection<?>) calendarDay.getNestedBusyDetails())) {
            isUnclickable = true;
        } else {
            isUnclickable = false;
        }
        if (isUnclickable || !getSelectedDates().contains(calendarDay.getDate())) {
            isSelected = false;
        } else {
            isSelected = true;
        }
        CalendarDetailDayRowEpoxyModel calendarDetailDayRowEpoxyModel = new CalendarDetailDayRowEpoxyModel();
        if (isAfterReservation()) {
            z = false;
        }
        return calendarDetailDayRowEpoxyModel.showTopSpace(z).calendarDay(calendarDay, this.calendarRule).setSelected(isSelected).clickListener(isUnclickable ? null : this.onClickDayListener).mo11716id(getIdForDate(calendarDay.getDate()));
    }

    private EpoxyModel<?> makeMonthRow(AirDate date) {
        return new CalendarDetailMonthRowEpoxyModel().airDate(date).shortForm(true).mo11716id(date.getTimeInMillisAtStartOfDay() + 1);
    }

    private long getIdForDate(AirDate date) {
        return date.getTimeInMillisAtStartOfDay();
    }

    private boolean isAfterReservation() {
        return !this.models.isEmpty() && (this.models.get(this.models.size() + -1) instanceof CalendarDetailReservationRowEpoxyModel);
    }
}
