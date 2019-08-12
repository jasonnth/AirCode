package com.airbnb.android.hostcalendar.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CalendarJitneyLogger;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.UpcomingReservationsRequest;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.UpcomingReservationsResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.HostCalendarGraph;
import com.airbnb.android.hostcalendar.adapters.CalendarAgendaAdapter;
import com.airbnb.android.hostcalendar.adapters.CalendarAgendaAdapter.InfiniteScrollListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetBuilder;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetItemClickListener;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetMenuItem;
import com.airbnb.p027n2.components.onboarding_overlay.OnboardingOverlayViewController;
import com.google.common.collect.ImmutableList;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.Observer;

public class AgendaCalendarFragment extends AirFragment {
    private static final int ITEMS_PER_FETCH = 50;
    private static final int ONBOARDING_OVERLAY_SHOW_ON_SEEN_TIMES = 1;
    private static final int OVERLAY_TARGET_VIEW_CIRCLE_SHAPE_PADDING = -1;
    private CalendarAgendaAdapter agendaAdapter = null;
    @BindView
    RecyclerView agendaRecyclerView;
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(AgendaCalendarFragment$$Lambda$3.lambdaFactory$(this)).onError(AgendaCalendarFragment$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();
    private BottomSheetDialog bottomSheetDialog;
    private final BottomSheetItemClickListener bottomSheetItemClickListener = AgendaCalendarFragment$$Lambda$11.lambdaFactory$(this);
    @State
    boolean eligibleForNestedListings;
    private final InfiniteScrollListener infiniteScrollListener = AgendaCalendarFragment$$Lambda$10.lambdaFactory$(this);
    final RequestListener<UpcomingReservationsResponse> initialAgendaReservationListener = new C0699RL().onResponse(AgendaCalendarFragment$$Lambda$2.lambdaFactory$(this)).build();
    /* access modifiers changed from: 0000 */
    @State
    public ArrayList<Reservation> initialAgendaReservations = new ArrayList<>();
    CalendarJitneyLogger jitneyLogger;
    final RequestListener<ListingResponse> listingLoaderListener = new C0699RL().onResponse(AgendaCalendarFragment$$Lambda$5.lambdaFactory$(this)).onError(AgendaCalendarFragment$$Lambda$6.lambdaFactory$(this)).build();
    @BindView
    LoadingView loadingView;
    @State
    boolean pageLoaded;
    final RequestListener<UpcomingReservationsResponse> reservationThumbnailListener = new C0699RL().onResponse(AgendaCalendarFragment$$Lambda$1.lambdaFactory$(this)).build();
    private MenuItem settingsMenuItem;
    private boolean skipCache = false;
    @BindView
    AirSwipeRefreshLayout swipeRefreshLayout;
    @State
    ArrayList<Listing> thumbnailListings;
    /* access modifiers changed from: private */
    public ArrayList<Reservation> thumbnailReservations;
    @BindView
    AirToolbar toolbar;
    final RequestListener<UpcomingReservationsResponse> upcomingReservationsListener = new C0699RL().onResponse(AgendaCalendarFragment$$Lambda$7.lambdaFactory$(this)).onError(AgendaCalendarFragment$$Lambda$8.lambdaFactory$(this)).onComplete(AgendaCalendarFragment$$Lambda$9.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$3(AgendaCalendarFragment agendaCalendarFragment, AirRequestNetworkException e) {
        agendaCalendarFragment.showLoader(false);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(agendaCalendarFragment.getView(), (NetworkException) e, AgendaCalendarFragment$$Lambda$15.lambdaFactory$(agendaCalendarFragment));
    }

    static /* synthetic */ void lambda$new$4(AgendaCalendarFragment agendaCalendarFragment, ListingResponse response) {
        agendaCalendarFragment.thumbnailListings.addAll(response.getListings());
        agendaCalendarFragment.agendaAdapter.addListingsThumbnailCarousel(response.getListings());
        agendaCalendarFragment.loadMoreListings(response.getListings().size());
    }

    static /* synthetic */ void lambda$new$8(AgendaCalendarFragment agendaCalendarFragment, AirRequestNetworkException e) {
        agendaCalendarFragment.agendaAdapter.endLoading();
        NetworkUtil.tryShowRetryableErrorWithSnackbar(agendaCalendarFragment.getView(), (NetworkException) e, AgendaCalendarFragment$$Lambda$14.lambdaFactory$(agendaCalendarFragment));
    }

    static /* synthetic */ void lambda$new$10(AgendaCalendarFragment agendaCalendarFragment, int offset) {
        UpcomingReservationsRequest.forCalendarAgenda(offset).withListener((Observer) agendaCalendarFragment.upcomingReservationsListener).execute(agendaCalendarFragment.requestManager);
        agendaCalendarFragment.jitneyLogger.multiListingAgendaLoadMore();
    }

    public static AgendaCalendarFragment newInstance() {
        return (AgendaCalendarFragment) FragmentBundler.make(new AgendaCalendarFragment()).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        ((HostCalendarGraph) CoreApplication.instance(getContext()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6418R.layout.fragment_host_calendar_agenda, container, false);
        bindViews(view);
        this.mBus.register(this);
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(0);
        this.agendaAdapter = new CalendarAgendaAdapter(getContext(), getActivity(), this.infiniteScrollListener);
        this.agendaRecyclerView.setAdapter(this.agendaAdapter);
        this.swipeRefreshLayout.setScrollableChild(this.agendaRecyclerView);
        this.swipeRefreshLayout.setOnRefreshListener(AgendaCalendarFragment$$Lambda$12.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$11(AgendaCalendarFragment agendaCalendarFragment) {
        agendaCalendarFragment.swipeRefreshLayout.setRefreshing(true);
        agendaCalendarFragment.agendaAdapter.clear();
        agendaCalendarFragment.skipCache = true;
        agendaCalendarFragment.retryLoading();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!this.pageLoaded) {
            retryLoading();
        } else {
            showPage();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.settingsMenuItem = menu.findItem(C6418R.C6420id.settings);
        this.settingsMenuItem.getActionView().setOnClickListener(AgendaCalendarFragment$$Lambda$13.lambdaFactory$(this));
        showSettingsMenuItemIfEligible();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6418R.C6420id.settings) {
            return false;
        }
        this.bottomSheetDialog = new BottomSheetBuilder(getContext(), C6418R.C6421menu.host_agenda_calendar_bottom).setItemClickListener(this.bottomSheetItemClickListener).build();
        this.bottomSheetDialog.show();
        return true;
    }

    static /* synthetic */ void lambda$new$13(AgendaCalendarFragment agendaCalendarFragment, BottomSheetMenuItem item) {
        if (item.getId() == C6418R.C6420id.nested_listings_button) {
            agendaCalendarFragment.startActivity(new Intent(agendaCalendarFragment.getContext(), Activities.nestedListings()));
            agendaCalendarFragment.bottomSheetDialog.dismiss();
            return;
        }
        throw new IllegalArgumentException("Unknown menu option: " + item.getTitle());
    }

    private void setUpOnboaringOverlayForSettingMenuItem(MenuItem settingMenuItem, boolean pageLoaded2) {
        if (!pageLoaded2) {
            View settingIcon = settingMenuItem.getActionView();
            if (settingIcon.getVisibility() == 0) {
                OnboardingOverlayViewController.show(getActivity(), settingIcon, getContext().getString(C6418R.string.onboarding_title_for_agenda_calendar_nested_listing), getContext().getString(C6418R.string.onboarding_dismiss_button), "calendar_agenda_nested_listings", -1, 1);
            }
        }
    }

    public void onPause() {
        super.onPause();
        showLoader(false);
    }

    public void showLoader(boolean visible) {
        ViewUtils.setVisibleIf((View) this.loadingView, visible);
        if (!visible) {
            this.swipeRefreshLayout.setRefreshing(false);
        }
    }

    /* access modifiers changed from: private */
    public void retryLoading() {
        List<BaseRequestV2<?>> requests = ImmutableList.m1288of(ListingRequest.forCalendar(0, 50), UpcomingReservationsRequest.forCalendarThumbnail(0, 50, this.agendaAdapter.getThumbnailStartDate(), AirDate.today().plusMonths(1)).withListener((Observer) this.reservationThumbnailListener), UpcomingReservationsRequest.forCalendarAgenda(0).withListener((Observer) this.initialAgendaReservationListener), UserRequest.newRequestForNestedListingsEligibility(this.mAccountManager.getCurrentUserId()));
        if (this.skipCache) {
            for (BaseRequestV2<?> request : requests) {
                request.skipCache();
            }
        }
        showLoader(true);
        new AirBatchRequest(requests, this.batchListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleBatchResponse(AirBatchResponse batchResponse) {
        showLoader(false);
        this.thumbnailListings = new ArrayList<>(((ListingResponse) batchResponse.singleResponse(ListingResponse.class)).getListings());
        this.eligibleForNestedListings = ((UserResponse) batchResponse.singleResponse(UserResponse.class)).user.isEligibleForNestedListings();
        showSettingsMenuItemIfEligible();
        showPage();
        PerformanceLoggerTimeline.logTimeToInteraction(Event.HOST_CALENDAR_AGENDA);
        loadMoreListings(this.thumbnailListings.size());
    }

    private void showPage() {
        this.agendaAdapter.initThumbnailCarousel(this.thumbnailListings, ListUtils.ensureNotNull(this.thumbnailReservations), !canShowCalendarThumbnail(this.thumbnailReservations));
        this.agendaAdapter.addOrUpdateReservations(this.initialAgendaReservations);
    }

    private void showSettingsMenuItemIfEligible() {
        if (this.settingsMenuItem == null) {
            return;
        }
        if (ListUtils.isEmpty((Collection<?>) this.thumbnailListings) || !FeatureToggles.isNestedListingEnabled(this.eligibleForNestedListings)) {
            this.settingsMenuItem.setVisible(false);
            return;
        }
        this.settingsMenuItem.setVisible(true);
        setUpOnboaringOverlayForSettingMenuItem(this.settingsMenuItem, this.pageLoaded);
    }

    private boolean canShowCalendarThumbnail(List<Reservation> reservations) {
        if (reservations == null) {
            return false;
        }
        if (reservations.isEmpty()) {
            return true;
        }
        return this.agendaAdapter.getThumbnailEndDate().isBefore(((Reservation) reservations.get(reservations.size() - 1)).getCheckinDate());
    }

    private void loadMoreListings(int lastLoadSize) {
        if (lastLoadSize >= 50) {
            ListingRequest request = ListingRequest.forCalendar(this.agendaAdapter.thumbnailCarouselSize(), 50);
            if (this.skipCache) {
                request.skipCache();
            }
            request.withListener((Observer) this.listingLoaderListener).execute(this.requestManager);
            return;
        }
        this.skipCache = false;
        this.pageLoaded = true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CalendarMultiListingAgenda;
    }
}
