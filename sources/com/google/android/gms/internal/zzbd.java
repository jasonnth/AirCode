package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzag.zza;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzbd {
    private static final String TAG = zzbd.class.getSimpleName();
    protected static final Object zzqC = new Object();
    private static zze zzqE = null;
    protected static final Object zzqy = new Object();
    private volatile boolean zzpU = false;
    private GoogleApiClient zzqA = null;
    protected boolean zzqB = false;
    protected boolean zzqD = false;
    protected boolean zzqF = false;
    private Map<Pair<String, String>, zzbz> zzqG;
    protected Context zzqn;
    protected Context zzqo;
    private ExecutorService zzqp;
    private DexClassLoader zzqq;
    private zzay zzqr;
    private byte[] zzqs;
    private volatile AdvertisingIdClient zzqt = null;
    private Future zzqu = null;
    private volatile zza zzqv = null;
    private Future zzqw = null;
    /* access modifiers changed from: private */
    public volatile boolean zzqx = false;
    private zzaq zzqz;

    private zzbd(Context context) {
        this.zzqn = context;
        this.zzqo = context.getApplicationContext();
        this.zzqG = new HashMap();
    }

    public static zzbd zza(Context context, String str, String str2, boolean z) {
        zzbd zzbd = new zzbd(context);
        try {
            zzbd.zzb(str, str2, z);
            return zzbd;
        } catch (zzba e) {
            return null;
        }
    }

    private File zza(String str, File file, String str2) throws zzay.zza, IOException {
        File file2 = new File(String.format("%s/%s.jar", new Object[]{file, str2}));
        if (!file2.exists()) {
            byte[] zzc = this.zzqr.zzc(this.zzqs, str);
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(zzc, 0, zzc.length);
            fileOutputStream.close();
        }
        return file2;
    }

    private void zza(File file) {
        if (!file.exists()) {
            Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
            return;
        }
        file.delete();
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r0v15, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r0v18 */
    /* JADX WARNING: type inference failed for: r0v20 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r0v22 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r0v24 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r1v20 */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* JADX WARNING: type inference failed for: r1v22 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r0v33 */
    /* JADX WARNING: type inference failed for: r0v34 */
    /* JADX WARNING: type inference failed for: r0v35 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009d A[SYNTHETIC, Splitter:B:27:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a2 A[SYNTHETIC, Splitter:B:30:0x00a2] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ae A[SYNTHETIC, Splitter:B:36:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b3 A[SYNTHETIC, Splitter:B:39:0x00b3] */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zza(java.io.File r12, java.lang.String r13) {
        /*
            r11 = this;
            r1 = 0
            r7 = 2
            r6 = 1
            r5 = 0
            java.io.File r3 = new java.io.File
            java.lang.String r0 = "%s/%s.tmp"
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r2[r5] = r12
            r2[r6] = r13
            java.lang.String r0 = java.lang.String.format(r0, r2)
            r3.<init>(r0)
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x001d
        L_0x001c:
            return
        L_0x001d:
            java.io.File r4 = new java.io.File
            java.lang.String r0 = "%s/%s.dex"
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r2[r5] = r12
            r2[r6] = r13
            java.lang.String r0 = java.lang.String.format(r0, r2)
            r4.<init>(r0)
            boolean r0 = r4.exists()
            if (r0 == 0) goto L_0x001c
            long r6 = r4.length()
            r8 = 0
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x001c
            int r0 = (int) r6
            byte[] r0 = new byte[r0]
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00d6, NoSuchAlgorithmException -> 0x0099, zza -> 0x00e0, all -> 0x00aa }
            r2.<init>(r4)     // Catch:{ IOException -> 0x00d6, NoSuchAlgorithmException -> 0x0099, zza -> 0x00e0, all -> 0x00aa }
            int r5 = r2.read(r0)     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            if (r5 > 0) goto L_0x0054
            r2.close()     // Catch:{ IOException -> 0x00ba }
        L_0x0050:
            r11.zza(r4)
            goto L_0x001c
        L_0x0054:
            com.google.android.gms.internal.zzag$zzd r5 = new com.google.android.gms.internal.zzag$zzd     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            r5.<init>()     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            byte[] r6 = r6.getBytes()     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            r5.zzcx = r6     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            byte[] r6 = r13.getBytes()     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            r5.zzcw = r6     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            com.google.android.gms.internal.zzay r6 = r11.zzqr     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            byte[] r7 = r11.zzqs     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            java.lang.String r0 = r6.zzd(r7, r0)     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            byte[] r0 = r0.getBytes()     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            r5.data = r0     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            byte[] r0 = com.google.android.gms.internal.zzao.zzh(r0)     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            r5.zzcv = r0     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            r3.createNewFile()     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x00d9, NoSuchAlgorithmException -> 0x00cf, zza -> 0x00e3, all -> 0x00c8 }
            byte[] r1 = com.google.android.gms.internal.zzbyj.zzf(r5)     // Catch:{ IOException -> 0x00dd, NoSuchAlgorithmException -> 0x00d3, zza -> 0x00e7, all -> 0x00ca }
            r3 = 0
            int r5 = r1.length     // Catch:{ IOException -> 0x00dd, NoSuchAlgorithmException -> 0x00d3, zza -> 0x00e7, all -> 0x00ca }
            r0.write(r1, r3, r5)     // Catch:{ IOException -> 0x00dd, NoSuchAlgorithmException -> 0x00d3, zza -> 0x00e7, all -> 0x00ca }
            r0.close()     // Catch:{ IOException -> 0x00dd, NoSuchAlgorithmException -> 0x00d3, zza -> 0x00e7, all -> 0x00ca }
            r2.close()     // Catch:{ IOException -> 0x00bc }
        L_0x0092:
            r0.close()     // Catch:{ IOException -> 0x00be }
        L_0x0095:
            r11.zza(r4)
            goto L_0x001c
        L_0x0099:
            r0 = move-exception
            r0 = r1
        L_0x009b:
            if (r1 == 0) goto L_0x00a0
            r1.close()     // Catch:{ IOException -> 0x00c0 }
        L_0x00a0:
            if (r0 == 0) goto L_0x00a5
            r0.close()     // Catch:{ IOException -> 0x00c2 }
        L_0x00a5:
            r11.zza(r4)
            goto L_0x001c
        L_0x00aa:
            r0 = move-exception
            r2 = r1
        L_0x00ac:
            if (r2 == 0) goto L_0x00b1
            r2.close()     // Catch:{ IOException -> 0x00c4 }
        L_0x00b1:
            if (r1 == 0) goto L_0x00b6
            r1.close()     // Catch:{ IOException -> 0x00c6 }
        L_0x00b6:
            r11.zza(r4)
            throw r0
        L_0x00ba:
            r0 = move-exception
            goto L_0x0050
        L_0x00bc:
            r1 = move-exception
            goto L_0x0092
        L_0x00be:
            r0 = move-exception
            goto L_0x0095
        L_0x00c0:
            r1 = move-exception
            goto L_0x00a0
        L_0x00c2:
            r0 = move-exception
            goto L_0x00a5
        L_0x00c4:
            r2 = move-exception
            goto L_0x00b1
        L_0x00c6:
            r1 = move-exception
            goto L_0x00b6
        L_0x00c8:
            r0 = move-exception
            goto L_0x00ac
        L_0x00ca:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
            goto L_0x00ac
        L_0x00cf:
            r0 = move-exception
            r0 = r1
            r1 = r2
            goto L_0x009b
        L_0x00d3:
            r1 = move-exception
            r1 = r2
            goto L_0x009b
        L_0x00d6:
            r0 = move-exception
            r0 = r1
            goto L_0x009b
        L_0x00d9:
            r0 = move-exception
            r0 = r1
            r1 = r2
            goto L_0x009b
        L_0x00dd:
            r1 = move-exception
            r1 = r2
            goto L_0x009b
        L_0x00e0:
            r0 = move-exception
            r0 = r1
            goto L_0x009b
        L_0x00e3:
            r0 = move-exception
            r0 = r1
            r1 = r2
            goto L_0x009b
        L_0x00e7:
            r1 = move-exception
            r1 = r2
            goto L_0x009b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbd.zza(java.io.File, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void zzaT() {
        try {
            if (this.zzqt == null && this.zzqo != null) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzqo);
                advertisingIdClient.start();
                this.zzqt = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e) {
            this.zzqt = null;
        }
    }

    private void zzaU() {
        if (((Boolean) zzgd.zzDV.get()).booleanValue()) {
            zzaV();
        }
    }

    /* access modifiers changed from: private */
    public void zzaW() {
        if (this.zzqD) {
            try {
                this.zzqv = zzaqg.zzq(this.zzqn, this.zzqn.getPackageName(), Integer.toString(this.zzqn.getPackageManager().getPackageInfo(this.zzqn.getPackageName(), 0).versionCode));
            } catch (Throwable th) {
            }
        }
    }

    private void zzaX() {
        boolean z = true;
        this.zzqp.execute(new Runnable() {
            public void run() {
                zzgd.initialize(zzbd.this.zzqn);
            }
        });
        try {
            zzqE = zze.zzuY();
            this.zzqB = zzqE.zzaC(this.zzqn) > 0;
            if (zzqE.isGooglePlayServicesAvailable(this.zzqn) != 0) {
                z = false;
            }
            this.zzqD = z;
            if (this.zzqn.getApplicationContext() != null) {
                this.zzqA = new Builder(this.zzqn).addApi(zzzk.API).build();
            }
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r4v6, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r4v7 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r0v7, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r0v15 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r0v22 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r0v24 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r0v26 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r4v11 */
    /* JADX WARNING: type inference failed for: r4v12 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c5 A[SYNTHETIC, Splitter:B:42:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ca A[SYNTHETIC, Splitter:B:45:0x00ca] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d4 A[SYNTHETIC, Splitter:B:51:0x00d4] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d9 A[SYNTHETIC, Splitter:B:54:0x00d9] */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean zzb(java.io.File r11, java.lang.String r12) {
        /*
            r10 = this;
            r1 = 0
            r6 = 2
            r0 = 1
            r2 = 0
            java.io.File r3 = new java.io.File
            java.lang.String r4 = "%s/%s.tmp"
            java.lang.Object[] r5 = new java.lang.Object[r6]
            r5[r2] = r11
            r5[r0] = r12
            java.lang.String r4 = java.lang.String.format(r4, r5)
            r3.<init>(r4)
            boolean r4 = r3.exists()
            if (r4 != 0) goto L_0x001e
            r0 = r2
        L_0x001d:
            return r0
        L_0x001e:
            java.io.File r5 = new java.io.File
            java.lang.String r4 = "%s/%s.dex"
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r2] = r11
            r6[r0] = r12
            java.lang.String r4 = java.lang.String.format(r4, r6)
            r5.<init>(r4)
            boolean r4 = r5.exists()
            if (r4 == 0) goto L_0x0038
            r0 = r2
            goto L_0x001d
        L_0x0038:
            long r6 = r3.length()     // Catch:{ IOException -> 0x00f8, NoSuchAlgorithmException -> 0x00c1, zza -> 0x0103, all -> 0x00d0 }
            r8 = 0
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 > 0) goto L_0x0047
            r10.zza(r3)     // Catch:{ IOException -> 0x00f8, NoSuchAlgorithmException -> 0x00c1, zza -> 0x0103, all -> 0x00d0 }
            r0 = r2
            goto L_0x001d
        L_0x0047:
            int r4 = (int) r6     // Catch:{ IOException -> 0x00f8, NoSuchAlgorithmException -> 0x00c1, zza -> 0x0103, all -> 0x00d0 }
            byte[] r6 = new byte[r4]     // Catch:{ IOException -> 0x00f8, NoSuchAlgorithmException -> 0x00c1, zza -> 0x0103, all -> 0x00d0 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00f8, NoSuchAlgorithmException -> 0x00c1, zza -> 0x0103, all -> 0x00d0 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x00f8, NoSuchAlgorithmException -> 0x00c1, zza -> 0x0103, all -> 0x00d0 }
            int r7 = r4.read(r6)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            if (r7 > 0) goto L_0x0065
            java.lang.String r0 = TAG     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            java.lang.String r5 = "Cannot read the cache data."
            android.util.Log.d(r0, r5)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            r10.zza(r3)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            r4.close()     // Catch:{ IOException -> 0x00dd }
        L_0x0063:
            r0 = r2
            goto L_0x001d
        L_0x0065:
            com.google.android.gms.internal.zzag$zzd r6 = com.google.android.gms.internal.zzag.zzd.zze(r6)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            java.lang.String r7 = new java.lang.String     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            byte[] r8 = r6.zzcw     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            r7.<init>(r8)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            boolean r7 = r12.equals(r7)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            if (r7 == 0) goto L_0x0092
            byte[] r7 = r6.zzcv     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            byte[] r8 = r6.data     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            byte[] r8 = com.google.android.gms.internal.zzao.zzh(r8)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            boolean r7 = java.util.Arrays.equals(r7, r8)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            if (r7 == 0) goto L_0x0092
            byte[] r7 = r6.zzcx     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            java.lang.String r8 = android.os.Build.VERSION.SDK     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            byte[] r8 = r8.getBytes()     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            boolean r7 = java.util.Arrays.equals(r7, r8)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            if (r7 != 0) goto L_0x009a
        L_0x0092:
            r10.zza(r3)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            r4.close()     // Catch:{ IOException -> 0x00df }
        L_0x0098:
            r0 = r2
            goto L_0x001d
        L_0x009a:
            com.google.android.gms.internal.zzay r3 = r10.zzqr     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            byte[] r7 = r10.zzqs     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            java.lang.String r8 = new java.lang.String     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            byte[] r6 = r6.data     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            r8.<init>(r6)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            byte[] r6 = r3.zzc(r7, r8)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            r5.createNewFile()     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            r3.<init>(r5)     // Catch:{ IOException -> 0x00fb, NoSuchAlgorithmException -> 0x00f0, zza -> 0x0106, all -> 0x00eb }
            r1 = 0
            int r5 = r6.length     // Catch:{ IOException -> 0x00ff, NoSuchAlgorithmException -> 0x00f4, zza -> 0x010a, all -> 0x00ed }
            r3.write(r6, r1, r5)     // Catch:{ IOException -> 0x00ff, NoSuchAlgorithmException -> 0x00f4, zza -> 0x010a, all -> 0x00ed }
            r4.close()     // Catch:{ IOException -> 0x00e1 }
        L_0x00b9:
            r3.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x001d
        L_0x00be:
            r1 = move-exception
            goto L_0x001d
        L_0x00c1:
            r0 = move-exception
            r0 = r1
        L_0x00c3:
            if (r1 == 0) goto L_0x00c8
            r1.close()     // Catch:{ IOException -> 0x00e3 }
        L_0x00c8:
            if (r0 == 0) goto L_0x00cd
            r0.close()     // Catch:{ IOException -> 0x00e5 }
        L_0x00cd:
            r0 = r2
            goto L_0x001d
        L_0x00d0:
            r0 = move-exception
            r4 = r1
        L_0x00d2:
            if (r4 == 0) goto L_0x00d7
            r4.close()     // Catch:{ IOException -> 0x00e7 }
        L_0x00d7:
            if (r1 == 0) goto L_0x00dc
            r1.close()     // Catch:{ IOException -> 0x00e9 }
        L_0x00dc:
            throw r0
        L_0x00dd:
            r0 = move-exception
            goto L_0x0063
        L_0x00df:
            r0 = move-exception
            goto L_0x0098
        L_0x00e1:
            r1 = move-exception
            goto L_0x00b9
        L_0x00e3:
            r1 = move-exception
            goto L_0x00c8
        L_0x00e5:
            r0 = move-exception
            goto L_0x00cd
        L_0x00e7:
            r2 = move-exception
            goto L_0x00d7
        L_0x00e9:
            r1 = move-exception
            goto L_0x00dc
        L_0x00eb:
            r0 = move-exception
            goto L_0x00d2
        L_0x00ed:
            r0 = move-exception
            r1 = r3
            goto L_0x00d2
        L_0x00f0:
            r0 = move-exception
            r0 = r1
            r1 = r4
            goto L_0x00c3
        L_0x00f4:
            r0 = move-exception
            r0 = r3
            r1 = r4
            goto L_0x00c3
        L_0x00f8:
            r0 = move-exception
            r0 = r1
            goto L_0x00c3
        L_0x00fb:
            r0 = move-exception
            r0 = r1
            r1 = r4
            goto L_0x00c3
        L_0x00ff:
            r0 = move-exception
            r0 = r3
            r1 = r4
            goto L_0x00c3
        L_0x0103:
            r0 = move-exception
            r0 = r1
            goto L_0x00c3
        L_0x0106:
            r0 = move-exception
            r0 = r1
            r1 = r4
            goto L_0x00c3
        L_0x010a:
            r0 = move-exception
            r0 = r3
            r1 = r4
            goto L_0x00c3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbd.zzb(java.io.File, java.lang.String):boolean");
    }

    private boolean zzb(String str, String str2, boolean z) throws zzba {
        this.zzqp = Executors.newCachedThreadPool();
        zzc(z);
        zzaX();
        zzaU();
        if (!zzbf.zzbc() || !((Boolean) zzgd.zzDT.get()).booleanValue()) {
            zzo(str);
            zzp(str2);
            this.zzqz = new zzaq(this);
            return true;
        }
        throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
    }

    private void zzc(boolean z) {
        this.zzpU = z;
        if (z) {
            this.zzqu = this.zzqp.submit(new Runnable() {
                public void run() {
                    zzbd.this.zzaT();
                }
            });
        }
    }

    private void zzo(String str) throws zzba {
        this.zzqr = new zzay(null);
        try {
            this.zzqs = this.zzqr.zzn(str);
        } catch (zzay.zza e) {
            throw new zzba(e);
        }
    }

    private boolean zzp(String str) throws zzba {
        File file;
        String zzU;
        File zza;
        try {
            File cacheDir = this.zzqn.getCacheDir();
            if (cacheDir == null) {
                cacheDir = this.zzqn.getDir("dex", 0);
                if (cacheDir == null) {
                    throw new zzba();
                }
            }
            file = cacheDir;
            zzU = zzaz.zzU();
            zza = zza(str, file, zzU);
            zzb(file, zzU);
            this.zzqq = new DexClassLoader(zza.getAbsolutePath(), file.getAbsolutePath(), null, this.zzqn.getClassLoader());
            zza(zza);
            zza(file, zzU);
            zzq(String.format("%s/%s.dex", new Object[]{file, zzU}));
            return true;
        } catch (FileNotFoundException e) {
            throw new zzba(e);
        } catch (IOException e2) {
            throw new zzba(e2);
        } catch (zzay.zza e3) {
            throw new zzba(e3);
        } catch (NullPointerException e4) {
            throw new zzba(e4);
        } catch (Throwable th) {
            zza(zza);
            zza(file, zzU);
            zzq(String.format("%s/%s.dex", new Object[]{file, zzU}));
            throw th;
        }
    }

    private void zzq(String str) {
        zza(new File(str));
    }

    public Context getApplicationContext() {
        return this.zzqo;
    }

    public Context getContext() {
        return this.zzqn;
    }

    public int zzQ() {
        zzaq zzaP = zzaP();
        if (zzaP != null) {
            return zzaP.zzQ();
        }
        return Integer.MIN_VALUE;
    }

    public boolean zza(String str, String str2, List<Class> list) {
        if (this.zzqG.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzqG.put(new Pair(str, str2), new zzbz(this, str, str2, list));
        return true;
    }

    public ExecutorService zzaI() {
        return this.zzqp;
    }

    public DexClassLoader zzaJ() {
        return this.zzqq;
    }

    public zzay zzaK() {
        return this.zzqr;
    }

    public byte[] zzaL() {
        return this.zzqs;
    }

    public GoogleApiClient zzaM() {
        return this.zzqA;
    }

    public boolean zzaN() {
        return this.zzqB;
    }

    public boolean zzaO() {
        return this.zzqF;
    }

    public zzaq zzaP() {
        return this.zzqz;
    }

    public boolean zzaQ() {
        return this.zzqD;
    }

    public zza zzaR() {
        return this.zzqv;
    }

    public Future zzaS() {
        return this.zzqw;
    }

    public void zzaV() {
        synchronized (zzqy) {
            if (!this.zzqx) {
                this.zzqw = this.zzqp.submit(new Runnable() {
                    public void run() {
                        zzbd.this.zzaW();
                        synchronized (zzbd.zzqy) {
                            zzbd.this.zzqx = false;
                        }
                    }
                });
                this.zzqx = true;
            }
        }
    }

    public AdvertisingIdClient zzaY() {
        if (!this.zzpU) {
            return null;
        }
        if (this.zzqt != null) {
            return this.zzqt;
        }
        if (this.zzqu != null) {
            try {
                this.zzqu.get(2000, TimeUnit.MILLISECONDS);
                this.zzqu = null;
            } catch (InterruptedException | ExecutionException e) {
            } catch (TimeoutException e2) {
                this.zzqu.cancel(true);
            }
        }
        return this.zzqt;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzaZ() {
        /*
            r2 = this;
            java.lang.Object r1 = zzqC     // Catch:{ Throwable -> 0x001e }
            monitor-enter(r1)     // Catch:{ Throwable -> 0x001e }
            boolean r0 = r2.zzqF     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
        L_0x0008:
            return
        L_0x0009:
            boolean r0 = r2.zzqD     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0020
            com.google.android.gms.common.api.GoogleApiClient r0 = r2.zzqA     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x0020
            com.google.android.gms.common.api.GoogleApiClient r0 = r2.zzqA     // Catch:{ all -> 0x001b }
            r0.connect()     // Catch:{ all -> 0x001b }
            r0 = 1
            r2.zzqF = r0     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            goto L_0x0008
        L_0x001b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            throw r0     // Catch:{ Throwable -> 0x001e }
        L_0x001e:
            r0 = move-exception
            goto L_0x0008
        L_0x0020:
            r0 = 0
            r2.zzqF = r0     // Catch:{ all -> 0x001b }
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbd.zzaZ():void");
    }

    public void zzba() {
        synchronized (zzqC) {
            if (this.zzqF && this.zzqA != null) {
                this.zzqA.disconnect();
                this.zzqF = false;
            }
        }
    }

    public Method zzc(String str, String str2) {
        zzbz zzbz = (zzbz) this.zzqG.get(new Pair(str, str2));
        if (zzbz == null) {
            return null;
        }
        return zzbz.zzbm();
    }
}
