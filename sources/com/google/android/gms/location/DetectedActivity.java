package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.Comparator;

public class DetectedActivity extends zza {
    public static final Creator<DetectedActivity> CREATOR = new zzh();
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    public static final Comparator<DetectedActivity> zzbjx = new Comparator<DetectedActivity>() {
        /* renamed from: zza */
        public int compare(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
            int compareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
            return compareTo == 0 ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : compareTo;
        }
    };
    public static final int[] zzbjy = {9, 10};
    public static final int[] zzbjz = {0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14};
    int zzbjA;
    int zzbjB;

    public DetectedActivity(int i, int i2) {
        this.zzbjA = i;
        this.zzbjB = i2;
    }

    private int zzke(int i) {
        if (i > 15) {
            return 4;
        }
        return i;
    }

    public static String zzkf(int i) {
        switch (i) {
            case 0:
                return "IN_VEHICLE";
            case 1:
                return "ON_BICYCLE";
            case 2:
                return "ON_FOOT";
            case 3:
                return "STILL";
            case 4:
                return "UNKNOWN";
            case 5:
                return "TILTING";
            case 7:
                return "WALKING";
            case 8:
                return "RUNNING";
            default:
                return Integer.toString(i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        return this.zzbjA == detectedActivity.zzbjA && this.zzbjB == detectedActivity.zzbjB;
    }

    public int getConfidence() {
        return this.zzbjB;
    }

    public int getType() {
        return zzke(this.zzbjA);
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.zzbjA), Integer.valueOf(this.zzbjB));
    }

    public String toString() {
        String valueOf = String.valueOf(zzkf(getType()));
        return new StringBuilder(String.valueOf(valueOf).length() + 48).append("DetectedActivity [type=").append(valueOf).append(", confidence=").append(this.zzbjB).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }
}
