package com.airbnb.android.core.wishlists;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.LongSparseArray;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.PostInteractiveInitializer;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.events.AuthStateEvent;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.requests.CreateWishListRequest;
import com.airbnb.android.core.requests.DeleteWishListRequest;
import com.airbnb.android.core.requests.RefreshWishListRequest;
import com.airbnb.android.core.requests.RemoveListingFromWishListRequest;
import com.airbnb.android.core.requests.RemovePlaceActivityFromWishListRequest;
import com.airbnb.android.core.requests.RemovePlaceFromWishListRequest;
import com.airbnb.android.core.requests.RemoveStoryArticleFromWishListRequest;
import com.airbnb.android.core.requests.RemoveTripFromWishListRequest;
import com.airbnb.android.core.requests.SaveListingToWishListRequest;
import com.airbnb.android.core.requests.SavePlaceActivityToWishListRequest;
import com.airbnb.android.core.requests.SavePlaceToWishListRequest;
import com.airbnb.android.core.requests.SaveStoryArticleToWishListRequest;
import com.airbnb.android.core.requests.SaveTripToWishListRequest;
import com.airbnb.android.core.requests.UpdateWishListRequest;
import com.airbnb.android.core.requests.WishlistsRequest;
import com.airbnb.android.core.responses.WishListResponse;
import com.airbnb.android.core.responses.WishlistsResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.rxgroups.RequestSubscription;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p032rx.Observer;

public class WishListManager implements PostInteractiveInitializer {
    private static final int REFRESH_WL_DELAY_MS = 3000;
    private static final long TEMP_WISHLIST_ID = Long.MAX_VALUE;
    private final AirbnbAccountManager accountManager;
    private final Set<WishListsChangedListener> changeListeners = new HashSet();
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final WishListData data = new WishListData();
    private final Handler handler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean hasMoreWishListsToLoad;
    private WishList lastUpdatedWishList;
    private final LongSparseArray<String> refreshRequestTagMap = new LongSparseArray<>();
    private final NonResubscribableRequestListener<WishListResponse> refreshWishlistListener = new C0699RL().onResponse(WishListManager$$Lambda$1.lambdaFactory$(this)).buildWithoutResubscription();
    private final RequestManager requestManager;
    private boolean shouldShowBadge;
    /* access modifiers changed from: private */
    public WishListsCallHelper wishListsCall;
    private final NonResubscribableRequestListener<WishlistsResponse> wishListsRequestListener = new NonResubscribableRequestListener<WishlistsResponse>() {
        public void onResponse(WishlistsResponse response) {
            List<WishList> wishLists = response.getWishLists();
            WishListManager.this.hasMoreWishListsToLoad = wishLists.size() == 20;
            if (WishListManager.this.wishListsCall.offset == 0) {
                WishListManager.this.setWishLists(wishLists);
            } else {
                WishListManager.this.addWishLists(wishLists);
            }
        }

        public void onErrorResponse(AirRequestNetworkException error) {
            NetworkUtil.toastNetworkError(WishListManager.this.context, C0716R.string.wishlist_fetch_error);
            WishListManager.this.hasMoreWishListsToLoad = false;
            WishListManager.this.notifyWishListsChanged();
        }

        public void onRequestCompleted(boolean successful) {
            WishListManager.this.wishListsCall = null;
            if (WishListManager.this.hasMoreWishListsToLoad) {
                WishListManager.this.fetchWishLists(WishListManager.this.data.getWishListCount(), false);
            }
        }
    };

    private static class WishListsCallHelper {
        final int offset;
        final RequestSubscription subscription;

        WishListsCallHelper(RequestSubscription subscription2, int offset2) {
            this.subscription = subscription2;
            this.offset = offset2;
        }

        /* access modifiers changed from: 0000 */
        public void cancelCall() {
            this.subscription.cancel();
        }
    }

    public WishListManager(Context context2, AirbnbAccountManager accountManager2, Bus bus, AirRequestInitializer airRequestInitializer) {
        this.accountManager = accountManager2;
        this.context = context2;
        this.requestManager = RequestManager.onCreate(airRequestInitializer, null, null);
        this.handler.post(WishListManager$$Lambda$2.lambdaFactory$(this, bus));
    }

    @Subscribe
    public void onAuthStatusChanged(AuthStateEvent event) {
        clearLastUpdatedWishList();
        refreshWishLists(true);
        notifyWishListsChanged();
    }

    /* access modifiers changed from: private */
    public void notifyWishListsChanged() {
        notifyWishListsChanged(null);
    }

    private void notifyWishListsChanged(WishListChangeInfo changeInfo) {
        if (changeInfo != null && changeInfo.isAdded()) {
            this.lastUpdatedWishList = changeInfo.getWishList();
            this.shouldShowBadge = true;
        }
        List<WishList> wishListsCopy = this.data.getWishLists();
        Iterator it = new HashSet<>(this.changeListeners).iterator();
        while (it.hasNext()) {
            ((WishListsChangedListener) it.next()).onWishListsChanged(wishListsCopy, changeInfo);
        }
    }

    public WishList getLastUpdatedWishList() {
        if (this.lastUpdatedWishList != null) {
            this.lastUpdatedWishList = getWishList(this.lastUpdatedWishList);
        }
        return this.lastUpdatedWishList;
    }

    public void clearLastUpdatedWishList() {
        this.lastUpdatedWishList = null;
        clearBadge();
    }

    public void addWishListsChangedListener(WishListsChangedListener listener) {
        this.changeListeners.add(listener);
    }

    public void removeWishListsChangedListener(WishListsChangedListener listener) {
        this.changeListeners.remove(listener);
    }

    public boolean isLoadingWishLists() {
        return this.hasMoreWishListsToLoad;
    }

    /* access modifiers changed from: private */
    public void fetchWishLists(int offset, boolean allowDoubleResponse) {
        Check.state(this.accountManager.isCurrentUserAuthorized());
        Check.state(this.wishListsCall == null);
        WishlistsRequest request = new WishlistsRequest(offset, this.wishListsRequestListener);
        if (allowDoubleResponse && offset == 0) {
            request.doubleResponse();
        }
        this.wishListsCall = new WishListsCallHelper(request.execute(NetworkUtil.singleFireExecutor()), offset);
    }

    public List<WishList> getWishLists() {
        return this.data.getWishLists();
    }

    /* access modifiers changed from: private */
    public void setWishLists(List<WishList> wishLists) {
        this.data.setWishLists(wishLists);
        notifyWishListsChanged();
    }

    /* access modifiers changed from: private */
    public void delayedRefreshWishList(WishList wishList) {
        cancelRefreshWishListRequest(wishList);
        this.handler.postAtTime(WishListManager$$Lambda$3.lambdaFactory$(this, wishList), getRefreshWLRequestTag(wishList), SystemClock.uptimeMillis() + 3000);
    }

    private void cancelRefreshWishListRequest(WishList wishList) {
        String tag = getRefreshWLRequestTag(wishList);
        this.requestManager.cancelRequest((BaseRequestListener<?>) this.refreshWishlistListener, tag);
        this.handler.removeCallbacksAndMessages(tag);
    }

    /* access modifiers changed from: private */
    public void immediateRefreshWishList(WishList wishList) {
        cancelRefreshWishListRequest(wishList);
        this.requestManager.executeWithTag(new RefreshWishListRequest(wishList.getId()).withListener((Observer) this.refreshWishlistListener), getRefreshWLRequestTag(wishList));
    }

    private String getRefreshWLRequestTag(WishList wishList) {
        String existingTag = (String) this.refreshRequestTagMap.get(wishList.getId());
        if (existingTag != null) {
            return existingTag;
        }
        String existingTag2 = "refreshWL" + wishList.getId();
        this.refreshRequestTagMap.put(wishList.getId(), existingTag2);
        return existingTag2;
    }

    public boolean isEmpty() {
        return this.data.getWishListCount() == 0;
    }

    /* access modifiers changed from: private */
    public void addWishLists(List<WishList> wishLists) {
        this.data.addWishLists(wishLists);
        notifyWishListsChanged();
    }

    public void addWishList(WishList wishList) {
        this.data.addWishList(wishList);
        notifyWishListsChanged();
    }

    public void addNewWishList(WishList wishList, WishListableData itemAdded) {
        this.data.addWishList(wishList);
        notifyWishListsChanged(WishListChangeInfo.forNewWishList(wishList, itemAdded));
    }

    public WishList getWishList(WishList wishList) {
        return this.data.getWishList(wishList);
    }

    public WishList getWishListById(long wishListId) {
        return this.data.getWishListById(wishListId);
    }

    public boolean hasWishList(WishList wishList) {
        return getWishList(wishList) != null;
    }

    public void removeWishList(WishList wishList) {
        this.data.removeWishList(wishList);
        notifyWishListsChanged();
    }

    public void saveItemToWishList(WishListableData data2, WishList wishList) {
        BaseRequestV2<BaseResponse> request;
        cancelRefreshWishListRequest(wishList);
        addItemToWishListLocally(data2, wishList, true);
        switch (data2.type()) {
            case Home:
                request = buildSaveListingToWishListRequest(data2, wishList);
                break;
            case PlaceActivity:
                request = buildSavePlaceActivityToWishListRequest(data2, wishList);
                break;
            case StoryArticle:
                request = buildSaveStoryArticleToWishListRequset(data2, wishList);
                break;
            case Place:
                request = buildSavePlaceToWishListRequest(data2, wishList);
                break;
            case Trip:
                request = buildSaveTripToWishListRequest(data2, wishList);
                break;
            default:
                throw new IllegalStateException("unknown type: " + data2.type());
        }
        request.withListener((Observer) new C0699RL().onResponse(WishListManager$$Lambda$4.lambdaFactory$(this, wishList)).onError(WishListManager$$Lambda$5.lambdaFactory$(this, data2, wishList)).buildWithoutResubscription()).execute(NetworkUtil.singleFireExecutor());
    }

    public void saveItemToSuggestedWishList(WishListableData item) {
        for (WishList wishList : getWishLists()) {
            if (wishList.getName().equalsIgnoreCase(item.suggestedWishListName())) {
                saveItemToWishList(item, wishList);
                return;
            }
        }
        WishList tempWishList = new WishList();
        tempWishList.setName(item.suggestedWishListName());
        tempWishList.setTripIds(new ArrayList());
        tempWishList.setPlaceIds(new ArrayList());
        tempWishList.setListingIds(new ArrayList());
        tempWishList.setPlaceActivityIds(new ArrayList());
        tempWishList.setArticleIds(new ArrayList());
        tempWishList.setId(Long.MAX_VALUE);
        addItemToWishListLocally(item, tempWishList, true);
        new CreateWishListRequest(item.suggestedWishListName(), item, true).withListener((Observer) new C0699RL().onResponse(WishListManager$$Lambda$6.lambdaFactory$(this, tempWishList)).onError(WishListManager$$Lambda$7.lambdaFactory$(this, tempWishList)).buildWithoutResubscription()).execute(NetworkUtil.singleFireExecutor());
    }

    static /* synthetic */ void lambda$saveItemToSuggestedWishList$5(WishListManager wishListManager, WishList tempWishList, WishListResponse response) {
        wishListManager.data.removeWishList(tempWishList);
        wishListManager.data.addWishList(response.wishList);
        wishListManager.lastUpdatedWishList = response.wishList;
    }

    static /* synthetic */ void lambda$saveItemToSuggestedWishList$6(WishListManager wishListManager, WishList tempWishList, AirRequestNetworkException e) {
        wishListManager.removeWishList(tempWishList);
        NetworkUtil.toastNetworkError(wishListManager.context, (NetworkException) e);
    }

    private BaseRequestV2<BaseResponse> buildSaveListingToWishListRequest(WishListableData listingData, WishList wishList) {
        return new SaveListingToWishListRequest(wishList, listingData.itemId());
    }

    private BaseRequestV2<BaseResponse> buildSavePlaceActivityToWishListRequest(WishListableData activityData, WishList wishList) {
        return new SavePlaceActivityToWishListRequest(wishList, activityData.itemId());
    }

    private BaseRequestV2<BaseResponse> buildSaveStoryArticleToWishListRequset(WishListableData storyData, WishList wishList) {
        return new SaveStoryArticleToWishListRequest(wishList, storyData.itemId());
    }

    private BaseRequestV2<BaseResponse> buildSaveTripToWishListRequest(WishListableData tripData, WishList wishList) {
        return new SaveTripToWishListRequest(wishList, tripData.itemId());
    }

    private BaseRequestV2<BaseResponse> buildSavePlaceToWishListRequest(WishListableData placeData, WishList wishList) {
        return new SavePlaceToWishListRequest(wishList, placeData.itemId());
    }

    /* access modifiers changed from: private */
    public void handleSaveItemFailure(WishListableData item, WishList wishList, NetworkException e) {
        removeItemFromWishListLocally(item, wishList, false);
        NetworkUtil.toastNetworkError(this.context, e);
        immediateRefreshWishList(wishList);
    }

    public void deleteWishList(final WishList wishList) {
        removeWishList(wishList);
        new DeleteWishListRequest(wishList.getId()).withListener((Observer) new SimpleRequestListener<WishListResponse>() {
            public void onErrorResponse(AirRequestNetworkException error) {
                WishListManager.this.addWishList(wishList);
                NetworkUtil.toastNetworkError(WishListManager.this.context, C0716R.string.wishlist_delete_error);
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    public boolean isItemInWishList(WishListItem item, WishList wishList) {
        return this.data.isItemInWishList(item, wishList);
    }

    public boolean isItemInWishList(WishListableData wishListableData, WishList wishList) {
        return this.data.isItemInWishList(wishListableData.type(), wishListableData.itemId(), wishList);
    }

    public void refreshWishLists(boolean allowCachedData) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.handler.post(WishListManager$$Lambda$8.lambdaFactory$(this, allowCachedData));
            return;
        }
        cancelAndClearWishListsCall();
        this.hasMoreWishListsToLoad = this.accountManager.isCurrentUserAuthorized();
        this.data.clear();
        if (this.hasMoreWishListsToLoad) {
            fetchWishLists(0, allowCachedData);
        }
    }

    private void cancelAndClearWishListsCall() {
        if (this.wishListsCall != null) {
            this.wishListsCall.cancelCall();
            this.wishListsCall = null;
        }
    }

    public void deleteItemFromAllWishLists(WishListableData item) {
        for (WishList wishList : this.data.getWishListsWithItem(item)) {
            deleteItemFromWishList(wishList, item);
        }
    }

    private void deleteItemFromWishList(WishList wishList, WishListableData item) {
        BaseRequestV2<BaseResponse> request;
        cancelRefreshWishListRequest(wishList);
        removeItemFromWishListLocally(item, wishList, true);
        switch (item.type()) {
            case Home:
                request = buildDeleteListingFromWishListRequest(wishList, item);
                break;
            case PlaceActivity:
                request = buildDeletePlaceActivityFromWishListRequest(wishList, item);
                break;
            case StoryArticle:
                request = buildDeleteStoryArticleFromWishListRequest(wishList, item);
                break;
            case Place:
                request = buildDeletePlaceFromWishListRequest(wishList, item);
                break;
            case Trip:
                request = buildDeleteTripFromWishListRequest(wishList, item);
                break;
            default:
                throw new IllegalStateException("unknown type: " + item.type());
        }
        request.withListener((Observer) new C0699RL().onResponse(WishListManager$$Lambda$9.lambdaFactory$(this, wishList)).onError(WishListManager$$Lambda$10.lambdaFactory$(this, item, wishList)).buildWithoutResubscription()).execute(NetworkUtil.singleFireExecutor());
    }

    private BaseRequestV2<BaseResponse> buildDeleteListingFromWishListRequest(WishList wishList, WishListableData listingData) {
        return new RemoveListingFromWishListRequest(wishList, listingData.itemId());
    }

    private BaseRequestV2<BaseResponse> buildDeleteTripFromWishListRequest(WishList wishList, WishListableData tripData) {
        return new RemoveTripFromWishListRequest(wishList, tripData.itemId());
    }

    private BaseRequestV2<BaseResponse> buildDeletePlaceActivityFromWishListRequest(WishList wishList, WishListableData data2) {
        return new RemovePlaceActivityFromWishListRequest(wishList, data2.itemId());
    }

    private BaseRequestV2<BaseResponse> buildDeleteStoryArticleFromWishListRequest(WishList wishList, WishListableData data2) {
        return new RemoveStoryArticleFromWishListRequest(wishList, data2.itemId());
    }

    private BaseRequestV2<BaseResponse> buildDeletePlaceFromWishListRequest(WishList wishList, WishListableData placeData) {
        return new RemovePlaceFromWishListRequest(wishList, placeData.itemId());
    }

    /* access modifiers changed from: private */
    public void handleDeleteItemFailure(WishListableData item, WishList wishList) {
        addItemToWishListLocally(item, wishList, false);
        NetworkUtil.toastNetworkError(this.context, C0716R.string.wishlist_update_error);
        immediateRefreshWishList(wishList);
    }

    public void addItemToWishListLocally(WishListableData item, WishList wishList, boolean doneByUser) {
        this.data.addItemToWishList(item, wishList);
        notifyWishListsChanged(WishListChangeInfo.forItemAdded(wishList, item, doneByUser));
    }

    public void removeItemFromWishListLocally(WishListableData item, WishList wishList, boolean doneByUser) {
        this.data.removeItemFromWishList(item, wishList);
        notifyWishListsChanged(WishListChangeInfo.forItemRemoved(wishList, item, doneByUser));
    }

    public void setDates(WishList wishList, AirDate checkIn, AirDate checkOut) {
        setDatesAndGuestFilters(wishList, checkIn, checkOut, wishList.getGuestDetails());
    }

    public void setGuestFilters(WishList wishList, GuestDetails newGuestFilters) {
        setDatesAndGuestFilters(wishList, wishList.getCheckin(), wishList.getCheckout(), newGuestFilters);
    }

    public void setDatesAndGuestFilters(WishList wishList, AirDate checkIn, AirDate checkOut, GuestDetails guestFilters) {
        final GuestDetails originalFilters = wishList.getGuestDetails();
        final AirDate originalCheckIn = wishList.getCheckin();
        final AirDate originalCheckOut = wishList.getCheckout();
        updateLocalWishListFilters(wishList, checkIn, checkOut, guestFilters);
        final WishList wishList2 = wishList;
        UpdateWishListRequest.forDateAndFilters(wishList.getId(), checkIn, checkOut, guestFilters).withListener((Observer) new NonResubscribableRequestListener<WishListResponse>() {
            public void onResponse(WishListResponse data) {
                WishListManager.this.addWishList(data.wishList);
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                NetworkUtil.toastNetworkError(WishListManager.this.context, C0716R.string.wishlist_update_error);
                if (WishListManager.this.hasWishList(wishList2)) {
                    WishListManager.this.updateLocalWishListFilters(wishList2, originalCheckIn, originalCheckOut, originalFilters);
                }
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void updateLocalWishListFilters(WishList wishList, AirDate checkIn, AirDate checkOut, GuestDetails guestFilters) {
        WishList internalCopy = (WishList) Check.notNull(getWishList(wishList), "Wish list doesn't exist: " + wishList);
        internalCopy.setGuestFilters(guestFilters);
        internalCopy.setCheckin(checkIn);
        internalCopy.setCheckout(checkOut);
        addWishList(internalCopy);
    }

    public boolean isItemWishListed(WishListableData itemData) {
        return this.data.isItemWishListed(itemData);
    }

    public boolean isItemWishListed(WishListableType type, long itemId) {
        return this.data.isItemWishListed(type, itemId);
    }

    public int getWishListCount() {
        return this.data.getWishListCount();
    }

    public void clearBadge() {
        this.shouldShowBadge = false;
    }

    public boolean shouldShowBadge() {
        return this.shouldShowBadge;
    }

    public void initialize() {
        refreshWishLists(true);
    }

    public static boolean isTemporaryWishList(WishList wishList) {
        return wishList.getId() == Long.MAX_VALUE;
    }
}
