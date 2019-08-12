package com.airbnb.android.wishlists;

import com.airbnb.android.wishlists.requests.WishListedPlaceActivitiesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WLDetailsDataController$$Lambda$10 implements Action1 {
    private final WLDetailsDataController arg$1;

    private WLDetailsDataController$$Lambda$10(WLDetailsDataController wLDetailsDataController) {
        this.arg$1 = wLDetailsDataController;
    }

    public static Action1 lambdaFactory$(WLDetailsDataController wLDetailsDataController) {
        return new WLDetailsDataController$$Lambda$10(wLDetailsDataController);
    }

    public void call(Object obj) {
        WLDetailsDataController.lambda$new$9(this.arg$1, (WishListedPlaceActivitiesResponse) obj);
    }
}
