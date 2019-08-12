package com.google.android.gms.internal;

import android.os.RemoteException;

public class zzfk extends com.google.android.gms.internal.zzer.zza {
    /* access modifiers changed from: private */
    public zzep zztk;

    private class zza extends com.google.android.gms.internal.zzeq.zza {
        private zza() {
        }

        public String getMediationAdapterClassName() throws RemoteException {
            return null;
        }

        public boolean isLoading() throws RemoteException {
            return false;
        }

        public void zzf(zzec zzec) throws RemoteException {
            zzqf.m1280e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    if (zzfk.this.zztk != null) {
                        try {
                            zzfk.this.zztk.onAdFailedToLoad(1);
                        } catch (RemoteException e) {
                            zzqf.zzc("Could not notify onAdFailedToLoad event.", e);
                        }
                    }
                }
            });
        }
    }

    public void zza(zzhc zzhc) throws RemoteException {
    }

    public void zza(zzhp zzhp) throws RemoteException {
    }

    public void zza(zzhq zzhq) throws RemoteException {
    }

    public void zza(String str, zzhs zzhs, zzhr zzhr) throws RemoteException {
    }

    public void zzb(zzep zzep) throws RemoteException {
        this.zztk = zzep;
    }

    public void zzb(zzex zzex) throws RemoteException {
    }

    public zzeq zzck() throws RemoteException {
        return new zza();
    }
}
