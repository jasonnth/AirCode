package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaad.zzb;

public final class zzvc implements CredentialsApi {

    private static class zza extends zzuy {
        private zzb<Status> zzajP;

        zza(zzb<Status> zzb) {
            this.zzajP = zzb;
        }

        public void zzh(Status status) {
            this.zzajP.setResult(status);
        }
    }

    public PendingResult<Status> delete(GoogleApiClient googleApiClient, final Credential credential) {
        return googleApiClient.zzb(new zzvd<Status>(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(Context context, zzvl zzvl) throws RemoteException {
                zzvl.zza((zzvk) new zza(this), new zzvg(credential));
            }

            /* access modifiers changed from: protected */
            /* renamed from: zzb */
            public Status zzc(Status status) {
                return status;
            }
        });
    }

    public PendingResult<Status> disableAutoSignIn(GoogleApiClient googleApiClient) {
        return googleApiClient.zzb(new zzvd<Status>(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(Context context, zzvl zzvl) throws RemoteException {
                zzvl.zza(new zza(this));
            }

            /* access modifiers changed from: protected */
            /* renamed from: zzb */
            public Status zzc(Status status) {
                return status;
            }
        });
    }

    public PendingResult<CredentialRequestResult> request(GoogleApiClient googleApiClient, final CredentialRequest credentialRequest) {
        return googleApiClient.zza(new zzvd<CredentialRequestResult>(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(Context context, zzvl zzvl) throws RemoteException {
                zzvl.zza((zzvk) new zzuy() {
                    public void zza(Status status, Credential credential) {
                        C42071.this.zzb(new zzvb(status, credential));
                    }

                    public void zzh(Status status) {
                        C42071.this.zzb(zzvb.zzi(status));
                    }
                }, credentialRequest);
            }

            /* access modifiers changed from: protected */
            /* renamed from: zzj */
            public CredentialRequestResult zzc(Status status) {
                return zzvb.zzi(status);
            }
        });
    }

    public PendingResult<Status> save(GoogleApiClient googleApiClient, final Credential credential) {
        return googleApiClient.zzb(new zzvd<Status>(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(Context context, zzvl zzvl) throws RemoteException {
                zzvl.zza((zzvk) new zza(this), new zzvm(credential));
            }

            /* access modifiers changed from: protected */
            /* renamed from: zzb */
            public Status zzc(Status status) {
                return status;
            }
        });
    }
}
