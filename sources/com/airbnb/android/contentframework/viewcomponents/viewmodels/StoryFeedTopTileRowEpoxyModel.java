package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import com.airbnb.android.contentframework.views.StoryFeedTopTileRow;
import com.airbnb.android.core.models.StoryFeedTopTile;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class StoryFeedTopTileRowEpoxyModel extends AirEpoxyModel<StoryFeedTopTileRow> {
    List<StoryFeedTopTile> topTileList;

    public void bind(StoryFeedTopTileRow view) {
        super.bind(view);
        view.bindData(this.topTileList);
    }
}
