package com.google.android.gms.ads.formats;

import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzqf;

public abstract class NativeAdView extends FrameLayout {
    private final FrameLayout zzrY;
    private final zzhh zzrZ;

    public void addView(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zzrY);
    }

    public void bringChildToFront(View view) {
        super.bringChildToFront(view);
        if (this.zzrY != view) {
            super.bringChildToFront(this.zzrY);
        }
    }

    public AdChoicesView getAdChoicesView() {
        View zzt = zzt("1098");
        if (zzt instanceof AdChoicesView) {
            return (AdChoicesView) zzt;
        }
        return null;
    }

    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.zzrZ != null) {
            try {
                this.zzrZ.zzb(zzd.zzA(view), i);
            } catch (RemoteException e) {
                zzqf.zzb("Unable to call onVisibilityChanged on delegate", e);
            }
        }
    }

    public void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzrY);
    }

    public void removeView(View view) {
        if (this.zzrY != view) {
            super.removeView(view);
        }
    }

    public void setAdChoicesView(AdChoicesView adChoicesView) {
        zza("1098", adChoicesView);
    }

    public void setNativeAd(NativeAd nativeAd) {
        try {
            this.zzrZ.zze((IObjectWrapper) nativeAd.zzbu());
        } catch (RemoteException e) {
            zzqf.zzb("Unable to call setNativeAd on delegate", e);
        }
    }

    /* access modifiers changed from: protected */
    public void zza(String str, View view) {
        try {
            this.zzrZ.zzd(str, zzd.zzA(view));
        } catch (RemoteException e) {
            zzqf.zzb("Unable to call setAssetView on delegate", e);
        }
    }

    /* access modifiers changed from: protected */
    public View zzt(String str) {
        try {
            IObjectWrapper zzU = this.zzrZ.zzU(str);
            if (zzU != null) {
                return (View) zzd.zzF(zzU);
            }
        } catch (RemoteException e) {
            zzqf.zzb("Unable to call getAssetView on delegate", e);
        }
        return null;
    }
}
