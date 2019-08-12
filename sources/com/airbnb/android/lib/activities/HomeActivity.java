package com.airbnb.android.lib.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.collections.fragments.SelectInvitationListingPickerFragment;
import com.airbnb.android.contentframework.StoriesExperimentUtil;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment.Mode;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.Fragments;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.analytics.AppboyAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.arguments.ReactNativeFragmentFactory;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.core.content.ListingDeepLinkParser;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.controllers.BottomBarController.OnBottomBarVisibilityChangeListener;
import com.airbnb.android.core.controllers.ExperimentConfigController;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.events.BandwidthModeChangedEvent;
import com.airbnb.android.core.events.ExperimentConfigFetchCompleteEvent;
import com.airbnb.android.core.events.ListingEvent.ListingCreatedEvent;
import com.airbnb.android.core.events.ListingEvent.ListingDeletedEvent;
import com.airbnb.android.core.events.LoginEvent;
import com.airbnb.android.core.events.LogoutEvent;
import com.airbnb.android.core.events.RemoveCohostEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.HeroMarqueeFragment;
import com.airbnb.android.core.host.ListingPromoController;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.intents.FixItIntents;
import com.airbnb.android.core.intents.ListYourSpaceIntents;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.interfaces.OnHomeListener;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.core.models.Currency;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.TravelCoupon;
import com.airbnb.android.core.models.TripTemplate.Type;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.net.LowBandwidthManager;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.requests.CurrenciesRequest;
import com.airbnb.android.core.requests.GetActiveAccountRequest;
import com.airbnb.android.core.requests.GetTravelCouponRequest;
import com.airbnb.android.core.requests.HomesCollectionsApplicationsRequest;
import com.airbnb.android.core.requests.MParticleCustomerIdRequest;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.core.responses.CurrenciesResponse;
import com.airbnb.android.core.responses.GetTravelCouponResponse;
import com.airbnb.android.core.responses.HomesCollectionsApplicationsResponse;
import com.airbnb.android.core.responses.MParticleCustomerIdResponse;
import com.airbnb.android.core.superhero.SuperHeroUtils;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ColdStartExperimentDeliverer;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.utils.UserUtils;
import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListsChangedListener;
import com.airbnb.android.explore.fragments.MTExploreParentFragment;
import com.airbnb.android.itinerary.fragments.ItineraryParentFragment;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.LibBindings;
import com.airbnb.android.lib.LibComponent.Builder;
import com.airbnb.android.lib.analytics.HomeAnalytics;
import com.airbnb.android.lib.contentproviders.HostHomeWidgetProvider;
import com.airbnb.android.lib.fragments.AccountPageFragment;
import com.airbnb.android.lib.fragments.AdvancedSettingsFragment;
import com.airbnb.android.lib.fragments.AppDisableDialogFragment;
import com.airbnb.android.lib.fragments.AppUpgradeDialogFragment;
import com.airbnb.android.lib.fragments.CurrencySelectorDialogFragment;
import com.airbnb.android.lib.fragments.InboxContainerFragment;
import com.airbnb.android.lib.fragments.TOSDialogFragment;
import com.airbnb.android.lib.fragments.inbox.BottomBarBadgeInboxHandler;
import com.airbnb.android.lib.host.HostReactivationIntents;
import com.airbnb.android.lib.host.stats.HostStatsFragment;
import com.airbnb.android.lib.interfaces.ModeSwitchListener;
import com.airbnb.android.lib.listyourspace.LYSAnalytics;
import com.airbnb.android.lib.requests.ChinaCampaignCouponClaimRequest;
import com.airbnb.android.lib.responses.ChinaCampaignCouponClaimResponse;
import com.airbnb.android.managelisting.picker.ManageListingPickerFragment;
import com.airbnb.android.profile_completion.ProfileCompletionHelper;
import com.airbnb.android.profile_completion.ProfileCompletionManager;
import com.airbnb.android.profile_completion.ProfileCompletionManager.ProfileCompletionListener;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.AnimationUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.SavedStateMap;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.wishlists.WishListsFragment;
import com.airbnb.p027n2.components.BottomBarBanner;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.appboy.Appboy;
import com.google.common.collect.FluentIterable;
import com.mparticle.MParticle;
import com.mparticle.MParticle.IdentityType;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;
import com.squareup.otto.Subscribe;
import dagger.Lazy;
import icepick.State;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import p032rx.Observer;

public class HomeActivity extends AirActivity implements OnBottomBarVisibilityChangeListener, WishListsChangedListener, ModeSwitchListener, ProfileCompletionListener {
    private static final int CONTENT_FRAGMENT_ID = C0880R.C0882id.content;
    private static final long REACT_INSTANCE_MANAGER_PRELOAD_DELAY_MS = 2000;
    private static final int REQUEST_CODE_VERIFY = 701;
    private static final int SWITCH_MODE_REPLACE_CONTENT_DELAY_MILLIS = 600;
    private static final String TAG = HomeActivity.class.getSimpleName();
    private static final String TAG_DIALOG_FRAGMENT = "dialog";
    private static final String TAG_TOS_DIALOG_FRAGMENT = "tos_dialog_fragment";
    @State
    AccountMode accountMode = AccountMode.GUEST;
    final RequestListener<AccountResponse> activeAccountListener = new C0699RL().onResponse(HomeActivity$$Lambda$2.lambdaFactory$(this)).build();
    AppRaterController appRaterController;
    private BottomBarBadgeInboxHandler badgeInboxHandler;
    @BindView
    BottomBar bottomBar;
    @BindView
    BottomBarBanner bottomBarBanner;
    @BindView
    ViewGroup bottomBarContainer;
    BottomBarController bottomBarController;
    @BindView
    FrameLayout container;
    final RequestListener<ChinaCampaignCouponClaimResponse> couponClaimResponseRequestListener = new C0699RL().onResponse(HomeActivity$$Lambda$6.lambdaFactory$(this)).build();
    Lazy<CurrencyFormatter> currencyHelper;
    final RequestListener<CurrenciesResponse> currencyRequestListener = new C0699RL().onResponse(HomeActivity$$Lambda$3.lambdaFactory$(this)).onError(HomeActivity$$Lambda$4.lambdaFactory$(this)).build();
    @State
    NavigationSection currentNavSection;
    DebugSettings debugSettings;
    ExperimentConfigController experimentConfigController;
    private final FragmentFactory fragmentFactory = new FragmentFactory();
    private final Handler handler = new Handler();
    @State
    boolean hasShownCouponConfirmation;
    @State
    boolean hasShownVerifiedIdDialog;
    IdentityJitneyLogger identityJitneyLogger;
    @State
    boolean isShowingAccountTabBadgeForTripsNavUpdate;
    ItineraryManager itineraryManager;
    AppLaunchUtils launchUtils;
    ListingPromoController listingPromoController;
    LocalPushNotificationManager localPushNotificationManager;
    LocationClientFacade locationHelper;
    Lazy<LowBandwidthManager> lowBandwidthUtils;
    @BindView
    AppModeTransitionLayout modeTransitionLayout;
    private final OnTabSelectListener onTabSelectListener = HomeActivity$$Lambda$1.lambdaFactory$(this);
    @State
    boolean pendingLaunchPostTrebuchetActions;
    ProfileCompletionManager profileCompletionManager;
    private boolean reactInstanceManagerPreloaded;
    @State
    SavedStateMap savedStateMap = new SavedStateMap();
    @State
    Boolean shouldShowSuperHeroMessageOnLaunch;
    private TabToLoadOnResume tabToLoadOnResume;
    final RequestListener<GetTravelCouponResponse> travelCouponListener = new C0699RL().onResponse(HomeActivity$$Lambda$5.lambdaFactory$(this)).build();

    public enum AccountMode {
        GUEST,
        HOST,
        TRIP_HOST;

        public static AccountMode from(int ordinal) {
            return values()[ordinal];
        }
    }

    class FragmentFactory {
        FragmentFactory() {
        }

        /* access modifiers changed from: 0000 */
        public Fragment get(NavigationSection section, Bundle extras) {
            switch (section) {
                case GuestHome:
                    if (extras != null) {
                        return MTExploreParentFragment.instanceForParams(extras);
                    }
                    return MTExploreParentFragment.instance();
                case Trips:
                    if (FeatureToggles.showNativeItinerary()) {
                        return ItineraryParentFragment.instance(ItineraryParentFragment.PARAM_TIMELINE);
                    }
                    return ReactNativeFragmentFactory.fragmentForItinerary(HomeActivity.this.getIntent().getBundleExtra(HomeActivityIntents.ARG_RN_PARAMS_BUNDLE));
                case GuestInbox:
                    return InboxContainerFragment.newInstance(InboxType.Guest);
                case HostInbox:
                    return InboxContainerFragment.newInstance(InboxType.Host);
                case Account:
                    return AccountPageFragment.newInstance(HomeActivity.this.accountMode);
                case Wishlists:
                    if (extras == null || !extras.containsKey(HomeActivityIntents.ARG_WISH_LIST_ID)) {
                        return WishListsFragment.instance();
                    }
                    return WishListsFragment.instance(extras.getLong(HomeActivityIntents.ARG_WISH_LIST_ID));
                case Stories:
                    return StoryFeedFragment.instance(Mode.Feed);
                case Listings:
                    return ManageListingPickerFragment.create();
                case TripHostInbox:
                    return InboxContainerFragment.newInstance(InboxType.ExperienceHost);
                case TripHostCalendar:
                    return ReactNativeFragmentFactory.fragmentForCityHostsManagerAvailability();
                case TripHostExperiences:
                    return ReactNativeFragmentFactory.fragmentForCityHostsManagerExperiences();
                case TripHostStats:
                    return ReactNativeFragmentFactory.fragmentForCityHostsManagerStats();
                case Calendar:
                    return Fragments.hostCalendar();
                case Performance:
                    return HostStatsFragment.newInstance();
                default:
                    return null;
            }
        }
    }

    private static class TabToLoadOnResume {
        Bundle extras;
        boolean restoreSavedState;
        NavigationSection section;

        public TabToLoadOnResume(NavigationSection section2, Bundle extras2, boolean restoreSavedState2) {
            this.section = section2;
            this.extras = extras2;
            this.restoreSavedState = restoreSavedState2;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_main);
        ButterKnife.bind((Activity) this);
        ((Builder) ((LibBindings) AirbnbApplication.instance(this).componentProvider()).libComponentProvider().get()).build().inject(this);
        this.bus.register(this);
        setUpTransitionIfNeeded();
        if (savedInstanceState == null) {
            setAccountMode(shouldDefaultToGuestMode(true) ? AccountMode.GUEST : AccountMode.HOST);
        }
        setupBottomNavigation();
        if (savedInstanceState == null) {
            handleIntentAction(getIntent());
        }
        handlePostSetupTasks(savedInstanceState);
        FeatureToggles.showTripsNuxIfNeeded(this, this.sharedPrefsHelper, false);
        this.wishListManager.addWishListsChangedListener(this);
        this.badgeInboxHandler = new BottomBarBadgeInboxHandler(this.accountMode, this, this.bottomBar);
        this.profileCompletionManager.addUpdateListener(this);
        this.profileCompletionManager.fetchStatus();
        if (!ColdStartExperimentDeliverer.isInTreatmentGroup()) {
            ((AirReactInstanceManager) this.reactInstanceManager.get()).preloadScreen(ReactNativeIntents.SCREEN_CITY_HOSTS_GUEST_SINGLE_EXPERIENCE_TEMPLATES);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !this.reactInstanceManagerPreloaded && ColdStartExperimentDeliverer.isInTreatmentGroup()) {
            this.reactInstanceManagerPreloaded = true;
            this.handler.postDelayed(HomeActivity$$Lambda$7.lambdaFactory$(this), REACT_INSTANCE_MANAGER_PRELOAD_DELAY_MS);
        }
    }

    private void setUpTransitionIfNeeded() {
        if (AndroidVersion.isAtLeastLollipop()) {
            setUpTransition();
        }
    }

    @TargetApi(21)
    private void setUpTransition() {
        getWindow().setSharedElementExitTransition(null);
        getWindow().setSharedElementReenterTransition(null);
    }

    private void setupBottomNavigation() {
        int tabsXmlRes;
        int activeTabColor;
        int rausch = getResources().getColor(C0880R.color.n2_rausch);
        int babu = getResources().getColor(C0880R.color.n2_babu);
        if (!this.accountManager.isCurrentUserAuthorized()) {
            tabsXmlRes = C0880R.xml.bottom_bar_signed_out;
            activeTabColor = rausch;
        } else if (this.accountMode == AccountMode.GUEST) {
            if (!StoriesExperimentUtil.shouldMoveItineraryToProfile()) {
                tabsXmlRes = C0880R.xml.bottom_bar_guest;
            } else if (StoriesExperimentUtil.shouldReplaceItineraryWithStoriesTab()) {
                tabsXmlRes = C0880R.xml.bottom_bar_guest_with_story;
            } else {
                tabsXmlRes = C0880R.xml.bottom_bar_guest_without_trips;
            }
            activeTabColor = rausch;
        } else if (this.accountMode == AccountMode.HOST) {
            tabsXmlRes = FeatureToggles.showCohostingDashboard() ? C0880R.xml.bottom_bar_host_with_cohosted_listing : C0880R.xml.bottom_bar_host_dls;
            activeTabColor = babu;
        } else if (this.accountMode == AccountMode.TRIP_HOST) {
            tabsXmlRes = FeatureToggles.showTripHostStatsTab() ? C0880R.xml.bottom_bar_trip_host_with_stats : C0880R.xml.bottom_bar_trip_host;
            activeTabColor = babu;
        } else {
            throw new IllegalStateException("Unsupported account mode: " + this.accountMode);
        }
        this.bottomBar.setBadgeBackgroundColor(activeTabColor);
        this.bottomBar.setActiveTabColor(activeTabColor);
        this.bottomBar.setTabTitleTextAppearance(C0880R.C0885style.BottomBarTitleText);
        this.bottomBar.setItems(tabsXmlRes);
        this.bottomBar.setOnTabSelectListener(false, this.onTabSelectListener);
        updateBottomBarBannerVisibility();
    }

    static /* synthetic */ void lambda$new$1(HomeActivity homeActivity, int tabId) {
        NavigationSection section = NavigationSection.findByItemId(tabId);
        if (section == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Could not find NavigationSection with id: " + homeActivity.getResources().getResourceEntryName(tabId)));
        } else {
            homeActivity.loadFragmentForTab(section, null, true);
        }
    }

    private void updateBottomBarBannerVisibility() {
        this.bottomBarBanner.setVisibility(8);
        if (this.accountManager.isCurrentUserAuthorized() && this.accountMode == AccountMode.HOST && FeatureToggles.showHostSuspensionDlsBanner()) {
            User currentUser = this.accountManager.getCurrentUser();
            this.bottomBarBanner.setText(UserUtils.getHostUserSuspensionText(this, currentUser));
            ViewLibUtils.setVisibleIf(this.bottomBarBanner, currentUser.isSuspended());
        }
    }

    private void selectTabAndCreateNewFragment(NavigationSection section, Bundle extras) {
        pauseBottomBarTabSelectListener();
        this.bottomBar.selectTabWithId(section.itemId);
        resumeBottomBarTabSelectListener();
        loadFragmentForTab(section, extras, false);
    }

    private void pauseBottomBarTabSelectListener() {
        this.bottomBar.setOnTabSelectListener(null);
    }

    private void resumeBottomBarTabSelectListener() {
        this.bottomBar.setOnTabSelectListener(false, this.onTabSelectListener);
    }

    private void loadFragmentForTab(NavigationSection section, Bundle extras, boolean restoreSavedState) {
        if (!isActivityResumed()) {
            this.tabToLoadOnResume = new TabToLoadOnResume(section, extras, restoreSavedState);
            return;
        }
        this.tabToLoadOnResume = null;
        if (this.currentNavSection != null) {
            BugsnagWrapper.setContext("HomeActivity" + section.name());
            trackNavigation(section);
        }
        this.currentNavSection = section;
        Fragment fragment = this.fragmentFactory.get(section, extras);
        if (fragment == null) {
            throw new IllegalStateException("Fragment not found for navigation section. Section=" + section.name());
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(C0880R.anim.n2_fade_in_fast, C0880R.anim.n2_fade_out_fast, C0880R.anim.n2_fade_in_fast, C0880R.anim.n2_fade_out_fast);
        maybeSaveCurrentFragmentState();
        if (restoreSavedState) {
            this.savedStateMap.restoreState(fragment, section.name());
        } else {
            this.savedStateMap.clearState(fragment, section.name());
        }
        transaction.replace(CONTENT_FRAGMENT_ID, fragment, section.name()).commitNow();
        startPerformanceLoggerTimeline(section);
        dismissBadgeForCurrentTab();
    }

    private void startPerformanceLoggerTimeline(NavigationSection section) {
        switch (section) {
            case HostInbox:
                PerformanceLoggerTimeline.start(Event.HOST_INBOX);
                return;
            case Listings:
                PerformanceLoggerTimeline.start(Event.HOST_MANAGE_LISTING_PICKER);
                return;
            case Calendar:
                PerformanceLoggerTimeline.start(Event.HOST_CALENDAR_AGENDA);
                return;
            case Performance:
                PerformanceLoggerTimeline.start(Event.HOST_STATS_SUMMARY);
                return;
            default:
                return;
        }
    }

    private void dismissBadgeForCurrentTab() {
        BottomBarTab currentTab = this.bottomBar.getCurrentTab();
        if (currentTab != null && !isTabBadgeControlledByInboxUnreadCount(currentTab) && !isTabBadgeShownAsTripsNavUpdateNux()) {
            currentTab.hideBadge();
        }
    }

    private boolean isTabBadgeControlledByInboxUnreadCount(BottomBarTab currentTab) {
        return Arrays.asList(new Integer[]{Integer.valueOf(NavigationSection.Account.itemId), Integer.valueOf(NavigationSection.HostInbox.itemId), Integer.valueOf(NavigationSection.GuestInbox.itemId)}).contains(Integer.valueOf(currentTab.getId()));
    }

    private boolean isTabBadgeShownAsTripsNavUpdateNux() {
        return this.currentNavSection == NavigationSection.Account && this.isShowingAccountTabBadgeForTripsNavUpdate;
    }

    private void maybeSaveCurrentFragmentState() {
        Fragment currentFragment = getCurrentFragment();
        AirFragment currentAirFragment = currentFragment != null ? (AirFragment) currentFragment : null;
        if (currentAirFragment != null) {
            currentAirFragment.setMenuVisibility(false);
            currentAirFragment.setUserVisibleHint(false);
            this.savedStateMap.saveState(getSupportFragmentManager(), currentAirFragment, currentAirFragment.getTag());
        }
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(CONTENT_FRAGMENT_ID);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        pauseBottomBarTabSelectListener();
        super.onRestoreInstanceState(savedInstanceState);
        resumeBottomBarTabSelectListener();
    }

    public void onStart() {
        super.onStart();
        this.bottomBarController.addOnBottomBarVisibilityChangeListener(this);
        onBottomBarVisibilityChange(this.bottomBarController.shouldShowBottomBar(), false);
    }

    public void onStop() {
        super.onStop();
        this.bottomBarController.removeOnBottomBarVisibilityChangeListener(this);
        this.bottomBarContainer.clearAnimation();
    }

    public void onBottomBarVisibilityChange(boolean show, boolean animate) {
        int duration = animate ? getResources().getInteger(17694720) : 0;
        if (show) {
            AnimationUtils.expand(this.bottomBarContainer, duration);
        } else {
            AnimationUtils.collapse(this.bottomBarContainer, duration);
        }
    }

    public void onWishListsChanged(List<WishList> list, WishListChangeInfo changeInfo) {
        updateWishListBadgeIfNeeded();
    }

    @OnClick
    public void onBottomBarBannerClicked() {
        if (this.accountMode == AccountMode.HOST && this.accountManager.getCurrentUser().isSuspended()) {
            startActivity(HostReactivationIntents.intentForHostReactivation(this));
        }
    }

    private void updateWishListBadgeIfNeeded() {
        BottomBarTab wishListTab = getTabForSection(NavigationSection.Wishlists);
        if (wishListTab != null) {
            if (!this.wishListManager.shouldShowBadge() || wishListTab == this.bottomBar.getCurrentTab()) {
                wishListTab.hideBadge();
                this.wishListManager.clearBadge();
                return;
            }
            wishListTab.showBadge();
        }
    }

    private void updateAccountBadgeIfNeeded() {
        BottomBarTab accountTab = getTabForSection(NavigationSection.Account);
        if (accountTab != null) {
            if ((this.airbnbApi.hasActiveTrip() || this.airbnbApi.hasUpcomingTrips()) && !this.sharedPrefsHelper.isBadgeSeenAndClearedForTripsTabMove() && StoriesExperimentUtil.shouldMoveItineraryToProfile()) {
                accountTab.showBadge();
                this.isShowingAccountTabBadgeForTripsNavUpdate = true;
            } else if (shouldShowBadgeForProfileCompletion()) {
                accountTab.showBadge();
            } else {
                accountTab.hideBadge();
            }
        }
    }

    private void updateListingsBadgeIfNeeded() {
        BottomBarTab listingsTab = getTabForSection(NavigationSection.Listings);
        if (listingsTab != null && !this.sharedPrefsHelper.hasSeenNuxFlowForFeature(AirbnbConstants.PREFS_CHECK_IN_GUIDE_NUX) && FeatureToggles.showHostCheckinGuideTool()) {
            listingsTab.showBadge();
        }
    }

    public boolean shouldShowBadgeForProfileCompletion() {
        if (Trebuchet.launch(TrebuchetKeys.PROFILE_COMPLETION) && this.accountMode == AccountMode.GUEST && ProfileCompletionHelper.shouldShowBadge(this.profileCompletionManager, this.sharedPrefsHelper)) {
            return true;
        }
        return false;
    }

    public void onFetchStatusSuccess(boolean completedStepsChanged) {
        updateAccountBadgeIfNeeded();
    }

    public void onFetchStatusError(NetworkException e) {
    }

    private BottomBarTab getTabForSection(NavigationSection section) {
        return this.bottomBar.getTabWithId(section.itemId);
    }

    private boolean hasTabForSection(NavigationSection section) {
        return getTabForSection(section) != null;
    }

    private void trackNavigation(NavigationSection nextNavSection) {
        HomeAnalytics.trackBottomNavigationClick(this.currentNavSection, nextNavSection);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    @Subscribe
    public void onExperimentConfigFetchComplete(ExperimentConfigFetchCompleteEvent event) {
        if (isActivityResumed()) {
            launchPostTrebuchetActions();
        } else {
            this.pendingLaunchPostTrebuchetActions = true;
        }
    }

    public void onClickSwitchMode(AccountMode newAccountMode) {
        switchToMode(newAccountMode, null, null);
    }

    private void switchToMode(AccountMode newAccountMode, NavigationSection section, Bundle extras) {
        if (!this.accountManager.isCurrentUserAuthorized()) {
            startActivity(LoginActivityIntents.intent(this));
        } else if (newAccountMode != AccountMode.HOST || canGoToHostMode()) {
            setAccountMode(newAccountMode);
            this.modeTransitionLayout.startModeAnimation(this.accountMode);
            this.handler.postDelayed(HomeActivity$$Lambda$8.lambdaFactory$(this, section, extras), 600);
        } else {
            startListYourSpaceActivity();
        }
    }

    static /* synthetic */ void lambda$switchToMode$2(HomeActivity homeActivity, NavigationSection section, Bundle extras) {
        homeActivity.setupBottomNavigation();
        homeActivity.badgeInboxHandler.updateBadgeOnAccountSwitch(homeActivity.accountMode);
        homeActivity.profileCompletionManager.fetchStatus();
        homeActivity.fetchAndShowSelectInvitations();
        if (section == null) {
            homeActivity.goToDefaultTab();
        } else {
            homeActivity.navigateToItem(homeActivity.accountMode, section, extras);
        }
    }

    private void goToDefaultTab() {
        switch (this.accountMode) {
            case GUEST:
                NavigationSection section = NavigationSection.findByAppTab(this.airbnbApi.getLandingTabId());
                if (section == null) {
                    navigateToItem(NavigationSection.GuestHome);
                    return;
                } else if (StoriesExperimentUtil.shouldMoveItineraryToProfile()) {
                    navigateToItem(NavigationSection.GuestHome);
                    return;
                } else {
                    navigateToItem(section);
                    return;
                }
            case HOST:
                navigateToItem(getDefaultHostSection());
                return;
            case TRIP_HOST:
                navigateToItem(NavigationSection.TripHostInbox);
                return;
            default:
                throw new IllegalStateException("Unsupported account mode: " + this.accountMode);
        }
    }

    private NavigationSection getDefaultHostSection() {
        return NavigationSection.HostInbox;
    }

    private void startListYourSpaceActivity() {
        LYSAnalytics.trackAction("account_drawer_host", "enter_lys", null);
        startActivity(ListYourSpaceIntents.intentForNewListing(this, "AccountDrawerHostMode", "ListYourSpace"));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntentAction(intent);
        checkAndClaimMobileCoupon();
    }

    private void handleIntentAction(Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Home activity action is null"));
            goToDefaultTab();
        } else if (getString(C0880R.string.facebook_open_graph_story_action).equals(action)) {
            ListingDeepLinkParser listingDeepLinkParser = new ListingDeepLinkParser(intent);
            long listingId = listingDeepLinkParser.getListingId();
            if (listingId > 0) {
                startActivity(P3ActivityIntents.withListingId(this, listingId, "deep_link"));
            }
        } else if (this.accountManager.isCurrentUserAuthorized()) {
            char c = 65535;
            switch (action.hashCode()) {
                case -1863317900:
                    if (action.equals(HomeActivityIntents.SHOW_WISH_LIST)) {
                        c = 5;
                        break;
                    }
                    break;
                case -1696891833:
                    if (action.equals(HomeActivityIntents.SHOW_TRIP_HOST_INBOX)) {
                        c = 13;
                        break;
                    }
                    break;
                case -1687478688:
                    if (action.equals(HomeActivityIntents.SHOW_TRIP_HOST_STATS)) {
                        c = 16;
                        break;
                    }
                    break;
                case -1608543854:
                    if (action.equals(HomeActivityIntents.SHOW_TRIP_TEMPLATE)) {
                        c = 6;
                        break;
                    }
                    break;
                case -1566666293:
                    if (action.equals(HomeActivityIntents.ACTION_UNHANDLED_DEEPLINK)) {
                        c = 22;
                        break;
                    }
                    break;
                case -1208485519:
                    if (action.equals(HomeActivityIntents.SHOW_LISTINGS)) {
                        c = 7;
                        break;
                    }
                    break;
                case -1208311510:
                    if (action.equals(HomeActivityIntents.SHOW_STORY_FEED)) {
                        c = 3;
                        break;
                    }
                    break;
                case -1158604470:
                    if (action.equals(HomeActivityIntents.SHOW_TRIP_HOST_EXPERIENCES)) {
                        c = 15;
                        break;
                    }
                    break;
                case -1100619818:
                    if (action.equals(HomeActivityIntents.SHOW_TRIP_HOST_SCHEDULED_TRIP)) {
                        c = 17;
                        break;
                    }
                    break;
                case -582238755:
                    if (action.equals(HomeActivityIntents.SHOW_TRIP_HOST_CALENDAR)) {
                        c = 14;
                        break;
                    }
                    break;
                case -428653343:
                    if (action.equals(HomeActivityIntents.SHOW_LISTING_FIX_IT_REPORT)) {
                        c = 8;
                        break;
                    }
                    break;
                case -161410712:
                    if (action.equals(HomeActivityIntents.SHOW_GUEST_HOME)) {
                        c = 0;
                        break;
                    }
                    break;
                case -125945664:
                    if (action.equals(HomeActivityIntents.SHOW_SUPER_HERO)) {
                        c = 9;
                        break;
                    }
                    break;
                case -47129821:
                    if (action.equals(HomeActivityIntents.SHOW_TRAVEL_INBOX)) {
                        c = 12;
                        break;
                    }
                    break;
                case 582871879:
                    if (action.equals(HomeActivityIntents.SHOW_WISH_LIST_INDEX)) {
                        c = 4;
                        break;
                    }
                    break;
                case 644593134:
                    if (action.equals(HomeActivityIntents.SHOW_PERFORMANCE)) {
                        c = 19;
                        break;
                    }
                    break;
                case 1126653228:
                    if (action.equals(HomeActivityIntents.SHOW_TRIPS)) {
                        c = 2;
                        break;
                    }
                    break;
                case 1169539507:
                    if (action.equals(HomeActivityIntents.SHOW_TRIP_HOST_TRIP_REVIEW)) {
                        c = 18;
                        break;
                    }
                    break;
                case 1561878080:
                    if (action.equals(HomeActivityIntents.SHOW_CALENDAR)) {
                        c = 11;
                        break;
                    }
                    break;
                case 1604441024:
                    if (action.equals(HomeActivityIntents.SHOW_IDENTITY)) {
                        c = 20;
                        break;
                    }
                    break;
                case 1688363605:
                    if (action.equals(HomeActivityIntents.SHOW_DEFAULT_TAB)) {
                        c = 21;
                        break;
                    }
                    break;
                case 1689058900:
                    if (action.equals(HomeActivityIntents.SHOW_HOSTHOME)) {
                        c = 10;
                        break;
                    }
                    break;
                case 1909569538:
                    if (action.equals(HomeActivityIntents.SHOW_SEARCH_LANDING)) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    navigateToItem(NavigationSection.GuestHome);
                    return;
                case 1:
                    navigateToItem(NavigationSection.GuestHome, intent.getExtras());
                    return;
                case 2:
                    if (!StoriesExperimentUtil.shouldMoveItineraryToProfile()) {
                        navigateToItem(NavigationSection.Trips);
                        return;
                    }
                    navigateToItem(NavigationSection.Account);
                    startActivity(ItineraryUtils.getItineraryIntent(this));
                    return;
                case 3:
                    if (hasTabForSection(NavigationSection.Stories)) {
                        navigateToItem(NavigationSection.Stories);
                        return;
                    } else {
                        goToDefaultTab();
                        return;
                    }
                case 4:
                    navigateToItem(NavigationSection.Wishlists);
                    return;
                case 5:
                    navigateToItem(NavigationSection.Wishlists, intent.getExtras());
                    return;
                case 6:
                    navigateToItem(NavigationSection.GuestHome);
                    startActivity(ReactNativeIntents.intentForExperiencePDP((Context) this, Type.fromId((int) DeepLinkUtils.getParamAsId(intent, "type")) == Type.Immersion, DeepLinkUtils.getParamAsId(intent, "id")));
                    return;
                case 7:
                    navigateToItem(NavigationSection.Listings);
                    return;
                case 8:
                    navigateToItem(NavigationSection.Listings);
                    long reportId = intent.getLongExtra(FixItIntents.INTENT_EXTRA_REPORT_ID, 0);
                    String listingName = intent.getStringExtra("listing_name");
                    if (reportId == 0 || listingName == null) {
                        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Need an id and listing name to show report!"));
                        return;
                    } else {
                        startActivity(FixItIntents.intentForReportId(this, reportId, listingName));
                        return;
                    }
                case 9:
                    this.shouldShowSuperHeroMessageOnLaunch = Boolean.valueOf(true);
                    return;
                case 10:
                    navigateToItem(getDefaultHostSection());
                    return;
                case 11:
                    navigateToItem(NavigationSection.Calendar, intent.getExtras());
                    return;
                case 12:
                    navigateToItem(NavigationSection.GuestInbox);
                    return;
                case 13:
                    navigateToItem(NavigationSection.TripHostInbox);
                    return;
                case 14:
                    navigateToItem(NavigationSection.TripHostCalendar);
                    return;
                case 15:
                    navigateToItem(NavigationSection.TripHostExperiences);
                    return;
                case 16:
                    navigateToItem(NavigationSection.TripHostStats);
                    return;
                case 17:
                    navigateToItem(NavigationSection.TripHostCalendar);
                    startActivity(ReactNativeIntents.intentForCityHostsScheduledTemplate(this, intent.getLongExtra(HomeActivityIntents.ARG_TRIP_HOST_SCHEDULED_TRIP_ID, -1)));
                    return;
                case 18:
                    navigateToItem(NavigationSection.TripHostStats);
                    startActivity(ReactNativeIntents.intentForCityHostsReview(this, intent.getLongExtra(HomeActivityIntents.ARG_TRIP_HOST_REVIEWABLE_INSTANCE_ID, -1)));
                    return;
                case 19:
                    navigateToItem(NavigationSection.Performance);
                    return;
                case 20:
                    navigateToIdentity(intent);
                    return;
                case 21:
                    goToDefaultTab();
                    return;
                case 22:
                    if (this.currentNavSection == null) {
                        goToDefaultTab();
                        return;
                    }
                    return;
                default:
                    goToDefaultTab();
                    BugsnagWrapper.notify((Throwable) new IllegalStateException("Unknown action: " + action));
                    return;
            }
        }
    }

    private void requestAndShowSuperHero() {
        if (SuperHeroUtils.isSuperHeroEnabled(this.preferences)) {
            if (this.shouldShowSuperHeroMessageOnLaunch == null || !this.shouldShowSuperHeroMessageOnLaunch.booleanValue()) {
                this.superHeroManager.fetchAndShowSuperHeroMessages();
            } else {
                long superHeroId = getIntent().getLongExtra(HomeActivityIntents.ARG_SUPER_HERO_ID, -1);
                Bundle superHeroBundle = getIntent().getBundleExtra(HomeActivityIntents.ARG_SUPER_HERO_BUNDLE);
                if (superHeroBundle != null) {
                    this.superHeroManager.show(superHeroBundle);
                } else if (superHeroId == -111) {
                    this.superHeroManager.showTest();
                } else if (superHeroId != -1) {
                    this.superHeroManager.fetchSuperHeroMessage(superHeroId);
                } else {
                    throw new IllegalStateException("Tried to show superhero message on launch but no message id or bundle provided");
                }
                this.shouldShowSuperHeroMessageOnLaunch = Boolean.valueOf(false);
            }
            this.superHeroManager.setupLocationListener(this);
        }
    }

    private void navigateToIdentity(Intent deepLinkIntent) {
        String firstVerificationStep = deepLinkIntent.getStringExtra("first_verification_step");
        startActivity(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this.identityJitneyLogger, this, AccountVerificationStartFragmentArguments.builder().verificationFlow(VerificationFlow.MobileHandOffNonBooking).firstVerificationStep(firstVerificationStep).phoneVerificationCode(deepLinkIntent.getStringExtra("phone_verification_code")).build()));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.pendingLaunchPostTrebuchetActions) {
            launchPostTrebuchetActions();
        }
        requestAndShowSuperHero();
        this.appRaterController.promptToRateAppIfNeeded(getSupportFragmentManager());
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.tabToLoadOnResume != null) {
            loadFragmentForTab(this.tabToLoadOnResume.section, this.tabToLoadOnResume.extras, this.tabToLoadOnResume.restoreSavedState);
        }
        updateWishListBadgeIfNeeded();
        updateAccountBadgeIfNeeded();
        updateListingsBadgeIfNeeded();
        dismissBadgeForCurrentTab();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
        this.bus.unregister(this);
        this.locationHelper.disconnectLocationClient();
        this.wishListManager.removeWishListsChangedListener(this);
        this.badgeInboxHandler.removeInboxUnreadCountChangeListener();
        this.profileCompletionManager.removeUpdateListener(this);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 701:
                if (resultCode == -1) {
                    startActivity(VerifiedIdActivityIntents.intentForVerifiedId(this));
                    return;
                }
                return;
            case CommunityCommitmentManager.REQUEST_CODE_ACCEPT_COMMUNITY_COMMITMENT /*702*/:
                goToDefaultTab();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private void handlePostSetupTasks(Bundle savedInstanceState) {
        this.locationHelper.connectLocationClient();
        checkForLogin();
        this.launchUtils.setupPush(this);
        checkUserStateChange();
        this.listingPromoController.refreshListingsInfo();
        if (savedInstanceState == null) {
            if (TextUtils.isEmpty(this.preferences.getSharedPreferences().getString(AirbnbConstants.PREFS_CURRENCY, ""))) {
                setupCurrencies();
            }
            buildExperimentConfigs();
            this.launchUtils.doOfflineSync(this);
            fetchTravelCoupon();
            checkAndClaimMobileCoupon();
            fetchAndShowSelectInvitations();
        }
    }

    private void checkUserStateChange() {
        this.localPushNotificationManager.reportUserUnsubscribeIfNecessary();
        this.resourceManager.fetchResourceIfLanguageChanged();
    }

    private void setAccountMode(AccountMode mode) {
        if (mode != AccountMode.HOST || canGoToHostMode()) {
            this.accountMode = mode;
            this.sharedPrefsHelper.setGuestMode(mode == AccountMode.GUEST);
        }
    }

    private boolean shouldDefaultToGuestMode(boolean guestMode) {
        if (!canGoToHostMode()) {
            return true;
        }
        if (guestMode && this.sharedPrefsHelper.hasSetAppMode()) {
            return this.sharedPrefsHelper.isGuestMode();
        }
        if (!this.accountManager.userHasActiveListings() || this.airbnbApi.hasUpcomingTrips() || this.airbnbApi.hasActiveTrip()) {
            return true;
        }
        return false;
    }

    private boolean canGoToHostMode() {
        return this.accountManager.userHasListings();
    }

    @Subscribe
    public void onUserLogIn(LoginEvent event) {
        setAccountMode(AccountMode.GUEST);
        this.launchUtils.setupPush(this);
        this.launchUtils.doOfflineSync(this);
        setupBottomNavigation();
        goToDefaultTab();
        HostHomeWidgetProvider.update(this);
        checkForLogin();
        mParticleTrackCustomerId();
    }

    private void mParticleTrackCustomerId() {
        MParticleCustomerIdRequest.newRequestForMParticleCustomerId().withListener((Observer) new SimpleRequestListener<MParticleCustomerIdResponse>() {
            public void onResponse(MParticleCustomerIdResponse data) {
                String mParticleCustomerId = data.mParticleUser.getMparticleCustomerId();
                MParticle.getInstance().setUserIdentity(mParticleCustomerId, IdentityType.CustomerId);
                Appboy.getInstance(HomeActivity.this).changeUser(mParticleCustomerId);
                HomeActivity.this.preferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_MPARTICLE_CURRENT_MPARTICLE_CUSTOMER_ID, mParticleCustomerId).apply();
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    @Subscribe
    public void onUserLogOut(LogoutEvent event) {
        this.itineraryManager.onClearAll();
        finish();
    }

    @Subscribe
    public void listingCreated(ListingCreatedEvent lcr) {
        new GetActiveAccountRequest(this, false).skipCache();
    }

    @Subscribe
    public void listingDeleted(ListingDeletedEvent ld) {
        new GetActiveAccountRequest(this, false).skipCache();
        if (this.accountMode == AccountMode.HOST && !canGoToHostMode()) {
            onClickSwitchMode(AccountMode.GUEST);
        }
    }

    @Subscribe
    public void cohostRemovedAndSwitchedToGuestMode(RemoveCohostEvent event) {
        onClickSwitchMode(AccountMode.GUEST);
    }

    @Subscribe
    public void onLowBandwidthActivated(BandwidthModeChangedEvent event) {
        if (event.mIsLowBandwidth && ((LowBandwidthManager) this.lowBandwidthUtils.get()).shouldShowLowBandwidthActivatedMessage()) {
            ((LowBandwidthManager) this.lowBandwidthUtils.get()).markShowLowBandwidthActivatedMessageSeen();
            Snackbar.make((View) this.container, C0880R.string.low_bandwidth_mode_activated, 0).setAction(C0880R.string.account_settings, HomeActivity$$Lambda$9.lambdaFactory$(this)).show();
        }
    }

    static /* synthetic */ void lambda$onLowBandwidthActivated$3(HomeActivity homeActivity, View view) {
        ((LowBandwidthManager) homeActivity.lowBandwidthUtils.get()).markShowLowBandwidthActivatedSettingsVisited();
        homeActivity.startActivity(AutoAirActivity.intentForFragment(homeActivity, AdvancedSettingsFragment.class, null, C0880R.string.advanced_settings));
    }

    private void navigateToItem(NavigationSection section) {
        navigateToItem(section, null);
    }

    private void navigateToItem(NavigationSection section, Bundle extras) {
        Check.notNull(this.accountMode, "Account mode not yet set");
        AccountMode mode = null;
        switch (section.modeType) {
            case GuestOnly:
                mode = AccountMode.GUEST;
                break;
            case HostOnly:
                mode = AccountMode.HOST;
                break;
            case TripHostOnly:
                mode = AccountMode.TRIP_HOST;
                break;
            case Shared:
                mode = this.accountMode;
                break;
            case Unknown:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown mode for section: " + section));
                return;
        }
        navigateToItem(mode, section, extras);
    }

    private void navigateToItem(AccountMode mode, NavigationSection section, Bundle extras) {
        if (this.accountMode != mode) {
            switchToMode(mode, section, extras);
        } else if (hasTabForSection(section)) {
            selectTabAndCreateNewFragment(section, extras);
        } else {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unable to load tab. Current mode: " + this.accountMode + " New mode: " + mode + " Section: " + section));
        }
    }

    private void buildExperimentConfigs() {
        this.experimentConfigController.fetchConfigurationForUser(this.accountManager.getCurrentUserId());
    }

    private void showVerifiedIDRequiredFragment() {
        if (!this.hasShownVerifiedIdDialog && isActivityResumed()) {
            HeroMarqueeFragment.builder().withTitle(C0880R.string.title_reservation_not_sent).withBodyText(C0880R.string.body_reservation_not_sent).withFirstButton(C0880R.string.button_complete_verified_id).withSecondButton(C0880R.string.account_verification_do_it_later).withIcon(C0880R.C0881drawable.belo_white_00).withRequestCode(701).build().show(getSupportFragmentManager(), (String) null);
            this.hasShownVerifiedIdDialog = true;
        }
    }

    private void launchPostTrebuchetActions() {
        this.pendingLaunchPostTrebuchetActions = false;
        FragmentManager fragmentManager = getSupportFragmentManager();
        AppUpgradeDialogFragment.showIfNeeded(this, fragmentManager);
        AppDisableDialogFragment.showIfNeeded(fragmentManager);
        if (Trebuchet.launch(Trebuchet.CHECKPOINT, Trebuchet.OUTSTANDING_VERIFICATION, false)) {
            showVerifiedIDRequiredFragment();
        }
    }

    static /* synthetic */ void lambda$new$4(HomeActivity homeActivity, AccountResponse response) {
        if (homeActivity.accountManager.hasCurrentUser()) {
            TargetUserType userType = homeActivity.accountManager.userHasListings() ? TargetUserType.ExistingHost : TargetUserType.ExistingGuest;
            boolean isCommunityCommitmentRequired = response.account.acceptCommunityCommitmentRequired();
            if (response.account.tosRequired()) {
                Fragment currentDialog = homeActivity.getSupportFragmentManager().findFragmentByTag(TAG_TOS_DIALOG_FRAGMENT);
                if (currentDialog == null || !(currentDialog instanceof TOSDialogFragment)) {
                    TOSDialogFragment.newInstance(isCommunityCommitmentRequired, userType).show(homeActivity.getSupportFragmentManager(), TAG_TOS_DIALOG_FRAGMENT);
                }
            } else {
                CommunityCommitmentManager.launchCommunityCommitmentIfNeeded(isCommunityCommitmentRequired, userType, homeActivity);
            }
            if (homeActivity.accountMode == AccountMode.HOST && !homeActivity.canGoToHostMode()) {
                homeActivity.switchToMode(AccountMode.GUEST, null, null);
            }
            if (homeActivity.accountMode == AccountMode.HOST && response.account.getUser().isSuspended()) {
                homeActivity.startActivity(HostReactivationIntents.intentForHostReactivation(homeActivity));
            }
            homeActivity.updateBottomBarBannerVisibility();
        }
    }

    private void checkForLogin() {
        if (this.accountManager.isCurrentUserAuthorized()) {
            new GetActiveAccountRequest(this).withListener((Observer) this.activeAccountListener).execute(this.requestManager);
            mParticleTrackCustomerId();
        }
    }

    private void setupCurrencies() {
        new CurrenciesRequest(this).withListener((Observer) this.currencyRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$6(HomeActivity homeActivity, CurrenciesResponse response) {
        if (response.requiresCurrencyChange) {
            Currency c = new Currency();
            c.setCode(((CurrencyFormatter) homeActivity.currencyHelper.get()).getLocalCurrencyString());
            List<Currency> currencies = response.currencies;
            CurrencySelectorDialogFragment fragment = CurrencySelectorDialogFragment.newInstance(currencies.indexOf(c), currencies);
            fragment.setOnCurrencySelectedDialogListener(HomeActivity$$Lambda$10.lambdaFactory$(homeActivity));
            DialogFragment prev = (DialogFragment) homeActivity.getSupportFragmentManager().findFragmentByTag(TAG_DIALOG_FRAGMENT);
            if (prev != null) {
                prev.dismiss();
            }
            fragment.show(homeActivity.getSupportFragmentManager(), TAG_DIALOG_FRAGMENT);
        }
    }

    static /* synthetic */ void lambda$new$7(HomeActivity homeActivity, AirRequestNetworkException error) {
        if (error.getMessage() != null) {
            C0715L.m1189d(TAG, error.getMessage());
        }
        Toast.makeText(homeActivity, C0880R.string.currency_unavailable, 0).show();
    }

    public void onBackPressed() {
        boolean isGuestMode;
        boolean isTabGuestHome;
        boolean isShowingGuestBar;
        if (isActivityResumed()) {
            Fragment currentFragment = getCurrentFragment();
            if (!(currentFragment instanceof OnBackListener) || !((OnBackListener) currentFragment).onBackPressed()) {
                if (this.accountMode == AccountMode.GUEST) {
                    isGuestMode = true;
                } else {
                    isGuestMode = false;
                }
                if (this.bottomBar.getCurrentTabId() == NavigationSection.GuestHome.itemId) {
                    isTabGuestHome = true;
                } else {
                    isTabGuestHome = false;
                }
                if (this.bottomBar.getTabWithId(NavigationSection.GuestHome.itemId) != null) {
                    isShowingGuestBar = true;
                } else {
                    isShowingGuestBar = false;
                }
                if (!isGuestMode || isTabGuestHome || !isShowingGuestBar) {
                    super.onBackPressed();
                } else {
                    this.bottomBar.selectTabWithId(NavigationSection.GuestHome.itemId);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        Fragment currentFragment = getCurrentFragment();
        if (!(currentFragment instanceof OnHomeListener) || !((OnHomeListener) currentFragment).onHomePressed()) {
            super.onHomeActionPressed();
        }
    }

    public void dispatchActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data);
    }

    private void fetchTravelCoupon() {
        new GetTravelCouponRequest().withListener((Observer) this.travelCouponListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$8(HomeActivity homeActivity, GetTravelCouponResponse response) {
        TravelCoupon coupon = response.findFirstValidCoupon();
        if (coupon != null) {
            homeActivity.preferences.getSharedPreferences().edit().putString("travel_credit_amount", coupon.getFormattedLocalizedAmount()).apply();
            if (NotificationManagerCompat.from(homeActivity).areNotificationsEnabled()) {
                Strap info = Strap.make().mo11639kv("coupon_expiration_date", coupon.getExpirationDate().getIsoDateString()).mo11639kv("coupon_localized_amount", coupon.getFormattedLocalizedAmount()).mo11639kv("coupon_lozalized_min_trip_cost", coupon.getFormattedLocalizedMinTripCost());
                MParticleAnalytics.logEvent("travel_coupon", info);
                AppboyAnalytics.logEvent(homeActivity, "travel_coupon", info);
                return;
            }
            return;
        }
        homeActivity.preferences.getSharedPreferences().edit().putString("travel_credit_amount", "").apply();
    }

    private void checkAndClaimMobileCoupon() {
        if (!this.hasShownCouponConfirmation && Trebuchet.launch(TrebuchetKeys.CBL_CLAIM_COUPON, false)) {
            new ChinaCampaignCouponClaimRequest().withListener((Observer) this.couponClaimResponseRequestListener).execute(this.requestManager);
        }
    }

    private void fetchAndShowSelectInvitations() {
        if (this.accountMode != AccountMode.HOST) {
            return;
        }
        if (this.sharedPrefsHelper.shouldShowCollectionInvitationLandingScreen() || this.sharedPrefsHelper.shouldShowSelectCloseToPassLandingScreen()) {
            HomesCollectionsApplicationsRequest.forListings(this.accountManager.getCurrentUserId()).withListener((Observer) new SimpleRequestListener<HomesCollectionsApplicationsResponse>() {
                public void onResponse(HomesCollectionsApplicationsResponse response) {
                    if (!ListUtils.isNullOrEmpty(response.applications)) {
                        List<HomeCollectionApplication> closeToPassListings = FluentIterable.from((Iterable<E>) response.applications).filter(HomeActivity$2$$Lambda$1.lambdaFactory$()).toList();
                        if (ListUtils.isEmpty((Collection<?>) closeToPassListings) || !HomeActivity.this.sharedPrefsHelper.shouldShowSelectCloseToPassLandingScreen()) {
                            List<HomeCollectionApplication> invitedListings = FluentIterable.from((Iterable<E>) response.applications).filter(HomeActivity$2$$Lambda$2.lambdaFactory$()).toList();
                            if (!ListUtils.isEmpty((Collection<?>) invitedListings) && HomeActivity.this.sharedPrefsHelper.shouldShowCollectionInvitationLandingScreen()) {
                                HomeActivity.this.sharedPrefsHelper.increaseCollectionInvitationLandingScreenSeenTimes();
                                HomeActivity.this.startActivity(SelectInvitationListingPickerFragment.intentForModal(HomeActivity.this, invitedListings, false));
                                return;
                            }
                            return;
                        }
                        HomeActivity.this.sharedPrefsHelper.increaseSelectCloseToPassLandingScreenSeenTimes();
                        HomeActivity.this.startActivity(SelectInvitationListingPickerFragment.intentForModal(HomeActivity.this, closeToPassListings, true));
                    }
                }

                static /* synthetic */ boolean lambda$onResponse$0(HomeCollectionApplication application) {
                    return application.getStatus() == 7;
                }

                static /* synthetic */ boolean lambda$onResponse$1(HomeCollectionApplication application) {
                    return application.getStatus() == 0;
                }
            }).execute(NetworkUtil.singleFireExecutor());
        }
    }

    static /* synthetic */ void lambda$new$9(HomeActivity homeActivity, ChinaCampaignCouponClaimResponse response) {
        Intent confirmationIntent = response.getCouponClaimConfirmationIntent(homeActivity);
        if (confirmationIntent != null) {
            homeActivity.hasShownCouponConfirmation = true;
            homeActivity.startActivity(confirmationIntent);
        }
    }
}
