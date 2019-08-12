package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.graphics.Rect;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.ItemDecoration;
import android.support.p002v7.widget.RecyclerView.State;
import android.view.View;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.interfaces.StoryFeedCardContainer;

public class StoryFeedItemDecoration extends ItemDecoration {
    private final int colCount;
    private final int edgePaddingPx;
    private final StoryFeedCardContainer storyFeedCardContainer;

    public StoryFeedItemDecoration(Context context, int colCount2, StoryFeedCardContainer storyFeedCardContainer2) {
        this.colCount = colCount2;
        this.storyFeedCardContainer = storyFeedCardContainer2;
        this.edgePaddingPx = context.getResources().getDimensionPixelSize(C5709R.dimen.story_feed_card_edge_padding);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        int pos = parent.getChildAdapterPosition(view);
        if (pos >= this.storyFeedCardContainer.getStoryCardOffset()) {
            int posInRow = (pos - this.storyFeedCardContainer.getStoryCardOffset()) % this.colCount;
            if (posInRow == 0) {
                outRect.left = this.edgePaddingPx;
            } else if (posInRow == this.colCount - 1) {
                outRect.right = this.edgePaddingPx;
            }
        }
    }
}
