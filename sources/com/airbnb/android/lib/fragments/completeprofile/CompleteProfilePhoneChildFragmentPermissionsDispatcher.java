package com.airbnb.android.lib.fragments.completeprofile;

import permissions.dispatcher.PermissionUtils;

final class CompleteProfilePhoneChildFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_FETCHPHONENUMBER = {"android.permission.READ_PHONE_STATE"};
    private static final int REQUEST_FETCHPHONENUMBER = 3;

    private CompleteProfilePhoneChildFragmentPermissionsDispatcher() {
    }

    static void fetchPhoneNumberWithCheck(CompleteProfilePhoneChildFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_FETCHPHONENUMBER)) {
            target.fetchPhoneNumber();
        } else {
            target.requestPermissions(PERMISSION_FETCHPHONENUMBER, 3);
        }
    }

    static void onRequestPermissionsResult(CompleteProfilePhoneChildFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 3:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.fetchPhoneNumber();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
