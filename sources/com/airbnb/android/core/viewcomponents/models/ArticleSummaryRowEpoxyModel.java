package com.airbnb.android.core.viewcomponents.models;

import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ArticleTag;
import com.airbnb.p027n2.components.ArticleSummaryRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ArticleSummaryRowEpoxyModel extends AirEpoxyModel<ArticleSummaryRow> {
    Article article;
    DisplayOptions displayOptions;
    boolean loading;
    OnClickListener onClickListener;

    public void bind(ArticleSummaryRow articleSummaryRow) {
        int i = 0;
        String str = null;
        super.bind(articleSummaryRow);
        articleSummaryRow.showTag(true);
        articleSummaryRow.setTitle(this.loading ? null : this.article.getTitle());
        articleSummaryRow.setTag(this.loading ? null : getTag());
        articleSummaryRow.setCommentCount(this.loading ? 0 : this.article.getCommentCount());
        if (!this.loading) {
            i = this.article.getLikeCount();
        }
        articleSummaryRow.setLikeCount(i);
        if (!this.loading) {
            str = this.article.getCoverImageUrl();
        }
        articleSummaryRow.setCoverImageUrl(str);
        articleSummaryRow.setOnClickListener(this.onClickListener);
    }

    public void unbind(ArticleSummaryRow articleSummaryRow) {
        super.unbind(articleSummaryRow);
        articleSummaryRow.setCoverImageUrl(null);
        articleSummaryRow.setOnClickListener(null);
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    private String getTag() {
        if (!TextUtils.isEmpty(this.article.getAuthor())) {
            return this.article.getAuthor();
        }
        if (this.article.getTags().isEmpty()) {
            return null;
        }
        return ((ArticleTag) this.article.getTags().get(0)).getText();
    }
}
