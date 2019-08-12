package com.airbnb.android.contentframework.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;

public class StoryFeedTopTileRow_ViewBinding implements Unbinder {
    private StoryFeedTopTileRow target;

    public StoryFeedTopTileRow_ViewBinding(StoryFeedTopTileRow target2) {
        this(target2, target2);
    }

    public StoryFeedTopTileRow_ViewBinding(StoryFeedTopTileRow target2, View source) {
        this.target = target2;
        target2.tile1 = (StoryFeedTopTileView) Utils.findRequiredViewAsType(source, C5709R.C5711id.tile_1, "field 'tile1'", StoryFeedTopTileView.class);
        target2.tile2 = (StoryFeedTopTileView) Utils.findRequiredViewAsType(source, C5709R.C5711id.tile_2, "field 'tile2'", StoryFeedTopTileView.class);
        target2.tile3 = (StoryFeedTopTileView) Utils.findRequiredViewAsType(source, C5709R.C5711id.tile_3, "field 'tile3'", StoryFeedTopTileView.class);
    }

    public void unbind() {
        StoryFeedTopTileRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.tile1 = null;
        target2.tile2 = null;
        target2.tile3 = null;
    }
}
