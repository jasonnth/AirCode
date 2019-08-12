package com.airbnb.android.wishlists;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.NavigationPill;

public class WishListDetailsFragment_ViewBinding implements Unbinder {
    private WishListDetailsFragment target;

    public WishListDetailsFragment_ViewBinding(WishListDetailsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1758R.C1760id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C1758R.C1760id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.swipeRefreshLayout = (AirSwipeRefreshLayout) Utils.findRequiredViewAsType(source, C1758R.C1760id.swipe_refresh_layout, "field 'swipeRefreshLayout'", AirSwipeRefreshLayout.class);
        target2.navigationPill = (NavigationPill) Utils.findRequiredViewAsType(source, C1758R.C1760id.map_toggle_pill, "field 'navigationPill'", NavigationPill.class);
    }

    public void unbind() {
        WishListDetailsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.swipeRefreshLayout = null;
        target2.navigationPill = null;
    }
}