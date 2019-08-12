package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment;
import com.airbnb.android.core.models.StoryFeedTopTile;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryFeedTopTileView extends FrameLayout {
    private static final int MAIN_TEXT_SIZE_REGULAR = 24;
    private static final int MAIN_TEXT_SIZE_SMALL = 19;
    private static final int MAX_LENGTH_FOR_REGULAR = 2;
    @BindView
    AirImageView image;
    @BindView
    AirTextView mainText;
    @BindView
    AirTextView secondaryText;

    public StoryFeedTopTileView(Context context) {
        super(context);
        init(context);
    }

    public StoryFeedTopTileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StoryFeedTopTileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, C5709R.layout.story_feed_top_tile_view, this);
        ButterKnife.bind((View) this);
    }

    public void bindData(StoryFeedTopTile topTile) {
        this.image.setImageUrl(topTile.getImageUrl());
        this.mainText.setText(topTile.getMainText());
        if (topTile.getMainText().length() <= 2) {
            this.mainText.setTextSize(24.0f);
        } else {
            this.mainText.setTextSize(19.0f);
        }
        this.secondaryText.setText(topTile.getSecondaryText());
        setOnClickListener(StoryFeedTopTileView$$Lambda$1.lambdaFactory$(this, topTile));
    }

    static /* synthetic */ void lambda$bindData$0(StoryFeedTopTileView storyFeedTopTileView, StoryFeedTopTile topTile, View v) {
        ContentFrameworkAnalytics.trackTopTileClicked(topTile);
        storyFeedTopTileView.getContext().startActivity(StoryFeedFragment.intentForTopTile(storyFeedTopTileView.getContext(), topTile));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), 1073741824);
        super.onMeasure(measureSpec, measureSpec);
    }
}
