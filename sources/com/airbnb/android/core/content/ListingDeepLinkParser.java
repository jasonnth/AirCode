package com.airbnb.android.core.content;

import android.content.Intent;
import android.text.TextUtils;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.GeneralAnalytics;
import com.airbnb.android.utils.NumberUtils;
import com.airbnb.android.utils.content.DeepLinkParser;
import java.util.List;

public class ListingDeepLinkParser extends DeepLinkParser {
    private static final long INVALID_ID = -1;

    public ListingDeepLinkParser(Intent intent) {
        super(intent);
    }

    public boolean isValid() {
        return getListingId() != -1;
    }

    public long getListingId() {
        List<String> pathSegments = getPathSegments();
        if (pathSegments == null || pathSegments.isEmpty()) {
            return -1;
        }
        if (pathSegments.size() > 1 && ("rooms".equals(pathSegments.get(0)) || "listings".equals(pathSegments.get(0)))) {
            AirbnbEventLogger.track(GeneralAnalytics.DeepLinking, GeneralAnalytics.AppOpen, "listing_details_intent");
            return NumberUtils.tryParseLong((String) pathSegments.get(1), -1);
        } else if ("rooms".equals(getHost())) {
            AirbnbEventLogger.track(GeneralAnalytics.DeepLinking, GeneralAnalytics.AppOpen, "listing_details_intent");
            return NumberUtils.tryParseLong((String) pathSegments.get(0), -1);
        } else if (!"listing".equals(pathSegments.get(0)) || TextUtils.isEmpty(getQueryParameter("id"))) {
            return -1;
        } else {
            return NumberUtils.tryParseLong(getQueryParameter("id"), -1);
        }
    }
}
