package com.airbnb.android.lib.content;

import android.content.Intent;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.utils.content.DeepLinkParser;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import p005cn.jpush.android.JPushConstants;

public class SearchDeepLinkParser extends DeepLinkParser {
    private static final String KEY_QUERY = "query";
    private static final String LEGACY_SEARCH_IDENTIFIER = "s";
    private static final String SEARCH_IDENTIFIER = "search";
    private static final String TAB_ALL = "all";
    private static final String TAB_EXPERIENCES = "experiences";
    private static final String TAB_HOMES = "homes";
    private static final String TAB_PLACES = "places";

    public SearchDeepLinkParser(Intent intent) {
        super(intent);
    }

    public String getSearchQuery() {
        return cleanupSearchQuery(parseSearchQuery());
    }

    public Tab getTab() {
        List<String> pathSegments = getPathSegments();
        if (pathSegments == null || pathSegments.isEmpty()) {
            return null;
        }
        return parseTab((String) pathSegments.get(pathSegments.size() - 1));
    }

    public boolean isValid() {
        return (parseSearchQuery() == null && getTab() == null) ? false : true;
    }

    public static String cleanupSearchQuery(String query) {
        if (query == null) {
            return null;
        }
        String query2 = query.replace("--", ", ").replace('-', ' ').replace('~', '-');
        try {
            return URLDecoder.decode(query2, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            BugsnagWrapper.notify((Throwable) e);
            return query2;
        }
    }

    private String parseSearchQuery() {
        List<String> pathSegments = getPathSegments();
        if (pathSegments == null || pathSegments.isEmpty()) {
            return null;
        }
        String path = (String) pathSegments.get(0);
        String query = null;
        if (LEGACY_SEARCH_IDENTIFIER.equals(getHost())) {
            query = path;
        } else if (LEGACY_SEARCH_IDENTIFIER.equals(path) || "search".equals(path)) {
            if (this.mQueryParameters.containsKey(KEY_QUERY)) {
                query = (String) this.mQueryParameters.get(KEY_QUERY);
            } else if (pathSegments.size() > 1) {
                query = (String) pathSegments.get(1);
            }
        }
        if (parseTab(query) != null) {
            return null;
        }
        return query;
    }

    private Tab parseTab(String tabId) {
        if (TAB_ALL.equalsIgnoreCase(tabId)) {
            return Tab.ALL;
        }
        if (TAB_HOMES.equalsIgnoreCase(tabId)) {
            return Tab.HOME;
        }
        if (TAB_EXPERIENCES.equalsIgnoreCase(tabId)) {
            return Tab.EXPERIENCE;
        }
        if (TAB_PLACES.equalsIgnoreCase(tabId)) {
            return Tab.PLACES;
        }
        return null;
    }
}
