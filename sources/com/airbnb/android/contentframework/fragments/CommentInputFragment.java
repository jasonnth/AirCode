package com.airbnb.android.contentframework.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.requests.PostCommentRequest;
import com.airbnb.android.core.responses.PostCommentResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import p032rx.Observer;

public final class CommentInputFragment extends AirFragment {
    private static final String ARG_ARTICLE_ID = "arg_article_id";
    private static final String ARG_PARENT_COMMENT = "arg_parent_comment";
    public static final String RESULT_EXTRA_INPUT = "result_extra_input";
    @BindView
    AirToolbar airToolbar;
    @BindView
    EditText inputEditText;
    @BindView
    LoaderFrame loaderFrame;
    @State
    ArticleComment parentComment;
    final RequestListener<PostCommentResponse> postCommentListener = new C0699RL().onResponse(CommentInputFragment$$Lambda$1.lambdaFactory$(this)).onError(CommentInputFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static Intent newIntent(Context context, long articleId) {
        return ((Builder) AutoFragmentActivity.create(context, CommentInputFragment.class).putLong(ARG_ARTICLE_ID, articleId)).build();
    }

    public static Intent newIntentForCommentReply(Context context, long articleId, ArticleComment parentComment2) {
        return ((Builder) ((Builder) AutoFragmentActivity.create(context, CommentInputFragment.class).putLong(ARG_ARTICLE_ID, articleId)).putParcelable(ARG_PARENT_COMMENT, parentComment2)).build();
    }

    static /* synthetic */ void lambda$new$0(CommentInputFragment commentInputFragment, PostCommentResponse data) {
        commentInputFragment.showLoader(false);
        Intent extra = new Intent();
        extra.putExtra("result_extra_input", data.comment);
        commentInputFragment.getActivity().setResult(-1, extra);
        commentInputFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$1(CommentInputFragment commentInputFragment, AirRequestNetworkException e) {
        commentInputFragment.showLoader(false);
        NetworkUtil.tryShowErrorWithSnackbar(commentInputFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.parentComment = (ArticleComment) getArguments().getParcelable(ARG_PARENT_COMMENT);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5709R.layout.fragment_comment_input, container, false);
        bindViews(view);
        setToolbar(this.airToolbar);
        setHasOptionsMenu(true);
        if (this.parentComment != null) {
            this.inputEditText.setHint(String.format(getString(C5709R.string.story_comment_reply_hint), new Object[]{this.parentComment.getAuthor().getFirstName()}));
        }
        this.inputEditText.requestFocus();
        this.inputEditText.postDelayed(CommentInputFragment$$Lambda$3.lambdaFactory$(this), 200);
        this.inputEditText.addTextChangedListener(TextWatcherUtils.create(CommentInputFragment$$Lambda$4.lambdaFactory$(this)));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$2(CommentInputFragment commentInputFragment) {
        if (commentInputFragment.getActivity() != null) {
            KeyboardUtils.showSoftKeyboard(commentInputFragment.getActivity(), commentInputFragment.inputEditText);
        }
    }

    public void onPause() {
        KeyboardUtils.dismissSoftKeyboard((View) this.inputEditText);
        super.onPause();
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (this.inputEditText != null && this.inputEditText.length() != 0) {
            MenuItem item = menu.add(getString(C5709R.string.content_framework_add_comment_submit_button));
            item.setShowAsAction(2);
            item.setOnMenuItemClickListener(CommentInputFragment$$Lambda$5.lambdaFactory$(this));
        }
    }

    static /* synthetic */ boolean lambda$onPrepareOptionsMenu$4(CommentInputFragment commentInputFragment, MenuItem menuItem) {
        PostCommentRequest request;
        KeyboardUtils.dismissSoftKeyboard((View) commentInputFragment.inputEditText);
        long articleId = commentInputFragment.getArguments().getLong(ARG_ARTICLE_ID);
        String content = commentInputFragment.inputEditText.getText().toString();
        if (commentInputFragment.parentComment == null) {
            request = PostCommentRequest.postCommentForArticle(articleId, content);
        } else {
            request = PostCommentRequest.replyToArticleComment(articleId, content, commentInputFragment.parentComment.getId());
        }
        request.withListener((Observer) commentInputFragment.postCommentListener).execute(commentInputFragment.requestManager);
        ContentFrameworkAnalytics.trackAddComment(articleId, commentInputFragment.parentComment);
        commentInputFragment.showLoader(true);
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.TextInput;
    }

    public void showLoader(boolean show) {
        if (show) {
            this.loaderFrame.setVisibility(0);
            this.loaderFrame.startAnimation();
            return;
        }
        this.loaderFrame.finish();
    }
}
