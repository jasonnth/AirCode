package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class zzasm extends zza implements Geofence {
    public static final Creator<zzasm> CREATOR = new zzasn();
    private final String zzOV;
    private final int zzbjC;
    private final short zzbjE;
    private final double zzbjF;
    private final double zzbjG;
    private final float zzbjH;
    private final int zzbjI;
    private final int zzbjJ;
    private final long zzbkT;

    public zzasm(String str, int i, short s, double d, double d2, float f, long j, int i2, int i3) {
        zzeT(str);
        zzf(f);
        zza(d, d2);
        int zzkA = zzkA(i);
        this.zzbjE = s;
        this.zzOV = str;
        this.zzbjF = d;
        this.zzbjG = d2;
        this.zzbjH = f;
        this.zzbkT = j;
        this.zzbjC = zzkA;
        this.zzbjI = i2;
        this.zzbjJ = i3;
    }

    private static void zza(double d, double d2) {
        if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        }
    }

    private static void zzeT(String str) {
        if (str == null || str.length() > 100) {
            String str2 = "requestId is null or too long: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    private static void zzf(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        }
    }

    private static int zzkA(int i) {
        int i2 = i & 7;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("No supported transition specified: " + i);
    }

    @SuppressLint({"DefaultLocale"})
    private static String zzkB(int i) {
        switch (i) {
            case 1:
                return "CIRCLE";
            default:
                return null;
        }
    }

    public static zzasm zzw(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        zzasm zzasm = (zzasm) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return zzasm;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzasm)) {
            return false;
        }
        zzasm zzasm = (zzasm) obj;
        if (this.zzbjH != zzasm.zzbjH) {
            return false;
        }
        if (this.zzbjF != zzasm.zzbjF) {
            return false;
        }
        if (this.zzbjG != zzasm.zzbjG) {
            return false;
        }
        return this.zzbjE == zzasm.zzbjE;
    }

    public long getExpirationTime() {
        return this.zzbkT;
    }

    public double getLatitude() {
        return this.zzbjF;
    }

    public double getLongitude() {
        return this.zzbjG;
    }

    public float getRadius() {
        return this.zzbjH;
    }

    public String getRequestId() {
        return this.zzOV;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zzbjF);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzbjG);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.zzbjH)) * 31) + this.zzbjE) * 31) + this.zzbjC;
    }

    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[]{zzkB(this.zzbjE), this.zzOV, Integer.valueOf(this.zzbjC), Double.valueOf(this.zzbjF), Double.valueOf(this.zzbjG), Float.valueOf(this.zzbjH), Integer.valueOf(this.zzbjI / 1000), Integer.valueOf(this.zzbjJ), Long.valueOf(this.zzbkT)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzasn.zza(this, parcel, i);
    }

    public short zzIu() {
        return this.zzbjE;
    }

    public int zzIv() {
        return this.zzbjC;
    }

    public int zzIw() {
        return this.zzbjI;
    }

    public int zzIx() {
        return this.zzbjJ;
    }
}
