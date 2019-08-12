package com.google.android.gms.internal;

import com.google.android.gms.common.zze;

public class zzsb {
    public static final String VERSION = String.valueOf(zze.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    public static final String zzadQ;

    static {
        String str = "ma";
        String valueOf = String.valueOf(VERSION);
        zzadQ = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }
}
