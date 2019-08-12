package jumio.p317nv.core;

import android.content.Context;
import com.jumio.commons.log.Log;
import com.jumio.core.data.document.ScanSide;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.environment.NVEnvironment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.spongycastle.asn1.ASN1Encoding;

/* renamed from: jumio.nv.core.o */
/* compiled from: TemplateMatcherFileStorage */
public class C4935o implements C4936p {

    /* renamed from: a */
    private final transient Context f4820a;

    public C4935o(Context context) {
        this.f4820a = context;
    }

    /* renamed from: a */
    public boolean mo46882a(Country country, NVDocumentType nVDocumentType, ScanSide scanSide) {
        try {
            if (new File(m3066a(), m3068a(country.getIsoCode(), nVDocumentType, scanSide)).exists()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.m1931w("TemplateMatcher", (Throwable) e);
            return false;
        }
    }

    /* renamed from: b */
    public List<String> mo46884b(Country country, NVDocumentType nVDocumentType, ScanSide scanSide) {
        ArrayList arrayList = new ArrayList();
        File file = new File(m3066a(), m3068a(country.getIsoCode(), nVDocumentType, scanSide));
        if (file.exists()) {
            arrayList.add(file.getAbsolutePath());
        }
        return arrayList;
    }

    /* renamed from: a */
    public boolean mo46883a(String str) {
        try {
            return new File(m3066a(), str).exists();
        } catch (Exception e) {
            Log.m1930w("TemplateMatcher", "hasTemplate", (Throwable) e);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo46881a(java.lang.String r4, byte[] r5) throws java.io.IOException {
        /*
            r3 = this;
            if (r5 != 0) goto L_0x0010
            java.io.IOException r0 = new java.io.IOException
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "data array was null!"
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x0010:
            if (r4 != 0) goto L_0x0020
            java.io.IOException r0 = new java.io.IOException
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "destination file path was null"
            r1.<init>(r2)
            r0.<init>(r1)
            throw r0
        L_0x0020:
            java.io.File r0 = new java.io.File
            java.io.File r1 = r3.m3066a()
            r0.<init>(r1, r4)
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x003b }
            r1.<init>(r0)     // Catch:{ all -> 0x003b }
            r1.write(r5)     // Catch:{ all -> 0x0043 }
            r1.flush()     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x003a
            com.jumio.commons.utils.IOUtils.closeQuietly(r1)
        L_0x003a:
            return
        L_0x003b:
            r0 = move-exception
            r1 = r2
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            com.jumio.commons.utils.IOUtils.closeQuietly(r1)
        L_0x0042:
            throw r0
        L_0x0043:
            r0 = move-exception
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.core.C4935o.mo46881a(java.lang.String, byte[]):void");
    }

    /* renamed from: a */
    private String m3068a(String str, NVDocumentType nVDocumentType, ScanSide scanSide) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.toUpperCase());
        sb.append("_");
        sb.append(m3067a(nVDocumentType).toLowerCase());
        sb.append("_");
        sb.append(scanSide.toString().toLowerCase());
        sb.append(".bin");
        return sb.toString();
    }

    /* renamed from: a */
    private String m3067a(NVDocumentType nVDocumentType) {
        if (nVDocumentType == NVDocumentType.DRIVER_LICENSE) {
            return ASN1Encoding.f6356DL;
        }
        if (nVDocumentType == NVDocumentType.IDENTITY_CARD) {
            return "ID";
        }
        if (nVDocumentType == NVDocumentType.PASSPORT) {
            return "PP";
        }
        return nVDocumentType.toString();
    }

    /* renamed from: a */
    private File m3066a() {
        File file = new File(this.f4820a.getFilesDir() + File.separator + "tm_" + NVEnvironment.CLIENTLIB_VERSION + File.separator);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
