package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;

public class Cap extends zza {
    public static final Creator<Cap> CREATOR = new zzb();
    private static final String TAG = Cap.class.getSimpleName();
    private final BitmapDescriptor bitmapDescriptor;
    private final int type;
    private final Float zzbpd;

    protected Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    Cap(int i, IBinder iBinder, Float f) {
        this(i, zzeh(iBinder), f);
    }

    private Cap(int i, BitmapDescriptor bitmapDescriptor2, Float f) {
        boolean z = false;
        boolean z2 = f != null && f.floatValue() > 0.0f;
        if (i != 3 || (bitmapDescriptor2 != null && z2)) {
            z = true;
        }
        String valueOf = String.valueOf(bitmapDescriptor2);
        String valueOf2 = String.valueOf(f);
        zzac.zzb(z, (Object) new StringBuilder(String.valueOf(valueOf).length() + 63 + String.valueOf(valueOf2).length()).append("Invalid Cap: type=").append(i).append(" bitmapDescriptor=").append(valueOf).append(" bitmapRefWidth=").append(valueOf2).toString());
        this.type = i;
        this.bitmapDescriptor = bitmapDescriptor2;
        this.zzbpd = f;
    }

    protected Cap(BitmapDescriptor bitmapDescriptor2, float f) {
        this(3, bitmapDescriptor2, Float.valueOf(f));
    }

    private static BitmapDescriptor zzeh(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        return new BitmapDescriptor(IObjectWrapper.zza.zzcd(iBinder));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.type == cap.type && zzaa.equal(this.bitmapDescriptor, cap.bitmapDescriptor) && zzaa.equal(this.zzbpd, cap.zzbpd);
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.type), this.bitmapDescriptor, this.zzbpd);
    }

    public String toString() {
        return "[Cap: type=" + this.type + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public Float zzJH() {
        return this.zzbpd;
    }

    /* access modifiers changed from: 0000 */
    public IBinder zzJI() {
        if (this.bitmapDescriptor == null) {
            return null;
        }
        return this.bitmapDescriptor.zzJm().asBinder();
    }

    /* access modifiers changed from: 0000 */
    public Cap zzJJ() {
        switch (this.type) {
            case 0:
                return new ButtCap();
            case 1:
                return new SquareCap();
            case 2:
                return new RoundCap();
            case 3:
                return new CustomCap(this.bitmapDescriptor, this.zzbpd.floatValue());
            default:
                Log.w(TAG, "Unknown Cap type: " + this.type);
                return this;
        }
    }
}
