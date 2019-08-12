package com.airbnb.android.core.authentication;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.SwitchAccountAnalytics;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.SecurityUtil;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.utils.AirbnbConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AirbnbAccountManager {
    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String JSON_NEW_USER_KEY = "json_new_user";
    private static final String KEY_SAVED_USER_BIRTHDATE = "saved_dob";
    private static final String KEY_SAVED_USER_CREATED_AT = "saved_user_created_at";
    private static final String KEY_SAVED_USER_FIRST_NAME = "saved_first_name";
    private static final String KEY_SAVED_USER_HAS_PROFILE_PIC = "saved_has_profile_pic";
    private static final String KEY_SAVED_USER_ID = "saved_user_id";
    private static final String KEY_SAVED_USER_LAST_NAME = "saved_last_name";
    private static final String KEY_SAVED_USER_LISTINGS_COUNT = "saved_listings_count";
    private static final String KEY_SAVED_USER_MANUALLY_VERIFIED = "manually_verified";
    private static final String KEY_SAVED_USER_PICTURE_URL = "saved_picture_url";
    private static final String KEY_SAVED_USER_PICTURE_URL_LARGE = "saved_picture_url_large";
    private static final String KEY_SAVED_USER_TOTAL_LISTINGS_COUNT = "saved_total_listings_count";
    private static final String KEY_SAVED_USER_VERIFICATIONS = "verifications";
    private static final String USER_JSON_KEY = "user_json";
    private String mAccessToken;
    private User mCurrentUser;
    private boolean mIsVRPlatformPoweredHost;
    private final SharedPreferences mPreferences;
    private final SharedPrefsHelper mSharedPrefsHelper;
    private final AccountManager manager;

    public AirbnbAccountManager(AccountManager manager2, AirbnbPreferences preferences) {
        this.manager = manager2;
        this.mPreferences = preferences.getSharedPreferences();
        this.mSharedPrefsHelper = new SharedPrefsHelper(preferences);
    }

    public User getCurrentUser() {
        if (this.mCurrentUser == null && hasAccessToken()) {
            retrieveCurrentUser();
        }
        return this.mCurrentUser;
    }

    private void retrieveCurrentUser() {
        User user = new User();
        user.setId(this.mPreferences.getLong(KEY_SAVED_USER_ID, -1));
        if (user.getId() > 0) {
            user.setFirstName(this.mPreferences.getString(KEY_SAVED_USER_FIRST_NAME, ""));
            user.setLastName(this.mPreferences.getString(KEY_SAVED_USER_LAST_NAME, ""));
            user.setPictureUrl(this.mPreferences.getString(KEY_SAVED_USER_PICTURE_URL, null));
            user.setPictureUrlLarge(this.mPreferences.getString(KEY_SAVED_USER_PICTURE_URL_LARGE, null));
            user.setHasProfilePic(this.mPreferences.getBoolean(KEY_SAVED_USER_HAS_PROFILE_PIC, false));
            user.setTotalListingsCount(this.mPreferences.getInt(KEY_SAVED_USER_TOTAL_LISTINGS_COUNT, 0));
            user.setListingsCount(this.mPreferences.getInt(KEY_SAVED_USER_LISTINGS_COUNT, 0));
            user.setBirthdate(new AirDate(this.mPreferences.getLong(KEY_SAVED_USER_BIRTHDATE, 0)));
            user.setCreatedAt(Long.valueOf(this.mPreferences.getLong(KEY_SAVED_USER_CREATED_AT, 0)));
            user.setVerifications(new ArrayList(this.mPreferences.getStringSet("verifications", new HashSet())));
            user.setManuallyVerified(this.mPreferences.getBoolean(KEY_SAVED_USER_MANUALLY_VERIFIED, false));
            setCurrentUser(user);
            return;
        }
        BugsnagWrapper.setLoggedIn(false);
    }

    public void setCurrentUser(User currentUser) {
        this.mCurrentUser = currentUser;
        saveCurrentUserEmail(currentUser);
        BugsnagWrapper.setLoggedIn(currentUser != null);
    }

    private void saveCurrentUserEmail(User currentUser) {
        String currentUserEmail = currentUser.getEmailAddress();
        List<String> airbnbAccEmails = this.mSharedPrefsHelper.getListFromGlobalSharedPrefs(AirbnbConstants.PREFS_PREVIOUS_ACCOUNT_EMAILS);
        if (!airbnbAccEmails.contains(currentUserEmail)) {
            airbnbAccEmails.add(currentUserEmail);
            this.mSharedPrefsHelper.saveListToGlobalSharedPrefs(AirbnbConstants.PREFS_PREVIOUS_ACCOUNT_EMAILS, airbnbAccEmails);
        }
    }

    public void storeCurrentUser() {
        long j = -1;
        Editor putLong = this.mPreferences.edit().putLong(KEY_SAVED_USER_ID, this.mCurrentUser.getId()).putString(KEY_SAVED_USER_FIRST_NAME, this.mCurrentUser.getFirstName()).putString(KEY_SAVED_USER_LAST_NAME, this.mCurrentUser.getLastName()).putString(KEY_SAVED_USER_PICTURE_URL, this.mCurrentUser.getPictureUrl()).putString(KEY_SAVED_USER_PICTURE_URL_LARGE, this.mCurrentUser.getPictureUrlLarge()).putBoolean(KEY_SAVED_USER_HAS_PROFILE_PIC, this.mCurrentUser.hasProfilePic()).putInt(KEY_SAVED_USER_TOTAL_LISTINGS_COUNT, this.mCurrentUser.getTotalListingsCount()).putInt(KEY_SAVED_USER_LISTINGS_COUNT, this.mCurrentUser.getListingsCount()).putLong(KEY_SAVED_USER_BIRTHDATE, this.mCurrentUser.getBirthdate() != null ? this.mCurrentUser.getBirthdate().getTimeInMillisAtStartOfDay() : -1);
        String str = KEY_SAVED_USER_CREATED_AT;
        if (this.mCurrentUser.getCreatedAt() != null) {
            j = this.mCurrentUser.getCreatedAt().getMillis();
        }
        putLong.putLong(str, j).putStringSet("verifications", this.mCurrentUser.getVerifications() != null ? new HashSet(this.mCurrentUser.getVerifications()) : new HashSet()).putBoolean(KEY_SAVED_USER_MANUALLY_VERIFIED, this.mCurrentUser.isManuallyVerified()).remove(JSON_NEW_USER_KEY).remove(USER_JSON_KEY).apply();
    }

    @Deprecated
    public boolean hasCurrentUser() {
        return getCurrentUser() != null;
    }

    public void invalidateSession() {
        this.mCurrentUser = null;
        this.mAccessToken = null;
        setIsVRPlatformPoweredHost(false);
        BugsnagWrapper.setLoggedIn(false);
    }

    public boolean isCurrentUserAuthorized() {
        return hasAccessToken() && hasCurrentUser();
    }

    public boolean hasAccessToken() {
        return !TextUtils.isEmpty(getAccessToken());
    }

    public String getAccessToken() {
        if (this.mAccessToken == null) {
            this.mAccessToken = this.mPreferences.getString("access_token", null);
        }
        return this.mAccessToken;
    }

    public void setAccessToken(String token) {
        this.mAccessToken = token;
        this.mPreferences.edit().putString("access_token", token).apply();
    }

    public boolean isCurrentUser(long userId) {
        User user = getCurrentUser();
        return user != null && user.getId() == userId;
    }

    public long getCurrentUserId() {
        User user = getCurrentUser();
        if (user != null) {
            return user.getId();
        }
        return -1;
    }

    public static long currentUserId() {
        return currentUserId(CoreApplication.appContext());
    }

    public static long currentUserId(Context context) {
        return CoreApplication.instance(context).component().accountManager().getCurrentUserId();
    }

    public boolean userHasListings() {
        return isCurrentUserAuthorized() && getCurrentUser().getTotalListingsCount() > 0;
    }

    public boolean userHasActiveListings() {
        return isCurrentUserAuthorized() && getCurrentUser().getListingsCount() > 0;
    }

    public void setIsVRPlatformPoweredHost(boolean isVRPlatformPoweredHost) {
        this.mIsVRPlatformPoweredHost = isVRPlatformPoweredHost;
    }

    public boolean isVRPlatformPoweredHost() {
        return this.mIsVRPlatformPoweredHost;
    }

    public AccountManager getManager() {
        return this.manager;
    }

    public boolean hasAccountSwitcher() {
        boolean hasAccounts;
        if (SecurityUtil.maybeGetAndroidAccounts(this.manager).length > 0) {
            hasAccounts = true;
        } else {
            hasAccounts = false;
        }
        if ((!Trebuchet.launch(Trebuchet.KEY_ACCOUNT_SWITCHER, "enabled", true) || !hasAccounts || SwitchAccountAnalytics.getAccountsCount() <= 1) && !BuildHelper.isDebugFeaturesEnabled()) {
            return false;
        }
        return true;
    }

    public void incrementListingCount() {
        User user = getCurrentUser();
        if (user != null) {
            user.setTotalListingsCount(user.getTotalListingsCount() + 1);
        }
    }

    public void decrementListingCount() {
        User user = getCurrentUser();
        if (user != null) {
            user.setTotalListingsCount(user.getTotalListingsCount() > 0 ? user.getTotalListingsCount() - 1 : 0);
        }
    }
}
