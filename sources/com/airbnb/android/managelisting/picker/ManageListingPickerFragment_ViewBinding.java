package com.airbnb.android.managelisting.picker;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingPickerFragment_ViewBinding implements Unbinder {
    private ManageListingPickerFragment target;

    public ManageListingPickerFragment_ViewBinding(ManageListingPickerFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C7368R.C7370id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.blockingOverlay = Utils.findRequiredView(source, C7368R.C7370id.block_overlay, "field 'blockingOverlay'");
        target2.swipeRefreshLayout = (AirSwipeRefreshLayout) Utils.findRequiredViewAsType(source, C7368R.C7370id.swipe_refresh_layout, "field 'swipeRefreshLayout'", AirSwipeRefreshLayout.class);
    }

    public void unbind() {
        ManageListingPickerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.blockingOverlay = null;
        target2.swipeRefreshLayout = null;
    }
}
