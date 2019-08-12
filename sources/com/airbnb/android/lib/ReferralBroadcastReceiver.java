package com.airbnb.android.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import p315io.branch.referral.InstallListener;

public class ReferralBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = ReferralBroadcastReceiver.class.getSimpleName();
    AffiliateInfo mAffiliateInfo;
    AirbnbPreferences mPreferences;

    public void onReceive(Context context, Intent intent) {
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        try {
            String referrerParamsString = intent.getExtras().getString("referrer", "");
            if (!TextUtils.isEmpty(referrerParamsString)) {
                Strap queryParams = Strap.make();
                for (String pair : referrerParamsString.split("&")) {
                    String[] keyval = pair.split("=");
                    try {
                        queryParams.put(keyval[0], keyval[1]);
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
                String affiliateID = (String) queryParams.get("af");
                String affiliateCampaign = (String) queryParams.get("c");
                this.mAffiliateInfo.storeAffiliateParams(affiliateID, affiliateCampaign, (String) queryParams.get("local_af_click"));
                AirbnbEventLogger.track("install", Strap.make().mo11639kv("intent_key_referrer", referrerParamsString).mo11639kv("bev", (String) queryParams.get("bev")));
                String googleClickID = (String) queryParams.get("gclid");
                if (googleClickID != null) {
                    AirbnbEventLogger.track("affiliate_click", Strap.make().mo11639kv(AirbnbConstants.PREFS_AFFILIATE_ID, (String) SanitizeUtils.defaultIfNull(affiliateID, "-1")).mo11639kv("campaign", SanitizeUtils.emptyIfNull(affiliateCampaign)).mo11639kv("first_seen", "true").mo11639kv("is_brand", InternalLogger.EVENT_PARAM_EXTRAS_FALSE).mo11639kv("click_id", googleClickID).mo11639kv("platform_id", AppEventsConstants.EVENT_PARAM_VALUE_NO).mo11639kv("platform_name", "google"));
                }
                Log.i(TAG, "install_referrer: " + referrerParamsString);
                this.mPreferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_INSTALL_REFERRER, referrerParamsString).commit();
                saveMobileWebAuthCredentials((String) queryParams.get("token"), (String) queryParams.get("user_id"), (String) queryParams.get("name"), (String) queryParams.get("login"));
            }
        } catch (Exception e2) {
        }
        new CampaignTrackingReceiver().onReceive(context, intent);
        new InstallListener().onReceive(context, intent);
    }

    private void trackAutoAuthCredentialRetrieval(String userId, String userName, String enabledFlag) {
        RegistrationAnalytics.trackMowebAuthTokenRetrieval(userId, userName, enabledFlag);
    }

    private void saveMobileWebAuthCredentials(String token, String userId, String userFirstName, String enabled) {
        if (!TextUtils.isEmpty(enabled) && !TextUtils.isEmpty(token) && !TextUtils.isEmpty(userId) && !TextUtils.isEmpty(userFirstName)) {
            trackAutoAuthCredentialRetrieval(userId, userFirstName, enabled);
            this.mPreferences.getSharedPreferences().edit().putBoolean("login", Boolean.parseBoolean(enabled)).putString(AirbnbConstants.PREFS_MOBILE_WEB_TOKEN, token).putString(AirbnbConstants.PREFS_MOBILE_WEB_ID, userId).putString(AirbnbConstants.PREFS_MOBILE_WEB_NAME, userFirstName).commit();
        }
    }
}
