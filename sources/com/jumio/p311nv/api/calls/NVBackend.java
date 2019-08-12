package com.jumio.p311nv.api.calls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.jumio.ale.swig.ALECore;
import com.jumio.analytics.JumioAnalytics;
import com.jumio.commons.obfuscate.StringObfuscater;
import com.jumio.commons.remote.exception.UnexpectedResponseException;
import com.jumio.core.mvp.model.Publisher;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall;
import com.jumio.core.network.ApiCall.DynamicProvider;
import com.jumio.core.network.EncryptionProvider;
import com.jumio.core.network.ale.AleSecurityConfig;
import com.jumio.p311nv.enums.NVErrorCase;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.MerchantSettingsModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.sdk.exception.JumioErrorCase;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.models.CredentialsModel;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLException;
import jumio.p317nv.core.C4918b;
import jumio.p317nv.core.C4920c;
import jumio.p317nv.core.C4921d;
import jumio.p317nv.core.C4922e;
import jumio.p317nv.core.C4923f;
import jumio.p317nv.core.C4924g;
import jumio.p317nv.core.C4925h;
import jumio.p317nv.core.C4927i;
import jumio.p317nv.core.C4930j;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;

/* renamed from: com.jumio.nv.api.calls.NVBackend */
public class NVBackend {
    public static final String USER_AGENT = "Jumio Netverify Android SDK JMSDK 2.4.0 (0-55)";

    /* renamed from: a */
    private static final String f3302a = StringObfuscater.format(new byte[]{-92, 67, 116, 10, 114, -8, 46, 47, 47, 39, 126, 125, -111, -124, -105, 47, 101, ISO7816.INS_READ_RECORD_STAMPED, 71, 93, ISO7816.INS_READ_BINARY_STAMPED, -7, 39, -45, ISOFileInfo.f6310A5, -22, 121, -73, ISO7816.INS_READ_RECORD2, -103, 60, 39, ISO7816.INS_DELETE_FILE, PassportService.SF_DG12, -88, -34, -34, 76, 87, 88, 37, ISO7816.INS_GET_DATA, 71, -43, ISO7816.INS_CREATE_FILE, -67, -12, 120, -86}, -4640201068163483407L);

    /* renamed from: b */
    private static final String f3303b = StringObfuscater.format(new byte[]{41, 0, 67, -126, 0, 71, ISOFileInfo.SECURITY_ATTR_EXP, -67, -75, -44, 41, 25, 77, ISO7816.INS_GET_DATA, -44, -23, 47, -44, -33, 85, 94, PassportService.SF_DG12, 24, ISOFileInfo.SECURITY_ATTR_EXP, -13, ISO7816.INS_APPEND_RECORD, 73, ISOFileInfo.f6310A5, -18, 10, 77, 73, 75, -47, 116, 104, ISOFileInfo.CHANNEL_SECURITY, -99, -8, -57, 106, ISO7816.INS_GET_DATA, 33, ISO7816.INS_INCREASE, -22, 46, -12, 22, ISO7816.INS_PSO, 19, ISOFileInfo.FCI_EXT, 109, -3}, -6229241285353764017L);

    /* renamed from: c */
    private static String f3304c = f3302a;

    /* renamed from: d */
    private static String f3305d = f3303b;

    /* renamed from: e */
    private static String f3306e = AleSecurityConfig.ALE_KEY_ID_US;

    /* renamed from: f */
    private static String f3307f = AleSecurityConfig.ALE_KEY_ID_EU;

    /* renamed from: g */
    private static String f3308g = AleSecurityConfig.ALE_PUBLIC_KEY_US;

    /* renamed from: h */
    private static String f3309h = AleSecurityConfig.ALE_PUBLIC_KEY_EU;

    /* renamed from: i */
    private static C4927i f3310i = new C4927i(new C4925h(), Executors.newSingleThreadExecutor());
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static ALECore f3311j;

    /* renamed from: com.jumio.nv.api.calls.NVBackend$a */
    static class C4435a implements DynamicProvider {

        /* renamed from: a */
        private Context f3313a;

        /* renamed from: b */
        private CredentialsModel f3314b;

        public C4435a(Context context, CredentialsModel credentialsModel) {
            this.f3313a = context;
            this.f3314b = credentialsModel;
        }

        public EncryptionProvider getEncryptionProvider() {
            return NVBackend.m1960b(this.f3313a, this.f3314b);
        }
    }

    /* renamed from: com.jumio.nv.api.calls.NVBackend$b */
    static class C4436b extends Publisher<Void> implements Callable<Void> {
        private C4436b() {
        }

        /* renamed from: a */
        public Void call() throws Exception {
            if (NVBackend.f3311j != null) {
                NVBackend.f3311j.delete();
                NVBackend.f3311j = null;
            }
            publishResult(null);
            return null;
        }
    }

    /* renamed from: a */
    private static String m1958a(CredentialsModel credentialsModel) {
        switch (credentialsModel.getDataCenter()) {
            case EU:
                return f3305d;
            case US:
                return f3304c;
            default:
                return null;
        }
    }

    public static ApiCall<String> initiate(Context context, CredentialsModel credentialsModel, MerchantSettingsModel merchantSettingsModel, ServerSettingsModel serverSettingsModel, Subscriber<String> subscriber) {
        C4923f fVar = new C4923f(context, new C4435a(context, credentialsModel), merchantSettingsModel, serverSettingsModel, f3310i);
        m1959a(context, subscriber, fVar, credentialsModel);
        return fVar;
    }

    public static ApiCall settings(Context context, CredentialsModel credentialsModel, MerchantSettingsModel merchantSettingsModel, Subscriber<String> subscriber) {
        C4930j jVar = new C4930j(context, new C4435a(context, credentialsModel), merchantSettingsModel, f3310i);
        m1959a(context, subscriber, jVar, credentialsModel);
        return jVar;
    }

    public static ApiCall addPart(Context context, CredentialsModel credentialsModel, NVScanPartModel nVScanPartModel, Subscriber<Void> subscriber, byte[] bArr) {
        C4918b bVar = new C4918b(context, new C4435a(context, credentialsModel), nVScanPartModel.getSideToScan(), nVScanPartModel.getScannedImage(), bArr, f3310i);
        m1959a(context, subscriber, bVar, credentialsModel);
        return bVar;
    }

    public static ApiCall liveness(Context context, CredentialsModel credentialsModel, Subscriber<Void> subscriber, String[] strArr) {
        C4924g gVar = new C4924g(context, new C4435a(context, credentialsModel), strArr, f3310i);
        m1959a(context, subscriber, gVar, credentialsModel);
        return gVar;
    }

    public static ApiCall data(Context context, CredentialsModel credentialsModel, Subscriber<Void> subscriber) {
        C4920c cVar = new C4920c(context, new C4435a(context, credentialsModel), f3310i);
        m1959a(context, subscriber, cVar, credentialsModel);
        return cVar;
    }

    public static ApiCall extractData(Context context, CredentialsModel credentialsModel, Subscriber<DocumentDataModel> subscriber, byte[] bArr) {
        C4921d dVar = new C4921d(context, new C4435a(context, credentialsModel), bArr, f3310i);
        m1959a(context, subscriber, dVar, credentialsModel);
        return dVar;
    }

    public static ApiCall finalizeCall(Context context, CredentialsModel credentialsModel, Subscriber<Void> subscriber) {
        C4922e eVar = new C4922e(context, new C4435a(context, credentialsModel), f3310i);
        m1959a(context, subscriber, eVar, credentialsModel);
        return eVar;
    }

    public static void registerForUpdates(Context context, Class<? extends ApiCall> cls, Subscriber subscriber) {
        f3310i.mo46868b(context, cls, subscriber);
    }

    public static void unregisterFromUpdates(Class<? extends ApiCall> cls, Subscriber subscriber) {
        f3310i.mo46865a(cls, subscriber);
    }

    public static void unlockAnalytics(Context context, CredentialsModel credentialsModel) {
        JumioAnalytics.unlock(context, new C4435a(context, credentialsModel), m1958a(credentialsModel), USER_AGENT);
    }

    public static int cancelAllPending() {
        int b = f3310i.mo46867b();
        f3310i.mo46869c();
        f3310i = new C4927i(new C4925h(), Executors.newSingleThreadExecutor());
        return b;
    }

    public static void forceRetry() {
        f3310i.mo46863a();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031 A[Catch:{ Exception -> 0x00b6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0060 A[Catch:{ Exception -> 0x00b6 }] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.jumio.core.network.EncryptionProvider m1960b(android.content.Context r6, com.jumio.sdk.models.CredentialsModel r7) {
        /*
            r0 = 0
            java.lang.Class<com.jumio.nv.api.calls.NVBackend> r4 = com.jumio.p311nv.api.calls.NVBackend.class
            monitor-enter(r4)
            com.jumio.ale.swig.ALECore r1 = f3311j     // Catch:{ all -> 0x00a3 }
            if (r1 != 0) goto L_0x00c4
            int[] r1 = com.jumio.p311nv.api.calls.NVBackend.C44341.f3312a     // Catch:{ all -> 0x00a3 }
            com.jumio.core.enums.JumioDataCenter r2 = r7.getDataCenter()     // Catch:{ all -> 0x00a3 }
            int r2 = r2.ordinal()     // Catch:{ all -> 0x00a3 }
            r1 = r1[r2]     // Catch:{ all -> 0x00a3 }
            switch(r1) {
                case 1: goto L_0x00a6;
                case 2: goto L_0x00ae;
                default: goto L_0x0017;
            }     // Catch:{ all -> 0x00a3 }
        L_0x0017:
            r2 = r0
            r3 = r0
        L_0x0019:
            com.jumio.core.environment.Environment.loadAleLib()     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = "^https://(?:nv|bam)-sdk\\.(.+?)?(?:\\.int)?(?:\\.)?jumio\\.com/.*$"
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)     // Catch:{ Exception -> 0x00b6 }
            java.lang.String r5 = m1958a(r7)     // Catch:{ Exception -> 0x00b6 }
            java.util.regex.Matcher r1 = r1.matcher(r5)     // Catch:{ Exception -> 0x00b6 }
            boolean r5 = r1.matches()     // Catch:{ Exception -> 0x00b6 }
            if (r5 == 0) goto L_0x0036
            r5 = 1
            java.lang.String r0 = r1.group(r5)     // Catch:{ Exception -> 0x00b6 }
        L_0x0036:
            r1 = r0
        L_0x0037:
            com.jumio.ale.swig.ALESettings r5 = new com.jumio.ale.swig.ALESettings     // Catch:{ all -> 0x00a3 }
            r5.<init>()     // Catch:{ all -> 0x00a3 }
            r5.setKeyID(r3)     // Catch:{ all -> 0x00a3 }
            r5.setPublicKey(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.io.File r2 = com.jumio.core.environment.Environment.getDataDirectory(r6)     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ all -> 0x00a3 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = "/ale/"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a3 }
            if (r1 == 0) goto L_0x0078
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            r2.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x00a3 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = "/"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a3 }
        L_0x0078:
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x00a3 }
            r1.<init>(r0)     // Catch:{ all -> 0x00a3 }
            boolean r2 = r1.exists()     // Catch:{ all -> 0x00a3 }
            if (r2 != 0) goto L_0x00ba
            boolean r2 = r1.mkdirs()     // Catch:{ all -> 0x00a3 }
            if (r2 != 0) goto L_0x00ba
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x00a3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            r2.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = "cannot create directory "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x00a3 }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a3 }
            r0.<init>(r1)     // Catch:{ all -> 0x00a3 }
            throw r0     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x00a6:
            java.lang.String r2 = f3307f     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = f3309h     // Catch:{ all -> 0x00a3 }
            r3 = r2
            r2 = r1
            goto L_0x0019
        L_0x00ae:
            java.lang.String r2 = f3306e     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = f3308g     // Catch:{ all -> 0x00a3 }
            r3 = r2
            r2 = r1
            goto L_0x0019
        L_0x00b6:
            r1 = move-exception
            r1 = r0
            goto L_0x0037
        L_0x00ba:
            r5.setDirectory(r0)     // Catch:{ all -> 0x00a3 }
            com.jumio.ale.swig.ALECore r0 = new com.jumio.ale.swig.ALECore     // Catch:{ all -> 0x00a3 }
            r0.<init>(r5)     // Catch:{ all -> 0x00a3 }
            f3311j = r0     // Catch:{ all -> 0x00a3 }
        L_0x00c4:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = r7.getApiToken()     // Catch:{ all -> 0x00a3 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = ":"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = r7.getApiSecret()     // Catch:{ all -> 0x00a3 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a3 }
            byte[] r0 = r0.getBytes()     // Catch:{ all -> 0x00a3 }
            r1 = 2
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r1)     // Catch:{ all -> 0x00a3 }
            com.jumio.core.network.ale.AleEncryptionProvider r1 = new com.jumio.core.network.ale.AleEncryptionProvider     // Catch:{ all -> 0x00a3 }
            com.jumio.ale.swig.ALECore r2 = f3311j     // Catch:{ all -> 0x00a3 }
            r1.<init>(r2, r0)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.p311nv.api.calls.NVBackend.m1960b(android.content.Context, com.jumio.sdk.models.CredentialsModel):com.jumio.core.network.EncryptionProvider");
    }

    public static synchronized void freeEncryption() {
        synchronized (NVBackend.class) {
            f3310i.mo46866a(new C4436b());
        }
    }

    /* renamed from: a */
    private static void m1959a(Context context, Subscriber subscriber, ApiCall apiCall, CredentialsModel credentialsModel) {
        f3310i.mo46868b(context, apiCall.getClass(), subscriber);
        apiCall.configure(m1958a(credentialsModel), USER_AGENT);
        f3310i.mo46866a(apiCall);
    }

    public static JumioException errorFromThrowable(Context context, Throwable th, Class<?> cls) {
        NetworkInfo networkInfo;
        int i;
        if (context != null) {
            networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } else {
            networkInfo = null;
        }
        JumioErrorCase jumioErrorCase = (networkInfo == null || !networkInfo.isConnected()) ? NVErrorCase.DEVICE_IS_OFFLINE : (!(th instanceof UnexpectedResponseException) || !((UnexpectedResponseException) th).isHttpUnauthorized()) ? (!(th instanceof UnexpectedResponseException) || ((UnexpectedResponseException) th).getStatusCode() != 403) ? (!(th instanceof UnexpectedResponseException) || ((UnexpectedResponseException) th).getStatusCode() != 305) ? th instanceof SSLException ? NVErrorCase.CERTIFICATE_ERROR : cls == C4930j.class ? NVErrorCase.REQUEST_SETTINGS_FAILED : cls == C4923f.class ? NVErrorCase.REQUEST_INITIATE_FAILED : cls == C4918b.class ? NVErrorCase.REQUEST_ADD_PART_FAILED : cls == C4922e.class ? NVErrorCase.REQUEST_FINALIZE_FAILED : cls == C4920c.class ? NVErrorCase.REQUEST_DATA_FAILED : cls == C4921d.class ? NVErrorCase.REQUEST_EXTRACT_DATA_FAILED : null : NVErrorCase.ALE_KEY_NOT_VALID : NVErrorCase.INVALID_CREDENTIALS : NVErrorCase.AUTH_FAILED;
        if (th instanceof UnexpectedResponseException) {
            i = ((UnexpectedResponseException) th).getStatusCode();
        } else {
            i = 0;
        }
        return new JumioException(jumioErrorCase, i);
    }
}
