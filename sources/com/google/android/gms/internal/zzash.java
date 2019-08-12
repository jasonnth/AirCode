package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzabh.zzb;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public class zzash extends zzarv {
    private final zzasg zzbkJ;

    public zzash(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, zzg zzg) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, str, zzg);
        this.zzbkJ = new zzasg(context, this.zzbks);
    }

    public void disconnect() {
        synchronized (this.zzbkJ) {
            if (isConnected()) {
                try {
                    this.zzbkJ.removeAllListeners();
                    this.zzbkJ.zzIq();
                } catch (Exception e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
            }
            super.disconnect();
        }
    }

    public Location getLastLocation() {
        return this.zzbkJ.getLastLocation();
    }

    public void zza(zzb<LocationListener> zzb, zzasc zzasc) throws RemoteException {
        this.zzbkJ.zza(zzb, zzasc);
    }

    public void zza(LocationRequest locationRequest, zzabh<LocationListener> zzabh, zzasc zzasc) throws RemoteException {
        synchronized (this.zzbkJ) {
            this.zzbkJ.zza(locationRequest, zzabh, zzasc);
        }
    }
}
