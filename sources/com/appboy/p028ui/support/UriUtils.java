package com.appboy.p028ui.support;

import android.net.Uri;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.appboy.ui.support.UriUtils */
public class UriUtils {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, UriUtils.class.getName()});

    public static Map<String, String> getQueryParameters(Uri uri) {
        int end;
        if (uri.isOpaque()) {
            AppboyLogger.m1733d(TAG, "URI is not hierarchical. There are no query parameters to parse.");
            return Collections.emptyMap();
        }
        String query = uri.getEncodedQuery();
        if (query == null) {
            return Collections.emptyMap();
        }
        Map<String, String> parameters = new HashMap<>();
        int start = 0;
        do {
            int next = query.indexOf(38, start);
            if (next == -1) {
                end = query.length();
            } else {
                end = next;
            }
            int separator = query.indexOf(61, start);
            if (separator > end || separator == -1) {
                separator = end;
            }
            if (end > start) {
                parameters.put(Uri.decode(query.substring(start, separator)), Uri.decode(query.substring(separator + 1, end)));
            }
            start = end + 1;
        } while (start < query.length());
        return Collections.unmodifiableMap(parameters);
    }
}
