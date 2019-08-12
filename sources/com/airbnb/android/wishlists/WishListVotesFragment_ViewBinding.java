package com.airbnb.android.wishlists;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;

public class WishListVotesFragment_ViewBinding implements Unbinder {
    private WishListVotesFragment target;

    public WishListVotesFragment_ViewBinding(WishListVotesFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C1758R.C1760id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1758R.C1760id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        WishListVotesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.toolbar = null;
    }
}
