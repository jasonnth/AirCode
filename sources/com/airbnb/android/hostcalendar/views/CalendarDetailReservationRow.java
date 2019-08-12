package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.adapters.CalendarDetailAdapter.CalendarDetailRow;

public class CalendarDetailReservationRow extends FrameLayout implements CalendarDetailRow {
    private static final int MAX_DAYS = 5;
    @BindView
    LinearLayout calendarRows;
    private String confirmationCode;
    @BindView
    CalendarDetailReservationBlock reservationBlock;
    @BindView
    FrameLayout rservationFrame;
    private long threadId;
    @BindDimen
    int verticalMargin;

    public interface CalendarDetailReservationClickListener {
        void onMessageClick(CalendarDetailReservationRow calendarDetailReservationRow);

        void onReservationClick(CalendarDetailReservationRow calendarDetailReservationRow);
    }

    public CalendarDetailReservationRow(Context context) {
        this(context, null);
    }

    public CalendarDetailReservationRow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarDetailReservationRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.threadId = -1;
        init();
    }

    private void init() {
        inflate(getContext(), C6418R.layout.calendar_detail_reservation_row, this);
        ButterKnife.bind((View) this);
    }

    private CalendarDetailDayRow getDayRow() {
        return (CalendarDetailDayRow) LayoutInflater.from(getContext()).inflate(C6418R.layout.view_holder_calendar_detail_day_row, this.calendarRows, false);
    }

    private CalendarDetailMonthRow getMonthRow() {
        return (CalendarDetailMonthRow) LayoutInflater.from(getContext()).inflate(C6418R.layout.view_holder_calendar_detail_month_row, this.calendarRows, false);
    }

    public void setReservation(Reservation reservation, boolean isNextDayBusy) {
        this.calendarRows.removeAllViews();
        if (reservation == null) {
            this.confirmationCode = null;
            this.threadId = -1;
            return;
        }
        this.confirmationCode = reservation.getConfirmationCode();
        this.threadId = (long) reservation.getThreadId();
        int nights = reservation.getReservedNightsCount();
        AirDate today = AirDate.today();
        AirDate day = reservation.getStartDate();
        int i = 0;
        while (i < nights) {
            if (!(nights > 5 && i >= 2 && i < nights + -2)) {
                CalendarDetailDayRow dayRow = getDayRow();
                dayRow.bindDay(day, day.isBefore(today));
                if (i == 0) {
                    dayRow.showTopSpace(false);
                }
                if (i == nights - 1) {
                    LayoutParams lp = (LayoutParams) dayRow.getLayoutParams();
                    lp.weight = 1.0f;
                    lp.height = 0;
                }
                this.calendarRows.addView(dayRow);
            }
            if (nights > 5 && i == 2) {
                CalendarDetailDayRow collapsedIconRow = getDayRow();
                collapsedIconRow.bindDay(null, false);
                this.calendarRows.addView(collapsedIconRow);
            }
            day = day.plusDays(1);
            if (day.getDayOfMonth() == 1) {
                CalendarDetailMonthRow monthRow = getMonthRow();
                monthRow.setMonthText(day, true);
                this.calendarRows.addView(monthRow);
            }
            i++;
        }
        this.reservationBlock.setReservation(reservation);
        this.rservationFrame.setBackgroundColor(ContextCompat.getColor(getContext(), isNextDayBusy ? C6418R.color.light_grey : C6418R.color.white));
    }

    public void setReservationClickListener(CalendarDetailReservationClickListener listener) {
        if (listener == null) {
            setOnClickListener(null);
        } else {
            setOnClickListener(CalendarDetailReservationRow$$Lambda$1.lambdaFactory$(this, listener));
        }
        this.reservationBlock.setReservationClickListener(this, listener);
    }

    public void showTopSpace(boolean show) {
        int paddingTop;
        if (show) {
            paddingTop = this.verticalMargin;
        } else {
            paddingTop = 0;
        }
        setPadding(0, paddingTop, 0, 0);
    }

    public String getConfirmationCode() {
        return (String) Check.notNull(this.confirmationCode);
    }

    public long getThreadId() {
        return Check.validId(this.threadId);
    }

    public AirDate getDateForScrolling() {
        int children = this.calendarRows.getChildCount();
        float y = -1.0f * getY();
        for (int i = 0; i < children; i++) {
            View child = this.calendarRows.getChildAt(i);
            if (this.calendarRows.getY() + ((float) child.getHeight()) + child.getY() > y) {
                return ((CalendarDetailRow) child).getDateForScrolling();
            }
        }
        return null;
    }
}
