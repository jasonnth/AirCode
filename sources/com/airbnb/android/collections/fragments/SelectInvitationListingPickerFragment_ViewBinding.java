package com.airbnb.android.collections.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.collections.C5698R;
import com.airbnb.p027n2.components.AirToolbar;

public class SelectInvitationListingPickerFragment_ViewBinding implements Unbinder {
    private SelectInvitationListingPickerFragment target;

    public SelectInvitationListingPickerFragment_ViewBinding(SelectInvitationListingPickerFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5698R.C5700id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5698R.C5700id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        SelectInvitationListingPickerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
    }
}
