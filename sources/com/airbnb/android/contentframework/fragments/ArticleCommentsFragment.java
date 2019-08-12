package com.airbnb.android.contentframework.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.adapters.ArticleCommentsAdapter;
import com.airbnb.android.contentframework.adapters.ArticleCommentsAdapter.CommentsLoader;
import com.airbnb.android.contentframework.controller.CommentActionController;
import com.airbnb.android.contentframework.controller.CommentActionController.CommentActionFragmentFacade;
import com.airbnb.android.contentframework.events.ArticleCommentSectionUpdatedEvent;
import com.airbnb.android.contentframework.requests.GetArticleCommentRequest;
import com.airbnb.android.contentframework.responses.GetArticleCommentResponse;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.interfaces.OnHomeListener;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public final class ArticleCommentsFragment extends AirFragment implements CommentsLoader, CommentActionFragmentFacade, OnHomeListener {
    private static final String ARG_ARTICLE_ID = "key_article_id";
    private static final String ARG_TOTAL_COMMENTS = "key_total_comments";
    private static final int COMMENT_LIMIT = 20;
    private ArticleCommentsAdapter articleCommentsAdapter;
    @State
    long articleId;
    private CommentActionController commentActionController;
    final RequestListener<GetArticleCommentResponse> commentsListener = new C0699RL().onResponse(ArticleCommentsFragment$$Lambda$1.lambdaFactory$(this)).onError(ArticleCommentsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    ArrayList<ArticleComment> loadedComments;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    @State
    int totalComments;

    public static Intent intentForArticle(Context context, long articleId2, int totalComments2) {
        Bundle args = new Bundle();
        args.putLong(ARG_ARTICLE_ID, articleId2);
        args.putInt(ARG_TOTAL_COMMENTS, totalComments2);
        return TransparentActionBarActivity.intentForFragment(context, ArticleCommentsFragment.class, args);
    }

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        getAirActivity().setOnHomePressedListener(this);
        this.articleId = getArguments().getLong(ARG_ARTICLE_ID);
        this.totalComments = getArguments().getInt(ARG_TOTAL_COMMENTS);
        if (this.loadedComments == null) {
            this.loadedComments = new ArrayList<>();
        }
        this.commentActionController = new CommentActionController(this, this.articleId);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = false;
        View view = inflater.inflate(C5709R.layout.fragment_view_article_comments, container, false);
        bindViews(view);
        setUpToolbar();
        setUpRecyclerView();
        if (savedInstanceState != null) {
            ArticleCommentsAdapter articleCommentsAdapter2 = this.articleCommentsAdapter;
            ArrayList<ArticleComment> arrayList = this.loadedComments;
            int i = this.totalComments;
            if (this.loadedComments.size() < this.totalComments) {
                z = true;
            }
            articleCommentsAdapter2.addComments(arrayList, i, z);
        } else {
            loadFirstPage();
        }
        return view;
    }

    private void setUpRecyclerView() {
        this.articleCommentsAdapter = new ArticleCommentsAdapter(getContext(), this, this.commentActionController, this.totalComments);
        this.recyclerView.setAdapter(this.articleCommentsAdapter);
        showLoader(true);
    }

    private void setUpToolbar() {
        if (getAirActivity() instanceof TransparentActionBarActivity) {
            ((TransparentActionBarActivity) getAirActivity()).getAirToolbar().setVisibility(8);
        }
        setToolbar(this.toolbar);
    }

    private void loadFirstPage() {
        GetArticleCommentRequest.forArticle(this.articleId, 20, 0).withListener((Observer) this.commentsListener).execute(this.requestManager);
    }

    public void onLoadNextPage() {
        GetArticleCommentRequest.forArticle(this.articleId, 20, this.loadedComments.size()).withListener((Observer) this.commentsListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$0(ArticleCommentsFragment articleCommentsFragment, GetArticleCommentResponse response) {
        boolean z = false;
        articleCommentsFragment.showLoader(false);
        if (articleCommentsFragment.loadedComments == null) {
            articleCommentsFragment.loadedComments = new ArrayList<>(response.comments);
        } else {
            articleCommentsFragment.loadedComments.addAll(response.comments);
        }
        articleCommentsFragment.totalComments = response.metaData.count;
        ArticleCommentsAdapter articleCommentsAdapter2 = articleCommentsFragment.articleCommentsAdapter;
        List<ArticleComment> list = response.comments;
        int i = response.metaData.count;
        if (articleCommentsFragment.loadedComments.size() < articleCommentsFragment.totalComments) {
            z = true;
        }
        articleCommentsAdapter2.addComments(list, i, z);
    }

    static /* synthetic */ void lambda$new$1(ArticleCommentsFragment articleCommentsFragment, AirRequestNetworkException e) {
        articleCommentsFragment.showLoader(false);
        NetworkUtil.tryShowErrorWithSnackbar(articleCommentsFragment.getView(), e);
    }

    public AirbnbAccountManager getAirbnbAccountManager() {
        return this.mAccountManager;
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public void onCommentRemoved(long commentId) {
        this.articleCommentsAdapter.deleteCommentWithId(commentId);
        onCommentSectionUpdated();
    }

    public void onCommentChanged(ArticleCommentRowEpoxyModel model) {
        this.articleCommentsAdapter.notifyCommentChange(model);
        onCommentSectionUpdated();
    }

    public void onCommentAdded(ArticleComment articleComment) {
        this.articleCommentsAdapter.addToFirstComment(articleComment);
        onCommentSectionUpdated();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.commentActionController.handleActivityResult(requestCode, resultCode, data);
    }

    public boolean onHomePressed() {
        return false;
    }

    private void onCommentSectionUpdated() {
        this.mBus.post(new ArticleCommentSectionUpdatedEvent(this.articleId));
    }
}
