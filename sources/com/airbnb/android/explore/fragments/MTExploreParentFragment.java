package com.airbnb.android.explore.fragments;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.erf.ErfAnalytics;
import com.airbnb.android.core.erf.ErfExperiment;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerController;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerControllerProvider;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.SearchActivityIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.interfaces.OnHomeListener;
import com.airbnb.android.core.interfaces.UpdateRequestListener;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.core.location.LocationClientFacade.Factory;
import com.airbnb.android.core.location.LocationClientFacade.LocationClientCallbacks;
import com.airbnb.android.core.models.ExperimentMetadata;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.ForYouMetaData;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.UnboundedViewPool;
import com.airbnb.android.core.views.SlidingTabLayout;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreComponent.Builder;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.controllers.ExploreControllerInterface;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreDataController.ExploreDataChangedListener;
import com.airbnb.android.explore.controllers.ExploreDataRepository;
import com.airbnb.android.explore.controllers.ExploreDataStore;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.android.explore.controllers.ExploreNavigationController.ExploreMode;
import com.airbnb.android.explore.controllers.ExploreNavigationController.ExploreNagivationInterface;
import com.airbnb.android.explore.controllers.ExploreNavigationController.ExploreNavigationListener;
import com.airbnb.android.explore.controllers.ExplorePerformanceAnalytics;
import com.airbnb.android.explore.controllers.GPSPermissionGetter;
import com.airbnb.android.explore.views.ExploreScrollController;
import com.airbnb.android.explore.views.MTExploreMarquee;
import com.airbnb.android.explore.views.MTTripsSearchInterface;
import com.airbnb.android.lib.ExploreBindings;
import com.airbnb.android.superhero.SuperHeroBundleUtil;
import com.airbnb.android.superhero.SuperHeroMessage;
import com.airbnb.android.utils.ConcurrentUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MTExploreParentFragment extends AirFragment implements GuestPickerControllerProvider, OnBackListener, OnHomeListener, ExploreControllerInterface, ExploreDataChangedListener, ExploreNavigationListener {
    public static final int RC_P3_PROPOGATE_GUEST = 1800;
    public static final int SUPERHERO_NUX_ID = 45300;
    private static final ExploreDataStore exploreDataStore = new ExploreDataStore();
    BusinessTravelJitneyLogger businessTravelJitneyLogger;
    @BindView
    View container;
    @BindView
    CoordinatorLayout coordinatorLayout;
    /* access modifiers changed from: private */
    public ExploreDataController dataController;
    ExploreDataRepository dataRepository;
    ErfAnalytics erfAnalytics;
    private Snackbar errorSnackbar;
    /* access modifiers changed from: private */
    public ExploreJitneyLogger exploreJitneyLogger;
    @BindView
    MTExploreMarquee exploreMarquee;
    OnGlobalLayoutListener exploreMarqueeLayoutListener = MTExploreParentFragment$$Lambda$1.lambdaFactory$(this);
    private final GPSPermissionGetter gpsPermissionGetter = new GPSPermissionGetter() {
        public void askForGPSPermission() {
            MTExploreParentFragment.this.locationClient = Factory.get(MTExploreParentFragment.this.getActivity(), MTExploreParentFragment.this.locationClientCallbacks);
            MTExploreParentFragmentPermissionsDispatcher.setupAndConnectLocationClientWithCheck(MTExploreParentFragment.this);
        }
    };
    private final GuestPickerController guestPickerController = new GuestPickerController() {
        public NavigationTag getNavigationAnalyticsTag() {
            return NavigationTag.ExplorePage;
        }

        public void onGuestDetailsSaved(GuestDetails guestData, UpdateRequestListener listener) {
            MTExploreParentFragment.this.exploreJitneyLogger.selectGuests(guestData, MTExploreParentFragment.this.dataController.getTopLevelSearchParams().guestDetails());
            MTExploreParentFragment.this.getNavigationController().closeModal();
            MTExploreParentFragment.this.dataController.setGuestData(guestData);
            if (MTExploreParentFragment.this.getNavigationController().getActiveTabId() != Tab.HOME.getTabId() && MTExploreParentFragment.this.haveChildrenOrInfantsBeenAdded(guestData) && FeatureToggles.shouldDefaultHomesTabForFamilies()) {
                MTExploreParentFragment.this.getNavigationController().setActiveTabId(Tab.HOME.getTabId());
            }
        }
    };
    private final Handler handler = new Handler();
    @BindView
    View loadingView;
    /* access modifiers changed from: private */
    public LocationClientFacade locationClient;
    /* access modifiers changed from: private */
    public final LocationClientCallbacks locationClientCallbacks = new LocationClientCallbacks() {
        public void onConnected() {
            MTExploreParentFragment.this.locationClient.requestLocationUpdates();
        }

        public void onLocationUpdated(Location location) {
            MTExploreParentFragment.this.locationClient.disconnectLocationClient();
            MTExploreParentFragment.this.dataController.updateUserLocationAndRefreshResultsIfNeeded(location);
        }
    };
    private final ExploreNagivationInterface nagivationInterface = new ExploreNagivationInterface() {
        public String getActiveTabId() {
            if (MTExploreParentFragment.this.navigationController != null) {
                return MTExploreParentFragment.this.navigationController.getActiveTabId();
            }
            return null;
        }

        public boolean isMapMode() {
            if (MTExploreParentFragment.this.navigationController != null) {
                return MTExploreParentFragment.this.navigationController.isMapMode();
            }
            return false;
        }
    };
    /* access modifiers changed from: private */
    public ExploreNavigationController navigationController;
    ExplorePerformanceAnalytics performanceAnalytics;
    private final RecycledViewPool recycledViewPool = new UnboundedViewPool();
    private ExploreScrollController scrollController;
    private MTTripsSearchInterface searchBar;
    @State
    boolean showPlaylistOnResume;
    private SlidingTabLayout tabLayout;

    public static MTExploreParentFragment instance() {
        return instanceForParams(new Bundle());
    }

    public static MTExploreParentFragment instanceForParams(Bundle searchIntentBundle) {
        return (MTExploreParentFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTExploreParentFragment()).putAll(searchIntentBundle)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ExploreBindings) CoreApplication.instance(getContext()).componentProvider()).exploreComponentProvider().get()).build().inject(this);
        this.dataController = new ExploreDataController(savedInstanceState, this.requestManager, this.mAccountManager, this.dataRepository, this.mPreferences, this.mBus, this.wishListManager, this.businessTravelAccountManager, this.businessTravelJitneyLogger, this.gpsPermissionGetter, this.nagivationInterface, this.performanceAnalytics, exploreDataStore);
        this.navigationController = new ExploreNavigationController(getActivity(), getChildFragmentManager(), savedInstanceState);
        this.exploreJitneyLogger = new ExploreJitneyLogger(this.loggingContextFactory, this.dataController, this.navigationController);
        this.businessTravelAccountManager.fetchBusinessTravelEmployeeInfo();
        setupLocationInfo();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        View view = inflater.inflate(C0857R.layout.fragment_mt_explore_parent, container2, false);
        bindViews(view);
        this.searchBar = this.exploreMarquee.getSearchBar();
        this.tabLayout = this.exploreMarquee.getTabLayout();
        this.exploreMarquee.setJitneyLogger(this.exploreJitneyLogger);
        if (savedInstanceState == null) {
            loadStateForArguments(getArguments());
            this.performanceAnalytics.trackTabsLoadStart();
        } else if (this.dataController.getTabs().isEmpty()) {
            this.dataController.fetchExploreTabs();
        }
        this.tabLayout.setOnTabClickedListener(MTExploreParentFragment$$Lambda$2.lambdaFactory$(this));
        this.searchBar.setClearAllClickListener(MTExploreParentFragment$$Lambda$3.lambdaFactory$(this));
        setupSearchBar();
        setupViewState();
        this.scrollController = new ExploreScrollController(this.exploreMarquee, this.container);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(MTExploreParentFragment mTExploreParentFragment, View v, int newPosition) {
        String tabId = mTExploreParentFragment.dataController.getTabIdAtPosition(newPosition);
        mTExploreParentFragment.exploreJitneyLogger.clickSubtab(tabId);
        mTExploreParentFragment.navigationController.setActiveTabId(tabId);
        ConcurrentUtil.deferParallel(MTExploreParentFragment$$Lambda$12.lambdaFactory$(mTExploreParentFragment, tabId));
    }

    private void loadStateForArguments(Bundle bundle) {
        SearchParams searchParams = (SearchParams) bundle.getParcelable(SearchActivityIntents.EXTRA_SEARCH_PARAMS);
        long playlistId = bundle.getLong("playlist_id");
        this.dataController.setIntentSource(bundle.getString(SearchActivityIntents.EXTRA_SOURCE));
        if (searchParams != null) {
            this.dataController.updateFromSearchParams(searchParams);
        }
        this.dataController.fetchExploreTabs();
        this.navigationController.showList();
        if (playlistId > 0) {
            this.showPlaylistOnResume = true;
        }
    }

    public void onStart() {
        super.onStart();
        this.dataController.addDataChangedListener(this);
        this.navigationController.addNavigationListener(this);
        this.exploreMarquee.getViewTreeObserver().addOnGlobalLayoutListener(this.exploreMarqueeLayoutListener);
    }

    public void onStop() {
        super.onStop();
        this.dataController.removeDataChangedListener(this);
        this.navigationController.removeNavigationListener(this);
        this.exploreMarquee.getViewTreeObserver().removeOnGlobalLayoutListener(this.exploreMarqueeLayoutListener);
    }

    public void onResume() {
        super.onResume();
        this.navigationController.onResume();
        if (this.showPlaylistOnResume) {
            this.handler.postDelayed(MTExploreParentFragment$$Lambda$4.lambdaFactory$(this), 50);
        }
    }

    static /* synthetic */ void lambda$onResume$4(MTExploreParentFragment mTExploreParentFragment) {
        mTExploreParentFragment.navigationController.showPlaylistPage(mTExploreParentFragment.getArguments().getLong("playlist_id"), C2443MtPdpReferrer.DeepLink);
        mTExploreParentFragment.showPlaylistOnResume = false;
        mTExploreParentFragment.getArguments().remove("playlist_id");
    }

    public void onPause() {
        this.navigationController.onPause();
        super.onPause();
    }

    public void onDestroy() {
        if (this.locationClient != null) {
            this.locationClient.disconnectLocationClient();
        }
        this.dataController.onDestroy();
        super.onDestroy();
    }

    private void setupSearchBar() {
        this.searchBar.setLocationClickListener(MTExploreParentFragment$$Lambda$5.lambdaFactory$(this));
        this.searchBar.setDatesClickListener(MTExploreParentFragment$$Lambda$6.lambdaFactory$(this));
        this.searchBar.setGuestsClickListener(MTExploreParentFragment$$Lambda$7.lambdaFactory$(this));
        setupSearchBarText();
    }

    static /* synthetic */ void lambda$setupSearchBar$5(MTExploreParentFragment mTExploreParentFragment, View v) {
        mTExploreParentFragment.exploreJitneyLogger.clickLocation();
        mTExploreParentFragment.navigationController.onLocationRowClicked(mTExploreParentFragment.searchBar.getLocationRect());
    }

    static /* synthetic */ void lambda$setupSearchBar$6(MTExploreParentFragment mTExploreParentFragment, View v) {
        mTExploreParentFragment.exploreJitneyLogger.clickDates();
        mTExploreParentFragment.navigationController.onDatesRowClicked(mTExploreParentFragment.searchBar.getDatesRect());
    }

    static /* synthetic */ void lambda$setupSearchBar$7(MTExploreParentFragment mTExploreParentFragment, View v) {
        mTExploreParentFragment.exploreJitneyLogger.clickGuests();
        mTExploreParentFragment.navigationController.onGuestsRowClicked(mTExploreParentFragment.searchBar.getGuestsRect(), mTExploreParentFragment.dataController.getTopLevelSearchParams().guestDetails());
    }

    private void setupSearchBarText() {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        if (ChinaUtils.enableSearchPlaceholderOptimization()) {
            setupSearchBarTextChina(data);
        } else {
            setupSearchBarTextGlobal(data);
        }
        this.searchBar.hideClearAll(data.isCleared());
        if (this.dataController.isInSeeAllMode()) {
            this.searchBar.setBackButtonClickListener(MTExploreParentFragment$$Lambda$8.lambdaFactory$(this));
            this.searchBar.showBackButtonInsteadOfSearchIcon(true);
            return;
        }
        this.searchBar.setBackButtonClickListener(null);
        this.searchBar.showBackButtonInsteadOfSearchIcon(false);
    }

    static /* synthetic */ void lambda$setupSearchBarText$8(MTExploreParentFragment mTExploreParentFragment, View view) {
        mTExploreParentFragment.dataController.exitSeeAllMode();
        mTExploreParentFragment.searchBar.showBackButtonInsteadOfSearchIcon(false);
    }

    private void setupSearchBarTextGlobal(TopLevelSearchParams data) {
        String location = processLocation(data.searchTerm());
        String time = data.getTimeText(getContext());
        String guests = GuestDetailsPresenter.formatGuestCountLabel(getContext(), data.guestDetails());
        MTTripsSearchInterface mTTripsSearchInterface = this.searchBar;
        if (TextUtils.isEmpty(location)) {
            location = getString(C0857R.string.explore_anywhere);
        }
        mTTripsSearchInterface.setLocationText(location);
        this.searchBar.setTimeText(time);
        this.searchBar.setGuestsText(guests);
    }

    private void setupSearchBarTextChina(TopLevelSearchParams data) {
        boolean hasGuest;
        String textCollpased;
        String location = processLocation(data.searchTerm());
        String time = data.getTimeTextChina(getContext());
        String guests = GuestDetailsPresenter.formatGuestCountLabel(getContext(), data.guestDetails());
        if (TextUtils.isEmpty(location)) {
            boolean hasDate = data.checkInDate() != null;
            if (data.guestDetails() == null || data.guestDetails().totalGuestCount() <= 1) {
                hasGuest = false;
            } else {
                hasGuest = true;
            }
            if (hasDate || hasGuest) {
                textCollpased = getString(C0857R.string.explore_location_placeholder_collapsed_with_date);
                this.searchBar.setTimeText(time);
                this.searchBar.setGuestsText(guests);
            } else {
                textCollpased = getString(C0857R.string.explore_location_placeholder_collapsed);
                this.searchBar.setTimeText(null, time);
                this.searchBar.setGuestsText(null, guests);
            }
            this.searchBar.setLocationText(textCollpased, getString(C0857R.string.explore_location_placeholder_expanded));
            return;
        }
        this.searchBar.setLocationText(location);
        this.searchBar.setTimeText(time);
        this.searchBar.setGuestsText(guests);
    }

    private String processLocation(String location) {
        if (this.dataController.hasMapBounds()) {
            return getString(C0857R.string.explore_selected_map_area);
        }
        if (this.dataController.getSearchInputType() == C5809SearchInputType.CurrentLocation) {
            return getString(C0857R.string.nearby);
        }
        return location;
    }

    public void onSaveInstanceState(Bundle outState) {
        this.navigationController.onSaveInstanceState(outState);
        this.dataController.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public ExploreNavigationController getNavigationController() {
        return this.navigationController;
    }

    public ExploreDataController getDataController() {
        return this.dataController;
    }

    public ExploreScrollController getScrollController() {
        return this.scrollController;
    }

    public GuestPickerController getGuestPickerController() {
        return this.guestPickerController;
    }

    /* access modifiers changed from: private */
    public boolean haveChildrenOrInfantsBeenAdded(GuestDetails guestData) {
        return guestData.getNumberOfChildren() > 0 || guestData.getNumberOfInfants() > 0;
    }

    private void setupViewState() {
        boolean z = true;
        clearErrorSnackbar();
        if (this.dataController.areTabsLoading()) {
            setLoading(true);
            this.tabLayout.setVisibility(8);
            return;
        }
        setLoading(false);
        List<ExploreTab> tabs = new ArrayList<>(this.dataController.getTabs().values());
        if (tabs.size() == 0) {
            this.tabLayout.setVisibility(8);
            this.container.setVisibility(8);
            this.errorSnackbar = NetworkUtil.tryShowRetryableErrorWithSnackbar(this.container, MTExploreParentFragment$$Lambda$9.lambdaFactory$(this));
            return;
        }
        SlidingTabLayout slidingTabLayout = this.tabLayout;
        if (tabs.size() <= 1) {
            z = false;
        }
        ViewLibUtils.setVisibleIf(slidingTabLayout, z);
    }

    public SlidingTabLayout getTabLayout() {
        return this.tabLayout;
    }

    private void setLoading(boolean loading) {
        ViewLibUtils.setVisibleIf(this.loadingView, loading);
        ViewLibUtils.setGoneIf(this.container, loading);
    }

    public void onSearchParamsUpdated() {
        setupSearchBar();
        setupViewState();
    }

    public void onTabsLoaded(String currentTabId, boolean fromNetwork) {
        this.navigationController.updateActiveTab(currentTabId, fromNetwork, this.dataController.getTabs().keySet().contains(this.navigationController.getActiveTabId()));
        showForYouNuxIfNeeded();
        setupViewState();
        setupSearchBarText();
    }

    private void showForYouNuxIfNeeded() {
        if (FeatureToggles.shouldShowForYouV3()) {
            ExploreTab forYouTab = (ExploreTab) this.dataController.getTabs().get(Tab.ALL.getTabId());
            if (forYouTab != null && forYouTab.hasResults() && !ListUtils.isEmpty((Collection<?>) ((ExploreSection) forYouTab.getSections().get(0)).getRecommendationItems())) {
                ForYouMetaData metaData = this.dataController.getForYouMetaData();
                if (metaData != null) {
                    String key = metaData.getUserTravelStatus().getType().getKey();
                    if (!this.mPrefsHelper.hasSeenForYouNux(key)) {
                        getAirActivity().getSuperHeroManager().show(SuperHeroBundleUtil.from(SuperHeroMessage.builder().mo11547id(45300).status(0).dismiss_text(getResources().getString(C0857R.string.for_you_nux_button_text)).messages(Lists.newArrayList((E[]) new String[]{getSuperHeroNuxCopy(metaData)})).should_takeover(true).build()));
                        this.mPrefsHelper.setHasSeenForYouNux(true, key);
                    }
                }
            }
        }
    }

    private String getSuperHeroNuxCopy(ForYouMetaData metaData) {
        String firstName = this.mAccountManager.getCurrentUser().getFirstName();
        String market = metaData.getUserTravelStatus().getMarket();
        switch (metaData.getUserTravelStatus().getType()) {
            case Local:
                return getResources().getString(C0857R.string.for_you_nux_locals, new Object[]{firstName, market});
            case TravelerOnTrip:
                return getResources().getString(C0857R.string.for_you_nux_traveler, new Object[]{firstName, market});
            case UpcomingTrip:
                return getResources().getString(C0857R.string.for_you_nux_upcoming_travelers, new Object[]{firstName, market});
            default:
                return null;
        }
    }

    public void onTabsLoadError(NetworkException e) {
        this.errorSnackbar = NetworkUtil.tryShowRetryableErrorWithSnackbar(this.container, e, MTExploreParentFragment$$Lambda$10.lambdaFactory$(this));
        this.loadingView.setVisibility(8);
    }

    public void onTabMetadataUpdated(String tabId) {
    }

    public void onExplorePlaylistLoaded() {
    }

    /* access modifiers changed from: private */
    public void retryFetchExploreTabs() {
        this.dataController.fetchExploreTabs();
        setupViewState();
    }

    public void onTabContentUpdated(String tabId, boolean fromNetwork, NetworkException exception) {
        if (exception != null) {
            this.errorSnackbar = NetworkUtil.tryShowRetryableErrorWithSnackbar(this.container, exception, MTExploreParentFragment$$Lambda$11.lambdaFactory$(this, tabId));
        } else {
            setupSearchBar();
        }
    }

    public boolean onBackPressed() {
        if (getActivity().isFinishing()) {
            return true;
        }
        if (!this.navigationController.hasModal() && this.dataController.isInSeeAllMode()) {
            this.dataController.exitSeeAllMode();
            return true;
        } else if (!this.navigationController.handleOnBackPressed()) {
            return getChildFragmentManager().popBackStackImmediate();
        } else {
            return true;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1800 && resultCode == -1) {
            this.dataController.setCheckInCheckOutDates((AirDate) data.getParcelableExtra(P3ActivityIntents.CHECK_IN_DATE_KEY), (AirDate) data.getParcelableExtra(P3ActivityIntents.CHECK_OUT_DATE_KEY));
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onExploreNavStateUpdated(ExploreMode mode, String activeTabId) {
        setupSearchBarText();
        setupViewState();
    }

    public ExploreJitneyLogger getLogger() {
        return (ExploreJitneyLogger) Check.notNull(this.exploreJitneyLogger);
    }

    public RecycledViewPool getViewPool() {
        return this.recycledViewPool;
    }

    public boolean onHomePressed() {
        return onBackPressed();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ExplorePage;
    }

    private void clearErrorSnackbar() {
        if (this.errorSnackbar != null) {
            this.errorSnackbar.dismiss();
            this.errorSnackbar = null;
        }
    }

    private void setupLocationInfo() {
        if (LocationUtil.hasLocationPermission(getContext())) {
            this.locationClient = Factory.get(getActivity(), this.locationClientCallbacks);
            this.locationClient.connectLocationClient();
            Location location = LocationUtil.getLastKnownLocation(getContext());
            ExploreDataController exploreDataController = this.dataController;
            if (location == null) {
                location = new Location("");
            }
            exploreDataController.setUserLocation(location);
        } else if (this.mPrefsHelper.hasPermanentlyDeniedLocationPermissions()) {
            this.dataController.setUserLocation(new Location(""));
        }
    }

    /* access modifiers changed from: private */
    public void logExperiments(String tabId) {
        ExploreTab exploreTab = (ExploreTab) this.dataController.getTabs().get(tabId);
        if (exploreTab != null && !ListUtils.isEmpty((Collection<?>) exploreTab.getExperimentsMetadata())) {
            for (ExperimentMetadata metadata : exploreTab.getExperimentsMetadata()) {
                this.erfAnalytics.logExperiment(new ErfExperiment(metadata.getExperiment(), metadata.getTreatment(), Collections.emptyList(), "user"), metadata.getTreatment());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void setupAndConnectLocationClient() {
        trackLocationPermissionActions("explore_location_permission_accepted");
        this.locationClient.connectLocationClient();
        Location lastLocation = LocationUtil.getLastKnownLocation(getContext());
        ExploreDataController exploreDataController = this.dataController;
        if (lastLocation == null) {
            lastLocation = new Location("");
        }
        exploreDataController.setUserLocation(lastLocation);
        this.dataController.fetchExploreTabs();
    }

    /* access modifiers changed from: 0000 */
    public void onLocationPermissionsDenied() {
        trackLocationPermissionActions("explore_location_permission_denied");
        this.locationClient = null;
    }

    /* access modifiers changed from: 0000 */
    public void onLocationPermissionPermanentlyDenied() {
        trackLocationPermissionActions("explore_location_permission_permanently_denied");
        this.mPrefsHelper.setHasPermanentlyDeniedLocationPermissions();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        MTExploreParentFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void trackLocationPermissionActions(String eventName) {
        AirbnbEventLogger.track(eventName, new Strap().mo11639kv("from_tab", this.navigationController.getActiveTabId()));
    }
}
