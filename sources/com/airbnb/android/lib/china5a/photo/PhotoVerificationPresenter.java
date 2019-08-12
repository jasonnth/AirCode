package com.airbnb.android.lib.china5a.photo;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.core.responses.UserWrapperResponse;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.china5a.BaseVerificationPresenter;
import com.airbnb.android.lib.china5a.VerificationResponse;
import com.facebook.internal.AnalyticsEvents;
import com.google.common.collect.FluentIterable;

public class PhotoVerificationPresenter extends BaseVerificationPresenter<PhotoVerificationModel, PhotoVerificationView> {
    AirbnbAccountManager accountManager;

    public PhotoVerificationPresenter(PhotoVerificationModel model, PhotoVerificationView view) {
        super(model, view);
        ((AirbnbGraph) AirbnbApplication.instance().component()).inject(this);
    }

    public void uploadPhoto(String filePath, boolean enableFaceDetection) {
        ((PhotoVerificationModel) this.model).uploadPhoto(filePath, enableFaceDetection);
        ((PhotoVerificationView) this.view).showLoading(true);
    }

    public void verifyCurrentPhoto() {
        if (this.accountManager.getCurrentUser().hasProfilePic()) {
            ((PhotoVerificationModel) this.model).verifyPhoto();
            ((PhotoVerificationView) this.view).showLoading(true);
        }
    }

    /* access modifiers changed from: protected */
    public void start() {
        super.start();
        this.f9513cs.add(((PhotoVerificationModel) this.model).getUploadPhotoResponse(true).subscribe(PhotoVerificationPresenter$$Lambda$1.lambdaFactory$(this)));
        this.f9513cs.add(((PhotoVerificationModel) this.model).getUploadPhotoResponse(false).subscribe(PhotoVerificationPresenter$$Lambda$2.lambdaFactory$(this)));
        this.f9513cs.add(((PhotoVerificationModel) this.model).getVerifyPhotoResponse().subscribe(PhotoVerificationPresenter$$Lambda$3.lambdaFactory$(this)));
    }

    static /* synthetic */ void lambda$start$0(PhotoVerificationPresenter photoVerificationPresenter, VerificationResponse resp) {
        ((PhotoVerificationView) photoVerificationPresenter.view).showLoading(false);
        if (resp.data != null) {
            ((PhotoVerificationView) photoVerificationPresenter.view).showPicture(((UserWrapperResponse) resp.data).user.getPictureUrl());
        } else {
            ((PhotoVerificationView) photoVerificationPresenter.view).showError();
        }
    }

    static /* synthetic */ void lambda$start$1(PhotoVerificationPresenter photoVerificationPresenter, VerificationResponse resp) {
        ((PhotoVerificationView) photoVerificationPresenter.view).showLoading(false);
        if (resp.data != null) {
            ((PhotoVerificationModel) photoVerificationPresenter.model).finish();
        } else {
            ((PhotoVerificationView) photoVerificationPresenter.view).showError();
        }
    }

    static /* synthetic */ void lambda$start$3(PhotoVerificationPresenter photoVerificationPresenter, VerificationResponse resp) {
        ((PhotoVerificationView) photoVerificationPresenter.view).showLoading(false);
        if (resp.data != null) {
            ((PhotoVerificationView) photoVerificationPresenter.view).showPicture(photoVerificationPresenter.accountManager.getCurrentUser().getPictureUrl());
            if (((AccountVerification) FluentIterable.from((Iterable<E>) ((AccountVerificationsResponse) resp.data).accountActivationVerifications).firstMatch(PhotoVerificationPresenter$$Lambda$4.lambdaFactory$()).orNull()) != null) {
                ((PhotoVerificationView) photoVerificationPresenter.view).showError();
            }
        }
    }

    static /* synthetic */ boolean lambda$null$2(AccountVerification verification) {
        return verification.getType().equals(AccountVerification.PHOTO) && !verification.isComplete();
    }

    /* access modifiers changed from: protected */
    public String getStepName() {
        return AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO;
    }
}
