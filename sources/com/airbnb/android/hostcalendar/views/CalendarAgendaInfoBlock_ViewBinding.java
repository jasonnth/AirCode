package com.airbnb.android.hostcalendar.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class CalendarAgendaInfoBlock_ViewBinding implements Unbinder {
    private CalendarAgendaInfoBlock target;

    public CalendarAgendaInfoBlock_ViewBinding(CalendarAgendaInfoBlock target2) {
        this(target2, target2);
    }

    public CalendarAgendaInfoBlock_ViewBinding(CalendarAgendaInfoBlock target2, View source) {
        this.target = target2;
        target2.infoTypeText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.info_type, "field 'infoTypeText'", AirTextView.class);
        target2.guestNameText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.guest_name, "field 'guestNameText'", AirTextView.class);
        target2.additionalDetailsText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.additional_details, "field 'additionalDetailsText'", AirTextView.class);
        target2.messageAction = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.message_action, "field 'messageAction'", AirTextView.class);
        target2.guestImageView = (HaloImageView) Utils.findRequiredViewAsType(source, C6418R.C6420id.guest_image, "field 'guestImageView'", HaloImageView.class);
    }

    public void unbind() {
        CalendarAgendaInfoBlock target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.infoTypeText = null;
        target2.guestNameText = null;
        target2.additionalDetailsText = null;
        target2.messageAction = null;
        target2.guestImageView = null;
    }
}
