package com.airbnb.android.cityregistration.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.p027n2.components.AirToolbar;

public class CityRegistrationDocTypeSelectionFragment_ViewBinding implements Unbinder {
    private CityRegistrationDocTypeSelectionFragment target;

    public CityRegistrationDocTypeSelectionFragment_ViewBinding(CityRegistrationDocTypeSelectionFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5630R.C5632id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5630R.C5632id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        CityRegistrationDocTypeSelectionFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
    }
}
