package com.airbnb.android.utils.content;

import android.content.Intent;
import android.net.Uri;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DeepLinkParser {
    private final Intent mIntent;
    protected final Map<String, String> mQueryParameters = new HashMap();

    public abstract boolean isValid();

    public DeepLinkParser(Intent intent) {
        this.mIntent = intent;
        Uri intentData = this.mIntent.getData();
        if (intentData != null) {
            for (String parameterName : intentData.getQueryParameterNames()) {
                this.mQueryParameters.put(parameterName, intentData.getQueryParameter(parameterName));
            }
        }
    }

    public List<String> getPathSegments() {
        if (this.mIntent.getData() != null) {
            return this.mIntent.getData().getPathSegments();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String getHost() {
        if (this.mIntent.getData() != null) {
            return this.mIntent.getData().getHost();
        }
        return null;
    }

    public String getQueryParameter(String key) {
        return (String) this.mQueryParameters.get(key);
    }

    public boolean hasPath(String path) {
        if (getPathSegments() == null) {
            return false;
        }
        String[] segments = path.split("/");
        if (segments.length != getPathSegments().size()) {
            return false;
        }
        for (int i = 0; i < segments.length; i++) {
            if (!((String) getPathSegments().get(i)).equals(segments[i])) {
                return false;
            }
        }
        return true;
    }
}
