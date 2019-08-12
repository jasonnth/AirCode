package com.airbnb.android.core.utils;

import android.os.Build;
import android.util.Log;
import com.airbnb.android.utils.ApplicationBuildConfig;

public final class BuildHelper {
    private BuildHelper() {
    }

    public static void debugLog(String tag, String message) {
        if (!isReleaseBuild()) {
            Log.d(tag, message);
        }
    }

    public static void debugErrorLog(String tag, String message, Exception exception) {
        if (!isReleaseBuild()) {
            Log.e(tag, message, exception);
        }
    }

    public static boolean isReleaseBuild() {
        return !ApplicationBuildConfig.DEBUG;
    }

    public static boolean isDevelopmentBuild() {
        return ApplicationBuildConfig.DEBUG;
    }

    public static String buildType() {
        return ApplicationBuildConfig.BUILD_TYPE;
    }

    public static int versionCode() {
        return ApplicationBuildConfig.VERSION_CODE;
    }

    public static String versionName() {
        return ApplicationBuildConfig.VERSION_NAME;
    }

    public static String applicationId() {
        return ApplicationBuildConfig.APPLICATION_ID;
    }

    public static String chinaInstallTag() {
        return ApplicationBuildConfig.CHINA_INSTALL_TAG;
    }

    public static String gitSha() {
        return ApplicationBuildConfig.GIT_SHA;
    }

    public static String gitBranch() {
        return ApplicationBuildConfig.GIT_BRANCH;
    }

    public static boolean optEnabled(String opt) {
        return isDebugFeaturesEnabled() && Log.isLoggable(opt, 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0020, code lost:
        if (com.airbnb.android.core.utils.DebugSettings.FUTURE_MODE.isEnabled() != false) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isFuture() {
        /*
            java.lang.String r0 = "future"
            boolean r0 = optEnabled(r0)
            if (r0 != 0) goto L_0x0022
            boolean r0 = isDebugFeaturesEnabled()
            if (r0 == 0) goto L_0x0024
            com.airbnb.android.core.CoreApplicationFacade r0 = com.airbnb.android.core.CoreApplication.instance()
            com.airbnb.android.core.BaseGraph r0 = r0.component()
            r0.debugSettings()
            com.airbnb.android.core.utils.DebugSettings$BooleanDebugSetting r0 = com.airbnb.android.core.utils.DebugSettings.FUTURE_MODE
            boolean r0 = r0.isEnabled()
            if (r0 == 0) goto L_0x0024
        L_0x0022:
            r0 = 1
        L_0x0023:
            return r0
        L_0x0024:
            r0 = 0
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.core.utils.BuildHelper.isFuture():boolean");
    }

    public static boolean isAmazonDevice() {
        return "amazon".equalsIgnoreCase(Build.MANUFACTURER);
    }

    public static boolean isDebugFeaturesEnabled() {
        return isDevelopmentBuild() || isQa() || isAlpha();
    }

    public static boolean isInternalSettingsEnabled() {
        return isDebugFeaturesEnabled() || Trebuchet.launch(TrebuchetKeys.INTERNAL_SETTINGS_ENABLED, false);
    }

    public static boolean isAlpha() {
        return buildType().equals("alpha");
    }

    public static boolean isQa() {
        return buildType().equals("qa");
    }

    public static boolean isBeta() {
        return buildType().equals("beta");
    }

    public static boolean isChina() {
        return buildType().equals("china");
    }
}
