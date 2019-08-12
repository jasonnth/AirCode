package com.airbnb.android.misnap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics.CaptureMode;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PhotoCompressor.PhotoCompressionCallback;
import com.airbnb.android.utils.IOUtils;
import com.airbnb.android.utils.Strap;
import com.miteksystems.misnap.params.MiSnapAPI;
import java.io.File;
import java.util.ArrayList;

public class TakeSelfieController {
    private static final int AIRBNB_TAKE_SELFIE = 88;
    public static final int CAMERA_ACCESS_ERROR = -4;
    public static final int IMAGE_CAPTURE_ERROR = -3;
    public static final int NO_CAMERA_ERROR = -2;
    public static final String SELFIE_ACTIVITY_ERROR = "error";
    private static final String SELFIE_FILE_NAME = "selfie";
    public static final String SELFIE_PATHS_EXTRA = "selfie_paths";
    /* access modifiers changed from: private */
    public final CaptureMode captureMode;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public TakeSelfieListener listener;
    private final PhotoCompressor photoCompressor;

    public interface TakeSelfieListener {
        void onCameraAccessError();

        void onMultipleSelfiePhotosReady(ArrayList<String> arrayList);

        void onNoCamera();

        void onSelfieCompressFailed();

        void onSelfieCompressStart();

        void onSelfiePhotoReady(String str);

        void startActivityForResult(Intent intent, int i);
    }

    public TakeSelfieController(Context context2, PhotoCompressor photoCompressor2) {
        this.context = context2;
        this.photoCompressor = photoCompressor2;
        this.captureMode = FeatureToggles.useAirbnbSelfie() ? CaptureMode.Airbnb : CaptureMode.Mitek;
    }

    public void init(TakeSelfieListener takeSelfieListener) {
        this.listener = takeSelfieListener;
    }

    public void startTakeSelfieIntent(Context context2) {
        if (this.captureMode == CaptureMode.Mitek) {
            this.listener.startActivityForResult(new Intent(context2, MiSnapTakeSelfieActivity.class), 3);
        } else {
            this.listener.startActivityForResult(new Intent(context2, Activities.airbnbTakeSelfie()), 88);
        }
        AccountVerificationAnalytics.trackRequestStart(this.captureMode == CaptureMode.Mitek ? NavigationTag.VerificationSelfieCameraMisnap : NavigationTag.VerificationSelfieCameraAirbnb, "take_selfie_button", Strap.make().mo11639kv("provider", this.captureMode.name()));
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3) {
            if (-1 != resultCode || data == null) {
                AccountVerificationAnalytics.trackRequestFailure(NavigationTag.VerificationSelfieCameraMisnap, "take_selfie_button", Strap.make().mo11639kv("provider", this.captureMode.name()));
                return true;
            }
            String miSnapResultCode = data.getExtras().getString(MiSnapAPI.RESULT_CODE);
            if (!MiSnapAPI.RESULT_SUCCESS_VIDEO.equals(miSnapResultCode) && !MiSnapAPI.RESULT_SUCCESS_STILL.equals(miSnapResultCode)) {
                return true;
            }
            compressImage(data.getByteArrayExtra(MiSnapAPI.RESULT_PICTURE_DATA), requestCode);
            return true;
        } else if (requestCode != 88) {
            return false;
        } else {
            if (data == null) {
                return true;
            }
            if (resultCode == -1) {
                this.listener.onMultipleSelfiePhotosReady(data.getStringArrayListExtra(SELFIE_PATHS_EXTRA));
                return true;
            } else if (data.getIntExtra("error", 0) == -2) {
                this.listener.onNoCamera();
                return true;
            } else if (data.getIntExtra("error", 0) == -4) {
                this.listener.onCameraAccessError();
                return true;
            } else if (data.getIntExtra("error", 0) != -3) {
                return true;
            } else {
                this.listener.onSelfieCompressFailed();
                AccountVerificationAnalytics.trackRequestFailure(NavigationTag.VerificationSelfieCameraAirbnb, "take_selfie_button", Strap.make().mo11639kv("provider", this.captureMode.name()));
                return true;
            }
        }
    }

    private void compressImage(final byte[] image, final int requestCode) {
        new AsyncTask<Void, Void, Boolean>() {
            /* access modifiers changed from: protected */
            public Boolean doInBackground(Void... params) {
                return Boolean.valueOf(IOUtils.createFile(TakeSelfieController.getSelfieFile(TakeSelfieController.this.context), image));
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Boolean success) {
                if (success.booleanValue()) {
                    AccountVerificationAnalytics.trackRequestSuccess(requestCode == 3 ? NavigationTag.VerificationSelfieCameraMisnap : NavigationTag.VerificationSelfieCameraAirbnb, "take_selfie_button", Strap.make().mo11639kv("provider", TakeSelfieController.this.captureMode.name()));
                    TakeSelfieController.this.compressImage();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void compressImage() {
        this.photoCompressor.compressPhoto(Uri.fromFile(getSelfieFile(this.context)), new PhotoCompressionCallback() {
            public void onPhotoCompressed(String filePath) {
                TakeSelfieController.this.listener.onSelfiePhotoReady(filePath);
            }

            public void onCompressionFailure() {
                TakeSelfieController.this.listener.onSelfieCompressFailed();
            }
        });
        this.listener.onSelfieCompressStart();
    }

    public static File getSelfieFile(Context context2) {
        return new File(context2.getCacheDir(), "selfie");
    }

    public CaptureMode getCaptureMode() {
        return this.captureMode;
    }

    public void destroy() {
        this.photoCompressor.cancelCompressionJobs();
        this.listener = null;
    }
}
