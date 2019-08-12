package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;

public final class zzzk {
    @Deprecated
    public static final Api<NoOptions> API = new Api<>("ClearcutLogger.API", zzaie, zzaid);
    public static final zzf<zzzp> zzaid = new zzf<>();
    public static final com.google.android.gms.common.api.Api.zza<zzzp, NoOptions> zzaie = new com.google.android.gms.common.api.Api.zza<zzzp, NoOptions>() {
        /* renamed from: zze */
        public zzzp zza(Context context, Looper looper, zzg zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzzp(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };
    /* access modifiers changed from: private */
    public final String zzRg;
    /* access modifiers changed from: private */
    public final int zzaxp;
    /* access modifiers changed from: private */
    public String zzaxq;
    /* access modifiers changed from: private */
    public int zzaxr;
    /* access modifiers changed from: private */
    public String zzaxs;
    /* access modifiers changed from: private */
    public String zzaxt;
    /* access modifiers changed from: private */
    public final boolean zzaxu;
    private int zzaxv;
    /* access modifiers changed from: private */
    public final zzzl zzaxw;
    /* access modifiers changed from: private */
    public zzd zzaxx;
    /* access modifiers changed from: private */
    public final zzb zzaxy;
    /* access modifiers changed from: private */
    public final zze zzuP;

    public class zza {
        private ArrayList<Integer> zzaxA;
        private ArrayList<String> zzaxB;
        private ArrayList<Integer> zzaxC;
        private ArrayList<byte[]> zzaxD;
        private boolean zzaxE;
        private final com.google.android.gms.internal.zzbyo.zzd zzaxF;
        private boolean zzaxG;
        private String zzaxq;
        private int zzaxr;
        private String zzaxs;
        private String zzaxt;
        private int zzaxv;
        private final zzc zzaxz;

        private zza(zzzk zzzk, byte[] bArr) {
            this(bArr, (zzc) null);
        }

        private zza(byte[] bArr, zzc zzc) {
            this.zzaxr = zzzk.this.zzaxr;
            this.zzaxq = zzzk.this.zzaxq;
            this.zzaxs = zzzk.this.zzaxs;
            this.zzaxt = zzzk.this.zzaxt;
            this.zzaxv = zzzk.zze(zzzk.this);
            this.zzaxA = null;
            this.zzaxB = null;
            this.zzaxC = null;
            this.zzaxD = null;
            this.zzaxE = true;
            this.zzaxF = new com.google.android.gms.internal.zzbyo.zzd();
            this.zzaxG = false;
            this.zzaxs = zzzk.this.zzaxs;
            this.zzaxt = zzzk.this.zzaxt;
            this.zzaxF.zzcxj = zzzk.this.zzuP.currentTimeMillis();
            this.zzaxF.zzcxk = zzzk.this.zzuP.elapsedRealtime();
            this.zzaxF.zzcxv = zzzk.this.zzaxx.zzH(this.zzaxF.zzcxj);
            if (bArr != null) {
                this.zzaxF.zzcxq = bArr;
            }
            this.zzaxz = zzc;
        }

        public zza zzcq(int i) {
            this.zzaxF.zzcxm = i;
            return this;
        }

        public zza zzcr(int i) {
            this.zzaxF.zzri = i;
            return this;
        }

        @Deprecated
        public PendingResult<Status> zze(GoogleApiClient googleApiClient) {
            return zzuU();
        }

        public zzzm zzuS() {
            zzzm zzzm = new zzzm(new zzzu(zzzk.this.zzRg, zzzk.this.zzaxp, this.zzaxr, this.zzaxq, this.zzaxs, this.zzaxt, zzzk.this.zzaxu, this.zzaxv), this.zzaxF, this.zzaxz, null, zzzk.zzb(null), zzzk.zzc(null), zzzk.zzb(null), zzzk.zzd(null), this.zzaxE);
            return zzzm;
        }

        @Deprecated
        public PendingResult<Status> zzuU() {
            if (this.zzaxG) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.zzaxG = true;
            zzzm zzuS = zzuS();
            zzzu zzzu = zzuS.zzaxI;
            return zzzk.this.zzaxy.zzh(zzzu.zzaye, zzzu.zzaya) ? zzzk.this.zzaxw.zza(zzuS) : PendingResults.immediatePendingResult(Status.zzazx);
        }
    }

    public interface zzb {
        boolean zzh(String str, int i);
    }

    public interface zzc {
        byte[] zzuV();
    }

    public static class zzd {
        public long zzH(long j) {
            return (long) (TimeZone.getDefault().getOffset(j) / 1000);
        }
    }

    public zzzk(Context context, int i, String str, String str2, String str3, boolean z, zzzl zzzl, zze zze, zzd zzd2, zzb zzb2) {
        boolean z2 = false;
        this.zzaxr = -1;
        this.zzaxv = 0;
        this.zzRg = context.getPackageName();
        this.zzaxp = zzaz(context);
        this.zzaxr = i;
        this.zzaxq = str;
        this.zzaxs = str2;
        this.zzaxt = str3;
        this.zzaxu = z;
        this.zzaxw = zzzl;
        this.zzuP = zze;
        if (zzd2 == null) {
            zzd2 = new zzd();
        }
        this.zzaxx = zzd2;
        this.zzaxv = 0;
        this.zzaxy = zzb2;
        if (this.zzaxu) {
            if (this.zzaxs == null) {
                z2 = true;
            }
            zzac.zzb(z2, (Object) "can't be anonymous with an upload account");
        }
    }

    public zzzk(Context context, String str, String str2) {
        this(context, -1, str, str2, null, false, zzzo.zzaA(context), zzi.zzzc(), null, new zzzt(context));
    }

    private int zzaz(Context context) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return z;
        }
    }

    /* access modifiers changed from: private */
    public static int[] zzb(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        int i = 0;
        Iterator it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return iArr;
            }
            i = i2 + 1;
            iArr[i2] = ((Integer) it.next()).intValue();
        }
    }

    /* access modifiers changed from: private */
    public static String[] zzc(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* access modifiers changed from: private */
    public static byte[][] zzd(ArrayList<byte[]> arrayList) {
        if (arrayList == null) {
            return null;
        }
        return (byte[][]) arrayList.toArray(new byte[0][]);
    }

    static /* synthetic */ int zze(zzzk zzzk) {
        return 0;
    }

    public zza zzm(byte[] bArr) {
        return new zza(bArr);
    }
}
