package com.airbnb.android.contentframework.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.StoryTitleTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class StoryCardFeedItem extends LinearLayout {
    private static final int MAX_COUNT = 999;
    private static final String OVERFLOW_COUNT_LABEL = "999+";
    @BindView
    AirImageView authorImageView;
    @BindView
    AirTextView commentCount;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView likeCount;
    @BindView
    AirTextView storyCategory;
    @BindView
    StoryTitleTextView titleTextView;

    public StoryCardFeedItem(Context context) {
        super(context);
        init();
    }

    public StoryCardFeedItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StoryCardFeedItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C5709R.layout.story_card_feed_item, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
    }

    public void setTitleText(String titleText, String tagText) {
        this.titleTextView.bindData(titleText, tagText);
    }

    public void setImageUrl(String url) {
        this.imageView.setImageUrl(url);
    }

    public void setStoryCategory(String category, String categoryBackgroundColor) {
        if (TextUtils.isEmpty(category)) {
            this.storyCategory.setVisibility(8);
            return;
        }
        this.storyCategory.setVisibility(0);
        this.storyCategory.setText(category);
        if (!TextUtils.isEmpty(categoryBackgroundColor)) {
            ((GradientDrawable) this.storyCategory.getBackground()).setColor(Color.parseColor(categoryBackgroundColor));
        }
    }

    public void setImageUrlWithPreview(String url, String encodedImageHash) {
        this.imageView.setImageUrlWithBlurredPreview(url, encodedImageHash);
    }

    public void setAuthorImageUrl(String url) {
        ViewLibUtils.setVisibleIf(this.authorImageView, !TextUtils.isEmpty(url));
        this.authorImageView.setImageUrl(url);
    }

    public void setLikeCount(int count) {
        this.likeCount.setText(getCappedCountString(count));
    }

    public void setCommentCount(int count) {
        this.commentCount.setText(getCappedCountString(count));
    }

    private static String getCappedCountString(int count) {
        if (count <= MAX_COUNT) {
            return Integer.toString(count);
        }
        return OVERFLOW_COUNT_LABEL;
    }
}
