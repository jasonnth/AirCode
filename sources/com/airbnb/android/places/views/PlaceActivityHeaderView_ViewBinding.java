package com.airbnb.android.places.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.primitives.AirTextView;

public class PlaceActivityHeaderView_ViewBinding implements Unbinder {
    private PlaceActivityHeaderView target;

    public PlaceActivityHeaderView_ViewBinding(PlaceActivityHeaderView target2) {
        this(target2, target2);
    }

    public PlaceActivityHeaderView_ViewBinding(PlaceActivityHeaderView target2, View source) {
        this.target = target2;
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.title, "field 'titleView'", AirTextView.class);
        target2.subtitleView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.subtitle, "field 'subtitleView'", AirTextView.class);
        target2.actionKickerView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.action_kicker, "field 'actionKickerView'", AirTextView.class);
        target2.sectionDivider = Utils.findRequiredView(source, C7627R.C7629id.section_divider, "field 'sectionDivider'");
    }

    public void unbind() {
        PlaceActivityHeaderView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.subtitleView = null;
        target2.actionKickerView = null;
        target2.sectionDivider = null;
    }
}
