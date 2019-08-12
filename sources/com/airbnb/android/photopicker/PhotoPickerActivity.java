package com.airbnb.android.photopicker;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.content.FileProvider;
import android.support.p002v7.app.AppCompatActivity;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.photopicker.PhotoPicker.Builder;
import java.io.File;

public class PhotoPickerActivity extends AppCompatActivity implements PhotoPickerDialogInterface {
    private static final String ARG_CAMERA_URI = "camera_uri";
    private static final String ARG_PROCESS_CAMERA = "process_camera";
    private static final String ARG_PROCESS_GALLERY = "process_gallery";
    private static final String ARG_SELECTED_IMAGE_URI = "selected_image_uri";
    public static final int REQUEST_CODE_CAMERA = 1000;
    public static final int REQUEST_CODE_GALLERY = 1001;
    private static final String TAG_DIALOG = "dialog";
    private Builder builder;
    private Uri cameraCaptureUri;
    private AsyncTask<Uri, Void, File> mPhotoProcessingTask;
    /* access modifiers changed from: private */
    public ProcessingDialogFragment processingDialogFragment;
    private Uri selectedImageUri;
    boolean shouldProcessCameraCapture = false;
    boolean shouldProcessGalleryCapture = false;
    private boolean showDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.shouldProcessCameraCapture = savedInstanceState.getBoolean(ARG_PROCESS_CAMERA);
            this.cameraCaptureUri = (Uri) savedInstanceState.getParcelable(ARG_CAMERA_URI);
            this.shouldProcessGalleryCapture = savedInstanceState.getBoolean(ARG_PROCESS_GALLERY);
            this.selectedImageUri = (Uri) savedInstanceState.getParcelable(ARG_SELECTED_IMAGE_URI);
        }
        overridePendingTransition(0, 0);
        this.builder = (Builder) getIntent().getSerializableExtra(AccountVerificationStartActivityIntents.EXTRA_BUNDLE);
        if (savedInstanceState == null) {
            switch (this.builder.imageSource) {
                case 0:
                    PhotoPickerDialogFragment.newInstance().show(getSupportFragmentManager(), TAG_DIALOG);
                    return;
                case 1:
                    onSelectCamera();
                    return;
                case 2:
                    onSelectGallery();
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.shouldProcessCameraCapture) {
            processCameraCapture();
        } else if (this.shouldProcessGalleryCapture) {
            processGalleryCapture();
        }
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.showDialog && this.processingDialogFragment == null) {
            ProcessingDialogFragment newInstance = ProcessingDialogFragment.newInstance();
            this.processingDialogFragment = newInstance;
            newInstance.show(getSupportFragmentManager(), TAG_DIALOG);
            this.showDialog = false;
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mPhotoProcessingTask != null) {
            this.mPhotoProcessingTask.cancel(true);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ARG_PROCESS_CAMERA, this.shouldProcessCameraCapture);
        outState.putBoolean(ARG_PROCESS_GALLERY, this.shouldProcessGalleryCapture);
        if (this.cameraCaptureUri != null) {
            outState.putParcelable(ARG_CAMERA_URI, this.cameraCaptureUri);
        }
        if (this.selectedImageUri != null) {
            outState.putParcelable(ARG_SELECTED_IMAGE_URI, this.selectedImageUri);
        }
    }

    public void onSelectCamera() {
        PhotoPickerActivityPermissionsDispatcher.showCameraWithCheck(this);
    }

    /* access modifiers changed from: 0000 */
    public void showCamera() {
        Uri uriForFile = FileProvider.getUriForFile(this, this.builder.applicationId + ".provider", FileUtils.createPhotoFile(this));
        this.cameraCaptureUri = uriForFile;
        startActivityForResult(CameraUtils.createCameraIntent(this, uriForFile), 1000);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        PhotoPickerActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /* access modifiers changed from: 0000 */
    public void onCameraPermissionDenied() {
        onCancel();
    }

    /* access modifiers changed from: 0000 */
    public void onShowGalleryPermissionDenied() {
        onCancel();
    }

    public void onSelectGallery() {
        PhotoPickerActivityPermissionsDispatcher.showGalleryWithCheck(this);
    }

    /* access modifiers changed from: 0000 */
    public void showGallery() {
        startActivityForResult(GalleryUtils.createGalleryIntent(this.builder.allowMultiple), 1001);
    }

    public void onCancel() {
        setResult(0);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            setResult(resultCode);
            finish();
            return;
        }
        this.showDialog = true;
        switch (requestCode) {
            case 1000:
                this.shouldProcessCameraCapture = true;
                return;
            case 1001:
                this.selectedImageUri = data.getData();
                this.shouldProcessGalleryCapture = true;
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private void processGalleryCapture() {
        processUri(this.selectedImageUri);
    }

    private void processCameraCapture() {
        processUri(this.cameraCaptureUri);
        revokePermission();
    }

    private void processUri(Uri captureUri) {
        processUri(captureUri, FileUtils.createPhotoFile(this));
    }

    private void processUri(Uri uri, File outputFile) {
        this.mPhotoProcessingTask = new PhotoProcessingTask(this, outputFile, this.builder.width, this.builder.height) {
            /* access modifiers changed from: protected */
            public void onPostExecute(File file) {
                PhotoPickerActivity.this.shouldProcessCameraCapture = false;
                PhotoPickerActivity.this.shouldProcessGalleryCapture = false;
                if (PhotoPickerActivity.this.processingDialogFragment != null) {
                    PhotoPickerActivity.this.processingDialogFragment.dismiss();
                }
                if (file == null) {
                    PhotoPickerActivity.this.setResult(1);
                    PhotoPickerActivity.this.finish();
                    return;
                }
                PhotoPickerActivity.this.setResult(-1, new Intent().putExtra(PhotoPicker.EXTRA_PHOTO_PATH, file.getAbsolutePath()));
                PhotoPickerActivity.this.finish();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{uri});
    }

    private void revokePermission() {
        if (VERSION.SDK_INT <= 22) {
            revokeUriPermission(this.cameraCaptureUri, 3);
        }
    }
}
