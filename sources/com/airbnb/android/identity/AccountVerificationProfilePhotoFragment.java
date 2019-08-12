package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.identity.ChooseProfilePhotoController;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.requests.AccountVerificationsRequest;
import com.airbnb.android.core.requests.SetProfilePhotoRequest;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.core.responses.UserWrapperResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.identity.AccountVerificationProfilePhoto.ProfilePhotoState;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.google.common.collect.FluentIterable;
import java.io.File;
import p032rx.Observer;

public class AccountVerificationProfilePhotoFragment extends BaseAccountVerificationFragment implements AccountVerificationProfilePhotoListener {
    private static final String CUSTOM_VIEW_PARCEL_KEY = "customView";
    private AccountVerificationProfilePhoto accountVerificationProfilePhotoView;
    ChooseProfilePhotoController chooseProfilePhotoController;
    final RequestListener<UserWrapperResponse> uploadPhotoRequestListener = new C0699RL().onResponse(AccountVerificationProfilePhotoFragment$$Lambda$1.lambdaFactory$(this)).onError(AccountVerificationProfilePhotoFragment$$Lambda$2.lambdaFactory$(this)).build();
    final RequestListener<AccountVerificationsResponse> verificationsRequestListener = new C0699RL().onResponse(AccountVerificationProfilePhotoFragment$$Lambda$3.lambdaFactory$(this)).onError(AccountVerificationProfilePhotoFragment$$Lambda$4.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment, UserWrapperResponse data) {
        AccountVerificationAnalytics.trackRequestSuccess(accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.getAnalyticsTag(), "upload_photo");
        accountVerificationProfilePhotoFragment.controller.getIdentityJitneyLogger().logApproveReject(accountVerificationProfilePhotoFragment.getVerificationFlow(), accountVerificationProfilePhotoFragment.controller.getUser(), IdentityVerificationType.PHOTO_WITH_FACE, Page.provide_photo_upload, null, true);
        accountVerificationProfilePhotoFragment.showLoader(false);
        if (accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.getProfilePhotoState().isError()) {
            accountVerificationProfilePhotoFragment.finish();
            return;
        }
        accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.setProfilePhotoUrl(data.user.getPictureUrl());
        accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.setState(ProfilePhotoState.Success);
    }

    static /* synthetic */ void lambda$new$1(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment, AirRequestNetworkException e) {
        AccountVerificationAnalytics.trackRequestFailure(accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.getAnalyticsTag(), "upload_photo");
        accountVerificationProfilePhotoFragment.controller.getIdentityJitneyLogger().logApproveReject(accountVerificationProfilePhotoFragment.getVerificationFlow(), accountVerificationProfilePhotoFragment.controller.getUser(), IdentityVerificationType.PHOTO_WITH_FACE, Page.provide_photo_upload, null, false);
        accountVerificationProfilePhotoFragment.showLoader(false);
        accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.setState(ProfilePhotoState.Error);
    }

    static /* synthetic */ void lambda$new$3(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment, AccountVerificationsResponse response) {
        accountVerificationProfilePhotoFragment.showLoader(false);
        AccountVerification targetVerification = (AccountVerification) FluentIterable.from((Iterable<E>) response.accountActivationVerifications).firstMatch(AccountVerificationProfilePhotoFragment$$Lambda$5.lambdaFactory$()).orNull();
        accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.setProfilePhotoUrl(accountVerificationProfilePhotoFragment.mAccountManager.getCurrentUser().getPictureUrl());
        accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.setState(targetVerification == null ? ProfilePhotoState.Success : ProfilePhotoState.ErrorForCurrentPhoto);
    }

    static /* synthetic */ boolean lambda$null$2(AccountVerification verification) {
        return verification.getType().equals(AccountVerification.PHOTO) && !verification.isComplete();
    }

    static /* synthetic */ void lambda$new$4(AccountVerificationProfilePhotoFragment accountVerificationProfilePhotoFragment, AirRequestNetworkException e) {
        AccountVerificationAnalytics.trackRequestFailure(accountVerificationProfilePhotoFragment.accountVerificationProfilePhotoView.getAnalyticsTag(), "get_verifications");
        accountVerificationProfilePhotoFragment.showLoader(false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((IdentityGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.accountVerificationProfilePhotoView = new AccountVerificationProfilePhoto(getContext());
        this.accountVerificationProfilePhotoView.setData(this, this, this.chooseProfilePhotoController, this.navigationAnalytics, this.controller.getIdentityJitneyLogger(), getVerificationFlow(), this.controller.getUser());
        if (savedInstanceState != null && savedInstanceState.containsKey(CUSTOM_VIEW_PARCEL_KEY)) {
            this.accountVerificationProfilePhotoView.onRestoreInstanceState(savedInstanceState.getParcelable(CUSTOM_VIEW_PARCEL_KEY));
        } else if (this.mAccountManager.getCurrentUser().hasProfilePic()) {
            showLoader(true);
            AccountVerificationsRequest.forFlow(getVerificationFlow()).withListener((Observer) this.verificationsRequestListener).execute(this.requestManager);
        }
        return this.accountVerificationProfilePhotoView;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.accountVerificationProfilePhotoView != null) {
            outState.putParcelable(CUSTOM_VIEW_PARCEL_KEY, this.accountVerificationProfilePhotoView.onSaveInstanceState());
        }
    }

    public void onResume() {
        super.onResume();
        this.accountVerificationProfilePhotoView.updateViewsForState();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.chooseProfilePhotoController.destroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.chooseProfilePhotoController.onActivityResult(requestCode, resultCode, data);
    }

    public void onNext() {
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHOTO_WITH_FACE, Page.profile_photo_review, Element.navigation_button_continue);
        if (!this.accountVerificationProfilePhotoView.getProfilePhotoState().isError() || this.accountVerificationProfilePhotoView.getProfilePhotoFilePath() == null) {
            finish();
            return;
        }
        showLoader(true);
        uploadPhoto(false);
    }

    public void uploadPhoto(boolean enableFaceDetection) {
        SetProfilePhotoRequest request;
        AccountVerificationAnalytics.trackButtonClick(this.accountVerificationProfilePhotoView.getAnalyticsTag(), "upload_photo");
        this.controller.getIdentityJitneyLogger().logSubmitted(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.PHOTO_WITH_FACE, Page.provide_photo_upload, null);
        File profilePhotoFile = new File(this.accountVerificationProfilePhotoView.getProfilePhotoFilePath());
        if (enableFaceDetection) {
            request = SetProfilePhotoRequest.newRequestWithFaceDetection(getContext(), profilePhotoFile);
        } else {
            request = SetProfilePhotoRequest.newRequestWithoutFaceDetection(getContext(), profilePhotoFile);
        }
        request.withListener(this.uploadPhotoRequestListener).execute(this.requestManager);
    }

    public void onProfilePhotoCompressFailed() {
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.profile_photo_error);
        showLoader(false);
    }

    private void finish() {
        this.controller.onStepFinished(AccountVerificationStep.ProfilePhoto, false);
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.accountVerificationProfilePhotoView.getAnalyticsTag();
    }

    public void showLoader(boolean show) {
        this.accountVerificationProfilePhotoView.showLoader(show);
    }
}
