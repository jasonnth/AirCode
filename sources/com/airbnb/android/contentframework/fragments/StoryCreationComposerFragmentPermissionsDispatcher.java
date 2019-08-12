package com.airbnb.android.contentframework.fragments;

import android.support.p000v4.app.Fragment;
import permissions.dispatcher.PermissionUtils;

final class StoryCreationComposerFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_OPENPHOTOPICKER = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_OPENPHOTOPICKER = 0;

    private StoryCreationComposerFragmentPermissionsDispatcher() {
    }

    static void openPhotoPickerWithCheck(StoryCreationComposerFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_OPENPHOTOPICKER)) {
            target.openPhotoPicker();
        } else {
            target.requestPermissions(PERMISSION_OPENPHOTOPICKER, 0);
        }
    }

    static void onRequestPermissionsResult(StoryCreationComposerFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.openPhotoPicker();
                    return;
                } else if (!PermissionUtils.shouldShowRequestPermissionRationale((Fragment) target, PERMISSION_OPENPHOTOPICKER)) {
                    target.onPermissionNeverAskAgain();
                    return;
                } else {
                    target.onPermissionDenied();
                    return;
                }
            default:
                return;
        }
    }
}
