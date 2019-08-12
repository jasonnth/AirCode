package com.airbnb.android.lib.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.UpdateUserRequest;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.businesstravel.BusinessTravelAutoEnrollFragment;
import p032rx.Observer;

public final class VerifyEmailActivity extends SolitAirActivity {
    final RequestListener<UserResponse> verifyEmailListener = new C0699RL().onResponse(VerifyEmailActivity$$Lambda$1.lambdaFactory$(this)).onError(VerifyEmailActivity$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(VerifyEmailActivity verifyEmailActivity, UserResponse response) {
        verifyEmailActivity.showLoader(false);
        if (verifyEmailActivity.isUserAutoEnrolledForBusinessTravel(response.user)) {
            verifyEmailActivity.showAutoEnrollFragment(response.user.getBtAutoEnrollCompanyName());
            return;
        }
        Toast.makeText(verifyEmailActivity, C0880R.string.verify_email_success, 0).show();
        verifyEmailActivity.finish();
    }

    static /* synthetic */ void lambda$new$1(VerifyEmailActivity verifyEmailActivity, AirRequestNetworkException e) {
        verifyEmailActivity.showLoader(false);
        Toast.makeText(verifyEmailActivity, C0880R.string.verify_email_failure, 0).show();
        verifyEmailActivity.finish();
    }

    private void showAutoEnrollFragment(String emailDomain) {
        showFragment(BusinessTravelAutoEnrollFragment.forEmailDomain(emailDomain), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, false);
    }

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        showLoader(true);
        UpdateUserRequest.forVerifyEmail(getIntent().getStringExtra("code")).withListener((Observer) this.verifyEmailListener).execute(this.requestManager);
    }

    private boolean isUserAutoEnrolledForBusinessTravel(User user) {
        String btAutoEnrollDisplayName = user.getBtAutoEnrollCompanyName();
        if (btAutoEnrollDisplayName == null || TextUtils.isEmpty(btAutoEnrollDisplayName)) {
            return false;
        }
        return true;
    }
}
