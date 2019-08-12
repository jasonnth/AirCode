package com.airbnb.android.react;

import android.app.Activity;
import android.os.Handler;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.wishlists.PickWishListActivityIntents;
import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListLogger;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.core.wishlists.WishListableType;
import com.airbnb.android.core.wishlists.WishListsChangedListener;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import java.util.List;

public class WishListModule extends VersionedReactModuleBase implements WishListsChangedListener {
    private static final int VERSION = 4;
    private final Handler mainThreadHandler;
    private final WishListManager wishListManager;
    private final WishListLogger wlLogger;

    WishListModule(ReactApplicationContext reactContext, WishListManager wishListManager2, WishListLogger wlLogger2) {
        super(reactContext, 4);
        this.wishListManager = wishListManager2;
        this.wlLogger = wlLogger2;
        this.mainThreadHandler = new Handler(reactContext.getMainLooper());
        wishListManager2.addWishListsChangedListener(this);
    }

    public String getName() {
        return "WishListBridge";
    }

    @ReactMethod
    public void isListingWishListed(Double listingId, Promise promise) {
        promise.resolve(Boolean.valueOf(this.wishListManager.isItemWishListed(WishListableType.Home, listingId.longValue())));
    }

    @ReactMethod
    public void toggleListingWishListed(ReadableMap map) {
        toggleItem(WishListableType.Home, map);
    }

    @ReactMethod
    public void isTripTemplateWishListed(Double tripTemplateId, Promise promise) {
        promise.resolve(Boolean.valueOf(this.wishListManager.isItemWishListed(WishListableType.Trip, tripTemplateId.longValue())));
    }

    @ReactMethod
    public void toggleTripTemplateWishListed(ReadableMap map) {
        toggleItem(WishListableType.Trip, map);
    }

    @ReactMethod
    public void isPlaceWishListed(Double placeId, Promise promise) {
        promise.resolve(Boolean.valueOf(this.wishListManager.isItemWishListed(WishListableType.Place, placeId.longValue())));
    }

    @ReactMethod
    public void togglePlaceWishListed(ReadableMap map) {
        toggleItem(WishListableType.Place, map);
    }

    private void toggleItem(WishListableType type, ReadableMap map) {
        this.mainThreadHandler.post(WishListModule$$Lambda$1.lambdaFactory$(this, WishListableData.forType(type, (long) map.getDouble("id")).source(getSource(map.getString("source"))).build()));
    }

    static /* synthetic */ void lambda$toggleItem$0(WishListModule wishListModule, WishListableData data) {
        if (wishListModule.wishListManager.isItemWishListed(data)) {
            wishListModule.wlLogger.heartClickedToRemove(data);
            wishListModule.wishListManager.deleteItemFromAllWishLists(data);
            return;
        }
        wishListModule.wlLogger.heartClickedToAdd(data);
        Activity activity = wishListModule.getCurrentActivity();
        if (activity != null) {
            activity.startActivity(PickWishListActivityIntents.newIntent(activity, data));
        }
    }

    private static C2813WishlistSource getSource(String sourceString) {
        if (sourceString == null) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Null source"));
            return C2813WishlistSource.TripDetail;
        }
        char c = 65535;
        switch (sourceString.hashCode()) {
            case -1567535420:
                if (sourceString.equals("PLACE_SCREEN")) {
                    c = 4;
                    break;
                }
                break;
            case 737469656:
                if (sourceString.equals("INSIDER_MAP_SCREEN")) {
                    c = 3;
                    break;
                }
                break;
            case 1517254513:
                if (sourceString.equals("INSIDER_DETAIL_SCREEN")) {
                    c = 2;
                    break;
                }
                break;
            case 1858711479:
                if (sourceString.equals("TRIP_TEMPLATE_SCREEN")) {
                    c = 0;
                    break;
                }
                break;
            case 2028533621:
                if (sourceString.equals("SINGLE_EXPERIENCE_TRIP_TEMPLATE_SCREEN")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return C2813WishlistSource.TripDetail;
            case 1:
                return C2813WishlistSource.SingleExperienceTripDetail;
            case 2:
                return C2813WishlistSource.AlbumDetail;
            case 3:
                return C2813WishlistSource.AlbumMap;
            case 4:
                return C2813WishlistSource.PlaceDetail;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown source: " + sourceString));
                return C2813WishlistSource.TripDetail;
        }
    }

    public void onWishListsChanged(List<WishList> list, WishListChangeInfo changeInfo) {
        if (changeInfo == null) {
            ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), "wishListBulkChange", null);
        } else {
            ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), "wishListStateChanged", ConversionUtil.toWritableMap(MapBuilder.builder().put("id", Long.toString(changeInfo.getId())).put("type", changeInfo.getType().getRnKey()).put("isWishlisted", Boolean.valueOf(changeInfo.isAdded())).build()));
        }
    }
}
