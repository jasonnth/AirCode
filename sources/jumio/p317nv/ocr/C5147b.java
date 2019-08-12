package jumio.p317nv.ocr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import com.jumio.clientlib.impl.imagequality.ImageQualityAcquisition;
import com.jumio.clientlib.impl.imagequality.PixelFormatType;
import com.jumio.clientlib.impl.livenessAndTM.FrameProcessorTemplateMatcher;
import com.jumio.clientlib.impl.livenessAndTM.LibImage;
import com.jumio.commons.camera.CameraManager.Size;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.commons.log.Log;
import com.jumio.core.ImageQuality;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.ocr.environment.NVOcrEnvironment;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: jumio.nv.ocr.b */
/* compiled from: TemplateMatcherClient */
public class C5147b extends ExtractionClient<ExtractionUpdate, DocumentDataModel> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final Handler f5774a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final Object f5775b = new Object();

    /* renamed from: c */
    private final Object f5776c = new Object();
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final AtomicBoolean f5777d = new AtomicBoolean(false);

    /* renamed from: e */
    private final Object f5778e = new Object();

    /* renamed from: f */
    private final int f5779f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public int f5780g = 10;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C5151c f5781h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public ExecutorService f5782i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public boolean f5783j = true;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public FrameProcessorTemplateMatcher f5784k = null;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public C5154e f5785l = null;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public int f5786m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public int f5787n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public boolean f5788o = false;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public byte[] f5789p = null;

    /* renamed from: q */
    private byte[] f5790q = null;

    /* renamed from: jumio.nv.ocr.b$a */
    /* compiled from: TemplateMatcherClient */
    class C5149a implements Runnable {
        private C5149a() {
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r7 = this;
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this
                java.lang.Object r6 = r0.f5775b
                monitor-enter(r6)
                r1 = 500(0x1f4, float:7.0E-43)
                com.jumio.clientlib.impl.livenessAndTM.HierarchicalClusteringSettings r3 = new com.jumio.clientlib.impl.livenessAndTM.HierarchicalClusteringSettings     // Catch:{ all -> 0x00b4 }
                r3.<init>()     // Catch:{ all -> 0x00b4 }
                com.jumio.nv.benchmark.Benchmark r0 = new com.jumio.nv.benchmark.Benchmark     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r2 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                android.content.Context r2 = r2.mContext     // Catch:{ all -> 0x00b4 }
                r0.<init>(r2)     // Catch:{ all -> 0x00b4 }
                com.jumio.nv.benchmark.BenchmarkAlgorithm$DeviceCategory r0 = r0.getDeviceCategory()     // Catch:{ all -> 0x00b4 }
                com.jumio.nv.benchmark.BenchmarkAlgorithm$DeviceCategory r2 = com.jumio.p311nv.benchmark.BenchmarkAlgorithm.DeviceCategory.FAST     // Catch:{ all -> 0x00b4 }
                if (r0 != r2) goto L_0x00a7
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r2 = 15
                r0.f5780g = r2     // Catch:{ all -> 0x00b4 }
            L_0x0028:
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r2 = 15
                r0.f5780g = r2     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                int r4 = r0.f5780g     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                int r5 = r0.f5780g     // Catch:{ all -> 0x00b4 }
                com.jumio.clientlib.impl.livenessAndTM.FrameProcessingSettingsTemplateMatcher r0 = new com.jumio.clientlib.impl.livenessAndTM.FrameProcessingSettingsTemplateMatcher     // Catch:{ all -> 0x00b4 }
                r2 = 1
                r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.e r2 = new jumio.nv.ocr.e     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r3 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                android.os.Handler r3 = r3.f5774a     // Catch:{ all -> 0x00b4 }
                r2.<init>(r3)     // Catch:{ all -> 0x00b4 }
                r1.f5785l = r2     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                com.jumio.clientlib.impl.livenessAndTM.FrameProcessorTemplateMatcher r2 = new com.jumio.clientlib.impl.livenessAndTM.FrameProcessorTemplateMatcher     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r3 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.e r3 = r3.f5785l     // Catch:{ all -> 0x00b4 }
                r2.<init>(r0, r3)     // Catch:{ all -> 0x00b4 }
                r1.f5784k = r2     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ Exception -> 0x013b }
                android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x013b }
                java.lang.Class<com.jumio.nv.models.SelectionModel> r1 = com.jumio.p311nv.models.SelectionModel.class
                java.io.Serializable r0 = com.jumio.persistence.DataAccess.load(r0, r1)     // Catch:{ Exception -> 0x013b }
                com.jumio.nv.models.SelectionModel r0 = (com.jumio.p311nv.models.SelectionModel) r0     // Catch:{ Exception -> 0x013b }
                if (r0 != 0) goto L_0x00c2
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ Exception -> 0x013b }
                com.jumio.sdk.exception.JumioException r1 = new com.jumio.sdk.exception.JumioException     // Catch:{ Exception -> 0x013b }
                com.jumio.nv.enums.NVErrorCase r2 = com.jumio.p311nv.enums.NVErrorCase.OCR_LOADING_FAILED     // Catch:{ Exception -> 0x013b }
                r1.<init>(r2)     // Catch:{ Exception -> 0x013b }
                r0.publishError(r1)     // Catch:{ Exception -> 0x013b }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ Exception -> 0x013b }
                r0.destroy()     // Catch:{ Exception -> 0x013b }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r1 = 1
                r0.f5788o = r1     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.f5777d     // Catch:{ all -> 0x00b4 }
                r1 = 0
                r0.set(r1)     // Catch:{ all -> 0x00b4 }
                java.lang.System.gc()     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                monitor-enter(r1)     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00bf }
                r2 = 0
                r0.f5783j = r2     // Catch:{ all -> 0x00bf }
                monitor-exit(r1)     // Catch:{ all -> 0x00bf }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r1 = 1
                r0.setDataExtractionActive(r1)     // Catch:{ all -> 0x00b4 }
                monitor-exit(r6)     // Catch:{ all -> 0x00b4 }
            L_0x00a6:
                return
            L_0x00a7:
                com.jumio.nv.benchmark.BenchmarkAlgorithm$DeviceCategory r2 = com.jumio.p311nv.benchmark.BenchmarkAlgorithm.DeviceCategory.MEDIUM     // Catch:{ all -> 0x00b4 }
                if (r0 != r2) goto L_0x00b7
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r2 = 10
                r0.f5780g = r2     // Catch:{ all -> 0x00b4 }
                goto L_0x0028
            L_0x00b4:
                r0 = move-exception
                monitor-exit(r6)     // Catch:{ all -> 0x00b4 }
                throw r0
            L_0x00b7:
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r2 = 7
                r0.f5780g = r2     // Catch:{ all -> 0x00b4 }
                goto L_0x0028
            L_0x00bf:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x00bf }
                throw r0     // Catch:{ all -> 0x00b4 }
            L_0x00c2:
                com.jumio.nv.data.country.Country r1 = r0.getSelectedCountry()     // Catch:{ Exception -> 0x013b }
                com.jumio.nv.models.TemplateModel r2 = new com.jumio.nv.models.TemplateModel     // Catch:{ Exception -> 0x013b }
                jumio.nv.ocr.b r3 = jumio.p317nv.ocr.C5147b.this     // Catch:{ Exception -> 0x013b }
                android.content.Context r3 = r3.mContext     // Catch:{ Exception -> 0x013b }
                r2.<init>(r3)     // Catch:{ Exception -> 0x013b }
                com.jumio.nv.data.document.DocumentType r3 = r0.getSelectedDoctype()     // Catch:{ Exception -> 0x013b }
                com.jumio.nv.data.document.NVDocumentType r3 = r3.getId()     // Catch:{ Exception -> 0x013b }
                com.jumio.nv.data.document.DocumentType r0 = r0.getSelectedDoctype()     // Catch:{ Exception -> 0x013b }
                com.jumio.core.data.document.ScanSide r0 = r0.getDocumentScanSide()     // Catch:{ Exception -> 0x013b }
                java.util.List r0 = r2.getTemplate(r1, r3, r0)     // Catch:{ Exception -> 0x013b }
                java.util.Iterator r2 = r0.iterator()     // Catch:{ Exception -> 0x013b }
            L_0x00e9:
                boolean r0 = r2.hasNext()     // Catch:{ Exception -> 0x013b }
                if (r0 == 0) goto L_0x01b4
                java.lang.Object r0 = r2.next()     // Catch:{ Exception -> 0x013b }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x013b }
                jumio.nv.ocr.b r3 = jumio.p317nv.ocr.C5147b.this     // Catch:{ Exception -> 0x013b }
                com.jumio.clientlib.impl.livenessAndTM.FrameProcessorTemplateMatcher r3 = r3.f5784k     // Catch:{ Exception -> 0x013b }
                boolean r3 = r3.addTemplatesFromBinaryFile(r0)     // Catch:{ Exception -> 0x013b }
                if (r3 != 0) goto L_0x016c
                jumio.nv.ocr.b r3 = jumio.p317nv.ocr.C5147b.this     // Catch:{ Exception -> 0x013b }
                com.jumio.sdk.exception.JumioException r4 = new com.jumio.sdk.exception.JumioException     // Catch:{ Exception -> 0x013b }
                com.jumio.nv.enums.NVErrorCase r5 = com.jumio.p311nv.enums.NVErrorCase.OCR_LOADING_FAILED     // Catch:{ Exception -> 0x013b }
                r4.<init>(r5)     // Catch:{ Exception -> 0x013b }
                r3.publishError(r4)     // Catch:{ Exception -> 0x013b }
                com.jumio.commons.utils.FileUtil.deleteFile(r0)     // Catch:{ Exception -> 0x013b }
                jumio.nv.ocr.b r3 = jumio.p317nv.ocr.C5147b.this     // Catch:{ Exception -> 0x013b }
                java.util.concurrent.ExecutorService r3 = r3.f5782i     // Catch:{ Exception -> 0x013b }
                r3.shutdownNow()     // Catch:{ Exception -> 0x013b }
                java.lang.String r3 = "TemplateMatcher"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013b }
                r4.<init>()     // Catch:{ Exception -> 0x013b }
                java.lang.String r5 = "Could not push file "
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x013b }
                java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ Exception -> 0x013b }
                java.lang.String r4 = " to template matcher"
                java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ Exception -> 0x013b }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x013b }
                com.jumio.commons.log.Log.m1929w(r3, r0)     // Catch:{ Exception -> 0x013b }
                goto L_0x00e9
            L_0x013b:
                r0 = move-exception
                java.lang.String r1 = "TemplateMatcherClient"
                java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x018f }
                com.jumio.commons.log.Log.m1929w(r1, r0)     // Catch:{ all -> 0x018f }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r1 = 1
                r0.f5788o = r1     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.f5777d     // Catch:{ all -> 0x00b4 }
                r1 = 0
                r0.set(r1)     // Catch:{ all -> 0x00b4 }
                java.lang.System.gc()     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                monitor-enter(r1)     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x01db }
                r2 = 0
                r0.f5783j = r2     // Catch:{ all -> 0x01db }
                monitor-exit(r1)     // Catch:{ all -> 0x01db }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r1 = 1
                r0.setDataExtractionActive(r1)     // Catch:{ all -> 0x00b4 }
            L_0x0169:
                monitor-exit(r6)     // Catch:{ all -> 0x00b4 }
                goto L_0x00a6
            L_0x016c:
                java.lang.String r0 = "TemplateMatcherClient"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013b }
                r3.<init>()     // Catch:{ Exception -> 0x013b }
                java.lang.String r4 = "pushed template "
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x013b }
                java.lang.StringBuilder r3 = r3.append(r1)     // Catch:{ Exception -> 0x013b }
                java.lang.String r4 = " to template matcher"
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x013b }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x013b }
                com.jumio.commons.log.Log.m1909d(r0, r3)     // Catch:{ Exception -> 0x013b }
                goto L_0x00e9
            L_0x018f:
                r0 = move-exception
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r2 = 1
                r1.f5788o = r2     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                java.util.concurrent.atomic.AtomicBoolean r1 = r1.f5777d     // Catch:{ all -> 0x00b4 }
                r2 = 0
                r1.set(r2)     // Catch:{ all -> 0x00b4 }
                java.lang.System.gc()     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                monitor-enter(r1)     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r2 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x01de }
                r3 = 0
                r2.f5783j = r3     // Catch:{ all -> 0x01de }
                monitor-exit(r1)     // Catch:{ all -> 0x01de }
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r2 = 1
                r1.setDataExtractionActive(r2)     // Catch:{ all -> 0x00b4 }
                throw r0     // Catch:{ all -> 0x00b4 }
            L_0x01b4:
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r1 = 1
                r0.f5788o = r1     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                java.util.concurrent.atomic.AtomicBoolean r0 = r0.f5777d     // Catch:{ all -> 0x00b4 }
                r1 = 0
                r0.set(r1)     // Catch:{ all -> 0x00b4 }
                java.lang.System.gc()     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r1 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                monitor-enter(r1)     // Catch:{ all -> 0x00b4 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x01d8 }
                r2 = 0
                r0.f5783j = r2     // Catch:{ all -> 0x01d8 }
                monitor-exit(r1)     // Catch:{ all -> 0x01d8 }
                jumio.nv.ocr.b r0 = jumio.p317nv.ocr.C5147b.this     // Catch:{ all -> 0x00b4 }
                r1 = 1
                r0.setDataExtractionActive(r1)     // Catch:{ all -> 0x00b4 }
                goto L_0x0169
            L_0x01d8:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x01d8 }
                throw r0     // Catch:{ all -> 0x00b4 }
            L_0x01db:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x01db }
                throw r0     // Catch:{ all -> 0x00b4 }
            L_0x01de:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x01de }
                throw r0     // Catch:{ all -> 0x00b4 }
            */
            throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.ocr.C5147b.C5149a.run():void");
        }
    }

    @SuppressLint({"HandlerLeak"})
    /* renamed from: jumio.nv.ocr.b$b */
    /* compiled from: TemplateMatcherClient */
    class C5150b extends Handler {
        private C5150b() {
        }

        public void handleMessage(Message message) {
            C5145a aVar = (C5145a) message.getData().getParcelable("templateInfo");
            byte[] byteArray = message.getData().getByteArray("finalResultFrame");
            if (aVar != null) {
                aVar.mo47267a(C5147b.this.getPreviewProperties(), C5147b.this.mIsTablet, C5147b.this.mIsPortrait, C5147b.this.mIsInverted);
                aVar.mo47265a(C5147b.this.f5780g);
                aVar.mo47268a(true);
            }
            switch (message.what) {
                case 101:
                    m3934a();
                    synchronized (C5147b.this) {
                        C5147b.this.f5783j = false;
                    }
                    return;
                case 102:
                    m3936a(byteArray, aVar);
                    synchronized (C5147b.this) {
                        C5147b.this.f5783j = false;
                    }
                    return;
                case 103:
                    m3938b(byteArray, aVar);
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        private void m3936a(byte[] bArr, C5145a aVar) {
            boolean z = false;
            if (aVar.mo47273d() || !aVar.mo47272c()) {
                z = true;
            }
            if (z) {
                m3935a(aVar.mo47281k());
            } else {
                C5147b.this.f5787n = C5147b.this.f5787n + 1;
            }
            C5147b.this.publishUpdate(new C5152c(C5153d.f5795a, aVar, (float) Math.round((((float) C5147b.this.f5787n) / ((float) (C5147b.this.f5780g - 1))) * 100.0f)));
            if (bArr != null && !z) {
                try {
                    System.gc();
                    Size size = new Size(-1, -1);
                    byte[] a = m3937a(bArr, aVar, false, size);
                    float f = 0.0f;
                    if (a != null) {
                        f = ImageQuality.calculateFocus(a, size.width, size.height, true);
                    }
                    if (f < 0.12f) {
                        Log.m1909d("TemplateMatcherClient", "request refocus - focus too low (" + f + ")");
                        C5147b.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.notifyFocus, new Point((int) aVar.mo47288r().x, (int) aVar.mo47288r().y)));
                    }
                    System.gc();
                } catch (OutOfMemoryError e) {
                    Log.m1930w("TemplateMatcher", "Intermediate result: focus check skipped - OOM!", (Throwable) e);
                    System.gc();
                } catch (Exception e2) {
                    Log.m1931w("TemplateMatcher handle IntermediateResult exception ", (Throwable) e2);
                }
            }
        }

        /* renamed from: a */
        private void m3934a() {
            synchronized (C5147b.this.f5775b) {
                C5147b.this.f5787n = 0;
            }
            C5147b.this.publishUpdate(new C5152c(C5153d.f5796b));
        }

        /* renamed from: b */
        private void m3938b(byte[] bArr, C5145a aVar) {
            C5147b.this.publishUpdate(new C5152c(C5153d.f5797c, aVar, 100.0f));
            synchronized (C5147b.this.f5775b) {
                Log.m1919i("TemplateMatcherClient", "Handler: final result, fIx = " + aVar.mo47281k());
                C5147b.this.f5787n = 0;
            }
            synchronized (C5147b.this) {
                C5147b.this.f5781h.interrupt();
            }
            if (!aVar.mo47272c() || aVar.mo47273d()) {
                Log.m1929w("TemplateMatcherClient", "discard final result - out of bounds or object too small");
                synchronized (C5147b.this) {
                    C5147b.this.f5783j = false;
                }
            } else {
                float w = ((float) aVar.mo47293w()) / ((float) aVar.mo47295x());
                int width = aVar.mo47286p().width();
                int i = (int) (((float) width) / w);
                byte[] a = C5155f.m3944a(bArr, C5147b.this.isPortrait(), aVar);
                float a2 = mo47305a(a, width, i, true);
                if (a2 >= 0.12f) {
                    C5147b.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.shotTaken, Float.valueOf(a2)));
                    Size size = new Size(-1, -1);
                    C5147b.this.mo47303a(C5155f.m3942a(bArr, 1.1f, size, C5147b.this.isPortrait(), aVar), size.width, size.height, a, width, i);
                    synchronized (C5147b.this) {
                        C5147b.this.f5783j = true;
                    }
                } else {
                    Log.m1929w("TemplateMatcherClient", "discard final result because focus < 0.12");
                    synchronized (C5147b.this) {
                        C5147b.this.f5783j = false;
                    }
                }
            }
            C5147b.this.f5784k.reset();
            C5147b.this.f5785l.mo47309a();
        }

        /* renamed from: a */
        public float mo47305a(byte[] bArr, int i, int i2, boolean z) {
            try {
                return ImageQualityAcquisition.Evaluate(bArr, ((long) i) * ((long) (z ? 3 : 1)), i, i2, z ? PixelFormatType.PIXEL_FORMAT_RGB_8 : PixelFormatType.PIXEL_FORMAT_YUV420_NV21);
            } catch (Exception e) {
                Log.printStackTrace(e);
                return -1.0f;
            }
        }

        /* renamed from: a */
        private void m3935a(int i) {
            try {
                if (C5147b.this.f5784k != null) {
                    C5147b.this.f5784k.discardFrameByID(i);
                }
            } catch (Exception e) {
                Log.printStackTrace(e);
            }
        }

        /* renamed from: a */
        private byte[] m3937a(byte[] bArr, C5145a aVar, boolean z, Size size) {
            int o;
            int i;
            int i2;
            int i3;
            Rect q = aVar.mo47287q();
            int i4 = q.left;
            int i5 = q.top;
            int height = q.height();
            int width = q.width();
            if (!z) {
                if (C5147b.this.isPortrait()) {
                    i = q.height();
                    o = q.width();
                    i2 = width;
                    i3 = i4;
                } else {
                    i = q.width();
                    o = q.height();
                    i2 = width;
                    i3 = i4;
                }
            } else if (C5147b.this.isPortrait()) {
                i = (int) aVar.mo47284n();
                int width2 = (int) (((float) q.width()) * 1.1f);
                int width3 = q.left - ((int) (((float) q.width()) * 0.05f));
                if (width3 < 0) {
                    width3 = 0;
                } else if (((float) (width2 + width3)) > aVar.mo47285o()) {
                    width2 = (int) (aVar.mo47285o() - ((float) width3));
                }
                o = width2;
                i2 = width2;
                height = i;
                i5 = 0;
                i3 = width3;
            } else {
                o = (int) aVar.mo47285o();
                int width4 = (int) (((float) q.width()) * 1.1f);
                int width5 = q.left - ((int) (((float) q.width()) * 0.05f));
                if (width5 < 0) {
                    width5 = 0;
                } else if (((float) (width4 + width5)) > aVar.mo47284n()) {
                    width4 = (int) (aVar.mo47284n() - ((float) width5));
                }
                i = width4;
                i2 = width4;
                height = o;
                i5 = 0;
                i3 = width5;
            }
            if (size != null) {
                size.width = i;
                size.height = o;
            }
            return CameraUtils.yuv2rgb(bArr, C5147b.this.mIsPortrait, C5147b.this.getPreviewProperties(), i2, height, i3, i5, i, o);
        }
    }

    /* renamed from: jumio.nv.ocr.b$c */
    /* compiled from: TemplateMatcherClient */
    class C5151c extends Thread {
        public C5151c(byte[] bArr) {
            setName("TM Task");
            try {
                C5147b.this.f5789p = new byte[bArr.length];
                if (C5147b.this.f5789p == null || C5147b.this.f5789p.length != bArr.length) {
                    throw new IllegalStateException("dataBuffer may not be null and must match data in size!");
                }
                System.arraycopy(bArr, 0, C5147b.this.f5789p, 0, bArr.length);
            } catch (Exception e) {
                C5147b.this.cancel();
                Log.m1915e("TemplateMatcherClient", "TemplateMatcherTask#ctor()", (Throwable) e);
            }
        }

        public void run() {
            int i;
            int i2;
            if (C5147b.this.isPortrait()) {
                i = C5147b.this.getPreviewProperties().preview.height;
                i2 = C5147b.this.getPreviewProperties().preview.width;
            } else {
                i = C5147b.this.getPreviewProperties().preview.width;
                i2 = C5147b.this.getPreviewProperties().preview.height;
            }
            C5147b.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.notifyFlash, Boolean.valueOf(ImageQuality.isFlashNeeded(C5147b.this.f5789p, i, i2, false))));
            try {
                C5147b.this.f5784k.pushFrame(new LibImage(C5147b.this.f5789p, com.jumio.clientlib.impl.livenessAndTM.PixelFormatType.PIXEL_FORMAT_YUV420_NV21, i, i2, (long) i, 1, 1, C5147b.this.f5786m = C5147b.this.f5786m + 1, 0));
            } catch (Exception e) {
                Log.m1915e("TemplateMatcherClient", "Error pushing Frame to TemplateMatcherEngine: ", (Throwable) e);
            }
        }
    }

    public C5147b(Context context) {
        super(context);
        NVOcrEnvironment.loadLivenessDetectorAndTemplateMatcherLib();
        this.f5779f = (int) System.currentTimeMillis();
        this.f5782i = Executors.newSingleThreadExecutor();
        this.f5774a = new C5150b();
    }

    public void configure(StaticModel staticModel) {
    }

    /* access modifiers changed from: protected */
    public void init() {
        if (this.f5777d.compareAndSet(false, true)) {
            Log.m1924v("TemplateMatcherClient", "init: cancel()");
            cancel();
            Log.m1924v("TemplateMatcherClient", "init: entering lifecyclelock()");
            synchronized (this.f5776c) {
                Log.m1909d("Lifecycle", "init: delete old vars");
                if (this.f5784k != null) {
                    this.f5784k.delete();
                }
                if (this.f5782i == null) {
                    this.f5782i = Executors.newFixedThreadPool(1);
                }
                synchronized (this) {
                    this.f5788o = false;
                    Log.m1909d("TemplateMatcherClient", "init: start loading task");
                    this.f5782i.submit(new C5149a());
                }
            }
            return;
        }
        Log.m1924v("TemplateMatcherClient", "initialization not started due to a running initialization");
    }

    public void destroy() {
        cancel();
        this.f5777d.set(false);
        this.f5788o = false;
    }

    /* renamed from: a */
    public void feed(byte[] bArr) {
        synchronized (this) {
            if (m3902a() && !this.f5783j) {
                this.f5783j = true;
                this.f5781h = new C5151c(bArr);
                this.f5781h.start();
            }
        }
    }

    /* renamed from: a */
    private boolean m3902a() {
        boolean z;
        synchronized (this.f5778e) {
            z = this.f5788o && !this.f5777d.get();
        }
        return z;
    }

    /* renamed from: a */
    public void mo47303a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        if (bArr2 != null) {
            Bitmap rgb2bitmap = CameraUtils.rgb2bitmap(bArr2, i3, i4);
            publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveImage, CameraUtils.rgb2bitmap(bArr, i, i2)));
            publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveExactImage, rgb2bitmap));
        }
        System.gc();
        publishResult(null);
    }
}
