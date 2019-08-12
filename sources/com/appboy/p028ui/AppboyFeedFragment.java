package com.appboy.p028ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.app.ListFragment;
import android.support.p000v4.view.GestureDetectorCompat;
import android.support.p000v4.widget.SwipeRefreshLayout;
import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.appboy.Appboy;
import com.appboy.Constants;
import com.appboy.enums.CardCategory;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.models.cards.Card;
import com.appboy.p028ui.adapters.AppboyListAdapter;
import com.appboy.support.AppboyLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.appboy.ui.AppboyFeedFragment */
public class AppboyFeedFragment extends ListFragment implements OnRefreshListener {
    /* access modifiers changed from: private */
    public static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyFeedFragment.class.getName()});
    /* access modifiers changed from: private */
    public AppboyListAdapter mAdapter;
    /* access modifiers changed from: private */
    public Appboy mAppboy;
    /* access modifiers changed from: private */
    public EnumSet<CardCategory> mCategories;
    int mCurrentCardIndexAtBottomOfScreen = 0;
    /* access modifiers changed from: private */
    public LinearLayout mEmptyFeedLayout;
    private RelativeLayout mFeedRootLayout;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout mFeedSwipeLayout;
    private IEventSubscriber<FeedUpdatedEvent> mFeedUpdatedSubscriber;
    /* access modifiers changed from: private */
    public GestureDetectorCompat mGestureDetector;
    /* access modifiers changed from: private */
    public ProgressBar mLoadingSpinner;
    /* access modifiers changed from: private */
    public final Handler mMainThreadLooper = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public LinearLayout mNetworkErrorLayout;
    int mPreviousVisibleHeadCardIndex = 0;
    /* access modifiers changed from: private */
    public final Runnable mShowNetworkError = new Runnable() {
        public void run() {
            if (AppboyFeedFragment.this.mLoadingSpinner != null) {
                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
            }
            if (AppboyFeedFragment.this.mNetworkErrorLayout != null) {
                AppboyFeedFragment.this.mNetworkErrorLayout.setVisibility(0);
            }
        }
    };
    boolean mSkipCardImpressionsReset = false;
    /* access modifiers changed from: private */
    public boolean mSortEnabled = false;
    /* access modifiers changed from: private */
    public View mTransparentFullBoundsContainerView;

    /* renamed from: com.appboy.ui.AppboyFeedFragment$FeedGestureListener */
    public class FeedGestureListener extends SimpleOnGestureListener {
        public FeedGestureListener() {
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float dx, float dy) {
            AppboyFeedFragment.this.getListView().smoothScrollBy((int) dy, 0);
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float velocityX, float velocityY) {
            long deltaTimeMillis = (motionEvent2.getEventTime() - motionEvent.getEventTime()) * 2;
            AppboyFeedFragment.this.getListView().smoothScrollBy(-((int) ((((float) deltaTimeMillis) * velocityY) / 1000.0f)), (int) (deltaTimeMillis * 2));
            return true;
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mAppboy = Appboy.getInstance(activity);
        if (this.mAdapter == null) {
            this.mAdapter = new AppboyListAdapter(activity, R.id.tag, new ArrayList());
            this.mCategories = CardCategory.getAllCategories();
        }
        this.mGestureDetector = new GestureDetectorCompat(activity, new FeedGestureListener());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.com_appboy_feed, container, false);
        this.mNetworkErrorLayout = (LinearLayout) view.findViewById(R.id.com_appboy_feed_network_error);
        this.mLoadingSpinner = (ProgressBar) view.findViewById(R.id.com_appboy_feed_loading_spinner);
        this.mEmptyFeedLayout = (LinearLayout) view.findViewById(R.id.com_appboy_feed_empty_feed);
        this.mFeedRootLayout = (RelativeLayout) view.findViewById(R.id.com_appboy_feed_root);
        this.mFeedSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.appboy_feed_swipe_container);
        this.mFeedSwipeLayout.setOnRefreshListener(this);
        this.mFeedSwipeLayout.setEnabled(false);
        this.mFeedSwipeLayout.setColorSchemeResources(R.color.com_appboy_newsfeed_swipe_refresh_color_1, R.color.com_appboy_newsfeed_swipe_refresh_color_2, R.color.com_appboy_newsfeed_swipe_refresh_color_3, R.color.com_appboy_newsfeed_swipe_refresh_color_4);
        this.mTransparentFullBoundsContainerView = view.findViewById(R.id.com_appboy_feed_transparent_full_bounds_container_view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadFragmentStateFromSavedInstanceState(savedInstanceState);
        if (this.mSkipCardImpressionsReset) {
            this.mSkipCardImpressionsReset = false;
        } else {
            this.mAdapter.resetCardImpressionTracker();
            AppboyLogger.m1733d(TAG, "Resetting card impressions.");
        }
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final ListView listView = getListView();
        listView.addHeaderView(inflater.inflate(R.layout.com_appboy_feed_header, null));
        listView.addFooterView(inflater.inflate(R.layout.com_appboy_feed_footer, null));
        this.mFeedRootLayout.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return AppboyFeedFragment.this.mGestureDetector.onTouchEvent(motionEvent);
            }
        });
        listView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            }

            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                AppboyFeedFragment.this.mFeedSwipeLayout.setEnabled(firstVisibleItem == 0);
                if (visibleItemCount != 0) {
                    int currentVisibleHeadCardIndex = firstVisibleItem - 1;
                    if (currentVisibleHeadCardIndex > AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex) {
                        AppboyFeedFragment.this.mAdapter.batchSetCardsToRead(AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex, currentVisibleHeadCardIndex);
                    }
                    AppboyFeedFragment.this.mPreviousVisibleHeadCardIndex = currentVisibleHeadCardIndex;
                    AppboyFeedFragment.this.mCurrentCardIndexAtBottomOfScreen = firstVisibleItem + visibleItemCount;
                }
            }
        });
        this.mTransparentFullBoundsContainerView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return view.getVisibility() == 0;
            }
        });
        this.mAppboy.removeSingleSubscription(this.mFeedUpdatedSubscriber, FeedUpdatedEvent.class);
        this.mFeedUpdatedSubscriber = new IEventSubscriber<FeedUpdatedEvent>() {
            public void trigger(final FeedUpdatedEvent event) {
                Activity activity = AppboyFeedFragment.this.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            AppboyLogger.m1733d(AppboyFeedFragment.TAG, "Updating feed views in response to FeedUpdatedEvent: " + event);
                            AppboyFeedFragment.this.mMainThreadLooper.removeCallbacks(AppboyFeedFragment.this.mShowNetworkError);
                            AppboyFeedFragment.this.mNetworkErrorLayout.setVisibility(8);
                            if (event.getCardCount(AppboyFeedFragment.this.mCategories) == 0) {
                                listView.setVisibility(8);
                                AppboyFeedFragment.this.mAdapter.clear();
                            } else {
                                AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
                                AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(8);
                            }
                            if (event.isFromOfflineStorage() && (event.lastUpdatedInSecondsFromEpoch() + 60) * 1000 < System.currentTimeMillis()) {
                                AppboyLogger.m1737i(AppboyFeedFragment.TAG, String.format("Feed received was older than the max time to live of %d seconds, displaying it for now, but requesting an updated view from the server.", new Object[]{Integer.valueOf(60)}));
                                AppboyFeedFragment.this.mAppboy.requestFeedRefresh();
                                if (event.getCardCount(AppboyFeedFragment.this.mCategories) == 0) {
                                    AppboyLogger.m1733d(AppboyFeedFragment.TAG, String.format("Old feed was empty, putting up a network spinner and registering the network error message on a delay of %dms.", new Object[]{Integer.valueOf(5000)}));
                                    AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(8);
                                    AppboyFeedFragment.this.mLoadingSpinner.setVisibility(0);
                                    AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                                    AppboyFeedFragment.this.mMainThreadLooper.postDelayed(AppboyFeedFragment.this.mShowNetworkError, 5000);
                                    return;
                                }
                            }
                            if (event.getCardCount(AppboyFeedFragment.this.mCategories) == 0) {
                                AppboyFeedFragment.this.mLoadingSpinner.setVisibility(8);
                                AppboyFeedFragment.this.mEmptyFeedLayout.setVisibility(0);
                                AppboyFeedFragment.this.mTransparentFullBoundsContainerView.setVisibility(0);
                            } else {
                                if (!AppboyFeedFragment.this.mSortEnabled || event.getCardCount(AppboyFeedFragment.this.mCategories) == event.getUnreadCardCount(AppboyFeedFragment.this.mCategories)) {
                                    AppboyFeedFragment.this.mAdapter.replaceFeed(event.getFeedCards(AppboyFeedFragment.this.mCategories));
                                } else {
                                    AppboyFeedFragment.this.mAdapter.replaceFeed(AppboyFeedFragment.this.sortFeedCards(event.getFeedCards(AppboyFeedFragment.this.mCategories)));
                                }
                                listView.setVisibility(0);
                            }
                            AppboyFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
                        }
                    });
                }
            }
        };
        this.mAppboy.subscribeToFeedUpdates(this.mFeedUpdatedSubscriber);
        listView.setAdapter(this.mAdapter);
        this.mAppboy.requestFeedRefreshFromCache();
    }

    public List<Card> sortFeedCards(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            public int compare(Card cardOne, Card cardTwo) {
                if (cardOne.isRead() == cardTwo.isRead()) {
                    return 0;
                }
                return cardOne.isRead() ? 1 : -1;
            }
        });
        return cards;
    }

    public void onResume() {
        super.onResume();
        Appboy.getInstance(getActivity()).logFeedDisplayed();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mAppboy.removeSingleSubscription(this.mFeedUpdatedSubscriber, FeedUpdatedEvent.class);
        setOnScreenCardsToRead();
    }

    public void onPause() {
        super.onPause();
        setOnScreenCardsToRead();
    }

    private void setOnScreenCardsToRead() {
        this.mAdapter.batchSetCardsToRead(this.mPreviousVisibleHeadCardIndex, this.mCurrentCardIndexAtBottomOfScreen);
    }

    public void onDetach() {
        super.onDetach();
        setListAdapter(null);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("PREVIOUS_VISIBLE_HEAD_CARD_INDEX", this.mPreviousVisibleHeadCardIndex);
        outState.putInt("CURRENT_CARD_INDEX_AT_BOTTOM_OF_SCREEN", this.mCurrentCardIndexAtBottomOfScreen);
        outState.putBoolean("SKIP_CARD_IMPRESSIONS_RESET", this.mSkipCardImpressionsReset);
        if (this.mCategories == null) {
            this.mCategories = CardCategory.getAllCategories();
        }
        ArrayList<String> cardCategoryArrayList = new ArrayList<>(this.mCategories.size());
        Iterator it = this.mCategories.iterator();
        while (it.hasNext()) {
            cardCategoryArrayList.add(((CardCategory) it.next()).name());
        }
        outState.putStringArrayList("CARD_CATEGORY", cardCategoryArrayList);
        super.onSaveInstanceState(outState);
        if (isVisible()) {
            this.mSkipCardImpressionsReset = true;
        }
    }

    /* access modifiers changed from: 0000 */
    public void loadFragmentStateFromSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (this.mCategories == null) {
                this.mCategories = CardCategory.getAllCategories();
            }
            this.mPreviousVisibleHeadCardIndex = savedInstanceState.getInt("PREVIOUS_VISIBLE_HEAD_CARD_INDEX", 0);
            this.mCurrentCardIndexAtBottomOfScreen = savedInstanceState.getInt("CURRENT_CARD_INDEX_AT_BOTTOM_OF_SCREEN", 0);
            this.mSkipCardImpressionsReset = savedInstanceState.getBoolean("SKIP_CARD_IMPRESSIONS_RESET", false);
            ArrayList<String> cardCategoryArrayList = savedInstanceState.getStringArrayList("CARD_CATEGORY");
            if (cardCategoryArrayList != null) {
                this.mCategories.clear();
                Iterator it = cardCategoryArrayList.iterator();
                while (it.hasNext()) {
                    this.mCategories.add(CardCategory.valueOf((String) it.next()));
                }
            }
        }
    }

    public void onRefresh() {
        this.mAppboy.requestFeedRefresh();
        this.mMainThreadLooper.postDelayed(new Runnable() {
            public void run() {
                AppboyFeedFragment.this.mFeedSwipeLayout.setRefreshing(false);
            }
        }, 2500);
    }
}
