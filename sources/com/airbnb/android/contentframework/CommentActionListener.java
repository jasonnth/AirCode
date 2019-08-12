package com.airbnb.android.contentframework;

import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel;
import com.airbnb.android.core.models.ArticleComment;

public interface CommentActionListener {
    void onAddComment();

    void onAuthorProfileClicked(ArticleComment articleComment);

    void onCommentLikeClicked(ArticleComment articleComment, ArticleCommentRowEpoxyModel articleCommentRowEpoxyModel);

    void onParentCommentAuthorProfileClicked(ArticleComment articleComment);

    void onShowAllComments(int i);

    void onShowMenuForComment(ArticleComment articleComment);
}
