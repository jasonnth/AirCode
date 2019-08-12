package com.airbnb.android.identity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics.CaptureMode;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.requests.AccountVerificationSelfieGetRequest;
import com.airbnb.android.core.requests.AccountVerificationSelfiePostRequest;
import com.airbnb.android.core.responses.AccountVerificationSelfieGetResponse;
import com.airbnb.android.core.responses.AccountVerificationSelfiePostResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.misnap.TakeSelfieController;
import com.airbnb.android.misnap.TakeSelfieController.TakeSelfieListener;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.collections.ProfilePhotoSheet;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import p032rx.Observer;

public class AccountVerificationSelfieConfirmFragment extends BaseAccountVerificationFragment implements TakeSelfieListener {
    final RequestListener<AccountVerificationSelfiePostResponse> airbnbSelfiePostResponseListener = new C0699RL().onError(AccountVerificationSelfieConfirmFragment$$Lambda$1.lambdaFactory$(this)).onComplete(AccountVerificationSelfieConfirmFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirButton bookingNextButton;
    @BindView
    AirButton confirmAndFinishButton;
    @State
    boolean hasClickedConfirm;
    @BindView
    JellyfishView jellyfishView;
    final RequestListener<AccountVerificationSelfiePostResponse> misnapSelfiePostResponseListener = new RequestListener<AccountVerificationSelfiePostResponse>() {
        public void onResponse(AccountVerificationSelfiePostResponse data) {
            AccountVerificationSelfieConfirmFragment.this.showLoader(false);
            AccountVerificationSelfieConfirmFragment.this.controller.onStepFinished(AccountVerificationStep.PhotoReview, false);
            AccountVerificationAnalytics.trackSelfieUpload(true, null, null, AccountVerificationSelfieConfirmFragment.this.getVerificationFlow(), System.currentTimeMillis() - AccountVerificationSelfieConfirmFragment.this.uploadStartTime);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            AccountVerificationSelfieConfirmFragment.this.showLoader(false);
            NetworkUtil.toastNetworkError(AccountVerificationSelfieConfirmFragment.this.getContext(), (NetworkException) e);
            AccountVerificationAnalytics.trackSelfieUpload(true, Integer.valueOf(e.statusCode()), e.getMessage(), AccountVerificationSelfieConfirmFragment.this.getVerificationFlow(), System.currentTimeMillis() - AccountVerificationSelfieConfirmFragment.this.uploadStartTime);
        }
    };
    @BindView
    AirButton nextButton;
    @BindView
    ProfilePhotoSheet profilePhotoSheet;
    final RequestListener<AccountVerificationSelfieGetResponse> selfieGetResponseListener = new RequestListener<AccountVerificationSelfieGetResponse>() {
        public void onResponse(AccountVerificationSelfieGetResponse data) {
            AccountVerificationSelfieConfirmFragment.this.profilePhotoSheet.setProfilePhoto(Uri.parse(data.getImageUrl()));
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            NetworkUtil.tryShowErrorWithSnackbar(AccountVerificationSelfieConfirmFragment.this.getView(), e);
        }
    };
    @State
    ArrayList<String> selfiePhotoFilePaths = new ArrayList<>();
    TakeSelfieController takeSelfieController;
    @State
    long uploadStartTime;

    static /* synthetic */ void lambda$new$0(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment, AirRequestNetworkException e) {
        accountVerificationSelfieConfirmFragment.showLoader(false);
        NetworkUtil.toastNetworkError(accountVerificationSelfieConfirmFragment.getContext(), (NetworkException) e);
        AccountVerificationAnalytics.trackSelfieUpload(true, Integer.valueOf(e.statusCode()), e.getMessage(), accountVerificationSelfieConfirmFragment.getVerificationFlow(), System.currentTimeMillis() - accountVerificationSelfieConfirmFragment.uploadStartTime);
    }

    static /* synthetic */ void lambda$new$1(AccountVerificationSelfieConfirmFragment accountVerificationSelfieConfirmFragment, Boolean success) {
        if (success.booleanValue() && !accountVerificationSelfieConfirmFragment.isUploadingPhotos()) {
            accountVerificationSelfieConfirmFragment.showLoader(false);
            if (accountVerificationSelfieConfirmFragment.hasClickedConfirm) {
                accountVerificationSelfieConfirmFragment.controller.onStepFinished(AccountVerificationStep.PhotoReview, false);
                AccountVerificationAnalytics.trackSelfieUpload(true, null, null, accountVerificationSelfieConfirmFragment.getVerificationFlow(), System.currentTimeMillis() - accountVerificationSelfieConfirmFragment.uploadStartTime);
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((IdentityGraph) CoreApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.selfiePhotoFilePaths = new ArrayList<>(this.controller.getSelfieFilePaths());
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_selfie_confirm, container, false);
        bindViews(view);
        this.profilePhotoSheet.setSubtitle(getVerificationFlow().getText().getSelfieReviewSubtitle());
        this.takeSelfieController.init(this);
        setIdentityStyle(view);
        this.controller.getIdentityJitneyLogger().logImpression(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.review_your_photos);
        return view;
    }

    private void setIdentityStyle(View view) {
        IdentityStyle style = IdentityStyle.getStyle(getVerificationFlow());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        this.profilePhotoSheet.setProfilePhotoBorder(ContextCompat.getColor(getContext(), style.photoBorderColorRes), getResources().getDimension(C6533R.dimen.verifications_halo_border_thickness));
        this.profilePhotoSheet.setMarqueeStyle(style.marqueeStyle);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        if (getVerificationFlow().showFinishFragment()) {
            this.nextButton.setVisibility(0);
            return;
        }
        ViewUtils.setVisibleIf((View) this.confirmAndFinishButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.bookingNextButton, style.whiteNextButtonVisible);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!ListUtils.isEmpty((Collection<?>) this.selfiePhotoFilePaths)) {
            this.controller.getIdentityJitneyLogger().logSubmitted(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, null);
        }
        Iterator it = this.selfiePhotoFilePaths.iterator();
        while (it.hasNext()) {
            String selfieFilePath = (String) it.next();
            this.requestManager.executeWithTag(new AccountVerificationSelfiePostRequest(new File(selfieFilePath), this.takeSelfieController.getCaptureMode().name()).withListener((Observer) this.airbnbSelfiePostResponseListener), selfieFilePath);
        }
    }

    public void onResume() {
        super.onResume();
        if (!ListUtils.isEmpty((Collection<?>) this.selfiePhotoFilePaths) && !TextUtils.isEmpty((CharSequence) this.selfiePhotoFilePaths.get(this.selfiePhotoFilePaths.size() - 1))) {
            setSelfieImageFromLocalFile();
        } else if (!this.requestManager.hasRequest((BaseRequestListener<T>) this.selfieGetResponseListener, AccountVerificationSelfieGetRequest.class)) {
            new AccountVerificationSelfieGetRequest(this.mAccountManager.getCurrentUser().getId()).withListener((Observer) this.selfieGetResponseListener).execute(this.requestManager);
        }
    }

    public void onDestroyView() {
        this.takeSelfieController.destroy();
        super.onDestroyView();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.takeSelfieController.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onConfirmAndFinishClick() {
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.review_your_photos, Element.navigation_button_finish);
        onConfirmClick();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClick() {
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.review_your_photos, Element.navigation_button_continue);
        onConfirmClick();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onBookingNextClick() {
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.review_your_photos, Element.navigation_button_finish);
        onConfirmClick();
    }

    /* access modifiers changed from: 0000 */
    public void onConfirmClick() {
        if (ListUtils.isEmpty((Collection<?>) this.selfiePhotoFilePaths) || TextUtils.isEmpty((CharSequence) this.selfiePhotoFilePaths.get(this.selfiePhotoFilePaths.size() - 1))) {
            this.controller.onStepFinished(AccountVerificationStep.PhotoReview, false);
        } else if (this.takeSelfieController.getCaptureMode() == CaptureMode.Mitek) {
            showLoader(true);
            this.controller.getIdentityJitneyLogger().logSubmitted(getVerificationFlow(), this.controller.getUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, null);
            this.uploadStartTime = System.currentTimeMillis();
            new AccountVerificationSelfiePostRequest(new File((String) this.selfiePhotoFilePaths.get(0)), this.takeSelfieController.getCaptureMode().name()).withListener((Observer) this.misnapSelfiePostResponseListener).execute(this.requestManager);
        } else if (this.takeSelfieController.getCaptureMode() != CaptureMode.Airbnb) {
        } else {
            if (isUploadingPhotos()) {
                this.hasClickedConfirm = true;
                showLoader(true);
                return;
            }
            AccountVerificationAnalytics.trackSelfieUpload(true, null, null, getVerificationFlow(), System.currentTimeMillis() - this.uploadStartTime);
            this.controller.onStepFinished(AccountVerificationStep.PhotoReview, false);
        }
    }

    public void onSelfieCompressStart() {
        showLoader(true);
    }

    public void onSelfieCompressFailed() {
        showLoader(false);
        showPhotoProcessingError();
    }

    public void onSelfiePhotoReady(String filePath) {
        showLoader(false);
        this.selfiePhotoFilePaths = new ArrayList<>();
        this.selfiePhotoFilePaths.add(filePath);
        setSelfieImageFromLocalFile();
    }

    public void onMultipleSelfiePhotosReady(ArrayList<String> selfiePaths) {
        showLoader(false);
        this.selfiePhotoFilePaths = selfiePaths;
        setSelfieImageFromLocalFile();
    }

    private void setSelfieImageFromLocalFile() {
        this.profilePhotoSheet.setProfilePhoto(Uri.fromFile(new File((String) this.selfiePhotoFilePaths.get(this.selfiePhotoFilePaths.size() - 1))));
    }

    public void onNoCamera() {
        showLoader(false);
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.account_verification_not_supported_no_camera_title, C6533R.string.account_verification_not_supported_no_camera_body);
    }

    public void onCameraAccessError() {
        showLoader(false);
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.account_verification_cannot_access_camera_title, C6533R.string.account_verification_cannot_access_camera_body);
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationPhotoReview;
    }

    private void showPhotoProcessingError() {
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.profile_photo_error);
    }

    public void showLoader(boolean show) {
        showLoader(this.confirmAndFinishButton, show);
        showLoader(this.nextButton, show);
        showLoader(this.bookingNextButton, show);
    }

    private void showLoader(AirButton button, boolean show) {
        button.setState(show ? AirButton.State.Loading : AirButton.State.Normal);
    }

    private boolean isUploadingPhotos() {
        return FluentIterable.from((Iterable<E>) this.selfiePhotoFilePaths).anyMatch(AccountVerificationSelfieConfirmFragment$$Lambda$3.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public boolean isPhotoUploading(String selfieFilePath) {
        return this.requestManager.hasRequest((BaseRequestListener<?>) this.airbnbSelfiePostResponseListener, selfieFilePath);
    }
}
