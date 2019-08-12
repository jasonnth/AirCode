package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.io.InputStream;

@zzme
public class zzdp extends zza {
    public static final Creator<zzdp> CREATOR = new zzdq();
    private ParcelFileDescriptor zzyK;

    public zzdp() {
        this(null);
    }

    public zzdp(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzyK = parcelFileDescriptor;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzdq.zza(this, parcel, i);
    }

    public synchronized boolean zzew() {
        return this.zzyK != null;
    }

    public synchronized InputStream zzex() {
        AutoCloseInputStream autoCloseInputStream = null;
        synchronized (this) {
            if (this.zzyK != null) {
                autoCloseInputStream = new AutoCloseInputStream(this.zzyK);
                this.zzyK = null;
            }
        }
        return autoCloseInputStream;
    }

    /* access modifiers changed from: 0000 */
    public synchronized ParcelFileDescriptor zzey() {
        return this.zzyK;
    }
}
