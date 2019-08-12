package com.jumio;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import com.jumio.commons.camera.CameraManager;
import com.jumio.commons.log.Log;
import com.jumio.core.exceptions.MissingPermissionException;
import com.jumio.core.exceptions.PlatformNotSupportedException;
import com.jumio.core.util.DeviceUtil;
import com.jumio.sdk.models.CredentialsModel;
import java.util.ArrayList;
import java.util.List;

public abstract class MobileSDK {
    private static int MEGABYTES_IN_BYTES = 1048576;
    private static int MEMORY_WARNING_THRESHOLD_IN_MB = 16;
    private int mCustomThemeId = 0;

    public abstract void start() throws MissingPermissionException;

    public static String getSDKVersion() {
        return "JMSDK 2.4.0 (0-55)";
    }

    protected static boolean isSupportedPlatform(boolean useIntel) {
        try {
            return checkSDKRequirements(useIntel);
        } catch (Exception e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public Intent getIntent(Context packageContext, Class intentClass, CredentialsModel credentialsModel) throws MissingPermissionException {
        Intent intent = new Intent(packageContext, intentClass);
        intent.putExtra(MobileActivity.EXTRA_CREDENTIALS, credentialsModel);
        if (this.mCustomThemeId != 0) {
            intent.putExtra(MobileActivity.EXTRA_CUSTOM_THEME, this.mCustomThemeId);
        }
        return intent;
    }

    public void setCustomTheme(int themeResourceId) {
        this.mCustomThemeId = themeResourceId;
    }

    protected static boolean checkSDKRequirements(boolean useIntel) throws PlatformNotSupportedException {
        if (VERSION.SDK_INT < 16) {
            throw new PlatformNotSupportedException("SDK Version 16 required");
        } else if (CameraManager.getNumberOfCameras() == 0) {
            throw new PlatformNotSupportedException("No useable camera present");
        } else if (!DeviceUtil.isSupportedPlatform(useIntel)) {
            throw new PlatformNotSupportedException("ARMv7 CPU Architecture with NEON Intrinsics required");
        } else {
            checkMemoryAllocation();
            return true;
        }
    }

    protected static void checkMemoryAllocation() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory() / ((long) MEGABYTES_IN_BYTES);
        long maxMemory = runtime.maxMemory() / ((long) MEGABYTES_IN_BYTES);
        long freeMemory = maxMemory - totalMemory;
        if (freeMemory < ((long) MEMORY_WARNING_THRESHOLD_IN_MB)) {
            Log.m1927w("Critical memory warning: Heap situation " + totalMemory + "/" + maxMemory + "MB , free " + freeMemory + "MB");
        }
    }

    public static String[] getRequiredPermissions() {
        return new String[]{"android.permission.CAMERA"};
    }

    public static boolean hasAllRequiredPermissions(Context context) {
        return hasPermissionsFor(context, getRequiredPermissions());
    }

    public static boolean hasPermissionsFor(Context context, String[] names) {
        for (String p : names) {
            if (ContextCompat.checkSelfPermission(context, p) != 0) {
                return false;
            }
        }
        return true;
    }

    public static String[] getMissingPermissions(Context context) {
        String[] requiredPermissions;
        if (hasAllRequiredPermissions(context)) {
            return new String[0];
        }
        List<String> neededPerms = new ArrayList<>();
        for (String p : getRequiredPermissions()) {
            if (ContextCompat.checkSelfPermission(context, p) != 0) {
                neededPerms.add(p);
            }
        }
        return (String[]) neededPerms.toArray(new String[neededPerms.size()]);
    }

    public synchronized void destroy() {
        System.gc();
    }
}
