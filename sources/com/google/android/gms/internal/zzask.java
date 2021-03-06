package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.location.zzj;
import com.google.android.gms.location.zzk;

public class zzask extends zza {
    public static final Creator<zzask> CREATOR = new zzasl();
    PendingIntent mPendingIntent;
    int zzbkO;
    zzasi zzbkP;
    zzk zzbkQ;
    zzj zzbkR;
    zzasc zzbkS;

    zzask(int i, zzasi zzasi, IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2, IBinder iBinder3) {
        zzasc zzasc = null;
        this.zzbkO = i;
        this.zzbkP = zzasi;
        this.zzbkQ = iBinder == null ? null : zzk.zza.zzde(iBinder);
        this.mPendingIntent = pendingIntent;
        this.zzbkR = iBinder2 == null ? null : zzj.zza.zzdd(iBinder2);
        if (iBinder3 != null) {
            zzasc = zzasc.zza.zzdg(iBinder3);
        }
        this.zzbkS = zzasc;
    }

    public static zzask zza(zzasi zzasi, PendingIntent pendingIntent, zzasc zzasc) {
        return new zzask(1, zzasi, null, pendingIntent, null, zzasc != null ? zzasc.asBinder() : null);
    }

    public static zzask zza(zzasi zzasi, zzj zzj, zzasc zzasc) {
        return new zzask(1, zzasi, null, null, zzj.asBinder(), zzasc != null ? zzasc.asBinder() : null);
    }

    public static zzask zza(zzasi zzasi, zzk zzk, zzasc zzasc) {
        return new zzask(1, zzasi, zzk.asBinder(), null, null, zzasc != null ? zzasc.asBinder() : null);
    }

    public static zzask zza(zzj zzj, zzasc zzasc) {
        return new zzask(2, null, null, null, zzj.asBinder(), zzasc != null ? zzasc.asBinder() : null);
    }

    public static zzask zza(zzk zzk, zzasc zzasc) {
        return new zzask(2, null, zzk.asBinder(), null, null, zzasc != null ? zzasc.asBinder() : null);
    }

    public static zzask zzb(PendingIntent pendingIntent, zzasc zzasc) {
        return new zzask(2, null, null, pendingIntent, null, zzasc != null ? zzasc.asBinder() : null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzasl.zza(this, parcel, i);
    }

    /* access modifiers changed from: 0000 */
    public IBinder zzIr() {
        if (this.zzbkQ == null) {
            return null;
        }
        return this.zzbkQ.asBinder();
    }

    /* access modifiers changed from: 0000 */
    public IBinder zzIs() {
        if (this.zzbkR == null) {
            return null;
        }
        return this.zzbkR.asBinder();
    }

    /* access modifiers changed from: 0000 */
    public IBinder zzIt() {
        if (this.zzbkS == null) {
            return null;
        }
        return this.zzbkS.asBinder();
    }
}
