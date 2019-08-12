package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.android.hostcalendar.C6418R;

public class CalendarMiniThumbnailGrid_ViewBinding implements Unbinder {
    public CalendarMiniThumbnailGrid_ViewBinding(CalendarMiniThumbnailGrid target) {
        this(target, target.getContext());
    }

    @Deprecated
    public CalendarMiniThumbnailGrid_ViewBinding(CalendarMiniThumbnailGrid target, View source) {
        this(target, source.getContext());
    }

    public CalendarMiniThumbnailGrid_ViewBinding(CalendarMiniThumbnailGrid target, Context context) {
        Resources res = context.getResources();
        target.backgroundGray = ContextCompat.getColor(context, C6418R.color.n2_horizontal_rule_gray);
        target.strokeWidth = res.getDimensionPixelSize(C6418R.dimen.calendar_stroke_width);
        target.dotSize = res.getDimensionPixelSize(C6418R.dimen.host_calendar_thumbnail_dot_size);
    }

    public void unbind() {
    }
}
