package com.airbnb.android.hostcalendar.views;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.primitives.AirTextView;

public class CalendarDetailDayRow_ViewBinding implements Unbinder {
    private CalendarDetailDayRow target;

    public CalendarDetailDayRow_ViewBinding(CalendarDetailDayRow target2) {
        this(target2, target2);
    }

    public CalendarDetailDayRow_ViewBinding(CalendarDetailDayRow target2, View source) {
        this.target = target2;
        target2.topSpace = Utils.findRequiredView(source, C6418R.C6420id.top_space, "field 'topSpace'");
        target2.dayContainer = Utils.findRequiredView(source, C6418R.C6420id.day_container, "field 'dayContainer'");
        target2.collapsedIcon = Utils.findRequiredView(source, C6418R.C6420id.collapsed_icon, "field 'collapsedIcon'");
        target2.dayText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.day, "field 'dayText'", AirTextView.class);
        target2.dayOfWeekText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.day_of_week, "field 'dayOfWeekText'", AirTextView.class);
        target2.availabilityText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.availability, "field 'availabilityText'", AirTextView.class);
        target2.priceText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.price, "field 'priceText'", AirTextView.class);
        target2.busyReasonText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.busy_reason, "field 'busyReasonText'", AirTextView.class);
        target2.notesText = (TextRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.notes, "field 'notesText'", TextRow.class);
        target2.smartPromoText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.smart_promo, "field 'smartPromoText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, C6418R.C6420id.section_divider, "field 'divider'");
        target2.smartPricingOffText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.smart_Pricing_off_text, "field 'smartPricingOffText'", AirTextView.class);
        Context context = source.getContext();
        Resources res = context.getResources();
        target2.whiteColor = ContextCompat.getColor(context, C6418R.color.white);
        target2.darkHofColor = ContextCompat.getColor(context, C6418R.color.n2_hof_dark);
        target2.lightHofColor = ContextCompat.getColor(context, C6418R.color.n2_hof_40);
        target2.transparentColor = ContextCompat.getColor(context, C6418R.color.transparent);
        target2.strokeWidth = res.getDimensionPixelSize(C6418R.dimen.calendar_stroke_width);
        target2.paddingSmall = res.getDimensionPixelSize(C6418R.dimen.n2_vertical_padding_small);
    }

    public void unbind() {
        CalendarDetailDayRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.topSpace = null;
        target2.dayContainer = null;
        target2.collapsedIcon = null;
        target2.dayText = null;
        target2.dayOfWeekText = null;
        target2.availabilityText = null;
        target2.priceText = null;
        target2.busyReasonText = null;
        target2.notesText = null;
        target2.smartPromoText = null;
        target2.divider = null;
        target2.smartPricingOffText = null;
    }
}
