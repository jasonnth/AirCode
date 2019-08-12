package com.airbnb.android.core.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.view.accessibility.AccessibilityManager;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.Services;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.GeneralAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.data.DTKPartnerTask;
import com.airbnb.android.core.data.SFRPartnerTask;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.persist.DomainStore;
import com.airbnb.android.core.requests.AffiliateClickRequest;
import com.airbnb.android.core.requests.CountriesRequest;
import com.airbnb.android.core.responses.CountriesResponse;
import com.airbnb.android.core.services.TripsReservationsSyncServiceIntents;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.ConcurrentUtil;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.Systems.p263v1.SystemsNativeApplicationLaunchEvent.Builder;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.Tracker;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import p032rx.Observer;

@Deprecated
public class AppLaunchUtils {
    private static final int FETCH_DOMAINS_DELAY = 2000;
    private final LoggingContextFactory loggingContextFactory;
    private final AirbnbAccountManager mAccountManager;
    private final AffiliateInfo mAffiliateInfo;
    /* access modifiers changed from: private */
    public final DomainStore mDomainStore;
    /* access modifiers changed from: private */
    public final AirbnbPreferences mPreferences;
    private final Tracker tracker;

    public AppLaunchUtils(AirbnbPreferences preferences, AffiliateInfo affiliateInfo, DomainStore domainStore, AirbnbAccountManager accountManager, Tracker tracker2, LoggingContextFactory loggingContextFactory2) {
        this.mPreferences = preferences;
        this.mAffiliateInfo = affiliateInfo;
        this.mDomainStore = domainStore;
        this.mAccountManager = accountManager;
        this.tracker = tracker2;
        this.loggingContextFactory = loggingContextFactory2;
    }

    public static String getCountryOfIPAddress() {
        DebugSettings debugSettings = CoreApplication.instance().component().debugSettings();
        if (DebugSettings.CHINA_SIMLATION.isEnabled()) {
            return AirbnbConstants.COUNTRY_CODE_CHINA;
        }
        if (DebugSettings.AUSTRALIA_SIMULATION.isEnabled()) {
            return AirbnbConstants.COUNTRY_CODE_AUSTRALIA;
        }
        if (DebugSettings.GERMANY_SIMLATION.isEnabled()) {
            return AirbnbConstants.COUNTRY_CODE_GERMANY;
        }
        return CoreApplication.instance().component().airbnbPreferences().getSharedPreferences().getString(AirbnbConstants.PREFS_COUNTRY_OF_CURRENT_IP, null);
    }

    public static boolean isUserInKorea() {
        return AirbnbConstants.COUNTRY_CODE_KOREA.equalsIgnoreCase(getCountryOfIPAddress());
    }

    public static boolean isUserInChina() {
        return AirbnbConstants.COUNTRY_CODE_CHINA.equalsIgnoreCase(getCountryOfIPAddress());
    }

    public static boolean isUserInUS() {
        return "US".equalsIgnoreCase(getCountryOfIPAddress());
    }

    public static boolean isUserInAustralia() {
        return AirbnbConstants.COUNTRY_CODE_AUSTRALIA.equalsIgnoreCase(getCountryOfIPAddress());
    }

    public static boolean isUserInGermany() {
        return AirbnbConstants.COUNTRY_CODE_GERMANY.equalsIgnoreCase(getCountryOfIPAddress());
    }

    public static boolean isIndiaRegion() {
        return AirbnbConstants.COUNTRY_CODE_INDIA.equals(Locale.getDefault().getCountry());
    }

    @SuppressLint({"MissingPermission"})
    @Deprecated
    public void doTrack(Activity activity, Intent intent) {
        ConcurrentUtil.deferParallel(AppLaunchUtils$$Lambda$1.lambdaFactory$(this, activity, intent));
    }

    static /* synthetic */ void lambda$doTrack$0(AppLaunchUtils appLaunchUtils, Activity activity, Intent intent) {
        String d;
        String d2;
        String l;
        Location lastLocation = null;
        boolean hasPermission = false;
        try {
            hasPermission = LocationUtil.hasLocationPermission(activity);
            if (hasPermission) {
                lastLocation = ((LocationManager) activity.getSystemService("location")).getLastKnownLocation("passive");
            }
        } catch (RuntimeException e) {
        }
        String str = "mobile_location_print";
        Strap make = Strap.make();
        String str2 = "lat";
        if (lastLocation == null) {
            d = "";
        } else {
            d = Double.toString(lastLocation.getLatitude());
        }
        Strap kv = make.mo11639kv(str2, d);
        String str3 = "lng";
        if (lastLocation == null) {
            d2 = "";
        } else {
            d2 = Double.toString(lastLocation.getLongitude());
        }
        Strap kv2 = kv.mo11639kv(str3, d2);
        String str4 = "loc_time";
        if (lastLocation == null) {
            l = "";
        } else {
            l = Long.toString(lastLocation.getTime());
        }
        AirbnbEventLogger.track(str, kv2.mo11639kv(str4, l).mo11640kv("has_permission", hasPermission));
        FacebookSdk.sdkInitialize(activity);
        try {
            AppEventsLogger.activateApp((Context) activity, activity.getString(C0716R.string.facebook_app_id));
        } catch (RuntimeException e2) {
        }
        Uri uri = intent.getData();
        if (uri != null) {
            if (uri.getQueryParameter("utm_source") != null) {
                appLaunchUtils.tracker.setScreenName(Activities.home().getName());
                appLaunchUtils.tracker.send(((AppViewBuilder) new AppViewBuilder().setCampaignParamsFromUrl(uri.getPath())).build());
            } else if (uri.getQueryParameter("referrer") != null) {
                appLaunchUtils.tracker.setReferrer(uri.getQueryParameter("referrer"));
            }
            trackAffiliateClick(uri);
        }
        JitneyPublisher.publish(new Builder(appLaunchUtils.loggingContextFactory.newInstance()));
    }

    private void refreshDomains() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                AppLaunchUtils.this.mDomainStore.refreshDomainsFromServer();
            }
        }, 2000);
    }

    public void setupPush(Context context) {
        if (this.mAccountManager.isCurrentUserAuthorized()) {
            PushHelper.newInstance(context).setupPush();
        }
    }

    public void doOfflineSync(Context context) {
        if (this.mAccountManager.isCurrentUserAuthorized()) {
            context.startService(TripsReservationsSyncServiceIntents.intent(context));
            if (FeatureToggles.isGuestCheckInGuideOfflineEnabled()) {
                context.startService(new Intent(context, Services.checkInDataSync()));
            }
            if (FeatureToggles.atlantisEnabled()) {
                context.startService(new Intent(context, Services.atlantis()));
            }
        }
    }

    @Deprecated
    public void doAppStartupStuff(Context context) {
        refreshDomains();
        fetchCountryFromIP();
        if (Experiments.useDynamicStrings()) {
            schedulePullStrings(context);
        }
        ConcurrentUtil.deferParallel(AppLaunchUtils$$Lambda$2.lambdaFactory$(this, context));
        trackPartnerInstall(context);
    }

    /* access modifiers changed from: private */
    @Deprecated
    public void appStartStuffAlwaysDeferred(Context context) {
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, "impressions");
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, BaseAnalytics.addDeviceType(context, Strap.make().mo11639kv(BaseAnalytics.OPERATION, GeneralAnalytics.AppOpen)), true);
        trackGooglePlayInstalled(context);
        trackAccessibilityFeaturesEnabled(context);
        trackStoreInstall(context);
        trackDeviceScreen(context);
    }

    public void doAppStartupStuffDeferred(Context context) {
        ConcurrentUtil.deferParallel(AppLaunchUtils$$Lambda$3.lambdaFactory$(this, context, Experiments.useDynamicStrings()));
    }

    static /* synthetic */ void lambda$doAppStartupStuffDeferred$2(AppLaunchUtils appLaunchUtils, Context context, boolean useDynamicStrings) {
        appLaunchUtils.refreshDomains();
        appLaunchUtils.fetchCountryFromIP();
        appLaunchUtils.trackPartnerInstall(context);
        appLaunchUtils.appStartStuffAlwaysDeferred(context);
        if (useDynamicStrings) {
            appLaunchUtils.schedulePullStrings(context);
        }
    }

    private void schedulePullStrings(Context context) {
        new PullStringsScheduler(context, this.mPreferences.getStringPreferences()).pullStringsIfNeeded();
    }

    private void trackPartnerInstall(Context context) {
        if (this.mAffiliateInfo.getAffiliateCampaignData() == null) {
            new DTKPartnerTask(context).getTokenAsync();
            new SFRPartnerTask(context).getTokenAsync();
        }
    }

    private void fetchCountryFromIP() {
        CountriesRequest.forCountryOfIP().withListener((Observer) new SimpleRequestListener<CountriesResponse>() {
            public void onResponse(CountriesResponse response) {
                AppLaunchUtils.this.mPreferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_COUNTRY_OF_CURRENT_IP, response.getFirstCountryCode()).apply();
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    private void trackStoreInstall(Context context) {
        if (!this.mPreferences.getGlobalSharedPreferences().contains(AirbnbConstants.PREFS_INSTALLER_STORE)) {
            String installerPackage = ExternalAppUtils.getInstallerPackage(context);
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("installer_package", installerPackage));
            AirbnbEventLogger.track(GeneralAnalytics.AppOpen, BaseAnalytics.addDeviceType(context, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "app_first_launch").mo11639kv(AirbnbConstants.PREFS_INSTALL_REFERRER, this.mPreferences.getSharedPreferences().getString(AirbnbConstants.PREFS_INSTALL_REFERRER, "")).mo11639kv("installer_package", installerPackage).mo11639kv("china_install_tag", BuildHelper.chinaInstallTag())), true);
            this.mPreferences.getGlobalSharedPreferences().edit().putBoolean(AirbnbConstants.PREFS_INSTALLER_STORE, true).apply();
        }
    }

    private static void trackDeviceScreen(Context context) {
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, new Strap().mo11639kv("c1", MiscUtils.isTabletScreen(context) ? "tablet_device" : "phone_device"));
    }

    private static void trackGooglePlayInstalled(Context context) {
        AirbnbEventLogger.track(GeneralAnalytics.AppOpen, new Strap().mo11639kv("c1", MiscUtils.hasGooglePlayServices(context) ? "has_google_services" : "no_google_services"));
    }

    private static void trackAccessibilityFeaturesEnabled(Context context) {
        try {
            AccessibilityManager am = (AccessibilityManager) context.getSystemService("accessibility");
            AirbnbEventLogger.track(GeneralAnalytics.AppOpen, Strap.make().mo11640kv("is_accessibility_enabled", am.isEnabled()).mo11640kv("is_touch_exploration_enabled", am.isTouchExplorationEnabled()));
        } catch (RuntimeException e) {
        }
    }

    private static void trackAffiliateClick(Uri uri) {
        String affiliateId = uri.getQueryParameter("af");
        if (affiliateId != null) {
            String affiliateCampaign = uri.getQueryParameter("c");
            if (affiliateCampaign != null) {
                Strap clickInfoStrap = Strap.make();
                for (String queryKey : uri.getQueryParameterNames()) {
                    clickInfoStrap.mo11639kv(queryKey, uri.getQueryParameter(queryKey));
                }
                AffiliateClickRequest.logAffiliateRequest(affiliateId, affiliateCampaign, clickInfoStrap.toJsonString()).execute(NetworkUtil.singleFireExecutor());
            }
        }
    }
}
