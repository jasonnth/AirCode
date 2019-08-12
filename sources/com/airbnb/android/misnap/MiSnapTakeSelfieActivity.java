package com.airbnb.android.misnap;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.miteksystems.facialcapture.science.FacialCaptureFragment;
import com.miteksystems.facialcapture.science.api.events.OnFacialCaptureProcessedEvent;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureApi;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureApiConstants;
import com.miteksystems.misnap.events.CaptureCurrentFrameEvent;
import com.miteksystems.misnap.events.OnCapturedFrameEvent;
import com.miteksystems.misnap.events.OnShutdownEvent;
import com.miteksystems.misnap.events.OnStartedEvent;
import com.miteksystems.misnap.events.ShutdownEvent;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.ParamsHelper;
import org.json.JSONException;
import org.json.JSONObject;
import p314de.greenrobot.event.EventBus;

public class MiSnapTakeSelfieActivity extends AirActivity {
    private static final int AUTO_MODE_TIMEOUT_IN_MS = 20000;
    private static final int MESSAGE_DELAY = 250;
    public static final int PERMISSION_REQUEST_CAMERA = 1;
    /* access modifiers changed from: private */
    public static final String TAG = MiSnapTakeSelfieActivity.class.getSimpleName();
    private static long messageLastDisplayedTime = System.currentTimeMillis();
    @BindView
    ImageButton captureButton;
    @BindView
    TextView captureModeButton;
    @BindView
    ImageView capturedFullOverlay;
    @BindView
    ImageButton closeButton;
    @BindView
    TextView errorTextView;
    long fpsNumFrames;
    long fpsStartTime;
    private final Handler handler = new Handler();
    /* access modifiers changed from: private */
    public boolean hasCapturedPic;
    private boolean hasPermissions;
    IdentityJitneyLogger identityJitneyLogger;
    @BindView
    View overlayView;

    class VideoTimeoutRunnable implements Runnable {
        VideoTimeoutRunnable() {
        }

        public void run() {
            if (!MiSnapTakeSelfieActivity.this.hasCapturedPic && MiSnapTakeSelfieActivity.this.isAutoMode()) {
                Log.d(MiSnapTakeSelfieActivity.TAG, "Session timed out");
                ParamsHelper.setCaptureMode(MiSnapTakeSelfieActivity.this.getIntent(), 1);
                MiSnapTakeSelfieActivity.this.startFacialCapture();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MiSnapGraph) CoreApplication.instance(this).component()).inject(this);
        getWindow().addFlags(1024);
        getWindow().addFlags(8192);
        this.identityJitneyLogger.logImpression(null, this.accountManager.getCurrentUser(), IdentityVerificationType.SELFIE, Page.selfie_camera);
        setContentView(C7471R.layout.activity_misnap_take_selfie);
        ButterKnife.bind((Activity) this);
        getWindow().setBackgroundDrawable(null);
        initializeMiSnap();
        this.hasPermissions = false;
    }

    private void initializeMiSnap() {
        JSONObject jobSettings = new JSONObject();
        try {
            jobSettings.put(MiSnapAPI.MiSnapAllowScreenshots, 1);
            jobSettings.put(MiSnapAPI.MiSnapCaptureMode, 2);
            jobSettings.put(FacialCaptureApi.BlinkThreshold, 400);
            jobSettings.put(FacialCaptureApi.EyeMinDistance, 200);
            jobSettings.put(FacialCaptureApi.EyeMaxDistance, FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT);
            jobSettings.put(FacialCaptureApi.LivenessThreshold, 500);
            jobSettings.put(FacialCaptureApi.LightingMinThreshold, 500);
            jobSettings.put(FacialCaptureApi.SharpnessMinThreshold, 200);
            jobSettings.put(FacialCaptureApi.CaptureEyesOpen, 1);
        } catch (JSONException e) {
        }
        getIntent().putExtra(MiSnapAPI.JOB_SETTINGS, jobSettings.toString());
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (!this.hasPermissions) {
            requestRuntimePermissions();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.handler.removeCallbacksAndMessages(null);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    finish();
                    break;
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
    }

    private void requestRuntimePermissions() {
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, 1);
            return;
        }
        this.hasPermissions = true;
        startFacialCapture();
    }

    private boolean hasPermissions() {
        return ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0;
    }

    /* access modifiers changed from: private */
    public boolean isAutoMode() {
        return ParamsHelper.isAutoCaptureMode(getIntent());
    }

    /* access modifiers changed from: private */
    public void startFacialCapture() {
        getSupportFragmentManager().beginTransaction().disallowAddToBackStack().replace(C7471R.C7473id.selfie_capture_container, new FacialCaptureFragment()).commit();
    }

    @OnClick
    public void onModeChange() {
        if (isAutoMode()) {
            this.handler.removeCallbacksAndMessages(null);
        }
        ParamsHelper.setCaptureMode(getIntent(), isAutoMode() ? 1 : 2);
        startFacialCapture();
    }

    @OnClick
    public void onClose() {
        this.identityJitneyLogger.logClick(null, this.accountManager.getCurrentUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, Element.navigation_button_cancel);
        this.handler.removeCallbacksAndMessages(null);
        this.captureButton.setVisibility(4);
        EventBus.getDefault().post(new ShutdownEvent(4));
        finish();
    }

    @OnClick
    public void onHelp() {
        this.identityJitneyLogger.logClick(null, this.accountManager.getCurrentUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, Element.button_help);
        AccountVerificationAnalytics.trackButtonClick(NavigationTag.VerificationScanIdWithMiSnap, AccountVerificationAnalytics.HELP_BUTTON);
        showOverlayView(false);
        showFragment(TakeSelfieHelpFragment.newInstance(), C7471R.C7473id.selfie_capture_container, FragmentTransitionType.SlideFromBottom, true);
    }

    @OnClick
    public void onCapture() {
        this.identityJitneyLogger.logClick(null, this.accountManager.getCurrentUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, Element.button_take_photo);
        this.handler.removeCallbacksAndMessages(null);
        this.captureButton.setVisibility(4);
        EventBus.getDefault().post(new CaptureCurrentFrameEvent());
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void onEventMainThread(OnFacialCaptureProcessedEvent event) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - messageLastDisplayedTime > 250) {
            if (!event.isDeviceUpright()) {
                showErrorMessage(C7471R.string.misnap_facialcapture_overlay_message_hold_phone_upright);
            } else if (!event.isFaceFound()) {
                showErrorMessage(C7471R.string.misnap_facialcapture_overlay_message_face_not_found);
            } else if (event.isFaceTooClose()) {
                showErrorMessage(C7471R.string.misnap_facialcapture_overlay_message_face_move_further_away);
            } else if (event.isFaceTooFarAway()) {
                showErrorMessage(C7471R.string.misnap_facialcapture_overlay_message_face_get_closer);
            } else if (!event.isLightingUniform()) {
                showErrorMessage(C7471R.string.misnap_facialcapture_overlay_message_lighting_fail);
            } else if (!event.isSharpnessGood()) {
                showErrorMessage(C7471R.string.misnap_facialcapture_overlay_message_sharpness_fail);
            } else if (!event.isBlinkDetected()) {
                if (isAutoMode()) {
                    showErrorMessage(C7471R.string.misnap_facialcapture_overlay_message_blink_now);
                } else {
                    showErrorMessage(C7471R.string.misnap_facialcapture_overlay_message_tap_now);
                }
            } else if (event.isBlinkDetected()) {
                hideErrorMessage();
            } else {
                hideErrorMessage();
            }
            messageLastDisplayedTime = currentTime;
        }
        if (BuildHelper.isDevelopmentBuild() && event.isBlinkDetected()) {
            Log.d(TAG, "FacialCapture detected a blink.");
        }
    }

    public void onEventMainThread(OnCapturedFrameEvent event) {
        this.hasCapturedPic = true;
        byte[] capturedImage = event.returnIntent.getByteArrayExtra(MiSnapAPI.RESULT_PICTURE_DATA);
        setResult(-1, event.returnIntent);
        EventBus.getDefault().post(new ShutdownEvent(0));
        hideErrorMessage();
        this.closeButton.setVisibility(4);
        if (BuildHelper.isDevelopmentBuild()) {
            this.capturedFullOverlay.setImageBitmap(BitmapFactory.decodeByteArray(capturedImage, 0, capturedImage.length));
            this.capturedFullOverlay.setVisibility(0);
            this.capturedFullOverlay.invalidate();
        }
        this.capturedFullOverlay.setBackgroundColor(getResources().getColor(17170443));
        this.capturedFullOverlay.setVisibility(0);
        finish();
    }

    private void hideErrorMessage() {
        this.errorTextView.setVisibility(4);
    }

    private void showErrorMessage(int stringResId) {
        this.errorTextView.setText(stringResId);
        this.errorTextView.setVisibility(0);
    }

    public void onEvent(OnStartedEvent event) {
        showOverlayView(true);
        if (isAutoMode()) {
            this.handler.postDelayed(new VideoTimeoutRunnable(), 20000);
        }
    }

    public void onEventMainThread(OnShutdownEvent event) {
        Log.d(TAG, "OnShutdown");
        if (event.errorCode == -1) {
            finish();
        } else if (event.errorReason.startsWith(MiSnapAPI.RESULT_ERROR_PREFIX)) {
            Toast.makeText(getApplicationContext(), event.errorReason, 1).show();
            finish();
        }
    }

    public void showOverlayView(boolean show) {
        ViewUtils.setVisibleIf(this.overlayView, show);
        if (show) {
            ViewUtils.setVisibleIf((View) this.captureButton, !isAutoMode());
            this.captureModeButton.setText(isAutoMode() ? C7471R.string.account_verifications_camera_manual_mode : C7471R.string.account_verifications_camera_auto_mode);
        }
    }
}
