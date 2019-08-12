package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.LoaderManager.LoaderCallbacks;
import android.support.p000v4.content.Loader;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private zzn zzakI;
    private SignInConfiguration zzakJ;
    private boolean zzakK;
    /* access modifiers changed from: private */
    public int zzakL;
    /* access modifiers changed from: private */
    public Intent zzakM;

    private class zza implements LoaderCallbacks<Void> {
        private zza() {
        }

        public Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zzb(SignInHubActivity.this, GoogleApiClient.zzvm());
        }

        public void onLoaderReset(Loader<Void> loader) {
        }

        /* renamed from: zza */
        public void onLoadFinished(Loader<Void> loader, Void voidR) {
            SignInHubActivity.this.setResult(SignInHubActivity.this.zzakL, SignInHubActivity.this.zzakM);
            SignInHubActivity.this.finish();
        }
    }

    private void zza(int i, Intent intent) {
        if (intent != null) {
            SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra("signInAccount");
            if (signInAccount != null && signInAccount.zzro() != null) {
                GoogleSignInAccount zzro = signInAccount.zzro();
                this.zzakI.zzb(zzro, this.zzakJ.zzrz());
                intent.removeExtra("signInAccount");
                intent.putExtra("googleSignInAccount", zzro);
                this.zzakK = true;
                this.zzakL = i;
                this.zzakM = intent;
                zzrA();
                return;
            } else if (intent.hasExtra("errorCode")) {
                zzbq(intent.getIntExtra("errorCode", 8));
                return;
            }
        }
        zzbq(8);
    }

    private void zzbq(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
    }

    private void zzj(Intent intent) {
        intent.setPackage("com.google.android.gms");
        intent.putExtra("config", this.zzakJ);
        try {
            startActivityForResult(intent, 40962);
        } catch (ActivityNotFoundException e) {
            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
            zzbq(8);
        }
    }

    private void zzrA() {
        getSupportLoaderManager().initLoader(0, null, new zza());
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        setResult(0);
        switch (i) {
            case 40962:
                zza(i2, intent);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzakI = zzn.zzas(this);
        Intent intent = getIntent();
        if (!"com.google.android.gms.auth.GOOGLE_SIGN_IN".equals(intent.getAction())) {
            String str = "AuthSignInClient";
            String str2 = "Unknown action: ";
            String valueOf = String.valueOf(intent.getAction());
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            finish();
        }
        this.zzakJ = (SignInConfiguration) intent.getParcelableExtra("config");
        if (this.zzakJ == null) {
            Log.e("AuthSignInClient", "Activity started with invalid configuration.");
            setResult(0);
            finish();
        } else if (bundle == null) {
            zzj(new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN"));
        } else {
            this.zzakK = bundle.getBoolean("signingInGoogleApiClients");
            if (this.zzakK) {
                this.zzakL = bundle.getInt("signInResultCode");
                this.zzakM = (Intent) bundle.getParcelable("signInResultData");
                zzrA();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzakK);
        if (this.zzakK) {
            bundle.putInt("signInResultCode", this.zzakL);
            bundle.putParcelable("signInResultData", this.zzakM);
        }
    }
}
