package com.airbnb.android.core.analytics;

import android.app.Activity;
import org.json.JSONObject;
import p315io.branch.referral.Branch.BranchReferralInitListener;
import p315io.branch.referral.BranchError;

final /* synthetic */ class BranchAnalytics$$Lambda$1 implements BranchReferralInitListener {
    private final Activity arg$1;

    private BranchAnalytics$$Lambda$1(Activity activity) {
        this.arg$1 = activity;
    }

    public static BranchReferralInitListener lambdaFactory$(Activity activity) {
        return new BranchAnalytics$$Lambda$1(activity);
    }

    public void onInitFinished(JSONObject jSONObject, BranchError branchError) {
        BranchAnalytics.lambda$trackAppLaunch$0(this.arg$1, jSONObject, branchError);
    }
}
