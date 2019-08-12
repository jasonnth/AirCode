package jumio.p317nv.ocr;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.p311nv.NVOverlay;
import com.jumio.p311nv.NVOverlay.NVOverlayConfig;
import com.jumio.p311nv.gui.PieProgressView;
import com.jumio.p311nv.ocr.C4469R;
import com.jumio.p311nv.ocr.overlay.TemplateMatcherFrameIndicator;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: jumio.nv.ocr.g */
/* compiled from: TemplateMatcherOverlay */
public class C5156g extends NVOverlay {

    /* renamed from: a */
    private final AtomicBoolean f5802a = new AtomicBoolean(false);

    /* renamed from: b */
    private PieProgressView f5803b;

    /* renamed from: c */
    private TemplateMatcherFrameIndicator f5804c;

    /* renamed from: d */
    private Rect f5805d;

    /* renamed from: e */
    private boolean f5806e;

    /* renamed from: f */
    private int f5807f;

    /* renamed from: g */
    private int f5808g;

    public C5156g(Context context) {
        super(context);
        TypedValue typedValue = new TypedValue();
        Theme theme = context.getTheme();
        theme.resolveAttribute(C4469R.attr.netverify_scanOverlayTemplateValid, typedValue, true);
        this.f5807f = typedValue.data;
        theme.resolveAttribute(C4469R.attr.netverify_scanOverlayTemplateInvalid, typedValue, true);
        this.f5808g = typedValue.data;
        this.f5803b = new PieProgressView(context);
        this.f5804c = new TemplateMatcherFrameIndicator(context);
    }

    public void update(ExtractionUpdate extractionUpdate) {
        if (extractionUpdate.getState() == C5153d.f5795a) {
            C5145a aVar = (C5145a) extractionUpdate.getData();
            if (extractionUpdate instanceof C5152c) {
                m3946a(aVar, ((C5152c) extractionUpdate).mo47308a(), false);
            }
        } else if (extractionUpdate.getState() == C5153d.f5796b) {
            this.f5803b.setVisibility(4);
            this.f5803b.setProgress(0);
            this.f5804c.setVisibility(4);
        } else if (extractionUpdate.getState() == C5153d.f5797c) {
            m3946a((C5145a) extractionUpdate.getData(), 100.0f, true);
            if (this.f5804c != null) {
                this.f5804c.mo43624a(null, null, null, null, null);
            }
            if (this.f5803b != null) {
                this.f5803b.setVisibility(8);
            }
        }
    }

    public NVOverlayConfig getNetverifyOverlayConfiguration(Context context) {
        NVOverlayConfig nVOverlayConfig = new NVOverlayConfig();
        nVOverlayConfig.drawBrackets = false;
        nVOverlayConfig.dimBackground = false;
        return nVOverlayConfig;
    }

    public void addViews(ViewGroup viewGroup) {
        if (this.f5802a.compareAndSet(false, true)) {
            int dipToPx = (int) ScreenUtil.dipToPx(this.mContext, 80.0f);
            this.f5803b.setLayoutParams(new LayoutParams(dipToPx, dipToPx));
            this.f5803b.setBorderWidth(2);
            this.f5803b.setVisibility(8);
            viewGroup.addView(this.f5803b);
            this.f5804c.setLayoutParams(new LayoutParams(-1, -1));
            viewGroup.addView(this.f5804c);
        }
    }

    public void setVisible(int i) {
        super.setVisible(i);
        if (i != 0) {
            this.f5803b.setVisibility(i);
            this.f5804c.setVisibility(i);
        }
    }

    public void prepareDraw(ScanSide scanSide, boolean z, boolean z2) {
        super.prepareDraw(scanSide, z, z2);
        this.f5806e = z;
    }

    public void calculate(DocumentScanMode documentScanMode, int i, int i2) {
        super.calculate(documentScanMode, i, i2);
        int i3 = (int) ((((float) i) - (((float) i) * 0.9f)) / 2.0f);
        int i4 = (int) ((((float) i2) - (((float) i2) * 0.95f)) / 2.0f);
        this.f5805d = new Rect(i3, i4, i - i3, i2 - (i4 * 3));
    }

    public Rect getOverlayBounds() {
        return this.f5805d;
    }

    /* renamed from: a */
    private void m3946a(C5145a aVar, float f, boolean z) {
        boolean z2;
        int i = 0;
        if (this.mVisibility.get() == 0 && aVar.mo47269a()) {
            if (f > 100.0f || z) {
                f = 100.0f;
            }
            PointF s = aVar.mo47289s();
            PointF t = aVar.mo47290t();
            PointF u = aVar.mo47291u();
            PointF v = aVar.mo47292v();
            PointF r = aVar.mo47288r();
            if (this.f5806e) {
                if (!aVar.mo47280j()) {
                    float y = aVar.mo47296y() / 2.0f;
                    s.x = (y - s.x) + y;
                    t.x = (y - t.x) + y;
                    u.x = (y - u.x) + y;
                    v.x = (y - v.x) + y;
                    r.x = y + (y - r.x);
                } else {
                    float z3 = aVar.mo47297z() / 2.0f;
                    s.y = (z3 - s.y) + z3;
                    t.y = (z3 - t.y) + z3;
                    u.y = (z3 - u.y) + z3;
                    v.y = (z3 - v.y) + z3;
                    r.y = z3 + (z3 - r.y);
                }
            }
            if (aVar.mo47273d() || !aVar.mo47272c()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (this.f5804c != null) {
                this.f5804c.setVisibility(0);
                this.f5804c.mo43624a(s, t, u, v, r);
                this.f5804c.setBorderColor(z2 ? this.f5808g : this.f5807f);
            }
            if (z) {
                Log.m1919i("TemplateMatcher", "setPoly: " + s + "/" + t + "/" + u + "/" + v);
            }
            if (this.f5803b != null) {
                PieProgressView pieProgressView = this.f5803b;
                if (z2 && f == 0.0f) {
                    i = 8;
                }
                pieProgressView.setVisibility(i);
                this.f5803b.setPieColor(z2 ? this.f5808g : this.f5807f);
                this.f5803b.setBorderColor(z2 ? this.f5808g : this.f5807f);
                this.f5803b.setProgress((int) f);
                this.f5803b.updatePosition(r);
                this.f5803b.invalidate();
            }
        }
    }
}
