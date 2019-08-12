package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ArticleCommentRowEpoxyModel$$Lambda$1 implements OnClickListener {
    private final ArticleCommentRowEpoxyModel arg$1;

    private ArticleCommentRowEpoxyModel$$Lambda$1(ArticleCommentRowEpoxyModel articleCommentRowEpoxyModel) {
        this.arg$1 = articleCommentRowEpoxyModel;
    }

    public static OnClickListener lambdaFactory$(ArticleCommentRowEpoxyModel articleCommentRowEpoxyModel) {
        return new ArticleCommentRowEpoxyModel$$Lambda$1(articleCommentRowEpoxyModel);
    }

    public void onClick(View view) {
        this.arg$1.commentActionListener.onCommentLikeClicked(this.arg$1.comment, this.arg$1);
    }
}
