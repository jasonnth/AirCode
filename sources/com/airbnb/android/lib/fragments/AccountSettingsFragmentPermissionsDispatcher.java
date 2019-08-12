package com.airbnb.android.lib.fragments;

import android.support.p000v4.app.Fragment;
import permissions.dispatcher.PermissionUtils;

final class AccountSettingsFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_SHOWACCOUNTSWITCHERDIALOG = {"android.permission.GET_ACCOUNTS"};
    private static final int REQUEST_SHOWACCOUNTSWITCHERDIALOG = 2;

    private AccountSettingsFragmentPermissionsDispatcher() {
    }

    static void showAccountSwitcherDialogWithCheck(AccountSettingsFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_SHOWACCOUNTSWITCHERDIALOG)) {
            target.showAccountSwitcherDialog();
        } else {
            target.requestPermissions(PERMISSION_SHOWACCOUNTSWITCHERDIALOG, 2);
        }
    }

    static void onRequestPermissionsResult(AccountSettingsFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.showAccountSwitcherDialog();
                    return;
                } else if (!PermissionUtils.shouldShowRequestPermissionRationale((Fragment) target, PERMISSION_SHOWACCOUNTSWITCHERDIALOG)) {
                    target.onContactsPermissionNeverAskAgain();
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
