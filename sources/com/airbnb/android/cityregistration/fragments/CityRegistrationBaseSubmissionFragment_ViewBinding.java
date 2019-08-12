package com.airbnb.android.cityregistration.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationBaseSubmissionFragment_ViewBinding implements Unbinder {
    private CityRegistrationBaseSubmissionFragment target;

    public CityRegistrationBaseSubmissionFragment_ViewBinding(CityRegistrationBaseSubmissionFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5630R.C5632id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5630R.C5632id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.saveButton = (AirButton) Utils.findRequiredViewAsType(source, C5630R.C5632id.save_button, "field 'saveButton'", AirButton.class);
    }

    public void unbind() {
        CityRegistrationBaseSubmissionFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
        target2.saveButton = null;
    }
}
