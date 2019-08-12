package com.airbnb.android.explore.fragments;

import android.support.p000v4.app.Fragment;
import permissions.dispatcher.PermissionUtils;

final class MTExploreParentFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_SETUPANDCONNECTLOCATIONCLIENT = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final int REQUEST_SETUPANDCONNECTLOCATIONCLIENT = 2;

    private MTExploreParentFragmentPermissionsDispatcher() {
    }

    static void setupAndConnectLocationClientWithCheck(MTExploreParentFragment target) {
        if (PermissionUtils.hasSelfPermissions(target.getActivity(), PERMISSION_SETUPANDCONNECTLOCATIONCLIENT)) {
            target.setupAndConnectLocationClient();
        } else {
            target.requestPermissions(PERMISSION_SETUPANDCONNECTLOCATIONCLIENT, 2);
        }
    }

    static void onRequestPermissionsResult(MTExploreParentFragment target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.setupAndConnectLocationClient();
                    return;
                } else if (!PermissionUtils.shouldShowRequestPermissionRationale((Fragment) target, PERMISSION_SETUPANDCONNECTLOCATIONCLIENT)) {
                    target.onLocationPermissionPermanentlyDenied();
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
