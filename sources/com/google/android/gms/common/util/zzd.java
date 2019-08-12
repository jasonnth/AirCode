package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.google.android.gms.internal.zzadg;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class zzd {
    public static int zzD(Context context, String str) {
        return zzc(zzE(context, str));
    }

    public static PackageInfo zzE(Context context, String str) {
        try {
            return zzadg.zzbi(context).getPackageInfo(str, 128);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static boolean zzF(Context context, String str) {
        "com.google.android.gms".equals(str);
        try {
            return (zzadg.zzbi(context).getApplicationInfo(str, 0).flags & PKIFailureInfo.badSenderNonce) != 0;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static int zzc(PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return -1;
        }
        Bundle bundle = packageInfo.applicationInfo.metaData;
        if (bundle != null) {
            return bundle.getInt("com.google.android.gms.version", -1);
        }
        return -1;
    }
}
