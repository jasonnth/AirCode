package com.airbnb.android.misnap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.p000v4.app.ActivityCompat;
import android.support.p002v7.app.AlertDialog.Builder;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics.CaptureMode;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.photopicker.FileUtils;
import com.airbnb.android.utils.IOUtils;
import com.miteksystems.misnap.MiSnapFragment;
import com.miteksystems.misnap.barcode.BarcodeFragment;
import com.miteksystems.misnap.events.CaptureCurrentFrameEvent;
import com.miteksystems.misnap.events.OnCaptureModeChangedEvent;
import com.miteksystems.misnap.events.OnCapturedBarcodeEvent;
import com.miteksystems.misnap.events.OnCapturedFrameEvent;
import com.miteksystems.misnap.events.OnFrameProcessedEvent;
import com.miteksystems.misnap.events.OnShutdownEvent;
import com.miteksystems.misnap.events.OnStartedEvent;
import com.miteksystems.misnap.events.OnTorchStateEvent;
import com.miteksystems.misnap.events.SetCaptureModeEvent;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.utils.Utils;
import java.io.File;
import p314de.greenrobot.event.EventBus;
import permissions.dispatcher.PermissionUtils;

class MiSnapController {
    private static final long AUTO_CAPTURE_TIME_OUT = 20000;
    public static final State INIT_STATE = State.PERMISSION_CHECK;
    private static final String MISNAP_FRAGMENT_TAG = MiSnapFragment.class.getSimpleName();
    private static final int PERMISSION_REQUEST_CAMERA = 1;
    private static final long SHOW_CAPTURED_IMAGE_DURATION_MS = 500;
    private static final int UI_FRAGMENT_FREEZE_FRAME_ID = 40000;
    private String barcodeResult;
    private final Handler handler = new Handler();
    private AsyncTask<Void, Void, Boolean> ioTask;
    private boolean isCapturing;
    private boolean isModeSwitching;
    /* access modifiers changed from: private */
    public MiSnapIdentityCaptureActivity miSnapCaptureActivity;
    /* access modifiers changed from: private */
    public String resultFile;
    private Intent results;

    private enum MisnapError {
        FourCorner(1, C7471R.string.misnap_error_four_corner_confidence),
        Distance(2, C7471R.string.misnap_error_get_closer_to_license),
        TooDark(4, C7471R.string.misnap_error_too_dark),
        TooMuchLight(8, C7471R.string.misnap_error_too_much_light),
        NotCentered(16, C7471R.string.misnap_error_center_license),
        NotSteady(32, C7471R.string.misnap_error_hold_steady),
        TooClose(64, C7471R.string.misnap_error_too_close),
        NotDirectly(128, C7471R.string.misnap_error_rotation_angle),
        LowContrast(256, C7471R.string.misnap_error_low_contrast),
        BusyBackground(512, C7471R.string.misnap_error_busy_background),
        WrongDocument(2048, C7471R.string.misnap_error_wrong_document),
        Glare(1024, C7471R.string.misnap_error_glare);
        
        final int errorResId;
        final int mask;

        private MisnapError(int mask2, int errorResId2) {
            this.mask = mask2;
            this.errorResId = errorResId2;
        }
    }

    public enum State {
        PERMISSION_CHECK,
        PERMISSION_GRANTED,
        CANCELED,
        ACTIVE,
        CAPTURED_FRAME,
        ERROR,
        FINISHED
    }

    MiSnapController(MiSnapIdentityCaptureActivity activity) {
        this.miSnapCaptureActivity = activity;
        this.miSnapCaptureActivity.showOverlayView(false);
    }

    /* access modifiers changed from: 0000 */
    public boolean canHandlePermissionResult(int requestCode) {
        return requestCode == 1;
    }

    /* access modifiers changed from: 0000 */
    public void onPermissionsResult(int[] grantResults) {
        gotoState(PermissionUtils.verifyPermissions(grantResults) ? State.PERMISSION_GRANTED : State.CANCELED);
    }

    public void pause() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void resume(State state) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        gotoState(state);
    }

    public void destroy() {
        if (this.ioTask != null) {
            this.ioTask.cancel(true);
            this.ioTask = null;
        }
        this.miSnapCaptureActivity = null;
        this.handler.removeCallbacksAndMessages(null);
    }

    /* access modifiers changed from: 0000 */
    public void onCaptureRequested() {
        if (!this.isCapturing) {
            this.isCapturing = true;
            this.miSnapCaptureActivity.setCaptureState(true);
            EventBus.getDefault().post(new CaptureCurrentFrameEvent());
        }
    }

    /* access modifiers changed from: 0000 */
    public void onCloseRequested() {
        gotoState(State.CANCELED);
    }

    /* access modifiers changed from: private */
    public void gotoState(State nextState) {
        if (this.miSnapCaptureActivity != null) {
            this.miSnapCaptureActivity.setControllerState(nextState);
            try {
                switch (nextState) {
                    case PERMISSION_CHECK:
                        ActivityCompat.requestPermissions(this.miSnapCaptureActivity, new String[]{"android.permission.CAMERA"}, 1);
                        return;
                    case PERMISSION_GRANTED:
                        if (this.miSnapCaptureActivity.isBarcodRequest) {
                            addBarcodeFragment();
                            return;
                        } else {
                            addMiSnapFragment();
                            return;
                        }
                    case CANCELED:
                        this.miSnapCaptureActivity.setResult(0);
                        EventBus.getDefault().post(new OnShutdownEvent(0, this.miSnapCaptureActivity.getIntent().getStringExtra(MiSnapAPI.RESULT_CODE)));
                        return;
                    case ACTIVE:
                        if (!this.miSnapCaptureActivity.isBarcodRequest) {
                            this.miSnapCaptureActivity.showOverlayView(true);
                            return;
                        } else {
                            this.miSnapCaptureActivity.showOverlayView(true);
                            return;
                        }
                    case CAPTURED_FRAME:
                        writeImageToFile();
                        return;
                    case ERROR:
                        this.miSnapCaptureActivity.setResult(-1);
                        EventBus.getDefault().post(new OnShutdownEvent(-1, this.miSnapCaptureActivity.getIntent().getStringExtra(MiSnapAPI.RESULT_CODE)));
                        return;
                    case FINISHED:
                        Intent data = new Intent();
                        data.putExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_RESULT_FILE, this.resultFile);
                        data.putExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_IS_BACK_PHOTO, this.miSnapCaptureActivity.getIntent().getBooleanExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_IS_BACK_PHOTO, false));
                        if (this.barcodeResult != null) {
                            data.putExtra(MiSnapIdentityCaptureActivity.MISNAP_ACTIVITY_BARCODE, this.barcodeResult);
                        }
                        this.miSnapCaptureActivity.setResult(-1, data);
                        EventBus.getDefault().post(new OnShutdownEvent(-1, this.miSnapCaptureActivity.getIntent().getStringExtra(MiSnapAPI.RESULT_CODE)));
                        return;
                    default:
                        return;
                }
            } catch (RuntimeException e) {
                gotoState(State.CANCELED);
            }
            gotoState(State.CANCELED);
        }
    }

    private void addMiSnapFragment() {
        boolean isAutoMode = MiSnapUtils.setupMiSnapCameraForAutoCaptureIfPossible(this.miSnapCaptureActivity.getApplicationContext(), this.miSnapCaptureActivity.getIntent());
        if (!isAutoMode) {
            this.miSnapCaptureActivity.disableModeSwitch();
        }
        this.miSnapCaptureActivity.setCaptureMode(isAutoMode);
        this.miSnapCaptureActivity.getSupportFragmentManager().beginTransaction().disallowAddToBackStack().replace(C7471R.C7473id.misnap_capture_container, new MiSnapFragment(), MISNAP_FRAGMENT_TAG).commitAllowingStateLoss();
        this.handler.postDelayed(MiSnapController$$Lambda$1.lambdaFactory$(this), AUTO_CAPTURE_TIME_OUT);
    }

    /* access modifiers changed from: private */
    public void switchToManualMode() {
        switchCaptureMode();
        this.miSnapCaptureActivity.showError(C7471R.string.misnap_error_auto_capture_time_out, true);
    }

    private void disableAutoCaptureTimeOut() {
        this.handler.removeCallbacksAndMessages(null);
    }

    private void addBarcodeFragment() {
        this.miSnapCaptureActivity.setCaptureMode(true);
        this.miSnapCaptureActivity.getSupportFragmentManager().beginTransaction().disallowAddToBackStack().replace(C7471R.C7473id.misnap_capture_container, new BarcodeFragment(), MISNAP_FRAGMENT_TAG).commitAllowingStateLoss();
    }

    private void showConfirmationDialog() {
        new Builder(this.miSnapCaptureActivity).setMessage(C7471R.string.verified_id_camera_confirmation_message).setPositiveButton(C7471R.string.yes, MiSnapController$$Lambda$2.lambdaFactory$(this)).setNegativeButton(C7471R.string.verified_id_camera_confirmation_message_reject, MiSnapController$$Lambda$3.lambdaFactory$(this)).setCancelable(false).show();
    }

    static /* synthetic */ void lambda$showConfirmationDialog$1(MiSnapController miSnapController, DialogInterface dialog, int arg1) {
        miSnapController.gotoState(State.CAPTURED_FRAME);
        dialog.dismiss();
    }

    static /* synthetic */ void lambda$showConfirmationDialog$2(MiSnapController miSnapController, DialogInterface dialog, int arg1) {
        miSnapController.gotoState(State.PERMISSION_CHECK);
        VerifiedIdAnalytics.trackPhotoRetake(miSnapController.miSnapCaptureActivity.getCaptureMode(), CaptureMode.Mitek.name());
        dialog.dismiss();
    }

    public void switchCaptureMode() {
        disableAutoCaptureTimeOut();
        if (!this.isModeSwitching) {
            this.isModeSwitching = true;
            if (!this.miSnapCaptureActivity.getCaptureMode()) {
                EventBus.getDefault().post(new SetCaptureModeEvent(2));
            } else {
                EventBus.getDefault().post(new SetCaptureModeEvent(1));
            }
        }
    }

    public void onEvent(OnStartedEvent event) {
        gotoState(State.ACTIVE);
    }

    public void onEvent(OnShutdownEvent event) {
        this.results = null;
        this.miSnapCaptureActivity.finish();
    }

    public void onEvent(OnCapturedFrameEvent event) {
        disableAutoCaptureTimeOut();
        this.isCapturing = false;
        this.miSnapCaptureActivity.setCaptureState(false);
        this.miSnapCaptureActivity.showOverlayView(false);
        this.results = event.returnIntent;
        Utils.sendMsgToUIFragment(this.miSnapCaptureActivity, UI_FRAGMENT_FREEZE_FRAME_ID, this.results.getByteArrayExtra(MiSnapAPI.RESULT_PICTURE_DATA), null, null, null, null);
        gotoState(State.CAPTURED_FRAME);
    }

    public void onEvent(OnCapturedBarcodeEvent event) {
        this.barcodeResult = this.miSnapCaptureActivity.hasBarcode ? event.returnIntent.getStringExtra(MiSnapAPI.RESULT_PDF417_DATA) : null;
    }

    public void onEvent(OnTorchStateEvent event) {
    }

    public void onEvent(OnFrameProcessedEvent event) {
        MisnapError[] values;
        for (MisnapError error : MisnapError.values()) {
            if ((event.frameChecksPassed & error.mask) != error.mask) {
                this.miSnapCaptureActivity.showError(error.errorResId, false);
                return;
            }
        }
    }

    public void onEvent(OnCaptureModeChangedEvent event) {
        boolean z;
        MiSnapIdentityCaptureActivity miSnapIdentityCaptureActivity = this.miSnapCaptureActivity;
        if (event.mode == 2) {
            z = true;
        } else {
            z = false;
        }
        miSnapIdentityCaptureActivity.setCaptureMode(z);
        this.miSnapCaptureActivity.prepareViewForCaptureMode();
        this.isModeSwitching = false;
    }

    private void writeImageToFile() {
        writeDateToFile(this.results.getByteArrayExtra(MiSnapAPI.RESULT_PICTURE_DATA));
    }

    private void writeDateToFile(final byte[] data) {
        this.ioTask = new AsyncTask<Void, Void, Boolean>() {
            final File photo = FileUtils.createTemporaryFile(MiSnapController.this.miSnapCaptureActivity);

            /* access modifiers changed from: protected */
            public Boolean doInBackground(Void... params) {
                return Boolean.valueOf(IOUtils.createFile(this.photo, data));
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Boolean success) {
                if (success.booleanValue()) {
                    MiSnapController.this.resultFile = this.photo.getAbsolutePath();
                    MiSnapController.this.gotoState(State.FINISHED);
                    return;
                }
                MiSnapController.this.gotoState(State.ERROR);
            }

            /* access modifiers changed from: protected */
            public void onCancelled(Boolean aBoolean) {
                if (this.photo != null && this.photo.exists()) {
                    this.photo.delete();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
