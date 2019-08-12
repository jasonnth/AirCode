package com.airbnb.android.lib.fragments.verifications;

import permissions.dispatcher.PermissionUtils;

final class PhoneVerificationFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_SETUPPHONENUMBER = {"android.permission.READ_PHONE_STATE"};
    private static final int REQUEST_SETUPPHONENUMBER = 5;

    private PhoneVerificationFragmentPermissionsDispatcher() {
    }

    static void setUpPhoneNumberWithCheck(PhoneVerificationFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_SETUPPHONENUMBER)) {
            target.setUpPhoneNumber();
        } else {
            target.requestPermissions(PERMISSION_SETUPPHONENUMBER, 5);
        }
    }

    static void onRequestPermissionsResult(PhoneVerificationFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 5:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.setUpPhoneNumber();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
