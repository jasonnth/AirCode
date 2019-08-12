package com.airbnb.android.sharedcalendar.views;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.android.sharedcalendar.C1576R;

public class CalendarGridMonthHeader_ViewBinding implements Unbinder {
    public CalendarGridMonthHeader_ViewBinding(CalendarGridMonthHeader target) {
        this(target, target.getContext());
    }

    @Deprecated
    public CalendarGridMonthHeader_ViewBinding(CalendarGridMonthHeader target, View source) {
        this(target, source.getContext());
    }

    public CalendarGridMonthHeader_ViewBinding(CalendarGridMonthHeader target, Context context) {
        Resources res = context.getResources();
        target.strokeWidth = res.getDimensionPixelSize(C1576R.dimen.calendar_stroke_width);
        target.dayTextSize = res.getDimensionPixelSize(C1576R.dimen.calendar_text_size_day);
        target.monthTextSize = res.getDimensionPixelSize(C1576R.dimen.calendar_text_size_month_small);
        target.monthLeftPadding = res.getDimensionPixelSize(C1576R.dimen.n2_horizontal_padding_medium_phone);
        target.verticalPadding = res.getDimensionPixelSize(C1576R.dimen.n2_vertical_padding_medium);
    }

    public void unbind() {
    }
}
