package com.airbnb.android.lib.fragments;

import permissions.dispatcher.PermissionUtils;

final class InviteGuestSelectFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_SETUPEMAILSUGGEST = {"android.permission.READ_CONTACTS"};
    private static final int REQUEST_SETUPEMAILSUGGEST = 4;

    private InviteGuestSelectFragmentPermissionsDispatcher() {
    }

    /* access modifiers changed from: 0000 */
    public static void setupEmailSuggestWithCheck(InviteGuestSelectFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_SETUPEMAILSUGGEST)) {
            target.setupEmailSuggest();
        } else {
            target.requestPermissions(PERMISSION_SETUPEMAILSUGGEST, 4);
        }
    }

    static void onRequestPermissionsResult(InviteGuestSelectFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 4:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.setupEmailSuggest();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
