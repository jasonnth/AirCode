package com.airbnb.android.explore.controllers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.location.Location;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.view.View;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.beta.models.guidebook.GuidebookItem;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerFragmentBuilder;
import com.airbnb.android.core.intents.ArticleIntents;
import com.airbnb.android.core.intents.ExploreIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.Article;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.ForYouMetaData;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.InlineSearchFeedFilterItem;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.p008mt.models.GuidebookItemType;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.fragments.ExploreExperiencesFiltersFragment;
import com.airbnb.android.explore.fragments.ExploreHomesFiltersFragment;
import com.airbnb.android.explore.fragments.ExploreHomesFiltersFragment.ExploreHomesFiltersFragmentBuilder;
import com.airbnb.android.explore.fragments.ExploreHomesFiltersFragment.NavigableFilter;
import com.airbnb.android.explore.fragments.ExplorePlacesFiltersFragment;
import com.airbnb.android.explore.fragments.MTDatesFragment;
import com.airbnb.android.explore.fragments.MTExploreFragment;
import com.airbnb.android.explore.fragments.MTExploreMapFragment;
import com.airbnb.android.explore.fragments.MTLocationChinaFragment;
import com.airbnb.android.explore.fragments.MTLocationFragment;
import com.airbnb.android.explore.fragments.MTPlaylistFragment;
import com.airbnb.android.explore.fragments.WheelchairAccessibleNotificationFragment;
import com.airbnb.android.react.ReactNativeUtils;
import com.airbnb.android.utils.ParcelableStringMap;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback;
import icepick.State;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ExploreNavigationController {
    public static final int FRAGMENT_LAUNCH_DEBOUNCE_TIME = 50;
    @State
    String activeTabId;
    private final Activity activity;
    @State
    ExploreMode currentMode = ExploreMode.LIST;
    private final FragmentManager fragmentManager;
    private boolean isPaused = true;
    private long lastFragmentLaunchTime;
    private final Set<ExploreNavigationListener> navStateChangedListeners = new HashSet();

    public enum ExploreMode {
        MAP(P3Arguments.FROM_MAP),
        PLAYLIST(ShareActivityIntents.ENTRY_POINT_PLAYLIST),
        LIST("list");
        
        private final String modeName;

        private ExploreMode(String modeName2) {
            this.modeName = modeName2;
        }

        public String getName() {
            return this.modeName;
        }

        public static ExploreMode fromString(String mode) {
            if (MAP.getName().equals(mode)) {
                return MAP;
            }
            if (LIST.getName().equals(mode)) {
                return LIST;
            }
            return null;
        }
    }

    public interface ExploreNagivationInterface {
        String getActiveTabId();

        boolean isMapMode();
    }

    public interface ExploreNavigationListener {
        void onExploreNavStateUpdated(ExploreMode exploreMode, String str);
    }

    private enum ModalTransitionType {
        FROM_BOTTOM,
        FROM_RIGHT
    }

    public void launchP3(View view, Listing listing, PricingQuote pricingQuote, TopLevelSearchParams searchParams, String searchSessionId, String from) {
        this.activity.startActivityForResult(P3ActivityIntents.withListingPricingQuoteAndExploreData(this.activity, listing, pricingQuote, searchParams, searchSessionId, from), 1800, AutoSharedElementCallback.getActivityOptionsBundle(this.activity, view));
    }

    public void launchTemplate(View view, TripTemplate template, TopLevelSearchParams params, ExploreJitneyLogger logger, C2443MtPdpReferrer pdpReferrer, long referrerId) {
        Intent intent = ReactNativeIntents.intentForExperiencePDP(this.activity, template.isImmersion(), template.getId(), template.getPosterUrl(), params, logger.searchContext(), pdpReferrer, referrerId);
        ReactNativeUtils.setHasSharedElementTransition(intent, true);
        this.activity.startActivity(intent, AutoSharedElementCallback.getActivityOptionsBundle(this.activity, view));
    }

    public void launchArticles(Article article) {
        this.activity.startActivity(ArticleIntents.intentForArticle(this.activity, article));
    }

    public void handleGuidebookItemClick(GuidebookItem guidebookItem, Location userLocation, ExploreJitneyLogger logger) {
        Intent intent = null;
        switch (GuidebookItemType.from(guidebookItem.getType())) {
            case Album:
                intent = ReactNativeIntents.intentForGuidebookInsider(this.activity, guidebookItem.getId(), guidebookItem.getHeroPhotoUrl(), guidebookItem.getBoldSubtitle(), logger.searchContext());
                break;
            case PlacesNearby:
                ParcelableStringMap queryParams = new ParcelableStringMap();
                if (userLocation != null) {
                    queryParams.put("gpsLat", String.valueOf(userLocation.getLatitude()));
                    queryParams.put("gpsLng", String.valueOf(userLocation.getLongitude()));
                }
                intent = ReactNativeIntents.intentForGuidebookSubcategory(this.activity, queryParams, logger.searchContext());
                break;
            case Detour:
                intent = ReactNativeIntents.intentForGuidebookDetour(this.activity, (String) guidebookItem.getQueryParams().get("id"), guidebookItem.getBoldSubtitle(), logger.searchContext());
                break;
            case Meetups:
                intent = ReactNativeIntents.intentForGuidebookMeetupCollection(this.activity, guidebookItem.getQueryParams(), logger.searchContext());
                break;
        }
        if (intent != null) {
            this.activity.startActivity(intent);
        }
    }

    public void handleRecommendationItemClick(RecommendationItem recommendationItem, TopLevelSearchParams searchParams, ExploreJitneyLogger logger, ForYouMetaData forYouMetaData, int videoPosition) {
        if (recommendationItem.getQueryParam() != null) {
            switch (recommendationItem.getItemType()) {
                case Playlist:
                    showPlaylistPage(recommendationItem.getId(), C2443MtPdpReferrer.ExploreP2Card, videoPosition);
                    return;
                default:
                    Intent intent = ExploreIntents.forType(this.activity, recommendationItem, logger.searchContext(), C2443MtPdpReferrer.Unknown, searchParams, forYouMetaData);
                    if (intent != null) {
                        this.activity.startActivity(intent);
                        return;
                    }
                    return;
            }
        }
    }

    public ExploreNavigationController(Activity activity2, FragmentManager fragmentManager2, Bundle savedState) {
        this.activity = activity2;
        this.fragmentManager = fragmentManager2;
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    public void addNavigationListener(ExploreNavigationListener listener) {
        Check.state(this.navStateChangedListeners.add(listener), "listener was already added to set");
    }

    public void removeNavigationListener(ExploreNavigationListener listener) {
        Check.state(this.navStateChangedListeners.remove(listener), "listener did not exist in set");
    }

    public void onPause() {
        this.isPaused = true;
    }

    public void onResume() {
        this.isPaused = false;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void setActiveTabId(String activeTabId2) {
        this.activeTabId = activeTabId2;
        notifyNavStateUpdate();
    }

    public void updateActiveTab(String tabIdToSet, boolean fromNetwork, boolean doesOldTabStillExist) {
        boolean hasActiveTab;
        boolean newTabSameAsOld = true;
        if (getActiveTabId() != null) {
            hasActiveTab = true;
        } else {
            hasActiveTab = false;
        }
        if (tabIdToSet == null || !tabIdToSet.equals(getActiveTabId())) {
            newTabSameAsOld = false;
        }
        if (hasActiveTab && doesOldTabStillExist && fromNetwork && !FeatureToggles.showAutocompleteVerticalOptions()) {
            return;
        }
        if (!doesOldTabStillExist || !hasActiveTab || !newTabSameAsOld) {
            setActiveTabId(tabIdToSet);
        }
    }

    public String getActiveTabId() {
        return this.activeTabId;
    }

    public ExploreMode getCurrentMode() {
        return this.currentMode;
    }

    public boolean isListMode() {
        return ExploreMode.LIST.equals(this.currentMode);
    }

    public boolean isMapMode() {
        return ExploreMode.MAP.equals(this.currentMode);
    }

    public void showMap() {
        this.currentMode = ExploreMode.MAP;
        transitionToModal(MTExploreMapFragment.newInstance());
        notifyNavStateUpdate();
    }

    public void showPlaylistPage(long playlistId, C2443MtPdpReferrer referrer) {
        showPlaylistPage(playlistId, referrer, 0);
    }

    public void showPlaylistPage(long playlistId, C2443MtPdpReferrer referrer, int videoPosition) {
        this.currentMode = ExploreMode.PLAYLIST;
        transitionToModal(MTPlaylistFragment.newInstance(playlistId, referrer, videoPosition), ModalTransitionType.FROM_RIGHT);
        notifyNavStateUpdate();
    }

    public void closeMap() {
        this.currentMode = ExploreMode.LIST;
    }

    public void showList() {
        this.currentMode = ExploreMode.LIST;
        showInContainer(MTExploreFragment.newInstance());
        notifyNavStateUpdate();
    }

    private void notifyNavStateUpdate() {
        for (ExploreNavigationListener listener : new ArrayList<>(this.navStateChangedListeners)) {
            listener.onExploreNavStateUpdated(this.currentMode, this.activeTabId);
        }
    }

    private void showInContainer(Fragment newFragment) {
        transitionTo(newFragment, false);
    }

    public void closeModal() {
        if (canLaunchFragment()) {
            this.fragmentManager.popBackStack();
        }
    }

    public void onDatesRowClicked(Rect animateRect) {
        transitionToModal(ChinaUtils.useSearchPageChina() ? MTDatesFragment.newInstanceForChina() : MTDatesFragment.newInstance(animateRect));
    }

    public void onLocationRowClicked(Rect animateRect) {
        transitionToModal(ChinaUtils.useSearchPageChina() ? MTLocationChinaFragment.newInstance(null) : MTLocationFragment.newInstance(animateRect));
    }

    public void onGuestsRowClicked(Rect animateRect, GuestDetails guestDetails) {
        GuestPickerFragmentBuilder builder = new GuestPickerFragmentBuilder(guestDetails, NavigationTag.ExplorePage.trackingName).showMaxGuestsDescription(false);
        if (ChinaUtils.useSearchPageChina()) {
            builder.setChinaStyle(true);
        } else {
            builder.setAnimateRect(animateRect);
        }
        transitionToModal(builder.build());
    }

    public void popBackStack() {
        this.fragmentManager.popBackStack();
    }

    public void showFilters() {
        if (Tab.HOME.isSameAs(getActiveTabId())) {
            transitionToModal(ExploreHomesFiltersFragment.newInstance());
        } else if (Tab.EXPERIENCE.isSameAs(getActiveTabId())) {
            transitionToModal(ExploreExperiencesFiltersFragment.newInstance());
        } else if (Tab.PLACES.isSameAs(getActiveTabId())) {
            transitionToModal(ExplorePlacesFiltersFragment.newInstance());
        }
    }

    public void showWheelchairAccessibleNotification() {
        transitionToModal(WheelchairAccessibleNotificationFragment.newInstance());
    }

    public boolean handleOnBackPressed() {
        Fragment f = getCurrentFragment();
        if (f instanceof OnBackListener) {
            return ((OnBackListener) f).onBackPressed();
        }
        return false;
    }

    private void transitionToFilterSheet(Fragment newFragment) {
        this.fragmentManager.beginTransaction().setCustomAnimations(0, C0857R.anim.fade_out_delayed, 0, 0).remove(getCurrentFragment()).add(C0857R.C0859id.filter_sheet_container, newFragment).addToBackStack(null).commit();
        notifyFragmentLaunch();
    }

    private void transitionTo(Fragment newFragment, boolean addToBackStack) {
        FragmentTransaction transaction = this.fragmentManager.beginTransaction().setCustomAnimations(17432576, 17432577, C0857R.anim.fragment_enter_pop, C0857R.anim.fragment_exit_pop).replace(C0857R.C0859id.container, newFragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
        notifyFragmentLaunch();
    }

    private AirFragment getCurrentFragment() {
        return (AirFragment) this.fragmentManager.findFragmentById(getActiveFragmentContainer());
    }

    private int getActiveFragmentContainer() {
        if (hasModal()) {
            return C0857R.C0859id.modal_container;
        }
        if (hasFilterSheet()) {
            return C0857R.C0859id.filter_sheet_container;
        }
        return C0857R.C0859id.container;
    }

    public boolean hasModal() {
        Fragment f = this.fragmentManager.findFragmentById(C0857R.C0859id.modal_container);
        return f != null && !f.isRemoving() && f.isResumed();
    }

    private boolean hasFilterSheet() {
        Fragment f = this.fragmentManager.findFragmentById(C0857R.C0859id.filter_sheet_container);
        return f != null && !f.isRemoving() && f.isResumed();
    }

    private void transitionToModal(Fragment newFragment) {
        transitionToModal(newFragment, ModalTransitionType.FROM_BOTTOM);
    }

    private void transitionToModal(Fragment newFragment, ModalTransitionType transitionType) {
        FragmentTransaction fragmentTransaction;
        if (canLaunchFragment()) {
            switch (transitionType) {
                case FROM_RIGHT:
                    fragmentTransaction = this.fragmentManager.beginTransaction().setCustomAnimations(C0857R.anim.fragment_enter, C0857R.anim.fragment_slide_delay, 0, C0857R.anim.fragment_exit_pop);
                    break;
                default:
                    fragmentTransaction = this.fragmentManager.beginTransaction().setCustomAnimations(C0857R.anim.enter_bottom, C0857R.anim.fragment_slide_delay, 0, C0857R.anim.exit_bottom);
                    break;
            }
            Fragment existingFragment = getCurrentFragment();
            if (existingFragment != null) {
                fragmentTransaction.hide(existingFragment);
            }
            fragmentTransaction.add(C0857R.C0859id.modal_container, newFragment).addToBackStack(null).commit();
            notifyFragmentLaunch();
        }
    }

    public void onNonAmenityInlineFilterItemClicked(InlineSearchFeedFilterItem item) {
        Fragment fragment = getFragmentToLaunchForFeedItem(item);
        if (fragment != null) {
            transitionToModal(fragment);
        }
    }

    private Fragment getFragmentToLaunchForFeedItem(InlineSearchFeedFilterItem item) {
        String sourceTag = NavigationTag.FilterSuggestion.trackingName;
        switch (item.getType()) {
            case Amenity:
            case LongTermStay:
                return null;
            case Dates:
                return MTDatesFragment.newInstance(sourceTag);
            case Guests:
                return new GuestPickerFragmentBuilder(GuestDetails.newInstance(), sourceTag).showMaxGuestsDescription(false).build();
            case InstantBookAutoOn:
            case InstantBook:
                return new ExploreHomesFiltersFragmentBuilder().setNavigableFilter(NavigableFilter.InstantBook).setSourceTag(sourceTag).build();
            case Price:
                return new ExploreHomesFiltersFragmentBuilder().setNavigableFilter(NavigableFilter.Price).setSourceTag(sourceTag).build();
            case BedroomCount:
                return new ExploreHomesFiltersFragmentBuilder().setNavigableFilter(NavigableFilter.BedCount).setSourceTag(sourceTag).build();
            case RoomType:
                return new ExploreHomesFiltersFragmentBuilder().setNavigableFilter(NavigableFilter.RoomType).setSourceTag(sourceTag).build();
            case Location:
                return MTLocationFragment.newInstance(null);
            default:
                BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Unknown filter item type " + item.getType()));
                return ExploreHomesFiltersFragment.newInstance();
        }
    }

    private boolean canLaunchFragment() {
        return !this.isPaused && System.currentTimeMillis() - this.lastFragmentLaunchTime > 50;
    }

    private void notifyFragmentLaunch() {
        this.lastFragmentLaunchTime = System.currentTimeMillis();
    }
}
