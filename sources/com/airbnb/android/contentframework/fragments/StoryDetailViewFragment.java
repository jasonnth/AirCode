package com.airbnb.android.contentframework.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.contentframework.ArticleReadPercentageHelper;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.ContentFrameworkBindings;
import com.airbnb.android.contentframework.ContentFrameworkComponent;
import com.airbnb.android.contentframework.adapters.StoryDetailAdapter;
import com.airbnb.android.contentframework.adapters.StoryDetailAdapter.Delegate;
import com.airbnb.android.contentframework.controller.CommentActionController;
import com.airbnb.android.contentframework.controller.CommentActionController.CommentActionFragmentFacade;
import com.airbnb.android.contentframework.events.ArticleCommentSectionUpdatedEvent;
import com.airbnb.android.contentframework.events.ArticleCommentUpdatedEvent;
import com.airbnb.android.contentframework.events.ArticleDeletedEvent;
import com.airbnb.android.contentframework.events.ArticleEngagementUpdatedEvent;
import com.airbnb.android.contentframework.events.ArticleLikeCountUpdatedEvent;
import com.airbnb.android.contentframework.requests.DeleteArticleRequest;
import com.airbnb.android.contentframework.requests.GetArticleCommentRequest;
import com.airbnb.android.contentframework.requests.GetArticleRequest;
import com.airbnb.android.contentframework.responses.DeleteArticleResponse;
import com.airbnb.android.contentframework.responses.GetArticleCommentResponse;
import com.airbnb.android.contentframework.responses.GetArticleResponse;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel;
import com.airbnb.android.contentframework.views.StoryFeedItemDecoration;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.SimpleOnScrollListener;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.PickWishListActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.models.StoryImageDetails;
import com.airbnb.android.core.models.StoryProductLinkDetails;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.requests.ContentFrameworkLikeRequest;
import com.airbnb.android.core.requests.ContentFrameworkUnlikeRequest;
import com.airbnb.android.core.responses.ContentFrameworkLikeUnlikeResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.core.views.WishListIcon;
import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.core.wishlists.WishListSnackBarHelper;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.core.wishlists.WishListableType;
import com.airbnb.android.core.wishlists.WishListsChangedListener;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.image_viewer.ImageViewerActivity;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.squareup.otto.Subscribe;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class StoryDetailViewFragment extends AirFragment implements Delegate, CommentActionFragmentFacade, WishListsChangedListener {
    private static final String ARG_ARTICLE_ID = "arg_article_id";
    private static final String ARG_OPTIONAL_MESSAGE = "arg_optional_message";
    private static final String ARG_PARTIAL_ARTICLE = "arg_article";
    private static final String ARG_REFERRER = "arg_referrer";
    private static final int RC_CONFIRM_DELETE = 211;
    private static final int RC_FLAG_STORY = 210;
    private static final int TOP_COMMENT_COUNT = 3;
    AppRaterController appRaterController;
    @State
    Article article;
    /* access modifiers changed from: private */
    public StoryDetailAdapter articleAdapter;
    @State
    long articleId;
    /* access modifiers changed from: private */
    public int colCount;
    private CommentActionController commentActionController;
    @BindView
    AirTextView commentCount;
    final RequestListener<DeleteArticleResponse> deleteArticleListener = new C0699RL().onResponse(StoryDetailViewFragment$$Lambda$1.lambdaFactory$(this)).onError(StoryDetailViewFragment$$Lambda$2.lambdaFactory$(this)).build();
    final RequestListener<GetArticleCommentResponse> getArticleCommentListener = new C0699RL().onResponse(StoryDetailViewFragment$$Lambda$11.lambdaFactory$(this)).onError(StoryDetailViewFragment$$Lambda$12.lambdaFactory$(this)).build();
    final RequestListener<GetArticleResponse> getArticleListener = new C0699RL().onResponse(StoryDetailViewFragment$$Lambda$3.lambdaFactory$(this)).onError(StoryDetailViewFragment$$Lambda$4.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public GridLayoutManager layoutManager;
    @BindView
    AirTextView likeCount;
    @BindView
    AirImageView likeIcon;
    final RequestListener<ContentFrameworkLikeUnlikeResponse> likeListener = new C0699RL().onResponse(StoryDetailViewFragment$$Lambda$5.lambdaFactory$(this)).onError(StoryDetailViewFragment$$Lambda$6.lambdaFactory$(this)).onComplete(StoryDetailViewFragment$$Lambda$7.lambdaFactory$(this)).build();
    /* access modifiers changed from: 0000 */
    @State
    public boolean likeUnlikeArticleRequestInFlight;
    @BindView
    LoaderFrame loaderFrame;
    @State
    long onCreateTime;
    @State
    boolean optionalMessageShown;
    @State
    Article partialArticle;
    /* access modifiers changed from: private */
    public ArticleReadPercentageHelper readPercentageHelper;
    @BindView
    RecyclerView recyclerView;
    private final OnScrollListener scrollListener = new SimpleOnScrollListener() {
        private final Handler handler = new Handler();

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (StoryDetailViewFragment.this.articleAdapter != null) {
                StoryDetailViewFragment.this.articleAdapter.onScroll();
                this.handler.removeCallbacksAndMessages(null);
                this.handler.postDelayed(StoryDetailViewFragment$2$$Lambda$1.lambdaFactory$(this), 150);
            }
        }
    };
    @BindView
    AirToolbar toolbar;
    final RequestListener<ContentFrameworkLikeUnlikeResponse> unlikeListener = new C0699RL().onResponse(StoryDetailViewFragment$$Lambda$8.lambdaFactory$(this)).onError(StoryDetailViewFragment$$Lambda$9.lambdaFactory$(this)).onComplete(StoryDetailViewFragment$$Lambda$10.lambdaFactory$(this)).build();
    WishListManager wishListManager;

    public static Intent forArticleId(Context context, long articleId2, String referrer) {
        return ((Builder) AutoFragmentActivity.create(context, StoryDetailViewFragment.class).putAll(getBaseBundleForRequiredParams(articleId2, referrer))).build();
    }

    public static Intent forPartialArticle(Context context, Article partialArticle2, String referrer) {
        return forPartialArticle(context, partialArticle2, referrer, null);
    }

    public static Intent forPartialArticle(Context context, Article partialArticle2, String referrer, String optionalMessage) {
        return ((Builder) ((Builder) ((Builder) AutoFragmentActivity.create(context, StoryDetailViewFragment.class).putAll(getBaseBundleForRequiredParams(partialArticle2.getId(), referrer))).putParcelable(ARG_PARTIAL_ARTICLE, partialArticle2)).putString(ARG_OPTIONAL_MESSAGE, optionalMessage)).build();
    }

    private static Bundle getBaseBundleForRequiredParams(long articleId2, String referrer) {
        return ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong(ARG_ARTICLE_ID, articleId2)).putString(ARG_REFERRER, referrer)).toBundle();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ContentFrameworkComponent.Builder) ((ContentFrameworkBindings) CoreApplication.instance(getContext()).componentProvider()).contentFrameworkComponentProvider().get()).build().inject(this);
        if (savedInstanceState == null) {
            this.articleId = getArguments().getLong(ARG_ARTICLE_ID);
            this.appRaterController.incrementSignificantEvent();
        }
        this.readPercentageHelper = new ArticleReadPercentageHelper(this.articleId, "simple_article");
        this.commentActionController = new CommentActionController(this, this.articleId);
        this.mBus.register(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContentFrameworkAnalytics.trackReadArticleImpression(this.articleId, getArguments().getString(ARG_REFERRER), "simple_article");
        this.onCreateTime = System.currentTimeMillis();
        View view = inflater.inflate(C5709R.layout.fragment_story_detail_view, container, false);
        bindViews(view);
        setupRecyclerView();
        setToolbar(this.toolbar);
        this.toolbar.setTitle(C5709R.string.story_detail_page_title);
        this.wishListManager.addWishListsChangedListener(this);
        WishListSnackBarHelper.registerAndShowWithView(this, this.recyclerView, this.wishListManager);
        return view;
    }

    private void setupRecyclerView() {
        this.recyclerView.addOnScrollListener(this.scrollListener);
        this.colCount = getResources().getInteger(C5709R.integer.story_feed_grid_span);
        this.layoutManager = new GridLayoutManager(getContext(), this.colCount);
        this.layoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(int position) {
                if (StoryDetailViewFragment.this.articleAdapter == null || !StoryDetailViewFragment.this.articleAdapter.isRelatedArticleRow(position)) {
                    return StoryDetailViewFragment.this.colCount;
                }
                return 1;
            }
        });
        this.recyclerView.setLayoutManager(this.layoutManager);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (this.article != null) {
            initWithArticle(this.article);
            loadCommentSection();
            return;
        }
        if (getArguments().getParcelable(ARG_PARTIAL_ARTICLE) != null) {
            this.partialArticle = (Article) getArguments().getParcelable(ARG_PARTIAL_ARTICLE);
            initWithArticle(this.partialArticle);
        }
        loadArticle();
        setHasOptionsMenu(true);
        String message = getArguments().getString(ARG_OPTIONAL_MESSAGE);
        if (!this.optionalMessageShown && !TextUtils.isEmpty(message)) {
            this.optionalMessageShown = true;
            toastMessageWithSnarkBar(message);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (this.article != null || this.partialArticle != null) {
            menu.findItem(C5709R.C5711id.delete).setVisible(isViewingOwnStory());
            ((WishListIcon) menu.findItem(C5709R.C5711id.wishlist).getActionView()).initIfNeeded(WishListableData.forStoryArticle(this.article != null ? this.article : this.partialArticle).source(C2813WishlistSource.ContentFramework).allowAutoAdd(false).build());
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.article == null) {
            return false;
        }
        if (item.getItemId() == C5709R.C5711id.share) {
            ContentFrameworkAnalytics.trackShareArticle(this.articleId);
            startActivity(ShareActivityIntents.newIntentForArticle(getContext(), this.article));
            return true;
        } else if (item.getItemId() != C5709R.C5711id.delete) {
            return false;
        } else {
            deleteStoryWithConfirmation();
            return true;
        }
    }

    public void onDestroyView() {
        ContentFrameworkAnalytics.trackLeaveArticle(this.articleId, "simple_article", getElapsedTimeSinceImpression());
        this.recyclerView.removeOnScrollListener(this.scrollListener);
        this.wishListManager.removeWishListsChangedListener(this);
        WishListSnackBarHelper.unregister(this);
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.commentActionController.handleActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            if (requestCode == RC_FLAG_STORY) {
                toastMessageWithSnarkBar(getString(C5709R.string.story_action_report_success));
                this.articleAdapter.onReportSuccess();
            } else if (requestCode == 211) {
                this.loaderFrame.startAnimation();
                ContentFrameworkAnalytics.trackDeleteStory(this.articleId);
                new DeleteArticleRequest(this.articleId).withListener((Observer) this.deleteArticleListener).execute(this.requestManager);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onAddCommentButtonClicked() {
        this.commentActionController.onAddComment();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onViewCommentButtonClicked() {
        this.commentActionController.onShowAllComments(this.article == null ? 0 : this.article.getCommentCount());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onLikeButtonClicked() {
        if (this.article != null && !this.likeUnlikeArticleRequestInFlight) {
            ContentFrameworkAnalytics.trackArticleLike(this.articleId, this.article.isLiked());
            this.likeUnlikeArticleRequestInFlight = true;
            if (this.article.isLiked()) {
                ContentFrameworkUnlikeRequest.requestForArticle(this.articleId).withListener((Observer) this.unlikeListener).execute(this.requestManager);
            } else {
                ContentFrameworkLikeRequest.requestForArticle(this.articleId).withListener((Observer) this.likeListener).execute(this.requestManager);
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleArticleLikeUnlikeSuccessResult() {
        int likeCountDelta;
        if (this.article.isLiked()) {
            this.article.setLiked(false);
            likeCountDelta = -1;
        } else {
            this.article.setLiked(true);
            likeCountDelta = 1;
        }
        this.article.setLikeCount(Math.max(0, this.article.getLikeCount() + likeCountDelta));
        this.mBus.post(new ArticleLikeCountUpdatedEvent(this.articleId, likeCountDelta));
        updateEngagementBarState(this.article);
    }

    private void initWithArticle(Article article2) {
        this.articleAdapter = new StoryDetailAdapter(getActivity(), article2, this, this.commentActionController);
        this.recyclerView.setAdapter(this.articleAdapter);
        this.recyclerView.addItemDecoration(new StoryFeedItemDecoration(getContext(), this.colCount, this.articleAdapter));
        updateEngagementBarState(article2);
    }

    private void loadArticle() {
        new GetArticleRequest(this.articleId).withListener((Observer) this.getArticleListener).execute(this.requestManager);
    }

    private void loadCommentSection() {
        GetArticleCommentRequest.hotCommentsForArticle(this.articleId, 3).withListener((Observer) this.getArticleCommentListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public long getElapsedTimeSinceImpression() {
        return System.currentTimeMillis() - this.onCreateTime;
    }

    private void updateEngagementBarState(Article article2) {
        this.likeIcon.setImageResource(article2.isLiked() ? C5709R.C5710drawable.ic_like_filled : C5709R.C5710drawable.ic_like);
        this.likeCount.setText(String.valueOf(article2.getLikeCount()));
        this.commentCount.setText(String.valueOf(article2.getCommentCount()));
    }

    static /* synthetic */ void lambda$new$0(StoryDetailViewFragment storyDetailViewFragment, DeleteArticleResponse data) {
        storyDetailViewFragment.mBus.post(new ArticleDeletedEvent(storyDetailViewFragment.articleId));
        storyDetailViewFragment.loaderFrame.finish();
        storyDetailViewFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$1(StoryDetailViewFragment storyDetailViewFragment, AirRequestNetworkException e) {
        storyDetailViewFragment.loaderFrame.finish();
        NetworkUtil.tryShowErrorWithSnackbar(storyDetailViewFragment.getView(), e);
    }

    static /* synthetic */ void lambda$new$2(StoryDetailViewFragment storyDetailViewFragment, GetArticleResponse data) {
        storyDetailViewFragment.article = data.article;
        if (storyDetailViewFragment.articleAdapter == null) {
            storyDetailViewFragment.initWithArticle(storyDetailViewFragment.article);
        } else {
            storyDetailViewFragment.articleAdapter.setFullArticle(storyDetailViewFragment.article);
        }
        storyDetailViewFragment.updateEngagementBarState(storyDetailViewFragment.article);
        storyDetailViewFragment.mBus.post(new ArticleEngagementUpdatedEvent(storyDetailViewFragment.articleId, data.article.getLikeCount(), storyDetailViewFragment.article.getCommentCount()));
        storyDetailViewFragment.getActivity().supportInvalidateOptionsMenu();
        storyDetailViewFragment.loadCommentSection();
    }

    static /* synthetic */ void lambda$new$3(StoryDetailViewFragment storyDetailViewFragment, AirRequestNetworkException e) {
        Toast.makeText(storyDetailViewFragment.getActivity(), C5709R.string.fetch_story_failure, 0).show();
        storyDetailViewFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$10(StoryDetailViewFragment storyDetailViewFragment, GetArticleCommentResponse response) {
        int commentCount2 = response.metaData.count;
        storyDetailViewFragment.article.setCommentCount(commentCount2);
        storyDetailViewFragment.articleAdapter.updateCommentSection(response.comments, commentCount2);
        storyDetailViewFragment.updateEngagementBarState(storyDetailViewFragment.article);
        storyDetailViewFragment.mBus.post(new ArticleCommentUpdatedEvent(storyDetailViewFragment.articleId, commentCount2));
    }

    static /* synthetic */ void lambda$new$11(StoryDetailViewFragment storyDetailViewFragment, AirRequestNetworkException e) {
        Toast.makeText(storyDetailViewFragment.getActivity(), C5709R.string.story_fetch_top_comments_failure, 0).show();
        storyDetailViewFragment.articleAdapter.updateCommentSection(new ArrayList(), 0);
    }

    public void onClickRelatedArticle(Article relatedArticle) {
        ContentFrameworkAnalytics.trackRelatedArticleClick(this.article.getId(), relatedArticle.getId(), "simple_article");
        startActivity(forPartialArticle(getContext(), relatedArticle, NavigationTag.StoryDetail.trackingName));
    }

    public void onAuthorRowClicked() {
        if (this.article != null && this.article.getAuthorObject() != null) {
            long authorId = this.article.getAuthorObject().getId();
            ContentFrameworkAnalytics.trackAuthorRowClicked(this.articleId, authorId);
            startActivity(UserProfileIntents.intentForUserId(getContext(), authorId));
        }
    }

    public AirbnbAccountManager getAirbnbAccountManager() {
        return this.mAccountManager;
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public void onCommentRemoved(long commentId) {
        loadCommentSection();
    }

    public void onCommentChanged(ArticleCommentRowEpoxyModel model) {
        this.articleAdapter.notifyCommentChange(model);
    }

    public void onCommentAdded(ArticleComment articleComment) {
        this.commentActionController.onShowAllComments(this.article.getCommentCount() + 1);
        loadCommentSection();
    }

    public void onProductClicked(StoryProductLinkDetails details) {
        ContentFrameworkAnalytics.trackProductLinkClick(this.article.getId(), details);
        switch (details.getObjectType()) {
            case Listing:
                startActivity(P3ActivityIntents.withListingId(getContext(), details.getObjectId()));
                return;
            case Place:
                startActivity(ReactNativeIntents.intentForPlaceP3(getContext(), Long.valueOf(details.getObjectId()), details.getTitle(), null));
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("unknown product link object type: " + details.getObjectTypeString()));
                return;
        }
    }

    public void onWishlistClicked(StoryProductLinkDetails details) {
        ContentFrameworkAnalytics.trackProductLinkWishlistClick(this.article.getId(), details, this.wishListManager);
        WishListableType type = details.getWishListableType();
        if (type != null) {
            WishListableData wishListableData = WishListableData.forType(type, details.getObjectId()).source(C2813WishlistSource.ContentFramework).allowAutoAdd(false).build();
            if (details.isWishlisted(this.wishListManager)) {
                this.wishListManager.deleteItemFromAllWishLists(wishListableData);
            } else {
                startActivity(PickWishListActivityIntents.forData(getContext(), wishListableData));
            }
        }
    }

    public WishListManager getWishlistManager() {
        return this.wishListManager;
    }

    public void onImageClick(StoryImageDetails details, View view, int photoIdx) {
        Intent intent = ImageViewerActivity.newIntent(getContext(), this.articleAdapter.getImageElementUrls(), photoIdx, "article", this.articleId);
        if (AndroidVersion.isAtLeastLollipopMR1()) {
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, ViewCompat.getTransitionName(view)).toBundle());
        } else {
            startActivity(intent);
        }
        ContentFrameworkAnalytics.trackImageClick(this.articleId, details.getImageIdentifier(), "simple_article");
    }

    public void onWishListsChanged(List<WishList> list, WishListChangeInfo changeInfo) {
        this.articleAdapter.notifyWishListsChanged(changeInfo);
    }

    @Subscribe
    public void onArticleCommentSectionUpdated(ArticleCommentSectionUpdatedEvent event) {
        loadCommentSection();
    }

    @Subscribe
    public void onStoryDeleted(ArticleDeletedEvent articleDeletedEvent) {
        this.articleAdapter.deleteStoryIfPresentInRelatedStories(articleDeletedEvent.articleId);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.StoryDetail;
    }

    private void deleteStoryWithConfirmation() {
        ZenDialog.builder().withTitle(C5709R.string.content_framework_confirm_delete_title).withBodyText(C5709R.string.content_framework_confirm_delete_body_story).withDualButton(C5709R.string.cancel, 0, C5709R.string.confirm, 211).create().show(getFragmentManager(), (String) null);
    }

    public void onReportStoryClicked() {
        ContentFrameworkAnalytics.trackReportStory(this.articleId);
        startActivityForResult(ReactNativeIntents.intentForFlagContent(getContext(), this.articleId, null, FlagContent.Story), RC_FLAG_STORY);
    }

    private void toastMessageWithSnarkBar(String message) {
        new SnackbarWrapper().view(getView()).body(message).duration(0).buildAndShow();
    }

    private boolean isViewingOwnStory() {
        if (this.article != null) {
            if (this.mAccountManager.getCurrentUserId() == this.article.getAuthorObject().getId()) {
                return true;
            }
            return false;
        } else if (this.partialArticle == null || this.partialArticle.getAuthorObject() == null) {
            return false;
        } else {
            if (this.mAccountManager.getCurrentUserId() != this.partialArticle.getAuthorObject().getId()) {
                return false;
            }
            return true;
        }
    }
}
