package com.google.android.gms.internal;

import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaad.zza;
import java.util.List;
import org.spongycastle.crypto.tls.CipherSuite;

public final class zzul implements AppIndexApi, zzuf {
    private static final String TAG = zzul.class.getSimpleName();

    private static abstract class zzb<T extends Result> extends zza<T, zzuj> {
        public zzb(GoogleApiClient googleApiClient) {
            super(zzto.zzagZ, googleApiClient);
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzb((Result) obj);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzug zzug) throws RemoteException;

        /* access modifiers changed from: protected */
        public final void zza(zzuj zzuj) throws RemoteException {
            zza(zzuj.zzqJ());
        }
    }

    public static abstract class zzc<T extends Result> extends zzb<Status> {
        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    public static final class zzd extends zzui<Status> {
        public zzd(com.google.android.gms.internal.zzaad.zzb<Status> zzb) {
            super(zzb);
        }

        public void zza(Status status) {
            this.zzahW.setResult(status);
        }
    }

    public static Intent zza(String str, Uri uri) {
        zzb(str, uri);
        if (zzm(uri)) {
            return new Intent("android.intent.action.VIEW", uri);
        }
        if (zzn(uri)) {
            return new Intent("android.intent.action.VIEW", zzl(uri));
        }
        String valueOf = String.valueOf(uri);
        throw new RuntimeException(new StringBuilder(String.valueOf(valueOf).length() + 70).append("appIndexingUri is neither an HTTP(S) URL nor an \"android-app://\" URL: ").append(valueOf).toString());
    }

    private PendingResult<Status> zza(GoogleApiClient googleApiClient, Action action, int i) {
        return zza(googleApiClient, zzuk.zza(action, System.currentTimeMillis(), googleApiClient.getContext().getPackageName(), i));
    }

    private static void zzb(String str, Uri uri) {
        if (zzm(uri)) {
            if (uri.getHost().isEmpty()) {
                String valueOf = String.valueOf(uri);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 98).append("AppIndex: The web URL must have a host (follow the format http(s)://<host>/<path>). Provided URI: ").append(valueOf).toString());
            }
        } else if (!zzn(uri)) {
            String valueOf2 = String.valueOf(uri);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf2).length() + CipherSuite.TLS_PSK_WITH_NULL_SHA256).append("AppIndex: The URI scheme must either be 'http(s)' or 'android-app'. If the latter, it must follow the format 'android-app://<package_name>/<scheme>/<host_path>'. Provided URI: ").append(valueOf2).toString());
        } else if (str == null || str.equals(uri.getHost())) {
            List pathSegments = uri.getPathSegments();
            if (pathSegments.isEmpty() || ((String) pathSegments.get(0)).isEmpty()) {
                String valueOf3 = String.valueOf(uri);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf3).length() + 128).append("AppIndex: The app URI scheme must exist and follow the format android-app://<package_name>/<scheme>/<host_path>). Provided URI: ").append(valueOf3).toString());
            }
        } else {
            String valueOf4 = String.valueOf(uri);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf4).length() + 150).append("AppIndex: The android-app URI host must match the package name and follow the format android-app://<package_name>/<scheme>/<host_path>. Provided URI: ").append(valueOf4).toString());
        }
    }

    private static Uri zzl(Uri uri) {
        List pathSegments = uri.getPathSegments();
        String str = (String) pathSegments.get(0);
        Builder builder = new Builder();
        builder.scheme(str);
        if (pathSegments.size() > 1) {
            builder.authority((String) pathSegments.get(1));
            int i = 2;
            while (true) {
                int i2 = i;
                if (i2 >= pathSegments.size()) {
                    break;
                }
                builder.appendPath((String) pathSegments.get(i2));
                i = i2 + 1;
            }
        } else {
            String str2 = TAG;
            String valueOf = String.valueOf(uri);
            Log.e(str2, new StringBuilder(String.valueOf(valueOf).length() + 88).append("The app URI must have the format: android-app://<package_name>/<scheme>/<path>. But got ").append(valueOf).toString());
        }
        builder.encodedQuery(uri.getEncodedQuery());
        builder.encodedFragment(uri.getEncodedFragment());
        return builder.build();
    }

    private static boolean zzm(Uri uri) {
        String scheme = uri.getScheme();
        return UriUtil.HTTP_SCHEME.equals(scheme) || UriUtil.HTTPS_SCHEME.equals(scheme);
    }

    private static boolean zzn(Uri uri) {
        return "android-app".equals(uri.getScheme());
    }

    public PendingResult<Status> end(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 2);
    }

    public PendingResult<Status> start(GoogleApiClient googleApiClient, Action action) {
        return zza(googleApiClient, action, 1);
    }

    public PendingResult<Status> zza(GoogleApiClient googleApiClient, final zzud... zzudArr) {
        return googleApiClient.zza(new zzc<Status>(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzug zzug) throws RemoteException {
                zzug.zza((zzuh) new zzd(this), (String) null, zzudArr);
            }
        });
    }
}
