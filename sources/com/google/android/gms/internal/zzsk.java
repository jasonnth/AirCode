package com.google.android.gms.internal;

import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.zzac;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class zzsk extends zzsa {
    private volatile String zzadh;
    private Future<String> zzaeS;

    protected zzsk(zzsc zzsc) {
        super(zzsc);
    }

    /* access modifiers changed from: private */
    public String zzoT() {
        String zzoU = zzoU();
        try {
            return !zzx(zznU().getContext(), zzoU) ? AppEventsConstants.EVENT_PARAM_VALUE_NO : zzoU;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
    }

    private boolean zzx(Context context, String str) {
        zzac.zzdr(str);
        zzac.zzdk("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStream = null;
        try {
            zza("Storing clientId", str);
            fileOutputStream = context.openFileOutput("gaClientId", 0);
            fileOutputStream.write(str.getBytes());
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zze("Failed to close clientId writing stream", e);
                }
            }
            return true;
        } catch (FileNotFoundException e2) {
            zze("Error creating clientId file", e2);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (IOException e3) {
                zze("Failed to close clientId writing stream", e3);
                return false;
            }
        } catch (IOException e4) {
            zze("Error writing to clientId file", e4);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (IOException e5) {
                zze("Failed to close clientId writing stream", e5);
                return false;
            }
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    zze("Failed to close clientId writing stream", e6);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f A[SYNTHETIC, Splitter:B:34:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009b A[SYNTHETIC, Splitter:B:44:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ac A[SYNTHETIC, Splitter:B:51:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzap(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r1 = "ClientId should be loaded from worker thread"
            com.google.android.gms.common.internal.zzac.zzdk(r1)
            java.lang.String r1 = "gaClientId"
            java.io.FileInputStream r2 = r7.openFileInput(r1)     // Catch:{ FileNotFoundException -> 0x007b, IOException -> 0x008b, all -> 0x00a7 }
            r1 = 36
            byte[] r3 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            r1 = 0
            r4 = 36
            int r4 = r2.read(r3, r1, r4)     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            int r1 = r2.available()     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            if (r1 <= 0) goto L_0x003c
            java.lang.String r1 = "clientId file seems corrupted, deleting it."
            r6.zzbS(r1)     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            if (r2 == 0) goto L_0x0033
            r2.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0033:
            return r0
        L_0x0034:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x0033
        L_0x003c:
            r1 = 14
            if (r4 >= r1) goto L_0x005d
            java.lang.String r1 = "clientId file is empty, deleting it."
            r6.zzbS(r1)     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            if (r2 == 0) goto L_0x0033
            r2.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0033
        L_0x0055:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x0033
        L_0x005d:
            r2.close()     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            java.lang.String r1 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            r5 = 0
            r1.<init>(r3, r5, r4)     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            java.lang.String r3 = "Read client id from disk"
            r6.zza(r3, r1)     // Catch:{ FileNotFoundException -> 0x00bc, IOException -> 0x00ba }
            if (r2 == 0) goto L_0x0071
            r2.close()     // Catch:{ IOException -> 0x0073 }
        L_0x0071:
            r0 = r1
            goto L_0x0033
        L_0x0073:
            r0 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r0)
            goto L_0x0071
        L_0x007b:
            r1 = move-exception
            r1 = r0
        L_0x007d:
            if (r1 == 0) goto L_0x0033
            r1.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0033
        L_0x0083:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x0033
        L_0x008b:
            r1 = move-exception
            r2 = r0
        L_0x008d:
            java.lang.String r3 = "Error reading client id file, deleting it"
            r6.zze(r3, r1)     // Catch:{ all -> 0x00b8 }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ all -> 0x00b8 }
            if (r2 == 0) goto L_0x0033
            r2.close()     // Catch:{ IOException -> 0x009f }
            goto L_0x0033
        L_0x009f:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x0033
        L_0x00a7:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x00aa:
            if (r2 == 0) goto L_0x00af
            r2.close()     // Catch:{ IOException -> 0x00b0 }
        L_0x00af:
            throw r0
        L_0x00b0:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x00af
        L_0x00b8:
            r0 = move-exception
            goto L_0x00aa
        L_0x00ba:
            r1 = move-exception
            goto L_0x008d
        L_0x00bc:
            r1 = move-exception
            r1 = r2
            goto L_0x007d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzsk.zzap(android.content.Context):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }

    public String zzoQ() {
        String str;
        zzob();
        synchronized (this) {
            if (this.zzadh == null) {
                this.zzaeS = zznU().zzc(new Callable<String>() {
                    /* renamed from: zzbY */
                    public String call() throws Exception {
                        return zzsk.this.zzoS();
                    }
                });
            }
            if (this.zzaeS != null) {
                try {
                    this.zzadh = (String) this.zzaeS.get();
                } catch (InterruptedException e) {
                    zzd("ClientId loading or generation was interrupted", e);
                    this.zzadh = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                } catch (ExecutionException e2) {
                    zze("Failed to load or generate client id", e2);
                    this.zzadh = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                }
                if (this.zzadh == null) {
                    this.zzadh = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                }
                zza("Loaded clientId", this.zzadh);
                this.zzaeS = null;
            }
            str = this.zzadh;
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    public String zzoR() {
        synchronized (this) {
            this.zzadh = null;
            this.zzaeS = zznU().zzc(new Callable<String>() {
                /* renamed from: zzbY */
                public String call() throws Exception {
                    return zzsk.this.zzoT();
                }
            });
        }
        return zzoQ();
    }

    /* access modifiers changed from: 0000 */
    public String zzoS() {
        String zzap = zzap(zznU().getContext());
        return zzap == null ? zzoT() : zzap;
    }

    /* access modifiers changed from: protected */
    public String zzoU() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
