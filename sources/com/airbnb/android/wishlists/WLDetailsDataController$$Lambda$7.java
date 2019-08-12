package com.airbnb.android.wishlists;

import com.airbnb.android.wishlists.requests.WishListedStoryArticleResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WLDetailsDataController$$Lambda$7 implements Action1 {
    private final WLDetailsDataController arg$1;

    private WLDetailsDataController$$Lambda$7(WLDetailsDataController wLDetailsDataController) {
        this.arg$1 = wLDetailsDataController;
    }

    public static Action1 lambdaFactory$(WLDetailsDataController wLDetailsDataController) {
        return new WLDetailsDataController$$Lambda$7(wLDetailsDataController);
    }

    public void call(Object obj) {
        WLDetailsDataController.lambda$new$6(this.arg$1, (WishListedStoryArticleResponse) obj);
    }
}
