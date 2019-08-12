package com.airbnb.android.wishlists;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;

public class WishListIndexFragment_ViewBinding implements Unbinder {
    private WishListIndexFragment target;

    public WishListIndexFragment_ViewBinding(WishListIndexFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C1758R.C1760id.wish_lists_recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.swipeRefreshLayout = (AirSwipeRefreshLayout) Utils.findRequiredViewAsType(source, C1758R.C1760id.swipe_refresh_layout, "field 'swipeRefreshLayout'", AirSwipeRefreshLayout.class);
    }

    public void unbind() {
        WishListIndexFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.swipeRefreshLayout = null;
    }
}
