package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.views.StoryHeaderRowView;
import com.airbnb.android.core.models.Article;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class StoryHeaderRowEpoxyModel extends AirEpoxyModel<StoryHeaderRowView> {
    Article article;
    OnClickListener onClickListener;

    public void bind(StoryHeaderRowView view) {
        super.bind(view);
        view.setAuthorImageUrl(this.article.getAuthorPictureUrl());
        view.setAuthorName(this.article.getAuthorObject().getName());
        view.setAffiliateLabel(this.article.getLocalizedArticleLocation(), this.article.getPublishedAtHumanReadable());
        view.setOnClickListener(this.onClickListener);
    }
}
