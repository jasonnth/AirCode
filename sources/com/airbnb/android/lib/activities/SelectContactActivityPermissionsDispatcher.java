package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.support.p000v4.app.ActivityCompat;
import permissions.dispatcher.PermissionUtils;

final class SelectContactActivityPermissionsDispatcher {
    private static final String[] PERMISSION_SHOWCONTACTS = {"android.permission.READ_CONTACTS"};
    private static final int REQUEST_SHOWCONTACTS = 0;

    private SelectContactActivityPermissionsDispatcher() {
    }

    static void showContactsWithCheck(SelectContactActivity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCONTACTS)) {
            target.showContacts();
        } else {
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCONTACTS, 0);
        }
    }

    static void onRequestPermissionsResult(SelectContactActivity target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.showContacts();
                    return;
                } else if (!PermissionUtils.shouldShowRequestPermissionRationale((Activity) target, PERMISSION_SHOWCONTACTS)) {
                    target.showContacts();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }
}
