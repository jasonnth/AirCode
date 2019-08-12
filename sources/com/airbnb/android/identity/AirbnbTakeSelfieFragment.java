package com.airbnb.android.identity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics.CaptureMode;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.CameraHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PhotoCompressor.PhotoCompressionCallback;
import com.airbnb.android.misnap.TakeSelfieController;
import com.airbnb.android.misnap.TakeSelfieHelpFragment;
import com.airbnb.android.utils.IOUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.p027n2.components.LoadingView;
import icepick.State;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class AirbnbTakeSelfieFragment extends AirFragment {
    public static final int PERMISSION_REQUEST_CAMERA = 1;
    private static final int SECOND_PICTURE_DELAY = 1000;
    private static final String SELFIE_FILE_NAME = "selfie";
    /* access modifiers changed from: private */
    public Camera camera;
    @State
    int cameraID = -1;
    @BindView
    ImageButton captureButton;
    @BindView
    ImageView capturedFullOverlay;
    @BindView
    ImageButton closeButton;
    @BindView
    TextView countdownTextView;
    @BindView
    TextView errorTextView;
    /* access modifiers changed from: private */
    public Animation fadeAnimation;
    @BindView
    ImageView flash;
    private final Handler handler = new Handler();
    @State
    boolean hasFrontCamera;
    private IdentityJitneyLogger identityJitneyLogger;
    @BindView
    LoadingView loader;
    /* access modifiers changed from: private */
    public int oritentationOffset;
    @BindView
    View overlayView;
    private PhotoCompressor photoCompressor;
    /* access modifiers changed from: private */
    public final PictureCallback pictureCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (bitmap == null) {
                AirbnbTakeSelfieFragment.this.finishWithCaptureError();
                return;
            }
            int rotation = AirbnbTakeSelfieFragment.this.oritentationOffset;
            if (AirbnbTakeSelfieFragment.this.hasFrontCamera) {
                rotation += 180;
            }
            Bitmap bitmap2 = ImageUtils.rotateIfNeeded(bitmap, rotation);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap2.compress(CompressFormat.JPEG, 100, stream);
            AirbnbTakeSelfieFragment.this.selfies.add(stream.toByteArray());
            AirbnbTakeSelfieFragment.this.writeAndCompressImages();
        }
    };
    PreviewCallback previewCallback = new PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Parameters parameters = camera.getParameters();
            int width = parameters.getPreviewSize().width;
            int height = parameters.getPreviewSize().height;
            YuvImage yuv = new YuvImage(data, parameters.getPreviewFormat(), width, height, null);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            yuv.compressToJpeg(new Rect(0, 0, width, height), 50, out);
            byte[] bytes = out.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if (bitmap == null) {
                AirbnbTakeSelfieFragment.this.finishWithCaptureError();
                return;
            }
            int rotation = AirbnbTakeSelfieFragment.this.oritentationOffset;
            if (AirbnbTakeSelfieFragment.this.hasFrontCamera) {
                rotation += 180;
            }
            Bitmap bitmap2 = ImageUtils.rotateIfNeeded(bitmap, rotation);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap2.compress(CompressFormat.JPEG, 100, stream);
            AirbnbTakeSelfieFragment.this.selfies.add(stream.toByteArray());
        }
    };
    @BindView
    FrameLayout selfieCaptureContainer;
    /* access modifiers changed from: private */
    public int selfieCount;
    /* access modifiers changed from: private */
    public ArrayList<String> selfiePaths;
    @BindView
    TextView selfieTip;
    /* access modifiers changed from: private */
    public ArrayList<byte[]> selfies;
    private final Runnable takePictureAndFinish = new Runnable() {
        public void run() {
            AirbnbTakeSelfieFragment.this.flash.setAnimation(AirbnbTakeSelfieFragment.this.fadeAnimation);
            AirbnbTakeSelfieFragment.this.countdownTextView.setText("");
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6533R.layout.fragment_airbnb_take_selfie, container, false);
        bindViews(view);
        this.captureButton.setEnabled(false);
        this.identityJitneyLogger = ((AirbnbTakeSelfieActivity) getActivity()).getIdentityJitneyLogger();
        this.identityJitneyLogger.logImpression(null, this.mAccountManager.getCurrentUser(), IdentityVerificationType.SELFIE, Page.selfie_camera);
        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.photoCompressor = new PhotoCompressor(getContext());
        this.selfies = new ArrayList<>();
        this.selfiePaths = new ArrayList<>();
        setUpFlash();
    }

    public void onResume() {
        super.onResume();
        requestRuntimePermissions();
    }

    public void onPause() {
        this.handler.removeCallbacksAndMessages(null);
        super.onPause();
    }

    public void onAttach(Context context) {
        Check.argument(context instanceof TakeSelfieViewContainer, "TakeSelfieFragment must attach to an TakeSelfieViewContainer");
        super.onAttach(context);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length == 0 || grantResults[0] != 0) {
                    getActivity().finish();
                    break;
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
    }

    private void requestRuntimePermissions() {
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.CAMERA"}, 1);
            return;
        }
        if (this.cameraID == -1) {
            setCameraID();
        }
        initializeCamera();
    }

    private boolean hasPermissions() {
        return ContextCompat.checkSelfPermission(getActivity(), "android.permission.CAMERA") == 0;
    }

    private void setCameraID() {
        this.cameraID = CameraHelper.getFrontCameraID();
        if (this.cameraID == -1) {
            this.cameraID = CameraHelper.getBackCameraID();
        } else {
            this.hasFrontCamera = true;
        }
    }

    private void initializeCamera() {
        if (this.cameraID != -1) {
            try {
                this.camera = Camera.open(this.cameraID);
            } catch (RuntimeException e) {
                BugsnagWrapper.throwOrNotify(e);
                Intent intent = new Intent();
                intent.putExtra("error", -4);
                getActivity().setResult(0, intent);
                getActivity().finish();
            }
        }
        if (this.camera != null) {
            this.oritentationOffset = CameraHelper.getOrientationOffset(getActivity(), this.cameraID);
            this.camera.setDisplayOrientation(this.oritentationOffset);
            this.selfieCaptureContainer.addView(new CameraSurfaceView(getContext(), this.camera));
            this.captureButton.setEnabled(true);
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("error", -2);
        getActivity().setResult(0, intent2);
        getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void setUpFlash() {
        this.fadeAnimation = AnimationUtils.loadAnimation(getContext(), C6533R.anim.fade_in_out);
        this.fadeAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation a) {
                AirbnbTakeSelfieFragment.this.flash.setVisibility(0);
            }

            public void onAnimationRepeat(Animation a) {
            }

            public void onAnimationEnd(Animation a) {
                AirbnbTakeSelfieFragment.this.flash.setVisibility(4);
                try {
                    AirbnbTakeSelfieFragment.this.camera.takePicture(null, null, AirbnbTakeSelfieFragment.this.pictureCallback);
                    AirbnbTakeSelfieFragment.this.showLoader();
                } catch (RuntimeException e) {
                    BugsnagWrapper.throwOrNotify(e);
                    ErrorUtils.showErrorUsingSnackbar(AirbnbTakeSelfieFragment.this.getView(), C6533R.string.account_verification_selfie_take_picture_error);
                    AirbnbTakeSelfieFragment.this.captureButton.setVisibility(0);
                    AirbnbTakeSelfieFragment.this.selfieTip.setVisibility(4);
                    AirbnbTakeSelfieFragment.this.setUpFlash();
                    AirbnbTakeSelfieFragment.this.camera.startPreview();
                }
            }
        });
    }

    @OnClick
    public void onClose() {
        this.identityJitneyLogger.logClick(null, this.mAccountManager.getCurrentUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, Element.navigation_button_cancel);
        this.handler.removeCallbacksAndMessages(null);
        getActivity().finish();
    }

    @OnClick
    public void onHelp() {
        this.identityJitneyLogger.logClick(null, this.mAccountManager.getCurrentUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, Element.button_help);
        showOverlayView(false);
        showFragment(TakeSelfieHelpFragment.newInstance(), C6533R.C6535id.selfie_capture_container, FragmentTransitionType.SlideFromBottom, true);
    }

    @OnClick
    public void onCapture() {
        this.identityJitneyLogger.logClick(null, this.mAccountManager.getCurrentUser(), IdentityVerificationType.SELFIE, Page.selfie_camera, Element.button_take_photo);
        this.handler.removeCallbacksAndMessages(null);
        this.captureButton.setVisibility(4);
        this.selfieTip.setVisibility(0);
        startCountdown();
    }

    private void showOverlayView(boolean show) {
        ViewUtils.setVisibleIf(this.overlayView, show);
    }

    private void startCountdown() {
        this.camera.setOneShotPreviewCallback(this.previewCallback);
        showLoader();
        this.handler.postDelayed(this.takePictureAndFinish, 1000);
    }

    /* access modifiers changed from: private */
    public void writeAndCompressImages() {
        new AsyncTask<Void, Void, Boolean>() {
            /* access modifiers changed from: protected */
            public Boolean doInBackground(Void... params) {
                boolean success = true;
                AirbnbTakeSelfieFragment.this.selfieCount = 0;
                while (AirbnbTakeSelfieFragment.this.selfieCount < AirbnbTakeSelfieFragment.this.selfies.size()) {
                    if (!success || !IOUtils.createFile(AirbnbTakeSelfieFragment.this.getSelfieFile(AirbnbTakeSelfieFragment.this.getContext(), AirbnbTakeSelfieFragment.this.selfieCount), (byte[]) AirbnbTakeSelfieFragment.this.selfies.get(AirbnbTakeSelfieFragment.this.selfieCount))) {
                        success = false;
                    } else {
                        success = true;
                    }
                    AirbnbTakeSelfieFragment.this.selfieCount = AirbnbTakeSelfieFragment.this.selfieCount + 1;
                }
                return Boolean.valueOf(success);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Boolean success) {
                if (success.booleanValue()) {
                    AccountVerificationAnalytics.trackRequestSuccess(NavigationTag.VerificationSelfieCameraAirbnb, "take_selfie_button", Strap.make().mo11639kv("provider", CaptureMode.Airbnb.name()));
                    AirbnbTakeSelfieFragment.this.compressAllImages();
                    return;
                }
                AirbnbTakeSelfieFragment.this.finishWithCaptureError();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void compressAllImages() {
        PhotoCompressionCallback callback = new PhotoCompressionCallback() {
            public void onPhotoCompressed(String filePath) {
                AirbnbTakeSelfieFragment.this.selfiePaths.add(filePath);
                if (AirbnbTakeSelfieFragment.this.selfiePaths.size() == AirbnbTakeSelfieFragment.this.selfies.size()) {
                    Activity activity = AirbnbTakeSelfieFragment.this.getActivity();
                    if (activity != null) {
                        Intent intent = new Intent();
                        intent.putStringArrayListExtra(TakeSelfieController.SELFIE_PATHS_EXTRA, AirbnbTakeSelfieFragment.this.selfiePaths);
                        activity.setResult(-1, intent);
                        activity.finish();
                    }
                }
            }

            public void onCompressionFailure() {
                AirbnbTakeSelfieFragment.this.finishWithCaptureError();
            }
        };
        this.selfieCount = 0;
        while (this.selfieCount < this.selfies.size()) {
            this.photoCompressor.compressPhoto(Uri.fromFile(getSelfieFile(getContext(), this.selfieCount)), callback);
            this.selfieCount++;
        }
    }

    /* access modifiers changed from: private */
    public void showLoader() {
        ViewUtils.setVisibleIf((View) this.loader, true);
    }

    /* access modifiers changed from: private */
    public File getSelfieFile(Context context, int selfieCount2) {
        return new File(context.getCacheDir(), "selfie" + selfieCount2);
    }

    /* access modifiers changed from: private */
    public void finishWithCaptureError() {
        Intent intent = new Intent();
        intent.putExtra("error", -3);
        getActivity().setResult(0, intent);
        getActivity().finish();
    }
}
