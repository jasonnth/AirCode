package com.airbnb.android.photopicker;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;

public class CameraUtils {
    public static boolean isCameraAvailable(Context context) {
        boolean handlesIntent;
        PackageManager pm = context.getPackageManager();
        if (!pm.queryIntentActivities(new Intent("android.media.action.IMAGE_CAPTURE"), 65536).isEmpty()) {
            handlesIntent = true;
        } else {
            handlesIntent = false;
        }
        if (VERSION.SDK_INT < 17) {
            return handlesIntent;
        }
        if (!pm.hasSystemFeature("android.hardware.camera.any") || !handlesIntent) {
            return false;
        }
        return true;
    }

    static Intent createCameraIntent(Context context, Uri uri) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uri);
        grantPermission(context, intent, uri);
        return intent;
    }

    private static void grantPermission(Context context, Intent intent, Uri uri) {
        if (VERSION.SDK_INT <= 22) {
            for (ResolveInfo resolvedIntentInfo : context.getPackageManager().queryIntentActivities(intent, 65536)) {
                context.grantUriPermission(resolvedIntentInfo.activityInfo.packageName, uri, 3);
            }
        }
    }
}
