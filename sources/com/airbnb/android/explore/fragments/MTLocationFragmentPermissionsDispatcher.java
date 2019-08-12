package com.airbnb.android.explore.fragments;

import permissions.dispatcher.PermissionUtils;

final class MTLocationFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_SETUPANDCONNECTLOCATIONCLIENT = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final int REQUEST_SETUPANDCONNECTLOCATIONCLIENT = 0;

    private MTLocationFragmentPermissionsDispatcher() {
    }

    static void setupAndConnectLocationClientWithCheck(MTLocationFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_SETUPANDCONNECTLOCATIONCLIENT)) {
            target.setupAndConnectLocationClient();
        } else {
            target.requestPermissions(PERMISSION_SETUPANDCONNECTLOCATIONCLIENT, 0);
        }
    }

    static void onRequestPermissionsResult(MTLocationFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 0:
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
