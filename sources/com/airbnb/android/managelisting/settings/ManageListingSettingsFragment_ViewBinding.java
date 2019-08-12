package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.collections.AirRecyclerView;

public class ManageListingSettingsFragment_ViewBinding implements Unbinder {
    private ManageListingSettingsFragment target;

    public ManageListingSettingsFragment_ViewBinding(ManageListingSettingsFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C7368R.C7370id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
    }

    public void unbind() {
        ManageListingSettingsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
    }
}
