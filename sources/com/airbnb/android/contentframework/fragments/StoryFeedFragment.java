package com.airbnb.android.contentframework.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.StoriesExperimentUtil;
import com.airbnb.android.contentframework.adapters.StoryFeedAdapter;
import com.airbnb.android.contentframework.adapters.StoryFeedAdapter.Listener;
import com.airbnb.android.contentframework.events.ArticleCommentUpdatedEvent;
import com.airbnb.android.contentframework.events.ArticleDeletedEvent;
import com.airbnb.android.contentframework.events.ArticleEngagementUpdatedEvent;
import com.airbnb.android.contentframework.events.ArticleLikeCountUpdatedEvent;
import com.airbnb.android.contentframework.requests.StoryFeedRequest;
import com.airbnb.android.contentframework.responses.StoryFeedResponse;
import com.airbnb.android.contentframework.views.StoryFeedItemDecoration;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.StoryFeedTopTile;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.NavigationPill;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.squareup.otto.Subscribe;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class StoryFeedFragment extends AirFragment implements Listener {
    private static final String ARG_MODE = "arg_mode";
    private static final String ARG_SEARCH_TERM = "arg_search_term";
    private static final String ARG_TOP_TILE = "arg_top_tile";
    private static final String ARG_USER_ID = "arg_user_id";
    private static final String ARG_USER_NAME = "arg_user_name";
    /* access modifiers changed from: private */
    public StoryFeedAdapter adapter;
    /* access modifiers changed from: private */
    public int colCount;
    @BindView
    NavigationPill composerPill;
    @State
    long impressionStartTime;
    @State
    boolean isLoading;
    @State
    ArrayList<Article> loadedArticles = new ArrayList<>();
    @State
    Mode mode;
    @State
    String paginationCursor;
    @BindView
    RecyclerView recyclerView;
    @BindView
    View searchEmptyStateView;
    final RequestListener<StoryFeedResponse> storyFeedRequestListener = new RequestListener<StoryFeedResponse>() {
        public void onResponse(StoryFeedResponse data) {
            StoryFeedFragment.this.isLoading = false;
            if (StoryFeedFragment.this.swipeRefreshLayout.isRefreshing()) {
                StoryFeedFragment.this.swipeRefreshLayout.setRefreshing(false);
                StoryFeedFragment.this.loadedArticles = new ArrayList<>(data.articles);
                StoryFeedFragment.this.topTiles = data.getTopTiles() == null ? new ArrayList<>() : new ArrayList<>(data.getTopTiles());
                StoryFeedFragment.this.adapter.setStories(data.getTopTiles(), data.articles, data.hasNextPage());
                if (StoryFeedFragment.this.isViewingSearchResult()) {
                    ViewLibUtils.setVisibleIf(StoryFeedFragment.this.searchEmptyStateView, StoryFeedFragment.this.loadedArticles.isEmpty());
                }
            } else {
                StoryFeedFragment.this.loadedArticles.addAll(data.articles);
                StoryFeedFragment.this.adapter.appendStories(data.articles, data.hasNextPage());
                ContentFrameworkAnalytics.trackLoadMoreStories(StoryFeedFragment.this.loadedArticles.size(), StoryFeedFragment.this.mode);
            }
            StoryFeedFragment.this.paginationCursor = data.tailCursor();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            StoryFeedFragment.this.isLoading = false;
            StoryFeedFragment.this.swipeRefreshLayout.setRefreshing(false);
            ErrorUtils.showErrorUsingSnackbar(StoryFeedFragment.this.getView(), C5709R.string.error_ro_unable_to_load);
        }
    };
    @BindView
    AirSwipeRefreshLayout swipeRefreshLayout;
    @BindView
    AirToolbar toolbar;
    @State
    ArrayList<StoryFeedTopTile> topTiles;

    public enum Mode {
        Feed(NavigationTag.StoryFeed),
        TopTile(NavigationTag.StoryTopTileFeed),
        SearchResult(NavigationTag.StorySearchResult),
        UserStories(NavigationTag.UserProfile);
        
        public final NavigationTag navigationTag;

        private Mode(NavigationTag navigationTag2) {
            this.navigationTag = navigationTag2;
        }
    }

    public static StoryFeedFragment instance(Mode mode2) {
        return (StoryFeedFragment) ((FragmentBundleBuilder) FragmentBundler.make(new StoryFeedFragment()).putSerializable(ARG_MODE, mode2)).build();
    }

    public static StoryFeedFragment instanceForSearchTerm(String searchTerm) {
        return (StoryFeedFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new StoryFeedFragment()).putSerializable(ARG_MODE, Mode.SearchResult)).putString(ARG_SEARCH_TERM, searchTerm)).build();
    }

    public static Intent intentForUserStories(Context context, long userId, String userName) {
        return ((Builder) ((Builder) ((Builder) AutoFragmentActivity.create(context, StoryFeedFragment.class).putSerializable(ARG_MODE, Mode.UserStories)).putLong(ARG_USER_ID, userId)).putString(ARG_USER_NAME, userName)).build();
    }

    public static Intent intentForTopTile(Context context, StoryFeedTopTile topTile) {
        return ((Builder) ((Builder) AutoFragmentActivity.create(context, StoryFeedFragment.class).putSerializable(ARG_MODE, Mode.TopTile)).putParcelable(ARG_TOP_TILE, topTile)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.colCount = getResources().getInteger(C5709R.integer.story_feed_grid_span);
        this.mode = (Mode) getArguments().getSerializable(ARG_MODE);
        this.adapter = new StoryFeedAdapter(getContext(), this, this.mode);
        this.mBus.register(this);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5709R.layout.fragment_story_feed, container, false);
        bindViews(view);
        setupComposerPill();
        setupToolbar();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), this.colCount);
        layoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(int position) {
                if (StoryFeedFragment.this.adapter.isFullSpanRow(position)) {
                    return StoryFeedFragment.this.colCount;
                }
                return 1;
            }
        });
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addItemDecoration(new StoryFeedItemDecoration(getContext(), this.colCount, this.adapter));
        this.swipeRefreshLayout.setScrollableChild(this.recyclerView);
        this.swipeRefreshLayout.setOnRefreshListener(StoryFeedFragment$$Lambda$1.lambdaFactory$(this));
        this.swipeRefreshLayout.setProgressViewOffset(false, 0, ViewUtils.getActionBarHeight(getContext()) + getResources().getDimensionPixelOffset(C5709R.dimen.n2_vertical_padding_medium));
        if (this.loadedArticles.isEmpty()) {
            this.swipeRefreshLayout.setRefreshing(true);
            loadFirstPage(false);
        } else {
            this.adapter.setStories(this.topTiles, this.loadedArticles, true);
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(StoryFeedFragment storyFeedFragment) {
        ContentFrameworkAnalytics.trackPullToRefresh(storyFeedFragment.loadedArticles.size(), storyFeedFragment.mode);
        storyFeedFragment.loadFirstPage(true);
    }

    public void onResume() {
        super.onResume();
        this.impressionStartTime = System.currentTimeMillis();
        ContentFrameworkAnalytics.trackStoryFeedImpressionStart(this.mode);
    }

    public void onPause() {
        super.onPause();
        ContentFrameworkAnalytics.trackStoryFeedImpressionEnd(System.currentTimeMillis() - this.impressionStartTime, this.mode);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(C5709R.C5711id.search).setVisible(this.mode == Mode.Feed);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C5709R.C5711id.search) {
            return super.onOptionsItemSelected(item);
        }
        ContentFrameworkAnalytics.trackSearchButtonClick();
        startActivity(ReactNativeIntents.intentForStoriesSearch(getContext()));
        return true;
    }

    public void onStoryClicked(Article article, int index) {
        ContentFrameworkAnalytics.trackStoryFeedItemClicked(article, index, this.mode);
        startActivity(StoryDetailViewFragment.forPartialArticle(getContext(), article, getNavigationTrackingTag().trackingName));
    }

    public void onPaginationLoaderDisplayed() {
        loadNextPage();
    }

    @Subscribe
    public void onArticleCommentCountChanged(ArticleCommentUpdatedEvent event) {
        this.adapter.onStoryCommentChanged(event.articleId, event.commentCount);
    }

    @Subscribe
    public void onArticleLikeCountChanged(ArticleLikeCountUpdatedEvent event) {
        this.adapter.onStoryLikeChanged(event.articleId, event.delta);
    }

    @Subscribe
    public void onArticleEngagementCountChanged(ArticleEngagementUpdatedEvent event) {
        this.adapter.onStoryEngagementChanged(event.articleId, event.likeCount, event.commentCount);
    }

    @Subscribe
    public void onStoryDeleted(ArticleDeletedEvent articleDeletedEvent) {
        if (this.adapter != null) {
            this.adapter.deleteStoryWithId(articleDeletedEvent.articleId);
        }
    }

    private void setupComposerPill() {
        if (this.mode != Mode.Feed || !StoriesExperimentUtil.shouldShowComposerPillOnStoryFeed()) {
            this.composerPill.setVisibility(8);
            return;
        }
        this.composerPill.setVisibility(0);
        this.composerPill.setRightButtonClickListener(StoryFeedFragment$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupComposerPill$1(StoryFeedFragment storyFeedFragment, View v) {
        ContentFrameworkAnalytics.trackStoryComposerPillClicked();
        storyFeedFragment.startActivity(StoryCreationPickTripFragment.newIntent(storyFeedFragment.getContext()));
    }

    private void setupToolbar() {
        setToolbar(this.toolbar);
        if (this.mode == Mode.Feed) {
            this.toolbar.setNavigationIcon(0);
            return;
        }
        this.toolbar.setNavigationIcon(1);
        this.toolbar.setTitle((CharSequence) getTitle());
    }

    private void loadFirstPage(boolean forceRefresh) {
        this.isLoading = true;
        getStoryFeedRequest(null).withListener((Observer) this.storyFeedRequestListener).skipCache(forceRefresh).execute(this.requestManager);
    }

    private void loadNextPage() {
        if (!this.isLoading) {
            this.isLoading = true;
            getStoryFeedRequest(this.paginationCursor).withListener((Observer) this.storyFeedRequestListener).execute(this.requestManager);
        }
    }

    private StoryFeedRequest getStoryFeedRequest(String cursor) {
        switch (this.mode) {
            case Feed:
                return StoryFeedRequest.forFeed(cursor);
            case UserStories:
                return StoryFeedRequest.forUser(getArguments().getLong(ARG_USER_ID), cursor);
            case SearchResult:
                return StoryFeedRequest.forSearch(getSearchTerm(), cursor);
            case TopTile:
                return StoryFeedRequest.forTopTileFeed(getTopTile(), cursor);
            default:
                throw new IllegalArgumentException("Unknown mode for story feed");
        }
    }

    private String getSearchTerm() {
        if (getArguments() == null) {
            return null;
        }
        return getArguments().getString(ARG_SEARCH_TERM);
    }

    private StoryFeedTopTile getTopTile() {
        return (StoryFeedTopTile) getArguments().getParcelable(ARG_TOP_TILE);
    }

    private String getTitle() {
        switch (this.mode) {
            case UserStories:
                return getString(C5709R.string.story_of_user_title, getArguments().getString(ARG_USER_NAME));
            case SearchResult:
                return getSearchTerm();
            case TopTile:
                return getTopTile().getMainText();
            default:
                return null;
        }
    }

    /* access modifiers changed from: private */
    public boolean isViewingSearchResult() {
        return this.mode == Mode.SearchResult;
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.mode.navigationTag;
    }
}
