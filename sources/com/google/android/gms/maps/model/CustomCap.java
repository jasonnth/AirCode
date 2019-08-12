package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.zzac;

public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    public CustomCap(BitmapDescriptor bitmapDescriptor2) {
        this(bitmapDescriptor2, 10.0f);
    }

    public CustomCap(BitmapDescriptor bitmapDescriptor2, float f) {
        super((BitmapDescriptor) zzac.zzb(bitmapDescriptor2, (Object) "bitmapDescriptor must not be null"), zza(f, "refWidth must be positive"));
        this.bitmapDescriptor = bitmapDescriptor2;
        this.refWidth = f;
    }

    private static float zza(float f, String str) {
        if (f > 0.0f) {
            return f;
        }
        throw new IllegalArgumentException(str);
    }

    public String toString() {
        String valueOf = String.valueOf(this.bitmapDescriptor);
        return new StringBuilder(String.valueOf(valueOf).length() + 55).append("[CustomCap: bitmapDescriptor=").append(valueOf).append(" refWidth=").append(this.refWidth).append("]").toString();
    }
}
