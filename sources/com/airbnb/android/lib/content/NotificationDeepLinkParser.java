package com.airbnb.android.lib.content;

import android.content.Intent;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.airbnb.android.utils.content.DeepLinkParser;
import java.util.Arrays;
import java.util.List;

public class NotificationDeepLinkParser extends DeepLinkParser {
    private final String mDeepLinkUri;

    public NotificationDeepLinkParser(Intent intent) {
        super(intent);
        this.mDeepLinkUri = intent.getStringExtra(PushNotificationConstants.DEEP_LINK_KEY);
        if (isValid()) {
            String[] split = this.mDeepLinkUri.split("\\?");
            if (split.length > 1) {
                for (String queryParamPair : split[1].split("&")) {
                    String[] kv = queryParamPair.split("=");
                    this.mQueryParameters.put(kv[0], kv.length > 1 ? kv[1] : null);
                }
            }
        }
    }

    public boolean isValid() {
        return this.mDeepLinkUri != null;
    }

    public List<String> getPathSegments() {
        String[] split = this.mDeepLinkUri.split("\\?");
        if (split.length > 0) {
            return Arrays.asList(split[0].split("/"));
        }
        return null;
    }
}
