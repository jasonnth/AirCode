package jumio.p317nv.nfc;

import android.os.AsyncTask;
import com.jumio.commons.log.Log;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

/* renamed from: jumio.nv.nfc.h */
/* compiled from: CertificateDatabase */
public class C5102h {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static Map<String, X509Certificate> f5577a;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static final Object f5578c = new Object();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public CertificateFactory f5579b;

    /* renamed from: jumio.nv.nfc.h$a */
    /* compiled from: CertificateDatabase */
    class C5104a extends AsyncTask<String, Integer, Map<String, X509Certificate>> {
        private C5104a() {
        }

        /* JADX WARNING: type inference failed for: r1v0 */
        /* JADX WARNING: type inference failed for: r4v0 */
        /* JADX WARNING: type inference failed for: r4v1 */
        /* JADX WARNING: type inference failed for: r4v2, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r4v3 */
        /* JADX WARNING: type inference failed for: r4v4, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r4v5 */
        /* JADX WARNING: type inference failed for: r4v6, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r4v7 */
        /* JADX WARNING: type inference failed for: r4v8, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r1v7, types: [java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r4v9 */
        /* JADX WARNING: type inference failed for: r4v10, types: [java.util.zip.ZipInputStream, java.io.Closeable] */
        /* JADX WARNING: type inference failed for: r1v8 */
        /* JADX WARNING: type inference failed for: r1v14, types: [java.io.ByteArrayInputStream] */
        /* JADX WARNING: type inference failed for: r2v16, types: [java.io.ByteArrayInputStream, java.io.InputStream] */
        /* JADX WARNING: type inference failed for: r1v15 */
        /* JADX WARNING: type inference failed for: r1v17 */
        /* JADX WARNING: type inference failed for: r1v18 */
        /* JADX WARNING: type inference failed for: r1v19 */
        /* JADX WARNING: type inference failed for: r4v11 */
        /* JADX WARNING: type inference failed for: r4v12 */
        /* JADX WARNING: type inference failed for: r4v13 */
        /* JADX WARNING: type inference failed for: r4v14 */
        /* JADX WARNING: type inference failed for: r4v15 */
        /* JADX WARNING: type inference failed for: r4v16 */
        /* JADX WARNING: type inference failed for: r4v17 */
        /* JADX WARNING: type inference failed for: r4v18 */
        /* JADX WARNING: type inference failed for: r4v19 */
        /* JADX WARNING: type inference failed for: r4v20 */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v1
          assigns: []
          uses: []
          mth insns count: 130
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0063 A[SYNTHETIC, Splitter:B:24:0x0063] */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0068 A[Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }] */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x00c1 A[SYNTHETIC, Splitter:B:53:0x00c1] */
        /* JADX WARNING: Removed duplicated region for block: B:70:0x0108 A[SYNTHETIC, Splitter:B:70:0x0108] */
        /* JADX WARNING: Removed duplicated region for block: B:78:0x011e A[SYNTHETIC, Splitter:B:78:0x011e] */
        /* JADX WARNING: Removed duplicated region for block: B:83:0x0127 A[Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }] */
        /* JADX WARNING: Unknown variable types count: 10 */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.Map<java.lang.String, java.security.cert.X509Certificate> doInBackground(java.lang.String... r9) {
            /*
                r8 = this;
                r1 = 0
                java.lang.Object r5 = jumio.p317nv.nfc.C5102h.f5578c
                monitor-enter(r5)
                java.util.Map r0 = jumio.p317nv.nfc.C5102h.f5577a     // Catch:{ all -> 0x00c5 }
                if (r0 == 0) goto L_0x001b
                java.lang.String r0 = "CertificateDatabase"
                java.lang.String r1 = "no need to download - skip"
                com.jumio.commons.log.Log.m1909d(r0, r1)     // Catch:{ all -> 0x00c5 }
                java.util.Map r0 = jumio.p317nv.nfc.C5102h.f5577a     // Catch:{ all -> 0x00c5 }
                monitor-exit(r5)     // Catch:{ all -> 0x00c5 }
            L_0x001a:
                return r0
            L_0x001b:
                java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x00c5 }
                r0.<init>()     // Catch:{ all -> 0x00c5 }
                jumio.p317nv.nfc.C5102h.f5577a = r0     // Catch:{ all -> 0x00c5 }
                java.net.URL r0 = new java.net.URL     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                r2 = 0
                r2 = r9[r2]     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                r0.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                java.net.URLConnection r0 = r0.openConnection()     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                r2 = 5000(0x1388, float:7.006E-42)
                r0.setReadTimeout(r2)     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                r2 = 5000(0x1388, float:7.006E-42)
                r0.setConnectTimeout(r2)     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                java.util.zip.ZipInputStream r4 = new java.util.zip.ZipInputStream     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                java.io.InputStream r0 = r0.getInputStream()     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
                r4.<init>(r0)     // Catch:{ FileNotFoundException -> 0x0138, SocketTimeoutException -> 0x0134, IOException -> 0x00f7, CertificateException -> 0x010d, all -> 0x0123 }
            L_0x0044:
                java.util.zip.ZipEntry r6 = r4.getNextEntry()     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                if (r6 == 0) goto L_0x00c8
                java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x013b }
                r3.<init>()     // Catch:{ all -> 0x013b }
                r0 = 1024(0x400, float:1.435E-42)
                byte[] r0 = new byte[r0]     // Catch:{ all -> 0x005f }
            L_0x0053:
                int r2 = r4.read(r0)     // Catch:{ all -> 0x005f }
                r7 = -1
                if (r2 == r7) goto L_0x0086
                r7 = 0
                r3.write(r0, r7, r2)     // Catch:{ all -> 0x005f }
                goto L_0x0053
            L_0x005f:
                r0 = move-exception
                r2 = r3
            L_0x0061:
                if (r1 == 0) goto L_0x0066
                r1.close()     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
            L_0x0066:
                if (r2 == 0) goto L_0x006b
                r2.close()     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
            L_0x006b:
                throw r0     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
            L_0x006c:
                r0 = move-exception
                r1 = r4
            L_0x006e:
                r0 = 0
                jumio.p317nv.nfc.C5102h.f5577a = r0     // Catch:{ all -> 0x012d }
                java.lang.String r0 = "CertificateDatabase"
                java.lang.String r2 = "zip file with certificates was not found at http://mobile-sdk-resources.jumio.com/android/assets/nv/certificates/2.1.0/certificates.zip"
                com.jumio.commons.log.Log.m1914e(r0, r2)     // Catch:{ all -> 0x012d }
                if (r1 == 0) goto L_0x0080
                com.jumio.commons.utils.IOUtils.closeQuietly(r1)     // Catch:{ all -> 0x00c5 }
            L_0x0080:
                monitor-exit(r5)     // Catch:{ all -> 0x00c5 }
                java.util.Map r0 = jumio.p317nv.nfc.C5102h.f5577a
                goto L_0x001a
            L_0x0086:
                java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x005f }
                byte[] r0 = r3.toByteArray()     // Catch:{ all -> 0x005f }
                r2.<init>(r0)     // Catch:{ all -> 0x005f }
                jumio.nv.nfc.h r0 = jumio.p317nv.nfc.C5102h.this     // Catch:{ all -> 0x013f }
                java.security.cert.CertificateFactory r0 = r0.f5579b     // Catch:{ all -> 0x013f }
                java.security.cert.Certificate r0 = r0.generateCertificate(r2)     // Catch:{ all -> 0x013f }
                java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0     // Catch:{ all -> 0x013f }
                java.util.Map r7 = jumio.p317nv.nfc.C5102h.f5577a     // Catch:{ all -> 0x013f }
                java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x013f }
                r7.put(r6, r0)     // Catch:{ all -> 0x013f }
                if (r2 == 0) goto L_0x00ab
                r2.close()     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
            L_0x00ab:
                if (r3 == 0) goto L_0x0044
                r3.close()     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                goto L_0x0044
            L_0x00b1:
                r0 = move-exception
            L_0x00b2:
                r1 = 0
                jumio.p317nv.nfc.C5102h.f5577a = r1     // Catch:{ all -> 0x012b }
                java.lang.String r1 = "CertificateDatabase"
                java.lang.String r2 = "Reading Certificates failed!"
                com.jumio.commons.log.Log.m1915e(r1, r2, r0)     // Catch:{ all -> 0x012b }
                if (r4 == 0) goto L_0x0080
                com.jumio.commons.utils.IOUtils.closeQuietly(r4)     // Catch:{ all -> 0x00c5 }
                goto L_0x0080
            L_0x00c5:
                r0 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x00c5 }
                throw r0
            L_0x00c8:
                java.lang.String r0 = "CertificateDatabase"
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                r1.<init>()     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                java.lang.String r2 = "downloaded "
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                java.util.Map r2 = jumio.p317nv.nfc.C5102h.f5577a     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                int r2 = r2.size()     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                java.lang.String r2 = " certificates"
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                java.lang.String r1 = r1.toString()     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                com.jumio.commons.log.Log.m1909d(r0, r1)     // Catch:{ FileNotFoundException -> 0x006c, SocketTimeoutException -> 0x00b1, IOException -> 0x0132, CertificateException -> 0x0130 }
                if (r4 == 0) goto L_0x0080
                com.jumio.commons.utils.IOUtils.closeQuietly(r4)     // Catch:{ all -> 0x00c5 }
                goto L_0x0080
            L_0x00f7:
                r0 = move-exception
                r4 = r1
            L_0x00f9:
                r1 = 0
                jumio.p317nv.nfc.C5102h.f5577a = r1     // Catch:{ all -> 0x012b }
                java.lang.String r1 = "CertificateDatabase"
                java.lang.String r2 = "error reading zip input stream: "
                com.jumio.commons.log.Log.m1915e(r1, r2, r0)     // Catch:{ all -> 0x012b }
                if (r4 == 0) goto L_0x0080
                com.jumio.commons.utils.IOUtils.closeQuietly(r4)     // Catch:{ all -> 0x00c5 }
                goto L_0x0080
            L_0x010d:
                r0 = move-exception
                r4 = r1
            L_0x010f:
                r1 = 0
                jumio.p317nv.nfc.C5102h.f5577a = r1     // Catch:{ all -> 0x012b }
                java.lang.String r1 = "CertificateDatabase"
                java.lang.String r2 = "error reading certificate files"
                com.jumio.commons.log.Log.m1915e(r1, r2, r0)     // Catch:{ all -> 0x012b }
                if (r4 == 0) goto L_0x0080
                com.jumio.commons.utils.IOUtils.closeQuietly(r4)     // Catch:{ all -> 0x00c5 }
                goto L_0x0080
            L_0x0123:
                r0 = move-exception
                r4 = r1
            L_0x0125:
                if (r4 == 0) goto L_0x012a
                com.jumio.commons.utils.IOUtils.closeQuietly(r4)     // Catch:{ all -> 0x00c5 }
            L_0x012a:
                throw r0     // Catch:{ all -> 0x00c5 }
            L_0x012b:
                r0 = move-exception
                goto L_0x0125
            L_0x012d:
                r0 = move-exception
                r4 = r1
                goto L_0x0125
            L_0x0130:
                r0 = move-exception
                goto L_0x010f
            L_0x0132:
                r0 = move-exception
                goto L_0x00f9
            L_0x0134:
                r0 = move-exception
                r4 = r1
                goto L_0x00b2
            L_0x0138:
                r0 = move-exception
                goto L_0x006e
            L_0x013b:
                r0 = move-exception
                r2 = r1
                goto L_0x0061
            L_0x013f:
                r0 = move-exception
                r1 = r2
                r2 = r3
                goto L_0x0061
            */
            throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5102h.C5104a.doInBackground(java.lang.String[]):java.util.Map");
        }
    }

    public C5102h(boolean z) throws CertificateException {
        this.f5579b = CertificateFactory.getInstance("X.509");
        Log.m1909d("CertificateDatabase", "ctor() -> downloadIfNecessary");
        if (z) {
            synchronized (f5578c) {
                f5577a = null;
            }
        }
        m3704a(true);
    }

    public C5102h() throws CertificateException {
        this(false);
    }

    /* renamed from: a */
    public boolean mo47166a(String str) {
        Log.m1909d("CertificateDatabase", "contains() -> downloadIfNecessary");
        m3704a(false);
        List b = mo47167b(str);
        if (b == null || b.isEmpty()) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public List<X509Certificate> mo47167b(String str) {
        Log.m1909d("CertificateDatabase", "get() -> downloadIfNecessary");
        m3704a(false);
        return m3706c(str);
    }

    /* renamed from: c */
    private List<X509Certificate> m3706c(String str) {
        String lowerCase = str.toLowerCase();
        ArrayList arrayList = new ArrayList();
        if (f5577a != null) {
            for (Entry entry : f5577a.entrySet()) {
                String str2 = (String) entry.getKey();
                X509Certificate x509Certificate = (X509Certificate) entry.getValue();
                if (str2.toLowerCase().startsWith(lowerCase) && str2.toLowerCase().endsWith(".cer")) {
                    arrayList.add(x509Certificate);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m3704a(boolean z) {
        Log.m1909d("CertificateDatabase", "    starting downloadIfNecessary");
        if (f5577a != null) {
            Log.m1909d("CertificateDatabase", "    don't download, already there");
            return;
        }
        C5104a aVar = new C5104a();
        if (z) {
            aVar.execute(new String[]{"http://mobile-sdk-resources.jumio.com/android/assets/nv/certificates/2.1.0/certificates.zip"});
            return;
        }
        try {
            aVar.execute(new String[]{"http://mobile-sdk-resources.jumio.com/android/assets/nv/certificates/2.1.0/certificates.zip"}).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
