package jumio.p317nv.core;

import android.content.Context;
import android.graphics.Bitmap;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.camera.CameraManager.Size;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.commons.log.Log;
import com.jumio.commons.log.Log.LogLevel;
import com.jumio.core.ImageQuality;
import com.jumio.core.data.document.DocumentFormat;
import com.jumio.core.environment.Environment;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.ocr.impl.smartEngines.swig.DetectionEngine;
import com.jumio.ocr.impl.smartEngines.swig.DetectionInternalSettingsFactory;
import com.jumio.ocr.impl.smartEngines.swig.DetectionResult;
import com.jumio.ocr.impl.smartEngines.swig.DetectionSettings;
import com.jumio.ocr.impl.smartEngines.swig.OcrPoint;
import com.jumio.ocr.impl.smartEngines.swig.OcrQuadrangle;
import com.jumio.p311nv.enums.NVErrorCase;
import com.jumio.p311nv.environment.NVEnvironment;
import com.jumio.p311nv.models.NVScanPartModel;
import com.jumio.p311nv.utils.NetverifyLogUtils;
import com.jumio.p311nv.utils.NetverifyLogUtils.C4475a;
import com.jumio.sdk.exception.JumioErrorCase;
import com.jumio.sdk.exception.JumioException;
import com.jumio.sdk.extraction.ExtractionClient;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;
import java.security.InvalidParameterException;
import java.util.Arrays;

/* renamed from: jumio.nv.core.u */
/* compiled from: LineFinderClient */
public class C4944u extends ExtractionClient<ExtractionUpdate, Void> {

    /* renamed from: a */
    public static int f4838a = 800;

    /* renamed from: b */
    public static int f4839b = 600;

    /* renamed from: c */
    private C4945a f4840c = null;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public DetectionEngine f4841d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public boolean f4842e = false;

    /* renamed from: f */
    private int f4843f = 0;

    /* renamed from: g */
    private DocumentFormat f4844g;

    /* renamed from: jumio.nv.core.u$a */
    /* compiled from: LineFinderClient */
    class C4945a extends Thread {

        /* renamed from: b */
        private byte[] f4846b;

        /* renamed from: c */
        private PreviewProperties f4847c;

        public C4945a(byte[] bArr) {
            this.f4846b = Arrays.copyOf(bArr, bArr.length);
            this.f4847c = C4944u.this.getPreviewProperties().copy();
        }

        public void run() {
            DetectionResult detectionResult;
            boolean z = false;
            C4944u.f4838a = 800;
            C4944u.f4839b = 600;
            if (C4944u.this.isPortrait()) {
                if (this.f4847c.surface.width < 800) {
                    C4944u.f4838a = this.f4847c.preview.width - (((int) ((((float) (this.f4847c.scaledPreview.width - this.f4847c.surface.width)) / 2.0f) * (((float) this.f4847c.preview.width) / ((float) this.f4847c.scaledPreview.width)))) * 2);
                    C4944u.f4839b = (int) (((float) C4944u.f4838a) * 0.75f);
                }
            } else if (C4944u.this.mIsTablet) {
                int i = (int) ((((float) (this.f4847c.scaledPreview.width - this.f4847c.surface.width)) / 2.0f) * (((float) this.f4847c.preview.width) / ((float) this.f4847c.scaledPreview.width)));
                if (this.f4847c.preview.width - (i * 2) < 800) {
                    C4944u.f4838a = this.f4847c.preview.width - (i * 2);
                    C4944u.f4839b = (int) (((float) C4944u.f4838a) * 0.75f);
                }
            } else if (this.f4847c.surface.height < 600) {
                C4944u.f4839b = this.f4847c.preview.height - (((int) ((((float) (this.f4847c.scaledPreview.height - this.f4847c.surface.height)) / 2.0f) * (((float) this.f4847c.preview.height) / ((float) this.f4847c.scaledPreview.height)))) * 2);
                C4944u.f4838a = (int) (((float) C4944u.f4839b) / 0.75f);
            }
            byte[] a = C4944u.this.m3093a(this.f4846b, C4944u.f4838a, C4944u.f4839b);
            if (a != null) {
                float calculateFocus = ImageQuality.calculateFocus(a, C4944u.f4838a, C4944u.f4839b, true);
                if (calculateFocus >= 0.12f) {
                    detectionResult = C4944u.this.f4841d.processRawImage(a, C4944u.f4838a, C4944u.f4839b, C4944u.f4838a * 3, (int) System.currentTimeMillis());
                } else {
                    detectionResult = null;
                }
                if (detectionResult != null) {
                    C4944u uVar = C4944u.this;
                    boolean hasTopOfCard = detectionResult.hasTopOfCard();
                    boolean hasBottomOfCard = detectionResult.hasBottomOfCard();
                    boolean hasLeftOfCard = detectionResult.hasLeftOfCard();
                    boolean hasRightOfCard = detectionResult.hasRightOfCard();
                    boolean flashTurnOn = detectionResult.getFlashTurnOn();
                    if (!detectionResult.isCardImageOfGoodQuality()) {
                        z = true;
                    }
                    z = uVar.mo46902a(hasTopOfCard, hasBottomOfCard, hasLeftOfCard, hasRightOfCard, flashTurnOn, z);
                    if (z) {
                        OcrQuadrangle cardQuadrangle = detectionResult.getCardQuadrangle();
                        if (cardQuadrangle != null) {
                            OcrPoint topLeft = cardQuadrangle.getTopLeft();
                            OcrPoint topRight = cardQuadrangle.getTopRight();
                            OcrPoint bottomLeft = cardQuadrangle.getBottomLeft();
                            OcrPoint bottomRight = cardQuadrangle.getBottomRight();
                            int x = topLeft.getX() < bottomLeft.getX() ? topLeft.getX() : bottomLeft.getX();
                            int y = topLeft.getY() < topRight.getY() ? topLeft.getY() : topRight.getY();
                            int x2 = bottomRight.getX() > topRight.getX() ? bottomRight.getX() : topRight.getX();
                            int y2 = bottomRight.getY() > bottomLeft.getY() ? bottomRight.getY() : bottomLeft.getY();
                            int i2 = x2 - x;
                            int i3 = y2 - y;
                            byte[] bArr = new byte[(i2 * i3 * 3)];
                            for (int i4 = y; i4 < y + i3; i4++) {
                                System.arraycopy(a, (C4944u.f4838a * i4 * 3) + (x * 3), bArr, (i4 - y) * i2 * 3, i2 * 3);
                            }
                            C4944u.this.mo46901a(a, C4944u.f4838a, C4944u.f4839b, bArr, i2, i3, calculateFocus);
                        }
                        if (Log.isLogEnabledForLevel(LogLevel.INFO)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("hasTopOfCard(): " + Boolean.toString(detectionResult.hasTopOfCard()));
                            sb.append("hasBottomOfCard(): " + Boolean.toString(detectionResult.hasBottomOfCard()));
                            sb.append("hasLeftOfCard(): " + Boolean.toString(detectionResult.hasLeftOfCard()));
                            sb.append("hasRightOfCard(): " + Boolean.toString(detectionResult.hasRightOfCard()));
                            sb.append("getFlashTurnOn(): " + Boolean.toString(detectionResult.getFlashTurnOn()));
                            sb.append("isCardImageOfGoodQuality(): " + Boolean.toString(detectionResult.isCardImageOfGoodQuality()));
                            sb.append("\r\n");
                            C4475a.m2009a(sb);
                            NetverifyLogUtils.logInfoInSubfolder(sb.toString(), C4944u.class.getSimpleName(), null);
                        }
                    }
                }
            }
            this.f4846b = null;
            System.gc();
            synchronized (C4944u.this) {
                C4944u.this.f4842e = z;
            }
            this.f4847c = null;
        }
    }

    public C4944u(Context context) {
        super(context);
    }

    public void configure(StaticModel staticModel) {
        if (!(staticModel instanceof NVScanPartModel)) {
            throw new InvalidParameterException("Configuration model should be an instance of ScanPartModel");
        }
        this.f4844g = ((NVScanPartModel) staticModel).getScanMode().getFormat();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.f4842e = false;
        Environment.loadJniInterfaceLib();
        DetectionSettings detectionSettings = new DetectionSettings();
        detectionSettings.setRoiLeftMargin(this.f4844g.getOverlayLeft());
        detectionSettings.setRoiRightMargin(this.f4844g.getOverlayRight());
        detectionSettings.setRoiTopMargin(this.f4844g.getOverlayTop());
        detectionSettings.setRoiBottomMargin(this.f4844g.getOverlayBottom());
        detectionSettings.setRoiVerticalDeviation(0.04d);
        detectionSettings.setRoiHorizontalDeviation(0.03d);
        try {
            String cardDetectionSettingsPath = NVEnvironment.getCardDetectionSettingsPath(this.mContext);
            if (cardDetectionSettingsPath == null) {
                throw new Exception("Loading detection settings failed!");
            }
            this.f4841d = new DetectionEngine(detectionSettings, DetectionInternalSettingsFactory.createFromFileSystem(cardDetectionSettingsPath));
        } catch (Exception e) {
            publishError(new JumioException((JumioErrorCase) NVErrorCase.OCR_LOADING_FAILED));
        }
    }

    public void destroy() {
    }

    public void cancel() {
        super.cancel();
        synchronized (this) {
            if (this.f4840c != null) {
                this.f4840c.interrupt();
            }
        }
    }

    /* renamed from: a */
    public void feed(byte[] bArr) {
        synchronized (this) {
            if (!this.f4842e && this.f4841d != null) {
                this.f4842e = true;
                this.f4840c = new C4945a(bArr);
                this.f4840c.start();
            }
        }
    }

    /* renamed from: a */
    private int m3089a(boolean z, boolean z2, boolean z3, boolean z4) {
        int i = 0;
        if (z) {
            i = 1;
        }
        if (z2) {
            i++;
        }
        if (z3) {
            i++;
        }
        if (z4) {
            return i + 1;
        }
        return i;
    }

    /* renamed from: a */
    public boolean mo46902a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        publishUpdate(new ExtractionUpdate(C4947w.f4854a, new C4946v(z4, z3, z, z2, z5, z6)));
        int a = m3089a(z, z2, z3, z4);
        if (a < 3 || z6) {
            this.f4843f = 0;
            if (Log.isLogEnabledForLevel(LogLevel.INFO)) {
                C4475a.m2007a();
            }
        } else {
            this.f4843f++;
            if (Log.isLogEnabledForLevel(LogLevel.INFO)) {
                C4475a.m2008a(a);
            }
        }
        if (this.f4843f >= 5) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo46901a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, float f) {
        publishUpdate(new ExtractionUpdate(ExtractionUpdateState.shotTaken, Float.valueOf(f)));
        publishUpdate(new ExtractionUpdate(C4947w.f4854a, new C4946v(false, false, false, false, false, false)));
        Bitmap rgb2bitmap = CameraUtils.rgb2bitmap(bArr, i, i2);
        Bitmap rgb2bitmap2 = CameraUtils.rgb2bitmap(bArr2, i3, i4);
        publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveImage, rgb2bitmap));
        publishUpdate(new ExtractionUpdate(ExtractionUpdateState.saveExactImage, rgb2bitmap2));
        publishResult(null);
        System.gc();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public byte[] m3093a(byte[] bArr, int i, int i2) {
        return CameraUtils.yuv2rgb(bArr, isPortrait(), getPreviewProperties(), 0.75f, new Size(i, i2), (StringBuilder) null);
    }
}
