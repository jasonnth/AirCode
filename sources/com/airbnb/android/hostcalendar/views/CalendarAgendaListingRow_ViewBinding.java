package com.airbnb.android.hostcalendar.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;

public class CalendarAgendaListingRow_ViewBinding implements Unbinder {
    private CalendarAgendaListingRow target;

    public CalendarAgendaListingRow_ViewBinding(CalendarAgendaListingRow target2) {
        this(target2, target2);
    }

    public CalendarAgendaListingRow_ViewBinding(CalendarAgendaListingRow target2, View source) {
        this.target = target2;
        target2.listingNameText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.listing_name, "field 'listingNameText'", AirTextView.class);
        target2.topInfoBlock = (CalendarAgendaInfoBlock) Utils.findRequiredViewAsType(source, C6418R.C6420id.top_info_block, "field 'topInfoBlock'", CalendarAgendaInfoBlock.class);
        target2.bottomInfoBlock = (CalendarAgendaInfoBlock) Utils.findRequiredViewAsType(source, C6418R.C6420id.bottom_info_block, "field 'bottomInfoBlock'", CalendarAgendaInfoBlock.class);
        target2.divider = Utils.findRequiredView(source, C6418R.C6420id.section_divider, "field 'divider'");
    }

    public void unbind() {
        CalendarAgendaListingRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.listingNameText = null;
        target2.topInfoBlock = null;
        target2.bottomInfoBlock = null;
        target2.divider = null;
    }
}
