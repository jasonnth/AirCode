package com.airbnb.android.p011p3;

/* renamed from: com.airbnb.android.p3.DataLoadedStatus */
public class DataLoadedStatus {
    private Boolean loadedFromCache;

    public void fromCache(boolean fromCache) {
        this.loadedFromCache = Boolean.valueOf(fromCache);
    }

    public boolean isLoaded() {
        return this.loadedFromCache != null;
    }

    public boolean fromCache() {
        return isLoaded() && this.loadedFromCache.booleanValue();
    }

    public boolean fromNetwork() {
        return isLoaded() && !this.loadedFromCache.booleanValue();
    }
}
