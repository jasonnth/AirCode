package com.facebook.accountkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.internal.Utility;
import java.util.Date;

public final class AccessToken implements Parcelable {
    public static final Creator<AccessToken> CREATOR = new Creator<AccessToken>() {
        public AccessToken createFromParcel(Parcel source) {
            return new AccessToken(source);
        }

        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }
    };
    private static final long DEFAULT_TOKEN_REFRESH_INTERVAL = 604800;
    private static final int PARCEL_VERSION = 2;
    private final String accountId;
    private final String applicationId;
    private final Date lastRefresh;
    private final String token;
    private final long tokenRefreshIntervalInSeconds;

    public AccessToken(String token2, String accountId2, String applicationId2, long tokenRefreshIntervalInSeconds2, Date lastRefreshTime) {
        this.token = token2;
        this.accountId = accountId2;
        this.applicationId = applicationId2;
        this.tokenRefreshIntervalInSeconds = tokenRefreshIntervalInSeconds2;
        if (lastRefreshTime == null) {
            lastRefreshTime = new Date();
        }
        this.lastRefresh = lastRefreshTime;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public Date getLastRefresh() {
        return this.lastRefresh;
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenRefreshIntervalSeconds() {
        return this.tokenRefreshIntervalInSeconds;
    }

    public String toString() {
        return "{AccessToken token:" + tokenToString() + " accountId:" + this.accountId + "}";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccessToken)) {
            return false;
        }
        AccessToken o = (AccessToken) other;
        if (this.tokenRefreshIntervalInSeconds != o.tokenRefreshIntervalInSeconds || !Utility.areObjectsEqual(this.accountId, o.accountId) || !Utility.areObjectsEqual(this.applicationId, o.applicationId) || !Utility.areObjectsEqual(this.lastRefresh, o.lastRefresh) || !Utility.areObjectsEqual(this.token, o.token)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((Utility.getHashCode(this.accountId) + 527) * 31) + Utility.getHashCode(this.applicationId)) * 31) + Utility.getHashCode(this.lastRefresh)) * 31) + Utility.getHashCode(this.token)) * 31) + Utility.getHashCode(Long.valueOf(this.tokenRefreshIntervalInSeconds));
    }

    private String tokenToString() {
        if (this.token == null) {
            return "null";
        }
        if (AccountKit.getLoggingBehaviors().isEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
            return this.token;
        }
        return "ACCESS_TOKEN_REMOVED";
    }

    private AccessToken(Parcel parcel) {
        String toSetToken;
        int version = 1;
        try {
            version = parcel.readInt();
        } catch (ClassCastException e) {
        }
        try {
            toSetToken = parcel.readString();
        } catch (ClassCastException e2) {
            parcel.readLong();
            toSetToken = parcel.readString();
        }
        this.token = toSetToken;
        this.accountId = parcel.readString();
        this.lastRefresh = new Date(parcel.readLong());
        this.applicationId = parcel.readString();
        if (version == 2) {
            this.tokenRefreshIntervalInSeconds = parcel.readLong();
        } else {
            this.tokenRefreshIntervalInSeconds = 604800;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(2);
        dest.writeString(this.token);
        dest.writeString(this.accountId);
        dest.writeLong(this.lastRefresh.getTime());
        dest.writeString(this.applicationId);
        dest.writeLong(this.tokenRefreshIntervalInSeconds);
    }
}
