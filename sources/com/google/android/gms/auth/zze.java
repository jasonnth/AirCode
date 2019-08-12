package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzn;
import com.google.android.gms.common.zzg;
import com.google.android.gms.internal.zzacm;
import com.google.android.gms.internal.zzvv;
import java.io.IOException;

public class zze {
    @SuppressLint({"InlinedApi"})
    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
    @SuppressLint({"InlinedApi"})
    public static final String KEY_CALLER_UID = "callerUid";
    /* access modifiers changed from: private */
    public static final zzacm zzaiA = zzd.zzb("GoogleAuthUtil");
    private static final String[] zzaiy = {"com.google", "com.google.work", "cn.google"};
    private static final ComponentName zzaiz = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");

    private interface zza<T> {
        T zzau(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException;
    }

    static {
        int i = VERSION.SDK_INT;
        int i2 = VERSION.SDK_INT;
    }

    public static String getToken(Context context, Account account, String str) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, account, str, new Bundle());
    }

    public static String getToken(Context context, Account account, String str, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        zzc(account);
        return zzc(context, account, str, bundle).getToken();
    }

    @Deprecated
    public static String getToken(Context context, String str, String str2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(str, "com.google"), str2);
    }

    @Deprecated
    public static void invalidateToken(Context context, String str) {
        AccountManager.get(context).invalidateAuthToken("com.google", str);
    }

    private static <T> T zza(Context context, ComponentName componentName, zza<T> zza2) throws IOException, GoogleAuthException {
        com.google.android.gms.common.zza zza3 = new com.google.android.gms.common.zza();
        zzn zzaU = zzn.zzaU(context);
        if (zzaU.zza(componentName, (ServiceConnection) zza3, "GoogleAuthUtil")) {
            try {
                T zzau = zza2.zzau(zza3.zzuX());
                zzaU.zzb(componentName, (ServiceConnection) zza3, "GoogleAuthUtil");
                return zzau;
            } catch (RemoteException | InterruptedException e) {
                zzaiA.zze("GoogleAuthUtil", "Error on service connection.", e);
                throw new IOException("Error on service connection.", e);
            } catch (Throwable th) {
                zzaU.zzb(componentName, (ServiceConnection) zza3, "GoogleAuthUtil");
                throw th;
            }
        } else {
            throw new IOException("Could not bind to service.");
        }
    }

    private static void zzaq(Context context) throws GoogleAuthException {
        try {
            zzg.zzaq(context.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            throw new GooglePlayServicesAvailabilityException(e.getConnectionStatusCode(), e.getMessage(), e.getIntent());
        } catch (GooglePlayServicesNotAvailableException e2) {
            throw new GoogleAuthException(e2.getMessage());
        }
    }

    public static TokenData zzc(Context context, final Account account, final String str, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        zzac.zzdk("Calling this from your main thread can lead to deadlock");
        zzac.zzh(str, "Scope cannot be empty or null.");
        zzc(account);
        zzaq(context);
        final Bundle bundle2 = bundle == null ? new Bundle() : new Bundle(bundle);
        String str2 = context.getApplicationInfo().packageName;
        bundle2.putString("clientPackageName", str2);
        if (TextUtils.isEmpty(bundle2.getString(KEY_ANDROID_PACKAGE_NAME))) {
            bundle2.putString(KEY_ANDROID_PACKAGE_NAME, str2);
        }
        bundle2.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        return (TokenData) zza(context, zzaiz, new zza<TokenData>() {
            /* renamed from: zzat */
            public TokenData zzau(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
                Bundle bundle = (Bundle) zze.zzn(com.google.android.gms.internal.zzcb.zza.zza(iBinder).zza(account, str, bundle2));
                TokenData zzd = TokenData.zzd(bundle, "tokenDetails");
                if (zzd != null) {
                    return zzd;
                }
                String string = bundle.getString("Error");
                Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
                zzvv zzcE = zzvv.zzcE(string);
                if (zzvv.zza(zzcE)) {
                    String valueOf = String.valueOf(zzcE);
                    zze.zzaiA.zzf("GoogleAuthUtil", new StringBuilder(String.valueOf(valueOf).length() + 31).append("isUserRecoverableError status: ").append(valueOf).toString());
                    throw new UserRecoverableAuthException(string, intent);
                } else if (zzvv.zzb(zzcE)) {
                    throw new IOException(string);
                } else {
                    throw new GoogleAuthException(string);
                }
            }
        });
    }

    private static void zzc(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        } else if (TextUtils.isEmpty(account.name)) {
            throw new IllegalArgumentException("Account name cannot be empty!");
        } else {
            String[] strArr = zzaiy;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                if (!strArr[i].equals(account.type)) {
                    i++;
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("Account type not supported");
        }
    }

    /* access modifiers changed from: private */
    public static <T> T zzn(T t) throws IOException {
        if (t != null) {
            return t;
        }
        zzaiA.zzf("GoogleAuthUtil", "Binder call returned null.");
        throw new IOException("Service unavailable.");
    }
}
