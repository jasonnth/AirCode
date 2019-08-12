package com.google.android.gms.internal;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.firstparty.zzf;
import com.google.android.gms.wallet.firstparty.zzj;
import com.google.android.gms.wallet.zzu;
import java.lang.ref.WeakReference;

public class zzbky extends zzl<zzbku> {
    private final Context mContext;
    private final int mTheme;
    private final String zzaiu;
    private final int zzbRw;
    private final boolean zzbRx;

    private static class zza extends com.google.android.gms.internal.zzbkw.zza {
        private zza() {
        }

        public void zza(int i, FullWallet fullWallet, Bundle bundle) {
        }

        public void zza(int i, MaskedWallet maskedWallet, Bundle bundle) {
        }

        public void zza(int i, boolean z, Bundle bundle) {
        }

        public void zza(Status status, Bundle bundle) {
        }

        public void zza(Status status, zzf zzf, Bundle bundle) {
        }

        public void zza(Status status, zzj zzj, Bundle bundle) {
        }

        public void zza(Status status, zzu zzu, Bundle bundle) {
        }

        public void zza(Status status, boolean z, Bundle bundle) {
        }

        public void zzb(int i, boolean z, Bundle bundle) {
        }

        public void zzb(Status status, Bundle bundle) {
        }

        public void zzm(int i, Bundle bundle) {
        }
    }

    private static class zzb extends zza {
        private final com.google.android.gms.internal.zzaad.zzb<BooleanResult> zzaGN;

        public zzb(com.google.android.gms.internal.zzaad.zzb<BooleanResult> zzb) {
            super();
            this.zzaGN = zzb;
        }

        public void zza(Status status, boolean z, Bundle bundle) {
            this.zzaGN.setResult(new BooleanResult(status, z));
        }
    }

    static final class zzc extends zza {
        private final int zzazu;
        private final WeakReference<Activity> zzbSv;

        public zzc(Context context, int i) {
            super();
            this.zzbSv = new WeakReference<>((Activity) context);
            this.zzazu = i;
        }

        public void zza(int i, FullWallet fullWallet, Bundle bundle) {
            int i2;
            Activity activity = (Activity) this.zzbSv.get();
            if (activity == null) {
                Log.d("WalletClientImpl", "Ignoring onFullWalletLoaded, Activity has gone");
                return;
            }
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
            }
            ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(activity, this.zzazu);
                } catch (SendIntentException e) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", e);
                }
            } else {
                Intent intent = new Intent();
                if (connectionResult.isSuccess()) {
                    i2 = -1;
                    intent.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", fullWallet);
                } else {
                    i2 = i == 408 ? 0 : 1;
                    intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", i);
                }
                PendingIntent createPendingResult = activity.createPendingResult(this.zzazu, intent, 1073741824);
                if (createPendingResult == null) {
                    Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
                    return;
                }
                try {
                    createPendingResult.send(i2);
                } catch (CanceledException e2) {
                    Log.w("WalletClientImpl", "Exception setting pending result", e2);
                }
            }
        }

        public void zza(int i, MaskedWallet maskedWallet, Bundle bundle) {
            int i2;
            Activity activity = (Activity) this.zzbSv.get();
            if (activity == null) {
                Log.d("WalletClientImpl", "Ignoring onMaskedWalletLoaded, Activity has gone");
                return;
            }
            PendingIntent pendingIntent = null;
            if (bundle != null) {
                pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
            }
            ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(activity, this.zzazu);
                } catch (SendIntentException e) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", e);
                }
            } else {
                Intent intent = new Intent();
                if (connectionResult.isSuccess()) {
                    i2 = -1;
                    intent.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", maskedWallet);
                } else {
                    i2 = i == 408 ? 0 : 1;
                    intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", i);
                }
                PendingIntent createPendingResult = activity.createPendingResult(this.zzazu, intent, 1073741824);
                if (createPendingResult == null) {
                    Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
                    return;
                }
                try {
                    createPendingResult.send(i2);
                } catch (CanceledException e2) {
                    Log.w("WalletClientImpl", "Exception setting pending result", e2);
                }
            }
        }

        public void zza(int i, boolean z, Bundle bundle) {
            Activity activity = (Activity) this.zzbSv.get();
            if (activity == null) {
                Log.d("WalletClientImpl", "Ignoring onPreAuthorizationDetermined, Activity has gone");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("com.google.android.gm.wallet.EXTRA_IS_USER_PREAUTHORIZED", z);
            PendingIntent createPendingResult = activity.createPendingResult(this.zzazu, intent, 1073741824);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
                return;
            }
            try {
                createPendingResult.send(-1);
            } catch (CanceledException e) {
                Log.w("WalletClientImpl", "Exception setting pending result", e);
            }
        }

        public void zza(Status status, boolean z, Bundle bundle) {
            Activity activity = (Activity) this.zzbSv.get();
            if (activity == null) {
                Log.d("WalletClientImpl", "Ignoring onIsReadyToPayDetermined, Activity has gone");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("com.google.android.gms.wallet.EXTRA_IS_READY_TO_PAY", z);
            PendingIntent createPendingResult = activity.createPendingResult(this.zzazu, intent, 1073741824);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onIsReadyToPayDetermined");
                return;
            }
            try {
                createPendingResult.send(-1);
            } catch (CanceledException e) {
                Log.w("WalletClientImpl", "Exception setting pending result in onIsReadyToPayDetermined", e);
            }
        }

        public void zzb(int i, boolean z, Bundle bundle) {
            Activity activity = (Activity) this.zzbSv.get();
            if (activity == null) {
                Log.d("WalletClientImpl", "Ignoring onIsNewUserDetermined, Activity has gone");
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("com.google.android.gms.wallet.EXTRA_IS_NEW_USER", z);
            PendingIntent createPendingResult = activity.createPendingResult(this.zzazu, intent, 1073741824);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onIsNewUserDetermined");
                return;
            }
            try {
                createPendingResult.send(-1);
            } catch (CanceledException e) {
                Log.w("WalletClientImpl", "Exception setting pending result", e);
            }
        }

        public void zzm(int i, Bundle bundle) {
            zzac.zzb(bundle, (Object) "Bundle should not be null");
            Activity activity = (Activity) this.zzbSv.get();
            if (activity == null) {
                Log.d("WalletClientImpl", "Ignoring onWalletObjectsCreated, Activity has gone");
                return;
            }
            ConnectionResult connectionResult = new ConnectionResult(i, (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(activity, this.zzazu);
                } catch (SendIntentException e) {
                    Log.w("WalletClientImpl", "Exception starting pending intent", e);
                }
            } else {
                String valueOf = String.valueOf(connectionResult);
                Log.e("WalletClientImpl", new StringBuilder(String.valueOf(valueOf).length() + 75).append("Create Wallet Objects confirmation UI will not be shown connection result: ").append(valueOf).toString());
                Intent intent = new Intent();
                intent.putExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
                PendingIntent createPendingResult = activity.createPendingResult(this.zzazu, intent, 1073741824);
                if (createPendingResult == null) {
                    Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
                    return;
                }
                try {
                    createPendingResult.send(1);
                } catch (CanceledException e2) {
                    Log.w("WalletClientImpl", "Exception setting pending result", e2);
                }
            }
        }
    }

    public zzbky(Context context, Looper looper, zzg zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, int i, int i2, boolean z) {
        super(context, looper, 4, zzg, connectionCallbacks, onConnectionFailedListener);
        this.mContext = context;
        this.zzbRw = i;
        this.zzaiu = zzg.getAccountName();
        this.mTheme = i2;
        this.zzbRx = z;
    }

    private Bundle zzUc() {
        return zza(this.zzbRw, this.mContext.getPackageName(), this.zzaiu, this.mTheme, this.zzbRx);
    }

    @TargetApi(14)
    public static Bundle zza(int i, String str, String str2, int i2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("com.google.android.gms.wallet.EXTRA_ENVIRONMENT", i);
        bundle.putBoolean("com.google.android.gms.wallet.EXTRA_USING_ANDROID_PAY_BRAND", z);
        bundle.putString("androidPackageName", str);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putParcelable("com.google.android.gms.wallet.EXTRA_BUYER_ACCOUNT", new Account(str2, "com.google"));
        }
        bundle.putInt("com.google.android.gms.wallet.EXTRA_THEME", i2);
        return bundle;
    }

    public void zza(FullWalletRequest fullWalletRequest, int i) {
        zzc zzc2 = new zzc(this.mContext, i);
        try {
            ((zzbku) zzxD()).zza(fullWalletRequest, zzUc(), (zzbkw) zzc2);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException getting full wallet", e);
            zzc2.zza(8, (FullWallet) null, Bundle.EMPTY);
        }
    }

    public void zza(IsReadyToPayRequest isReadyToPayRequest, com.google.android.gms.internal.zzaad.zzb<BooleanResult> zzb2) {
        zzb zzb3 = new zzb(zzb2);
        try {
            ((zzbku) zzxD()).zza(isReadyToPayRequest, zzUc(), (zzbkw) zzb3);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException during isReadyToPay", e);
            zzb3.zza(Status.zzazz, false, Bundle.EMPTY);
        }
    }

    public void zza(MaskedWalletRequest maskedWalletRequest, int i) {
        Bundle zzUc = zzUc();
        zzc zzc2 = new zzc(this.mContext, i);
        try {
            ((zzbku) zzxD()).zza(maskedWalletRequest, zzUc, (zzbkw) zzc2);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException getting masked wallet", e);
            zzc2.zza(8, (MaskedWallet) null, Bundle.EMPTY);
        }
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.wallet.internal.IOwService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.wallet.service.BIND";
    }

    public void zzf(String str, String str2, int i) {
        Bundle zzUc = zzUc();
        zzc zzc2 = new zzc(this.mContext, i);
        try {
            ((zzbku) zzxD()).zza(str, str2, zzUc, zzc2);
        } catch (RemoteException e) {
            Log.e("WalletClientImpl", "RemoteException changing masked wallet", e);
            zzc2.zza(8, (MaskedWallet) null, Bundle.EMPTY);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzfy */
    public zzbku zzh(IBinder iBinder) {
        return com.google.android.gms.internal.zzbku.zza.zzfv(iBinder);
    }

    public boolean zzxE() {
        return true;
    }
}
