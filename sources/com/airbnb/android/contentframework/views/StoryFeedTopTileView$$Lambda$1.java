package com.airbnb.android.contentframework.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.StoryFeedTopTile;

final /* synthetic */ class StoryFeedTopTileView$$Lambda$1 implements OnClickListener {
    private final StoryFeedTopTileView arg$1;
    private final StoryFeedTopTile arg$2;

    private StoryFeedTopTileView$$Lambda$1(StoryFeedTopTileView storyFeedTopTileView, StoryFeedTopTile storyFeedTopTile) {
        this.arg$1 = storyFeedTopTileView;
        this.arg$2 = storyFeedTopTile;
    }

    public static OnClickListener lambdaFactory$(StoryFeedTopTileView storyFeedTopTileView, StoryFeedTopTile storyFeedTopTile) {
        return new StoryFeedTopTileView$$Lambda$1(storyFeedTopTileView, storyFeedTopTile);
    }

    public void onClick(View view) {
        StoryFeedTopTileView.lambda$bindData$0(this.arg$1, this.arg$2, view);
    }
}
