package com.airbnb.android.core.wishlists;

import android.util.LongSparseArray;
import com.airbnb.android.core.models.WishList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class WishListData {
    private final LongSparseArray<Set<WishList>> listingIdToWishListMap = new LongSparseArray<>();
    private final LongSparseArray<Set<WishList>> placeActivityIdToWishListMap = new LongSparseArray<>();
    private final LongSparseArray<Set<WishList>> placeIdToWishListMap = new LongSparseArray<>();
    private final LongSparseArray<Set<WishList>> storyArticleIdToWishListMap = new LongSparseArray<>();
    private final LongSparseArray<Set<WishList>> tripIdToWishListMap = new LongSparseArray<>();
    private final LinkedList<WishList> wishLists = new LinkedList<>();

    private interface WishListIdCallback {
        List<Long> getWishListedIds(WishList wishList);
    }

    private interface WishListItemAction {
        void action(WishList wishList, long j);
    }

    WishListData() {
    }

    /* access modifiers changed from: 0000 */
    public void setWishLists(List<WishList> wishLists2) {
        clear();
        this.wishLists.addAll(wishLists2);
        rebuildMaps();
    }

    /* access modifiers changed from: 0000 */
    public void addWishLists(List<WishList> wishListsToAdd) {
        this.wishLists.removeAll(wishListsToAdd);
        this.wishLists.addAll(wishListsToAdd);
        rebuildMaps();
    }

    /* access modifiers changed from: 0000 */
    public void addWishList(WishList wishListToAdd) {
        this.wishLists.remove(wishListToAdd);
        this.wishLists.addFirst(wishListToAdd);
        rebuildMaps();
    }

    private void rebuildMaps() {
        rebuildMap(this.listingIdToWishListMap, WishListData$$Lambda$1.lambdaFactory$());
        rebuildMap(this.tripIdToWishListMap, WishListData$$Lambda$2.lambdaFactory$());
        rebuildMap(this.placeIdToWishListMap, WishListData$$Lambda$3.lambdaFactory$());
        rebuildMap(this.placeActivityIdToWishListMap, WishListData$$Lambda$4.lambdaFactory$());
        rebuildMap(this.storyArticleIdToWishListMap, WishListData$$Lambda$5.lambdaFactory$());
    }

    private void rebuildMap(LongSparseArray<Set<WishList>> map, WishListIdCallback idCallback) {
        map.clear();
        Iterator it = this.wishLists.iterator();
        while (it.hasNext()) {
            WishList wishList = (WishList) it.next();
            for (Long id : idCallback.getWishListedIds(wishList)) {
                Set<WishList> wishLists2 = (Set) map.get(id.longValue());
                if (wishLists2 == null) {
                    wishLists2 = new HashSet<>(3);
                    map.put(id.longValue(), wishLists2);
                }
                wishLists2.add(wishList);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public WishList getWishList(WishList wishList) {
        int index = this.wishLists.indexOf(wishList);
        if (index == -1) {
            return null;
        }
        return (WishList) this.wishLists.get(index);
    }

    public WishList getWishListById(long wishListId) {
        Iterator it = this.wishLists.iterator();
        while (it.hasNext()) {
            WishList wishList = (WishList) it.next();
            if (wishList.getId() == wishListId) {
                return wishList;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public List<WishList> getWishLists() {
        return new ArrayList(this.wishLists);
    }

    /* access modifiers changed from: 0000 */
    public void removeWishList(WishList wishList) {
        this.wishLists.remove(wishList);
        rebuildMaps();
    }

    /* access modifiers changed from: 0000 */
    public void addItemToWishList(WishListableData item, WishList wishList) {
        WishListItemAction action;
        switch (item.type()) {
            case Home:
                action = WishListData$$Lambda$6.lambdaFactory$();
                break;
            case Trip:
                action = WishListData$$Lambda$7.lambdaFactory$();
                break;
            case PlaceActivity:
                action = WishListData$$Lambda$8.lambdaFactory$();
                break;
            case StoryArticle:
                action = WishListData$$Lambda$9.lambdaFactory$();
                break;
            case Place:
                action = WishListData$$Lambda$10.lambdaFactory$();
                break;
            default:
                throw new IllegalStateException("unknown type: " + item.type());
        }
        forWishListItem(wishList, item.itemId(), action);
    }

    /* access modifiers changed from: 0000 */
    public void removeItemFromWishList(WishListableData item, WishList wishList) {
        WishListItemAction action;
        switch (item.type()) {
            case Home:
                action = WishListData$$Lambda$11.lambdaFactory$();
                break;
            case Trip:
                action = WishListData$$Lambda$12.lambdaFactory$();
                break;
            case PlaceActivity:
                action = WishListData$$Lambda$13.lambdaFactory$();
                break;
            case StoryArticle:
                action = WishListData$$Lambda$14.lambdaFactory$();
                break;
            case Place:
                action = WishListData$$Lambda$15.lambdaFactory$();
                break;
            default:
                throw new IllegalStateException("unknown type: " + item.type());
        }
        forWishListItem(wishList, item.itemId(), action);
    }

    private void forWishListItem(WishList wishList, long itemId, WishListItemAction callback) {
        callback.action(wishList, itemId);
        WishList existingWishList = getWishList(wishList);
        if (existingWishList != null) {
            callback.action(existingWishList, itemId);
            addWishList(existingWishList);
            return;
        }
        addWishList(wishList);
    }

    private void forAllWishListsWithItem(LongSparseArray<Set<WishList>> map, long itemId, WishListItemAction callback) {
        for (WishList wishList : getWishListsWithItem(map, itemId)) {
            callback.action(wishList, itemId);
        }
        rebuildMaps();
    }

    /* access modifiers changed from: 0000 */
    public void removeListingFromAllWishLists(long listingId) {
        forAllWishListsWithItem(this.listingIdToWishListMap, listingId, WishListData$$Lambda$16.lambdaFactory$());
    }

    /* access modifiers changed from: 0000 */
    public List<WishList> getWishListsWithItem(WishListableData item) {
        return getWishListsWithItem(mapForType(item.type()), item.itemId());
    }

    private static List<WishList> getWishListsWithItem(LongSparseArray<Set<WishList>> map, long itemId) {
        Set<WishList> wishLists2 = (Set) map.get(itemId);
        if (wishLists2 == null) {
            return Collections.emptyList();
        }
        return new ArrayList(wishLists2);
    }

    public boolean isItemWishListed(WishListableData item) {
        return isItemWishListed(item.type(), item.itemId());
    }

    public boolean isItemWishListed(WishListableType type, long itemId) {
        return isItemWishListed(mapForType(type), itemId);
    }

    private boolean isItemWishListed(LongSparseArray<Set<WishList>> map, long itemId) {
        Set<WishList> wishLists2 = (Set) map.get(itemId);
        return wishLists2 != null && !wishLists2.isEmpty();
    }

    public boolean isItemInWishList(WishListItem item, WishList wishList) {
        return isItemInWishList(item.getItemType(), item.getItemId(), wishList);
    }

    public boolean isItemInWishList(WishListableType type, long itemId, WishList wishList) {
        return isItemInWishList(mapForType(type), itemId, wishList);
    }

    private boolean isItemInWishList(LongSparseArray<Set<WishList>> map, long itemId, WishList wishList) {
        Set<WishList> wishLists2 = (Set) map.get(itemId);
        return wishLists2 != null && wishLists2.contains(wishList);
    }

    private LongSparseArray<Set<WishList>> mapForType(WishListableType type) {
        switch (type) {
            case Home:
                return this.listingIdToWishListMap;
            case Trip:
                return this.tripIdToWishListMap;
            case PlaceActivity:
                return this.placeActivityIdToWishListMap;
            case StoryArticle:
                return this.storyArticleIdToWishListMap;
            case Place:
                return this.placeIdToWishListMap;
            default:
                throw new IllegalStateException("Unknown type: " + type);
        }
    }

    /* access modifiers changed from: 0000 */
    public void clear() {
        this.wishLists.clear();
        rebuildMaps();
    }

    /* access modifiers changed from: 0000 */
    public int getWishListCount() {
        return this.wishLists.size();
    }

    public String toString() {
        return "{WishListData{ Wish Lists: " + this.wishLists + "\nMap data: " + this.listingIdToWishListMap + "}";
    }
}
