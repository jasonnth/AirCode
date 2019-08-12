package p315io.branch.referral;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import p005cn.jpush.android.JPushConstants;
import p315io.branch.referral.Defines.Jsonkey;

/* renamed from: io.branch.referral.InstallListener */
public class InstallListener extends BroadcastReceiver {
    private static IInstallReferrerEvents callback_ = null;
    private static String installID_ = "bnc_no_value";
    private static boolean isWaitingForReferrer;
    private static boolean unReportedReferrerAvailable;

    /* renamed from: io.branch.referral.InstallListener$IInstallReferrerEvents */
    interface IInstallReferrerEvents {
        void onInstallReferrerEventsFinished();
    }

    public static void captureInstallReferrer(long maxWaitTime) {
        if (unReportedReferrerAvailable) {
            reportInstallReferrer();
            return;
        }
        isWaitingForReferrer = true;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                InstallListener.reportInstallReferrer();
            }
        }, maxWaitTime);
    }

    public void onReceive(Context context, Intent intent) {
        String rawReferrerString = intent.getStringExtra("referrer");
        if (rawReferrerString != null) {
            try {
                String rawReferrerString2 = URLDecoder.decode(rawReferrerString, JPushConstants.ENCODING_UTF_8);
                HashMap<String, String> referrerMap = new HashMap<>();
                for (String referrerParam : rawReferrerString2.split("&")) {
                    String[] keyValue = referrerParam.split("=");
                    if (keyValue.length > 1) {
                        referrerMap.put(URLDecoder.decode(keyValue[0], JPushConstants.ENCODING_UTF_8), URLDecoder.decode(keyValue[1], JPushConstants.ENCODING_UTF_8));
                    }
                }
                PrefHelper prefHelper = PrefHelper.getInstance(context);
                if (referrerMap.containsKey(Jsonkey.LinkClickID.getKey())) {
                    installID_ = (String) referrerMap.get(Jsonkey.LinkClickID.getKey());
                    prefHelper.setLinkClickIdentifier(installID_);
                }
                if (referrerMap.containsKey(Jsonkey.IsFullAppConv.getKey()) && referrerMap.containsKey(Jsonkey.ReferringLink.getKey())) {
                    prefHelper.setIsFullAppConversion(Boolean.parseBoolean((String) referrerMap.get(Jsonkey.IsFullAppConv.getKey())));
                    prefHelper.setAppLink((String) referrerMap.get(Jsonkey.ReferringLink.getKey()));
                }
                if (referrerMap.containsKey(Jsonkey.GoogleSearchInstallReferrer.getKey())) {
                    prefHelper.setGoogleSearchInstallIdentifier((String) referrerMap.get(Jsonkey.GoogleSearchInstallReferrer.getKey()));
                    prefHelper.setGooglePlayReferrer(rawReferrerString2);
                }
                unReportedReferrerAvailable = true;
                if (isWaitingForReferrer) {
                    reportInstallReferrer();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                Log.w("BranchSDK", "Illegal characters in url encoded string");
            }
        }
    }

    public static String getInstallationID() {
        return installID_;
    }

    /* access modifiers changed from: private */
    public static void reportInstallReferrer() {
        if (callback_ != null) {
            callback_.onInstallReferrerEventsFinished();
            callback_ = null;
            unReportedReferrerAvailable = false;
        }
    }

    public static void setListener(IInstallReferrerEvents installReferrerFetch) {
        callback_ = installReferrerFetch;
    }
}
