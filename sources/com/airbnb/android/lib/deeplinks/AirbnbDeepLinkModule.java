package com.airbnb.android.lib.deeplinks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.p008mt.models.C6213ProductType;
import com.airbnb.android.utils.ParcelableStringMap;

public class AirbnbDeepLinkModule {
    public static Intent cityHostGuest(Context context, Bundle bundle) {
        return ReactNativeIntents.intentForExperiencePDP(context, true, getLongFromDeeplink(bundle, "tripTemplateId"));
    }

    public static Intent tripTemplate(Context context, Bundle bundle) {
        return ReactNativeIntents.intentForExperiencePDP(context, C6213ProductType.IMMERSION == C6213ProductType.from(bundle.getInt("type", C6213ProductType.IMMERSION.ordinal())), getLongFromDeeplink(bundle, "id"));
    }

    public static Intent placesActivity(Context context, Bundle bundle) {
        return PlacesIntents.intentForPlaceActivityPDP(context, getLongFromDeeplink(bundle, "id"));
    }

    public static Intent meetupPDP(Context context, Bundle bundle) {
        return PlacesIntents.intentForMeetupPDP(context, getLongFromDeeplink(bundle, "id"));
    }

    public static Intent placesInsiderGuidebook(Context context, Bundle bundle) {
        return ReactNativeIntents.intentForGuidebookInsider(context, getLongFromDeeplink(bundle, "id"), null, null, null);
    }

    public static Intent placesGuidebookPlace(Context context, Bundle bundle) {
        return ReactNativeIntents.intentForPlaceP3(context, Long.valueOf(getLongFromDeeplink(bundle, "placeId")), null, null);
    }

    public static Intent placesDetour(Context context, Bundle bundle) {
        return ReactNativeIntents.intentForGuidebookDetour(context, bundle.getString("id"), null, null);
    }

    public static Intent placesMeetupCollection(Context context, Bundle bundle) {
        ParcelableStringMap cityParam = new ParcelableStringMap();
        cityParam.put("city", bundle.getString("city"));
        return ReactNativeIntents.intentForGuidebookMeetupCollection(context, cityParam, null);
    }

    private static long getLongFromDeeplink(Bundle deepLinkBundle, String key) {
        return Long.valueOf(deepLinkBundle.getString(key)).longValue();
    }
}
