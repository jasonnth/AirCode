package com.airbnb.android.core;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import com.airbnb.android.core.analytics.TimeSkewAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateData;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ClientSessionManager;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context.Builder;
import com.airbnb.jitney.event.logging.core.context.p025v2.MobileBuildType;
import com.airbnb.jitney.event.logging.core.context.p025v2.MobileContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.ScreenOrientation;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class LoggingContextFactory {
    private static final MobileBuildType BUILD_TYPE = getBuildType();
    private static final String DEVICE_TYPE = (Build.MANUFACTURER + "_" + Build.MODEL);
    private static final String PLATFORM_PHONE = "phone";
    private static final String PLATFORM_TABLET = "tablet";
    private static final String SOURCE = "android";
    private static final long VERSION_CODE = ((long) BuildHelper.versionCode());
    private final AirbnbAccountManager accountManager;
    private final AffiliateInfo affiliateInfo;
    private final AirbnbPreferences airbnbPreferences;
    private final Context androidContext;
    private final String androidId;
    private final String carrierName;
    private final ClientSessionManager clientSessionManager;
    private final DeviceInfo deviceInfo;
    private final String language = this.locale.getLanguage();
    private final Locale locale = Locale.getDefault();
    private final DisplayMetrics metrics = new DisplayMetrics();
    private final String platform;
    private final TimeSkewAnalytics timeSkewAnalytics;

    public LoggingContextFactory(Context androidContext2, DeviceInfo deviceInfo2, AirbnbAccountManager accountManager2, AffiliateInfo affiliateInfo2, ClientSessionManager clientSessionManager2, AirbnbPreferences airbnbPreferences2, TimeSkewAnalytics timeSkewAnalytics2) {
        this.androidContext = androidContext2;
        this.deviceInfo = deviceInfo2;
        this.accountManager = accountManager2;
        this.affiliateInfo = affiliateInfo2;
        this.clientSessionManager = clientSessionManager2;
        this.airbnbPreferences = airbnbPreferences2;
        this.timeSkewAnalytics = timeSkewAnalytics2;
        this.androidId = MiscUtils.getAndroidId(androidContext2);
        this.platform = MiscUtils.isTabletScreen(androidContext2) ? PLATFORM_TABLET : "phone";
        this.carrierName = MiscUtils.getTelephonyOperatorName(androidContext2);
    }

    public com.airbnb.jitney.event.logging.core.context.p025v2.Context newInstance() {
        return newInstance(this.accountManager.getCurrentUserId());
    }

    public com.airbnb.jitney.event.logging.core.context.p025v2.Context newInstanceAsUser(long originalUserId) {
        return newInstance(originalUserId);
    }

    private com.airbnb.jitney.event.logging.core.context.p025v2.Context newInstance(long userId) {
        Builder loggingContext = new Builder(Long.valueOf(Calendar.getInstance().getTimeInMillis()), "android", this.platform, this.deviceInfo.getUserAgent()).version(BuildHelper.versionName()).locale(this.locale.toString()).language(this.language).screen_width(Long.valueOf((long) (((float) this.metrics.widthPixels) / this.metrics.density))).screen_height(Long.valueOf((long) (((float) this.metrics.heightPixels) / this.metrics.density))).mobile(newMobileContext()).client_session_id(this.clientSessionManager.getClientSessionId()).extra_data(extraData());
        AffiliateData affiliateData = this.affiliateInfo.getAffiliateCampaignData();
        if (affiliateData != null) {
            loggingContext.campaign(affiliateData.campaign()).affiliate_id(String.valueOf(affiliateData.affiliateId()));
        }
        if (userId != -1) {
            loggingContext.user_id(Long.valueOf(userId));
        }
        return loggingContext.build();
    }

    private MobileContext newMobileContext() {
        MobileContext.Builder mobileContext = new MobileContext.Builder(DEVICE_TYPE, this.androidId, Long.valueOf(VERSION_CODE), getScreenOrientation(), NetworkUtil.getNetworkType(this.androidContext)).build_type(BUILD_TYPE).carrier_country(this.deviceInfo.getDeviceCountry());
        if (this.carrierName != null) {
            mobileContext.carrier_name(this.carrierName);
        }
        return mobileContext.build();
    }

    private static MobileBuildType getBuildType() {
        if (BuildHelper.isAlpha()) {
            return MobileBuildType.Alpha;
        }
        if (BuildHelper.isBeta()) {
            return MobileBuildType.Beta;
        }
        if (BuildHelper.isChina()) {
            return MobileBuildType.China;
        }
        if (BuildHelper.isQa()) {
            return MobileBuildType.QA;
        }
        if (BuildHelper.isDevelopmentBuild()) {
            return MobileBuildType.Debug;
        }
        return MobileBuildType.Release;
    }

    private ScreenOrientation getScreenOrientation() {
        switch (this.androidContext.getResources().getConfiguration().orientation) {
            case 1:
                return ScreenOrientation.Portrait;
            case 2:
                return ScreenOrientation.Landscape;
            default:
                return ScreenOrientation.Portrait;
        }
    }

    private Map<String, String> extraData() {
        return Strap.make().mo11639kv("appboy_user_id", this.airbnbPreferences.getSharedPreferences().getString(AirbnbConstants.PREFS_MPARTICLE_CURRENT_MPARTICLE_CUSTOMER_ID, "")).mix(this.timeSkewAnalytics.getCorrectedTimeForJitney());
    }
}
