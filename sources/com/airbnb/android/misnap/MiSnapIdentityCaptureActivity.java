package com.airbnb.android.misnap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.MiSnapIntentCheck;
import icepick.State;
import org.json.JSONException;
import org.json.JSONObject;

public class MiSnapIdentityCaptureActivity extends AirActivity {
    private static final int ERROR_DISPLAY_PERIOD = 2000;
    public static final String MISNAP_ACTIVITY_BARCODE = "misnap_activity_barcode";
    public static final String MISNAP_ACTIVITY_IS_BACK_PHOTO = "misnap_activity_is_back_photo";
    public static final String MISNAP_ACTIVITY_RESULT_FILE = "misnap_activity_result_file";
    @BindView
    View captureButton;
    @BindView
    TextView captureModeButton;
    @State
    MiSnapController.State currentState;
    @BindView
    TextView errorTextView;
    private final Handler handler = new Handler();
    @State
    boolean hasBarcode;
    @BindView
    View idFrameView;
    IdentityJitneyLogger identityJitneyLogger;
    @State
    boolean isAutoCaptureMode;
    @State
    boolean isBarcodRequest;
    @State
    boolean isShowingError;
    private MiSnapController miSnapController;
    @BindView
    View overlayView;
    @BindView
    TextView statusView;

    public static Intent createIntentForBackPhotoOfDocumentType(Context context, String documentType) {
        return buildIntent(context, documentType, true);
    }

    public static Intent createIntentForDocumentType(Context context, String documentType) {
        return buildIntent(context, documentType, false);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MiSnapIntentCheck.isDangerous(getIntent())) {
            setResult(0);
            finish();
            return;
        }
        getWindow().addFlags(1024);
        getWindow().addFlags(8192);
        setContentView(C7471R.layout.activity_account_verification_misnap);
        ButterKnife.bind((Activity) this);
        ((MiSnapGraph) CoreApplication.instance(this).component()).inject(this);
        getWindow().setBackgroundDrawable(null);
        this.identityJitneyLogger.logImpression(null, this.accountManager.getCurrentUser(), IdentityVerificationType.GOVERNMENT_ID, Page.mitek_id_scan);
        this.isBarcodRequest = MiSnapUtils.isBarcodeRequest(getIntent());
        this.miSnapController = new MiSnapController(this);
        this.hasBarcode = true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.miSnapController.destroy();
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        this.miSnapController.resume(this.currentState == null ? MiSnapController.INIT_STATE : this.currentState);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.miSnapController.pause();
    }

    @OnClick
    public void onModeChange() {
        this.miSnapController.switchCaptureMode();
    }

    @OnClick
    public void onClose() {
        this.miSnapController.onCloseRequested();
        this.identityJitneyLogger.logClick(null, this.accountManager.getCurrentUser(), IdentityVerificationType.GOVERNMENT_ID, Page.mitek_id_scan, Element.navigation_button_cancel);
    }

    @OnClick
    public void onCapture() {
        this.miSnapController.onCaptureRequested();
        this.identityJitneyLogger.logClick(null, this.accountManager.getCurrentUser(), IdentityVerificationType.GOVERNMENT_ID, Page.mitek_id_scan, Element.button_take_photo);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        if (this.miSnapController.canHandlePermissionResult(requestCode)) {
            this.miSnapController.onPermissionsResult(grantResults);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        this.identityJitneyLogger.logClick(null, this.accountManager.getCurrentUser(), IdentityVerificationType.GOVERNMENT_ID, Page.mitek_id_scan, Element.navigation_button_back);
        return true;
    }

    public void showOverlayView(boolean show) {
        ViewUtils.setVisibleIf(this.overlayView, show);
        if (show) {
            prepareViewForCaptureMode();
        }
    }

    public void setCaptureState(boolean isCapturing) {
        if (!getIntent().getBooleanExtra(MISNAP_ACTIVITY_IS_BACK_PHOTO, false)) {
            TextView textView = this.statusView;
            int i = isCapturing ? C7471R.string.verified_id_camera_overlay_capturing : this.isAutoCaptureMode ? C7471R.string.verified_id_camera_overlay_auto_mode_desc_front : C7471R.string.verified_id_camera_overlay_desc_front;
            textView.setText(i);
        } else if (this.isBarcodRequest) {
            TextView textView2 = this.statusView;
            int i2 = isCapturing ? C7471R.string.verified_id_camera_overlay_capturing : this.isAutoCaptureMode ? C7471R.string.verified_id_camera_overlay_barcode_back : C7471R.string.verified_id_camera_overlay_no_barcode_back;
            textView2.setText(i2);
        } else {
            TextView textView3 = this.statusView;
            int i3 = isCapturing ? C7471R.string.verified_id_camera_overlay_capturing : this.isAutoCaptureMode ? C7471R.string.verified_id_camera_overlay_auto_mode_desc_back : C7471R.string.verified_id_camera_overlay_desc_back;
            textView3.setText(i3);
        }
    }

    public void setControllerState(MiSnapController.State state) {
        this.currentState = state;
    }

    public boolean getCaptureMode() {
        return this.isAutoCaptureMode;
    }

    public void disableModeSwitch() {
        this.captureModeButton.setVisibility(8);
    }

    public void setCaptureMode(boolean captureMode) {
        this.isAutoCaptureMode = captureMode;
    }

    public void prepareViewForCaptureMode() {
        int i;
        boolean z = false;
        boolean isBackPhoto = getIntent().getBooleanExtra(MISNAP_ACTIVITY_IS_BACK_PHOTO, false);
        View view = this.captureButton;
        if (!this.isAutoCaptureMode) {
            z = true;
        }
        ViewUtils.setVisibleIf(view, z);
        LayoutParams lp = (LayoutParams) this.idFrameView.getLayoutParams();
        lp.setMargins(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.topMargin);
        if (!isBackPhoto) {
            this.captureModeButton.setText(this.isAutoCaptureMode ? C7471R.string.account_verifications_camera_manual_mode : C7471R.string.account_verifications_camera_auto_mode);
            TextView textView = this.statusView;
            if (this.isAutoCaptureMode) {
                i = C7471R.string.verified_id_camera_overlay_auto_mode_desc_front;
            } else {
                i = C7471R.string.verified_id_camera_overlay_desc_front;
            }
            textView.setText(i);
        } else if (this.isBarcodRequest) {
            this.captureModeButton.setText(this.isAutoCaptureMode ? C7471R.string.verified_id_camera_no_barcode_mode : C7471R.string.verified_id_camera_barcode_mode);
            this.statusView.setText(this.isAutoCaptureMode ? C7471R.string.verified_id_camera_overlay_barcode_back : C7471R.string.verified_id_camera_overlay_no_barcode_back);
        } else {
            this.captureModeButton.setText(this.isAutoCaptureMode ? C7471R.string.account_verifications_camera_manual_mode : C7471R.string.account_verifications_camera_auto_mode);
            this.statusView.setText(this.isAutoCaptureMode ? C7471R.string.verified_id_camera_overlay_auto_mode_desc_back : C7471R.string.verified_id_camera_overlay_desc_back);
        }
    }

    private static Intent buildIntent(Context context, String documentType, boolean isForBackPhoto) {
        Intent miSnapIdentityCaptureIntent = new Intent(context, MiSnapIdentityCaptureActivity.class);
        JSONObject param = new JSONObject();
        try {
            param.put(MiSnapAPI.MiSnapDocumentType, documentType);
            param.put(MiSnapAPI.MiSnapLockView, 1);
            param.put(MiSnapAPI.MiSnapAllowScreenshots, 1);
            miSnapIdentityCaptureIntent.putExtra(MISNAP_ACTIVITY_IS_BACK_PHOTO, isForBackPhoto);
            miSnapIdentityCaptureIntent.putExtra(MiSnapAPI.JOB_SETTINGS, param.toString());
            return miSnapIdentityCaptureIntent;
        } catch (JSONException e) {
            throw new RuntimeException("Failed to create MiSnap acctivity.", e);
        }
    }

    /* access modifiers changed from: protected */
    public void showError(int errorResId, boolean forceShow) {
        if (!this.isShowingError || forceShow) {
            this.isShowingError = true;
            this.errorTextView.setText(errorResId);
            this.errorTextView.setVisibility(0);
            this.handler.postDelayed(MiSnapIdentityCaptureActivity$$Lambda$1.lambdaFactory$(this), 2000);
        }
    }

    /* access modifiers changed from: private */
    public void hideError() {
        this.errorTextView.setVisibility(4);
        this.isShowingError = false;
    }
}
