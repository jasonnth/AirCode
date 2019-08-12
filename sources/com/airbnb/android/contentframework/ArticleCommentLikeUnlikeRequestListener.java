package com.airbnb.android.contentframework;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.contentframework.controller.CommentActionController.CommentActionFragmentFacade;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.responses.ContentFrameworkLikeUnlikeResponse;
import com.airbnb.android.core.utils.NetworkUtil;

public final class ArticleCommentLikeUnlikeRequestListener extends NonResubscribableRequestListener<ContentFrameworkLikeUnlikeResponse> {
    private final ArticleComment comment;
    private final CommentActionFragmentFacade fragmentFacade;
    private final boolean isLikeRequest;
    private final ArticleCommentRowEpoxyModel model;

    public ArticleCommentLikeUnlikeRequestListener(CommentActionFragmentFacade fragmentFacade2, ArticleComment comment2, ArticleCommentRowEpoxyModel model2, boolean isLikeRequest2) {
        this.fragmentFacade = fragmentFacade2;
        this.comment = comment2;
        this.model = model2;
        this.isLikeRequest = isLikeRequest2;
    }

    public void onResponse(ContentFrameworkLikeUnlikeResponse data) {
        this.comment.setLiked(this.isLikeRequest);
        this.comment.setLikeCount(Integer.valueOf((this.isLikeRequest ? 1 : -1) + this.comment.getLikeCount().intValue()));
        this.fragmentFacade.onCommentChanged(this.model);
    }

    public void onErrorResponse(AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(this.fragmentFacade.getView(), e);
    }
}
