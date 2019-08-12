package com.jumio.p311nv.models;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.DownloadTask;
import com.jumio.commons.utils.DownloadTask.ProgressListener;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.Publisher;
import com.jumio.core.mvp.model.PublisherWithUpdate;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jumio.p317nv.core.C4934n;
import jumio.p317nv.core.C4935o;
import jumio.p317nv.core.C4936p;
import org.spongycastle.asn1.ASN1Encoding;

/* renamed from: com.jumio.nv.models.TemplateModel */
public class TemplateModel extends Publisher<Void> implements StaticModel {
    public static final String SERVER_BASE_URL = "http://mobile-sdk-resources.jumio.com/android/";

    /* renamed from: a */
    private static final NVDocumentType[] f3427a = {NVDocumentType.DRIVER_LICENSE, NVDocumentType.IDENTITY_CARD};

    /* renamed from: b */
    private transient C4934n f3428b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C4936p f3429c;

    /* renamed from: com.jumio.nv.models.TemplateModel$a */
    class C4458a implements C4934n {
        private C4458a() {
        }

        /* renamed from: a */
        public DownloadTask mo43586a(String str) {
            return new DownloadTask(str);
        }
    }

    /* renamed from: com.jumio.nv.models.TemplateModel$b */
    class C4459b extends PublisherWithUpdate<Float, NVDocumentType> implements ProgressListener {

        /* renamed from: b */
        private Country f3432b;

        /* renamed from: c */
        private NVDocumentType f3433c;

        /* renamed from: d */
        private ScanSide f3434d;

        /* renamed from: e */
        private Handler f3435e;

        public C4459b(Handler handler, Country country, NVDocumentType nVDocumentType, ScanSide scanSide) {
            this.f3432b = country;
            this.f3433c = nVDocumentType;
            this.f3434d = scanSide;
            this.f3435e = handler;
        }

        public void onProgressUpdate(float f) {
            publishUpdate(Float.valueOf(f));
        }

        public void onProgressDone(byte[] bArr) {
            try {
                Log.m1909d("TemplateModel", "download finished");
                if (bArr != null) {
                    TemplateModel.this.f3429c.mo46881a(TemplateModel.this.m1994a(this.f3432b.getIsoCode(), this.f3433c, this.f3434d), bArr);
                }
                Message message = new Message();
                message.obj = this.f3433c;
                message.arg1 = 0;
                this.f3435e.sendMessage(message);
                publishResult(this.f3433c);
            } catch (IOException e) {
                Log.m1931w("TemplateModel", (Throwable) e);
                Message message2 = new Message();
                message2.obj = this.f3433c;
                message2.arg1 = 1;
                this.f3435e.sendMessage(message2);
                publishError(e);
            }
        }
    }

    /* renamed from: com.jumio.nv.models.TemplateModel$c */
    static class C4460c extends Handler {

        /* renamed from: a */
        private ArrayList<NVDocumentType> f3436a;

        /* renamed from: b */
        private final WeakReference<TemplateModel> f3437b;

        public C4460c(TemplateModel templateModel) {
            super(Looper.getMainLooper());
            this.f3437b = new WeakReference<>(templateModel);
        }

        public void handleMessage(Message message) {
            TemplateModel templateModel = (TemplateModel) this.f3437b.get();
            if (templateModel != null) {
                templateModel.m1996a(message, this.f3436a);
            }
        }

        /* renamed from: a */
        public void mo43587a(NVDocumentType[] nVDocumentTypeArr) {
            if (this.f3436a == null) {
                this.f3436a = new ArrayList<>();
            } else {
                this.f3436a.clear();
            }
            Collections.addAll(this.f3436a, nVDocumentTypeArr);
        }
    }

    public TemplateModel(Context context) {
        this.f3429c = new C4935o(context);
        this.f3428b = new C4458a();
    }

    public TemplateModel(C4936p pVar, C4934n nVar) {
        this.f3429c = pVar;
        this.f3428b = nVar;
    }

    /* renamed from: a */
    private static String m1991a() {
        return "http://mobile-sdk-resources.jumio.com/android/assets/nv/templatematcher/1.91.10/";
    }

    public boolean hasTemplate(Country country, DocumentType documentType) {
        String str;
        if (documentType.isThirdPartyOcrDefined()) {
            String a = m1994a(country.getIsoCode(), documentType.getId(), documentType.getDocumentScanSide());
            if (!this.f3429c.mo46883a(a)) {
                try {
                    boolean exists = m1990a(m1991a() + a).exists();
                    String str2 = "TemplateModel";
                    if (exists) {
                        str = "template exists only on server";
                    } else {
                        str = "template not available";
                    }
                    Log.m1909d(str2, str);
                    return exists;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.m1909d("TemplateModel", "template already cached");
                return true;
            }
        }
        Log.m1909d("TemplateModel", "thirdPartyOcr not defined!");
        return false;
    }

    /* renamed from: a */
    private DownloadTask m1990a(String str) {
        return this.f3428b.mo43586a(str);
    }

    public List<String> getTemplate(Country country, NVDocumentType nVDocumentType, ScanSide scanSide) {
        if (this.f3429c.mo46883a(m1994a(country.getIsoCode(), nVDocumentType, scanSide))) {
            Log.m1909d("TemplateModel", "getTemplate() cache hit");
            return this.f3429c.mo46884b(country, nVDocumentType, scanSide);
        }
        Log.m1909d("TemplateModel", "getTemplate(): cache miss");
        return null;
    }

    public void getOrLoad(Country country, NVDocumentType[] nVDocumentTypeArr) {
        if (nVDocumentTypeArr == null || nVDocumentTypeArr.length == 0) {
            nVDocumentTypeArr = f3427a;
        }
        C4460c cVar = new C4460c(this);
        cVar.mo43587a(nVDocumentTypeArr);
        try {
            for (NVDocumentType nVDocumentType : nVDocumentTypeArr) {
                if (this.f3429c.mo46882a(country, nVDocumentType, ScanSide.FRONT)) {
                    Log.m1909d("TemplateModel", "getOrLoad(): cache hit - publishing");
                    Message message = new Message();
                    message.obj = nVDocumentType;
                    message.arg1 = 0;
                    cVar.sendMessage(message);
                } else {
                    Log.m1909d("TemplateModel", "getOrLoad(): cache miss - trying download");
                    DownloadTask a = m1990a(m1991a() + m1994a(country.getIsoCode(), nVDocumentType, ScanSide.FRONT));
                    a.setListener(new C4459b(cVar, country, nVDocumentType, ScanSide.FRONT));
                    a.start();
                }
            }
        } catch (Exception e) {
            publishError(e);
        }
    }

    /* access modifiers changed from: protected */
    public boolean existsOnServer(Country country, NVDocumentType nVDocumentType, ScanSide scanSide) throws Exception {
        return m1990a(m1991a() + m1994a(country.getIsoCode(), nVDocumentType, scanSide)).exists();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public String m1994a(String str, NVDocumentType nVDocumentType, ScanSide scanSide) {
        return str.toUpperCase() + "_" + m1992a(nVDocumentType).toLowerCase() + "_" + scanSide.toString().toLowerCase() + ".bin";
    }

    /* renamed from: a */
    private String m1992a(NVDocumentType nVDocumentType) {
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

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1996a(Message message, ArrayList<NVDocumentType> arrayList) {
        if (message.obj instanceof NVDocumentType) {
            if (arrayList.contains(message.obj)) {
                arrayList.remove(message.obj);
            }
            if (arrayList.isEmpty()) {
                publishResult(null);
            }
        }
    }
}
