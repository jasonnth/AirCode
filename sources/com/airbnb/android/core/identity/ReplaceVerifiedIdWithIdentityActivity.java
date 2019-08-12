package com.airbnb.android.core.identity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import p032rx.Observer;

public class ReplaceVerifiedIdWithIdentityActivity extends AirActivity {
    private static final String EXTRA_USER_ID = "extra_user_id";
    private static final String EXTRA_VERIFICATION_FLOW = "extra_verification_flow";
    private static final int RESULT_CODE_VERIFIED_ID_OR_IDENTITY = 100;
    IdentityJitneyLogger identityJitneyLogger;
    @BindView
    LoaderFrame mLoaderFrame;
    final RequestListener<UserResponse> userRequestListener = new C0699RL().onResponse(ReplaceVerifiedIdWithIdentityActivity$$Lambda$1.lambdaFactory$(this)).onError(ReplaceVerifiedIdWithIdentityActivity$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ReplaceVerifiedIdWithIdentityActivity replaceVerifiedIdWithIdentityActivity, UserResponse response) {
        replaceVerifiedIdWithIdentityActivity.mLoaderFrame.finish();
        replaceVerifiedIdWithIdentityActivity.startActivityForResult(IdentityVerificationUtil.getIntentForVerifiedIdOrIdentity(replaceVerifiedIdWithIdentityActivity.identityJitneyLogger, replaceVerifiedIdWithIdentityActivity, response.user, (VerificationFlow) replaceVerifiedIdWithIdentityActivity.getIntent().getSerializableExtra("extra_verification_flow")), 100);
        AccountVerificationAnalytics.trackWebIntentDeepLink(FeatureToggles.replaceVerifiedIdWithIdentity(response.user));
    }

    static /* synthetic */ void lambda$new$1(ReplaceVerifiedIdWithIdentityActivity replaceVerifiedIdWithIdentityActivity, AirRequestNetworkException e) {
        replaceVerifiedIdWithIdentityActivity.mLoaderFrame.finish();
        NetworkUtil.toastGenericNetworkError(replaceVerifiedIdWithIdentityActivity);
    }

    public static Intent intent(Context context, long userId, VerificationFlow flow) {
        Intent intent = new Intent(context, ReplaceVerifiedIdWithIdentityActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        intent.putExtra("extra_verification_flow", flow);
        return intent;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                finish();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CoreGraph) CoreApplication.instance(this).component()).inject(this);
        setContentView(C0716R.layout.activity_replace_verified_id_with_identity);
        ButterKnife.bind((Activity) this);
        this.mLoaderFrame.startAnimation();
        UserRequest.newRequestForVerifications(getIntent().getLongExtra(EXTRA_USER_ID, 0)).withListener((Observer) this.userRequestListener).execute(this.requestManager);
    }
}
