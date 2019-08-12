package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;

public final class LocationRequest extends zza implements ReflectedParcelable {
    public static final Creator<LocationRequest> CREATOR = new zzm();
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    int mPriority;
    boolean zzaWy;
    long zzbjD;
    long zzbjT;
    long zzbjU;
    int zzbjV;
    float zzbjW;
    long zzbjX;

    public LocationRequest() {
        this.mPriority = 102;
        this.zzbjT = 3600000;
        this.zzbjU = 600000;
        this.zzaWy = false;
        this.zzbjD = Long.MAX_VALUE;
        this.zzbjV = Integer.MAX_VALUE;
        this.zzbjW = 0.0f;
        this.zzbjX = 0;
    }

    LocationRequest(int i, long j, long j2, boolean z, long j3, int i2, float f, long j4) {
        this.mPriority = i;
        this.zzbjT = j;
        this.zzbjU = j2;
        this.zzaWy = z;
        this.zzbjD = j3;
        this.zzbjV = i2;
        this.zzbjW = f;
        this.zzbjX = j4;
    }

    public static LocationRequest create() {
        return new LocationRequest();
    }

    private static void zzV(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid interval: " + j);
        }
    }

    private static void zze(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("invalid displacement: " + f);
        }
    }

    private static void zzkk(int i) {
        switch (i) {
            case 100:
            case 102:
            case 104:
            case 105:
                return;
            default:
                throw new IllegalArgumentException("invalid quality: " + i);
        }
    }

    public static String zzkl(int i) {
        switch (i) {
            case 100:
                return "PRIORITY_HIGH_ACCURACY";
            case 102:
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            case 104:
                return "PRIORITY_LOW_POWER";
            case 105:
                return "PRIORITY_NO_POWER";
            default:
                return "???";
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        return this.mPriority == locationRequest.mPriority && this.zzbjT == locationRequest.zzbjT && this.zzbjU == locationRequest.zzbjU && this.zzaWy == locationRequest.zzaWy && this.zzbjD == locationRequest.zzbjD && this.zzbjV == locationRequest.zzbjV && this.zzbjW == locationRequest.zzbjW && getMaxWaitTime() == locationRequest.getMaxWaitTime();
    }

    public long getExpirationTime() {
        return this.zzbjD;
    }

    public long getFastestInterval() {
        return this.zzbjU;
    }

    public long getInterval() {
        return this.zzbjT;
    }

    public long getMaxWaitTime() {
        long j = this.zzbjX;
        return j < this.zzbjT ? this.zzbjT : j;
    }

    public int getNumUpdates() {
        return this.zzbjV;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public float getSmallestDisplacement() {
        return this.zzbjW;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.mPriority), Long.valueOf(this.zzbjT), Float.valueOf(this.zzbjW), Long.valueOf(this.zzbjX));
    }

    public LocationRequest setExpirationDuration(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j > Long.MAX_VALUE - elapsedRealtime) {
            this.zzbjD = Long.MAX_VALUE;
        } else {
            this.zzbjD = elapsedRealtime + j;
        }
        if (this.zzbjD < 0) {
            this.zzbjD = 0;
        }
        return this;
    }

    public LocationRequest setExpirationTime(long j) {
        this.zzbjD = j;
        if (this.zzbjD < 0) {
            this.zzbjD = 0;
        }
        return this;
    }

    public LocationRequest setFastestInterval(long j) {
        zzV(j);
        this.zzaWy = true;
        this.zzbjU = j;
        return this;
    }

    public LocationRequest setInterval(long j) {
        zzV(j);
        this.zzbjT = j;
        if (!this.zzaWy) {
            this.zzbjU = (long) (((double) this.zzbjT) / 6.0d);
        }
        return this;
    }

    public LocationRequest setMaxWaitTime(long j) {
        zzV(j);
        this.zzbjX = j;
        return this;
    }

    public LocationRequest setNumUpdates(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("invalid numUpdates: " + i);
        }
        this.zzbjV = i;
        return this;
    }

    public LocationRequest setPriority(int i) {
        zzkk(i);
        this.mPriority = i;
        return this;
    }

    public LocationRequest setSmallestDisplacement(float f) {
        zze(f);
        this.zzbjW = f;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[").append(zzkl(this.mPriority));
        if (this.mPriority != 105) {
            sb.append(" requested=");
            sb.append(this.zzbjT).append("ms");
        }
        sb.append(" fastest=");
        sb.append(this.zzbjU).append("ms");
        if (this.zzbjX > this.zzbjT) {
            sb.append(" maxWait=");
            sb.append(this.zzbjX).append("ms");
        }
        if (this.zzbjW > 0.0f) {
            sb.append(" smallestDisplacement=");
            sb.append(this.zzbjW).append("m");
        }
        if (this.zzbjD != Long.MAX_VALUE) {
            long elapsedRealtime = this.zzbjD - SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(elapsedRealtime).append("ms");
        }
        if (this.zzbjV != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.zzbjV);
        }
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }
}
