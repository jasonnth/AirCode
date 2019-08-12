package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.ForYouMetaData;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.p008mt.models.C6213ProductType;
import com.airbnb.android.utils.ParcelableStringMap;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;

public class ExploreIntents {
    public static Intent forType(Context context, RecommendationItem recommendationItem, C2731SearchContext searchContext, C2443MtPdpReferrer referrer, TopLevelSearchParams searchParams, ForYouMetaData forYouMetaData) {
        ParcelableStringMap queryParams = recommendationItem.getQueryParam() != null ? recommendationItem.getQueryParam() : new ParcelableStringMap();
        switch (recommendationItem.getItemType()) {
            case Experience:
                return ReactNativeIntents.intentForExperiencePDP(context, Integer.parseInt((String) queryParams.get("product_type")) == C6213ProductType.IMMERSION.f8481id, recommendationItem.getId(), (String) queryParams.get("poster_url"), searchParams, searchContext, referrer, recommendationItem.getId());
            case InsiderGuidebook:
                return ReactNativeIntents.intentForGuidebookInsider(context, recommendationItem.getId(), (String) queryParams.get("hero_photo_url"), (String) queryParams.get("bold_subtitle"), searchContext);
            case Detour:
                return ReactNativeIntents.intentForGuidebookDetour(context, String.valueOf(recommendationItem.getId()), (String) queryParams.get("bold_subtitle"), searchContext);
            case GuidebookActivity:
                return PlacesIntents.intentForPlaceActivityPDP(context, recommendationItem.getId(), forYouMetaData, searchContext);
            case Meetup:
                return PlacesIntents.intentForMeetupPDP(context, recommendationItem.getId());
            case Place:
                return ReactNativeIntents.intentForPlaceP3(context, Long.valueOf(Long.valueOf((String) queryParams.get("place_id")).longValue()), (String) queryParams.get("title"), searchContext);
            case WebLinkContent:
                return new Intent("android.intent.action.VIEW", Uri.parse((String) recommendationItem.getQueryParam().get("web_link_url")));
            case Home:
                return P3ActivityIntents.withListingId(context, recommendationItem.getId());
            default:
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Unknown type: " + recommendationItem.getItemType()));
                return null;
        }
    }

    public static Intent forType(Context context, RecommendationItem recommendationItem) {
        return forType(context, recommendationItem, null, C2443MtPdpReferrer.Unknown, null, null);
    }
}
