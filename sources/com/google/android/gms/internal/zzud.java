package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.appindexing.AppIndexApi.AppIndexingLink;
import com.google.android.gms.internal.zzaqn.zza.C7701zza;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.zip.CRC32;
import p005cn.jpush.android.JPushConstants;

public class zzud extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Creator<zzud> CREATOR = new zzue();
    public final String zzAT;
    final zztr zzahP;
    final long zzahQ;
    int zzahR;
    final zztp zzahS;
    final boolean zzahT;
    int zzahU;
    int zzahV;

    public static final class zza {
        private zztr zzahP;
        private long zzahQ = -1;
        private int zzahR = -1;
        private zztp zzahS;
        private boolean zzahT = false;
        private int zzahU = -1;
        private int zzahV = 0;

        public zza zzB(long j) {
            this.zzahQ = j;
            return this;
        }

        public zza zza(zztp zztp) {
            this.zzahS = zztp;
            return this;
        }

        public zza zza(zztr zztr) {
            this.zzahP = zztr;
            return this;
        }

        public zza zzaS(int i) {
            this.zzahR = i;
            return this;
        }

        public zza zzaT(int i) {
            this.zzahV = i;
            return this;
        }

        public zza zzaa(boolean z) {
            this.zzahT = z;
            return this;
        }

        public zzud zzqI() {
            return new zzud(this.zzahP, this.zzahQ, this.zzahR, null, this.zzahS, this.zzahT, this.zzahU, this.zzahV);
        }
    }

    zzud(zztr zztr, long j, int i, String str, zztp zztp, boolean z, int i2, int i3) {
        this.zzahP = zztr;
        this.zzahQ = j;
        this.zzahR = i;
        this.zzAT = str;
        this.zzahS = zztp;
        this.zzahT = z;
        this.zzahU = i2;
        this.zzahV = i3;
    }

    public zzud(String str, Intent intent, String str2, Uri uri, String str3, List<AppIndexingLink> list, int i) {
        this(zza(str, intent), System.currentTimeMillis(), 0, null, zza(intent, str2, uri, str3, list).zzqE(), false, -1, i);
    }

    public static com.google.android.gms.internal.zztp.zza zza(Intent intent, String str, Uri uri, String str2, List<AppIndexingLink> list) {
        com.google.android.gms.internal.zztp.zza zza2 = new com.google.android.gms.internal.zztp.zza();
        zza2.zza(zzcp(str));
        if (uri != null) {
            zza2.zza(zzk(uri));
        }
        if (list != null) {
            zza2.zza(zzv(list));
        }
        String action = intent.getAction();
        if (action != null) {
            zza2.zza(zzu("intent_action", action));
        }
        String dataString = intent.getDataString();
        if (dataString != null) {
            zza2.zza(zzu("intent_data", dataString));
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            zza2.zza(zzu("intent_activity", component.getClassName()));
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String string = extras.getString("intent_extra_data_key");
            if (string != null) {
                zza2.zza(zzu("intent_extra_data", string));
            }
        }
        return zza2.zzcl(str2).zzX(true);
    }

    public static zztr zza(String str, Intent intent) {
        return zzt(str, zzg(intent));
    }

    private static zztt zzcp(String str) {
        return new zztt(str, new com.google.android.gms.internal.zzub.zza("title").zzaQ(1).zzZ(true).zzco("name").zzqH(), "text1");
    }

    private static String zzg(Intent intent) {
        String uri = intent.toUri(1);
        CRC32 crc32 = new CRC32();
        try {
            crc32.update(uri.getBytes(JPushConstants.ENCODING_UTF_8));
            return Long.toHexString(crc32.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static zztt zzk(Uri uri) {
        return new zztt(uri.toString(), new com.google.android.gms.internal.zzub.zza("web_url").zzaQ(4).zzY(true).zzco("url").zzqH());
    }

    private static zztr zzt(String str, String str2) {
        return new zztr(str, "", str2);
    }

    private static zztt zzu(String str, String str2) {
        return new zztt(str2, new com.google.android.gms.internal.zzub.zza(str).zzY(true).zzqH(), str);
    }

    private static zztt zzv(List<AppIndexingLink> list) {
        com.google.android.gms.internal.zzaqn.zza zza2 = new com.google.android.gms.internal.zzaqn.zza();
        C7701zza[] zzaArr = new C7701zza[list.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < zzaArr.length) {
                zzaArr[i2] = new C7701zza();
                AppIndexingLink appIndexingLink = (AppIndexingLink) list.get(i2);
                zzaArr[i2].zzbhi = appIndexingLink.appIndexingUrl.toString();
                zzaArr[i2].viewId = appIndexingLink.viewId;
                if (appIndexingLink.webUrl != null) {
                    zzaArr[i2].zzbhj = appIndexingLink.webUrl.toString();
                }
                i = i2 + 1;
            } else {
                zza2.zzbhg = zzaArr;
                return new zztt(zzbyj.zzf(zza2), new com.google.android.gms.internal.zzub.zza("outlinks").zzY(true).zzco(".private:outLinks").zzcn("blob").zzqH());
            }
        }
    }

    public String toString() {
        return String.format(Locale.US, "UsageInfo[documentId=%s, timestamp=%d, usageType=%d, status=%d]", new Object[]{this.zzahP, Long.valueOf(this.zzahQ), Integer.valueOf(this.zzahR), Integer.valueOf(this.zzahV)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzue.zza(this, parcel, i);
    }
}
