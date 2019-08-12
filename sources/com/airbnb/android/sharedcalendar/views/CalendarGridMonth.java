package com.airbnb.android.sharedcalendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateInterval;
import com.airbnb.android.airdate.AirMonth;
import com.airbnb.android.airdate.DayOfWeek;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.sharedcalendar.C1576R;
import com.airbnb.android.sharedcalendar.listeners.CalendarGridTapListener;
import com.airbnb.android.sharedcalendar.listeners.OnboardingOverlayListener;
import com.airbnb.android.sharedcalendar.models.CalendarGridDayModel;
import com.airbnb.android.sharedcalendar.models.CalendarGridNestedBusyModel;
import com.airbnb.android.sharedcalendar.models.CalendarGridReservationModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalendarGridMonth extends LinearLayout {
    private static final int NUMBER_OF_DAYS = 7;
    private static final int SECOND_ROW_INDEX = 1;
    @BindView
    CalendarGridMonthHeader monthHeader;
    @BindViews
    CalendarGridRow[] rows;

    public CalendarGridMonth(Context context) {
        this(context, null);
    }

    public CalendarGridMonth(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarGridMonth(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), C1576R.layout.host_calendar_grid_month, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void bindMonth(DayOfWeek firstDayOfWeek, String[] dayOfWeekInitials, CalendarDays calendarDays, AirMonth month, Set<AirDate> selectedDates, CalendarGridTapListener tapListener, OnboardingOverlayListener listener) {
        innerBind(firstDayOfWeek, dayOfWeekInitials, calendarDays, month, selectedDates, tapListener, month.firstDayOfMonth(), month.lastDayOfMonth(), null, null, false, null, listener);
    }

    public void bindInterval(DayOfWeek firstDayOfWeek, String[] dayOfWeekInitials, CalendarDays calendarDays, AirMonth month, AirDate startDate, AirDate endDate, AirDateInterval interval, CalendarGridTapListener tapListener, String guestPhotoUrl, boolean hideGuestProfilePhoto, CharSequence profilePlaceholderText) {
        innerBind(firstDayOfWeek, dayOfWeekInitials, calendarDays, month, new HashSet(), tapListener, startDate, endDate, interval, guestPhotoUrl, hideGuestProfilePhoto, profilePlaceholderText, null);
    }

    private void innerBind(DayOfWeek firstDayOfWeek, String[] dayOfWeekInitials, CalendarDays calendarDays, AirMonth month, Set<AirDate> selectedDates, CalendarGridTapListener tapListener, AirDate startDate, AirDate endDate, AirDateInterval interval, String guestPhotoUrl, boolean hideGuestProfilePhoto, CharSequence profilePlaceholderText, OnboardingOverlayListener onboardingOverlayListener) {
        this.monthHeader.setMonth(month.formatMonth(getContext().getString(C1576R.string.month_name_format)));
        this.monthHeader.setDayOfWeekInitials(dayOfWeekInitials);
        int currentRow = 0;
        AirDate firstDayOfCurrentWeek = startDate;
        while (firstDayOfCurrentWeek.isSameDayOrBefore(endDate)) {
            int positionStart = firstDayOfCurrentWeek.getDaysFromDayOfWeek(firstDayOfWeek);
            AirDate firstDayOfNextWeek = firstDayOfCurrentWeek.plusDays(7 - positionStart);
            if (firstDayOfNextWeek.isAfter(endDate)) {
                firstDayOfNextWeek = endDate.plusDays(1);
            }
            int positionEnd = firstDayOfNextWeek.plusDays(-1).getDaysFromDayOfWeek(firstDayOfWeek) + 1;
            CalendarGridRow gridRow = this.rows[currentRow];
            List<CalendarGridReservationModel> reservationModels = getReservationModels(positionStart, positionEnd, calendarDays, firstDayOfCurrentWeek, interval, guestPhotoUrl, hideGuestProfilePhoto, profilePlaceholderText);
            List<CalendarGridDayModel> dayModels = CalendarGridDayModel.getDayModels(positionStart, positionEnd, calendarDays, firstDayOfCurrentWeek, selectedDates);
            List<CalendarGridNestedBusyModel> nestedBusyModels = new ArrayList<>();
            if (FeatureToggles.showNestedListings()) {
                nestedBusyModels = CalendarGridNestedBusyModel.getNestedBusyModels(positionStart, positionEnd, calendarDays, firstDayOfCurrentWeek);
            }
            boolean editMode = !selectedDates.isEmpty();
            if (interval != null) {
                for (CalendarGridDayModel dayModel : dayModels) {
                    if (interval.contains(dayModel.getCalendarDay().getDate())) {
                        dayModel.setHasPendingRequest(true);
                    }
                }
            }
            if (currentRow == 1 && onboardingOverlayListener != null) {
                onboardingOverlayListener.showOnboardingOverlay(this.rows[1]);
            }
            gridRow.bind(reservationModels, dayModels, nestedBusyModels, positionStart, positionEnd, editMode);
            gridRow.setTapListener(tapListener);
            gridRow.setVisibility(0);
            currentRow++;
            firstDayOfCurrentWeek = firstDayOfNextWeek;
        }
        while (currentRow < this.rows.length) {
            this.rows[currentRow].setVisibility(8);
            currentRow++;
        }
    }

    private List<CalendarGridReservationModel> getReservationModels(int positionStart, int positionEnd, CalendarDays calendarDays, AirDate firstDayOfCurrentWeek, AirDateInterval interval, String guestPhotoUrl, boolean hideGuestProfilePhoto, CharSequence profilePlaceholderText) {
        List<CalendarGridReservationModel> reservationModels = CalendarGridReservationModel.getReservationModels(positionStart, positionEnd, calendarDays, firstDayOfCurrentWeek);
        if (interval != null) {
            CalendarGridReservationModel model = CalendarGridReservationModel.getCalendarGridRequestModel(positionStart, positionEnd, calendarDays, firstDayOfCurrentWeek, interval, guestPhotoUrl, hideGuestProfilePhoto, profilePlaceholderText);
            if (model != null) {
                reservationModels.add(model);
            }
        }
        return reservationModels;
    }
}
