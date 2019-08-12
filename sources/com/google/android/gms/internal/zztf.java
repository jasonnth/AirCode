package com.google.android.gms.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.android.gms.common.internal.zzac;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;
import p005cn.jpush.android.JPushConstants;

class zztf extends zzsa {
    /* access modifiers changed from: private */
    public static final byte[] zzagz = "\n".getBytes();
    private final String zzIA = zza("GoogleAnalytics", zzsb.VERSION, VERSION.RELEASE, zztm.zza(Locale.getDefault()), Build.MODEL, Build.ID);
    private final zztj zzagy;

    private class zza {
        private int zzagA;
        private ByteArrayOutputStream zzagB = new ByteArrayOutputStream();

        public zza() {
        }

        public byte[] getPayload() {
            return this.zzagB.toByteArray();
        }

        public boolean zzj(zzsz zzsz) {
            zzac.zzw(zzsz);
            if (this.zzagA + 1 > zztf.this.zznT().zzph()) {
                return false;
            }
            String zza = zztf.this.zza(zzsz, false);
            if (zza == null) {
                zztf.this.zznS().zza(zzsz, "Error formatting hit");
                return true;
            }
            byte[] bytes = zza.getBytes();
            int length = bytes.length;
            if (length > zztf.this.zznT().zzoZ()) {
                zztf.this.zznS().zza(zzsz, "Hit size exceeds the maximum size limit");
                return true;
            }
            if (this.zzagB.size() > 0) {
                length++;
            }
            if (length + this.zzagB.size() > zztf.this.zznT().zzpb()) {
                return false;
            }
            try {
                if (this.zzagB.size() > 0) {
                    this.zzagB.write(zztf.zzagz);
                }
                this.zzagB.write(bytes);
                this.zzagA++;
                return true;
            } catch (IOException e) {
                zztf.this.zze("Failed to write payload when batching hits", e);
                return true;
            }
        }

        public int zzqd() {
            return this.zzagA;
        }
    }

    zztf(zzsc zzsc) {
        super(zzsc);
        this.zzagy = new zztj(zzsc.zznR());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0079 A[SYNTHETIC, Splitter:B:24:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008f A[SYNTHETIC, Splitter:B:34:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0094  */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int zza(java.net.URL r6, byte[] r7) {
        /*
            r5 = this;
            r1 = 0
            com.google.android.gms.common.internal.zzac.zzw(r6)
            com.google.android.gms.common.internal.zzac.zzw(r7)
            java.lang.String r0 = "POST bytes, url"
            int r2 = r7.length
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r5.zzb(r0, r2, r6)
            boolean r0 = r5.zzkI()
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = "Post payload\n"
            java.lang.String r2 = new java.lang.String
            r2.<init>(r7)
            r5.zza(r0, r2)
        L_0x0023:
            android.content.Context r0 = r5.getContext()     // Catch:{ IOException -> 0x006f, all -> 0x008b }
            r0.getPackageName()     // Catch:{ IOException -> 0x006f, all -> 0x008b }
            java.net.HttpURLConnection r2 = r5.zzc(r6)     // Catch:{ IOException -> 0x006f, all -> 0x008b }
            r0 = 1
            r2.setDoOutput(r0)     // Catch:{ IOException -> 0x00a2 }
            int r0 = r7.length     // Catch:{ IOException -> 0x00a2 }
            r2.setFixedLengthStreamingMode(r0)     // Catch:{ IOException -> 0x00a2 }
            r2.connect()     // Catch:{ IOException -> 0x00a2 }
            java.io.OutputStream r1 = r2.getOutputStream()     // Catch:{ IOException -> 0x00a2 }
            r1.write(r7)     // Catch:{ IOException -> 0x00a2 }
            r5.zzb(r2)     // Catch:{ IOException -> 0x00a2 }
            int r0 = r2.getResponseCode()     // Catch:{ IOException -> 0x00a2 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r0 != r3) goto L_0x0052
            com.google.android.gms.internal.zzry r3 = r5.zzmA()     // Catch:{ IOException -> 0x00a2 }
            r3.zznP()     // Catch:{ IOException -> 0x00a2 }
        L_0x0052:
            java.lang.String r3 = "POST status"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x00a2 }
            r5.zzb(r3, r4)     // Catch:{ IOException -> 0x00a2 }
            if (r1 == 0) goto L_0x0061
            r1.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0061:
            if (r2 == 0) goto L_0x0066
            r2.disconnect()
        L_0x0066:
            return r0
        L_0x0067:
            r1 = move-exception
            java.lang.String r3 = "Error closing http post connection output stream"
            r5.zze(r3, r1)
            goto L_0x0061
        L_0x006f:
            r0 = move-exception
            r2 = r1
        L_0x0071:
            java.lang.String r3 = "Network POST connection error"
            r5.zzd(r3, r0)     // Catch:{ all -> 0x00a0 }
            if (r1 == 0) goto L_0x007c
            r1.close()     // Catch:{ IOException -> 0x0083 }
        L_0x007c:
            if (r2 == 0) goto L_0x0081
            r2.disconnect()
        L_0x0081:
            r0 = 0
            goto L_0x0066
        L_0x0083:
            r0 = move-exception
            java.lang.String r1 = "Error closing http post connection output stream"
            r5.zze(r1, r0)
            goto L_0x007c
        L_0x008b:
            r0 = move-exception
            r2 = r1
        L_0x008d:
            if (r1 == 0) goto L_0x0092
            r1.close()     // Catch:{ IOException -> 0x0098 }
        L_0x0092:
            if (r2 == 0) goto L_0x0097
            r2.disconnect()
        L_0x0097:
            throw r0
        L_0x0098:
            r1 = move-exception
            java.lang.String r3 = "Error closing http post connection output stream"
            r5.zze(r3, r1)
            goto L_0x0092
        L_0x00a0:
            r0 = move-exception
            goto L_0x008d
        L_0x00a2:
            r0 = move-exception
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztf.zza(java.net.URL, byte[]):int");
    }

    private static String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    private void zza(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(str, JPushConstants.ENCODING_UTF_8));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, JPushConstants.ENCODING_UTF_8));
    }

    private int zzb(URL url) {
        zzac.zzw(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection zzc = zzc(url);
            zzc.connect();
            zzb(zzc);
            int responseCode = zzc.getResponseCode();
            if (responseCode == 200) {
                zzmA().zznP();
            }
            zzb("GET status", Integer.valueOf(responseCode));
            if (zzc == null) {
                return responseCode;
            }
            zzc.disconnect();
            return responseCode;
        } catch (IOException e) {
            zzd("Network GET connection error", e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return 0;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c9 A[SYNTHETIC, Splitter:B:42:0x00c9] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int zzb(java.net.URL r11, byte[] r12) {
        /*
            r10 = this;
            r1 = 0
            com.google.android.gms.common.internal.zzac.zzw(r11)
            com.google.android.gms.common.internal.zzac.zzw(r12)
            android.content.Context r0 = r10.getContext()     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            r0.getPackageName()     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            byte[] r4 = zzk(r12)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            java.lang.String r0 = "POST compressed size, ratio %, url"
            int r2 = r4.length     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            r6 = 100
            int r3 = r4.length     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            long r8 = (long) r3     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            long r6 = r6 * r8
            int r3 = r12.length     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            long r8 = (long) r3     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            long r6 = r6 / r8
            java.lang.Long r3 = java.lang.Long.valueOf(r6)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            r10.zza(r0, r2, r3, r11)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            int r0 = r4.length     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            int r2 = r12.length     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            if (r0 <= r2) goto L_0x003d
            java.lang.String r0 = "Compressed payload is larger then uncompressed. compressed, uncompressed"
            int r2 = r4.length     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            int r3 = r12.length     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            r10.zzc(r0, r2, r3)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
        L_0x003d:
            boolean r0 = r10.zzkI()     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            if (r0 == 0) goto L_0x005f
            java.lang.String r2 = "Post payload"
            java.lang.String r3 = "\n"
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            r0.<init>(r12)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            int r5 = r0.length()     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            if (r5 == 0) goto L_0x00a3
            java.lang.String r0 = r3.concat(r0)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
        L_0x005c:
            r10.zza(r2, r0)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
        L_0x005f:
            java.net.HttpURLConnection r3 = r10.zzc(r11)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            r0 = 1
            r3.setDoOutput(r0)     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            java.lang.String r0 = "Content-Encoding"
            java.lang.String r2 = "gzip"
            r3.addRequestProperty(r0, r2)     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            int r0 = r4.length     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            r3.setFixedLengthStreamingMode(r0)     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            r3.connect()     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            java.io.OutputStream r2 = r3.getOutputStream()     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            r2.write(r4)     // Catch:{ IOException -> 0x00e5, all -> 0x00dc }
            r2.close()     // Catch:{ IOException -> 0x00e5, all -> 0x00dc }
            r10.zzb(r3)     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            int r0 = r3.getResponseCode()     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            r2 = 200(0xc8, float:2.8E-43)
            if (r0 != r2) goto L_0x0093
            com.google.android.gms.internal.zzry r2 = r10.zzmA()     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            r2.zznP()     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
        L_0x0093:
            java.lang.String r2 = "POST status"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            r10.zzb(r2, r4)     // Catch:{ IOException -> 0x00e2, all -> 0x00da }
            if (r3 == 0) goto L_0x00a2
            r3.disconnect()
        L_0x00a2:
            return r0
        L_0x00a3:
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x00a9, all -> 0x00c5 }
            goto L_0x005c
        L_0x00a9:
            r0 = move-exception
            r2 = r1
        L_0x00ab:
            java.lang.String r3 = "Network compressed POST connection error"
            r10.zzd(r3, r0)     // Catch:{ all -> 0x00df }
            if (r1 == 0) goto L_0x00b6
            r1.close()     // Catch:{ IOException -> 0x00bd }
        L_0x00b6:
            if (r2 == 0) goto L_0x00bb
            r2.disconnect()
        L_0x00bb:
            r0 = 0
            goto L_0x00a2
        L_0x00bd:
            r0 = move-exception
            java.lang.String r1 = "Error closing http compressed post connection output stream"
            r10.zze(r1, r0)
            goto L_0x00b6
        L_0x00c5:
            r0 = move-exception
            r3 = r1
        L_0x00c7:
            if (r1 == 0) goto L_0x00cc
            r1.close()     // Catch:{ IOException -> 0x00d2 }
        L_0x00cc:
            if (r3 == 0) goto L_0x00d1
            r3.disconnect()
        L_0x00d1:
            throw r0
        L_0x00d2:
            r1 = move-exception
            java.lang.String r2 = "Error closing http compressed post connection output stream"
            r10.zze(r2, r1)
            goto L_0x00cc
        L_0x00da:
            r0 = move-exception
            goto L_0x00c7
        L_0x00dc:
            r0 = move-exception
            r1 = r2
            goto L_0x00c7
        L_0x00df:
            r0 = move-exception
            r3 = r2
            goto L_0x00c7
        L_0x00e2:
            r0 = move-exception
            r2 = r3
            goto L_0x00ab
        L_0x00e5:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x00ab
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zztf.zzb(java.net.URL, byte[]):int");
    }

    private URL zzb(zzsz zzsz, String str) {
        String sb;
        if (zzsz.zzpS()) {
            String valueOf = String.valueOf(zznT().zzpj());
            String valueOf2 = String.valueOf(zznT().zzpl());
            sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length() + String.valueOf(str).length()).append(valueOf).append(valueOf2).append("?").append(str).toString();
        } else {
            String valueOf3 = String.valueOf(zznT().zzpk());
            String valueOf4 = String.valueOf(zznT().zzpl());
            sb = new StringBuilder(String.valueOf(valueOf3).length() + 1 + String.valueOf(valueOf4).length() + String.valueOf(str).length()).append(valueOf3).append(valueOf4).append("?").append(str).toString();
        }
        try {
            return new URL(sb);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private void zzb(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            do {
            } while (inputStream.read(new byte[1024]) > 0);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    zze("Error closing http connection input stream", e);
                }
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zze("Error closing http connection input stream", e2);
                }
            }
        }
    }

    private boolean zzg(zzsz zzsz) {
        zzac.zzw(zzsz);
        String zza2 = zza(zzsz, !zzsz.zzpS());
        if (zza2 == null) {
            zznS().zza(zzsz, "Error formatting hit for upload");
            return true;
        } else if (zza2.length() <= zznT().zzoY()) {
            URL zzb = zzb(zzsz, zza2);
            if (zzb != null) {
                return zzb(zzb) == 200;
            }
            zzbT("Failed to build collect GET endpoint url");
            return false;
        } else {
            String zza3 = zza(zzsz, false);
            if (zza3 == null) {
                zznS().zza(zzsz, "Error formatting hit for POST upload");
                return true;
            }
            byte[] bytes = zza3.getBytes();
            if (bytes.length > zznT().zzpa()) {
                zznS().zza(zzsz, "Hit payload exceeds size limit");
                return true;
            }
            URL zzh = zzh(zzsz);
            if (zzh != null) {
                return zza(zzh, bytes) == 200;
            }
            zzbT("Failed to build collect POST endpoint url");
            return false;
        }
    }

    private URL zzh(zzsz zzsz) {
        String concat;
        if (zzsz.zzpS()) {
            String valueOf = String.valueOf(zznT().zzpj());
            String valueOf2 = String.valueOf(zznT().zzpl());
            concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            String valueOf3 = String.valueOf(zznT().zzpk());
            String valueOf4 = String.valueOf(zznT().zzpl());
            concat = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
        }
        try {
            return new URL(concat);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private String zzi(zzsz zzsz) {
        return String.valueOf(zzsz.zzpP());
    }

    private static byte[] zzk(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private URL zzqb() {
        String valueOf = String.valueOf(zznT().zzpj());
        String valueOf2 = String.valueOf(zznT().zzpm());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public String zza(zzsz zzsz, boolean z) {
        zzac.zzw(zzsz);
        StringBuilder sb = new StringBuilder();
        try {
            for (Entry entry : zzsz.zzfE().entrySet()) {
                String str = (String) entry.getKey();
                if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str) && !"z".equals(str) && !"_gmsv".equals(str)) {
                    zza(sb, str, (String) entry.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzsz.zzpQ()));
            zza(sb, "qt", String.valueOf(zznR().currentTimeMillis() - zzsz.zzpQ()));
            if (z) {
                long zzpT = zzsz.zzpT();
                zza(sb, "z", zzpT != 0 ? String.valueOf(zzpT) : zzi(zzsz));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public List<Long> zza(List<zzsz> list, boolean z) {
        zzac.zzaw(!list.isEmpty());
        zza("Uploading batched hits. compression, count", Boolean.valueOf(z), Integer.valueOf(list.size()));
        zza zza2 = new zza();
        ArrayList arrayList = new ArrayList();
        for (zzsz zzsz : list) {
            if (!zza2.zzj(zzsz)) {
                break;
            }
            arrayList.add(Long.valueOf(zzsz.zzpP()));
        }
        if (zza2.zzqd() == 0) {
            return arrayList;
        }
        URL zzqb = zzqb();
        if (zzqb == null) {
            zzbT("Failed to build batching endpoint url");
            return Collections.emptyList();
        }
        int zza3 = z ? zzb(zzqb, zza2.getPayload()) : zza(zzqb, zza2.getPayload());
        if (200 == zza3) {
            zza("Batched upload completed. Hits batched", Integer.valueOf(zza2.zzqd()));
            return arrayList;
        }
        zza("Network error uploading hits. status code", Integer.valueOf(zza3));
        if (zznT().zzpp().contains(Integer.valueOf(zza3))) {
            zzbS("Server instructed the client to stop batching");
            this.zzagy.start();
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: 0000 */
    public HttpURLConnection zzc(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain http connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(zznT().zzpw());
        httpURLConnection.setReadTimeout(zznT().zzpx());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestProperty("User-Agent", this.zzIA);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        zza("Network initialized. User agent", this.zzIA);
    }

    public boolean zzqa() {
        NetworkInfo networkInfo;
        zzmR();
        zzob();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        zzbP("No network connectivity");
        return false;
    }

    public List<Long> zzt(List<zzsz> list) {
        boolean z;
        boolean z2 = true;
        zzmR();
        zzob();
        zzac.zzw(list);
        if (zznT().zzpp().isEmpty() || !this.zzagy.zzA(zznT().zzpi() * 1000)) {
            z2 = false;
            z = false;
        } else {
            z = zznT().zzpn() != zzsj.NONE;
            if (zznT().zzpo() != zzsm.GZIP) {
                z2 = false;
            }
        }
        return z ? zza(list, z2) : zzu(list);
    }

    /* access modifiers changed from: 0000 */
    public List<Long> zzu(List<zzsz> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (zzsz zzsz : list) {
            if (zzg(zzsz)) {
                arrayList.add(Long.valueOf(zzsz.zzpP()));
                if (arrayList.size() >= zznT().zzpg()) {
                    break;
                }
            } else {
                break;
            }
        }
        return arrayList;
    }
}
