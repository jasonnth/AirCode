package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.StoryFeedTopTile;
import java.util.List;

public class StoryFeedTopTileRow extends LinearLayout {
    public static final int MIN_TILES_COUNT = 3;
    @BindView
    StoryFeedTopTileView tile1;
    @BindView
    StoryFeedTopTileView tile2;
    @BindView
    StoryFeedTopTileView tile3;

    public StoryFeedTopTileRow(Context context) {
        super(context);
        init(context);
    }

    public StoryFeedTopTileRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StoryFeedTopTileRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, C5709R.layout.story_feed_top_tile_row, this);
        ButterKnife.bind((View) this);
        setOrientation(0);
    }

    public void bindData(List<StoryFeedTopTile> topTileList) {
        if (topTileList.size() < 3) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("StoryFeedTopTileRow cannot be shown with fewer tiles than expected"));
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.tile1.bindData((StoryFeedTopTile) topTileList.get(0));
        this.tile2.bindData((StoryFeedTopTile) topTileList.get(1));
        this.tile3.bindData((StoryFeedTopTile) topTileList.get(2));
    }
}
