package com.airbnb.android.host_referrals.activities;

import android.os.Bundle;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.SheetFlowActivity;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.core.requests.GetHostReferralInfoRequest;
import com.airbnb.android.core.requests.GetHostReferralsRequest;
import com.airbnb.android.core.responses.GetHostReferralInfoResponse;
import com.airbnb.android.core.responses.GetHostReferralsResponse;
import com.airbnb.android.host_referrals.C6405R;
import com.airbnb.android.host_referrals.fragments.HostReferralsFragment;
import com.airbnb.android.host_referrals.fragments.SentHostReferralsFragment;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class HostReferralsActivity extends SheetFlowActivity {
    final RequestListener<GetHostReferralInfoResponse> getHostReferralInfoResponseRequestListener = new C0699RL().onResponse(HostReferralsActivity$$Lambda$5.lambdaFactory$(this)).onError(HostReferralsActivity$$Lambda$6.lambdaFactory$(this)).build();
    final RequestListener<GetHostReferralsResponse> getHostReferralsResponseRequestListener = new C0699RL().onResponse(HostReferralsActivity$$Lambda$1.lambdaFactory$(this)).onError(HostReferralsActivity$$Lambda$4.lambdaFactory$(this)).build();
    @State
    HostReferralReferrerInfo referrerInfo;

    static /* synthetic */ void lambda$new$0(HostReferralsActivity hostReferralsActivity, GetHostReferralsResponse response) {
        hostReferralsActivity.stopLoader();
        hostReferralsActivity.showFragment(SentHostReferralsFragment.newInstance(new ArrayList(response.referrees), hostReferralsActivity.referrerInfo));
    }

    static /* synthetic */ void lambda$new$2(HostReferralsActivity hostReferralsActivity, GetHostReferralInfoResponse response) {
        hostReferralsActivity.stopLoader();
        hostReferralsActivity.referrerInfo = response.info;
        hostReferralsActivity.showFragment(HostReferralsFragment.newInstance(hostReferralsActivity.referrerInfo));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            startLoader();
            new GetHostReferralInfoRequest(this.accountManager.getCurrentUserId()).withListener((Observer) this.getHostReferralInfoResponseRequestListener).execute(this.requestManager);
        }
    }

    public SheetTheme getDefaultTheme() {
        return SheetTheme.WHITE;
    }

    public boolean useHomeAsBack() {
        return true;
    }

    public void onTravelCreditClicked() {
        startLoader();
        new GetHostReferralsRequest(this.accountManager.getCurrentUserId()).withListener((Observer) this.getHostReferralsResponseRequestListener).execute(this.requestManager);
    }

    public void onTermsClicked() {
        startActivity(WebViewIntentBuilder.newBuilder(this, getString(C6405R.string.tos_url_host_referrals)).title(C6405R.string.terms_and_conditions).toIntent());
    }
}
