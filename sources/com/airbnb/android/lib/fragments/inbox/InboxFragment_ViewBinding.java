package com.airbnb.android.lib.fragments.inbox;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.EmptyResultsCardView;
import com.airbnb.p027n2.components.AirToolbar;

public class InboxFragment_ViewBinding implements Unbinder {
    private InboxFragment target;

    public InboxFragment_ViewBinding(InboxFragment target2, View source) {
        this.target = target2;
        target2.mSwipeRefreshLayout = (AirSwipeRefreshLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.swipe_refresh_layout, "field 'mSwipeRefreshLayout'", AirSwipeRefreshLayout.class);
        target2.newRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'newRecyclerView'", RecyclerView.class);
        target2.emptyResultsCard = (EmptyResultsCardView) Utils.findRequiredViewAsType(source, C0880R.C0882id.empty_results_card, "field 'emptyResultsCard'", EmptyResultsCardView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        InboxFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mSwipeRefreshLayout = null;
        target2.newRecyclerView = null;
        target2.emptyResultsCard = null;
        target2.toolbar = null;
    }
}
