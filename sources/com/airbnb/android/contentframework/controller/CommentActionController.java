package com.airbnb.android.contentframework.controller;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.p000v4.app.FragmentManager;
import android.view.View;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.contentframework.ArticleCommentLikeUnlikeRequestListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.CommentActionListener;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.fragments.ArticleCommentsFragment;
import com.airbnb.android.contentframework.fragments.CommentInputFragment;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.requests.ContentFrameworkLikeRequest;
import com.airbnb.android.core.requests.ContentFrameworkUnlikeRequest;
import com.airbnb.android.core.requests.DeleteCommentRequest;
import com.airbnb.android.core.responses.DeleteCommentResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import p032rx.Observer;

public class CommentActionController implements CommentActionListener {
    private static final int RC_ADD_COMMENT = 234;
    private static final int RC_CONFIRM_DELETE = 233;
    private static final int REPLY_COMMENT_MENU_INDEX = 0;
    private static final int REPORT_COMMENT_MENU_INDEX = 1;
    private final long articleId;
    /* access modifiers changed from: private */
    public long commentIdToBeDeleted;
    /* access modifiers changed from: private */
    public final CommentActionFragmentFacade fragmentFacade;
    private ArticleCommentLikeUnlikeRequestListener likeUnlikeRequestListener;

    public interface CommentActionFragmentFacade {
        AirActivity getAirActivity();

        AirbnbAccountManager getAirbnbAccountManager();

        Context getContext();

        FragmentManager getFragmentManager();

        RequestManager getRequestManager();

        View getView();

        void onCommentAdded(ArticleComment articleComment);

        void onCommentChanged(ArticleCommentRowEpoxyModel articleCommentRowEpoxyModel);

        void onCommentRemoved(long j);

        void startActivity(Intent intent);

        void startActivityForResult(Intent intent, int i);
    }

    public CommentActionController(CommentActionFragmentFacade fragmentFacade2, long articleId2) {
        this.fragmentFacade = fragmentFacade2;
        this.articleId = articleId2;
    }

    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case RC_CONFIRM_DELETE /*233*/:
                    DeleteCommentRequest.requestForCommentId(this.commentIdToBeDeleted).withListener((Observer) new NonResubscribableRequestListener<DeleteCommentResponse>() {
                        public void onResponse(DeleteCommentResponse data) {
                            CommentActionController.this.fragmentFacade.onCommentRemoved(CommentActionController.this.commentIdToBeDeleted);
                        }

                        public void onErrorResponse(AirRequestNetworkException e) {
                            NetworkUtil.tryShowErrorWithSnackbar(CommentActionController.this.fragmentFacade.getView(), e);
                        }
                    }).execute(this.fragmentFacade.getRequestManager());
                    ContentFrameworkAnalytics.trackDeleteComment(this.articleId, this.commentIdToBeDeleted);
                    return;
                case 234:
                    this.fragmentFacade.onCommentAdded((ArticleComment) data.getParcelableExtra("result_extra_input"));
                    return;
                default:
                    return;
            }
        }
    }

    public void onShowAllComments(int totalCommentCount) {
        this.fragmentFacade.startActivity(ArticleCommentsFragment.intentForArticle(this.fragmentFacade.getContext(), this.articleId, totalCommentCount));
    }

    public void onAddComment() {
        this.fragmentFacade.startActivityForResult(CommentInputFragment.newIntent(this.fragmentFacade.getContext(), this.articleId), 234);
    }

    public void onAuthorProfileClicked(ArticleComment comment) {
        ContentFrameworkAnalytics.trackCommentAuthorClicked(this.articleId, comment.getAuthor().getId(), comment.getId());
        this.fragmentFacade.startActivity(UserProfileIntents.intentForUserId(this.fragmentFacade.getContext(), comment.getAuthor().getId()));
    }

    public void onParentCommentAuthorProfileClicked(ArticleComment comment) {
        long profileId = comment.getParentComment().getAuthor().getId();
        ContentFrameworkAnalytics.trackParentCommentAuthorClicked(this.articleId, profileId, comment.getId());
        this.fragmentFacade.startActivity(UserProfileIntents.intentForUserId(this.fragmentFacade.getContext(), profileId));
    }

    public void onCommentLikeClicked(ArticleComment comment, ArticleCommentRowEpoxyModel model) {
        boolean z = false;
        if (this.likeUnlikeRequestListener == null || !this.fragmentFacade.getRequestManager().hasRequests(this.likeUnlikeRequestListener)) {
            if (comment.isLiked()) {
                this.likeUnlikeRequestListener = new ArticleCommentLikeUnlikeRequestListener(this.fragmentFacade, comment, model, false);
                ContentFrameworkUnlikeRequest.requestForComment(comment.getId()).withListener((Observer) this.likeUnlikeRequestListener).execute(this.fragmentFacade.getRequestManager());
            } else {
                this.likeUnlikeRequestListener = new ArticleCommentLikeUnlikeRequestListener(this.fragmentFacade, comment, model, false);
                ContentFrameworkLikeRequest.requestForComment(comment.getId()).withListener((Observer) this.likeUnlikeRequestListener).execute(this.fragmentFacade.getRequestManager());
            }
            long j = this.articleId;
            long id = comment.getId();
            if (!comment.isLiked()) {
                z = true;
            }
            ContentFrameworkAnalytics.trackCommentLike(j, id, z);
        }
    }

    public void onShowMenuForComment(ArticleComment comment) {
        boolean isCommentAuthor = comment.getAuthor().getId() == this.fragmentFacade.getAirbnbAccountManager().getCurrentUserId();
        new Builder(this.fragmentFacade.getAirActivity()).setItems(isCommentAuthor ? C5709R.array.comment_actions_for_comment_writer : C5709R.array.comment_actions_for_non_comment_writer, CommentActionController$$Lambda$1.lambdaFactory$(this, isCommentAuthor, comment)).create().show();
    }

    static /* synthetic */ void lambda$onShowMenuForComment$0(CommentActionController commentActionController, boolean isCommentAuthor, ArticleComment comment, DialogInterface dialog, int which) {
        if (isCommentAuthor) {
            commentActionController.deleteComment(comment);
            return;
        }
        switch (which) {
            case 0:
                commentActionController.replyToComment(comment);
                return;
            case 1:
                commentActionController.reportComment(comment);
                return;
            default:
                return;
        }
    }

    private void replyToComment(ArticleComment comment) {
        this.fragmentFacade.startActivityForResult(CommentInputFragment.newIntentForCommentReply(this.fragmentFacade.getContext(), this.articleId, comment), 234);
    }

    private void deleteComment(ArticleComment comment) {
        this.commentIdToBeDeleted = comment.getId();
        ZenDialog.builder().withTitle(C5709R.string.content_framework_confirm_delete_title).withBodyText(C5709R.string.content_framework_confirm_delete_body).withDualButton(C5709R.string.cancel, 0, C5709R.string.confirm, RC_CONFIRM_DELETE).create().show(this.fragmentFacade.getFragmentManager(), (String) null);
    }

    private void reportComment(ArticleComment comment) {
        if (comment.getUserFlag() != null) {
            new SnackbarWrapper().view(this.fragmentFacade.getView()).body(C5709R.string.story_comment_already_reported).duration(0).buildAndShow();
            return;
        }
        ContentFrameworkAnalytics.trackReportComment(this.articleId, comment.getId());
        this.fragmentFacade.startActivity(ReactNativeIntents.intentForFlagContent(this.fragmentFacade.getContext(), this.articleId, comment.getUserFlag() == null ? null : Long.valueOf(comment.getUserFlag().getId()), FlagContent.Story));
    }
}
