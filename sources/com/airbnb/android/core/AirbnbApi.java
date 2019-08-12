package com.airbnb.android.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.analytics.AppboyAnalytics;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.events.LogoutEvent;
import com.airbnb.android.core.events.UpcomingTripEvent;
import com.airbnb.android.core.messaging.MessageStore;
import com.airbnb.android.core.requests.CreateAirNotificationDeviceRequest;
import com.airbnb.android.core.requests.DeleteOauthTokenRequest;
import com.airbnb.android.core.requests.MParticleCustomerIdRequest;
import com.airbnb.android.core.requests.UpdateAirNotificationDeviceRequest;
import com.airbnb.android.core.responses.AirNotificationDeviceResponse;
import com.airbnb.android.core.responses.MParticleCustomerIdResponse;
import com.airbnb.android.core.utils.AirCookieManager;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PushHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Experiments;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.mparticle.MParticle;
import com.squareup.otto.Bus;
import java.io.IOException;
import okhttp3.Cache;
import p032rx.Observer;

public class AirbnbApi {
    private static final String ACTIVE_TRIP_KEY = "active_trip";
    public static final String ANDROID_LOCALHOST_AVD_API_URL = "http://10.0.2.2/";
    public static String API_ENDPOINT_URL = null;
    private static final String CHINA_API_URL = "https://api.airbnbchina.cn/";
    public static final String GENYMOTION_LOCALHOST_API_URL = "http://10.0.3.2/";
    public static final String GRAPHQL_URL_PATH = "/api/v2/graphql";
    private static final String LANDING_TAB_KEY = "key_landing_tab";
    public static final String NEXT_API_URL = "https://api.next.airbnb.com/";
    private static final String PREF_DEBUG_API_ENDPOINT_URL = "saved_endpoint_url";
    public static final String PROD_API_URL = "https://api.airbnb.com/";
    private static final String UPCOMING_TRIPS_KEY = "upcoming_trips";
    private final Cache cache;
    private final DeviceInfo deviceInfo;
    private boolean hasActiveTrip;
    private final AirbnbAccountManager mAccountManager;
    private final Bus mBus;
    private final Context mContext;
    private boolean mHasUpcomingTrips = false;
    /* access modifiers changed from: private */
    public final AirbnbPreferences mPreferences;
    private final MessageStore messageStore;

    public AirbnbApi(Context context, AirbnbPreferences sharedPreferences, MessageStore messageStore2, AirbnbAccountManager accountManager, Bus bus, Cache cache2, DeviceInfo deviceInfo2) {
        this.mAccountManager = accountManager;
        this.mContext = context;
        this.mPreferences = sharedPreferences;
        this.messageStore = messageStore2;
        this.cache = cache2;
        this.deviceInfo = deviceInfo2;
        this.mBus = bus;
        new Handler(Looper.getMainLooper()).post(AirbnbApi$$Lambda$1.lambdaFactory$(this, bus));
        if (BuildHelper.optEnabled("dev_server")) {
            API_ENDPOINT_URL = "http://api.localhost.airbnb.com/";
        } else if (BuildHelper.isDebugFeaturesEnabled() && this.mPreferences.getGlobalSharedPreferences().contains(PREF_DEBUG_API_ENDPOINT_URL)) {
            API_ENDPOINT_URL = this.mPreferences.getGlobalSharedPreferences().getString(PREF_DEBUG_API_ENDPOINT_URL, PROD_API_URL);
        } else if (shouldUseChinaDomain(this.mPreferences.getSharedPreferences().getString(AirbnbConstants.PREFS_COUNTRY_OF_CURRENT_IP, ""))) {
            API_ENDPOINT_URL = CHINA_API_URL;
        } else {
            API_ENDPOINT_URL = PROD_API_URL;
        }
    }

    public void enablePushNotifications() {
        final PushHelper pushHelper = PushHelper.newInstance(this.mContext);
        String pushToken = pushHelper.getCachedRegistrationIdSafe();
        if (!TextUtils.isEmpty(pushToken)) {
            CreateAirNotificationDeviceRequest.newInstance(pushHelper.getDeviceType(), pushToken).withListener((Observer) new NonResubscribableRequestListener<AirNotificationDeviceResponse>() {
                public void onResponse(AirNotificationDeviceResponse data) {
                    pushHelper.saveAirNotificationDeviceId(data.notificationDevice.getId());
                }

                public void onErrorResponse(AirRequestNetworkException e) {
                    pushHelper.forceUpdateOnNextLaunch();
                }
            }).execute(NetworkUtil.singleFireExecutor());
        } else {
            pushHelper.setupPush();
        }
    }

    private boolean shouldUseChinaDomain(String country) {
        if (AirbnbConstants.COUNTRY_CODE_CHINA.equalsIgnoreCase(country)) {
            if (Trebuchet.launch(TrebuchetKeys.CHINA_DOMAIN_FORCE)) {
                return true;
            }
            if (Trebuchet.launch(TrebuchetKeys.CHINA_DOMAIN_ENABLE) && Experiments.enableAirbnbChina()) {
                return true;
            }
        }
        return false;
    }

    public void setEndpointUrl(String url) {
        if (BuildHelper.isDebugFeaturesEnabled()) {
            if (this.mAccountManager.hasAccessToken()) {
                logout();
            }
            API_ENDPOINT_URL = url;
            this.mPreferences.getGlobalSharedPreferences().edit().putString(PREF_DEBUG_API_ENDPOINT_URL, API_ENDPOINT_URL).apply();
            return;
        }
        API_ENDPOINT_URL = PROD_API_URL;
    }

    private void disablePushNotifications(final Runnable postLogoutRunnable) {
        final PushHelper pushHelper = PushHelper.newInstance(this.mContext);
        pushHelper.deleteRegistrationId();
        if (!this.mAccountManager.isCurrentUserAuthorized()) {
            pushHelper.saveAirNotificationDeviceId(0);
            return;
        }
        long airNotifDeviceId = pushHelper.getAirNotificationDeviceId();
        if (airNotifDeviceId > 0) {
            UpdateAirNotificationDeviceRequest.forDisableDevice(airNotifDeviceId).withListener((Observer) new SimpleRequestListener<AirNotificationDeviceResponse>() {
                public void onResponse(AirNotificationDeviceResponse response) {
                    if (postLogoutRunnable != null) {
                        postLogoutRunnable.run();
                    }
                    pushHelper.saveAirNotificationDeviceId(0);
                }
            }).execute(NetworkUtil.singleFireExecutor());
        } else if (postLogoutRunnable != null) {
            postLogoutRunnable.run();
        }
    }

    public void logout() {
        if (!MiscUtils.isUserAMonkey()) {
            logoutWithoutRevokingOauth(AirbnbApi$$Lambda$2.lambdaFactory$(new DeleteOauthTokenRequest()));
        }
    }

    public void logoutWithoutRevokingOauth() {
        logoutWithoutRevokingOauth(null);
    }

    private void logoutWithoutRevokingOauth(Runnable postLogoutRunnable) {
        if (FacebookSdk.isInitialized()) {
            LoginManager.getInstance().logOut();
        }
        mParticleRemoveUserIdentity();
        disablePushNotifications(postLogoutRunnable);
        clearUserSession();
        this.mBus.post(new LogoutEvent());
        MParticleAnalytics.logEvent("logout", Strap.make());
        AppboyAnalytics.logEvent(this.mContext, "logout", Strap.make());
    }

    public void clearUserSession() {
        clearJsonUserPrefs();
        this.messageStore.clearAll();
        try {
            this.cache.evictAll();
        } catch (IOException e) {
        }
        this.mAccountManager.invalidateSession();
        setHasUpcomingTrips(false);
        AirCookieManager.clearSessionCookies(this.mContext);
    }

    private void mParticleRemoveUserIdentity() {
        if (this.mAccountManager.isCurrentUserAuthorized()) {
            MParticleCustomerIdRequest.newRequestForMParticleCustomerId().withListener((Observer) new NonResubscribableRequestListener<MParticleCustomerIdResponse>() {
                public void onResponse(MParticleCustomerIdResponse data) {
                    MParticle.getInstance().removeUserIdentity(data.mParticleUser.getMparticleCustomerId());
                    AirbnbApi.this.mPreferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_MPARTICLE_CURRENT_MPARTICLE_CUSTOMER_ID, "").apply();
                }

                public void onErrorResponse(AirRequestNetworkException e) {
                    String mParticleCustomerId = AirbnbApi.this.mPreferences.getSharedPreferences().getString(AirbnbConstants.PREFS_MPARTICLE_CURRENT_MPARTICLE_CUSTOMER_ID, "");
                    if (!TextUtils.isEmpty(mParticleCustomerId)) {
                        MParticle.getInstance().removeUserIdentity(mParticleCustomerId);
                    }
                    AirbnbApi.this.mPreferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_MPARTICLE_CURRENT_MPARTICLE_CUSTOMER_ID, "").apply();
                    BugsnagWrapper.notify((NetworkException) e);
                }
            }).execute(NetworkUtil.singleFireExecutor());
        }
    }

    private void clearJsonUserPrefs() {
        this.mPreferences.getSharedPreferences().edit().clear().apply();
    }

    @Deprecated
    public String getAndroidId() {
        return this.deviceInfo.getAndroidId();
    }

    @Deprecated
    public String getDeviceCountry() {
        return this.deviceInfo.getDeviceCountry();
    }

    @Deprecated
    public String getUserAgent() {
        return this.deviceInfo.getUserAgent();
    }

    public void setHasUpcomingTrips(boolean hasUpcomingTrips) {
        if (!this.mHasUpcomingTrips && hasUpcomingTrips) {
            this.mBus.post(new UpcomingTripEvent());
        }
        this.mHasUpcomingTrips = hasUpcomingTrips;
        this.mPreferences.getSharedPreferences().edit().putBoolean(UPCOMING_TRIPS_KEY, hasUpcomingTrips).apply();
    }

    public void setLandingTabId(String tabId) {
        this.mPreferences.getSharedPreferences().edit().putString(LANDING_TAB_KEY, tabId).apply();
    }

    public String getLandingTabId() {
        return this.mPreferences.getSharedPreferences().getString(LANDING_TAB_KEY, null);
    }

    public void setHasActiveTrip(boolean hasActiveTrip2) {
        this.hasActiveTrip = hasActiveTrip2;
        this.mPreferences.getSharedPreferences().edit().putBoolean(ACTIVE_TRIP_KEY, hasActiveTrip2).apply();
    }

    public boolean hasUpcomingTrips() {
        if (!this.mAccountManager.isCurrentUserAuthorized()) {
            return false;
        }
        if (!this.mHasUpcomingTrips) {
            this.mHasUpcomingTrips = this.mPreferences.getSharedPreferences().getBoolean(UPCOMING_TRIPS_KEY, false);
        }
        return this.mHasUpcomingTrips;
    }

    public boolean hasActiveTrip() {
        if (!this.mAccountManager.isCurrentUserAuthorized()) {
            return false;
        }
        if (!this.hasActiveTrip) {
            this.hasActiveTrip = this.mPreferences.getSharedPreferences().getBoolean(ACTIVE_TRIP_KEY, false);
        }
        return this.hasActiveTrip;
    }

    public static boolean devEndpointEnabled() {
        return !PROD_API_URL.equals(API_ENDPOINT_URL) && !NEXT_API_URL.equals(API_ENDPOINT_URL) && !CHINA_API_URL.equals(API_ENDPOINT_URL);
    }
}
