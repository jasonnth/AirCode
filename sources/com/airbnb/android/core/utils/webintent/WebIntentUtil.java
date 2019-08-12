package com.airbnb.android.core.utils.webintent;

import android.net.Uri;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.models.GuestDetails;
import java.util.List;

public final class WebIntentUtil {
    private WebIntentUtil() {
    }

    public static Long getIdFromPath(Uri uri, int position) {
        List<String> segments = uri.getPathSegments();
        if (segments == null || position >= segments.size() || position < 0) {
            return null;
        }
        return parseStringAsLong((String) segments.get(position));
    }

    public static Long getIdFromQueryParams(Uri uri, String queryParam) {
        return parseStringAsLong(uri.getQueryParameter(queryParam));
    }

    public static Integer parseQueryParamAsInt(Uri uri, String param) {
        Integer num = null;
        String value = uri.getQueryParameter(param);
        if (value == null) {
            return num;
        }
        try {
            return Integer.valueOf(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return num;
        }
    }

    public static AirDate parseQueryParamAsDate(Uri uri, String param) {
        String value = uri.getQueryParameter(param);
        if (value == null) {
            return null;
        }
        try {
            return new AirDate(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static AirDate parseQueryParamAsDate(Uri uri, String... params) {
        for (String param : params) {
            AirDate airDate = parseQueryParamAsDate(uri, param);
            if (airDate != null) {
                return airDate;
            }
        }
        return null;
    }

    private static Long parseStringAsLong(String segment) {
        Long l = null;
        if (TextUtils.isEmpty(segment)) {
            return l;
        }
        try {
            return Long.valueOf(Long.parseLong(segment));
        } catch (NumberFormatException e) {
            return l;
        }
    }

    public static GuestDetails getGuestDetailsFromUriParams(Uri uri) {
        int i = 0;
        Integer guests = parseQueryParamAsInt(uri, FindTweenAnalytics.GUESTS);
        Integer adults = parseQueryParamAsInt(uri, FindTweenAnalytics.ADULTS);
        Integer children = parseQueryParamAsInt(uri, FindTweenAnalytics.CHILDREN);
        Integer infants = parseQueryParamAsInt(uri, FindTweenAnalytics.INFANTS);
        GuestDetails guestDetails = new GuestDetails();
        if (adults != null) {
            GuestDetails childrenCount = guestDetails.adultsCount(adults.intValue()).childrenCount(children != null ? children.intValue() : 0);
            if (infants != null) {
                i = infants.intValue();
            }
            childrenCount.infantsCount(i);
        } else if (guests != null) {
            guestDetails.adultsCount(guests.intValue());
        }
        return guestDetails;
    }
}
