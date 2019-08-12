package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class GroupedDates_ViewBinding implements Unbinder {
    private GroupedDates target;

    public GroupedDates_ViewBinding(GroupedDates target2) {
        this(target2, target2);
    }

    public GroupedDates_ViewBinding(GroupedDates target2, View source) {
        this.target = target2;
        target2.mNumNightsText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_stay_duration, "field 'mNumNightsText'", TextView.class);
        target2.checkInTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_arriving, "field 'checkInTitle'", TextView.class);
        target2.checkOutTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_departing, "field 'checkOutTitle'", TextView.class);
        target2.mCheckInText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_check_in_date, "field 'mCheckInText'", TextView.class);
        target2.mCheckOutText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_check_out_date, "field 'mCheckOutText'", TextView.class);
    }

    public void unbind() {
        GroupedDates target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mNumNightsText = null;
        target2.checkInTitle = null;
        target2.checkOutTitle = null;
        target2.mCheckInText = null;
        target2.mCheckOutText = null;
    }
}
