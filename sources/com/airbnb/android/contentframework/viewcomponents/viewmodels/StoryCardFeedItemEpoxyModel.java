package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.views.StoryCardFeedItem;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.Article;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class StoryCardFeedItemEpoxyModel extends AirEpoxyModel<StoryCardFeedItem> {
    Article article;
    DisplayOptions displayOptions;
    OnClickListener onClickListener;

    public void bind(StoryCardFeedItem view) {
        super.bind(view);
        if (this.displayOptions != null) {
            this.displayOptions.adjustLayoutParams(view);
        }
        view.setTitleText(this.article.getTitle(), this.article.getLocalizedArticleLocation());
        if (this.article.getCoverImagePreview() != null) {
            view.setImageUrlWithPreview(this.article.getCoverImageUrl(), this.article.getCoverImagePreview());
        } else {
            view.setImageUrl(this.article.getCoverImageUrl());
        }
        view.setAuthorImageUrl(this.article.getAuthorPictureUrl());
        view.setLikeCount(this.article.getLikeCount());
        view.setCommentCount(this.article.getCommentCount());
        view.setOnClickListener(this.onClickListener);
        view.setStoryCategory(this.article.getArticleCategory(), this.article.getArticleCategoryBgColor());
    }

    public void unbind(StoryCardFeedItem view) {
        super.unbind(view);
        view.setImageUrl(null);
        view.setAuthorImageUrl(null);
        view.setOnClickListener(null);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        if (this.displayOptions != null) {
            return this.displayOptions.getSpanSize(totalSpanCount);
        }
        return super.getSpanSize(totalSpanCount, position, itemCount);
    }
}
