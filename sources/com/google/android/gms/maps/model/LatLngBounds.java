package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;

public final class LatLngBounds extends zza implements ReflectedParcelable {
    public static final Creator<LatLngBounds> CREATOR = new zze();
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double zzbpv = Double.POSITIVE_INFINITY;
        private double zzbpw = Double.NEGATIVE_INFINITY;
        private double zzbpx = Double.NaN;
        private double zzbpy = Double.NaN;

        private boolean zzi(double d) {
            boolean z = false;
            if (this.zzbpx <= this.zzbpy) {
                return this.zzbpx <= d && d <= this.zzbpy;
            }
            if (this.zzbpx <= d || d <= this.zzbpy) {
                z = true;
            }
            return z;
        }

        public LatLngBounds build() {
            zzac.zza(!Double.isNaN(this.zzbpx), (Object) "no included points");
            return new LatLngBounds(new LatLng(this.zzbpv, this.zzbpx), new LatLng(this.zzbpw, this.zzbpy));
        }

        public Builder include(LatLng latLng) {
            this.zzbpv = Math.min(this.zzbpv, latLng.latitude);
            this.zzbpw = Math.max(this.zzbpw, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.zzbpx)) {
                this.zzbpx = d;
                this.zzbpy = d;
            } else if (!zzi(d)) {
                if (LatLngBounds.zzb(this.zzbpx, d) < LatLngBounds.zzc(this.zzbpy, d)) {
                    this.zzbpx = d;
                } else {
                    this.zzbpy = d;
                }
            }
            return this;
        }
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        zzac.zzb(latLng, (Object) "null southwest");
        zzac.zzb(latLng2, (Object) "null northeast");
        zzac.zzb(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude));
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        Float f = obtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude) ? Float.valueOf(obtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0.0f)) : null;
        Float f2 = obtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude) ? Float.valueOf(obtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0.0f)) : null;
        Float f3 = obtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude) ? Float.valueOf(obtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0.0f)) : null;
        Float f4 = obtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude) ? Float.valueOf(obtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0.0f)) : null;
        if (f == null || f2 == null || f3 == null || f4 == null) {
            return null;
        }
        return new LatLngBounds(new LatLng((double) f.floatValue(), (double) f2.floatValue()), new LatLng((double) f3.floatValue(), (double) f4.floatValue()));
    }

    /* access modifiers changed from: private */
    public static double zzb(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* access modifiers changed from: private */
    public static double zzc(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    private boolean zzh(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    private boolean zzi(double d) {
        boolean z = false;
        if (this.southwest.longitude <= this.northeast.longitude) {
            return this.southwest.longitude <= d && d <= this.northeast.longitude;
        }
        if (this.southwest.longitude <= d || d <= this.northeast.longitude) {
            z = true;
        }
        return z;
    }

    public boolean contains(LatLng latLng) {
        return zzh(latLng.latitude) && zzi(latLng.longitude);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public LatLng getCenter() {
        double d = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        return new LatLng(d, d3 <= d2 ? (d2 + d3) / 2.0d : ((d2 + 360.0d) + d3) / 2.0d);
    }

    public int hashCode() {
        return zzaa.hashCode(this.southwest, this.northeast);
    }

    public LatLngBounds including(LatLng latLng) {
        double d;
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = latLng.longitude;
        if (zzi(d4)) {
            d4 = d3;
            d = d2;
        } else if (zzb(d3, d4) < zzc(d2, d4)) {
            d = d2;
        } else {
            double d5 = d3;
            d = d4;
            d4 = d5;
        }
        return new LatLngBounds(new LatLng(min, d4), new LatLng(max, d));
    }

    public String toString() {
        return zzaa.zzv(this).zzg("southwest", this.southwest).zzg("northeast", this.northeast).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }
}
