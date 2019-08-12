package com.airbnb.android.wishlists;

import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.models.WishListedArticle;
import com.airbnb.android.core.models.WishListedPlace;
import com.airbnb.android.core.models.WishListedPlaceActivity;
import com.airbnb.android.core.models.WishListedTrip;
import com.airbnb.android.core.models.WishlistedListing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListableType;
import com.airbnb.android.wishlists.requests.WishListedPlaceActivitiesRequest;
import com.airbnb.android.wishlists.requests.WishListedPlaceActivitiesResponse;
import com.airbnb.android.wishlists.requests.WishListedPlacesRequest;
import com.airbnb.android.wishlists.requests.WishListedPlacesResponse;
import com.airbnb.android.wishlists.requests.WishListedStoryArticleRequest;
import com.airbnb.android.wishlists.requests.WishListedStoryArticleResponse;
import com.airbnb.android.wishlists.requests.WishListedTripsRequest;
import com.airbnb.android.wishlists.requests.WishListedTripsResponse;
import com.airbnb.android.wishlists.requests.WishlistedListingsRequest;
import com.airbnb.android.wishlists.requests.WishlistedListingsResponse;
import com.airbnb.jitney.event.logging.VoteMethod.p289v1.C2806VoteMethod;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p032rx.Observer;

public class WLDetailsDataController {
    @State
    ArrayList<WishListedArticle> articles = new ArrayList<>();
    @State
    ArrayList<WishlistedListing> availableListings = new ArrayList<>();
    @State
    ArrayList<WishListedTrip> availableTripExperiences = new ArrayList<>();
    @State
    ArrayList<WishListedTrip> availableTripImmersions = new ArrayList<>();
    private final Set<OnWLDetailsDataChanged> dataChangeListeners = new HashSet();
    private final List<? extends BaseRequestListener<?>> dataRequestListeners;
    private WLDetailsFragmentInterface fragmentInterface;
    @State
    boolean hasStartedLoad;
    private final List<List<? extends WishListItem>> itemLists;
    private final List<List<? extends WishListItem>> listingLists;
    final RequestListener<WishlistedListingsResponse> listingsRequestListener = new C0699RL().onResponse(WLDetailsDataController$$Lambda$1.lambdaFactory$(this)).onError(WLDetailsDataController$$Lambda$2.lambdaFactory$(this)).onComplete(WLDetailsDataController$$Lambda$3.lambdaFactory$(this)).build();
    @State
    ArrayList<WishListedPlaceActivity> placeActivities = new ArrayList<>();
    final RequestListener<WishListedPlaceActivitiesResponse> placeActivitiesRequestListener = new C0699RL().onResponse(WLDetailsDataController$$Lambda$10.lambdaFactory$(this)).onError(WLDetailsDataController$$Lambda$11.lambdaFactory$(this)).onComplete(WLDetailsDataController$$Lambda$12.lambdaFactory$(this)).build();
    private final List<List<? extends WishListItem>> placeActivityLists;
    private final List<List<? extends WishListItem>> placeLists;
    @State
    ArrayList<WishListedPlace> places = new ArrayList<>();
    final RequestListener<WishListedPlacesResponse> placesRequestListener = new C0699RL().onResponse(WLDetailsDataController$$Lambda$4.lambdaFactory$(this)).onError(WLDetailsDataController$$Lambda$5.lambdaFactory$(this)).onComplete(WLDetailsDataController$$Lambda$6.lambdaFactory$(this)).build();
    private RequestManager requestManager;
    private final List<List<? extends WishListItem>> storyArticleLists;
    final RequestListener<WishListedStoryArticleResponse> storyArticleRequestListener = new C0699RL().onResponse(WLDetailsDataController$$Lambda$7.lambdaFactory$(this)).onError(WLDetailsDataController$$Lambda$8.lambdaFactory$(this)).onComplete(WLDetailsDataController$$Lambda$9.lambdaFactory$(this)).build();
    private final List<List<? extends WishListItem>> tripLists;
    final RequestListener<WishListedTripsResponse> tripsRequestListener = new C0699RL().onResponse(WLDetailsDataController$$Lambda$13.lambdaFactory$(this)).onError(WLDetailsDataController$$Lambda$14.lambdaFactory$(this)).onComplete(WLDetailsDataController$$Lambda$15.lambdaFactory$(this)).build();
    @State
    ArrayList<WishlistedListing> unavailableListings = new ArrayList<>();
    @State
    ArrayList<WishListedTrip> unavailableTrips = new ArrayList<>();
    final RequestListener<BaseResponse> votingRequestListener = new C0699RL().onError(WLDetailsDataController$$Lambda$16.lambdaFactory$(this)).build();

    public interface OnWLDetailsDataChanged {
        void onWLDetailsDataChanged();
    }

    public WLDetailsDataController(Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
        this.listingLists = ImmutableList.m1286of(this.availableListings, this.unavailableListings);
        this.placeLists = ImmutableList.m1285of(this.places);
        this.placeActivityLists = ImmutableList.m1285of(this.placeActivities);
        this.tripLists = ImmutableList.m1287of(this.availableTripExperiences, this.availableTripImmersions, this.unavailableTrips);
        this.storyArticleLists = ImmutableList.m1285of(this.articles);
        List<List<? extends WishListItem>> allItemLists = new ArrayList<>();
        allItemLists.addAll(this.listingLists);
        allItemLists.addAll(this.placeLists);
        allItemLists.addAll(this.placeActivityLists);
        allItemLists.addAll(this.storyArticleLists);
        allItemLists.addAll(this.tripLists);
        this.itemLists = ImmutableList.copyOf((Collection<? extends E>) allItemLists);
        this.dataRequestListeners = ImmutableList.m1289of(this.listingsRequestListener, this.placesRequestListener, this.tripsRequestListener, this.placeActivitiesRequestListener, this.storyArticleRequestListener);
    }

    public void attachInterface(WLDetailsFragmentInterface fragmentInterface2) {
        this.requestManager = fragmentInterface2.getRequestManager();
        this.fragmentInterface = fragmentInterface2;
        updateItemsIfNeeded();
        this.requestManager.subscribe(this);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void addListener(OnWLDetailsDataChanged listener) {
        this.dataChangeListeners.add(listener);
    }

    public void removeListener(OnWLDetailsDataChanged listener) {
        this.dataChangeListeners.remove(listener);
    }

    /* access modifiers changed from: private */
    public void notifyListeners() {
        for (OnWLDetailsDataChanged dataChangeListener : this.dataChangeListeners) {
            dataChangeListener.onWLDetailsDataChanged();
        }
    }

    public boolean hasAvailableListings() {
        return !this.availableListings.isEmpty();
    }

    public boolean hasAvailableTripImmersions() {
        return !this.availableTripImmersions.isEmpty();
    }

    public boolean hasAvailableTripExperiences() {
        return !this.availableTripExperiences.isEmpty();
    }

    public void clear() {
        this.hasStartedLoad = false;
        for (List<? extends WishListItem> itemList : this.itemLists) {
            itemList.clear();
        }
        for (BaseRequestListener<?> dataRequestListener : this.dataRequestListeners) {
            this.requestManager.cancelRequests(dataRequestListener);
        }
        notifyListeners();
    }

    public void refresh() {
        clear();
        loadDataIfNeeded();
    }

    public boolean loadDataIfNeeded() {
        if (this.hasStartedLoad || !this.fragmentInterface.hasLoadedWishList()) {
            return false;
        }
        this.hasStartedLoad = true;
        refreshListings();
        refreshTrips();
        refreshPlaces();
        refreshPlaceActivities();
        refreshStoryArticles();
        notifyListeners();
        return true;
    }

    public boolean hasLoadedTabContent(WLTab tab) {
        switch (tab) {
            case Homes:
                return hasLoadedType(WishListableType.Home);
            case Places:
                return hasLoadedType(WishListableType.Place) && hasLoadedType(WishListableType.PlaceActivity);
            case Trips:
                return hasLoadedType(WishListableType.Trip);
            case Stories:
                return hasLoadedType(WishListableType.StoryArticle);
            default:
                throw new IllegalStateException("unknown tab: " + tab);
        }
    }

    public boolean hasLoadedType(WishListableType type) {
        if (!this.hasStartedLoad) {
            return false;
        }
        switch (type) {
            case Home:
                if (this.requestManager.hasRequest((BaseRequestListener<T>) this.listingsRequestListener, WishlistedListingsRequest.class)) {
                    return false;
                }
                return true;
            case Place:
                if (this.requestManager.hasRequest((BaseRequestListener<T>) this.placesRequestListener, WishListedPlacesRequest.class)) {
                    return false;
                }
                return true;
            case PlaceActivity:
                if (this.requestManager.hasRequest((BaseRequestListener<T>) this.placeActivitiesRequestListener, WishListedPlaceActivitiesRequest.class)) {
                    return false;
                }
                return true;
            case Trip:
                if (this.requestManager.hasRequest((BaseRequestListener<T>) this.tripsRequestListener, WishListedTripsRequest.class)) {
                    return false;
                }
                return true;
            case StoryArticle:
                if (this.requestManager.hasRequest((BaseRequestListener<T>) this.storyArticleRequestListener, WishListedStoryArticleRequest.class)) {
                    return false;
                }
                return true;
            default:
                throw new IllegalStateException("unknown type: " + type);
        }
    }

    private static void clearAllLists(List<List<? extends WishListItem>> listOfLists) {
        for (List<?> list : listOfLists) {
            list.clear();
        }
    }

    private void refreshListings() {
        WishlistedListingsRequest request;
        clearAllLists(this.listingLists);
        if (wishList().getListingsCount() != 0) {
            if (this.fragmentInterface.isCurrentUserAMember()) {
                request = WishlistedListingsRequest.forMembers(wishList());
            } else {
                request = WishlistedListingsRequest.forPublic(wishList());
            }
            request.withListener((Observer) this.listingsRequestListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$0(WLDetailsDataController wLDetailsDataController, WishlistedListingsResponse response) {
        if (!((WishlistedListingsRequest) response.metadata.request()).hasSameListings(wLDetailsDataController.wishList())) {
            wLDetailsDataController.refreshListings();
            wLDetailsDataController.notifyListeners();
            return;
        }
        for (WishlistedListing wishListedListing : response.wishListedListings) {
            wishListedListing.getListing().trimForHomeCard();
        }
        wLDetailsDataController.sortByAvailability(response.wishListedListings, wLDetailsDataController.availableListings, wLDetailsDataController.unavailableListings);
    }

    private WishList wishList() {
        return (WishList) Check.notNull(this.fragmentInterface.getWishList(), "Wish list is null");
    }

    private void refreshPlaces() {
        WishListedPlacesRequest request;
        clearAllLists(this.placeLists);
        if (wishList().getPlacesCount() != 0) {
            if (this.fragmentInterface.isCurrentUserAMember()) {
                request = WishListedPlacesRequest.forMembers(wishList());
            } else {
                request = WishListedPlacesRequest.forPublic(wishList());
            }
            request.withListener((Observer) this.placesRequestListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$3(WLDetailsDataController wLDetailsDataController, WishListedPlacesResponse response) {
        if (!((WishListedPlacesRequest) response.metadata.request()).hasSamePlaces(wLDetailsDataController.wishList())) {
            wLDetailsDataController.refreshPlaces();
            wLDetailsDataController.notifyListeners();
            return;
        }
        wLDetailsDataController.places.clear();
        wLDetailsDataController.places.addAll(FluentIterable.from((Iterable<E>) response.wishListedPlaces).filter(WLDetailsDataController$$Lambda$17.lambdaFactory$()).toList());
        Iterator it = wLDetailsDataController.places.iterator();
        while (it.hasNext()) {
            ((WishListedPlace) it.next()).getGuidebookPlace().trimForPlaceCard();
        }
    }

    private void refreshPlaceActivities() {
        WishListedPlaceActivitiesRequest request;
        clearAllLists(this.placeActivityLists);
        if (wishList().getPlaceActivitiesCount() != 0) {
            if (this.fragmentInterface.isCurrentUserAMember()) {
                request = WishListedPlaceActivitiesRequest.forMembers(wishList());
            } else {
                request = WishListedPlaceActivitiesRequest.forPublic(wishList());
            }
            request.withListener((Observer) this.placeActivitiesRequestListener).execute(this.requestManager);
        }
    }

    private void refreshStoryArticles() {
        clearAllLists(this.storyArticleLists);
        if (wishList().getArticlesCount() != 0) {
            new WishListedStoryArticleRequest(wishList()).withListener((Observer) this.storyArticleRequestListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$6(WLDetailsDataController wLDetailsDataController, WishListedStoryArticleResponse response) {
        if (!((WishListedStoryArticleRequest) response.metadata.request()).hasSameStoryArticles(wLDetailsDataController.wishList())) {
            wLDetailsDataController.refreshStoryArticles();
            wLDetailsDataController.notifyListeners();
            return;
        }
        wLDetailsDataController.articles.clear();
        wLDetailsDataController.articles.addAll(response.articles);
    }

    static /* synthetic */ void lambda$new$9(WLDetailsDataController wLDetailsDataController, WishListedPlaceActivitiesResponse response) {
        if (!((WishListedPlaceActivitiesRequest) response.metadata.request()).hasSamePlaceActivities(wLDetailsDataController.wishList())) {
            wLDetailsDataController.refreshPlaceActivities();
            wLDetailsDataController.notifyListeners();
            return;
        }
        wLDetailsDataController.placeActivities.clear();
        wLDetailsDataController.placeActivities.addAll(response.wishListedPlaceActivities);
    }

    private void refreshTrips() {
        WishListedTripsRequest request;
        clearAllLists(this.tripLists);
        if (wishList().getTripsCount() != 0) {
            if (this.fragmentInterface.isCurrentUserAMember()) {
                request = WishListedTripsRequest.forMembers(wishList());
            } else {
                request = WishListedTripsRequest.forPublic(wishList());
            }
            request.withListener((Observer) this.tripsRequestListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$12(WLDetailsDataController wLDetailsDataController, WishListedTripsResponse response) {
        if (!((WishListedTripsRequest) response.metadata.request()).hasSameTrips(wLDetailsDataController.wishList())) {
            wLDetailsDataController.refreshTrips();
            wLDetailsDataController.notifyListeners();
            return;
        }
        for (WishListedTrip wishListedTrip : response.wishListedTrips) {
            wishListedTrip.getTrip().trimForPosterCard();
        }
        List<WishListedTrip> availableTrips = new ArrayList<>();
        wLDetailsDataController.sortByAvailability(response.wishListedTrips, availableTrips, wLDetailsDataController.unavailableTrips);
        wLDetailsDataController.sortByType(availableTrips, wLDetailsDataController.availableTripImmersions, wLDetailsDataController.availableTripExperiences);
    }

    public void updateItemsIfNeeded() {
        if (this.hasStartedLoad) {
            removeAllUnwishlistedItems();
            if (wishList().getListingsCount() != totalItemCount(this.listingLists) && hasLoadedType(WishListableType.Home)) {
                refreshListings();
            }
            if (wishList().getPlacesCount() != totalItemCount(this.placeLists) && hasLoadedType(WishListableType.Place)) {
                refreshPlaces();
            }
            if (wishList().getPlaceActivitiesCount() != totalItemCount(this.placeActivityLists) && hasLoadedType(WishListableType.PlaceActivity)) {
                refreshPlaceActivities();
            }
            if (wishList().getTripsCount() != totalItemCount(this.tripLists) && hasLoadedType(WishListableType.Trip)) {
                refreshTrips();
            }
            if (wishList().getArticlesCount() != totalItemCount(this.storyArticleLists) && hasLoadedType(WishListableType.StoryArticle)) {
                refreshStoryArticles();
            }
        }
    }

    private int totalItemCount(List<List<? extends WishListItem>> lists) {
        int result = 0;
        for (List<? extends WishListItem> list : lists) {
            result += list.size();
        }
        return result;
    }

    public void removeAllUnwishlistedItems() {
        for (List<? extends WishListItem> itemList : this.itemLists) {
            removeUnwishlistedItems(itemList);
        }
    }

    private <T extends WishListItem> void removeUnwishlistedItems(List<T> items) {
        if (this.fragmentInterface.isCurrentUserAMember()) {
            Iterator<T> it = items.iterator();
            while (it.hasNext()) {
                if (!this.fragmentInterface.getWishListManager().isItemInWishList((WishListItem) (WishListItem) it.next(), wishList())) {
                    it.remove();
                }
            }
        }
    }

    private <T extends WishListItem> void sortByAvailability(List<T> items, List<T> available, List<T> unavailable) {
        available.clear();
        unavailable.clear();
        for (T item : items) {
            if (item.isAvailable() || !this.fragmentInterface.isCurrentUserAMember()) {
                available.add(item);
            } else {
                unavailable.add(item);
            }
        }
    }

    private void sortByType(List<WishListedTrip> items, List<WishListedTrip> immersions, List<WishListedTrip> experiences) {
        immersions.clear();
        experiences.clear();
        for (WishListedTrip item : items) {
            switch (item.getTrip().getProductType()) {
                case Immersion:
                    immersions.add(item);
                    break;
                case Experience:
                    experiences.add(item);
                    break;
                default:
                    BugsnagWrapper.notify((Throwable) new IllegalStateException("Unknown trip product type"));
                    break;
            }
        }
    }

    public void saveVoteForCurrentUser(WishListItem item, WLItemVote newVote) {
        Check.notNull(newVote);
        User currentUser = this.fragmentInterface.getCurrentUser();
        WLItemVote originalVote = WLItemVote.getVote(item, currentUser);
        if (newVote == originalVote) {
            newVote = WLItemVote.None;
            if (originalVote == WLItemVote.None) {
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Wish list vote 'None' was selected twice"));
            }
        }
        logVoteChange(item, originalVote, newVote);
        setVoteForItem(item, newVote);
        WLVoteRequest request = new WLVoteRequest(item, currentUser, originalVote, newVote);
        request.withListener((Observer) this.votingRequestListener);
        this.requestManager.executeWithTag(request, getVoteRequestTag(item));
    }

    private void logVoteChange(WishListItem item, WLItemVote originalVote, WLItemVote newVote) {
        C2806VoteMethod voteMethod;
        switch (originalVote) {
            case None:
                if (newVote != WLItemVote.Up) {
                    voteMethod = C2806VoteMethod.Down;
                    break;
                } else {
                    voteMethod = C2806VoteMethod.Up;
                    break;
                }
            case Up:
                voteMethod = C2806VoteMethod.UpUndo;
                break;
            case Down:
                voteMethod = C2806VoteMethod.DownUndo;
                break;
            default:
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Unknown vote type: " + originalVote));
                voteMethod = C2806VoteMethod.Up;
                break;
        }
        this.fragmentInterface.getWLLogger().clickVoteOnItem(wishList(), voteMethod, item);
    }

    private void setVoteForItem(WishListItem item, WLItemVote vote) {
        for (List<? extends WishListItem> itemList : this.itemLists) {
            Iterator it = itemList.iterator();
            while (true) {
                if (it.hasNext()) {
                    WishListItem wishListItem = (WishListItem) it.next();
                    if (wishListItem.equals(item)) {
                        WLItemVote.setVoteForUser(wishListItem, vote, this.fragmentInterface.getCurrentUser());
                        WLItemVote.setVoteForUser(item, vote, this.fragmentInterface.getCurrentUser());
                        notifyListeners();
                        return;
                    }
                }
            }
        }
    }

    static /* synthetic */ void lambda$new$15(WLDetailsDataController wLDetailsDataController, AirRequestNetworkException e) {
        WLVoteRequest request = (WLVoteRequest) e.request();
        wLDetailsDataController.setVoteForItem(request.item, request.originalVote);
        wLDetailsDataController.fragmentInterface.showNetworkError(e);
    }

    private String getVoteRequestTag(WishListItem item) {
        return item.getItemType().name() + item.getId();
    }
}
