package com.google.android.gms.internal;

import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzabh.zzc;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzj;
import com.google.android.gms.location.zzk;
import java.util.HashMap;
import java.util.Map;

public class zzasg {
    private final Context mContext;
    private final Map<com.google.android.gms.internal.zzabh.zzb<LocationListener>, zzb> zzaWg = new HashMap();
    private ContentProviderClient zzbkD = null;
    private boolean zzbkE = false;
    private final Map<com.google.android.gms.internal.zzabh.zzb<LocationCallback>, zza> zzbkF = new HashMap();
    private final zzaso<zzase> zzbks;

    private static class zza extends com.google.android.gms.location.zzj.zza {
        private final zzabh<LocationCallback> zzaDf;

        public void onLocationAvailability(final LocationAvailability locationAvailability) {
            this.zzaDf.zza(new zzc<LocationCallback>(this) {
                /* renamed from: zza */
                public void zzs(LocationCallback locationCallback) {
                    locationCallback.onLocationAvailability(locationAvailability);
                }

                public void zzwc() {
                }
            });
        }

        public void onLocationResult(final LocationResult locationResult) {
            this.zzaDf.zza(new zzc<LocationCallback>(this) {
                /* renamed from: zza */
                public void zzs(LocationCallback locationCallback) {
                    locationCallback.onLocationResult(locationResult);
                }

                public void zzwc() {
                }
            });
        }
    }

    private static class zzb extends com.google.android.gms.location.zzk.zza {
        private final zzabh<LocationListener> zzaDf;

        zzb(zzabh<LocationListener> zzabh) {
            this.zzaDf = zzabh;
        }

        public synchronized void onLocationChanged(final Location location) {
            this.zzaDf.zza(new zzc<LocationListener>(this) {
                /* renamed from: zza */
                public void zzs(LocationListener locationListener) {
                    locationListener.onLocationChanged(location);
                }

                public void zzwc() {
                }
            });
        }

        public synchronized void release() {
            this.zzaDf.clear();
        }
    }

    public zzasg(Context context, zzaso<zzase> zzaso) {
        this.mContext = context;
        this.zzbks = zzaso;
    }

    private zzb zzf(zzabh<LocationListener> zzabh) {
        zzb zzb2;
        synchronized (this.zzaWg) {
            zzb2 = (zzb) this.zzaWg.get(zzabh.zzwW());
            if (zzb2 == null) {
                zzb2 = new zzb(zzabh);
            }
            this.zzaWg.put(zzabh.zzwW(), zzb2);
        }
        return zzb2;
    }

    public Location getLastLocation() {
        this.zzbks.zzxC();
        try {
            return ((zzase) this.zzbks.zzxD()).zzeR(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void removeAllListeners() {
        try {
            synchronized (this.zzaWg) {
                for (zzb zzb2 : this.zzaWg.values()) {
                    if (zzb2 != null) {
                        ((zzase) this.zzbks.zzxD()).zza(zzask.zza((zzk) zzb2, (zzasc) null));
                    }
                }
                this.zzaWg.clear();
            }
            synchronized (this.zzbkF) {
                for (zza zza2 : this.zzbkF.values()) {
                    if (zza2 != null) {
                        ((zzase) this.zzbks.zzxD()).zza(zzask.zza((zzj) zza2, (zzasc) null));
                    }
                }
                this.zzbkF.clear();
            }
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void zzIq() {
        if (this.zzbkE) {
            try {
                zzaG(false);
            } catch (RemoteException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public void zza(com.google.android.gms.internal.zzabh.zzb<LocationListener> zzb2, zzasc zzasc) throws RemoteException {
        this.zzbks.zzxC();
        zzac.zzb(zzb2, (Object) "Invalid null listener key");
        synchronized (this.zzaWg) {
            zzb zzb3 = (zzb) this.zzaWg.remove(zzb2);
            if (zzb3 != null) {
                zzb3.release();
                ((zzase) this.zzbks.zzxD()).zza(zzask.zza((zzk) zzb3, zzasc));
            }
        }
    }

    public void zza(LocationRequest locationRequest, zzabh<LocationListener> zzabh, zzasc zzasc) throws RemoteException {
        this.zzbks.zzxC();
        ((zzase) this.zzbks.zzxD()).zza(zzask.zza(zzasi.zzb(locationRequest), (zzk) zzf(zzabh), zzasc));
    }

    public void zzaG(boolean z) throws RemoteException {
        this.zzbks.zzxC();
        ((zzase) this.zzbks.zzxD()).zzaG(z);
        this.zzbkE = z;
    }
}
