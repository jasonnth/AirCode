package com.google.android.gms.internal;

import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class zzary implements FusedLocationProviderApi {

    private static abstract class zza extends com.google.android.gms.location.LocationServices.zza<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    private static class zzb extends com.google.android.gms.internal.zzasc.zza {
        private final com.google.android.gms.internal.zzaad.zzb<Status> zzaGN;

        public zzb(com.google.android.gms.internal.zzaad.zzb<Status> zzb) {
            this.zzaGN = zzb;
        }

        public void zza(zzarz zzarz) {
            this.zzaGN.setResult(zzarz.getStatus());
        }
    }

    public Location getLastLocation(GoogleApiClient googleApiClient) {
        try {
            return LocationServices.zzj(googleApiClient).getLastLocation();
        } catch (Exception e) {
            return null;
        }
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, final LocationListener locationListener) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzash zzash) throws RemoteException {
                zzash.zza(zzabi.zza(locationListener, LocationListener.class.getSimpleName()), new zzb(this));
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener) {
        zzac.zzb(Looper.myLooper(), (Object) "Calling thread must be a prepared Looper thread.");
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzash zzash) throws RemoteException {
                zzash.zza(locationRequest, zzabi.zzb(locationListener, zzata.zzJl(), LocationListener.class.getSimpleName()), new zzb(this));
            }
        });
    }
}
