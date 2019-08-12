package com.airbnb.android.airmapview;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.PermissionChecker;

final class RuntimePermissionUtils {
    private static final String[] LOCATION_PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static final byte LOCATION_PERMISSION_REQUEST_CODE = 1;

    private RuntimePermissionUtils() {
    }

    private static boolean verifyPermissions(int... grantResults) {
        for (int result : grantResults) {
            if (result != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasSelfPermissions(Context context, String... permissions2) {
        for (String permission : permissions2) {
            if (PermissionChecker.checkSelfPermission(context, permission) == 0) {
                return true;
            }
        }
        return false;
    }

    static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions2) {
        for (String permission : permissions2) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    static boolean checkLocationPermissions(Activity targetActivity, AirMapInterface airMapInterface) {
        if (hasSelfPermissions(targetActivity, LOCATION_PERMISSIONS)) {
            airMapInterface.onLocationPermissionsGranted();
            return true;
        }
        if (VERSION.SDK_INT >= 23) {
            targetActivity.requestPermissions(LOCATION_PERMISSIONS, 1);
        }
        return false;
    }

    static void onRequestPermissionsResult(AirMapInterface airMapInterface, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (verifyPermissions(grantResults)) {
                    airMapInterface.onLocationPermissionsGranted();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
