package jumio.p317nv.core;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.util.TypedValue;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.p311nv.C4430R;
import com.jumio.p311nv.NVOverlay;
import com.jumio.p311nv.NVOverlay.NVOverlayConfig;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;

/* renamed from: jumio.nv.core.x */
/* compiled from: LineFinderOverlay */
public class C4948x extends NVOverlay {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Paint f4855a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Paint f4856b;

    /* renamed from: c */
    private int f4857c;

    /* renamed from: d */
    private int f4858d;

    /* renamed from: e */
    private int f4859e;

    /* renamed from: f */
    private C4949a f4860f;

    /* renamed from: g */
    private C4949a f4861g;

    /* renamed from: i */
    private C4949a f4862i;

    /* renamed from: j */
    private C4949a f4863j;

    /* renamed from: k */
    private NVOverlayConfig f4864k;

    /* renamed from: jumio.nv.core.x$a */
    /* compiled from: LineFinderOverlay */
    class C4949a {

        /* renamed from: b */
        private int f4866b = 0;

        /* renamed from: c */
        private int f4867c = 0;

        /* renamed from: d */
        private int f4868d = 0;

        /* renamed from: e */
        private int f4869e = 0;

        /* renamed from: f */
        private boolean f4870f = false;

        public C4949a() {
        }

        /* renamed from: a */
        public void mo46908a(int i, int i2, int i3, int i4) {
            this.f4866b = i;
            this.f4868d = i2;
            this.f4867c = i3;
            this.f4869e = i4;
        }

        /* renamed from: a */
        public void mo46910a(boolean z) {
            this.f4870f = z;
        }

        /* renamed from: a */
        public void mo46909a(Canvas canvas) {
            if (this.f4870f) {
                canvas.drawRect((float) this.f4866b, (float) this.f4868d, (float) this.f4867c, (float) this.f4869e, C4948x.this.f4855a);
                canvas.drawRect((float) this.f4866b, (float) this.f4868d, (float) this.f4867c, (float) this.f4869e, C4948x.this.f4856b);
            }
        }
    }

    public C4948x(Context context) {
        super(context);
        this.f4855a = null;
        this.f4856b = null;
        this.f4857c = 0;
        this.f4858d = 0;
        this.f4859e = 0;
        this.f4860f = null;
        this.f4861g = null;
        this.f4862i = null;
        this.f4863j = null;
        this.f4864k = new NVOverlayConfig();
        this.f4860f = new C4949a();
        this.f4861g = new C4949a();
        this.f4862i = new C4949a();
        this.f4863j = new C4949a();
        this.f4859e = (int) ScreenUtil.dipToPx(context, 20.0f);
        this.f4858d = (int) ScreenUtil.dipToPx(context, 4.0f);
        this.f4857c = (int) ScreenUtil.dipToPx(context, 2.0f);
    }

    public NVOverlayConfig getNetverifyOverlayConfiguration(Context context) {
        this.f4864k.drawBrackets = true;
        this.f4864k.dimBackground = true;
        this.f4864k.topLeftCornerRadius = ScreenUtil.dpToPx(context, 15);
        this.f4864k.topRightCornerRadius = this.f4864k.topLeftCornerRadius;
        this.f4864k.bottomLeftCornerRadius = this.f4864k.topLeftCornerRadius;
        this.f4864k.bottomRightCornerRadius = this.f4864k.topLeftCornerRadius;
        return this.f4864k;
    }

    public void calculate(DocumentScanMode documentScanMode, int i, int i2) {
        super.calculate(documentScanMode, i, i2);
        this.f4860f.mo46908a(this.overlayLeftPixel + this.f4859e, (this.overlayTopPixel - this.f4857c) - this.f4858d, this.overlayRightPixel - this.f4859e, this.overlayTopPixel - this.f4858d);
        this.f4861g.mo46908a(this.overlayLeftPixel + this.f4859e, this.overlayBottomPixel + this.f4858d, this.overlayRightPixel - this.f4859e, this.overlayBottomPixel + this.f4857c + this.f4858d);
        this.f4862i.mo46908a((this.overlayLeftPixel - this.f4857c) - this.f4858d, this.overlayTopPixel + this.f4859e, this.overlayLeftPixel - this.f4858d, this.overlayBottomPixel - this.f4859e);
        this.f4863j.mo46908a(this.overlayRightPixel + this.f4858d, this.overlayTopPixel + this.f4859e, this.overlayRightPixel + this.f4857c + this.f4858d, this.overlayBottomPixel - this.f4859e);
    }

    public void prepareDraw(ScanSide scanSide, boolean z, boolean z2) {
        super.prepareDraw(scanSide, z, z2);
        TypedValue typedValue = new TypedValue();
        Theme theme = this.mContext.getTheme();
        theme.resolveAttribute(C4430R.attr.netverify_scanOverlayDetectedLine, typedValue, true);
        this.f4855a = new Paint(1);
        this.f4855a.setStrokeWidth(0.0f);
        this.f4855a.setStrokeJoin(Join.ROUND);
        this.f4855a.setStrokeCap(Cap.ROUND);
        this.f4855a.setPathEffect(new CornerPathEffect(1.0f));
        this.f4855a.setStyle(Style.FILL);
        this.f4855a.setColor(typedValue.data);
        this.f4855a.setAlpha(128);
        theme.resolveAttribute(C4430R.attr.netverify_scanOverlayDetectedLineStroke, typedValue, true);
        this.f4856b = new Paint(1);
        this.f4856b.setStrokeWidth(0.0f);
        this.f4856b.setStrokeJoin(Join.ROUND);
        this.f4856b.setStrokeCap(Cap.ROUND);
        this.f4856b.setPathEffect(new CornerPathEffect(1.0f));
        this.f4856b.setStyle(Style.STROKE);
        this.f4856b.setColor(typedValue.data);
        this.f4856b.setAlpha(128);
        this.mTextOverlayView.setText(NVStrings.getExternalString(this.mContext, scanSide == ScanSide.FRONT ? NVStrings.USE_FRONT_SIDE_OF_CARD : NVStrings.USE_BACK_SIDE_OF_CARD));
        this.mTextOverlayView.setPosition((float) (((double) (((float) this.f3232w) - this.mTextOverlayView.measureText())) * 0.5d), (float) (this.overlayTopPixel + ((int) ScreenUtil.dipToPx(this.mContext, 30.0f))));
    }

    public void doDraw(Canvas canvas) {
        if (this.mVisibility.get() == 0) {
            super.doDraw(canvas);
            this.f4860f.mo46909a(canvas);
            this.f4861g.mo46909a(canvas);
            this.f4862i.mo46909a(canvas);
            this.f4863j.mo46909a(canvas);
            this.mTextOverlayView.draw(canvas);
        }
    }

    public void update(ExtractionUpdate extractionUpdate) {
        if (extractionUpdate.getData() instanceof C4946v) {
            C4946v vVar = (C4946v) extractionUpdate.getData();
            this.f4860f.mo46910a(vVar.mo46906c());
            this.f4861g.mo46910a(vVar.mo46907d());
            this.f4862i.mo46910a(vVar.mo46905b());
            this.f4863j.mo46910a(vVar.mo46904a());
        }
    }
}
