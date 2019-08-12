package jumio.p317nv.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.Pair;
import android.util.SparseArray;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.jumio.clientlib.impl.livenessAndTM.FaceRect;
import com.jumio.clientlib.impl.livenessAndTM.FrameProcessorLivenessDetector;
import com.jumio.clientlib.impl.livenessAndTM.ImageOrientation;
import com.jumio.clientlib.impl.livenessAndTM.LibImage;
import com.jumio.clientlib.impl.livenessAndTM.LivenessDetectorCallback;
import com.jumio.clientlib.impl.livenessAndTM.LivenessDetectorResult;
import com.jumio.clientlib.impl.livenessAndTM.PixelFormatType;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.camera.CameraManager.Size;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.commons.log.Log;
import com.jumio.commons.log.Log.LogLevel;
import com.jumio.commons.log.LogUtils;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.enums.NVErrorCase;
import com.jumio.p311nv.environment.NVEnvironment;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.p311nv.utils.NetverifyLogUtils;
import com.jumio.persistence.DataAccess;
import com.jumio.sdk.exception.JumioErrorCase;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;
import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: jumio.nv.core.z */
/* compiled from: LivenessClient */
public class C4951z extends ExtractionClient<ExtractionUpdate, DocumentDataModel> {

    /* renamed from: a */
    public Bitmap f4877a = null;

    /* renamed from: b */
    PreviewProperties f4878b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public String f4879c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C4954c f4880d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public LivenessDetectorCallback f4881e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C4889ac f4882f;

    /* renamed from: g */
    private C4952a f4883g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public int f4884h = 0;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public int f4885i = 0;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f4886j = 0;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public int f4887k = 0;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public int f4888l = 0;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public C4950y f4889m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public SparseArray<C4950y> f4890n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public int f4891o;

    /* renamed from: jumio.nv.core.z$a */
    /* compiled from: LivenessClient */
    public class C4952a extends CountDownTimer {
        public C4952a(long j, long j2) {
            super(j, j2);
        }

        public void onFinish() {
            C4951z.this.publishUpdate(new ExtractionUpdate(C4888ab.f4709d, new Pair(Integer.valueOf(0), Integer.valueOf(0))));
            if (C4951z.this.f4889m == null) {
                C4951z.this.m3130f();
            } else {
                C4951z.this.m3133g();
            }
        }

        public void onTick(long j) {
            float f = 100.0f;
            float f2 = (((float) ((5000 - j) + 200)) * 100.0f) / 5000.0f;
            if (f2 <= 100.0f) {
                f = f2;
            }
            C4951z.this.publishUpdate(new ExtractionUpdate(C4888ab.f4709d, new Pair(Integer.valueOf(100 - ((int) f)), Integer.valueOf(Math.round(((float) j) / 1000.0f)))));
        }
    }

    /* renamed from: jumio.nv.core.z$b */
    /* compiled from: LivenessClient */
    class C4953b extends LivenessDetectorCallback {
        C4953b() {
        }

        public void onResult(LivenessDetectorResult livenessDetectorResult, int i) {
            if (C4951z.this.f4891o != C4888ab.f4708c && C4951z.this.f4880d != null) {
                C4951z.this.f4885i = C4951z.this.f4885i + 1;
                FaceRect faceRect = livenessDetectorResult.getFaceRect();
                boolean isTracking = livenessDetectorResult.isTracking();
                boolean z = false;
                if (isTracking) {
                    RectF a = C4951z.this.m3108a(faceRect);
                    RectF rectF = new RectF();
                    rectF.top = a.top - (a.height() * 0.55f);
                    rectF.bottom = a.bottom + (a.height() * 0.15f);
                    rectF.left = a.left - (a.width() * 0.05f);
                    rectF.right = (a.width() * 0.05f) + a.right;
                    z = m3150a(rectF);
                }
                if (!isTracking || !z) {
                    C4951z.this.f4888l = C4951z.this.f4888l + 1;
                    if (C4951z.this.f4890n != null) {
                        C4950y yVar = (C4950y) C4951z.this.f4890n.get(i);
                        if (yVar != null) {
                            yVar.f4871a = null;
                        }
                        C4951z.this.f4890n.remove(i);
                    }
                    if (C4951z.this.f4888l >= 2 && C4951z.this.f4891o != C4888ab.f4706a) {
                        C4951z.this.m3113a(C4888ab.f4706a);
                        C4951z.this.m3130f();
                    }
                } else {
                    if (livenessDetectorResult.isSmiling()) {
                        C4951z.this.f4887k = C4951z.this.f4887k + 1;
                    }
                    if (livenessDetectorResult.isLeftEyeBlinking() || livenessDetectorResult.isRightEyeBlinking()) {
                        C4951z.this.f4886j = C4951z.this.f4886j + 1;
                    }
                    if (C4951z.this.f4891o != C4888ab.f4707b) {
                        C4951z.this.m3113a(C4888ab.f4707b);
                        C4951z.this.m3124d();
                    }
                    if (C4951z.this.f4889m == null && C4951z.this.f4890n != null && C4951z.this.f4890n.size() > 0 && !livenessDetectorResult.isLeftEyeBlinking() && !livenessDetectorResult.isRightEyeBlinking()) {
                        C4951z.this.f4889m = (C4950y) C4951z.this.f4890n.get(i);
                        C4951z.this.m3121c();
                    }
                }
                System.gc();
            }
        }

        /* renamed from: a */
        private boolean m3150a(RectF rectF) {
            boolean z;
            boolean z2;
            boolean z3;
            float f;
            boolean z4;
            float f2;
            boolean z5;
            boolean z6;
            int i = C4951z.this.getPreviewProperties().surface.width;
            int i2 = C4951z.this.getPreviewProperties().surface.height;
            float width = rectF.width();
            float height = rectF.height();
            if (((float) i) / ((float) i2) < 0.7616f) {
                float f3 = ((float) i) * 0.95f;
                float f4 = f3 / 0.7616f;
                z2 = width < ((float) i) * 0.5f;
                if (width > f3) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                float f5 = f4;
                f = f3;
                z4 = z6;
                f2 = f5;
            } else {
                float f6 = ((float) i2) * 0.95f;
                float f7 = f6 * 0.7616f;
                if (height < ((float) i2) * 0.5f) {
                    z = true;
                } else {
                    z = false;
                }
                if (height > ((float) i2)) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                float f8 = f6;
                f = f7;
                z4 = z3;
                f2 = f8;
            }
            float f9 = (((float) i) - f) / 2.0f;
            float f10 = (((float) i2) - f2) / 2.0f;
            if (rectF.left < f9 || rectF.right > ((float) i) - f9 || rectF.top < f10 || rectF.bottom > ((float) i2) - f10) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (z5 || z4 || z2) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: jumio.nv.core.z$c */
    /* compiled from: LivenessClient */
    public class C4954c extends Thread {

        /* renamed from: b */
        private FrameProcessorLivenessDetector f4895b;

        /* renamed from: c */
        private Queue<C4950y> f4896c = new ConcurrentLinkedQueue();

        /* renamed from: d */
        private int f4897d = -1;

        public C4954c() {
        }

        /* renamed from: a */
        public void mo46914a() {
            this.f4896c.clear();
            this.f4897d = -1;
            C4951z.this.f4884h = -1;
            System.gc();
        }

        /* renamed from: a */
        public void mo46915a(byte[] bArr) {
            byte[] bArr2;
            try {
                if (this.f4895b != null && !isInterrupted()) {
                    Size size = C4951z.super.getPreviewProperties().preview;
                    int i = size.width;
                    int i2 = size.height;
                    if (C4951z.this.getPreviewProperties().orientation == 0 || C4951z.this.getPreviewProperties().orientation == 180) {
                        bArr2 = CameraUtils.yuv2rgb(bArr, false, C4951z.super.getPreviewProperties(), i, i2, 0, 0, C4951z.this.getPreviewProperties().preview.width, C4951z.this.getPreviewProperties().preview.height);
                    } else {
                        bArr2 = CameraUtils.yuv2rgb(bArr, true, C4951z.super.getPreviewProperties(), i2, i, 0, 0, C4951z.this.getPreviewProperties().preview.width, C4951z.this.getPreviewProperties().preview.height);
                    }
                    if (bArr2 != null) {
                        C4950y yVar = new C4950y(bArr2, C4951z.this.getPreviewProperties().preview.width, C4951z.this.getPreviewProperties().preview.height, 0, PixelFormatType.PIXEL_FORMAT_RGB_8, C4951z.this.getPreviewProperties().preview.width * 3);
                        this.f4896c.add(yVar);
                        if (C4951z.this.f4882f != null) {
                            C4951z.this.f4882f.mo46801a(yVar);
                        }
                    }
                }
            } catch (OutOfMemoryError e) {
                this.f4896c.clear();
                Log.printStackTrace(e);
                System.gc();
            }
        }

        public void run() {
            ImageOrientation imageOrientation;
            try {
                this.f4895b = new FrameProcessorLivenessDetector(C4951z.this.f4881e, C4951z.this.f4879c);
            } catch (Exception e) {
                C4951z.this.publishError(new JumioException((JumioErrorCase) NVErrorCase.OCR_LOADING_FAILED));
                Log.printStackTrace(e);
            }
            while (this.f4895b != null && !isInterrupted()) {
                C4950y yVar = (C4950y) this.f4896c.poll();
                if (yVar != null) {
                    switch (yVar.f4874d) {
                        case 0:
                            imageOrientation = ImageOrientation.IMAGE_ORIENTATION_LANDSCAPE;
                            break;
                        case 180:
                            imageOrientation = ImageOrientation.IMAGE_ORIENTATION_REVERSE_LANDSCAPE;
                            break;
                        case MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS /*270*/:
                            imageOrientation = ImageOrientation.IMAGE_ORIENTATION_REVERSE_PORTRAIT;
                            break;
                        default:
                            imageOrientation = ImageOrientation.IMAGE_ORIENTATION_PORTRAIT;
                            break;
                    }
                    byte[] bArr = yVar.f4871a;
                    PixelFormatType pixelFormatType = yVar.f4876f;
                    int i = yVar.f4872b;
                    int i2 = yVar.f4873c;
                    long j = (long) yVar.f4875e;
                    int i3 = this.f4897d + 1;
                    this.f4897d = i3;
                    LibImage libImage = new LibImage(bArr, pixelFormatType, i, i2, j, 3, 1, i3, 0, false);
                    try {
                        if (C4951z.this.f4889m == null && C4951z.this.f4890n != null) {
                            C4951z.this.f4890n.put(this.f4897d, yVar);
                        }
                        this.f4895b.pushFrame(libImage, imageOrientation);
                        libImage.delete();
                        System.gc();
                    } catch (Exception e2) {
                        Log.printStackTrace(e2);
                    }
                }
            }
            mo46914a();
            m3151b();
        }

        /* renamed from: b */
        private void m3151b() {
            if (this.f4895b != null) {
                this.f4895b.reset();
                this.f4895b.delete();
                this.f4895b = null;
            }
        }
    }

    /* renamed from: jumio.nv.core.z$d */
    /* compiled from: LivenessClient */
    class C4955d extends Thread {
        public C4955d() {
        }

        public void run() {
            Boolean valueOf = Boolean.valueOf(C4951z.this.f4886j + C4951z.this.f4887k >= 2);
            C4951z.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.shotTaken, Float.valueOf(1.0f)));
            Bitmap rgb2bitmap = CameraUtils.rgb2bitmap(C4951z.this.f4889m.f4871a, C4951z.this.getPreviewProperties().preview.width, C4951z.this.getPreviewProperties().preview.height);
            if (Log.isLogEnabledForLevel(LogLevel.INFO)) {
                StringBuilder sb = new StringBuilder();
                LogUtils.appendParameter(sb, "smiles", (long) C4951z.this.f4887k);
                LogUtils.appendParameter(sb, "blinks", (long) C4951z.this.f4886j);
                LogUtils.appendParameter(sb, "isAlive", String.valueOf(valueOf));
                NetverifyLogUtils.logInfoInSubfolder(sb.toString(), C4951z.class.getSimpleName(), null);
            }
            C4951z.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveImage, rgb2bitmap));
            C4951z.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveExactImage, rgb2bitmap));
            C4886aa aaVar = new C4886aa();
            aaVar.setLivenessDetected(valueOf);
            if (C4951z.this.f4882f != null) {
                String[] b = C4951z.this.f4882f.mo46803b();
                aaVar.mo46794a(b);
                for (int i = 0; i < b.length; i++) {
                    NetverifyLogUtils.copyFile(b[i], C4951z.class.getSimpleName(), String.format("%02d_%s.jpg", new Object[]{Integer.valueOf(i), new File(b[i]).getName()}));
                }
            }
            C4951z.this.publishResult(aaVar);
        }
    }

    public C4951z(Context context) {
        super(context);
        NVEnvironment.loadLivenessDetectorAndTemplateMatcherLib();
        this.f4881e = new C4953b();
        this.f4879c = NVEnvironment.getLivenessEngineSettingsPath(context);
        this.f4883g = new C4952a(5000, 100);
        this.f4889m = null;
        this.f4890n = new SparseArray<>();
        m3113a(C4888ab.f4706a);
    }

    public void configure(StaticModel staticModel) {
    }

    public void setDataExtractionActive(boolean z) {
        super.setDataExtractionActive((this.mIsPortrait || this.mIsTablet) && z);
    }

    /* access modifiers changed from: protected */
    public void init() {
        m3135h();
        if (this.f4880d == null) {
            ServerSettingsModel serverSettingsModel = (ServerSettingsModel) DataAccess.load(this.mContext, ServerSettingsModel.class);
            this.f4880d = new C4954c();
            this.f4880d.start();
            this.f4882f = new C4889ac(this.mContext, serverSettingsModel.getMaxLivenessImages(), 60);
            this.f4882f.mo46802a(true);
            this.f4882f.start();
            setDataExtractionActive(true);
        }
    }

    public void destroy() {
        m3112a();
        m3117b();
        m3121c();
        this.f4877a = null;
        this.f4889m = null;
    }

    public void cancel() {
        super.cancel();
        m3112a();
        m3113a(C4888ab.f4706a);
        m3130f();
    }

    /* renamed from: a */
    public void feed(byte[] bArr) {
        if (this.f4880d != null) {
            this.f4884h++;
            if (this.f4884h > 10) {
                this.f4880d.mo46915a(bArr);
            }
        }
    }

    /* renamed from: a */
    private void m3112a() {
        if (this.f4880d != null) {
            this.f4880d.interrupt();
            this.f4880d = null;
        }
        if (this.f4882f != null) {
            this.f4882f.interrupt();
            this.f4882f = null;
        }
    }

    /* renamed from: b */
    private void m3117b() {
        if (this.f4883g != null) {
            this.f4883g.cancel();
            this.f4883g = null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m3121c() {
        if (this.f4890n != null) {
            this.f4890n.clear();
            this.f4890n = null;
            System.gc();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public RectF m3108a(FaceRect faceRect) {
        float x;
        float f;
        float scaleFactor = getPreviewProperties().getScaleFactor();
        float f2 = ((float) (getPreviewProperties().scaledPreview.width - getPreviewProperties().surface.width)) / 2.0f;
        float f3 = ((float) (getPreviewProperties().scaledPreview.height - getPreviewProperties().surface.height)) / 2.0f;
        float width = ((float) faceRect.getWidth()) * scaleFactor;
        float height = ((float) faceRect.getHeight()) * scaleFactor;
        if (getPreviewProperties().orientation == 0) {
            f = ((float) getPreviewProperties().scaledPreview.width) - (f2 + (((float) faceRect.getX()) * scaleFactor));
            x = f - width;
        } else {
            x = (((float) faceRect.getX()) * scaleFactor) - f2;
            f = x + width;
        }
        float y = (scaleFactor * ((float) faceRect.getY())) - f3;
        return new RectF(x, y, f, y + height);
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m3124d() {
        this.f4888l = 0;
        this.f4883g.start();
    }

    /* renamed from: e */
    private void m3127e() {
        this.f4883g.cancel();
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m3130f() {
        m3127e();
        this.f4889m = null;
        m3121c();
        this.f4890n = new SparseArray<>();
        this.f4887k = 0;
        this.f4886j = 0;
        this.f4888l = 0;
        if (this.f4880d != null) {
            this.f4880d.mo46914a();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m3133g() {
        if (this.f4880d != null) {
            this.f4880d.interrupt();
            m3113a(C4888ab.f4708c);
            m3127e();
            new C4955d().start();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m3113a(int i) {
        this.f4891o = i;
        if (this.f4882f != null) {
            this.f4882f.mo46802a(this.f4891o == C4888ab.f4706a);
        }
        publishUpdate(new ExtractionUpdate(i, null));
    }

    /* renamed from: h */
    private void m3135h() {
        float f;
        int i;
        int i2 = 0;
        this.f4878b = super.getPreviewProperties().copy();
        int i3 = super.getPreviewProperties().surface.width;
        int i4 = super.getPreviewProperties().surface.height;
        float f2 = i3 > i4 ? ((float) i3) / ((float) i4) : ((float) i4) / ((float) i3);
        if (this.mIsPortrait) {
            f = ((float) super.getPreviewProperties().preview.width) / ((float) super.getPreviewProperties().preview.height);
        } else {
            f = ((float) super.getPreviewProperties().preview.height) / ((float) super.getPreviewProperties().preview.width);
        }
        int i5 = (int) (f * ((float) 640));
        float f3 = ((float) 640) / ((float) i5);
        if (this.mIsPortrait) {
            this.f4878b.preview = new Size(i5, 640);
            if (f3 >= f2) {
                i = (int) ((((float) 640) / ((float) i5)) * ((float) i3));
                i2 = i3;
            } else {
                if (f3 < f2) {
                    i2 = (int) (((float) i4) / (((float) 640) / ((float) i5)));
                    i = i4;
                }
                i = 0;
            }
        } else {
            this.f4878b.preview = new Size(640, i5);
            if (f3 <= f2) {
                i = (int) (((float) i3) / (((float) 640) / ((float) i5)));
                i2 = i3;
            } else {
                if (f3 > f2) {
                    i2 = (int) ((((float) 640) / ((float) i5)) * ((float) i4));
                    i = i4;
                }
                i = 0;
            }
        }
        this.f4878b.scaledPreview = new Size(i2, i);
    }

    public PreviewProperties getPreviewProperties() {
        return this.f4878b;
    }
}
