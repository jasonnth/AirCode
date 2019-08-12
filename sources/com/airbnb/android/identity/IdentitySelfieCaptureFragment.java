package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.identity.IdentityComponent.Builder;
import com.airbnb.android.misnap.TakeSelfieController;
import com.airbnb.android.misnap.TakeSelfieController.TakeSelfieListener;
import com.airbnb.android.utils.Strap;
import icepick.State;
import java.util.ArrayList;

public class IdentitySelfieCaptureFragment extends BaseAccountVerificationFragment implements IdentityLoaderFragment, TakeSelfieListener {
    @State
    boolean pendingStart;
    TakeSelfieController takeSelfieController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((IdentityBindings) CoreApplication.instance().componentProvider()).identityComponentProvider().get()).build().inject(this);
        show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_identity_loader, container, false);
        bindViews(view);
        return view;
    }

    public void onResume() {
        super.onResume();
        if (this.pendingStart) {
            show();
            this.pendingStart = false;
        }
    }

    public void show() {
        if (this.takeSelfieController == null) {
            this.pendingStart = true;
        }
        this.takeSelfieController.init(this);
        this.takeSelfieController.startTakeSelfieIntent(getContext());
    }

    public void onDestroyView() {
        this.takeSelfieController.destroy();
        super.onDestroyView();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.takeSelfieController.onActivityResult(requestCode, resultCode, data) && resultCode == 0) {
            this.controller.showPreviousStep();
        }
    }

    public void onSelfieCompressStart() {
    }

    public void onSelfieCompressFailed() {
        showPhotoProcessingError();
    }

    public void onSelfiePhotoReady(String filePath) {
        ArrayList<String> selfiePhotoFilePaths = new ArrayList<>();
        selfiePhotoFilePaths.add(filePath);
        this.controller.setSelfieFilePathsForPreview(selfiePhotoFilePaths);
        this.controller.onStepFinished(AccountVerificationStep.SelfieCapture, false);
    }

    public void onMultipleSelfiePhotosReady(ArrayList<String> selfiePaths) {
        this.controller.setSelfieFilePathsForPreview(selfiePaths);
        this.controller.onStepFinished(AccountVerificationStep.SelfieCapture, false);
    }

    public void onNoCamera() {
        showLoader(false);
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.account_verification_not_supported_no_camera_title, C6533R.string.account_verification_not_supported_no_camera_body);
    }

    public void onCameraAccessError() {
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.account_verification_cannot_access_camera_title, C6533R.string.account_verification_cannot_access_camera_body);
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.takeSelfieController.getCaptureMode().getSelfieNavigationTag();
    }

    private void showPhotoProcessingError() {
        ErrorUtils.showErrorUsingSnackbar(getView(), C6533R.string.profile_photo_error);
    }
}
