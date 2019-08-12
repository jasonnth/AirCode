package com.bugsnag.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.bugsnag.android.JsonStream.Streamable;
import java.io.IOException;

class AppData implements Streamable {
    protected final String appName;
    private final Configuration config;
    protected final String guessedReleaseStage;
    protected final String packageName;
    protected final Integer versionCode;
    protected final String versionName;

    AppData(Context appContext, Configuration config2) {
        this.config = config2;
        this.packageName = getPackageName(appContext);
        this.appName = getAppName(appContext);
        this.versionCode = getVersionCode(appContext);
        this.versionName = getVersionName(appContext);
        this.guessedReleaseStage = guessReleaseStage(appContext);
    }

    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("id").value(this.packageName);
        writer.name("name").value(this.appName);
        writer.name("packageName").value(this.packageName);
        writer.name("versionName").value(this.versionName);
        writer.name("versionCode").value((Number) this.versionCode);
        writer.name("buildUUID").value(this.config.getBuildUUID());
        writer.name("version").value(getAppVersion());
        writer.name("releaseStage").value(getReleaseStage());
        writer.endObject();
    }

    public String getReleaseStage() {
        if (this.config.getReleaseStage() != null) {
            return this.config.getReleaseStage();
        }
        return this.guessedReleaseStage;
    }

    public String getAppVersion() {
        if (this.config.getAppVersion() != null) {
            return this.config.getAppVersion();
        }
        return this.versionName;
    }

    private static String getPackageName(Context appContext) {
        return appContext.getPackageName();
    }

    private static String getAppName(Context appContext) {
        try {
            PackageManager packageManager = appContext.getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(appContext.getPackageName(), 0));
        } catch (NameNotFoundException e) {
            Logger.warn("Could not get app name");
            return null;
        }
    }

    private static Integer getVersionCode(Context appContext) {
        try {
            return Integer.valueOf(appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            Logger.warn("Could not get versionCode");
            return null;
        }
    }

    private static String getVersionName(Context appContext) {
        try {
            return appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            Logger.warn("Could not get versionName");
            return null;
        }
    }

    private static String guessReleaseStage(Context appContext) {
        try {
            if ((appContext.getPackageManager().getApplicationInfo(appContext.getPackageName(), 0).flags & 2) != 0) {
                return "development";
            }
        } catch (NameNotFoundException e) {
            Logger.warn("Could not get releaseStage");
        }
        return "production";
    }
}
