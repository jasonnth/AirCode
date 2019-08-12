package com.facebook.accountkit.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

final class AccessTokenCache {
    private static final String ACCESS_TOKEN_ACCOUNT_ID_KEY = "account_id";
    private static final String ACCESS_TOKEN_APPLICATION_ID_KEY = "application_id";
    private static final String ACCESS_TOKEN_LAST_REFRESH_KEY = "last_refresh";
    private static final String ACCESS_TOKEN_REFRESH_INTERVAL_KEY = "tokenRefreshIntervalInSeconds";
    private static final String ACCESS_TOKEN_TOKEN_KEY = "token";
    private static final String ACCESS_TOKEN_VERSION_KEY = "version";
    private static final int ACCESS_TOKEN_VERSION_VALUE = 1;
    static final String CACHED_ACCESS_TOKEN_KEY = "com.facebook.accountkit.AccessTokenManager.CachedAccessToken";
    private final SharedPreferences sharedPreferences;

    AccessTokenCache(SharedPreferences sharedPreferences2) {
        this.sharedPreferences = sharedPreferences2;
    }

    AccessTokenCache(Context applicationContext) {
        this(applicationContext.getSharedPreferences("com.facebook.accountkit.AccessTokenManager.SharedPreferences", 0));
    }

    public void clear() {
        this.sharedPreferences.edit().remove(CACHED_ACCESS_TOKEN_KEY).apply();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.String, com.facebook.accountkit.AccessToken] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [java.lang.String, com.facebook.accountkit.AccessToken]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [java.lang.String, com.facebook.accountkit.AccessToken]
      mth insns count: 11
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.accountkit.AccessToken load() {
        /*
            r5 = this;
            r2 = 0
            android.content.SharedPreferences r3 = r5.sharedPreferences
            java.lang.String r4 = "com.facebook.accountkit.AccessTokenManager.CachedAccessToken"
            java.lang.String r1 = r3.getString(r4, r2)
            if (r1 == 0) goto L_0x0015
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0016 }
            r3.<init>(r1)     // Catch:{ JSONException -> 0x0016 }
            com.facebook.accountkit.AccessToken r2 = deserializeAccessToken(r3)     // Catch:{ JSONException -> 0x0016 }
        L_0x0015:
            return r2
        L_0x0016:
            r0 = move-exception
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AccessTokenCache.load():com.facebook.accountkit.AccessToken");
    }

    public void save(AccessToken accessToken) {
        try {
            this.sharedPreferences.edit().putString(CACHED_ACCESS_TOKEN_KEY, serializeAccessToken(accessToken).toString()).apply();
        } catch (JSONException e) {
        }
    }

    static AccessToken deserializeAccessToken(JSONObject jsonObject) throws JSONException {
        if (jsonObject.getInt("version") <= 1) {
            return new AccessToken(jsonObject.getString("token"), jsonObject.getString(ACCESS_TOKEN_ACCOUNT_ID_KEY), jsonObject.getString(ACCESS_TOKEN_APPLICATION_ID_KEY), jsonObject.getLong(ACCESS_TOKEN_REFRESH_INTERVAL_KEY), new Date(jsonObject.getLong(ACCESS_TOKEN_LAST_REFRESH_KEY)));
        }
        throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.INVALID_ACCESS_TOKEN_FORMAT);
    }

    static JSONObject serializeAccessToken(AccessToken accessToken) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", 1);
        jsonObject.put(ACCESS_TOKEN_ACCOUNT_ID_KEY, accessToken.getAccountId());
        jsonObject.put(ACCESS_TOKEN_APPLICATION_ID_KEY, accessToken.getApplicationId());
        jsonObject.put(ACCESS_TOKEN_REFRESH_INTERVAL_KEY, accessToken.getTokenRefreshIntervalSeconds());
        jsonObject.put(ACCESS_TOKEN_LAST_REFRESH_KEY, accessToken.getLastRefresh().getTime());
        jsonObject.put("token", accessToken.getToken());
        return jsonObject;
    }
}
