package com.airbnb.android.core.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import com.airbnb.android.core.ApplicationFacade;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.InvocationTargetException;

public final class AppIdentityVerifier implements PostApplicationCreatedInitializer {
    private static final String SECURITY_CHECK = "security_check";
    private static final Strap appSecurityDataToLog = Strap.make();
    private Context context;

    private AppIdentityVerifier() {
    }

    public AppIdentityVerifier(Context context2) {
        this.context = context2;
    }

    public void initialize() {
        performChecks(this.context);
    }

    public static void performChecks(Context context2) {
        ApplicationFacade application = CoreApplication.instance(context2);
        if (!application.isTestApplication()) {
            SharedPreferences globalSharePreference = application.component().airbnbPreferences().getGlobalSharedPreferences();
            if (!globalSharePreference.getBoolean("security_check", false)) {
                globalSharePreference.edit().putBoolean("security_check", true).apply();
                if (BuildHelper.isReleaseBuild()) {
                    checkAppSignature(context2);
                    checkEmulator();
                    verifyInstaller(context2);
                    checkDebuggable(context2);
                    AirbnbEventLogger.track("security_check", appSecurityDataToLog);
                }
            }
        }
    }

    private static void checkAppSignature(Context context2) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context2.getPackageManager().getPackageInfo(context2.getPackageName(), 64);
        } catch (NameNotFoundException e) {
            appSecurityDataToLog.mo11639kv("release_signature_match", "NameNotFoundException");
            BugsnagWrapper.notify((Throwable) new RuntimeException("Error checking signatures " + e.getMessage()));
        }
        if (packageInfo != null && packageInfo.signatures != null) {
            for (Signature signature : packageInfo.signatures) {
                String currentSignature = Base64.encodeToString(MiscUtils.hashWithAlgorithm(signature.toByteArray(), "SHA"), 0).replace("\n", "");
                boolean signatureMatch = context2.getString(C0716R.string.signature_hash).equals(currentSignature);
                if (!signatureMatch) {
                    BugsnagWrapper.notify((Throwable) new SecurityException("Release build is signed with wrong key: " + currentSignature));
                }
                appSecurityDataToLog.mo11640kv("release_signature_match", signatureMatch).mo11639kv("release_signature_received", currentSignature);
            }
        }
    }

    private static void verifyInstaller(Context context2) {
        String installer = ExternalAppUtils.getInstallerPackage(context2);
        appSecurityDataToLog.mo11639kv("verify_installer_name", installer).mo11640kv("verify_installer_known", ExternalAppUtils.isKnownInstaller(context2));
    }

    private static String getSystemProperty(String name) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> systemPropertyClazz = Class.forName("android.os.SystemProperties");
        return (String) systemPropertyClazz.getMethod("get", new Class[]{String.class}).invoke(systemPropertyClazz, new Object[]{name});
    }

    private static void checkEmulator() {
        boolean isEmulator = false;
        try {
            boolean goldfish = getSystemProperty("ro.hardware").contains("goldfish");
            boolean emu = getSystemProperty("ro.kernel.qemu").length() > 0;
            boolean sdk = getSystemProperty("ro.product.model").equals("sdk");
            if (emu || goldfish || sdk) {
                isEmulator = true;
            }
            if (isEmulator) {
                appSecurityDataToLog.mo11639kv("check_emulator_isEmulator", "goldfish : " + goldfish + " emu : " + emu + " sdk : " + sdk);
            } else {
                appSecurityDataToLog.mo11640kv("check_emulator_isEmulator", false);
            }
        } catch (Exception es) {
            BugsnagWrapper.notify((Throwable) new RuntimeException("Error checking emulator " + es.getMessage()));
            appSecurityDataToLog.mo11639kv("check_emulator_isEmulator", "error");
        }
    }

    private static void checkDebuggable(Context context2) {
        appSecurityDataToLog.mo11640kv("check_debuggable", (context2.getApplicationInfo().flags & 2) != 0);
    }
}
