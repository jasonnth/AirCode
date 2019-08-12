package permissions.dispatcher;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.app.AppOpsManagerCompat;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.PermissionChecker;
import android.support.p000v4.util.SimpleArrayMap;

public final class PermissionUtils {
    private static final SimpleArrayMap<String, Integer> MIN_SDK_PERMISSIONS = new SimpleArrayMap<>(8);

    static {
        MIN_SDK_PERMISSIONS.put("com.android.voicemail.permission.ADD_VOICEMAIL", Integer.valueOf(14));
        MIN_SDK_PERMISSIONS.put("android.permission.BODY_SENSORS", Integer.valueOf(20));
        MIN_SDK_PERMISSIONS.put("android.permission.READ_CALL_LOG", Integer.valueOf(16));
        MIN_SDK_PERMISSIONS.put("android.permission.READ_EXTERNAL_STORAGE", Integer.valueOf(16));
        MIN_SDK_PERMISSIONS.put("android.permission.USE_SIP", Integer.valueOf(9));
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_CALL_LOG", Integer.valueOf(16));
        MIN_SDK_PERMISSIONS.put("android.permission.SYSTEM_ALERT_WINDOW", Integer.valueOf(23));
        MIN_SDK_PERMISSIONS.put("android.permission.WRITE_SETTINGS", Integer.valueOf(23));
    }

    public static boolean verifyPermissions(int... grantResults) {
        if (grantResults.length == 0) {
            return false;
        }
        for (int result : grantResults) {
            if (result != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean permissionExists(String permission) {
        Integer minVersion = (Integer) MIN_SDK_PERMISSIONS.get(permission);
        return minVersion == null || VERSION.SDK_INT >= minVersion.intValue();
    }

    public static boolean hasSelfPermissions(Context context, String... permissions2) {
        for (String permission : permissions2) {
            if (permissionExists(permission) && !hasSelfPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasSelfPermission(Context context, String permission) {
        if (VERSION.SDK_INT >= 23 && "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER)) {
            return hasSelfPermissionForXiaomi(context, permission);
        }
        try {
            if (PermissionChecker.checkSelfPermission(context, permission) == 0) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static boolean hasSelfPermissionForXiaomi(Context context, String permission) {
        String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
        if (permissionToOp == null) {
            return true;
        }
        if (AppOpsManagerCompat.noteOp(context, permissionToOp, Process.myUid(), context.getPackageName()) == 0 && PermissionChecker.checkSelfPermission(context, permission) == 0) {
            return true;
        }
        return false;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String... permissions2) {
        for (String permission : permissions2) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    public static boolean shouldShowRequestPermissionRationale(Fragment fragment, String... permissions2) {
        for (String permission : permissions2) {
            if (fragment.shouldShowRequestPermissionRationale(permission)) {
                return true;
            }
        }
        return false;
    }
}
