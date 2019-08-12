package com.airbnb.android.lib.wishlists;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.utils.Strap;

public class WLDetailsDeeplinkInterceptorActivity extends AirActivity {
    AffiliateInfo affiliateInfo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        Intent intent = getIntent();
        this.affiliateInfo.storeAffiliateParams(intent.getExtras());
        long wishListId = DeepLinkUtils.getParamAsId(intent, "wishlist_id", "id");
        if (wishListId == -1) {
            NetworkUtil.toastGenericNetworkError(this);
            String dataString = intent.getDataString();
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("type", "wishlist_deep_link").mo11639kv("unexpected_wl_url", dataString));
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Invalid wishlist intent: " + dataString));
        } else {
            startActivity(HomeActivityIntents.intentForWishList(this, wishListId));
        }
        finish();
    }
}
