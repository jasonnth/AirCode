package com.airbnb.android.identity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics.CaptureMode;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.requests.AccountVerificationSelfiePostRequest;
import com.airbnb.android.core.responses.AccountVerificationSelfiePostResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import icepick.State;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import p032rx.Observer;

public class IdentitySelfieReviewFragment extends BaseAccountVerificationFragment implements IdentityLoaderFragment {
    private static final int RC_RN_SELFIE_REVIEW = 900;
    private static final int RESULT_CODE_RETAKE_SELFIE = 100;
    public final RequestListener<AccountVerificationSelfiePostResponse> airbnbSelfiePostResponseListener = new C0699RL().onError(IdentitySelfieReviewFragment$$Lambda$1.lambdaFactory$(this)).onComplete(IdentitySelfieReviewFragment$$Lambda$2.lambdaFactory$(this)).build();
    public final RequestListener<AccountVerificationSelfiePostResponse> misnapSelfiePostResponseListener = new RequestListener<AccountVerificationSelfiePostResponse>() {
        public void onResponse(AccountVerificationSelfiePostResponse data) {
            IdentitySelfieReviewFragment.this.showLoader(false);
            IdentitySelfieReviewFragment.this.controller.onStepFinished(AccountVerificationStep.PhotoReview, false);
            AccountVerificationAnalytics.trackSelfieUpload(true, null, null, IdentitySelfieReviewFragment.this.getVerificationFlow(), System.currentTimeMillis() - IdentitySelfieReviewFragment.this.uploadStartTime);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            IdentitySelfieReviewFragment.this.showLoader(false);
            NetworkUtil.toastNetworkError(IdentitySelfieReviewFragment.this.getContext(), (NetworkException) e);
            AccountVerificationAnalytics.trackSelfieUpload(true, Integer.valueOf(e.statusCode()), e.getMessage(), IdentitySelfieReviewFragment.this.getVerificationFlow(), System.currentTimeMillis() - IdentitySelfieReviewFragment.this.uploadStartTime);
        }
    };
    @State
    ArrayList<String> selfiePhotoFilePaths = new ArrayList<>();
    @State
    int uploadCount;
    @State
    long uploadStartTime;

    static /* synthetic */ void lambda$new$0(IdentitySelfieReviewFragment identitySelfieReviewFragment, AirRequestNetworkException e) {
        identitySelfieReviewFragment.showLoader(false);
        NetworkUtil.toastNetworkError(identitySelfieReviewFragment.getContext(), (NetworkException) e);
        AccountVerificationAnalytics.trackSelfieUpload(true, Integer.valueOf(e.statusCode()), e.getMessage(), identitySelfieReviewFragment.getVerificationFlow(), System.currentTimeMillis() - identitySelfieReviewFragment.uploadStartTime);
    }

    static /* synthetic */ void lambda$new$1(IdentitySelfieReviewFragment identitySelfieReviewFragment, Boolean success) {
        if (success.booleanValue()) {
            identitySelfieReviewFragment.uploadCount++;
            if (identitySelfieReviewFragment.uploadCount == identitySelfieReviewFragment.selfiePhotoFilePaths.size()) {
                identitySelfieReviewFragment.controller.onStepFinished(AccountVerificationStep.PhotoReview, true);
            }
            AccountVerificationAnalytics.trackSelfieUpload(true, null, null, identitySelfieReviewFragment.getVerificationFlow(), System.currentTimeMillis() - identitySelfieReviewFragment.uploadStartTime);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.selfiePhotoFilePaths = new ArrayList<>(this.controller.getSelfieFilePaths());
        }
        show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_identity_loader, container, false);
        bindViews(view);
        return view;
    }

    public void show() {
        startActivityForResult(ReactNativeIntents.intentForVerificationSelfieReview(getContext(), Uri.fromFile(new File((String) this.selfiePhotoFilePaths.get(this.selfiePhotoFilePaths.size() - 1))).toString()), RC_RN_SELFIE_REVIEW);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RC_RN_SELFIE_REVIEW) {
            return;
        }
        if (resultCode == -1) {
            onConfirmClick();
        } else if (resultCode == 0 || resultCode == 100) {
            this.controller.showPreviousStep();
        }
    }

    /* access modifiers changed from: 0000 */
    public void onConfirmClick() {
        CaptureMode captureMode = FeatureToggles.useAirbnbSelfie() ? CaptureMode.Airbnb : CaptureMode.Mitek;
        if (ListUtils.isEmpty((Collection<?>) this.selfiePhotoFilePaths) || TextUtils.isEmpty((CharSequence) this.selfiePhotoFilePaths.get(this.selfiePhotoFilePaths.size() - 1))) {
            this.controller.onStepFinished(AccountVerificationStep.PhotoReview, true);
            return;
        }
        this.uploadCount = 0;
        this.controller.getIdentityJitneyLogger().logSubmitted(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, null);
        if (captureMode == CaptureMode.Mitek) {
            this.uploadStartTime = System.currentTimeMillis();
            new AccountVerificationSelfiePostRequest(new File((String) this.selfiePhotoFilePaths.get(0)), captureMode.name()).withListener((Observer) this.misnapSelfiePostResponseListener).execute(this.requestManager);
        } else if (captureMode == CaptureMode.Airbnb) {
            Iterator it = this.selfiePhotoFilePaths.iterator();
            while (it.hasNext()) {
                String selfieFilePath = (String) it.next();
                this.requestManager.executeWithTag(new AccountVerificationSelfiePostRequest(new File(selfieFilePath), captureMode.name()).withListener((Observer) this.airbnbSelfiePostResponseListener), selfieFilePath);
            }
        }
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationPhotoReview;
    }
}
