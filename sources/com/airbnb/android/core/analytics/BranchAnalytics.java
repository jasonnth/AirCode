package com.airbnb.android.core.analytics;

import android.app.Activity;
import android.content.Context;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import org.json.JSONException;
import org.json.JSONObject;
import p315io.branch.referral.Branch;
import p315io.branch.referral.BranchError;
import p315io.branch.referral.Defines.Jsonkey;

public class BranchAnalytics {
    private static Branch init(Context context) {
        Branch.disableDeviceIDFetch(Boolean.valueOf(true));
        return Branch.getAutoInstance(context);
    }

    public static void trackAppLaunch(Activity activity) {
        if (!isKillSwitched()) {
            Branch branch = Branch.getInstance();
            if (branch == null) {
                branch = init(CoreApplication.appContext());
            }
            if (branch == null) {
                BugsnagWrapper.notify(new Throwable("Branch instance is null"));
            } else {
                branch.initSession(BranchAnalytics$$Lambda$1.lambdaFactory$(activity), activity.getIntent().getData(), activity);
            }
        }
    }

    static /* synthetic */ void lambda$trackAppLaunch$0(Activity activity, JSONObject referringParams, BranchError error) {
        String eventName;
        if (error == null) {
            try {
                if (!referringParams.has(Jsonkey.IsFirstSession.getKey()) || !referringParams.getBoolean(Jsonkey.IsFirstSession.getKey())) {
                    eventName = "tracked_click_branch";
                } else {
                    eventName = "tracked_install_branch";
                }
                AirbnbEventLogger.track(eventName, MiscUtils.jsonObjectToMap(activity, referringParams));
            } catch (JSONException e) {
                BugsnagWrapper.notify((Throwable) e);
            }
        }
    }

    private static boolean isKillSwitched() {
        return Trebuchet.launch(TrebuchetKeys.DISABLE_BRANCH_TREBUCHET, false);
    }
}
