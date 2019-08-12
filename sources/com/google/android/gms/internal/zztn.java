package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class zztn extends zzsa {
    protected boolean zzabJ;
    protected String zzacL;
    protected String zzacM;
    protected int zzafc;
    protected boolean zzagV;
    protected boolean zzagW;
    protected int zzage;

    public zztn(zzsc zzsc) {
        super(zzsc);
    }

    private static int zzck(String str) {
        String lowerCase = str.toLowerCase();
        if ("verbose".equals(lowerCase)) {
            return 0;
        }
        if ("info".equals(lowerCase)) {
            return 1;
        }
        if ("warning".equals(lowerCase)) {
            return 2;
        }
        return "error".equals(lowerCase) ? 3 : -1;
    }

    /* access modifiers changed from: 0000 */
    public void zza(zzsy zzsy) {
        zzbP("Loading global XML config values");
        if (zzsy.zzpG()) {
            String zzmY = zzsy.zzmY();
            this.zzacL = zzmY;
            zzb("XML config - app name", zzmY);
        }
        if (zzsy.zzpH()) {
            String zzmZ = zzsy.zzmZ();
            this.zzacM = zzmZ;
            zzb("XML config - app version", zzmZ);
        }
        if (zzsy.zzpI()) {
            int zzck = zzck(zzsy.zzpJ());
            if (zzck >= 0) {
                this.zzafc = zzck;
                zza("XML config - log level", Integer.valueOf(zzck));
            }
        }
        if (zzsy.zzpK()) {
            int zzpL = zzsy.zzpL();
            this.zzage = zzpL;
            this.zzagV = true;
            zzb("XML config - dispatch period (sec)", Integer.valueOf(zzpL));
        }
        if (zzsy.zzpM()) {
            boolean zzpN = zzsy.zzpN();
            this.zzabJ = zzpN;
            this.zzagW = true;
            zzb("XML config - dry run", Boolean.valueOf(zzpN));
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        zzqC();
    }

    public String zzmY() {
        zzob();
        return this.zzacL;
    }

    public String zzmZ() {
        zzob();
        return this.zzacM;
    }

    public boolean zzpI() {
        zzob();
        return false;
    }

    public boolean zzpK() {
        zzob();
        return this.zzagV;
    }

    public boolean zzpM() {
        zzob();
        return this.zzagW;
    }

    public boolean zzpN() {
        zzob();
        return this.zzabJ;
    }

    public int zzqB() {
        zzob();
        return this.zzage;
    }

    /* access modifiers changed from: protected */
    public void zzqC() {
        ApplicationInfo applicationInfo;
        Context context = getContext();
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 129);
        } catch (NameNotFoundException e) {
            zzd("PackageManager doesn't know about the app package", e);
            applicationInfo = null;
        }
        if (applicationInfo == null) {
            zzbS("Couldn't get ApplicationInfo to load global config");
            return;
        }
        Bundle bundle = applicationInfo.metaData;
        if (bundle != null) {
            int i = bundle.getInt("com.google.android.gms.analytics.globalConfigResource");
            if (i > 0) {
                zzsy zzsy = (zzsy) new zzsx(zznQ()).zzaI(i);
                if (zzsy != null) {
                    zza(zzsy);
                }
            }
        }
    }
}
