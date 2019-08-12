package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

public class zzrx extends zzsa {
    public static boolean zzadA;
    private Info zzadB;
    private final zztj zzadC;
    private String zzadD;
    private boolean zzadE = false;
    private Object zzadF = new Object();

    zzrx(zzsc zzsc) {
        super(zzsc);
        this.zzadC = new zztj(zzsc.zznR());
    }

    private boolean zza(Info info, Info info2) {
        String str;
        String str2 = null;
        String id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        String zzoQ = zznX().zzoQ();
        synchronized (this.zzadF) {
            if (!this.zzadE) {
                this.zzadD = zznJ();
                this.zzadE = true;
            } else if (TextUtils.isEmpty(this.zzadD)) {
                if (info != null) {
                    str2 = info.getId();
                }
                if (str2 == null) {
                    String valueOf = String.valueOf(id);
                    String valueOf2 = String.valueOf(zzoQ);
                    boolean zzbO = zzbO(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
                    return zzbO;
                }
                String valueOf3 = String.valueOf(str2);
                String valueOf4 = String.valueOf(zzoQ);
                this.zzadD = zzbN(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3));
            }
            String valueOf5 = String.valueOf(id);
            String valueOf6 = String.valueOf(zzoQ);
            String zzbN = zzbN(valueOf6.length() != 0 ? valueOf5.concat(valueOf6) : new String(valueOf5));
            if (TextUtils.isEmpty(zzbN)) {
                return false;
            }
            if (zzbN.equals(this.zzadD)) {
                return true;
            }
            if (!TextUtils.isEmpty(this.zzadD)) {
                zzbP("Resetting the client id because Advertising Id changed.");
                str = zznX().zzoR();
                zza("New client Id", str);
            } else {
                str = zzoQ;
            }
            String valueOf7 = String.valueOf(id);
            String valueOf8 = String.valueOf(str);
            boolean zzbO2 = zzbO(valueOf8.length() != 0 ? valueOf7.concat(valueOf8) : new String(valueOf7));
            return zzbO2;
        }
    }

    private static String zzbN(String str) {
        MessageDigest zzch = zztm.zzch("MD5");
        if (zzch == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzch.digest(str.getBytes()))});
    }

    private boolean zzbO(String str) {
        try {
            String zzbN = zzbN(str);
            zzbP("Storing hashed adid.");
            FileOutputStream openFileOutput = getContext().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zzbN.getBytes());
            openFileOutput.close();
            this.zzadD = zzbN;
            return true;
        } catch (IOException e) {
            zze("Error creating hash file", e);
            return false;
        }
    }

    private synchronized Info zznH() {
        if (this.zzadC.zzA(1000)) {
            this.zzadC.start();
            Info zznI = zznI();
            if (zza(this.zzadB, zznI)) {
                this.zzadB = zznI;
            } else {
                zzbT("Failed to reset client id on adid change. Not using adid");
                this.zzadB = new Info("", false);
            }
        }
        return this.zzadB;
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public String zznG() {
        zzob();
        Info zznH = zznH();
        String str = zznH != null ? zznH.getId() : null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    /* access modifiers changed from: protected */
    public Info zznI() {
        boolean z = false;
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(getContext());
        } catch (IllegalStateException e) {
            zzbS("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return z;
        } catch (Throwable th) {
            if (zzadA) {
                return z;
            }
            zzadA = true;
            zzd("Error getting advertiser id", th);
            return z;
        }
    }

    /* access modifiers changed from: protected */
    public String zznJ() {
        String str = null;
        try {
            FileInputStream openFileInput = getContext().openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                zzbS("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                getContext().deleteFile("gaClientIdData");
                return null;
            } else if (read <= 0) {
                zzbP("Hash file is empty.");
                openFileInput.close();
                return null;
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                    return str2;
                } catch (FileNotFoundException e) {
                    return str2;
                } catch (IOException e2) {
                    Object obj = e2;
                    str = str2;
                    e = obj;
                    zzd("Error reading Hash file, deleting it", e);
                    getContext().deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
            return null;
        } catch (IOException e4) {
            e = e4;
            zzd("Error reading Hash file, deleting it", e);
            getContext().deleteFile("gaClientIdData");
            return str;
        }
    }

    public boolean zznw() {
        zzob();
        Info zznH = zznH();
        return zznH != null && !zznH.isLimitAdTrackingEnabled();
    }
}
