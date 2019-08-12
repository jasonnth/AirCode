package com.airbnb.android.hostcalendar.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public class MultiDayPriceTipsFragment_ViewBinding implements Unbinder {
    private MultiDayPriceTipsFragment target;

    public MultiDayPriceTipsFragment_ViewBinding(MultiDayPriceTipsFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C6418R.C6420id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.applyButton = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C6418R.C6420id.apply_button, "field 'applyButton'", FixedDualActionFooter.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6418R.C6420id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        MultiDayPriceTipsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.applyButton = null;
        target2.toolbar = null;
    }
}
