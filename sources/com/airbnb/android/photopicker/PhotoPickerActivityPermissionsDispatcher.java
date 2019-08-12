package com.airbnb.android.photopicker;

import android.support.p000v4.app.ActivityCompat;
import permissions.dispatcher.PermissionUtils;

public final class PhotoPickerActivityPermissionsDispatcher {
    private static final String[] PERMISSION_SHOWCAMERA = {"android.permission.CAMERA"};
    private static final String[] PERMISSION_SHOWGALLERY = {"android.permission.READ_EXTERNAL_STORAGE"};
    private static final int REQUEST_SHOWCAMERA = 0;
    private static final int REQUEST_SHOWGALLERY = 1;

    private PhotoPickerActivityPermissionsDispatcher() {
    }

    public static void showCameraWithCheck(PhotoPickerActivity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCAMERA)) {
            target.showCamera();
        } else {
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, 0);
        }
    }

    public static void showGalleryWithCheck(PhotoPickerActivity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWGALLERY)) {
            target.showGallery();
        } else {
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWGALLERY, 1);
        }
    }

    public static void onRequestPermissionsResult(PhotoPickerActivity target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.showCamera();
                    return;
                } else {
                    target.onCameraPermissionDenied();
                    return;
                }
            case 1:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.showGallery();
                    return;
                } else {
                    target.onShowGalleryPermissionDenied();
                    return;
                }
            default:
                return;
        }
    }
}
