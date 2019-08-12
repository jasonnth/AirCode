package com.airbnb.android.core.localpushnotifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.p000v4.app.NotificationCompat;
import android.support.p000v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.analytics.PushAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.airbnb.android.core.requests.GuestReservationsRequest;
import com.airbnb.android.core.requests.P3ListingRequest;
import com.airbnb.android.core.responses.GuestReservationsResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;

public final class LocalPushNotificationManager {
    public static final String LOCAL_PUSH_EXTRA_LISTING_NAME = "local_push_listing_name";
    public static final String LOCAL_PUSH_EXTRA_LOCALIZED_CITY = "local_push_localized_city";
    public static final String LOCAL_PUSH_EXTRA_PUSH_TYPE = "local_push_type";
    private static final int LOCAL_PUSH_P2_SEARCH_TIMES_MIN = 2;
    private static final int LOCAL_PUSH_P3_LISTING_VIEW_TIMES_MIN = 2;
    private static final long ONE_DAY = 86400000;
    private static final long ONE_SECOND = 1000;
    private static final String PREFS_LOCAL_PUSH_LISTING_CHECK_FOR_RESERVATION_CITY = "listing_city_check_for_reservation";
    private static final String PREFS_LOCAL_PUSH_LISTING_CHECK_FOR_RESERVATION_LISTING_ID = "listing_id_check_for_reservation";
    private static final String PREFS_LOCAL_PUSH_LISTING_ICON = "listing_icon";
    private static final String PREFS_LOCAL_PUSH_LISTING_NAME = "listing_name";
    private static final String PREFS_LOCAL_PUSH_UNSUBSCRIBE = "local_push_unsubscribe";
    private static final String PREFS_P2_SEARCH_LOCATION_RECORD_MAP = "p2_search_location_map";
    private static final String PREFS_P3_LISTINGS_RECORD_MAP = "p3_listings_storage_map";
    private static final String PREFS_P4_LOCAL_PUSH_CITY = "p4_local_push_city";
    private static final String PREFS_P4_LOCAL_PUSH_LISTING_TIME_STAMP = "p4_listing_time_stamp";
    private static final String PREFS_SEEN_LOCAL_PUSH_TIME_STAMP = "prefs_seen_local_push_time_stamp";
    private static long REPEAT_PUSH_GAP_INTERVAL = 0;
    public static final int REQUEST_CODE = 766;
    private static long SCHEDULE_LOCAL_PUSH_NOTIFICATION_FUTURE_TIME = 0;
    private static final long SEVEN_DAYS = 604800000;
    private static final String TAG = LocalPushNotificationManager.class.getSimpleName();
    private static final long THREE_MINUTES = 180000;
    private static final long TWEENTY_SECONDS = 20000;
    private static long VALID_LOCAL_PUSH_INTERVAL;
    private final AirbnbAccountManager accountManager;
    private final AirbnbApi airbnbApi;
    private final Context context;
    DebugSettings debugSettings;
    private final NonResubscribableRequestListener<ListingResponse> listingRequestListener = new NonResubscribableRequestListener<ListingResponse>() {
        public void onResponse(ListingResponse response) {
            Listing listing = response.listing;
            if (listing != null) {
                LocalPushNotificationManager.this.schedulePush(listing.getId(), listing.getName(), listing.getThumbnailUrl(), listing.getLocalizedCity(), PushType.P3);
                LocalPushNotificationManager.this.sharedPref.getSharedPreferences().edit().putString(LocalPushNotificationManager.PREFS_LOCAL_PUSH_LISTING_CHECK_FOR_RESERVATION_CITY, listing.getLocalizedCity()).apply();
            }
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            LocalPushAnalytics.trackOnRequestError(LocalPushNotificationManager.this.getLocalPushType(), e.getMessage());
        }
    };
    private LocalPushDeliverListener localPushDeliverListener;
    /* access modifiers changed from: private */
    public final AirbnbPreferences sharedPref;
    private final NonResubscribableRequestListener<GuestReservationsResponse> upcomingTripsRequestListener = new NonResubscribableRequestListener<GuestReservationsResponse>() {
        public void onResponse(GuestReservationsResponse response) {
            LocalPushNotificationManager.this.handleUpcomingReservation(response);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            LocalPushAnalytics.trackOnRequestError(LocalPushNotificationManager.this.getLocalPushType(), e.getMessage());
        }
    };

    public enum PushType {
        P2,
        P3,
        P4
    }

    public LocalPushNotificationManager(Context context2, AirbnbPreferences sharedPref2, AirbnbApi airbnbApi2, AirbnbAccountManager accountManager2) {
        long j = THREE_MINUTES;
        this.context = context2;
        this.sharedPref = sharedPref2;
        this.airbnbApi = airbnbApi2;
        this.accountManager = accountManager2;
        ((CoreGraph) CoreApplication.instance().component()).inject(this);
        boolean isDebug = BuildHelper.isDebugFeaturesEnabled();
        VALID_LOCAL_PUSH_INTERVAL = isDebug ? 180000 : 604800000;
        SCHEDULE_LOCAL_PUSH_NOTIFICATION_FUTURE_TIME = isDebug ? 1000 : 86400000;
        DebugSettings debugSettings2 = this.debugSettings;
        if (DebugSettings.DELIVER_LOCAL_PUSH_IN_TWENTY_SECONDS.isEnabled()) {
            SCHEDULE_LOCAL_PUSH_NOTIFICATION_FUTURE_TIME = TWEENTY_SECONDS;
        }
        if (!isDebug) {
            j = 604800000;
        }
        REPEAT_PUSH_GAP_INTERVAL = j;
    }

    public LocalPushNotificationManager withListener(LocalPushDeliverListener listener) {
        this.localPushDeliverListener = listener;
        return this;
    }

    /* access modifiers changed from: private */
    public void handleUpcomingReservation(GuestReservationsResponse response) {
        if (PushType.P4 == getLocalPushType()) {
            if (!response.findReservationById(this.sharedPref.getSharedPreferences().getLong(PREFS_LOCAL_PUSH_LISTING_CHECK_FOR_RESERVATION_LISTING_ID, 0))) {
                deliverLocalPush();
                return;
            }
        } else if (!response.findReservationByLocation(this.sharedPref.getSharedPreferences().getString(PREFS_LOCAL_PUSH_LISTING_CHECK_FOR_RESERVATION_CITY, ""))) {
            deliverLocalPush();
            return;
        }
        LocalPushAnalytics.trackListingIsBookedBeforePushDeliverying(getLocalPushType());
    }

    private void deliverLocalPush() {
        this.localPushDeliverListener.deliverLocalPush();
        C0715L.m1189d(TAG, "Localpush about to deliver push for " + getLocalPushType());
        LocalPushAnalytics.trackDeliverLocalPushNotification(getLocalPushType());
    }

    public void onLocalPushReceived() {
        if (!NotificationManagerCompat.from(this.context).areNotificationsEnabled()) {
            C0715L.m1189d(TAG, "Localpush disabled");
            LocalPushAnalytics.trackUserDisabledPushNotificationFromSystem();
        } else if (haveSeenLocalPush()) {
            C0715L.m1189d(TAG, "Localpush has seen local push");
            LocalPushAnalytics.trackUserHasSeenLocalPush();
        } else if (this.localPushDeliverListener == null) {
            BugsnagWrapper.throwOrNotify(new RuntimeException("Not expected, forgot to set localPushDeliverListener for LocalPushNotificationManager"));
        } else {
            checkForBookingAndDeliverLocalPush();
        }
    }

    private void checkForBookingAndDeliverLocalPush() {
        C0715L.m1189d(TAG, "Localpush about to check reservation");
        GuestReservationsRequest.forGuestHome(this.airbnbApi, this.accountManager.getCurrentUserId()).withListener((Observer) this.upcomingTripsRequestListener).execute(NetworkUtil.singleFireExecutor());
    }

    public void scheduleNotification() throws JSONException {
        if ((!haveSeenLocalPush() || BuildHelper.isDebugFeaturesEnabled()) && !scheduleP4PushNotificaiton() && !scheduleP3PushNotificaiton()) {
            scheduleP2PushNotificaiton();
        }
    }

    private void scheduleP2PushNotificaiton() throws JSONException {
        String targetCity = findTargetP2City();
        if (!TextUtils.isEmpty(targetCity)) {
            schedulePush(0, "", "", targetCity, PushType.P2);
            this.sharedPref.getSharedPreferences().edit().putString(PREFS_LOCAL_PUSH_LISTING_CHECK_FOR_RESERVATION_CITY, targetCity).apply();
        }
    }

    private boolean scheduleP3PushNotificaiton() throws JSONException {
        long targetListingId = findTargetP3Listing();
        if (targetListingId == 0) {
            return false;
        }
        P3ListingRequest.forListingId(targetListingId).withListener((Observer) this.listingRequestListener).execute(NetworkUtil.singleFireExecutor());
        return true;
    }

    private long findTargetP3Listing() throws JSONException {
        Entry<String, Integer> maxEntry = findMostViewedEntry(PREFS_P3_LISTINGS_RECORD_MAP);
        if (maxEntry == null || ((Integer) maxEntry.getValue()).intValue() < 2) {
            return 0;
        }
        return Long.valueOf((String) maxEntry.getKey()).longValue();
    }

    private String findTargetP2City() throws JSONException {
        Entry<String, Integer> maxEntry = findMostViewedEntry(PREFS_P2_SEARCH_LOCATION_RECORD_MAP);
        if (maxEntry == null || ((Integer) maxEntry.getValue()).intValue() < 2) {
            return "";
        }
        return (String) maxEntry.getKey();
    }

    private Entry<String, Integer> findMostViewedEntry(String prefKey) throws JSONException {
        clearExpiredRecord(prefKey);
        JSONObject recordMap = getStoredRecordMap(prefKey);
        Iterator<String> recordKeys = recordMap.keys();
        if (!recordKeys.hasNext()) {
            return null;
        }
        Map<String, Integer> RecordCountMap = new HashMap<>();
        while (recordKeys.hasNext()) {
            String valueRecord = recordMap.getString((String) recordKeys.next());
            RecordCountMap.put(valueRecord, Integer.valueOf(RecordCountMap.get(valueRecord) == null ? 1 : ((Integer) RecordCountMap.get(valueRecord)).intValue() + 1));
        }
        Entry<String, Integer> maxEntry = null;
        for (Entry<String, Integer> entry : RecordCountMap.entrySet()) {
            if (maxEntry == null || ((Integer) entry.getValue()).compareTo((Integer) maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }

    private boolean scheduleP4PushNotificaiton() {
        SharedPreferences pref = this.sharedPref.getSharedPreferences();
        long listingId = pref.getLong(PREFS_LOCAL_PUSH_LISTING_CHECK_FOR_RESERVATION_LISTING_ID, 0);
        if (listingId == 0) {
            return false;
        }
        if (System.currentTimeMillis() - pref.getLong(PREFS_P4_LOCAL_PUSH_LISTING_TIME_STAMP, 0) > VALID_LOCAL_PUSH_INTERVAL) {
            return false;
        }
        String listingName = pref.getString("listing_name", "");
        String listingIcon = pref.getString(PREFS_LOCAL_PUSH_LISTING_ICON, "");
        if (TextUtils.isEmpty(listingName)) {
            return false;
        }
        schedulePush(listingId, listingName, listingIcon, this.sharedPref.getSharedPreferences().getString(PREFS_P4_LOCAL_PUSH_CITY, ""), PushType.P4);
        return true;
    }

    /* access modifiers changed from: private */
    public void schedulePush(long listingId, String listingName, String listingIcon, String localizedCity, PushType pushType) {
        LocalPushAnalytics.trackScheduling(pushType, listingId, listingName, localizedCity);
        long futureInMillis = SystemClock.elapsedRealtime() + SCHEDULE_LOCAL_PUSH_NOTIFICATION_FUTURE_TIME;
        ((AlarmManager) this.context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(2, futureInMillis, PendingIntent.getBroadcast(this.context, REQUEST_CODE, constructIntent(new Intent(this.context, LocalPushNotificationReceiver.class), listingName, listingId, listingIcon, localizedCity, pushType), 134217728));
        this.sharedPref.getSharedPreferences().edit().putString(LOCAL_PUSH_EXTRA_PUSH_TYPE, pushType.toString()).commit();
    }

    private Intent constructIntent(Intent intent, String listingName, long listingId, String listingIcon, String localizedCity, PushType pushType) {
        if (PushType.P4.equals(pushType) || PushType.P3.equals(pushType)) {
            return p3p4ConstructIntent(intent, listingName, listingId, listingIcon, localizedCity, pushType);
        }
        if (PushType.P2.equals(pushType)) {
            return p2ConstructIntent(intent, localizedCity);
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unsupported push type " + pushType));
        return intent;
    }

    private Intent p3p4ConstructIntent(Intent intent, String listingName, long listingId, String listingIcon, String localizedCity, PushType pushType) {
        LocalPushAnalytics.trackConstructPushIntent(pushType, listingId, listingName, localizedCity);
        intent.putExtra(LOCAL_PUSH_EXTRA_PUSH_TYPE, pushType).putExtra(LOCAL_PUSH_EXTRA_LISTING_NAME, listingName).putExtra(LOCAL_PUSH_EXTRA_LOCALIZED_CITY, localizedCity).putExtra(PushNotificationConstants.EXTRA_ICON_URL, listingIcon).putExtra(PushNotificationConstants.DEEP_LINK_KEY, "airbnb://d/listing/" + listingId);
        return intent;
    }

    private Intent p2ConstructIntent(Intent intent, String localizedCity) {
        LocalPushAnalytics.trackConstructPushIntent(PushType.P2, -4, "leave_blank", localizedCity);
        intent.putExtra(LOCAL_PUSH_EXTRA_PUSH_TYPE, PushType.P2).putExtra(LOCAL_PUSH_EXTRA_LOCALIZED_CITY, localizedCity).putExtra(PushNotificationConstants.DEEP_LINK_KEY, "airbnb://d/s/" + localizedCity);
        return intent;
    }

    public void p4StoreListing(Listing listing) {
        this.sharedPref.getSharedPreferences().edit().putLong(PREFS_LOCAL_PUSH_LISTING_CHECK_FOR_RESERVATION_LISTING_ID, listing.getId()).putLong(PREFS_P4_LOCAL_PUSH_LISTING_TIME_STAMP, System.currentTimeMillis()).putString("listing_name", listing.getName()).putString(PREFS_P4_LOCAL_PUSH_CITY, listing.getLocalizedCity()).putString(PREFS_LOCAL_PUSH_LISTING_ICON, listing.getThumbnailUrl()).apply();
    }

    public void p3StoreListing(Listing listing) throws JSONException {
        JSONObject preListingsJSONMap = getStoredRecordMap(PREFS_P3_LISTINGS_RECORD_MAP);
        preListingsJSONMap.put(String.valueOf(System.currentTimeMillis()), listing.getId());
        storeJSONString(PREFS_P3_LISTINGS_RECORD_MAP, preListingsJSONMap.toString(), false);
    }

    public void p2StoreLocation(String city) throws JSONException {
        if (!TextUtils.isEmpty(city)) {
            JSONObject preSearchLocationJSONMap = getStoredRecordMap(PREFS_P2_SEARCH_LOCATION_RECORD_MAP);
            preSearchLocationJSONMap.put(String.valueOf(System.currentTimeMillis()), city);
            storeJSONString(PREFS_P2_SEARCH_LOCATION_RECORD_MAP, preSearchLocationJSONMap.toString(), false);
        }
    }

    private void clearExpiredRecord(String prefKey) throws JSONException {
        long currentTime = System.currentTimeMillis();
        JSONObject preRecordMap = getStoredRecordMap(prefKey);
        Iterator<String> preRecordKeys = preRecordMap.keys();
        JSONObject newRecordMap = new JSONObject();
        while (preRecordKeys.hasNext()) {
            String key = (String) preRecordKeys.next();
            if (currentTime - Long.valueOf(key).longValue() < VALID_LOCAL_PUSH_INTERVAL) {
                newRecordMap.put(key, preRecordMap.getString(key));
            }
        }
        storeJSONString(prefKey, newRecordMap.toString(), true);
    }

    private void storeJSONString(String prefKey, String jsonString, boolean isThreadSafe) {
        Log.d(TAG, "about to store json map: " + jsonString);
        if (isThreadSafe) {
            this.sharedPref.getSharedPreferences().edit().putString(prefKey, jsonString).commit();
        } else {
            this.sharedPref.getSharedPreferences().edit().putString(prefKey, jsonString).apply();
        }
    }

    private JSONObject getStoredRecordMap(String prefKey) throws JSONException {
        String preRecord = this.sharedPref.getSharedPreferences().getString(prefKey, "");
        if (TextUtils.isEmpty(preRecord)) {
            return new JSONObject();
        }
        return new JSONObject(preRecord);
    }

    public boolean haveSeenLocalPush() {
        DebugSettings debugSettings2 = this.debugSettings;
        if (DebugSettings.DISABLE_LOCAL_PUSH.isEnabled()) {
            return true;
        }
        DebugSettings debugSettings3 = this.debugSettings;
        if (DebugSettings.INFINITE_LOCAL_PUSH.isEnabled()) {
            return false;
        }
        if (!Trebuchet.launch(TrebuchetKeys.ANDROID_LOCAL_PUSH_NOTIFICATION_DISABLED)) {
            return lastSeenPushIsWithinGap();
        }
        return true;
    }

    private boolean lastSeenPushIsWithinGap() {
        return System.currentTimeMillis() - this.sharedPref.getSharedPreferences().getLong(PREFS_SEEN_LOCAL_PUSH_TIME_STAMP, 0) <= REPEAT_PUSH_GAP_INTERVAL;
    }

    public void markLocalPushSeen() {
        this.sharedPref.getSharedPreferences().edit().putLong(PREFS_SEEN_LOCAL_PUSH_TIME_STAMP, System.currentTimeMillis()).apply();
    }

    public void reportUserUnsubscribeIfNecessary() {
        boolean pushEnabled = NotificationManagerCompat.from(this.context).areNotificationsEnabled();
        if (!pushEnabled && this.sharedPref.getSharedPreferences().getBoolean(PREFS_LOCAL_PUSH_UNSUBSCRIBE, false)) {
            PushAnalytics.reportUserUnsubscribed();
        }
        this.sharedPref.getSharedPreferences().edit().putBoolean(PREFS_LOCAL_PUSH_UNSUBSCRIBE, pushEnabled).apply();
    }

    /* access modifiers changed from: private */
    public PushType getLocalPushType() {
        String pushType = this.sharedPref.getSharedPreferences().getString(LOCAL_PUSH_EXTRA_PUSH_TYPE, "");
        if (!TextUtils.isEmpty(pushType)) {
            return PushType.valueOf(pushType);
        }
        return null;
    }
}
