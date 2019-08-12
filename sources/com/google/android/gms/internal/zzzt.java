package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

public class zzzt implements com.google.android.gms.internal.zzzk.zzb {
    private static final Charset UTF_8 = Charset.forName(JPushConstants.ENCODING_UTF_8);
    static Boolean zzaxU = null;
    final zza zzaxV;

    static class zza {
        final ContentResolver mContentResolver;

        zza(Context context) {
            if (context == null || !zzaB(context)) {
                this.mContentResolver = null;
                return;
            }
            this.mContentResolver = context.getContentResolver();
            zzble.zzb(this.mContentResolver, "gms:playlog:service:sampling_");
        }

        private static boolean zzaB(Context context) {
            if (zzzt.zzaxU == null) {
                zzzt.zzaxU = Boolean.valueOf(zzadg.zzbi(context).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
            }
            return zzzt.zzaxU.booleanValue();
        }

        /* access modifiers changed from: 0000 */
        public String zzdb(String str) {
            if (this.mContentResolver == null) {
                return null;
            }
            ContentResolver contentResolver = this.mContentResolver;
            String valueOf = String.valueOf("gms:playlog:service:sampling_");
            String valueOf2 = String.valueOf(str);
            return zzble.zza(contentResolver, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), (String) null);
        }

        /* access modifiers changed from: 0000 */
        public long zzuW() {
            if (this.mContentResolver == null) {
                return 0;
            }
            return zzble.getLong(this.mContentResolver, JPushReportInterface.ANDROID_ID, 0);
        }
    }

    static class zzb {
        public final String zzaxW;
        public final long zzaxX;
        public final long zzaxY;

        public zzb(String str, long j, long j2) {
            this.zzaxW = str;
            this.zzaxX = j;
            this.zzaxY = j2;
        }
    }

    public zzzt() {
        this(new zza(null));
    }

    public zzzt(Context context) {
        this(new zza(context));
    }

    zzzt(zza zza2) {
        this.zzaxV = (zza) zzac.zzw(zza2);
    }

    static long zzJ(long j) {
        return zzzq.zzn(ByteBuffer.allocate(8).putLong(j).array());
    }

    static boolean zza(long j, long j2, long j3) {
        if (j2 >= 0 && j3 >= 0) {
            return j3 > 0 && zzzw.zzd(j, j3) < j2;
        }
        throw new IllegalArgumentException("negative values not supported: " + j2 + "/" + j3);
    }

    static long zzd(String str, long j) {
        if (str == null || str.isEmpty()) {
            return zzJ(j);
        }
        byte[] bytes = str.getBytes(UTF_8);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
        allocate.put(bytes);
        allocate.putLong(j);
        return zzzq.zzn(allocate.array());
    }

    static zzb zzda(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            String str3 = "LogSamplerImpl";
            String str4 = "Failed to parse the rule: ";
            String valueOf = String.valueOf(str);
            Log.e(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzb(str2, parseLong, parseLong2);
            }
            Log.e("LogSamplerImpl", "negative values not supported: " + parseLong + "/" + parseLong2);
            return null;
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            String str5 = "LogSamplerImpl";
            String str6 = "parseLong() failed while parsing: ";
            String valueOf2 = String.valueOf(str);
            Log.e(str5, valueOf2.length() != 0 ? str6.concat(valueOf2) : new String(str6), numberFormatException);
            return null;
        }
    }

    public boolean zzh(String str, int i) {
        if (str == null || str.isEmpty()) {
            str = i >= 0 ? String.valueOf(i) : null;
        }
        if (str == null) {
            return true;
        }
        long zzuW = this.zzaxV.zzuW();
        zzb zzda = zzda(this.zzaxV.zzdb(str));
        if (zzda != null) {
            return zza(zzd(zzda.zzaxW, zzuW), zzda.zzaxX, zzda.zzaxY);
        }
        return true;
    }
}
