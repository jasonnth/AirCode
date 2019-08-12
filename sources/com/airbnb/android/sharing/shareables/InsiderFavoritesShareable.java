package com.airbnb.android.sharing.shareables;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.airbnb.android.sharing.models.ShareArguments;

public class InsiderFavoritesShareable extends Shareable {
    private final String baseUrl;

    /* renamed from: id */
    private final long f1857id;
    private final String imageUrl;
    private final String location;
    private final int numPlaces;
    private final String title;

    /* renamed from: com.airbnb.android.sharing.shareables.InsiderFavoritesShareable$1 */
    static /* synthetic */ class C16001 {

        /* renamed from: $SwitchMap$com$airbnb$android$sharing$enums$CustomShareActivities */
        static final /* synthetic */ int[] f1858x32796897 = new int[CustomShareActivities.values().length];
    }

    private InsiderFavoritesShareable(Context context, long id, String baseUrl2, String imageUrl2, String location2, String title2, int numPlaces2) {
        super(context);
        this.f1857id = id;
        this.baseUrl = baseUrl2;
        this.imageUrl = imageUrl2;
        this.location = location2;
        this.title = title2;
        this.numPlaces = numPlaces2;
    }

    public InsiderFavoritesShareable(Context context, ShareArguments shareArguments) {
        this(context, shareArguments.mo11468id().longValue(), shareArguments.url(), shareArguments.primaryImageUrl(), shareArguments.location(), shareArguments.guidebookTitle(), shareArguments.numPlaces().intValue());
    }

    public String getUrl() {
        return this.baseUrl;
    }

    public String getName() {
        return this.title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getDeeplinkPath() {
        return "d/Guidebook/insider?guidebookId=" + this.f1857id;
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        AirbnbEventLogger.event().name("sharing").mo8238kv(BaseAnalytics.OPERATION, "click").mo8238kv(BaseAnalytics.TARGET, "share_button").mo8238kv(ShareActivityIntents.ARG_ENTRY_POINT, ShareActivityIntents.ENTRY_POINT_GUIDEBOOK_INSIDER).mo8237kv("guidebook_id", this.f1857id).mo8238kv("service", packageName).track();
        int i = C16001.f1858x32796897[csa.ordinal()];
        return buildDefaultIntent(baseIntent, csa);
    }
}
