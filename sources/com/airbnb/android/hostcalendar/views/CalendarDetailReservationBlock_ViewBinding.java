package com.airbnb.android.hostcalendar.views;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class CalendarDetailReservationBlock_ViewBinding implements Unbinder {
    private CalendarDetailReservationBlock target;

    public CalendarDetailReservationBlock_ViewBinding(CalendarDetailReservationBlock target2) {
        this(target2, target2);
    }

    public CalendarDetailReservationBlock_ViewBinding(CalendarDetailReservationBlock target2, View source) {
        this.target = target2;
        target2.guestImage = (HaloImageView) Utils.findRequiredViewAsType(source, C6418R.C6420id.guest_image, "field 'guestImage'", HaloImageView.class);
        target2.guestName = (TextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.guest_name, "field 'guestName'", TextView.class);
        target2.guestsNightsDetail = (TextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.guests_nights_detail, "field 'guestsNightsDetail'", TextView.class);
        target2.price = (TextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.price, "field 'price'", TextView.class);
        target2.messageAction = (TextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.message_action, "field 'messageAction'", TextView.class);
        Resources res = source.getContext().getResources();
        target2.radius = res.getDimensionPixelSize(C6418R.dimen.calendar_detail_background_radius);
        target2.strokeWidth = res.getDimensionPixelSize(C6418R.dimen.calendar_detail_stroke_width);
    }

    public void unbind() {
        CalendarDetailReservationBlock target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.guestImage = null;
        target2.guestName = null;
        target2.guestsNightsDetail = null;
        target2.price = null;
        target2.messageAction = null;
    }
}
