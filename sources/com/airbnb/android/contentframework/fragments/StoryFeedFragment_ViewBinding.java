package com.airbnb.android.contentframework.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.NavigationPill;

public class StoryFeedFragment_ViewBinding implements Unbinder {
    private StoryFeedFragment target;

    public StoryFeedFragment_ViewBinding(StoryFeedFragment target2, View source) {
        this.target = target2;
        target2.swipeRefreshLayout = (AirSwipeRefreshLayout) Utils.findRequiredViewAsType(source, C5709R.C5711id.swipe_refresh_layout, "field 'swipeRefreshLayout'", AirSwipeRefreshLayout.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5709R.C5711id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.composerPill = (NavigationPill) Utils.findRequiredViewAsType(source, C5709R.C5711id.story_composer_pill, "field 'composerPill'", NavigationPill.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5709R.C5711id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.searchEmptyStateView = Utils.findRequiredView(source, C5709R.C5711id.search_empty_state_view, "field 'searchEmptyStateView'");
    }

    public void unbind() {
        StoryFeedFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.swipeRefreshLayout = null;
        target2.recyclerView = null;
        target2.composerPill = null;
        target2.toolbar = null;
        target2.searchEmptyStateView = null;
    }
}
