package com.airbnb.android.referrals;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.models.GrayUser;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.models.Referree;
import com.airbnb.android.core.requests.AssociatedGrayUsersRequest;
import com.airbnb.android.core.requests.ReferralRequest;
import com.airbnb.android.core.requests.ReferralStatusForMobileRequest;
import com.airbnb.android.core.responses.AssociatedGrayUsersResponse;
import com.airbnb.android.core.responses.GetUserReferralResponse;
import com.airbnb.android.core.responses.ReferralStatusForMobileResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.referrals.analytics.ReferralsAnalytics;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.erf.Experiments;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import p032rx.Observer;

public class ReferralsActivity extends AirActivity {
    @State
    ArrayList<Referree> earnedReferrees;
    @State
    String entryPoint;
    public final RequestListener<AssociatedGrayUsersResponse> getAssociatedGrayUsersListener = new C0699RL().onResponse(ReferralsActivity$$Lambda$1.lambdaFactory$(this)).onError(ReferralsActivity$$Lambda$4.lambdaFactory$(this)).onComplete(ReferralsActivity$$Lambda$5.lambdaFactory$(this)).build();
    final RequestListener<ReferralStatusForMobileResponse> getReferralStatusListener = new C0699RL().onResponse(ReferralsActivity$$Lambda$6.lambdaFactory$(this)).onError(ReferralsActivity$$Lambda$7.lambdaFactory$(this)).build();
    final RequestListener<GetUserReferralResponse> getUserReferralListener = new C0699RL().onResponse(ReferralsActivity$$Lambda$8.lambdaFactory$(this)).onError(ReferralsActivity$$Lambda$9.lambdaFactory$(this)).build();
    /* access modifiers changed from: 0000 */
    @State
    public ArrayList<GrayUser> grayUsers;
    @BindView
    LoaderFrame loader;
    @State
    ArrayList<Referree> pendingReferrees;
    @State
    ReferralStatusForMobile referralStatus;
    @BindView
    ViewGroup root;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1532R.layout.activity_referrals);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.entryPoint = getIntent().getStringExtra(ReferralsIntents.ARG_ENTRY_POINT);
            ReferralsAnalytics.trackLandingPageImpression(getIntent().getStringExtra(ReferralsIntents.ARG_ENTRY_POINT));
            startLoader();
            ReferralStatusForMobileRequest.newInstance(this.accountManager.getCurrentUserId()).withListener((Observer) this.getReferralStatusListener).execute(this.requestManager);
            AssociatedGrayUsersRequest.newInstance(this.accountManager.getCurrentUserId()).withListener((Observer) this.getAssociatedGrayUsersListener).execute(this.requestManager);
        }
    }

    public void showFragment(Fragment frag) {
        stopLoader();
        super.showFragment(frag, C1532R.C1534id.content, FragmentTransitionType.SlideInFromSide, true);
    }

    /* access modifiers changed from: private */
    public void onDataLoaded() {
        String str = this.entryPoint;
        char c = 65535;
        switch (str.hashCode()) {
            case -1417464935:
                if (str.equals(ReferralsIntents.ENTRY_POINT_AIRNAV)) {
                    c = 1;
                    break;
                }
                break;
            case 644573143:
                if (str.equals(ReferralsIntents.ENTRY_POINT_POST_REVIEW)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (!ListUtils.isEmpty((Collection<?>) this.grayUsers) && Experiments.shouldShowPostReviewOneClickReferrals() && this.referralStatus != null) {
                    showFragment(ReferralsOneClickFragment.newInstance(this.referralStatus, this.grayUsers));
                    return;
                } else if (this.grayUsers != null && this.referralStatus != null) {
                    showFragment(ReferralsFragment.newInstance(this.referralStatus, this.entryPoint));
                    return;
                } else {
                    return;
                }
            default:
                if (this.referralStatus != null) {
                    showFragment(ReferralsFragment.newInstance(this.referralStatus, this.entryPoint));
                    return;
                }
                return;
        }
    }

    static /* synthetic */ void lambda$new$3(ReferralsActivity referralsActivity, ReferralStatusForMobileResponse response) {
        referralsActivity.referralStatus = response.referralStatus;
        referralsActivity.onDataLoaded();
    }

    static /* synthetic */ void lambda$new$4(ReferralsActivity referralsActivity, AirRequestNetworkException error) {
        NetworkUtil.toastGenericNetworkError(referralsActivity);
        referralsActivity.finish();
    }

    static /* synthetic */ void lambda$new$5(ReferralsActivity referralsActivity, GetUserReferralResponse data) {
        referralsActivity.pendingReferrees = new ArrayList<>();
        referralsActivity.earnedReferrees = new ArrayList<>();
        for (Referree referree : data.referrees) {
            if (!referree.hasEmailBlocked()) {
                if (referree.hasBeenInvited() || referree.hasJoined()) {
                    referralsActivity.pendingReferrees.add(referree);
                } else if (referree.hasTraveled() || referree.hasHosted()) {
                    referralsActivity.earnedReferrees.add(referree);
                }
            }
        }
        referralsActivity.showFragment(SentReferralsFragment.newInstance(referralsActivity.referralStatus, referralsActivity.pendingReferrees, referralsActivity.earnedReferrees));
    }

    static /* synthetic */ void lambda$new$6(ReferralsActivity referralsActivity, AirRequestNetworkException e) {
        referralsActivity.stopLoader();
        NetworkUtil.tryShowErrorWithSnackbar(referralsActivity.root, e);
    }

    public void onSentReferralsClicked() {
        startLoader();
        new ReferralRequest(this.accountManager.getCurrentUserId()).withListener((Observer) this.getUserReferralListener).execute(this.requestManager);
    }

    private void startLoader() {
        this.loader.startAnimation();
    }

    private void stopLoader() {
        this.loader.finishImmediate();
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
