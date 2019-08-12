package com.airbnb.android.cohosting.fragments;

import permissions.dispatcher.PermissionUtils;

final class CohostingInviteFriendWithFeeOptionFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_ASKFORPERMISSIONANDPICKCONTACT = {"android.permission.READ_CONTACTS"};
    private static final int REQUEST_ASKFORPERMISSIONANDPICKCONTACT = 0;

    private CohostingInviteFriendWithFeeOptionFragmentPermissionsDispatcher() {
    }

    static void askForPermissionAndPickContactWithCheck(CohostingInviteFriendWithFeeOptionFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_ASKFORPERMISSIONANDPICKCONTACT)) {
            target.askForPermissionAndPickContact();
        } else {
            target.requestPermissions(PERMISSION_ASKFORPERMISSIONANDPICKCONTACT, 0);
        }
    }

    static void onRequestPermissionsResult(CohostingInviteFriendWithFeeOptionFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.askForPermissionAndPickContact();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
