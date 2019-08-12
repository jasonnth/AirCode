package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenLogin implements Parcelable {
    @JsonProperty("account")
    protected Account mAccount;
    @JsonProperty("expires_at")
    protected AirDateTime mExpiresAt;
    @JsonProperty("id")
    protected String mId;
    @JsonProperty("refresh_token")
    protected String mRefreshToken;
    @JsonProperty("valid")
    protected boolean mValid;

    protected GenLogin(Account account, AirDateTime expiresAt, String id, String refreshToken, boolean valid) {
        this();
        this.mAccount = account;
        this.mExpiresAt = expiresAt;
        this.mId = id;
        this.mRefreshToken = refreshToken;
        this.mValid = valid;
    }

    protected GenLogin() {
    }

    public Account getAccount() {
        return this.mAccount;
    }

    @JsonProperty("account")
    public void setAccount(Account value) {
        this.mAccount = value;
    }

    public AirDateTime getExpiresAt() {
        return this.mExpiresAt;
    }

    @JsonProperty("expires_at")
    public void setExpiresAt(AirDateTime value) {
        this.mExpiresAt = value;
    }

    public String getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(String value) {
        this.mId = value;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    @JsonProperty("refresh_token")
    public void setRefreshToken(String value) {
        this.mRefreshToken = value;
    }

    public boolean isValid() {
        return this.mValid;
    }

    @JsonProperty("valid")
    public void setValid(boolean value) {
        this.mValid = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mAccount, 0);
        parcel.writeParcelable(this.mExpiresAt, 0);
        parcel.writeString(this.mId);
        parcel.writeString(this.mRefreshToken);
        parcel.writeBooleanArray(new boolean[]{this.mValid});
    }

    public void readFromParcel(Parcel source) {
        this.mAccount = (Account) source.readParcelable(Account.class.getClassLoader());
        this.mExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mId = source.readString();
        this.mRefreshToken = source.readString();
        this.mValid = source.createBooleanArray()[0];
    }
}
