package com.facebook.accountkit.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.util.HashSet;
import java.util.TreeSet;

abstract class NativeAppInfo {
    private static final String FBI_HASH = "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc";
    private static final String FBL_HASH = "5e8f16062ea3cd2c4a0d547876baa6f38cabf625";
    private static final String FBR_HASH = "8a3c4b262d721acd49a4bf97d5213199c86fa2b9";
    private static final String TAG = NativeAppInfo.class.getSimpleName();
    private static final HashSet<String> validAppSignatureHashes = buildAppSignatureHashes();
    private boolean appInstalled;
    private TreeSet<Integer> availableVersions;

    /* access modifiers changed from: protected */
    public abstract String getPackage();

    /* access modifiers changed from: protected */
    public abstract Intent getPlatformServiceIntent();

    NativeAppInfo() {
    }

    private static HashSet<String> buildAppSignatureHashes() {
        HashSet<String> set = new HashSet<>();
        set.add(FBR_HASH);
        set.add(FBI_HASH);
        set.add(FBL_HASH);
        return set;
    }

    public boolean validateSignature(Context context, String packageName) {
        String brand = Build.BRAND;
        int applicationFlags = context.getApplicationInfo().flags;
        if (brand.startsWith("generic") && (applicationFlags & 2) != 0) {
            return true;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 64);
            if (packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                return false;
            }
            for (Signature signature : packageInfo.signatures) {
                if (!validAppSignatureHashes.contains(Utility.sha1hash(signature.toByteArray()))) {
                    return false;
                }
            }
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public boolean isAppInstalled() {
        if (this.availableVersions == null) {
            fetchAvailableVersions(false);
        }
        return this.appInstalled;
    }

    public TreeSet<Integer> getAvailableVersions() {
        if (this.availableVersions == null) {
            fetchAvailableVersions(false);
        }
        return this.availableVersions;
    }

    public synchronized void fetchAvailableVersions(boolean force) {
        if (this.availableVersions == null || force) {
            TreeSet<Integer> allAvailableVersions = new TreeSet<>();
            ContentResolver contentResolver = AccountKitController.getApplicationContext().getContentResolver();
            String[] projection = {"version"};
            Uri uri = Uri.parse("content://" + getPackage() + ".provider.PlatformProvider/versions");
            Cursor c = null;
            try {
                ProviderInfo pInfo = null;
                try {
                    pInfo = AccountKitController.getApplicationContext().getPackageManager().resolveContentProvider(getPackage() + ".provider.PlatformProvider", 0);
                } catch (RuntimeException e) {
                    Log.e(TAG, "Failed to query content resolver.", e);
                }
                if (pInfo != null) {
                    try {
                        c = contentResolver.query(uri, projection, null, null, null);
                    } catch (NullPointerException | SecurityException e2) {
                        Log.e(TAG, "Failed to query content resolver.");
                        c = null;
                    }
                    if (c != null) {
                        while (c.moveToNext()) {
                            allAvailableVersions.add(Integer.valueOf(c.getInt(c.getColumnIndex("version"))));
                        }
                    }
                }
                if (c != null) {
                    this.appInstalled = true;
                    c.close();
                }
                this.availableVersions = allAvailableVersions;
            } catch (Throwable th) {
                if (c != null) {
                    this.appInstalled = true;
                    c.close();
                }
                throw th;
            }
        }
    }
}
