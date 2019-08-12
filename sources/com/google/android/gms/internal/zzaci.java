package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

abstract class zzaci<R extends Result> extends com.google.android.gms.internal.zzaad.zza<R, zzacj> {

    static abstract class zza extends zzaci<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    public zzaci(GoogleApiClient googleApiClient) {
        super(zzacf.API, googleApiClient);
    }
}
