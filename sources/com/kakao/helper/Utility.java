package com.kakao.helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Utility {
    private static final String TAG = Utility.class.getCanonicalName();

    public static PackageInfo getPackageInfo(Context context) {
        return getPackageInfo(context, 0);
    }

    public static PackageInfo getPackageInfo(Context context, int flag) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), flag);
        } catch (NameNotFoundException e) {
            Log.w(TAG, "Unable to get PackageInfo", e);
            return null;
        }
    }

    public static int getAppVersion(Context context) {
        return getPackageInfo(context).versionCode;
    }

    public static String getAppPackageName(Context context) {
        return getPackageInfo(context).packageName;
    }

    public static String getKeyHash(Context context) {
        String str = null;
        PackageInfo packageInfo = getPackageInfo(context, 64);
        if (packageInfo != null) {
            Signature[] signatureArr = packageInfo.signatures;
            int length = signatureArr.length;
            int i = 0;
            while (i < length) {
                Signature signature = signatureArr[i];
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    str = Base64.encodeToString(md.digest(), 2);
                    break;
                } catch (NoSuchAlgorithmException e) {
                    Log.w(TAG, "Unable to get MessageDigest. signature=" + signature, e);
                    i++;
                }
            }
        }
        return str;
    }
}
