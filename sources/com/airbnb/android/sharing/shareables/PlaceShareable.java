package com.airbnb.android.sharing.shareables;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.airbnb.android.sharing.models.ShareArguments;

public class PlaceShareable extends Shareable {
    private final String baseUrl;

    /* renamed from: id */
    private final long f1859id;
    private final String imageUrl;
    private final String location;
    private final String placeName;
    private final String placeType;

    /* renamed from: com.airbnb.android.sharing.shareables.PlaceShareable$1 */
    static /* synthetic */ class C16011 {

        /* renamed from: $SwitchMap$com$airbnb$android$sharing$enums$CustomShareActivities */
        static final /* synthetic */ int[] f1860x32796897 = new int[CustomShareActivities.values().length];
    }

    private PlaceShareable(Context context, long id, String baseUrl2, String imageUrl2, String location2, String placeName2, String placeType2) {
        super(context);
        this.f1859id = id;
        this.baseUrl = baseUrl2;
        this.imageUrl = imageUrl2;
        this.location = location2;
        this.placeName = placeName2;
        this.placeType = placeType2;
    }

    public PlaceShareable(Context context, ShareArguments shareArguments) {
        this(context, shareArguments.mo11468id().longValue(), shareArguments.url(), shareArguments.primaryImageUrl(), shareArguments.location(), shareArguments.placeName(), shareArguments.placeType());
    }

    public String getUrl() {
        return this.baseUrl;
    }

    public String getName() {
        return this.placeName;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getDeeplinkPath() {
        return "d/Guidebook/place?placeId=" + this.f1859id;
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        AirbnbEventLogger.event().name("sharing").mo8238kv(BaseAnalytics.OPERATION, "click").mo8238kv(BaseAnalytics.TARGET, "share_button").mo8238kv(ShareActivityIntents.ARG_ENTRY_POINT, ShareActivityIntents.ENTRY_POINT_GUIDEBOOK_PLACE).mo8237kv("place_id", this.f1859id).mo8238kv("service", packageName).track();
        int i = C16011.f1860x32796897[csa.ordinal()];
        return buildDefaultIntent(baseIntent, csa);
    }
}
