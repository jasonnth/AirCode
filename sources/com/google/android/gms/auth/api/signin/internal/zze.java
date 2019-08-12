package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaax;
import com.google.android.gms.internal.zzacm;

public final class zze {
    private static zzacm zzakx = new zzacm("GoogleSignInCommon", new String[0]);

    private static abstract class zza<R extends Result> extends com.google.android.gms.internal.zzaad.zza<R, zzd> {
        public zza(GoogleApiClient googleApiClient) {
            super(Auth.GOOGLE_SIGN_IN_API, googleApiClient);
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzb((Result) obj);
        }
    }

    public static Intent zza(Context context, GoogleSignInOptions googleSignInOptions) {
        zzakx.zzb("GoogleSignInCommon", "getSignInIntent()");
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setClass(context, SignInHubActivity.class);
        intent.putExtra("config", signInConfiguration);
        return intent;
    }

    private static void zzar(Context context) {
        zzn.zzas(context).zzrD();
        for (GoogleApiClient zzvn : GoogleApiClient.zzvm()) {
            zzvn.zzvn();
        }
        zzaax.zzwx();
    }

    public static PendingResult<Status> zzb(GoogleApiClient googleApiClient, Context context) {
        zzakx.zzb("GoogleSignInCommon", "Revoking access");
        zzar(context);
        return googleApiClient.zzb(new zza<Status>(googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzd zzd) throws RemoteException {
                ((zzk) zzd.zzxD()).zzc(new zza() {
                    public void zzm(Status status) throws RemoteException {
                        C40783.this.zzb(status);
                    }
                }, zzd.zzrt());
            }

            /* access modifiers changed from: protected */
            /* renamed from: zzb */
            public Status zzc(Status status) {
                return status;
            }
        });
    }
}
