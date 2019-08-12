package com.airbnb.android.places.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;

public class PlaceActivityDetailsView_ViewBinding implements Unbinder {
    private PlaceActivityDetailsView target;

    public PlaceActivityDetailsView_ViewBinding(PlaceActivityDetailsView target2) {
        this(target2, target2);
    }

    public PlaceActivityDetailsView_ViewBinding(PlaceActivityDetailsView target2, View source) {
        this.target = target2;
        target2.sectionDivider = Utils.findRequiredView(source, C7627R.C7629id.section_divider, "field 'sectionDivider'");
    }

    public void unbind() {
        PlaceActivityDetailsView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sectionDivider = null;
    }
}
