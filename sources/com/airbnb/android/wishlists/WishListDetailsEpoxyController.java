package com.airbnb.android.wishlists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCardFeedItemEpoxyModel_;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.beta.models.guidebook.GuidebookPlace;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.SearchActivityIntents;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.models.WishListedArticle;
import com.airbnb.android.core.models.WishListedPlace;
import com.airbnb.android.core.models.WishListedPlaceActivity;
import com.airbnb.android.core.models.WishListedTrip;
import com.airbnb.android.core.models.WishlistedListing;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.presenters.WishListPresenter;
import com.airbnb.android.core.viewcomponents.models.CarouselModel_;
import com.airbnb.android.core.viewcomponents.models.CollaboratorsRowModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.HomeCardEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.HomeCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PosterCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RecommendationCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.react.ReactNativeUtils;
import com.airbnb.android.wishlists.WLVotingRow.WLVotingClickListener;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WishListDetailsEpoxyController extends AirEpoxyController {
    private static final int NO_TAB_POSITION = -1;
    private final Activity activity;
    /* access modifiers changed from: private */
    public WLDetailsFragmentInterface adapterInterface;
    CollaboratorsRowModel_ collaboratorModel;
    /* access modifiers changed from: private */
    public WLTab currentTab;
    /* access modifiers changed from: private */
    public final WLDetailsDataController dataController;
    SectionHeaderEpoxyModel_ firstSectionHeader;
    @State
    protected int lastSelectedTabPosition = -1;
    LinkActionRowEpoxyModel_ linkRow;
    EpoxyControllerLoadingModel_ loaderModel;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    private final OnTabSelectedListener onTabSelectedListener = new OnTabSelectedListener() {
        public void onTabSelected(Tab tab) {
            if (WishListDetailsEpoxyController.this.validateHasWishList()) {
                WLTab previousTab = WishListDetailsEpoxyController.this.currentTab;
                WishListDetailsEpoxyController.this.lastSelectedTabPosition = tab.getPosition();
                WishListDetailsEpoxyController.this.requestModelBuild();
                WishListDetailsEpoxyController.this.adapterInterface.getWLLogger().clickDetailsSubTab(WishListDetailsEpoxyController.this.wishList(), WLTab.forJitney(previousTab), WLTab.forJitney(WishListDetailsEpoxyController.this.currentTab));
            }
        }

        public void onTabUnselected(Tab tab) {
        }

        public void onTabReselected(Tab tab) {
        }
    };
    MapPillSpacerModel pillSpacerModel;
    SectionHeaderEpoxyModel_ secondSectionHeader;
    private final OnClickListener startExploringClickListener = WishListDetailsEpoxyController$$Lambda$1.lambdaFactory$();
    WLDetailsTabBarModel_ tabBarModel;
    CarouselModel_ unavailableHomesCarouselModel;
    MicroSectionHeaderEpoxyModel_ unavailableItemsHeaderModel;
    CarouselModel_ unavailableTripsCarouselModel;

    public WishListDetailsEpoxyController(Activity activity2, Bundle savedState, WLDetailsDataController dataController2) {
        this.dataController = dataController2;
        this.activity = activity2;
        onRestoreInstanceState(savedState);
    }

    public void setInterface(WLDetailsFragmentInterface adapterInterface2) {
        this.adapterInterface = adapterInterface2;
    }

    private boolean hasWishList() {
        return this.adapterInterface != null && this.adapterInterface.hasLoadedWishList();
    }

    private boolean isUserAMember() {
        return this.adapterInterface != null && this.adapterInterface.isCurrentUserAMember();
    }

    /* access modifiers changed from: private */
    public WishList wishList() {
        return this.adapterInterface.getWishList();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        List<WLTab> tabs = WLTab.getTabsForState(wishList());
        updateCurrentTab(tabs);
        addMarquee(tabs);
        if (this.currentTab != null) {
            if (this.dataController.hasLoadedTabContent(this.currentTab)) {
                addContentForTab(this.currentTab);
            } else {
                add((EpoxyModel<?>) this.loaderModel);
            }
        } else if (this.adapterInterface.hasLoadedWishList()) {
            addEmptyState();
        } else {
            add((EpoxyModel<?>) this.loaderModel);
        }
        this.adapterInterface.onTabShown(this.currentTab);
        this.pillSpacerModel.addIf(this.adapterInterface.isShowingMapPill(), (EpoxyController) this);
    }

    private void updateCurrentTab(List<WLTab> tabs) {
        if (tabs.isEmpty()) {
            if (this.dataController.hasStartedLoad) {
                this.lastSelectedTabPosition = 0;
            }
            this.currentTab = null;
            return;
        }
        if (this.lastSelectedTabPosition == -1) {
            this.lastSelectedTabPosition = 0;
        } else if (this.lastSelectedTabPosition >= tabs.size()) {
            this.lastSelectedTabPosition = tabs.size() - 1;
        }
        this.currentTab = (WLTab) tabs.get(this.lastSelectedTabPosition);
    }

    private void addMarquee(List<WLTab> tabs) {
        boolean z;
        if (hasWishList()) {
            this.marqueeModel.titleText((CharSequence) wishList().getName()).captionText((CharSequence) isUserAMember() ? WishListPresenter.formatDatesGuestCountPrivacyExperimental(this.activity, wishList()) : null);
            this.collaboratorModel.showDivider(false).friendsClickListener(WishListDetailsEpoxyController$$Lambda$2.lambdaFactory$(this)).inviteClickListener(WishListDetailsEpoxyController$$Lambda$3.lambdaFactory$(this)).imageUrls(FluentIterable.from((Iterable<E>) this.adapterInterface.getWishListMembers()).transform(WishListDetailsEpoxyController$$Lambda$4.lambdaFactory$()).toList()).addIf(this.adapterInterface.isCurrentUserAMember(), (EpoxyController) this);
            WLDetailsTabBarModel_ onTabSelectedListener2 = this.tabBarModel.showDivider(true).selectedPosition(this.lastSelectedTabPosition).tabs(tabs).onTabSelectedListener(this.onTabSelectedListener);
            if (tabs.size() > 1) {
                z = true;
            } else {
                z = false;
            }
            onTabSelectedListener2.addIf(z, (EpoxyController) this);
        }
    }

    static /* synthetic */ void lambda$addMarquee$0(WishListDetailsEpoxyController wishListDetailsEpoxyController, View v) {
        if (wishListDetailsEpoxyController.validateHasWishList()) {
            wishListDetailsEpoxyController.adapterInterface.onMembersRowClicked();
        }
    }

    static /* synthetic */ void lambda$addMarquee$1(WishListDetailsEpoxyController wishListDetailsEpoxyController, View v) {
        if (wishListDetailsEpoxyController.validateHasWishList()) {
            wishListDetailsEpoxyController.adapterInterface.onInviteFriendClicked();
        }
    }

    /* access modifiers changed from: private */
    public boolean validateHasWishList() {
        if (hasWishList()) {
            return true;
        }
        BugsnagWrapper.notify((Throwable) new IllegalStateException("Wish list not loaded"));
        return false;
    }

    private void addContentForTab(WLTab currentTab2) {
        if (currentTab2 == WLTab.Homes) {
            addListingContent();
        } else if (currentTab2 == WLTab.Trips) {
            addTripContent();
        } else if (currentTab2 == WLTab.Places) {
            addPlacesTabContent();
        } else if (currentTab2 == WLTab.Stories) {
            addStoriesTabContent();
        }
    }

    private void addListingContent() {
        String title;
        if (this.dataController.hasAvailableListings()) {
            if (Experiments.showImprovedWishListFiltersUx()) {
                if (this.adapterInterface.isCurrentUserWishListOwner()) {
                    this.firstSectionHeader.buttonTextRes(C1758R.string.change_filters).buttonOnClickListener(WishListDetailsEpoxyController$$Lambda$5.lambdaFactory$(this));
                }
                if (!wishList().hasDates() && this.adapterInterface.isCurrentUserWishListOwner()) {
                    this.firstSectionHeader.descriptionRes(C1758R.string.wishlistdetails_add_dates_for_price_and_availability);
                }
            }
            if (isUserAMember()) {
                title = WishListPresenter.formatAvailableListingsCount(this.activity, this.dataController.availableListings.size());
            } else {
                title = WishListPresenter.formatListingCount((Context) this.activity, wishList());
            }
            this.firstSectionHeader.title(title);
        }
        Iterator it = this.dataController.availableListings.iterator();
        while (it.hasNext()) {
            WishlistedListing wishlistedListing = (WishlistedListing) it.next();
            wrapWithVotingIfNeeded(wishlistedListing, buildHomeCardModel(wishlistedListing).displayOptions(DisplayOptions.forHomeCard(this.activity, DisplayType.Vertical))).addTo(this);
        }
        addUnavailableListingContentIfAny();
    }

    private void addUnavailableListingContentIfAny() {
        if (!this.dataController.unavailableListings.isEmpty()) {
            int numUnavailableListings = this.dataController.unavailableListings.size();
            addUnavailabilityHeader(WishListPresenter.formatUnavailableListingsCount(this.activity, numUnavailableListings), !this.dataController.hasAvailableListings());
            List<HomeCardEpoxyModel> unavailableListingModels = new ArrayList<>(numUnavailableListings);
            Iterator it = this.dataController.unavailableListings.iterator();
            while (it.hasNext()) {
                unavailableListingModels.add(buildHomeCardModel((WishlistedListing) it.next()).displayOptions(DisplayOptions.forHomeCard(this.activity, DisplayType.Horizontal)));
            }
            this.unavailableHomesCarouselModel.models(unavailableListingModels);
        }
    }

    private void addUnavailabilityHeader(String title, boolean isEverythingUnavailable) {
        if (this.adapterInterface.isCurrentUserWishListOwner() && Experiments.showImprovedWishListFiltersUx()) {
            this.unavailableItemsHeaderModel.buttonTextRes(C1758R.string.change_filters).buttonOnClickListener(WishListDetailsEpoxyController$$Lambda$6.lambdaFactory$(this));
        }
        this.unavailableItemsHeaderModel.title(title).descriptionRes(getUnavailableDescription(isEverythingUnavailable));
    }

    private HomeCardEpoxyModel_ buildHomeCardModel(WishlistedListing wishlistedListing) {
        return new HomeCardEpoxyModel_().listing(wishlistedListing.getListing()).pricingQuote(wishlistedListing.getPricingQuote()).wishListableData(WishListableData.forHome(wishlistedListing.getListing()).source(C2813WishlistSource.SavedHomes).build()).m4729id((CharSequence) "home", wishlistedListing.getId()).showDivider(false).showListingTitle(true).clickListener(WishListDetailsEpoxyController$$Lambda$7.lambdaFactory$(this, wishlistedListing));
    }

    private int getUnavailableDescription(boolean everythingUnavailable) {
        if (!this.adapterInterface.isCurrentUserWishListOwner()) {
            return 0;
        }
        return (!everythingUnavailable || !Experiments.showImprovedWishListFiltersUx()) ? C1758R.string.try_updating_your_dates_or_number_of_guests : C1758R.string.wishlistdetails_nothing_available;
    }

    /* access modifiers changed from: private */
    public void onListingClicked(View view, WishlistedListing listing) {
        if (validateHasWishList()) {
            this.adapterInterface.getWLLogger().clickWishListedItem(wishList(), listing, WLTab.forJitney(this.currentTab));
            this.adapterInterface.getAnalytics().trackListingClicked(wishList(), listing.getListing());
            this.activity.startActivity(P3ActivityIntents.withListingPricingAndGuests(this.activity, listing.getListing(), listing.getPricingQuote(), wishList().getGuestDetails(), "wishlist"), AutoSharedElementCallback.getActivityOptionsBundle(this.activity, view));
        }
    }

    private void addTripContent() {
        addTripImmersionContent();
        addTripExperienceContent();
        addUnavailableTripContent();
    }

    private void addTripImmersionContent() {
        this.firstSectionHeader.title(WishListPresenter.formatAvailableImmersionsCount(this.activity, this.dataController.availableTripImmersions.size())).addIf(this.dataController.hasAvailableTripImmersions(), (EpoxyController) this);
        addTripModels(this.dataController.availableTripImmersions);
    }

    private void addTripExperienceContent() {
        if (this.dataController.hasAvailableTripExperiences()) {
            this.secondSectionHeader.title(WishListPresenter.formatAvailableExperiencesCount(this.activity, this.dataController.availableTripExperiences.size()));
            addTripModels(this.dataController.availableTripExperiences);
        }
    }

    private void addTripModels(List<WishListedTrip> trips) {
        for (WishListedTrip trip : trips) {
            add(wrapWithVotingIfNeeded(trip, buildTripModel(trip).displayOptions(DisplayOptions.forPosterCard(this.activity, DisplayType.Vertical))));
        }
    }

    private void addUnavailableTripContent() {
        if (!this.dataController.unavailableTrips.isEmpty()) {
            int numUnavailableTrips = this.dataController.unavailableTrips.size();
            addUnavailabilityHeader(WishListPresenter.formatUnavailableExperiencesCount(this.activity, numUnavailableTrips), !this.dataController.hasAvailableTripExperiences() && !this.dataController.hasAvailableTripImmersions());
            List<EpoxyModel<?>> unavailableTripModels = new ArrayList<>(numUnavailableTrips);
            Iterator it = this.dataController.unavailableTrips.iterator();
            while (it.hasNext()) {
                unavailableTripModels.add(buildTripModel((WishListedTrip) it.next()).displayOptions(DisplayOptions.forPosterCard(this.activity, DisplayType.Horizontal)));
            }
            this.unavailableTripsCarouselModel.models(unavailableTripModels);
        }
    }

    private PosterCardEpoxyModel_ buildTripModel(WishListedTrip wishListedTrip) {
        TripTemplate tripTemplate = wishListedTrip.getTrip();
        return new PosterCardEpoxyModel_().tripTemplate(tripTemplate).wishListableData(WishListableData.forTrip(tripTemplate).source(C2813WishlistSource.SavedExperiences).build()).clickListener(WishListDetailsEpoxyController$$Lambda$8.lambdaFactory$(this, wishListedTrip)).m5305id((CharSequence) "trip", wishListedTrip.getId());
    }

    /* access modifiers changed from: private */
    public void onTripClicked(View view, WishListedTrip trip) {
        if (validateHasWishList()) {
            this.adapterInterface.getWLLogger().clickWishListedItem(wishList(), trip, WLTab.forJitney(this.currentTab));
            TopLevelSearchParams searchParams = TopLevelSearchParams.builder().checkInDate(wishList().getCheckin()).checkOutDate(wishList().getCheckout()).guestDetails(wishList().getGuestDetails()).build();
            Intent intent = ReactNativeIntents.intentForExperiencePDP((Context) this.activity, trip.getTrip(), searchParams);
            ReactNativeUtils.setHasSharedElementTransition(intent, true);
            this.activity.startActivity(intent, AutoSharedElementCallback.getActivityOptionsBundle(this.activity, view));
        }
    }

    private void addStoriesTabContent() {
        Iterator it = this.dataController.articles.iterator();
        while (it.hasNext()) {
            WishListedArticle article = (WishListedArticle) it.next();
            add((EpoxyModel<?>) new StoryCardFeedItemEpoxyModel_().article(article.getArticle()).displayOptions(DisplayOptions.forStoryCard(this.activity, DisplayType.Vertical)).onClickListener(WishListDetailsEpoxyController$$Lambda$9.lambdaFactory$(this, article)).m4143id((CharSequence) "article", article.getId()).withGridPaddingLayout());
        }
    }

    private void addPlacesTabContent() {
        Iterator it = this.dataController.places.iterator();
        while (it.hasNext()) {
            WishListedPlace wishListedPlace = (WishListedPlace) it.next();
            GuidebookPlace guidebookPlace = wishListedPlace.getGuidebookPlace();
            add(wrapWithVotingIfNeeded(wishListedPlace, new RecommendationCardEpoxyModel_().title(guidebookPlace.getPrimaryPlace().getName()).actionKicker(guidebookPlace.getTopCategory()).imageUrl(guidebookPlace.getCoverPhoto()).clickListener(WishListDetailsEpoxyController$$Lambda$10.lambdaFactory$(this, wishListedPlace)).displayOptions(DisplayOptions.forRecommendationCard(this.activity, DisplayType.Vertical)).m5413id((CharSequence) "place", wishListedPlace.getId())));
        }
        Iterator it2 = this.dataController.placeActivities.iterator();
        while (it2.hasNext()) {
            WishListedPlaceActivity wishListedPlaceActivity = (WishListedPlaceActivity) it2.next();
            PlaceActivity placeActivity = wishListedPlaceActivity.getPlaceActivity();
            add(wrapWithVotingIfNeeded(wishListedPlaceActivity, new RecommendationCardEpoxyModel_().title(placeActivity.getPlace().getName()).actionKicker(placeActivity.getPlace().getCategoryForDisplay()).imageUrl(placeActivity.getCoverPhoto()).clickListener(WishListDetailsEpoxyController$$Lambda$11.lambdaFactory$(this, wishListedPlaceActivity)).displayOptions(DisplayOptions.forRecommendationCard(this.activity, DisplayType.Vertical)).m5413id((CharSequence) "placeActivity", (long) placeActivity.getId())));
        }
    }

    /* access modifiers changed from: private */
    public void onPlaceClicked(WishListedPlace place) {
        if (validateHasWishList()) {
            this.adapterInterface.getWLLogger().clickWishListedItem(wishList(), place, WLTab.forJitney(this.currentTab));
            this.activity.startActivity(ReactNativeIntents.intentForPlaceP3(this.activity, place.getGuidebookPlace()));
        }
    }

    /* access modifiers changed from: private */
    public void onPlaceActivityClicked(WishListedPlaceActivity placeActivity) {
        if (validateHasWishList()) {
            this.adapterInterface.getWLLogger().clickWishListedItem(wishList(), placeActivity, WLTab.forJitney(this.currentTab));
            this.activity.startActivity(PlacesIntents.intentForPlaceActivityPDP((Context) this.activity, placeActivity.getPlaceActivity()));
        }
    }

    /* access modifiers changed from: private */
    public void onStoryArticleClicked(WishListedArticle article) {
        if (validateHasWishList()) {
            this.adapterInterface.getWLLogger().clickWishListedItem(wishList(), article, WLTab.forJitney(this.currentTab));
            this.activity.startActivity(StoryDetailViewFragment.forPartialArticle(this.activity, article.getArticle(), NavigationTag.WishList.trackingName));
        }
    }

    private EpoxyModel<?> wrapWithVotingIfNeeded(final WishListItem item, AirEpoxyModel<?> model) {
        if (!this.adapterInterface.isCurrentUserAMember() || this.adapterInterface.getWishListMembers().size() <= 1) {
            return model;
        }
        return new WLVotingWrapperModel(item, new WLVotingRowModel_().vote(WLItemVote.getVote(item, this.adapterInterface.getCurrentUser())).upVoteCount(item.getUpVotes().size()).downVoteCount(item.getDownVotes().size()).showDivider(false).gridMode(isTablet()).m1470id(model.mo11715id()).listener(new WLVotingClickListener() {
            public void onVoteClicked(WLItemVote vote) {
                if (WishListDetailsEpoxyController.this.validateHasWishList()) {
                    WishListDetailsEpoxyController.this.dataController.saveVoteForCurrentUser(item, vote);
                }
            }

            public void onVoteCountClicked() {
                if (WishListDetailsEpoxyController.this.validateHasWishList()) {
                    WishListDetailsEpoxyController.this.adapterInterface.showVotesForItem(item);
                }
            }
        }), model);
    }

    private void addEmptyState() {
        this.firstSectionHeader.titleRes(C1758R.string.wish_list_empty_state_title).descriptionRes(C1758R.string.wish_lists_empty_state_message);
        this.linkRow.textRes(C1758R.string.start_exploring_call_to_action).clickListener(this.startExploringClickListener);
    }

    static /* synthetic */ void lambda$new$9(View v) {
        Context context = v.getContext();
        context.startActivity(SearchActivityIntents.intent(context));
    }

    private boolean isTablet() {
        return getSpanCount() == 4;
    }
}
