package p315io.branch.referral;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.miteksystems.misnap.params.MiSnapApiConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: io.branch.referral.PrefHelper */
public class PrefHelper {
    private static boolean BNC_Dev_Debug = false;
    private static boolean BNC_Logging = false;
    private static String Branch_Key = null;
    private static PrefHelper prefHelper_;
    private static JSONObject savedAnalyticsData_;
    private SharedPreferences appSharedPrefs_;
    private Context context_;
    private Editor prefsEditor_;
    private JSONObject requestMetadata;

    public PrefHelper() {
    }

    private PrefHelper(Context context) {
        this.appSharedPrefs_ = context.getSharedPreferences("branch_referral_shared_pref", 0);
        this.prefsEditor_ = this.appSharedPrefs_.edit();
        this.context_ = context;
        this.requestMetadata = new JSONObject();
    }

    public static PrefHelper getInstance(Context context) {
        if (prefHelper_ == null) {
            prefHelper_ = new PrefHelper(context);
        }
        return prefHelper_;
    }

    public String getAPIBaseUrl() {
        return "https://api.branch.io/";
    }

    public int getTimeout() {
        return getInteger("bnc_timeout", MiSnapApiConstants.IMAGE_HORIZONTAL_PIXELS_PARAM_MAX_VALUE);
    }

    public int getRetryCount() {
        return getInteger("bnc_retry_count", 3);
    }

    public int getRetryInterval() {
        return getInteger("bnc_retry_interval", 1000);
    }

    public void setAppVersion(String version) {
        setString("bnc_app_version", version);
    }

    public String getAppVersion() {
        return getString("bnc_app_version");
    }

    public boolean setBranchKey(String key) {
        Branch_Key = key;
        String currentBranchKey = getString("bnc_branch_key");
        if (key != null && currentBranchKey != null && currentBranchKey.equals(key)) {
            return false;
        }
        clearPrefOnBranchKeyChange();
        setString("bnc_branch_key", key);
        return true;
    }

    public String getBranchKey() {
        if (Branch_Key == null) {
            Branch_Key = getString("bnc_branch_key");
        }
        return Branch_Key;
    }

    public String readBranchKey(boolean isLive) {
        String branchKey = null;
        String metaDataKey = isLive ? "io.branch.sdk.BranchKey" : "io.branch.sdk.BranchKey.test";
        if (!isLive) {
            setExternDebug();
        }
        try {
            ApplicationInfo ai = this.context_.getPackageManager().getApplicationInfo(this.context_.getPackageName(), 128);
            if (ai.metaData != null) {
                branchKey = ai.metaData.getString(metaDataKey);
                if (branchKey == null && !isLive) {
                    branchKey = ai.metaData.getString("io.branch.sdk.BranchKey");
                }
            }
        } catch (NameNotFoundException e) {
        }
        if (TextUtils.isEmpty(branchKey)) {
            try {
                Resources resources = this.context_.getResources();
                branchKey = resources.getString(resources.getIdentifier(metaDataKey, "string", this.context_.getPackageName()));
            } catch (Exception e2) {
            }
        }
        if (branchKey == null) {
            return "bnc_no_value";
        }
        return branchKey;
    }

    public void setDeviceFingerPrintID(String device_fingerprint_id) {
        setString("bnc_device_fingerprint_id", device_fingerprint_id);
    }

    public String getDeviceFingerPrintID() {
        return getString("bnc_device_fingerprint_id");
    }

    public void setSessionID(String session_id) {
        setString("bnc_session_id", session_id);
    }

    public String getSessionID() {
        return getString("bnc_session_id");
    }

    public void setIdentityID(String identity_id) {
        setString("bnc_identity_id", identity_id);
    }

    public String getIdentityID() {
        return getString("bnc_identity_id");
    }

    public void setIdentity(String identity) {
        setString("bnc_identity", identity);
    }

    public String getIdentity() {
        return getString("bnc_identity");
    }

    public void setLinkClickID(String link_click_id) {
        setString("bnc_link_click_id", link_click_id);
    }

    public String getLinkClickID() {
        return getString("bnc_link_click_id");
    }

    public void setIsAppLinkTriggeredInit(Boolean isAppLinkTriggered) {
        setBool("bnc_triggered_by_fb_app_link", isAppLinkTriggered);
    }

    public boolean getIsAppLinkTriggeredInit() {
        return getBool("bnc_triggered_by_fb_app_link");
    }

    public void setExternalIntentUri(String uri) {
        setString("bnc_external_intent_uri", uri);
    }

    public String getExternalIntentUri() {
        return getString("bnc_external_intent_uri");
    }

    public void setExternalIntentExtra(String extras) {
        setString("bnc_external_intent_extra", extras);
    }

    public String getExternalIntentExtra() {
        return getString("bnc_external_intent_extra");
    }

    public void setLinkClickIdentifier(String identifier) {
        setString("bnc_link_click_identifier", identifier);
    }

    public String getLinkClickIdentifier() {
        return getString("bnc_link_click_identifier");
    }

    public void setGoogleSearchInstallIdentifier(String identifier) {
        setString("bnc_google_search_install_identifier", identifier);
    }

    public String getGoogleSearchInstallIdentifier() {
        return getString("bnc_google_search_install_identifier");
    }

    public void setGooglePlayReferrer(String referrer) {
        setString("bnc_google_play_install_referrer_extras", referrer);
    }

    public String getGooglePlayReferrer() {
        return getString("bnc_google_play_install_referrer_extras");
    }

    public void setAppLink(String appLinkUrl) {
        setString("bnc_app_link", appLinkUrl);
    }

    public String getAppLink() {
        return getString("bnc_app_link");
    }

    public void setIsFullAppConversion(boolean isFullAppConversion) {
        setBool("bnc_is_full_app_conversion", Boolean.valueOf(isFullAppConversion));
    }

    public boolean isFullAppConversion() {
        return getBool("bnc_is_full_app_conversion");
    }

    public void setPushIdentifier(String pushIdentifier) {
        setString("bnc_push_identifier", pushIdentifier);
    }

    public String getPushIdentifier() {
        return getString("bnc_push_identifier");
    }

    public String getSessionParams() {
        return getString("bnc_session_params");
    }

    public void setSessionParams(String params) {
        setString("bnc_session_params", params);
    }

    public String getInstallParams() {
        return getString("bnc_install_params");
    }

    public void setInstallParams(String params) {
        setString("bnc_install_params", params);
    }

    public void setInstallReferrerParams(String params) {
        setString("bnc_install_referrer", params);
    }

    public void setUserURL(String user_url) {
        setString("bnc_user_url", user_url);
    }

    public String getUserURL() {
        return getString("bnc_user_url");
    }

    public int getIsReferrable() {
        return getInteger("bnc_is_referrable");
    }

    public void setIsReferrable() {
        setInteger("bnc_is_referrable", 1);
    }

    public void clearIsReferrable() {
        setInteger("bnc_is_referrable", 0);
    }

    public void clearUserValues() {
        Iterator it = getBuckets().iterator();
        while (it.hasNext()) {
            setCreditCount((String) it.next(), 0);
        }
        setBuckets(new ArrayList());
        Iterator it2 = getActions().iterator();
        while (it2.hasNext()) {
            String action = (String) it2.next();
            setActionTotalCount(action, 0);
            setActionUniqueCount(action, 0);
        }
        setActions(new ArrayList());
    }

    private ArrayList<String> getBuckets() {
        String bucketList = getString("bnc_buckets");
        if (bucketList.equals("bnc_no_value")) {
            return new ArrayList<>();
        }
        return deserializeString(bucketList);
    }

    private void setBuckets(ArrayList<String> buckets) {
        if (buckets.size() == 0) {
            setString("bnc_buckets", "bnc_no_value");
        } else {
            setString("bnc_buckets", serializeArrayList(buckets));
        }
    }

    public void setCreditCount(String bucket, int count) {
        ArrayList<String> buckets = getBuckets();
        if (!buckets.contains(bucket)) {
            buckets.add(bucket);
            setBuckets(buckets);
        }
        setInteger("bnc_credit_base_" + bucket, count);
    }

    public int getCreditCount(String bucket) {
        return getInteger("bnc_credit_base_" + bucket);
    }

    private ArrayList<String> getActions() {
        String actionList = getString("bnc_actions");
        if (actionList.equals("bnc_no_value")) {
            return new ArrayList<>();
        }
        return deserializeString(actionList);
    }

    private void setActions(ArrayList<String> actions) {
        if (actions.size() == 0) {
            setString("bnc_actions", "bnc_no_value");
        } else {
            setString("bnc_actions", serializeArrayList(actions));
        }
    }

    public void setActionTotalCount(String action, int count) {
        ArrayList<String> actions = getActions();
        if (!actions.contains(action)) {
            actions.add(action);
            setActions(actions);
        }
        setInteger("bnc_total_base_" + action, count);
    }

    public void setActionUniqueCount(String action, int count) {
        setInteger("bnc_balance_base_" + action, count);
    }

    private String serializeArrayList(ArrayList<String> strings) {
        String retString = "";
        Iterator it = strings.iterator();
        while (it.hasNext()) {
            retString = retString + ((String) it.next()) + ",";
        }
        return retString.substring(0, retString.length() - 1);
    }

    private ArrayList<String> deserializeString(String list) {
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, list.split(","));
        return strings;
    }

    public int getInteger(String key) {
        return getInteger(key, 0);
    }

    public int getInteger(String key, int defaultValue) {
        return prefHelper_.appSharedPrefs_.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return prefHelper_.appSharedPrefs_.getLong(key, 0);
    }

    public String getString(String key) {
        return prefHelper_.appSharedPrefs_.getString(key, "bnc_no_value");
    }

    public boolean getBool(String key) {
        return prefHelper_.appSharedPrefs_.getBoolean(key, false);
    }

    public void setInteger(String key, int value) {
        prefHelper_.prefsEditor_.putInt(key, value);
        prefHelper_.prefsEditor_.apply();
    }

    public void setLong(String key, long value) {
        prefHelper_.prefsEditor_.putLong(key, value);
        prefHelper_.prefsEditor_.apply();
    }

    public void setString(String key, String value) {
        prefHelper_.prefsEditor_.putString(key, value);
        prefHelper_.prefsEditor_.apply();
    }

    public void setBool(String key, Boolean value) {
        prefHelper_.prefsEditor_.putBoolean(key, value.booleanValue());
        prefHelper_.prefsEditor_.apply();
    }

    public void updateBranchViewUsageCount(String branchViewId) {
        setInteger("bnc_branch_view_use_" + branchViewId, getBranchViewUsageCount(branchViewId) + 1);
    }

    public int getBranchViewUsageCount(String branchViewId) {
        return getInteger("bnc_branch_view_use_" + branchViewId, 0);
    }

    public JSONObject getBranchAnalyticsData() {
        if (savedAnalyticsData_ != null) {
            return savedAnalyticsData_;
        }
        String savedAnalyticsData = getString("bnc_branch_analytical_data");
        JSONObject analyticsDataObject = new JSONObject();
        if (TextUtils.isEmpty(savedAnalyticsData) || savedAnalyticsData.equals("bnc_no_value")) {
            return analyticsDataObject;
        }
        try {
            return new JSONObject(savedAnalyticsData);
        } catch (JSONException e) {
            return analyticsDataObject;
        }
    }

    public void clearBranchAnalyticsData() {
        savedAnalyticsData_ = null;
        setString("bnc_branch_analytical_data", "");
    }

    public void saveBranchAnalyticsData(JSONObject analyticsData) {
        JSONArray viewDataArray;
        String sessionID = getSessionID();
        if (!sessionID.equals("bnc_no_value")) {
            if (savedAnalyticsData_ == null) {
                savedAnalyticsData_ = getBranchAnalyticsData();
            }
            try {
                if (savedAnalyticsData_.has(sessionID)) {
                    viewDataArray = savedAnalyticsData_.getJSONArray(sessionID);
                } else {
                    viewDataArray = new JSONArray();
                    savedAnalyticsData_.put(sessionID, viewDataArray);
                }
                viewDataArray.put(analyticsData);
                setString("bnc_branch_analytical_data", savedAnalyticsData_.toString());
            } catch (JSONException e) {
            }
        }
    }

    public void saveLastStrongMatchTime(long strongMatchCheckTime) {
        setLong("bnc_branch_strong_match_time", strongMatchCheckTime);
    }

    public long getLastStrongMatchTime() {
        return getLong("bnc_branch_strong_match_time");
    }

    private void clearPrefOnBranchKeyChange() {
        String linkClickID = getLinkClickID();
        String linkClickIdentifier = getLinkClickIdentifier();
        String appLink = getAppLink();
        String pushIdentifier = getPushIdentifier();
        this.prefsEditor_.clear();
        setLinkClickID(linkClickID);
        setLinkClickIdentifier(linkClickIdentifier);
        setAppLink(appLink);
        setPushIdentifier(pushIdentifier);
        prefHelper_.prefsEditor_.apply();
    }

    public void setExternDebug() {
        BNC_Dev_Debug = true;
    }

    public boolean getExternDebug() {
        return BNC_Dev_Debug;
    }

    public void setLogging(boolean logging) {
        BNC_Logging = logging;
    }

    public JSONObject getRequestMetadata() {
        return this.requestMetadata;
    }

    public void log(String tag, String message) {
        if (BNC_Dev_Debug || BNC_Logging) {
            Log.i(tag, message);
        }
    }

    public static void Debug(String tag, String message) {
        if (prefHelper_ != null) {
            prefHelper_.log(tag, message);
        } else if (BNC_Dev_Debug || BNC_Logging) {
            Log.i(tag, message);
        }
    }
}
