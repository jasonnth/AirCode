package com.airbnb.android.lib.fragments.inbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.analytics.MessagingAnalytics;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.events.AlertsChangedEvent;
import com.airbnb.android.core.events.MessageReceivedEvent;
import com.airbnb.android.core.events.MessageSyncEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.InboxActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.models.ThreadType;
import com.airbnb.android.core.requests.AirRequestFactory;
import com.airbnb.android.core.requests.DashboardAlertsRequest;
import com.airbnb.android.core.requests.InboxRequest;
import com.airbnb.android.core.requests.UpdateThreadRequest;
import com.airbnb.android.core.responses.DashboardAlertsResponse;
import com.airbnb.android.core.responses.InboxResponse;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.HostReservationObjectActivity;
import com.airbnb.android.lib.adapters.RecyclerInfiniteAdapter;
import com.airbnb.android.lib.adapters.RecyclerSectionedAdapter;
import com.airbnb.android.lib.fragments.ArchiveThreadDialog;
import com.airbnb.android.lib.fragments.alerts.AlertsFragment;
import com.airbnb.android.lib.fragments.inbox.ThreadList.Listener;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;
import com.airbnb.android.lib.tripassistant.HelpThreadActivity;
import com.airbnb.android.lib.utils.ThemeUtils;
import com.airbnb.android.lib.views.EmptyResultsCardView;
import com.airbnb.android.superhero.SuperHeroMessage;
import com.airbnb.android.superhero.SuperHeroTableOpenHelper;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.onboarding_overlay.OnboardingOverlayViewController;
import com.airbnb.p027n2.primitives.AirBadgableMenuActionView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.common.collect.FluentIterable;
import com.squareup.otto.Subscribe;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class InboxFragment extends AirFragment implements ThreadList {
    private static final String ARCHIVE_REQUEST_TAG = "archive_thread_";
    private static final String ARG_INBOX_TYPE = "inbox_type";
    private static final int ONBOARDING_OVERLAY_SHOW_ON_SEEN_TIMES = 2;
    private static final int OVERLAY_TARGET_VIEW_CIRCLE_SHAPE_PADDING = -1;
    private static final int RESULT_CODE_DIALOG_ARCHIVE_THREAD = 99781;
    private static final int RESULT_CODE_VIEW_ALERTS = 1;
    private static final int RESULT_CODE_WRITE_REVIEW = 2;
    /* access modifiers changed from: private */
    public static final String TAG = InboxFragment.class.getSimpleName();
    private static final int TOGGLE_ARCHIVE_FEEDBACK_SHOW_TIME = 10000;
    @State
    protected ArrayList<DashboardAlert> alerts;
    final RequestListener<DashboardAlertsResponse> alertsListener = new C0699RL().onResponse(InboxFragment$$Lambda$2.lambdaFactory$(this)).onError(InboxFragment$$Lambda$3.lambdaFactory$(this)).onComplete(InboxFragment$$Lambda$4.lambdaFactory$(this)).build();
    private MenuItem alertsMenuItem;
    final RequestListener<Object> archiveListener = new C0699RL().onError(InboxFragment$$Lambda$1.lambdaFactory$(this)).build();
    private final RecyclerSectionedAdapter baseAdapter = new RecyclerSectionedAdapter();
    @BindView
    EmptyResultsCardView emptyResultsCard;
    /* access modifiers changed from: private */
    public InboxAdapter inboxAdapter;
    final RequestListener<InboxResponse> inboxRequestListener = new C0699RL().onResponse(InboxFragment$$Lambda$5.lambdaFactory$(this)).onError(InboxFragment$$Lambda$6.lambdaFactory$(this)).onComplete(InboxFragment$$Lambda$7.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public InboxType inboxType;
    private RecyclerInfiniteAdapter<InboxRequest, Thread, InboxResponse> infiniteAdapter;
    /* access modifiers changed from: private */
    public boolean isUndoClicked;
    @State
    protected boolean loadingAlerts;
    @State
    protected boolean loadingInitialInbox;
    @State
    protected boolean loadingSuperHero;
    @BindView
    AirSwipeRefreshLayout mSwipeRefreshLayout;
    private InboxMarqueeAdapter marqueeAdapter;
    protected MessagingRequestFactory messagingRequestFactory;
    @BindView
    RecyclerView newRecyclerView;
    @State
    protected boolean refreshOnResume;
    protected SuperHeroManager superHeroManager;
    private Subscription superHeroPreviewSubscription;
    protected SuperHeroTableOpenHelper superHeroTableOpenHelper;
    private final Listener threadClickListener = new Listener() {
        public void onClick(Thread thread, long position) {
            if ((InboxFragment.this.inboxType.isHostMode() && thread.getThreadType() == ThreadType.PlaceBooking && thread.isRequiresResponse()) && thread.hasDates()) {
                InboxFragment.this.refreshOnResume = true;
                if (thread.getReservationStatus() == ReservationStatus.Inquiry && thread.isUnread()) {
                    InboxFragment.this.markThreadRead(thread);
                    InboxFragment.this.messagingRequestFactory.markThreadRead(InboxFragment.this.inboxType, thread);
                }
                String confirmationCode = thread.getReservationConfirmationCode();
                if (confirmationCode == null) {
                    InboxFragment.this.startActivity(HostReservationObjectActivity.intentForThread(InboxFragment.this.getContext(), thread.getId(), ROLaunchSource.HostInbox));
                } else {
                    InboxFragment.this.startActivity(HostReservationObjectActivity.intentForConfirmationCode(InboxFragment.this.getContext(), confirmationCode, ROLaunchSource.HostInbox));
                }
            } else if (thread.getThreadType() == ThreadType.HelpThread) {
                InboxFragment.this.startActivity(HelpThreadActivity.intentForId(InboxFragment.this.getContext(), thread.getAttachment().getId()));
            } else if (thread.getThreadType() == ThreadType.SupportMessagingThread) {
                InboxFragment.this.startActivity(ReactNativeIntents.intentForSupportMessagingThread(InboxFragment.this.getContext(), thread.getAttachment().getId()));
            } else {
                InboxFragment.this.triggerMessageThreadClicked(InboxFragment.this.inboxType, thread, null);
            }
        }

        public boolean onLongClick(Thread thread) {
            ArchiveThreadDialog.newInstance(thread, !InboxFragment.this.inboxType.archived, InboxFragment.RESULT_CODE_DIALOG_ARCHIVE_THREAD, InboxFragment.this).show(InboxFragment.this.getFragmentManager(), (String) null);
            return true;
        }

        public void onSuperHeroClick() {
            InboxFragment.this.superHeroThreadClicked();
        }

        public void onSwipe(Thread thread) {
            if (FeatureToggles.shouldEnableSwipeToArchive(InboxFragment.this.inboxType)) {
                InboxFragment.this.toggleArchivedWithSwiping(thread);
            } else {
                InboxFragment.this.toggleArchived(thread);
            }
        }

        public void onReviewButtonClick(Thread thread) {
            InboxFragment.this.startActivityForResult(WriteReviewActivity.newIntent(InboxFragment.this.getContext(), thread.getReviewId()), 2);
        }
    };
    private Listener threadListListener;
    @State
    protected ArrayList<String> threadTagsToArchive = new ArrayList<>();
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(InboxFragment inboxFragment, AirRequestNetworkException e) {
        inboxFragment.loadMessagesFromNetwork();
        NetworkUtil.tryShowErrorWithSnackbar(inboxFragment.getView(), e);
    }

    static /* synthetic */ void lambda$new$3(InboxFragment inboxFragment, Boolean success) {
        inboxFragment.loadingAlerts = false;
        inboxFragment.updateEmptyState();
    }

    public static InboxFragment newInstance(InboxType inboxType2) {
        return (InboxFragment) ((FragmentBundleBuilder) FragmentBundler.make(new InboxFragment()).putSerializable("inbox_type", inboxType2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (getArguments() != null) {
            this.inboxType = (InboxType) Check.notNull(getArguments().getSerializable("inbox_type"));
        }
        if (savedInstanceState == null) {
            this.messagingRequestFactory.sync(this.inboxType);
        }
        this.marqueeAdapter = new InboxMarqueeAdapter(this.inboxType, getContext());
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        boolean showArchive;
        boolean showSearch;
        super.onCreateOptionsMenu(menu, inflater);
        if (this.inboxType == InboxType.Host || this.inboxType == InboxType.Guest) {
            showArchive = true;
        } else {
            showArchive = false;
        }
        menu.findItem(C0880R.C0882id.menu_switch_inbox).setVisible(showArchive);
        if (this.inboxType == InboxType.Host || (this.inboxType == InboxType.Guest && Experiments.shouldShowGuestMessagingSearch())) {
            showSearch = true;
        } else {
            showSearch = false;
        }
        MenuItem searchMenuItem = menu.findItem(C0880R.C0882id.action_search);
        searchMenuItem.setVisible(showSearch);
        setUpClickListenerForSearchMenuItem(searchMenuItem);
        setUpOnboaringOverlayForSearchMenuItem(searchMenuItem);
        this.alertsMenuItem = menu.findItem(C0880R.C0882id.menu_alerts).setVisible(shouldShowAlerts());
        this.alertsMenuItem.getActionView().setOnClickListener(InboxFragment$$Lambda$8.lambdaFactory$(this));
        updateAlertsBadge();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == C0880R.C0882id.menu_switch_inbox) {
            startActivity(InboxActivityIntents.intentForInbox(getContext(), this.inboxType.getArchivedInboxType()));
            return true;
        } else if (itemId != C0880R.C0882id.menu_alerts) {
            return super.onOptionsItemSelected(item);
        } else {
            startAlerts();
            return true;
        }
    }

    private boolean shouldShowAlerts() {
        return this.inboxType == InboxType.Host || this.inboxType == InboxType.Guest;
    }

    private void fetchAlertsIfRequired() {
        if (shouldShowAlerts()) {
            this.loadingAlerts = true;
            DashboardAlertsRequest.forInboxType(this.inboxType, getContext()).withListener((Observer) this.alertsListener).execute(this.requestManager);
        }
    }

    /* access modifiers changed from: private */
    public void setAlerts(ArrayList<DashboardAlert> alerts2) {
        this.alerts = alerts2;
        updateAlertsBadge();
    }

    static /* synthetic */ boolean lambda$updateAlertsBadge$5(DashboardAlert alert) {
        return !alert.isViewed();
    }

    private void updateAlertsBadge() {
        boolean hasUnread = this.alerts != null && FluentIterable.from((Iterable<E>) this.alerts).anyMatch(InboxFragment$$Lambda$9.lambdaFactory$());
        if (this.alertsMenuItem != null) {
            ((AirBadgableMenuActionView) this.alertsMenuItem.getActionView()).showBadge(hasUnread);
        }
    }

    private void startAlerts() {
        startActivityForResult(AlertsFragment.createIntent(getContext(), this.inboxType, this.alerts), 1);
    }

    /* access modifiers changed from: private */
    public void startInboxSearch() {
        startActivity(TransparentActionBarActivity.intentForFragment(getContext(), InboxSearchFragment.newInstance(this.inboxType, null)));
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.inboxAdapter != null) {
            this.inboxAdapter.onSaveInstanceState(outState);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        C0715L.m1189d(TAG, "onCreateView()");
        View view = ThemeUtils.inflaterForPhonePadding(inflater).inflate(C0880R.layout.fragment_inbox, container, false);
        bindViews(view);
        this.mBus.register(this);
        setupToolbar();
        this.emptyResultsCard.setBackgroundImageRes(C0880R.C0881drawable.empty_messages);
        this.emptyResultsCard.setupActionButton(C0880R.string.start_exploring, InboxFragment$$Lambda$10.lambdaFactory$(this));
        this.newRecyclerView.setHasFixedSize(true);
        this.mSwipeRefreshLayout.setOnRefreshListener(InboxFragment$$Lambda$11.lambdaFactory$(this));
        AirRequestFactory<InboxRequest, InboxResponse> factory = new AirRequestFactory<InboxRequest, InboxResponse>() {
            public InboxRequest getNextOffset(int offset, BaseRequestListener<InboxResponse> callback) {
                C0715L.m1189d(InboxFragment.TAG, "Creating historic inbox request");
                InboxRequest inboxRequest = InboxFragment.this.messagingRequestFactory.createInboxRequest(InboxFragment.this.inboxType, InboxFragment.this.inboxAdapter.getLastThread());
                inboxRequest.withListener((Observer) callback);
                return inboxRequest;
            }
        };
        this.inboxAdapter = new InboxAdapter(getContext(), savedInstanceState, this.inboxType, this.threadClickListener);
        this.infiniteAdapter = new RecyclerInfiniteAdapter<>(this.inboxAdapter, 1, factory, this.requestManager, C0880R.layout.n2_loading_row_frame);
        this.baseAdapter.addAdapter(this.marqueeAdapter);
        this.baseAdapter.addAdapter(this.infiniteAdapter);
        this.newRecyclerView.setAdapter(this.baseAdapter);
        if (savedInstanceState != null) {
            updateEmptyState();
            this.infiniteAdapter.startLoadingMore();
        } else {
            this.marqueeAdapter.setLoading(true);
            loadFromCache();
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$7(InboxFragment inboxFragment) {
        inboxFragment.mSwipeRefreshLayout.setRefreshing(true);
        inboxFragment.loadMessagesAndSuperHeroFromNetwork();
    }

    private void setupToolbar() {
        setToolbar(this.toolbar);
        if (this.inboxType.archived) {
            this.toolbar.setNavigationIcon(1);
        } else {
            this.toolbar.setNavigationIcon(0);
        }
        this.toolbar.scrollWith(this.newRecyclerView);
    }

    public void onResume() {
        super.onResume();
        if (this.inboxAdapter.isEmpty()) {
            return;
        }
        if (this.refreshOnResume || this.inboxType.useMessagingSync()) {
            this.refreshOnResume = false;
            loadFromCache();
        }
    }

    public void setThreadListListener(Listener threadListListener2) {
        this.threadListListener = threadListListener2;
    }

    public void onDetach() {
        super.onDetach();
        this.threadListListener = null;
        if (this.superHeroPreviewSubscription != null) {
            this.superHeroPreviewSubscription.unsubscribe();
            this.superHeroPreviewSubscription = null;
        }
    }

    public void onDestroyView() {
        this.mBus.unregister(this);
        super.onDestroyView();
    }

    private void setUpOnboaringOverlayForSearchMenuItem(MenuItem searchMenuItem) {
        if (this.inboxType.isHostMode()) {
            View searchIcon = searchMenuItem.getActionView();
            if (searchIcon.getVisibility() == 0) {
                OnboardingOverlayViewController.show(getActivity(), searchIcon, getString(C0880R.string.onboarding_title_for_search_icon), getString(C0880R.string.onboarding_dismiss_button), "search_message_icon", -1, 2);
            }
        }
    }

    private void setUpClickListenerForSearchMenuItem(MenuItem searchMenuItem) {
        searchMenuItem.getActionView().setOnClickListener(InboxFragment$$Lambda$12.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void triggerMessageThreadClicked(InboxType inboxType2, Thread thread, Long postId) {
        this.refreshOnResume = true;
        if (thread.isUnread()) {
            markThreadRead(thread);
        }
        this.threadListListener.onThreadClicked(inboxType2, thread, postId);
    }

    /* access modifiers changed from: private */
    public void markThreadRead(Thread thread) {
        this.marqueeAdapter.decrementUnreadCount(getResources());
        thread.setUnread(false);
    }

    /* access modifiers changed from: private */
    public void superHeroThreadClicked() {
        this.threadListListener.onSuperHeroClicked();
    }

    private void updateEmptyState() {
        boolean isLoading;
        boolean isEmpty;
        boolean showEmpty;
        boolean showEmptyInterstitial;
        if (this.loadingAlerts || this.loadingInitialInbox || this.loadingSuperHero) {
            isLoading = true;
        } else {
            isLoading = false;
        }
        if (!this.inboxAdapter.isEmpty() || !ListUtils.isEmpty((Collection<?>) this.alerts)) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }
        if (isLoading || !isEmpty) {
            showEmpty = false;
        } else {
            showEmpty = true;
        }
        if (this.inboxType != InboxType.Guest || !this.threadListListener.setEmptyState(this.inboxType, showEmpty)) {
            this.marqueeAdapter.setIsEmpty(getResources(), showEmpty, this.inboxType);
            if (!showEmpty || this.inboxType != InboxType.Guest) {
                showEmptyInterstitial = false;
            } else {
                showEmptyInterstitial = true;
            }
            ViewUtils.setInvisibleIf(this.newRecyclerView, showEmptyInterstitial);
            ViewUtils.setVisibleIf((View) this.emptyResultsCard, showEmptyInterstitial);
        }
    }

    private void loadMessagesAndSuperHeroFromNetwork() {
        load(true, true);
    }

    private void loadMessagesFromNetwork() {
        load(true, false);
    }

    private void loadFromCache() {
        load(false, false);
    }

    private void load(boolean refreshReservationMessages, boolean refreshSuperHeroMessages) {
        boolean z = false;
        this.infiniteAdapter.refresh();
        C0715L.m1189d(TAG, String.format("load($1%b, $2%b)", new Object[]{Boolean.valueOf(refreshReservationMessages), Boolean.valueOf(refreshSuperHeroMessages)}));
        this.loadingInitialInbox = true;
        BaseRequest skipCache = this.messagingRequestFactory.createInboxRequest(this.inboxType, null).withListener((Observer) this.inboxRequestListener).skipCache(refreshReservationMessages);
        if (!refreshReservationMessages) {
            z = true;
        }
        skipCache.doubleResponse(z).execute(this.requestManager);
        if (this.inboxType == InboxType.Guest && FeatureToggles.isSuperHeroEnabled()) {
            this.loadingSuperHero = true;
            if (this.superHeroPreviewSubscription != null) {
                this.superHeroPreviewSubscription.unsubscribe();
            }
            this.superHeroPreviewSubscription = Observable.fromCallable(InboxFragment$$Lambda$13.lambdaFactory$(this)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(InboxFragment$$Lambda$14.lambdaFactory$(this));
            if (refreshSuperHeroMessages) {
                this.superHeroManager.fetchAndShowSuperHeroMessages();
            }
        }
        fetchAlertsIfRequired();
        updateEmptyState();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (data != null && data.hasExtra(AlertsFragment.RESULT_UPDATED_ALERTS)) {
                    setAlerts(data.getParcelableArrayListExtra(AlertsFragment.RESULT_UPDATED_ALERTS));
                    return;
                }
                return;
            case 2:
                if (resultCode == -1) {
                    this.inboxAdapter.removeAllThreads();
                    loadFromCache();
                    return;
                }
                return;
            case RESULT_CODE_DIALOG_ARCHIVE_THREAD /*99781*/:
                Thread thread = (Thread) data.getParcelableExtra("message_thread");
                if (FeatureToggles.shouldEnableSwipeToArchive(this.inboxType)) {
                    toggleArchivedWithSwiping(thread);
                    return;
                } else {
                    toggleArchived(thread);
                    return;
                }
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    /* access modifiers changed from: private */
    public void toggleArchived(Thread thread) {
        if (thread.isUnread() && !FeatureToggles.shouldEnableSwipeToArchive(this.inboxType)) {
            this.marqueeAdapter.decrementUnreadCount(getResources());
        }
        this.inboxAdapter.removeThread(thread.getId());
        UpdateThreadRequest request = this.messagingRequestFactory.archiveThread(this.inboxType, thread, !this.inboxType.archived);
        String tag = requestTagForThread(thread);
        this.threadTagsToArchive.add(tag);
        if (isResumed()) {
            this.requestManager.executeWithTag(request.withListener((Observer) this.archiveListener), tag);
        } else {
            request.execute(NetworkUtil.singleFireExecutor());
        }
    }

    private static String requestTagForThread(Thread thread) {
        return ARCHIVE_REQUEST_TAG + String.valueOf(thread.getId());
    }

    /* access modifiers changed from: private */
    public void toggleArchivedWithSwiping(Thread thread) {
        if (thread.isUnread()) {
            this.marqueeAdapter.decrementUnreadCount(getResources());
        }
        this.inboxAdapter.hideThread(thread.getId());
        displayToggleArchiveConfirmation(thread);
    }

    private void displayToggleArchiveConfirmation(Thread thread) {
        boolean isInquiry;
        boolean isInquiryOrRequest = thread.getReservationStatus().matchesAny(ReservationStatus.Inquiry, ReservationStatus.Pending);
        if (this.inboxType.archived || !thread.isRequiresResponse() || !isInquiryOrRequest) {
            toggleArchiveConfirmationSnackBar(thread, this.inboxType.archived ? C0880R.string.unarchive_confirmation : C0880R.string.archive_confirmation, 0, C0880R.string.undo, 0, 2);
            return;
        }
        if (thread.getReservationStatus() == ReservationStatus.Inquiry) {
            isInquiry = true;
        } else {
            isInquiry = false;
        }
        toggleArchiveConfirmationSnackBar(thread, isInquiry ? C0880R.string.text_unresponded_inquiry : C0880R.string.text_unresponded_request, isInquiry ? C0880R.string.text_confirm_archive_unresponded_inquiry : C0880R.string.text_confirm_archive_unresponded_request, C0880R.string.unarchive, 1, 1);
    }

    private void toggleArchiveConfirmationSnackBar(final Thread thread, int titleRes, int bodyRes, int actionRes, int orientation, int theme) {
        OnClickListener undoListener = InboxFragment$$Lambda$15.lambdaFactory$(this, thread);
        SnackbarWrapper snackbarWrapper = new SnackbarWrapper().view(getView()).title(titleRes, false).orientation(orientation).action(actionRes, undoListener).duration(10000).callBack(new Callback() {
            public void onDismissed(Snackbar snackbar, int event) {
                if (!InboxFragment.this.isUndoClicked) {
                    InboxFragment.this.toggleArchived(thread);
                }
                InboxFragment.this.isUndoClicked = false;
            }
        });
        if (bodyRes != 0) {
            snackbarWrapper = snackbarWrapper.body(bodyRes);
        }
        snackbarWrapper.buildAndShow(theme);
    }

    static /* synthetic */ void lambda$toggleArchiveConfirmationSnackBar$10(InboxFragment inboxFragment, Thread thread, View v) {
        if (thread.isUnread()) {
            inboxFragment.marqueeAdapter.incrementUnreadCount(inboxFragment.getResources());
        }
        inboxFragment.inboxAdapter.showThread(thread.getId());
        inboxFragment.isUndoClicked = true;
    }

    @Subscribe
    public void onAlertsChanged(AlertsChangedEvent alertsChangedEvent) {
        fetchAlertsIfRequired();
    }

    @Subscribe
    public void onMessage(MessageReceivedEvent event) {
        if (this.inboxAdapter.onMessageReceived(event.threadId, event.post)) {
            this.marqueeAdapter.incrementUnreadCount(getResources());
        }
        loadFromCache();
    }

    @Subscribe
    public void onMessageSyncEvent(MessageSyncEvent event) {
        if (event.inboxType == this.inboxType) {
            this.marqueeAdapter.setUnreadCount(getResources(), event.unreadCount);
            loadFromCache();
        }
    }

    static /* synthetic */ void lambda$new$12(InboxFragment inboxFragment, InboxResponse response) {
        C0715L.m1189d(TAG, String.format("InboxResponse. IsResumed: %1$b, isAdded: %2$b, isInLayout: %3$b, isRemoving: %4$b", new Object[]{Boolean.valueOf(inboxFragment.isResumed()), Boolean.valueOf(inboxFragment.isAdded()), Boolean.valueOf(inboxFragment.isInLayout()), Boolean.valueOf(inboxFragment.isRemoving())}));
        if (!inboxFragment.isResumed()) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("AirRequest response callback triggered for non-resumed fragment"));
            return;
        }
        inboxFragment.mSwipeRefreshLayout.setRefreshing(false);
        List<Thread> threads = response.messageThreads;
        if (!InboxRequest.shouldIncludeHelpThreads()) {
            threads = FluentIterable.from((Iterable<E>) threads).filter(InboxFragment$$Lambda$16.lambdaFactory$()).toList();
        }
        inboxFragment.inboxAdapter.setThreads(threads);
        if (!threads.isEmpty()) {
            inboxFragment.infiniteAdapter.startLoadingMore();
            inboxFragment.threadListListener.onThreadsLoaded(inboxFragment.inboxType, (Thread) threads.get(0));
            if (response.inboxMetadata != null) {
                inboxFragment.marqueeAdapter.setUnreadCount(inboxFragment.getResources(), response.inboxMetadata.getUnreadHostCount());
            }
        }
        MessagingAnalytics.trackInboxViewMessages(threads, inboxFragment.getContext());
    }

    static /* synthetic */ boolean lambda$null$11(Thread thread) {
        return thread.getThreadType() != ThreadType.HelpThread;
    }

    static /* synthetic */ void lambda$new$13(InboxFragment inboxFragment, AirRequestNetworkException error) {
        C0715L.m1189d(TAG, String.format("InboxErrorResponse. IsResumed: %1$b, isAdded: %2$b, isInLayout: %3$b, isRemoving: %4$b", new Object[]{Boolean.valueOf(inboxFragment.isResumed()), Boolean.valueOf(inboxFragment.isAdded()), Boolean.valueOf(inboxFragment.isInLayout()), Boolean.valueOf(inboxFragment.isRemoving())}));
        if (!inboxFragment.isResumed()) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("AirRequest response callback triggered for non-resumed fragment"));
            return;
        }
        inboxFragment.marqueeAdapter.showError();
        NetworkUtil.tryShowErrorWithSnackbar(inboxFragment.getView(), error);
    }

    static /* synthetic */ void lambda$new$14(InboxFragment inboxFragment, Boolean success) {
        C0715L.m1189d(TAG, String.format("InboxCompleteResponse. IsResumed: %1$b, isAdded: %2$b, isInLayout: %3$b, isRemoving: %4$b", new Object[]{Boolean.valueOf(inboxFragment.isResumed()), Boolean.valueOf(inboxFragment.isAdded()), Boolean.valueOf(inboxFragment.isInLayout()), Boolean.valueOf(inboxFragment.isRemoving())}));
        if (!inboxFragment.isResumed()) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("AirRequest response callback triggered for non-resumed fragment"));
            return;
        }
        inboxFragment.mSwipeRefreshLayout.setRefreshing(false);
        inboxFragment.marqueeAdapter.setLoading(false);
        inboxFragment.loadingInitialInbox = false;
        inboxFragment.updateEmptyState();
        PerformanceLoggerTimeline.logTimeToInteraction(Event.HOST_INBOX);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Inbox;
    }

    public Strap getNavigationTrackingParams() {
        return this.inboxType.addLoggingParams(super.getNavigationTrackingParams());
    }

    /* access modifiers changed from: private */
    public void setSuperHeroPreview(SuperHeroMessage messageToPreview) {
        this.inboxAdapter.setSuperHeroThreadPreview(messageToPreview);
        this.loadingSuperHero = false;
        updateEmptyState();
        this.superHeroPreviewSubscription = null;
    }
}
