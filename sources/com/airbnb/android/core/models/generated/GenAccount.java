package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAccount implements Parcelable {
    @JsonProperty("account_exists")
    protected boolean mAccountExists;
    @JsonProperty("account_type")
    protected String mAccountType;
    @JsonProperty("currency")
    protected String mCurrency;
    @JsonProperty("first_name")
    protected String mFirstName;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("is_complete")
    protected boolean mIsComplete;
    @JsonProperty("is_vr_platform_powered_host")
    protected boolean mIsVrPlatformPoweredHost;
    @JsonProperty("locale")
    protected String mLocale;
    @JsonProperty("photo_experiment")
    protected boolean mPhotoExperiment;
    @JsonProperty("picture_url")
    protected String mPictureUrl;
    @JsonProperty("required_steps")
    protected String[] mRequiredSteps;
    @JsonProperty("user")
    protected User mUser;

    protected GenAccount(String currency, String locale, String accountType, String firstName, String pictureUrl, String[] requiredSteps, User user, boolean photoExperiment, boolean isVrPlatformPoweredHost, boolean isComplete, boolean accountExists, int id) {
        this();
        this.mCurrency = currency;
        this.mLocale = locale;
        this.mAccountType = accountType;
        this.mFirstName = firstName;
        this.mPictureUrl = pictureUrl;
        this.mRequiredSteps = requiredSteps;
        this.mUser = user;
        this.mPhotoExperiment = photoExperiment;
        this.mIsVrPlatformPoweredHost = isVrPlatformPoweredHost;
        this.mIsComplete = isComplete;
        this.mAccountExists = accountExists;
        this.mId = id;
    }

    protected GenAccount() {
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    @JsonProperty("currency")
    public void setCurrency(String value) {
        this.mCurrency = value;
    }

    public String getLocale() {
        return this.mLocale;
    }

    @JsonProperty("locale")
    public void setLocale(String value) {
        this.mLocale = value;
    }

    public String getAccountType() {
        return this.mAccountType;
    }

    @JsonProperty("account_type")
    public void setAccountType(String value) {
        this.mAccountType = value;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String value) {
        this.mFirstName = value;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    @JsonProperty("picture_url")
    public void setPictureUrl(String value) {
        this.mPictureUrl = value;
    }

    public String[] getRequiredSteps() {
        return this.mRequiredSteps;
    }

    @JsonProperty("required_steps")
    public void setRequiredSteps(String[] value) {
        this.mRequiredSteps = value;
    }

    public User getUser() {
        return this.mUser;
    }

    @JsonProperty("user")
    public void setUser(User value) {
        this.mUser = value;
    }

    public boolean isPhotoExperiment() {
        return this.mPhotoExperiment;
    }

    @JsonProperty("photo_experiment")
    public void setPhotoExperiment(boolean value) {
        this.mPhotoExperiment = value;
    }

    public boolean isVrPlatformPoweredHost() {
        return this.mIsVrPlatformPoweredHost;
    }

    @JsonProperty("is_vr_platform_powered_host")
    public void setIsVrPlatformPoweredHost(boolean value) {
        this.mIsVrPlatformPoweredHost = value;
    }

    public boolean isComplete() {
        return this.mIsComplete;
    }

    @JsonProperty("is_complete")
    public void setIsComplete(boolean value) {
        this.mIsComplete = value;
    }

    public boolean isAccountExists() {
        return this.mAccountExists;
    }

    @JsonProperty("account_exists")
    public void setAccountExists(boolean value) {
        this.mAccountExists = value;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCurrency);
        parcel.writeString(this.mLocale);
        parcel.writeString(this.mAccountType);
        parcel.writeString(this.mFirstName);
        parcel.writeString(this.mPictureUrl);
        parcel.writeStringArray(this.mRequiredSteps);
        parcel.writeParcelable(this.mUser, 0);
        parcel.writeBooleanArray(new boolean[]{this.mPhotoExperiment, this.mIsVrPlatformPoweredHost, this.mIsComplete, this.mAccountExists});
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCurrency = source.readString();
        this.mLocale = source.readString();
        this.mAccountType = source.readString();
        this.mFirstName = source.readString();
        this.mPictureUrl = source.readString();
        this.mRequiredSteps = source.createStringArray();
        this.mUser = (User) source.readParcelable(User.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mPhotoExperiment = bools[0];
        this.mIsVrPlatformPoweredHost = bools[1];
        this.mIsComplete = bools[2];
        this.mAccountExists = bools[3];
        this.mId = source.readInt();
    }
}
