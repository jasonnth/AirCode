package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.content.LocalBroadcastManager;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccessTokenTracker;

final class AccessTokenManager {
    static final String SHARED_PREFERENCES_NAME = "com.facebook.accountkit.AccessTokenManager.SharedPreferences";
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private final LocalBroadcastManager localBroadcastManager;

    AccessTokenManager(Context applicationContext, LocalBroadcastManager localBroadcastManager2) {
        this(new AccessTokenCache(applicationContext), localBroadcastManager2);
    }

    AccessTokenManager(AccessTokenCache accessTokenCache2, LocalBroadcastManager localBroadcastManager2) {
        this.accessTokenCache = accessTokenCache2;
        this.localBroadcastManager = localBroadcastManager2;
    }

    /* access modifiers changed from: 0000 */
    public AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    /* access modifiers changed from: 0000 */
    public boolean loadCurrentAccessToken() {
        AccessToken accessToken = this.accessTokenCache.load();
        if (accessToken == null) {
            return false;
        }
        setCurrentAccessToken(accessToken, false);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentAccessToken(AccessToken currentAccessToken2) {
        setCurrentAccessToken(currentAccessToken2, true);
    }

    /* access modifiers changed from: 0000 */
    public void refreshCurrentAccessToken(AccessToken currentAccessToken2) {
        setCurrentAccessToken(new AccessToken(currentAccessToken2.getToken(), currentAccessToken2.getAccountId(), currentAccessToken2.getApplicationId(), currentAccessToken2.getTokenRefreshIntervalSeconds(), null));
    }

    private void setCurrentAccessToken(AccessToken currentAccessToken2, boolean saveToCache) {
        AccessToken oldAccessToken = this.currentAccessToken;
        this.currentAccessToken = currentAccessToken2;
        if (saveToCache) {
            if (currentAccessToken2 != null) {
                this.accessTokenCache.save(currentAccessToken2);
            } else {
                this.accessTokenCache.clear();
            }
        }
        if (!Utility.areObjectsEqual(oldAccessToken, currentAccessToken2)) {
            sendCurrentAccessTokenChangedBroadcast(oldAccessToken, currentAccessToken2);
        }
    }

    private void sendCurrentAccessTokenChangedBroadcast(AccessToken oldAccessToken, AccessToken currentAccessToken2) {
        Intent intent = new Intent(AccessTokenTracker.ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(AccessTokenTracker.EXTRA_OLD_ACCESS_TOKEN, oldAccessToken);
        intent.putExtra(AccessTokenTracker.EXTRA_NEW_ACCESS_TOKEN, currentAccessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }
}
