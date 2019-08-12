package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;

public class CalendarDetailHeaderRow extends LinearLayout {
    private AirDate date;
    @BindView
    CalendarDetailMonthRow monthRow;
    @BindView
    AirTextView todayClickable;

    public CalendarDetailHeaderRow(Context context) {
        this(context, null);
    }

    public CalendarDetailHeaderRow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarDetailHeaderRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), C6418R.layout.calendar_detail_header_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        this.monthRow.showSectionDivider(false);
    }

    public void setMonthText(AirDate date2, boolean shortForm) {
        this.date = date2;
        this.monthRow.monthText.setText(date2.formatDate(getResources().getString(shortForm ? C6418R.string.short_month_format : C6418R.string.month_name_format)));
    }

    public void setTodayClickableVisibility(boolean visible) {
        this.todayClickable.setVisibility(visible ? 0 : 4);
    }

    public void setTodayClickableOnClickListener(OnClickListener listener) {
        this.todayClickable.setOnClickListener(listener);
    }
}
