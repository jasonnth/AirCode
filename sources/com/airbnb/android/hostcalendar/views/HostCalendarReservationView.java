package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateInterval;
import com.airbnb.android.airdate.AirMonth;
import com.airbnb.android.airdate.DayOfWeek;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.sharedcalendar.listeners.CalendarGridTapListener;
import com.airbnb.android.sharedcalendar.models.CalendarGridDayModel;
import com.airbnb.android.sharedcalendar.views.CalendarGridMonth;

public class HostCalendarReservationView extends LinearLayout {
    private final CalendarGridTapListener calendarGridTapListener;
    @BindView
    LinearLayout calendarMonthsViewHolder;
    private String[] dayOfWeekInitials;
    private final DayOfWeek firstDayOfWeek;
    /* access modifiers changed from: private */
    public OnClickListener listener;
    @BindView
    TextView moreNightsText;

    public HostCalendarReservationView(Context context) {
        this(context, null);
    }

    public HostCalendarReservationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HostCalendarReservationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.firstDayOfWeek = AirDate.getDayOfWeekFromCalendar();
        this.calendarGridTapListener = new CalendarGridTapListener() {
            public void onDayClick(CalendarGridDayModel dayModel) {
                HostCalendarReservationView.this.listener.onClick(HostCalendarReservationView.this);
            }

            public void onReservationClick(String reservationCode) {
                HostCalendarReservationView.this.listener.onClick(HostCalendarReservationView.this);
            }
        };
        setOrientation(1);
        inflate(getContext(), C6418R.layout.host_calendar_reservation_view, this);
        ButterKnife.bind((View) this);
    }

    public void bind(CalendarDays calendarDays, OnClickListener listener2, AirDate requestStartDate, AirDate requestEndDate, String guestPhotoUrl, boolean hideGuestProfilePhoto, CharSequence profilePlaceholderText) {
        AirDate endDate;
        this.calendarMonthsViewHolder.removeAllViews();
        this.listener = listener2;
        this.dayOfWeekInitials = CalendarDays.initDayOfWeekInitials(this.firstDayOfWeek, getContext());
        AirDate minDate = calendarDays.getMinDate();
        AirDate maxDate = calendarDays.getMaxDate();
        AirMonth firstMonth = new AirMonth(minDate);
        AirDate lastDayOfMonth = firstMonth.lastDayOfMonth();
        if (maxDate.isAfter(firstMonth.lastDayOfMonth())) {
            endDate = lastDayOfMonth;
        } else {
            endDate = maxDate;
        }
        AirDateInterval interval = new AirDateInterval(requestStartDate, requestEndDate);
        addMonthView(firstMonth, calendarDays, minDate, endDate, interval, guestPhotoUrl, hideGuestProfilePhoto, profilePlaceholderText);
        AirMonth lastMonth = new AirMonth(calendarDays.getMaxDate());
        if (!firstMonth.equals(lastMonth)) {
            CalendarDays calendarDays2 = calendarDays;
            addMonthView(lastMonth, calendarDays2, lastMonth.firstDayOfMonth(), calendarDays.getMaxDate(), interval, guestPhotoUrl, hideGuestProfilePhoto, profilePlaceholderText);
        }
        setOnClickListener(listener2);
        setMoreNightsText(requestEndDate, maxDate);
    }

    private void addMonthView(AirMonth month, CalendarDays calendarDays, AirDate startDate, AirDate endDate, AirDateInterval interval, String guestPhotoUrl, boolean hideGuestProfilePhoto, CharSequence profilePlaceholderText) {
        LayoutParams params = new LayoutParams(-1, -2);
        CalendarGridMonth monthView = new CalendarGridMonth(getContext());
        monthView.setLayoutParams(params);
        monthView.bindInterval(this.firstDayOfWeek, this.dayOfWeekInitials, calendarDays, month, startDate, endDate, interval, this.calendarGridTapListener, guestPhotoUrl, hideGuestProfilePhoto, profilePlaceholderText);
        this.calendarMonthsViewHolder.addView(monthView);
    }

    private void setMoreNightsText(AirDate requestEndDate, AirDate fetchEndDate) {
        if (requestEndDate.isBefore(fetchEndDate)) {
            this.moreNightsText.setVisibility(8);
            return;
        }
        int numDays = fetchEndDate.getDaysUntil(requestEndDate);
        this.moreNightsText.setText(getResources().getQuantityString(C6418R.plurals.plus_x_nights, numDays, new Object[]{Integer.valueOf(numDays)}));
        this.moreNightsText.setVisibility(0);
    }
}
