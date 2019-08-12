package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzhf.zza;
import java.util.ArrayList;
import java.util.List;

@zzme
public class zzhm extends NativeContentAd {
    private final VideoController zzAl = new VideoController();
    private final List<Image> zzHs = new ArrayList();
    private final zzhl zzHu;
    private final zzhg zzHv;

    public zzhm(zzhl zzhl) {
        zzhg zzhg;
        this.zzHu = zzhl;
        try {
            List<Object> images = this.zzHu.getImages();
            if (images != null) {
                for (Object zze : images) {
                    zzhf zze2 = zze(zze);
                    if (zze2 != null) {
                        this.zzHs.add(new zzhg(zze2));
                    }
                }
            }
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get image.", e);
        }
        try {
            zzhf zzfV = this.zzHu.zzfV();
            if (zzfV != null) {
                zzhg = new zzhg(zzfV);
                this.zzHv = zzhg;
            }
        } catch (RemoteException e2) {
            zzqf.zzb("Failed to get icon.", e2);
        }
        zzhg = null;
        this.zzHv = zzhg;
    }

    public CharSequence getAdvertiser() {
        try {
            return this.zzHu.getAdvertiser();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get attribution.", e);
            return null;
        }
    }

    public CharSequence getBody() {
        try {
            return this.zzHu.getBody();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get body.", e);
            return null;
        }
    }

    public CharSequence getCallToAction() {
        try {
            return this.zzHu.getCallToAction();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public CharSequence getHeadline() {
        try {
            return this.zzHu.getHeadline();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public List<Image> getImages() {
        return this.zzHs;
    }

    public Image getLogo() {
        return this.zzHv;
    }

    public VideoController getVideoController() {
        try {
            if (this.zzHu.zzbF() != null) {
                this.zzAl.zza(this.zzHu.zzbF());
            }
        } catch (RemoteException e) {
            zzqf.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzAl;
    }

    /* access modifiers changed from: 0000 */
    public zzhf zze(Object obj) {
        if (obj instanceof IBinder) {
            return zza.zzB((IBinder) obj);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzfR */
    public IObjectWrapper zzbu() {
        try {
            return this.zzHu.zzfR();
        } catch (RemoteException e) {
            zzqf.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}
