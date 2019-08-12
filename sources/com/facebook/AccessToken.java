package com.facebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.internal.Utility;
import com.facebook.internal.Utility.GraphMeRequestWithCacheCallback;
import com.facebook.internal.Validate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String APPLICATION_ID_KEY = "application_id";
    public static final Creator<AccessToken> CREATOR = new Creator() {
        public AccessToken createFromParcel(Parcel source) {
            return new AccessToken(source);
        }

        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }
    };
    private static final int CURRENT_JSON_FORMAT = 1;
    private static final String DECLINED_PERMISSIONS_KEY = "declined_permissions";
    private static final AccessTokenSource DEFAULT_ACCESS_TOKEN_SOURCE = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    private static final Date DEFAULT_EXPIRATION_TIME = MAX_DATE;
    private static final Date DEFAULT_LAST_REFRESH_TIME = new Date();
    private static final String EXPIRES_AT_KEY = "expires_at";
    public static final String EXPIRES_IN_KEY = "expires_in";
    private static final String LAST_REFRESH_KEY = "last_refresh";
    private static final Date MAX_DATE = new Date(Long.MAX_VALUE);
    private static final String PERMISSIONS_KEY = "permissions";
    private static final String SOURCE_KEY = "source";
    private static final String TOKEN_KEY = "token";
    public static final String USER_ID_KEY = "user_id";
    private static final String VERSION_KEY = "version";
    private final String applicationId;
    private final Set<String> declinedPermissions;
    private final Date expires;
    private final Date lastRefresh;

    /* renamed from: permissions reason: collision with root package name */
    private final Set<String> f11342permissions;
    private final AccessTokenSource source;
    private final String token;
    private final String userId;

    public interface AccessTokenCreationCallback {
        void onError(FacebookException facebookException);

        void onSuccess(AccessToken accessToken);
    }

    public interface AccessTokenRefreshCallback {
        void OnTokenRefreshFailed(FacebookException facebookException);

        void OnTokenRefreshed(AccessToken accessToken);
    }

    public AccessToken(String accessToken, String applicationId2, String userId2, Collection<String> permissions2, Collection<String> declinedPermissions2, AccessTokenSource accessTokenSource, Date expirationTime, Date lastRefreshTime) {
        Validate.notNullOrEmpty(accessToken, "accessToken");
        Validate.notNullOrEmpty(applicationId2, "applicationId");
        Validate.notNullOrEmpty(userId2, "userId");
        if (expirationTime == null) {
            expirationTime = DEFAULT_EXPIRATION_TIME;
        }
        this.expires = expirationTime;
        this.f11342permissions = Collections.unmodifiableSet(permissions2 != null ? new HashSet(permissions2) : new HashSet());
        this.declinedPermissions = Collections.unmodifiableSet(declinedPermissions2 != null ? new HashSet(declinedPermissions2) : new HashSet());
        this.token = accessToken;
        if (accessTokenSource == null) {
            accessTokenSource = DEFAULT_ACCESS_TOKEN_SOURCE;
        }
        this.source = accessTokenSource;
        if (lastRefreshTime == null) {
            lastRefreshTime = DEFAULT_LAST_REFRESH_TIME;
        }
        this.lastRefresh = lastRefreshTime;
        this.applicationId = applicationId2;
        this.userId = userId2;
    }

    public static AccessToken getCurrentAccessToken() {
        return AccessTokenManager.getInstance().getCurrentAccessToken();
    }

    public static void setCurrentAccessToken(AccessToken accessToken) {
        AccessTokenManager.getInstance().setCurrentAccessToken(accessToken);
    }

    public static void refreshCurrentAccessTokenAsync() {
        AccessTokenManager.getInstance().refreshCurrentAccessToken(null);
    }

    public static void refreshCurrentAccessTokenAsync(AccessTokenRefreshCallback callback) {
        AccessTokenManager.getInstance().refreshCurrentAccessToken(callback);
    }

    public String getToken() {
        return this.token;
    }

    public Date getExpires() {
        return this.expires;
    }

    public Set<String> getPermissions() {
        return this.f11342permissions;
    }

    public Set<String> getDeclinedPermissions() {
        return this.declinedPermissions;
    }

    public AccessTokenSource getSource() {
        return this.source;
    }

    public Date getLastRefresh() {
        return this.lastRefresh;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getUserId() {
        return this.userId;
    }

    public static void createFromNativeLinkingIntent(Intent intent, final String applicationId2, final AccessTokenCreationCallback accessTokenCallback) {
        Validate.notNull(intent, "intent");
        if (intent.getExtras() == null) {
            accessTokenCallback.onError(new FacebookException("No extras found on intent"));
            return;
        }
        final Bundle extras = new Bundle(intent.getExtras());
        String accessToken = extras.getString("access_token");
        if (accessToken == null || accessToken.isEmpty()) {
            accessTokenCallback.onError(new FacebookException("No access token found on intent"));
            return;
        }
        String userId2 = extras.getString("user_id");
        if (userId2 == null || userId2.isEmpty()) {
            Utility.getGraphMeRequestWithCacheAsync(accessToken, new GraphMeRequestWithCacheCallback() {
                public void onSuccess(JSONObject userInfo) {
                    try {
                        extras.putString("user_id", userInfo.getString("id"));
                        accessTokenCallback.onSuccess(AccessToken.createFromBundle(null, extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), applicationId2));
                    } catch (JSONException e) {
                        accessTokenCallback.onError(new FacebookException("Unable to generate access token due to missing user id"));
                    }
                }

                public void onFailure(FacebookException error) {
                    accessTokenCallback.onError(error);
                }
            });
        } else {
            accessTokenCallback.onSuccess(createFromBundle(null, extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), applicationId2));
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{AccessToken");
        builder.append(" token:").append(tokenToString());
        appendPermissions(builder);
        builder.append("}");
        return builder.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccessToken)) {
            return false;
        }
        AccessToken o = (AccessToken) other;
        if (!this.expires.equals(o.expires) || !this.f11342permissions.equals(o.f11342permissions) || !this.declinedPermissions.equals(o.declinedPermissions) || !this.token.equals(o.token) || this.source != o.source || !this.lastRefresh.equals(o.lastRefresh) || (this.applicationId != null ? !this.applicationId.equals(o.applicationId) : o.applicationId != null) || !this.userId.equals(o.userId)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((this.expires.hashCode() + 527) * 31) + this.f11342permissions.hashCode()) * 31) + this.declinedPermissions.hashCode()) * 31) + this.token.hashCode()) * 31) + this.source.hashCode()) * 31) + this.lastRefresh.hashCode()) * 31) + (this.applicationId == null ? 0 : this.applicationId.hashCode())) * 31) + this.userId.hashCode();
    }

    @SuppressLint({"FieldGetter"})
    static AccessToken createFromRefresh(AccessToken current, Bundle bundle) {
        if (current.source == AccessTokenSource.FACEBOOK_APPLICATION_WEB || current.source == AccessTokenSource.FACEBOOK_APPLICATION_NATIVE || current.source == AccessTokenSource.FACEBOOK_APPLICATION_SERVICE) {
            Date expires2 = Utility.getBundleLongAsDate(bundle, EXPIRES_IN_KEY, new Date(0));
            String token2 = bundle.getString("access_token");
            if (Utility.isNullOrEmpty(token2)) {
                return null;
            }
            return new AccessToken(token2, current.applicationId, current.getUserId(), current.getPermissions(), current.getDeclinedPermissions(), current.source, expires2, new Date());
        }
        throw new FacebookException("Invalid token source: " + current.source);
    }

    static AccessToken createFromLegacyCache(Bundle bundle) {
        List<String> permissions2 = getPermissionsFromBundle(bundle, LegacyTokenHelper.PERMISSIONS_KEY);
        List<String> declinedPermissions2 = getPermissionsFromBundle(bundle, LegacyTokenHelper.DECLINED_PERMISSIONS_KEY);
        String applicationId2 = LegacyTokenHelper.getApplicationId(bundle);
        if (Utility.isNullOrEmpty(applicationId2)) {
            applicationId2 = FacebookSdk.getApplicationId();
        }
        String tokenString = LegacyTokenHelper.getToken(bundle);
        try {
            return new AccessToken(tokenString, applicationId2, Utility.awaitGetGraphMeRequestWithCache(tokenString).getString("id"), permissions2, declinedPermissions2, LegacyTokenHelper.getSource(bundle), LegacyTokenHelper.getDate(bundle, LegacyTokenHelper.EXPIRATION_DATE_KEY), LegacyTokenHelper.getDate(bundle, LegacyTokenHelper.LAST_REFRESH_DATE_KEY));
        } catch (JSONException e) {
            return null;
        }
    }

    static List<String> getPermissionsFromBundle(Bundle bundle, String key) {
        List<String> originalPermissions = bundle.getStringArrayList(key);
        if (originalPermissions == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(originalPermissions));
    }

    public boolean isExpired() {
        return new Date().after(this.expires);
    }

    /* access modifiers changed from: 0000 */
    public JSONObject toJSONObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("version", 1);
        jsonObject.put("token", this.token);
        jsonObject.put("expires_at", this.expires.getTime());
        jsonObject.put("permissions", new JSONArray(this.f11342permissions));
        jsonObject.put(DECLINED_PERMISSIONS_KEY, new JSONArray(this.declinedPermissions));
        jsonObject.put(LAST_REFRESH_KEY, this.lastRefresh.getTime());
        jsonObject.put("source", this.source.name());
        jsonObject.put(APPLICATION_ID_KEY, this.applicationId);
        jsonObject.put("user_id", this.userId);
        return jsonObject;
    }

    static AccessToken createFromJSONObject(JSONObject jsonObject) throws JSONException {
        if (jsonObject.getInt("version") > 1) {
            throw new FacebookException("Unknown AccessToken serialization format.");
        }
        String token2 = jsonObject.getString("token");
        Date expiresAt = new Date(jsonObject.getLong("expires_at"));
        JSONArray permissionsArray = jsonObject.getJSONArray("permissions");
        JSONArray declinedPermissionsArray = jsonObject.getJSONArray(DECLINED_PERMISSIONS_KEY);
        Date lastRefresh2 = new Date(jsonObject.getLong(LAST_REFRESH_KEY));
        return new AccessToken(token2, jsonObject.getString(APPLICATION_ID_KEY), jsonObject.getString("user_id"), Utility.jsonArrayToStringList(permissionsArray), Utility.jsonArrayToStringList(declinedPermissionsArray), AccessTokenSource.valueOf(jsonObject.getString("source")), expiresAt, lastRefresh2);
    }

    /* access modifiers changed from: private */
    public static AccessToken createFromBundle(List<String> requestedPermissions, Bundle bundle, AccessTokenSource source2, Date expirationBase, String applicationId2) {
        String token2 = bundle.getString("access_token");
        Date expires2 = Utility.getBundleLongAsDate(bundle, EXPIRES_IN_KEY, expirationBase);
        String userId2 = bundle.getString("user_id");
        if (Utility.isNullOrEmpty(token2) || expires2 == null) {
            return null;
        }
        return new AccessToken(token2, applicationId2, userId2, requestedPermissions, null, source2, expires2, new Date());
    }

    private String tokenToString() {
        if (this.token == null) {
            return "null";
        }
        if (FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
            return this.token;
        }
        return "ACCESS_TOKEN_REMOVED";
    }

    private void appendPermissions(StringBuilder builder) {
        builder.append(" permissions:");
        if (this.f11342permissions == null) {
            builder.append("null");
            return;
        }
        builder.append("[");
        builder.append(TextUtils.join(", ", this.f11342permissions));
        builder.append("]");
    }

    AccessToken(Parcel parcel) {
        this.expires = new Date(parcel.readLong());
        ArrayList<String> permissionsList = new ArrayList<>();
        parcel.readStringList(permissionsList);
        this.f11342permissions = Collections.unmodifiableSet(new HashSet(permissionsList));
        permissionsList.clear();
        parcel.readStringList(permissionsList);
        this.declinedPermissions = Collections.unmodifiableSet(new HashSet(permissionsList));
        this.token = parcel.readString();
        this.source = AccessTokenSource.valueOf(parcel.readString());
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = parcel.readString();
        this.userId = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.expires.getTime());
        dest.writeStringList(new ArrayList(this.f11342permissions));
        dest.writeStringList(new ArrayList(this.declinedPermissions));
        dest.writeString(this.token);
        dest.writeString(this.source.name());
        dest.writeLong(this.lastRefresh.getTime());
        dest.writeString(this.applicationId);
        dest.writeString(this.userId);
    }
}
