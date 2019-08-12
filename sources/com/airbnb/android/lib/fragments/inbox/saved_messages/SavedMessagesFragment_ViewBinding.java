package com.airbnb.android.lib.fragments.inbox.saved_messages;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.LoaderRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;

public class SavedMessagesFragment_ViewBinding implements Unbinder {
    private SavedMessagesFragment target;

    public SavedMessagesFragment_ViewBinding(SavedMessagesFragment target2, View source) {
        this.target = target2;
        target2.loaderRecyclerView = (LoaderRecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.loader_recycler_view, "field 'loaderRecyclerView'", LoaderRecyclerView.class);
        target2.swipeRefreshLayout = (AirSwipeRefreshLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.swipe_refresh_layout, "field 'swipeRefreshLayout'", AirSwipeRefreshLayout.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.fullLoader = Utils.findRequiredView(source, C0880R.C0882id.full_loader, "field 'fullLoader'");
    }

    public void unbind() {
        SavedMessagesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loaderRecyclerView = null;
        target2.swipeRefreshLayout = null;
        target2.toolbar = null;
        target2.fullLoader = null;
    }
}
