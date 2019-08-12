package com.airbnb.android.explore.fragments;

import permissions.dispatcher.PermissionUtils;

final class MTLocationChinaFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_SETUPANDCONNECTLOCATIONCLIENT = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final int REQUEST_SETUPANDCONNECTLOCATIONCLIENT = 1;

    private MTLocationChinaFragmentPermissionsDispatcher() {
    }

    static void setupAndConnectLocationClientWithCheck(MTLocationChinaFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_SETUPANDCONNECTLOCATIONCLIENT)) {
            target.setupAndConnectLocationClient();
        } else {
            target.requestPermissions(PERMISSION_SETUPANDCONNECTLOCATIONCLIENT, 1);
        }
    }

    static void onRequestPermissionsResult(MTLocationChinaFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.setupAndConnectLocationClient();
                    return;
                } else {
                    target.onLocationPermissionsDenied();
                    return;
                }
            default:
                return;
        }
    }
}
