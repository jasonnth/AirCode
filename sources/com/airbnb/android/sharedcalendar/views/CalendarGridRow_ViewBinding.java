package com.airbnb.android.sharedcalendar.views;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.android.sharedcalendar.C1576R;

public class CalendarGridRow_ViewBinding implements Unbinder {
    public CalendarGridRow_ViewBinding(CalendarGridRow target) {
        this(target, target.getContext());
    }

    @Deprecated
    public CalendarGridRow_ViewBinding(CalendarGridRow target, View source) {
        this(target, source.getContext());
    }

    public CalendarGridRow_ViewBinding(CalendarGridRow target, Context context) {
        Resources res = context.getResources();
        target.cellPadding = res.getDimensionPixelSize(C1576R.dimen.calendar_cell_padding);
        target.imagePadding = res.getDimensionPixelSize(C1576R.dimen.calendar_image_padding);
        target.strokeWidth = res.getDimensionPixelSize(C1576R.dimen.calendar_stroke_width);
        target.tipCircleRadius = res.getDimensionPixelSize(C1576R.dimen.calendar_tip_circle_radius);
    }

    public void unbind() {
    }
}
