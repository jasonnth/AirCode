package com.airbnb.android.managelisting.settings;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingGuestRequirementsFragment_ViewBinding implements Unbinder {
    private ManageListingGuestRequirementsFragment target;

    public ManageListingGuestRequirementsFragment_ViewBinding(ManageListingGuestRequirementsFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findOptionalViewAsType(source, C7368R.C7370id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        ManageListingGuestRequirementsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
    }
}
