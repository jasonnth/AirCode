package com.airbnb.android.core.data;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;

public class AffiliateInfo {
    private static final long CAMPAIGN_VALID_PERIOD = 2592000000L;
    public static final String LOCAL_AF_CLICK_KEY = "local_af_click";
    private static final String TAG = AffiliateInfo.class.getSimpleName();
    private final SharedPreferences mSharedPreferences;

    public AffiliateInfo(AirbnbPreferences preferences) {
        this.mSharedPreferences = preferences.getSharedPreferences();
    }

    public void storeAffiliateParams(Uri intentData) {
        storeAffiliateParams(intentData.getQueryParameter("af"), intentData.getQueryParameter("c"), intentData.getQueryParameter("local_af_click"));
    }

    public void storeAffiliateParams(Bundle parameters) {
        storeAffiliateParams(parameters.getString("af"), parameters.getString("c"), parameters.getString("local_af_click"));
    }

    public void storeAffiliateParams(String affiliateId, String campaign, String localAfClick) {
        if (TextUtils.isEmpty(affiliateId)) {
            storeAffiliateParams(-1, campaign, localAfClick);
        } else if (isValidAffiliateInfo(campaign, affiliateId, localAfClick)) {
            storeAffiliateParams(Integer.parseInt(affiliateId), campaign, localAfClick);
        }
    }

    public void storeAffiliateParams(int affiliate, String campaign, String localAfClick) {
        if (isValidAffiliateInfo(campaign, affiliate, localAfClick)) {
            String decodedCampaign = Uri.decode(campaign);
            String decodedLocalAfClick = Uri.decode(localAfClick);
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("page", "affiliate_info").mo11637kv("affiliate", affiliate).mo11639kv("campaign", campaign).mo11639kv("decoded_campaign", decodedCampaign).mo11639kv("local_af_click", localAfClick).mo11639kv("decoded_local_af_click", decodedLocalAfClick).mo11639kv("stack_trace", MiscUtils.getPrettyStackTrace()));
            this.mSharedPreferences.edit().putInt(AirbnbConstants.PREFS_AFFILIATE_ID, affiliate).putString(AirbnbConstants.PREFS_AFFILIATE_CAMPAIGN, decodedCampaign).putString("local_af_click", decodedLocalAfClick).putLong(AirbnbConstants.PREFS_AFFILIATE_CAMPAIGN_DATE, System.currentTimeMillis()).apply();
        }
    }

    public AffiliateData getAffiliateCampaignData() {
        int id;
        long campaignTimestamp = this.mSharedPreferences.getLong(AirbnbConstants.PREFS_AFFILIATE_CAMPAIGN_DATE, 0);
        if (campaignTimestamp <= 0) {
            return null;
        }
        if (!(System.currentTimeMillis() <= CAMPAIGN_VALID_PERIOD + campaignTimestamp)) {
            clearCampaignData();
            return null;
        }
        String campaign = this.mSharedPreferences.getString(AirbnbConstants.PREFS_AFFILIATE_CAMPAIGN, null);
        String localAfClick = this.mSharedPreferences.getString("local_af_click", null);
        try {
            id = this.mSharedPreferences.getInt(AirbnbConstants.PREFS_AFFILIATE_ID, -1);
        } catch (ClassCastException e) {
            String strId = this.mSharedPreferences.getString(AirbnbConstants.PREFS_AFFILIATE_ID, "");
            try {
                id = Integer.parseInt(strId);
            } catch (NumberFormatException e2) {
                C0715L.m1198w(TAG, "Failed to read affiliate ID from shared preferences. Expected an integer, got " + strId);
                id = -1;
            }
        }
        if (isValidAffiliateInfo(campaign, id, localAfClick)) {
            return AffiliateData.create(campaign, id, localAfClick);
        }
        return null;
    }

    private void clearCampaignData() {
        this.mSharedPreferences.edit().remove(AirbnbConstants.PREFS_AFFILIATE_CAMPAIGN_DATE).remove(AirbnbConstants.PREFS_AFFILIATE_CAMPAIGN).remove(AirbnbConstants.PREFS_AFFILIATE_ID).apply();
    }

    private static boolean isValidAffiliateInfo(String campaign, String affiliateId, String localAfClick) {
        try {
            return isValidAffiliateInfo(campaign, Integer.parseInt(affiliateId), localAfClick);
        } catch (NumberFormatException e) {
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("page", "affiliate_info").mo11639kv("affiliate_not_int", affiliateId));
            return false;
        }
    }

    private static boolean isValidAffiliateInfo(String campaign, int campaginId, String localAfClick) {
        return (!TextUtils.isEmpty(campaign) && campaginId > 0) || !TextUtils.isEmpty(localAfClick);
    }
}
