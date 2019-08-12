package jumio.p317nv.mrz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.commons.log.Log;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.mrz.impl.smartEngines.swig.MrzDate;
import com.jumio.mrz.impl.smartEngines.swig.MrzEngine;
import com.jumio.mrz.impl.smartEngines.swig.MrzEngineInternalSettings;
import com.jumio.mrz.impl.smartEngines.swig.MrzEngineSessionHelpers;
import com.jumio.mrz.impl.smartEngines.swig.MrzEngineSessionSettings;
import com.jumio.mrz.impl.smartEngines.swig.MrzField;
import com.jumio.mrz.impl.smartEngines.swig.MrzRectMatrix;
import com.jumio.mrz.impl.smartEngines.swig.MrzRectVector;
import com.jumio.mrz.impl.smartEngines.swig.MrzResult;
import com.jumio.mrz.impl.smartEngines.swig.StreamReporterInterface;
import com.jumio.mrz.impl.smartEngines.swig.StringVector;
import com.jumio.p311nv.data.document.NVMRZFormat;
import com.jumio.p311nv.enums.NVErrorCase;
import com.jumio.p311nv.enums.NVGender;
import com.jumio.p311nv.extraction.JumioRect;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.p311nv.mrz.environment.MrzEnvironment;
import com.jumio.sdk.exception.JumioErrorCase;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: jumio.nv.mrz.a */
/* compiled from: MrzClient */
public class C4956a extends ExtractionClient<ExtractionUpdate, DocumentDataModel> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public MrzEngine f4899a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public MrzEngineSessionHelpers f4900b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public MrzEngineSessionSettings f4901c;

    /* renamed from: d */
    private C4961d f4902d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C4958a f4903e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public ExecutorService f4904f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public DocumentScanMode f4905g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public boolean f4906h = false;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public int f4907i = 0;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f4908j = 0;

    /* renamed from: jumio.nv.mrz.a$a */
    /* compiled from: MrzClient */
    class C4958a extends StreamReporterInterface {

        /* renamed from: a */
        public boolean f4910a;

        /* renamed from: b */
        public MrzResult f4911b;

        /* renamed from: c */
        public MrzRectVector[] f4912c;

        private C4958a() {
            this.f4910a = false;
        }

        /* renamed from: a */
        public void mo46921a() {
            this.f4910a = false;
            this.f4911b = null;
            this.f4912c = new MrzRectVector[0];
        }

        public void SnapshotProcessed(MrzResult mrzResult, boolean z) {
            this.f4910a = z;
            if (z) {
                this.f4911b = new MrzResult(mrzResult);
            }
        }

        public void SnapshotRejected() {
            this.f4912c = new MrzRectVector[0];
        }

        public void SymbolRectsFound(MrzRectMatrix mrzRectMatrix) {
            this.f4912c = new MrzRectVector[((int) mrzRectMatrix.size())];
            for (int i = 0; ((long) i) < mrzRectMatrix.size(); i++) {
                this.f4912c[i] = new MrzRectVector(mrzRectMatrix.get(i).size());
                for (int i2 = 0; ((long) i2) < mrzRectMatrix.get(i).size(); i2++) {
                    this.f4912c[i].set(i2, mrzRectMatrix.get(i).get(i2));
                }
            }
        }
    }

    /* renamed from: jumio.nv.mrz.a$b */
    /* compiled from: MrzClient */
    class C4959b implements Runnable {
        private C4959b() {
        }

        public void run() {
            C4956a.this.f4903e.mo46921a();
            if (C4956a.this.f4899a != null) {
                try {
                    C4956a.this.f4899a.InitializeSession(C4956a.this.f4903e, C4956a.this.f4900b, C4956a.this.f4901c);
                } catch (Exception e) {
                    Log.m1915e("MrzClient", "MrzInitTask", (Throwable) e);
                    C4956a.this.publishError(new JumioException((JumioErrorCase) NVErrorCase.OCR_LOADING_FAILED));
                    C4956a.this.f4904f.shutdownNow();
                }
            }
            C4965d dVar = new C4965d(C4956a.this.mContext);
            dVar.calculate(C4956a.this.f4905g, C4956a.this.getPreviewProperties().surface.width, C4956a.this.getPreviewProperties().surface.height);
            Rect overlayBounds = dVar.getOverlayBounds();
            Rect a = dVar.mo46933a();
            C4956a.this.f4907i = overlayBounds.left;
            int i = (int) (((double) (C4956a.this.getPreviewProperties().surface.width - (C4956a.this.f4907i * 2))) / C4956a.this.f4900b.get_optimal_aspect_ratio());
            C4956a.this.f4908j = a.top - ((i - (a.bottom - a.top)) / 2);
            if (C4956a.this.f4908j + i > C4956a.this.getPreviewProperties().surface.height) {
                C4956a.this.f4908j = C4956a.this.getPreviewProperties().surface.height - i;
            }
            synchronized (C4956a.this) {
                C4956a.this.f4906h = false;
            }
        }
    }

    /* renamed from: jumio.nv.mrz.a$c */
    /* compiled from: MrzClient */
    class C4960c implements Runnable {
        private C4960c() {
        }

        public void run() {
            try {
                String mRZEngineSettingsPath = MrzEnvironment.getMRZEngineSettingsPath(C4956a.this.mContext);
                if (mRZEngineSettingsPath == null) {
                    throw new Exception("Loading mrz settings failed!");
                }
                C4956a.this.f4899a = new MrzEngine(MrzEngineInternalSettings.createFromFilesystem(mRZEngineSettingsPath));
            } catch (Exception e) {
                Log.m1915e("MrzClient", "MrzLoadingTask", (Throwable) e);
                C4956a.this.publishError(new JumioException((JumioErrorCase) NVErrorCase.OCR_LOADING_FAILED));
                C4956a.this.f4904f.shutdownNow();
            }
        }
    }

    /* renamed from: jumio.nv.mrz.a$d */
    /* compiled from: MrzClient */
    class C4961d extends Thread {

        /* renamed from: b */
        private byte[] f4917b;

        /* renamed from: c */
        private PreviewProperties f4918c;

        /* renamed from: d */
        private boolean f4919d;

        public C4961d(byte[] bArr, boolean z) {
            this.f4917b = Arrays.copyOf(bArr, bArr.length);
            this.f4919d = z;
            this.f4918c = C4956a.this.getPreviewProperties().copy();
        }

        public void run() {
            try {
                m3180a(this.f4917b);
            } catch (Exception e) {
                Log.printStackTrace(e);
            }
            synchronized (C4956a.this) {
                C4956a.this.f4906h = C4956a.this.f4903e.f4910a;
                this.f4917b = null;
                this.f4918c = null;
                System.gc();
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:13:0x00b6  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x00fa  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0132  */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x014c  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void m3180a(byte[] r22) throws java.lang.Exception {
            /*
                r21 = this;
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r4 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r4 = r4.preview
                int r4 = r4.width
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r5 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r5 = r5.preview
                int r5 = r5.height
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                com.jumio.mrz.impl.smartEngines.swig.MrzEngineSessionHelpers r6 = r6.f4900b
                double r6 = r6.get_optimal_aspect_ratio()
                r0 = r21
                jumio.nv.mrz.a r8 = jumio.p317nv.mrz.C4956a.this
                int r8 = r8.f4907i
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r9 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r9 = r9.scaledPreview
                int r9 = r9.width
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r10 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r10 = r10.surface
                int r10 = r10.width
                int r9 = r9 - r10
                int r9 = r9 / 2
                int r8 = r8 + r9
                int r8 = r8 * r4
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r9 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r9 = r9.scaledPreview
                int r9 = r9.width
                int r12 = r8 / r9
                int r8 = r12 * 2
                int r13 = r4 - r8
                double r8 = (double) r13
                double r6 = r8 / r6
                int r14 = (int) r6
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                int r6 = r6.f4908j
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r7 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r7 = r7.scaledPreview
                int r7 = r7.height
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r8 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r8 = r8.surface
                int r8 = r8.height
                int r7 = r7 - r8
                int r7 = r7 / 2
                int r6 = r6 + r7
                int r6 = r6 * r5
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r7 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r7 = r7.scaledPreview
                int r7 = r7.height
                int r15 = r6 / r7
                com.jumio.mrz.impl.smartEngines.swig.MrzRect r10 = new com.jumio.mrz.impl.smartEngines.swig.MrzRect
                r10.<init>(r12, r15, r13, r14)
                r0 = r21
                boolean r6 = r0.f4919d
                if (r6 == 0) goto L_0x0109
                r7 = r4
                r6 = r5
            L_0x007f:
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r8 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.Landscape
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r9 = r0.f4918c
                int r9 = r9.orientation
                r11 = 90
                if (r9 != r11) goto L_0x010d
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r8 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.Portrait
            L_0x008d:
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r9 = r0.f4918c
                boolean r9 = r9.frontFacing
                if (r9 == 0) goto L_0x074c
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r9 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.Portrait
                if (r8 != r9) goto L_0x0129
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r8 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.InvertedPortrait
                r11 = r8
            L_0x009c:
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r8 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r8 = r8.surface
                int r8 = r8.height
                float r8 = (float) r8
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r9 = r0.f4918c
                com.jumio.commons.camera.CameraManager$Size r9 = r9.surface
                int r9 = r9.width
                float r9 = (float) r9
                float r9 = r8 / r9
                r0 = r21
                boolean r8 = r0.f4919d
                if (r8 == 0) goto L_0x0132
                r8 = 1065353216(0x3f800000, float:1.0)
            L_0x00b8:
                r0 = r21
                boolean r0 = r0.f4919d
                r16 = r0
                if (r16 == 0) goto L_0x0135
                int r16 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
                if (r16 >= 0) goto L_0x0135
                r8 = r9
            L_0x00c5:
                com.jumio.commons.camera.CameraManager$Size r16 = new com.jumio.commons.camera.CameraManager$Size
                r9 = -1
                r17 = -1
                r0 = r16
                r1 = r17
                r0.<init>(r9, r1)
                r0 = r21
                boolean r9 = r0.f4919d
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r0 = r0.f4918c
                r17 = r0
                r0 = r22
                r1 = r17
                r2 = r16
                byte[] r17 = com.jumio.commons.camera.CameraUtils.yuv2rgb(r0, r9, r1, r8, r2)
                r0 = r16
                int r8 = r0.width
                int r4 = r4 - r8
                int r4 = r4 / 2
                int r18 = r12 - r4
                r0 = r16
                int r4 = r0.height
                int r4 = r5 - r4
                int r4 = r4 / 2
                int r19 = r15 - r4
                if (r17 != 0) goto L_0x014c
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                monitor-enter(r5)
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this     // Catch:{ all -> 0x0149 }
                r6 = 0
                r4.f4906h = r6     // Catch:{ all -> 0x0149 }
                monitor-exit(r5)     // Catch:{ all -> 0x0149 }
            L_0x0108:
                return
            L_0x0109:
                r7 = r5
                r6 = r4
                goto L_0x007f
            L_0x010d:
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r9 = r0.f4918c
                int r9 = r9.orientation
                r11 = 180(0xb4, float:2.52E-43)
                if (r9 != r11) goto L_0x011b
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r8 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.InvertedLandscape
                goto L_0x008d
            L_0x011b:
                r0 = r21
                com.jumio.commons.camera.CameraManager$PreviewProperties r9 = r0.f4918c
                int r9 = r9.orientation
                r11 = 270(0x10e, float:3.78E-43)
                if (r9 != r11) goto L_0x008d
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r8 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.InvertedPortrait
                goto L_0x008d
            L_0x0129:
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r9 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.InvertedPortrait
                if (r8 != r9) goto L_0x074c
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r8 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.Portrait
                r11 = r8
                goto L_0x009c
            L_0x0132:
                r8 = 1061158912(0x3f400000, float:0.75)
                goto L_0x00b8
            L_0x0135:
                r0 = r21
                boolean r0 = r0.f4919d
                r16 = r0
                if (r16 != 0) goto L_0x00c5
                int r16 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
                if (r16 <= 0) goto L_0x00c5
                r8 = 1065353216(0x3f800000, float:1.0)
                float r8 = java.lang.Math.min(r8, r9)
                goto L_0x00c5
            L_0x0149:
                r4 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0149 }
                throw r4
            L_0x014c:
                r0 = r16
                int r4 = r0.width
                r0 = r16
                int r5 = r0.height
                r8 = 1
                r0 = r17
                float r20 = com.jumio.core.ImageQuality.calculateFocus(r0, r4, r5, r8)
                r0 = r16
                int r4 = r0.width
                r0 = r16
                int r5 = r0.height
                r8 = 1
                r0 = r17
                boolean r4 = com.jumio.core.ImageQuality.isFlashNeeded(r0, r4, r5, r8)
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                com.jumio.sdk.extraction.ExtractionClient$ExtractionUpdate r8 = new com.jumio.sdk.extraction.ExtractionClient$ExtractionUpdate
                int r9 = com.jumio.sdk.extraction.ExtractionUpdateState.notifyFlash
                java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
                r8.<init>(r9, r4)
                r5.publishUpdate(r8)
                r4 = 1039516303(0x3df5c28f, float:0.12)
                int r4 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
                if (r4 > 0) goto L_0x01b2
                r4 = 1
            L_0x0184:
                if (r4 == 0) goto L_0x01b4
                int r4 = r13 / 2
                int r4 = r4 + r12
                int r5 = r14 / 2
                int r5 = r5 + r15
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                com.jumio.sdk.extraction.ExtractionClient$ExtractionUpdate r7 = new com.jumio.sdk.extraction.ExtractionClient$ExtractionUpdate
                int r8 = com.jumio.sdk.extraction.ExtractionUpdateState.notifyFocus
                android.graphics.Point r9 = new android.graphics.Point
                r9.<init>(r4, r5)
                r7.<init>(r8, r9)
                r6.publishUpdate(r7)
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                monitor-enter(r5)
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this     // Catch:{ all -> 0x01af }
                r6 = 0
                r4.f4906h = r6     // Catch:{ all -> 0x01af }
                monitor-exit(r5)     // Catch:{ all -> 0x01af }
                goto L_0x0108
            L_0x01af:
                r4 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x01af }
                throw r4
            L_0x01b2:
                r4 = 0
                goto L_0x0184
            L_0x01b4:
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine r4 = r4.f4899a
                r9 = 1
                r5 = r22
                r8 = r6
                r4.FeedUncompressedImageData(r5, r6, r7, r8, r9, r10, r11)
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r4 = r4.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzRectVector[] r4 = r4.f4912c
                r0 = r21
                r1 = r18
                r2 = r19
                java.util.ArrayList r10 = r0.m3179a(r4, r1, r2)
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r4 = r4.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzRectVector[] r4 = r4.f4912c
                if (r4 == 0) goto L_0x025d
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r4 = r4.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzRectVector[] r4 = r4.f4912c
                int r4 = r4.length
                if (r4 == 0) goto L_0x025d
                int r4 = r13 * r14
                byte[] r8 = new byte[r4]
                r4 = 0
                r5 = r4
            L_0x01f6:
                if (r5 >= r14) goto L_0x025d
                r4 = 0
            L_0x01f9:
                if (r4 >= r13) goto L_0x0259
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r9 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.Landscape
                if (r11 != r9) goto L_0x0211
                int r9 = r5 * r13
                int r9 = r9 + r4
                int r18 = r12 + r4
                int r19 = r5 + r15
                int r19 = r19 * r6
                int r18 = r18 + r19
                byte r18 = r22[r18]
                r8[r9] = r18
            L_0x020e:
                int r4 = r4 + 1
                goto L_0x01f9
            L_0x0211:
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r9 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.InvertedLandscape
                if (r11 != r9) goto L_0x022b
                int r9 = r5 * r13
                int r9 = r9 + r4
                int r18 = r7 - r15
                int r18 = r18 + -1
                int r18 = r18 - r5
                int r18 = r18 * r6
                int r18 = r18 + r12
                int r18 = r18 + r13
                int r18 = r18 - r4
                byte r18 = r22[r18]
                r8[r9] = r18
                goto L_0x020e
            L_0x022b:
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r9 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.Portrait
                if (r11 != r9) goto L_0x0243
                int r9 = r5 * r13
                int r9 = r9 + r4
                int r18 = r12 + r13
                int r18 = r18 + -1
                int r18 = r18 - r4
                int r18 = r18 * r6
                int r18 = r18 + r15
                int r18 = r18 + r5
                byte r18 = r22[r18]
                r8[r9] = r18
                goto L_0x020e
            L_0x0243:
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r9 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.InvertedPortrait
                if (r11 != r9) goto L_0x020e
                int r9 = r5 * r13
                int r9 = r9 + r4
                int r18 = r12 + r4
                int r18 = r18 * r6
                int r18 = r18 + r6
                int r18 = r18 - r15
                int r18 = r18 - r5
                byte r18 = r22[r18]
                r8[r9] = r18
                goto L_0x020e
            L_0x0259:
                int r4 = r5 + 1
                r5 = r4
                goto L_0x01f6
            L_0x025d:
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r4 = r4.f4903e
                boolean r4 = r4.f4910a
                if (r4 == 0) goto L_0x070d
                java.lang.Class<jumio.nv.mrz.a> r4 = jumio.p317nv.mrz.C4956a.class
                java.lang.String r18 = r4.getSimpleName()
                com.jumio.commons.log.Log$LogLevel r4 = com.jumio.commons.log.Log.LogLevel.INFO
                boolean r4 = com.jumio.commons.log.Log.isLogEnabledForLevel(r4)
                if (r4 == 0) goto L_0x06f2
                java.lang.StringBuilder r19 = new java.lang.StringBuilder
                r19.<init>()
                java.util.Calendar r4 = java.util.Calendar.getInstance()
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r8 = "width = "
                java.lang.StringBuilder r5 = r5.append(r8)
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r8 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r8)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r8 = "height = "
                java.lang.StringBuilder r5 = r5.append(r8)
                java.lang.StringBuilder r5 = r5.append(r7)
                java.lang.String r7 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r7)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r7 = "stride = "
                java.lang.StringBuilder r5 = r5.append(r7)
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "roi_x = "
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.StringBuilder r5 = r5.append(r12)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "roi_y = "
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.StringBuilder r5 = r5.append(r15)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "roiWidth = "
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.StringBuilder r5 = r5.append(r13)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "roiHeight = "
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.StringBuilder r5 = r5.append(r14)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.String r5 = "roi = new MrzRect(roi_x, roi_y, roiWidth, roiHeight)\r\n"
                r0 = r19
                r0.append(r5)
                java.lang.String r5 = "Portrait"
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r6 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.InvertedPortrait
                if (r11 != r6) goto L_0x03f7
                java.lang.String r5 = "InvertedPortrait"
            L_0x0372:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r7 = "orientation = "
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.StringBuilder r5 = r6.append(r5)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.String r5 = "FeedUncompressedImageData(buffer, width, height, stride, 1, roi, orientation)"
                r0 = r19
                r0.append(r5)
                java.lang.String r5 = "\r\n"
                r0 = r19
                java.lang.StringBuilder r5 = r0.append(r5)
                java.lang.String r6 = "\r\n"
                r5.append(r6)
                java.lang.String r5 = "Result:"
                r0 = r19
                java.lang.StringBuilder r5 = r0.append(r5)
                java.lang.String r6 = "\r\n"
                r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r5 = r5.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r5 = r5.f4911b
                com.jumio.mrz.impl.smartEngines.swig.StringVector r6 = r5.getMrzLines()
                r5 = 0
            L_0x03c0:
                long r8 = (long) r5
                long r12 = r6.size()
                int r7 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r7 >= 0) goto L_0x0409
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r8 = "getMrzLines().get(%d) "
                r9 = 1
                java.lang.Object[] r9 = new java.lang.Object[r9]
                r11 = 0
                java.lang.Integer r12 = java.lang.Integer.valueOf(r5)
                r9[r11] = r12
                java.lang.String r8 = java.lang.String.format(r8, r9)
                java.lang.StringBuilder r7 = r7.append(r8)
                java.lang.String r8 = r6.get(r5)
                java.lang.StringBuilder r7 = r7.append(r8)
                java.lang.String r7 = r7.toString()
                r0 = r19
                r0.append(r7)
                int r5 = r5 + 1
                goto L_0x03c0
            L_0x03f7:
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r6 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.Landscape
                if (r11 != r6) goto L_0x0400
                java.lang.String r5 = "Landscape"
                goto L_0x0372
            L_0x0400:
                com.jumio.mrz.impl.smartEngines.swig.MrzEngine$ImageOrientation r6 = com.jumio.mrz.impl.smartEngines.swig.MrzEngine.ImageOrientation.InvertedLandscape
                if (r11 != r6) goto L_0x0372
                java.lang.String r5 = "InvertedLandscape"
                goto L_0x0372
            L_0x0409:
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getDocType() "
                java.lang.StringBuilder r5 = r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r6 = r6.getDocType()
                java.lang.String r6 = r6.getAsString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getDocTypeCode() "
                java.lang.StringBuilder r5 = r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r6 = r6.getDocTypeCode()
                java.lang.String r6 = r6.getAsString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getDocNum() "
                java.lang.StringBuilder r5 = r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r6 = r6.getDocNum()
                java.lang.String r6 = r6.getAsString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getOptData1() "
                java.lang.StringBuilder r5 = r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r6 = r6.getOptData1()
                java.lang.String r6 = r6.getAsString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getOptData2() "
                java.lang.StringBuilder r5 = r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r6 = r6.getOptData2()
                java.lang.String r6 = r6.getAsString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r5 = r5.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r5 = r5.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzDateField r5 = r5.getExpidate()
                com.jumio.mrz.impl.smartEngines.swig.MrzDate r5 = r5.getAsMrzDate()
                int r5 = r5.getYear()
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzDateField r6 = r6.getExpidate()
                com.jumio.mrz.impl.smartEngines.swig.MrzDate r6 = r6.getAsMrzDate()
                int r6 = r6.getMonth()
                int r5 = r5 + r6
                int r5 = r5 + -1
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzDateField r6 = r6.getExpidate()
                com.jumio.mrz.impl.smartEngines.swig.MrzDate r6 = r6.getAsMrzDate()
                int r6 = r6.getDay()
                r7 = 0
                r8 = 0
                r9 = 0
                r4.set(r5, r6, r7, r8, r9)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getExpidate() "
                java.lang.StringBuilder r5 = r5.append(r6)
                java.util.Date r6 = r4.getTime()
                java.lang.String r6 = r6.toString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getCountry() "
                java.lang.StringBuilder r5 = r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r6 = r6.getCountry()
                java.lang.String r6 = r6.getAsString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getSecondName() "
                java.lang.StringBuilder r5 = r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r6 = r6.getSecondName()
                java.lang.String r6 = r6.getAsString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getFirstName() "
                java.lang.StringBuilder r5 = r5.append(r6)
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r6 = r6.getFirstName()
                java.lang.String r6 = r6.getAsString()
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r6 = "\r\n"
                java.lang.StringBuilder r5 = r5.append(r6)
                java.lang.String r5 = r5.toString()
                r0 = r19
                r0.append(r5)
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r5 = r5.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r5 = r5.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzDateField r5 = r5.getBirthdate()
                com.jumio.mrz.impl.smartEngines.swig.MrzDate r5 = r5.getAsMrzDate()
                int r5 = r5.getYear()
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzDateField r6 = r6.getBirthdate()
                com.jumio.mrz.impl.smartEngines.swig.MrzDate r6 = r6.getAsMrzDate()
                int r6 = r6.getMonth()
                int r5 = r5 + r6
                int r5 = r5 + -1
                r0 = r21
                jumio.nv.mrz.a r6 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r6 = r6.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r6 = r6.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzDateField r6 = r6.getBirthdate()
                com.jumio.mrz.impl.smartEngines.swig.MrzDate r6 = r6.getAsMrzDate()
                int r6 = r6.getDay()
                r7 = 0
                r8 = 0
                r9 = 0
                r4.set(r5, r6, r7, r8, r9)
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "getBirthdate() "
                java.lang.StringBuilder r5 = r5.append(r6)
                java.util.Date r4 = r4.getTime()
                java.lang.String r4 = r4.toString()
                java.lang.StringBuilder r4 = r5.append(r4)
                java.lang.String r5 = "\r\n"
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r4 = r4.toString()
                r0 = r19
                r0.append(r4)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "getSex() "
                java.lang.StringBuilder r4 = r4.append(r5)
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r5 = r5.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r5 = r5.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r5 = r5.getSex()
                java.lang.String r5 = r5.getAsString()
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r5 = "\r\n"
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r4 = r4.toString()
                r0 = r19
                r0.append(r4)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "getNationality() "
                java.lang.StringBuilder r4 = r4.append(r5)
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r5 = r5.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r5 = r5.f4911b
                com.jumio.mrz.impl.smartEngines.swig.MrzField r5 = r5.getNationality()
                java.lang.String r5 = r5.getAsString()
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r5 = "\r\n"
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r4 = r4.toString()
                r0 = r19
                r0.append(r4)
                r0 = r19
                com.jumio.p311nv.utils.NetverifyLogUtils.appendCoordinatesLog(r0, r10)
                java.lang.String r4 = r19.toString()
                r5 = 0
                r0 = r18
                com.jumio.p311nv.utils.NetverifyLogUtils.logInfoInSubfolder(r4, r0, r5)
            L_0x06f2:
                com.jumio.commons.log.Log$LogLevel r4 = com.jumio.commons.log.Log.LogLevel.VERBOSE
                boolean r4 = com.jumio.commons.log.Log.isLogEnabledForLevel(r4)
                if (r4 == 0) goto L_0x070d
                r0 = r16
                int r4 = r0.width
                r0 = r16
                int r5 = r0.height
                r0 = r17
                android.graphics.Bitmap r4 = com.jumio.commons.utils.ImageUtil.rgbToBitmap(r0, r4, r5)
                r0 = r18
                com.jumio.p311nv.utils.NetverifyLogUtils.dumpPictureWithCoordinates(r10, r4, r0)
            L_0x070d:
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r4 = r4.f4903e
                boolean r4 = r4.f4910a
                if (r4 == 0) goto L_0x0739
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r5 = r5.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzResult r5 = r5.f4911b
                r0 = r16
                int r8 = r0.width
                r0 = r16
                int r9 = r0.height
                r6 = r10
                r7 = r17
                r10 = r20
                r4.mo46918a(r5, r6, r7, r8, r9, r10)
                goto L_0x0108
            L_0x0739:
                r0 = r21
                jumio.nv.mrz.a r4 = jumio.p317nv.mrz.C4956a.this
                r0 = r21
                jumio.nv.mrz.a r5 = jumio.p317nv.mrz.C4956a.this
                jumio.nv.mrz.a$a r5 = r5.f4903e
                com.jumio.mrz.impl.smartEngines.swig.MrzRectVector[] r5 = r5.f4912c
                r4.mo46920a(r5)
                goto L_0x0108
            L_0x074c:
                r11 = r8
                goto L_0x009c
            */
            throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.mrz.C4956a.C4961d.m3180a(byte[]):void");
        }

        /* renamed from: a */
        private ArrayList<ArrayList<JumioRect>> m3179a(MrzRectVector[] mrzRectVectorArr, int i, int i2) {
            if (mrzRectVectorArr == null || mrzRectVectorArr.length == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (MrzRectVector mrzRectVector : mrzRectVectorArr) {
                ArrayList arrayList2 = new ArrayList();
                for (int i3 = 0; ((long) i3) < mrzRectVector.size(); i3++) {
                    int x = mrzRectVector.get(i3).getX() + i;
                    int y = mrzRectVector.get(i3).getY() + i2;
                    arrayList2.add(new JumioRect(x, y, mrzRectVector.get(i3).getWidth() + x, mrzRectVector.get(i3).getHeight() + y));
                }
                arrayList.add(arrayList2);
            }
            return arrayList;
        }
    }

    public C4956a(Context context) {
        super(context);
        MrzEnvironment.loadMRZJniInterfaceLib();
        this.f4904f = Executors.newSingleThreadExecutor();
        this.f4904f.submit(new C4960c());
        this.f4901c = new MrzEngineSessionSettings();
        this.f4901c.set_should_postprocess(true);
        this.f4901c.set_m3z_support_enabled(false);
        this.f4900b = new MrzEngineSessionHelpers();
    }

    public void configure(StaticModel staticModel) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        if (!(staticModel instanceof NVScanPartModel)) {
            throw new InvalidParameterException("Configuration model should be an instance of ScanPartModel");
        }
        this.f4905g = ((NVScanPartModel) staticModel).getScanMode();
        this.f4901c.set_mrp_support_enabled(this.f4905g == DocumentScanMode.MRP);
        MrzEngineSessionSettings mrzEngineSessionSettings = this.f4901c;
        if (this.f4905g == DocumentScanMode.TD1) {
            z = true;
        } else {
            z = false;
        }
        mrzEngineSessionSettings.set_td1_support_enabled(z);
        MrzEngineSessionSettings mrzEngineSessionSettings2 = this.f4901c;
        if (this.f4905g == DocumentScanMode.TD2 || this.f4905g == DocumentScanMode.CNIS) {
            z2 = true;
        } else {
            z2 = false;
        }
        mrzEngineSessionSettings2.set_td2_support_enabled(z2);
        MrzEngineSessionSettings mrzEngineSessionSettings3 = this.f4901c;
        if (this.f4905g == DocumentScanMode.CNIS) {
            z3 = true;
        } else {
            z3 = false;
        }
        mrzEngineSessionSettings3.set_cnis_support_enabled(z3);
        MrzEngineSessionSettings mrzEngineSessionSettings4 = this.f4901c;
        if (this.f4905g == DocumentScanMode.MRV) {
            z4 = true;
        } else {
            z4 = false;
        }
        mrzEngineSessionSettings4.set_mrva_support_enabled(z4);
        MrzEngineSessionSettings mrzEngineSessionSettings5 = this.f4901c;
        if (this.f4905g != DocumentScanMode.MRV) {
            z5 = false;
        }
        mrzEngineSessionSettings5.set_mrvb_support_enabled(z5);
        this.f4903e = new C4958a();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.f4904f.submit(new C4959b());
    }

    public void destroy() {
        cancel();
    }

    /* renamed from: a */
    public void feed(byte[] bArr) {
        if (!this.f4906h && this.f4899a != null) {
            this.f4906h = true;
            this.f4902d = new C4961d(bArr, isPortrait());
            this.f4904f.submit(this.f4902d);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo46918a(MrzResult mrzResult, ArrayList<ArrayList<JumioRect>> arrayList, byte[] bArr, int i, int i2, float f) {
        publishUpdate(new ExtractionUpdate(ExtractionUpdateState.shotTaken, Float.valueOf(f)));
        C4963c cVar = new C4963c();
        Bitmap rgb2bitmap = CameraUtils.rgb2bitmap(bArr, i, i2);
        publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveImage, rgb2bitmap));
        publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveExactImage, rgb2bitmap));
        StringVector mrzLines = mrzResult.getMrzLines();
        mrzResult.getDocType();
        mrzResult.getDocTypeCode();
        cVar.setIdNumber(m3157a(mrzResult.getDocNum()));
        switch (this.f4905g) {
            case MRV:
                m3159a(mrzResult, (DocumentDataModel) cVar);
                break;
            case MRP:
                String a = m3157a(mrzResult.getOptData2());
                if (!(a == null || a.length() == 0)) {
                    a = a.replaceAll("\\s+", "");
                }
                cVar.setPersonalNumber(a);
                break;
            case TD1:
                String a2 = m3157a(mrzResult.getOptData1());
                if (!(a2 == null || a2.length() == 0)) {
                    cVar.setOptionalData1(a2);
                }
                m3159a(mrzResult, (DocumentDataModel) cVar);
                break;
            case TD2:
            case CNIS:
                m3159a(mrzResult, (DocumentDataModel) cVar);
                break;
        }
        cVar.setExpiryDate(m3158a(mrzResult.getExpidate().getAsMrzDate(), false));
        String a3 = m3157a(mrzResult.getCountry());
        if (a3 != null) {
            a3 = a3.trim();
        }
        if ("D".equals(a3)) {
            a3 = ServerSettingsModel.GERMAN_ISO3;
        }
        cVar.setIssuingCountry(a3);
        cVar.setLastName(m3157a(mrzResult.getSecondName()));
        String a4 = m3157a(mrzResult.getFirstName());
        if (this.f4905g != DocumentScanMode.CNIS || a4 == null) {
            cVar.setFirstName(a4);
        } else {
            String[] split = a4.split("\\s{2}", 2);
            if (split.length >= 1) {
                cVar.setFirstName(split[0]);
            }
            if (split.length >= 2) {
                cVar.setMiddleName(split[1].replaceAll("  ", " "));
            }
        }
        cVar.setDob(m3158a(mrzResult.getBirthdate().getAsMrzDate(), true));
        if (NVGender.M.name().equals(m3157a(mrzResult.getSex()))) {
            cVar.setGender(NVGender.M);
        } else if (NVGender.F.name().equals(m3157a(mrzResult.getSex()))) {
            cVar.setGender(NVGender.F);
        }
        String a5 = m3157a(mrzResult.getNationality());
        if (a5 != null) {
            a5 = a5.trim();
        }
        if ("D".equals(a5)) {
            a5 = ServerSettingsModel.GERMAN_ISO3;
        }
        cVar.setOriginatingCountry(a5);
        NVMRZFormat nVMRZFormat = null;
        switch (this.f4905g) {
            case MRV:
                if (mrzLines.size() != 2 || mrzLines.get(0).length() != 36) {
                    if (mrzLines.size() == 2 && mrzLines.get(0).length() == 44) {
                        nVMRZFormat = NVMRZFormat.MRV_A;
                        break;
                    }
                } else {
                    nVMRZFormat = NVMRZFormat.MRV_B;
                    break;
                }
                break;
            case TD1:
                nVMRZFormat = NVMRZFormat.TD1;
                break;
            case TD2:
                nVMRZFormat = NVMRZFormat.TD2;
                break;
            case CNIS:
                nVMRZFormat = NVMRZFormat.CNIS;
                break;
            default:
                nVMRZFormat = NVMRZFormat.MRP;
                break;
        }
        cVar.mo46926a(mrzResult, nVMRZFormat);
        cVar.mo46927a(mrzLines);
        cVar.mo46928a(arrayList);
        publishResult(cVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo46920a(MrzRectVector[] mrzRectVectorArr) {
        publishUpdate(new ExtractionUpdate(C4962b.f4920a, mrzRectVectorArr));
    }

    public void cancel() {
        super.cancel();
    }

    /* renamed from: a */
    private void m3159a(MrzResult mrzResult, DocumentDataModel documentDataModel) {
        String a = m3157a(mrzResult.getOptData2());
        if (a != null && a.length() != 0) {
            if (this.f4905g == DocumentScanMode.CNIS) {
                documentDataModel.setPersonalNumber(a.replaceAll("\\s+", ""));
            } else {
                documentDataModel.setOptionalData2(a);
            }
        }
    }

    /* renamed from: a */
    private Date m3158a(MrzDate mrzDate, boolean z) {
        Calendar instance = Calendar.getInstance();
        if (mrzDate.getYear() == -1 || mrzDate.getMonth() < 1 || mrzDate.getMonth() > 12 || mrzDate.getDay() < 1 || mrzDate.getDay() > 31) {
            return null;
        }
        instance.set(mrzDate.getYear(), mrzDate.getMonth() - 1, mrzDate.getDay(), 0, 0, 0);
        Date time = instance.getTime();
        if (!z) {
            return time;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            if (time.after(simpleDateFormat.parse(simpleDateFormat.format(new Date())))) {
                time = null;
            }
            return time;
        } catch (ParseException e) {
            return time;
        }
    }

    /* renamed from: a */
    private String m3157a(MrzField mrzField) {
        return mrzField.getAsString();
    }
}
