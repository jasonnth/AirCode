package com.airbnb.android.lib.share;

import permissions.dispatcher.PermissionUtils;

final class ShareYourTripToWechatFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_SHARETOWECHAT = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_SHARETOWECHAT = 1;

    private ShareYourTripToWechatFragmentPermissionsDispatcher() {
    }

    static void shareToWechatWithCheck(ShareYourTripToWechatFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_SHARETOWECHAT)) {
            target.shareToWechat();
        } else {
            target.requestPermissions(PERMISSION_SHARETOWECHAT, 1);
        }
    }

    static void onRequestPermissionsResult(ShareYourTripToWechatFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.shareToWechat();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
