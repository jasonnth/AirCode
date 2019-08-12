package com.airbnb.android.lib.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class DateAndGuestCountView_ViewBinding implements Unbinder {
    private DateAndGuestCountView target;

    public DateAndGuestCountView_ViewBinding(DateAndGuestCountView target2) {
        this(target2, target2);
    }

    public DateAndGuestCountView_ViewBinding(DateAndGuestCountView target2, View source) {
        this.target = target2;
        target2.mGroupedDates = (GroupedDates) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_dates, "field 'mGroupedDates'", GroupedDates.class);
        target2.guestCountSelector = (GroupedCounter) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_guest_counter, "field 'guestCountSelector'", GroupedCounter.class);
    }

    public void unbind() {
        DateAndGuestCountView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mGroupedDates = null;
        target2.guestCountSelector = null;
    }
}
