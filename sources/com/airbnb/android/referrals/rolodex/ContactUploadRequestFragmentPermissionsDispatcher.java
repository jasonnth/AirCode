package com.airbnb.android.referrals.rolodex;

import android.support.p000v4.app.Fragment;
import permissions.dispatcher.PermissionUtils;

final class ContactUploadRequestFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_STARTCONTACTUPLOADING = {"android.permission.READ_CONTACTS"};
    private static final int REQUEST_STARTCONTACTUPLOADING = 0;

    private ContactUploadRequestFragmentPermissionsDispatcher() {
    }

    static void startContactUploadingWithCheck(ContactUploadRequestFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_STARTCONTACTUPLOADING)) {
            target.startContactUploading();
        } else {
            target.requestPermissions(PERMISSION_STARTCONTACTUPLOADING, 0);
        }
    }

    static void onRequestPermissionsResult(ContactUploadRequestFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.startContactUploading();
                    return;
                } else if (!PermissionUtils.shouldShowRequestPermissionRationale((Fragment) target, PERMISSION_STARTCONTACTUPLOADING)) {
                    target.onContactsPermissionDenied();
                    return;
                } else {
                    target.onContactsPermissionDenied();
                    return;
                }
            default:
                return;
        }
    }
}
