package com.airbnb.android.core.utils;

import android.util.Log;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.utils.AirbnbConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class SharedPrefsHelper {
    private static final String TAG = SharedPrefsHelper.class.getSimpleName();
    private final AirbnbPreferences preferences;

    public SharedPrefsHelper(AirbnbPreferences preferences2) {
        this.preferences = preferences2;
    }

    private boolean isTrueFor(String key) {
        return this.preferences.getGlobalSharedPreferences().getBoolean(key, false);
    }

    private void setTrueFor(String key) {
        setValueFor(key, true);
    }

    private void setValueFor(String key, boolean value) {
        this.preferences.getGlobalSharedPreferences().edit().putBoolean(key, value).apply();
    }

    private boolean hasSetKey(String key) {
        return this.preferences.getGlobalSharedPreferences().contains(key);
    }

    public boolean shouldShowBasePriceToolTip() {
        return !isTrueFor(AirbnbConstants.PREFS_SEEN_BASE_PRICE_TOOLTIP);
    }

    public void markBasePriceToolTipAsSeen() {
        setTrueFor(AirbnbConstants.PREFS_SEEN_BASE_PRICE_TOOLTIP);
    }

    public boolean shouldShowMaxPriceToolTip() {
        return !isTrueFor(AirbnbConstants.PREFS_SEEN_MAX_PRICE_TOOLTIP);
    }

    public void markMaxPriceToolTipAsSeen() {
        setTrueFor(AirbnbConstants.PREFS_SEEN_MAX_PRICE_TOOLTIP);
    }

    public boolean shouldShowMinPriceToolTip() {
        return !isTrueFor(AirbnbConstants.PREFS_SEEN_MIN_PRICE_TOOLTIP);
    }

    public void markMinPriceToolTipAsSeen() {
        setTrueFor(AirbnbConstants.PREFS_SEEN_MIN_PRICE_TOOLTIP);
    }

    public boolean shouldSeeInstantBookFtue() {
        return shouldSeeFtue("instant_book_ftue");
    }

    public void markInstantBookFtueAsSeen() {
        setTrueFor("instant_book_ftue");
    }

    public void markCannedMessageTooltipAsSeen() {
        setTrueFor(AirbnbConstants.PREFS_SEEN_CANNED_MESSAGE_TOOLTIP);
    }

    public boolean shouldSeeCannedMessageTooltip() {
        return shouldSeeFtue(AirbnbConstants.PREFS_SEEN_CANNED_MESSAGE_TOOLTIP);
    }

    private boolean shouldSeeFtue(String ftueKey) {
        if (!this.preferences.getGlobalSharedPreferences().getBoolean(ftueKey, false)) {
            return true;
        }
        return false;
    }

    public void markBadgeSeenAndClearedForTripsTabMove() {
        setValueForPerUser(AirbnbConstants.PREFS_BADGE_SEEN_AND_CLEARED_FOR_TRIPS_TAB_MOVE, true);
    }

    public boolean isBadgeSeenAndClearedForTripsTabMove() {
        return isTrueForPerUser(AirbnbConstants.PREFS_BADGE_SEEN_AND_CLEARED_FOR_TRIPS_TAB_MOVE);
    }

    private void setValueForPerUser(String key, boolean value) {
        this.preferences.getSharedPreferences().edit().putBoolean(key, value).apply();
    }

    private boolean isTrueForPerUser(String key) {
        return this.preferences.getSharedPreferences().getBoolean(key, false);
    }

    public int getLoginServiceType() {
        return this.preferences.getGlobalSharedPreferences().getInt(AirbnbConstants.PREFS_LOGIN_SERVICE_TYPE, -1);
    }

    public void saveLoginServiceType(int svcType) {
        this.preferences.getSharedPreferences().edit().putInt(AirbnbConstants.PREFS_LOGIN_SERVICE_TYPE, svcType).apply();
    }

    public void saveCannedMessages(List<String> cannedMessages, boolean forHost) {
        this.preferences.getSharedPreferences().edit().putString(forHost ? AirbnbConstants.PREFS_CANNED_MSG_HOST : AirbnbConstants.PREFS_CANNED_MSG_GUEST, new JSONArray(cannedMessages).toString()).apply();
    }

    public List<String> getCannedMessages(boolean forHost) {
        return deserializeJsonStringArray(this.preferences.getSharedPreferences().getString(forHost ? AirbnbConstants.PREFS_CANNED_MSG_HOST : AirbnbConstants.PREFS_CANNED_MSG_GUEST, null));
    }

    public void addRecentInboxSearch(String query) {
        List<String> searches = getRecentInboxSearches();
        searches.remove(query);
        searches.add(0, query);
        this.preferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_INBOX_SEARCH_RECENTLY_SEARCHED_STRING_ARRAY, new JSONArray(searches.subList(0, Math.min(searches.size(), 5))).toString()).apply();
    }

    public List<String> getRecentInboxSearches() {
        return deserializeJsonStringArray(this.preferences.getSharedPreferences().getString(AirbnbConstants.PREFS_INBOX_SEARCH_RECENTLY_SEARCHED_STRING_ARRAY, null));
    }

    private List<String> deserializeJsonStringArray(String array) {
        List<String> items = new ArrayList<>();
        if (array != null) {
            try {
                JSONArray jsonArray = new JSONArray(array);
                for (int i = 0; i < jsonArray.length(); i++) {
                    items.add(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                Log.w(TAG, "Failed to retrieve json array: ", e);
            }
        }
        return items;
    }

    public void saveListToGlobalSharedPrefs(String key, List<String> list) {
        this.preferences.getGlobalSharedPreferences().edit().putString(key, new JSONArray(list).toString()).apply();
    }

    public List<String> getListFromGlobalSharedPrefs(String key) {
        List<String> list = new ArrayList<>();
        String jsonString = this.preferences.getGlobalSharedPreferences().getString(key, null);
        if (jsonString != null) {
            try {
                JSONArray array = new JSONArray(jsonString);
                for (int i = 0; i < array.length(); i++) {
                    list.add(array.getString(i));
                }
            } catch (JSONException e) {
            }
        }
        return list;
    }

    public boolean isShowTotalPriceEnabled() {
        return isTrueFor(AirbnbConstants.PREFS_SHOW_TOTAL_PRICE);
    }

    public void setShowTotalPriceEnabled(boolean enabled) {
        setValueFor(AirbnbConstants.PREFS_SHOW_TOTAL_PRICE, enabled);
    }

    public void setGuestMode(boolean guestMode) {
        setValueFor(AirbnbConstants.PREFS_OPEN_IN_TRAVEL_MODE, guestMode);
    }

    public boolean isGuestMode() {
        return isTrueFor(AirbnbConstants.PREFS_OPEN_IN_TRAVEL_MODE);
    }

    public boolean hasSetAppMode() {
        return hasSetKey(AirbnbConstants.PREFS_OPEN_IN_TRAVEL_MODE);
    }

    public boolean hasSeenNuxFlowForFeature(String feature) {
        return isTrueFor(feature);
    }

    public boolean hasDismissedSelectNotReadyBanner(long listingId) {
        return isTrueFor(AirbnbConstants.PREFS_HAS_DISMISSED_SELECT_NOT_READY_BANNER_FOR_LISTING + Long.toString(listingId));
    }

    public void dismisSelectNotReadyBanner(long listingId) {
        setValueFor(AirbnbConstants.PREFS_HAS_DISMISSED_SELECT_NOT_READY_BANNER_FOR_LISTING + Long.toString(listingId), true);
    }

    public void setHasSeenNuxFlowForFeature(String feature, boolean hasSeen) {
        setValueFor(feature, hasSeen);
    }

    public boolean hasSeenForYouNux(String keySuffix) {
        return isTrueFor(getForYouNuxKey(keySuffix));
    }

    public void setHasSeenForYouNux(boolean hasSeen, String keySuffix) {
        setValueFor(getForYouNuxKey(keySuffix), hasSeen);
    }

    private String getForYouNuxKey(String keySuffix) {
        return AirbnbConstants.PREFS_HAS_SEEN_FOR_YOU_NUX_PREFIX + keySuffix;
    }

    public boolean hasSeenMagicalTripsNux() {
        return isTrueFor(AirbnbConstants.PREFS_HAS_SEEN_MAGICAL_TRIPS_NUX);
    }

    public void setHasSeenMagicalTripsNux(boolean hasSeen) {
        setValueFor(AirbnbConstants.PREFS_HAS_SEEN_MAGICAL_TRIPS_NUX, hasSeen);
    }

    public boolean hasPermanentlyDeniedLocationPermissions() {
        return isTrueFor(AirbnbConstants.PREFS_HAS_PERMANENTLY_DENIED_LOCATION_PERMISSION);
    }

    public void setHasPermanentlyDeniedLocationPermissions() {
        setValueFor(AirbnbConstants.PREFS_HAS_PERMANENTLY_DENIED_LOCATION_PERMISSION, true);
    }

    public void setSecureIdentifier(String secureIdentifier) {
        this.preferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_SECURE_IDENTIFIER, secureIdentifier).apply();
    }

    public String getSecureIdentifier() {
        return SanitizeUtils.emptyIfNull(this.preferences.getSharedPreferences().getString(AirbnbConstants.PREFS_SECURE_IDENTIFIER, ""));
    }

    public boolean shouldShowPostHostContactReferral() {
        return this.preferences.getSharedPreferences().getInt(AirbnbConstants.PREFS_HOST_CONTACTS_UNTIL_REFERRAL, 2) == 0;
    }

    public void setHostContactForReferral() {
        int contacts_until_referral = this.preferences.getSharedPreferences().getInt(AirbnbConstants.PREFS_HOST_CONTACTS_UNTIL_REFERRAL, 2);
        if (contacts_until_referral >= 0) {
            this.preferences.getSharedPreferences().edit().putInt(AirbnbConstants.PREFS_HOST_CONTACTS_UNTIL_REFERRAL, contacts_until_referral - 1).apply();
        }
    }

    public int getAcceptedRequestCount(String key) {
        return this.preferences.getGlobalSharedPreferences().getInt(key, 0);
    }

    public void setAcceptedRequestCount(String key, int value) {
        this.preferences.getGlobalSharedPreferences().edit().putInt(key, value).apply();
    }

    public void setInstantBookUpsellFlag(String key) {
        this.preferences.getGlobalSharedPreferences().edit().putBoolean(key, true).apply();
    }

    public boolean hasInstantBookUpsellFlag(String key) {
        return this.preferences.getGlobalSharedPreferences().contains(key);
    }

    public void setLastContactUploadTime(long time) {
        this.preferences.getSharedPreferences().edit().putLong(AirbnbConstants.PREFS_LAST_CONTACT_UPLOAD_FOR_ROLODEX_TIME, time).apply();
    }

    public long getLastContactUploadTime() {
        return this.preferences.getSharedPreferences().getLong(AirbnbConstants.PREFS_LAST_CONTACT_UPLOAD_FOR_ROLODEX_TIME, -1);
    }

    public String getLastDebugEmailUsed() {
        return this.preferences.getGlobalSharedPreferences().getString(AirbnbConstants.PREF_PREVIOUS_INTERNAL_REPORT_EMAIL, "");
    }

    public void setLastDebugEmailUsed(String address) {
        this.preferences.getGlobalSharedPreferences().edit().putString(AirbnbConstants.PREF_PREVIOUS_INTERNAL_REPORT_EMAIL, address).apply();
    }

    public boolean shouldShowCollectionInvitationLandingScreen() {
        return this.preferences.getSharedPreferences().getInt(AirbnbConstants.PREFS_NUM_OF_TIME_SEEING_COLLECTIONS_INVITATION_LANDING_SCREEN, 0) < 2;
    }

    public void increaseCollectionInvitationLandingScreenSeenTimes() {
        this.preferences.getSharedPreferences().edit().putInt(AirbnbConstants.PREFS_NUM_OF_TIME_SEEING_COLLECTIONS_INVITATION_LANDING_SCREEN, this.preferences.getSharedPreferences().getInt(AirbnbConstants.PREFS_NUM_OF_TIME_SEEING_COLLECTIONS_INVITATION_LANDING_SCREEN, 0) + 1).apply();
    }

    public boolean shouldShowSelectCloseToPassLandingScreen() {
        return FeatureToggles.showCloseToPassModal() && this.preferences.getSharedPreferences().getInt(AirbnbConstants.PREFS_NUM_OF_TIMES_SEEING_SELECT_CLOSE_TO_PASS_LANDING_SCREEN, 0) < 1;
    }

    public void increaseSelectCloseToPassLandingScreenSeenTimes() {
        this.preferences.getSharedPreferences().edit().putInt(AirbnbConstants.PREFS_NUM_OF_TIMES_SEEING_SELECT_CLOSE_TO_PASS_LANDING_SCREEN, this.preferences.getSharedPreferences().getInt(AirbnbConstants.PREFS_NUM_OF_TIMES_SEEING_SELECT_CLOSE_TO_PASS_LANDING_SCREEN, 0) + 1).apply();
    }

    public void markBadgeSeenAndClearedForProfileCompletion() {
        setValueForPerUser(AirbnbConstants.PREFS_BADGE_SEEN_AND_CLEARED_FOR_PROFILE_COMPLETION, true);
    }

    public boolean isBadgeSeenAndClearedForProfileCompletion() {
        return isTrueForPerUser(AirbnbConstants.PREFS_BADGE_SEEN_AND_CLEARED_FOR_PROFILE_COMPLETION);
    }

    public boolean hasBeenAssignedToNewGuestExperimentForProfileCompletion() {
        return this.preferences.getSharedPreferences().contains(AirbnbConstants.PREFS_NEW_GUEST_EXPERIMENT_ASSIGNMENT_FOR_PROFILE_COMPLETION);
    }

    public boolean isTreatmentNewGuestExperimentAssignmentForProfileCompletion() {
        return isTrueForPerUser(AirbnbConstants.PREFS_NEW_GUEST_EXPERIMENT_ASSIGNMENT_FOR_PROFILE_COMPLETION);
    }

    public void setNewGuestExperimentAssignmentForProfileCompletion(boolean assigned) {
        setValueForPerUser(AirbnbConstants.PREFS_NEW_GUEST_EXPERIMENT_ASSIGNMENT_FOR_PROFILE_COMPLETION, assigned);
    }

    public long getOfflineTransactionCautionInsertedId(long threadId) {
        return this.preferences.getSharedPreferences().getLong("offline_transaction_caution_inserted_id_" + threadId, -1);
    }

    public void setOfflineTransactionCautionInsertedId(long threadId, long postId) {
        this.preferences.getSharedPreferences().edit().putLong("offline_transaction_caution_inserted_id_" + threadId, postId).apply();
    }

    public boolean hasClickedGuestRatingsIbUpsell() {
        return isTrueFor(AirbnbConstants.PREFS_HAS_CLICKED_GUEST_RATINGS_IB_UPSELL);
    }

    public void setHasClickedGuestRatingsIbUpsell(boolean hasClicked) {
        setValueFor(AirbnbConstants.PREFS_HAS_CLICKED_GUEST_RATINGS_IB_UPSELL, hasClicked);
    }

    public void setShowFlightEntryPoint(boolean show) {
        setValueForPerUser(AirbnbConstants.PREFS_SHOW_FLIGHT_ENTRY_POINT, show);
    }

    public boolean shouldShowFlightEntryPoint() {
        return this.preferences.getSharedPreferences().getBoolean(AirbnbConstants.PREFS_SHOW_FLIGHT_ENTRY_POINT, true);
    }
}
