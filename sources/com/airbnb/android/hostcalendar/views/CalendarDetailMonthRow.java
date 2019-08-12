package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.adapters.CalendarDetailAdapter.CalendarDetailRow;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;

public class CalendarDetailMonthRow extends LinearLayout implements CalendarDetailRow {
    private AirDate date;
    @BindView
    AirTextView monthText;
    @BindView
    View sectionDivider;

    public CalendarDetailMonthRow(Context context) {
        this(context, null);
    }

    public CalendarDetailMonthRow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarDetailMonthRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), C6418R.layout.calendar_detail_month_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void setMonthText(AirDate date2, boolean shortForm) {
        this.date = date2;
        this.monthText.setText(date2.formatDate(getResources().getString(shortForm ? C6418R.string.short_month_format : C6418R.string.month_name_format)));
    }

    public void showSectionDivider(boolean show) {
        ViewUtils.setVisibleIf(this.sectionDivider, show);
    }

    public AirDate getDateForScrolling() {
        return this.date;
    }
}
