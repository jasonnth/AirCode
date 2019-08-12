package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.maps.model.internal.zza;

public final class BitmapDescriptorFactory {
    private static zza zzboY;

    public static BitmapDescriptor defaultMarker(float f) {
        try {
            return new BitmapDescriptor(zzJG().zzi(f));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        try {
            return new BitmapDescriptor(zzJG().zze(bitmap));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static BitmapDescriptor fromResource(int i) {
        try {
            return new BitmapDescriptor(zzJG().zzly(i));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static zza zzJG() {
        return (zza) zzac.zzb(zzboY, (Object) "IBitmapDescriptorFactory is not initialized");
    }

    public static void zza(zza zza) {
        if (zzboY == null) {
            zzboY = (zza) zzac.zzw(zza);
        }
    }
}
