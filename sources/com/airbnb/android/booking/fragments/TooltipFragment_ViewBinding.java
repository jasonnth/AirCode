package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.EntryMarquee;
import com.airbnb.p027n2.components.TextRow;

public class TooltipFragment_ViewBinding implements Unbinder {
    private TooltipFragment target;

    public TooltipFragment_ViewBinding(TooltipFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.tooltipMarquee = (EntryMarquee) Utils.findRequiredViewAsType(source, C0704R.C0706id.tooltip_marquee, "field 'tooltipMarquee'", EntryMarquee.class);
        target2.tooltipTextRow = (TextRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.tooltip_textRow, "field 'tooltipTextRow'", TextRow.class);
    }

    public void unbind() {
        TooltipFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.tooltipMarquee = null;
        target2.tooltipTextRow = null;
    }
}
