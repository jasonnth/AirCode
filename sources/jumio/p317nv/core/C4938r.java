package jumio.p317nv.core;

import android.content.Context;
import android.graphics.Bitmap;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.core.ImageQuality;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.models.DocumentDataModel;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: jumio.nv.core.r */
/* compiled from: FaceClient */
public class C4938r extends ExtractionClient<ExtractionUpdate, DocumentDataModel> {

    /* renamed from: a */
    private final Executor f4821a = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final AtomicBoolean f4822b = new AtomicBoolean(true);

    /* renamed from: c */
    private final AtomicBoolean f4823c = new AtomicBoolean(false);

    /* renamed from: d */
    private AtomicBoolean f4824d = new AtomicBoolean(false);

    /* renamed from: jumio.nv.core.r$a */
    /* compiled from: FaceClient */
    class C4939a extends Thread {

        /* renamed from: b */
        private byte[] f4826b;

        public C4939a(byte[] bArr) {
            this.f4826b = Arrays.copyOf(bArr, bArr.length);
        }

        public void run() {
            int i;
            int i2;
            if (C4938r.this.isPortrait()) {
                i = C4938r.this.getPreviewProperties().preview.height;
                i2 = C4938r.this.getPreviewProperties().preview.width;
            } else {
                i = C4938r.this.getPreviewProperties().preview.width;
                i2 = C4938r.this.getPreviewProperties().preview.height;
            }
            C4938r.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.notifyFlash, Boolean.valueOf(ImageQuality.isFlashNeeded(this.f4826b, i, i2, false))));
            this.f4826b = null;
            System.gc();
            synchronized (C4938r.this) {
                C4938r.this.f4822b.set(false);
            }
        }
    }

    /* renamed from: jumio.nv.core.r$b */
    /* compiled from: FaceClient */
    class C4940b extends Thread {

        /* renamed from: b */
        private byte[] f4828b;

        public C4940b(byte[] bArr) {
            this.f4828b = Arrays.copyOf(bArr, bArr.length);
        }

        public void run() {
            C4938r.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.shotTaken, Float.valueOf(1.0f)));
            Bitmap yuv2bitmap = CameraUtils.yuv2bitmap(this.f4828b, C4938r.this.isPortrait(), C4938r.this.getPreviewProperties(), true, 640);
            C4938r.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveImage, yuv2bitmap));
            C4938r.this.publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveExactImage, yuv2bitmap));
            C4938r.this.publishResult(new DocumentDataModel());
            this.f4828b = null;
            System.gc();
        }
    }

    public C4938r(Context context) {
        super(context);
    }

    public void configure(StaticModel staticModel) {
    }

    /* access modifiers changed from: protected */
    public void init() {
    }

    public void destroy() {
    }

    /* renamed from: a */
    public void feed(byte[] bArr) {
        if (this.f4822b.compareAndSet(false, true)) {
            this.f4821a.execute(new C4939a(bArr));
        }
        if (this.f4823c.get() && !this.f4824d.get()) {
            this.f4824d.set(true);
            new C4940b(bArr).start();
        }
    }

    public void takePicture() {
        this.f4823c.set(true);
    }

    public boolean takePictureManually() {
        return true;
    }
}
