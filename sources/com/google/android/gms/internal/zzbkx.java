package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.C1458Payments;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.Wallet.zza;
import com.google.android.gms.wallet.Wallet.zzb;

@SuppressLint({"MissingRemoteException"})
public class zzbkx implements C1458Payments {
    public void changeMaskedWallet(GoogleApiClient googleApiClient, String str, String str2, int i) {
        final String str3 = str;
        final String str4 = str2;
        final int i2 = i;
        googleApiClient.zza(new zzb(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzbky zzbky) {
                zzbky.zzf(str3, str4, i2);
                zzb(Status.zzazx);
            }
        });
    }

    public PendingResult<BooleanResult> isReadyToPay(GoogleApiClient googleApiClient) {
        return googleApiClient.zza(new zza<BooleanResult>(this, googleApiClient) {
            /* access modifiers changed from: protected */
            /* renamed from: zzM */
            public BooleanResult zzc(Status status) {
                return new BooleanResult(status, false);
            }

            /* access modifiers changed from: protected */
            public void zza(zzbky zzbky) {
                zzbky.zza(IsReadyToPayRequest.newBuilder().build(), (zzaad.zzb<BooleanResult>) this);
            }
        });
    }

    public void loadFullWallet(GoogleApiClient googleApiClient, final FullWalletRequest fullWalletRequest, final int i) {
        googleApiClient.zza(new zzb(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzbky zzbky) {
                zzbky.zza(fullWalletRequest, i);
                zzb(Status.zzazx);
            }
        });
    }

    public void loadMaskedWallet(GoogleApiClient googleApiClient, final MaskedWalletRequest maskedWalletRequest, final int i) {
        googleApiClient.zza(new zzb(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzbky zzbky) {
                zzbky.zza(maskedWalletRequest, i);
                zzb(Status.zzazx);
            }
        });
    }
}
