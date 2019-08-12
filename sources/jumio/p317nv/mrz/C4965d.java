package jumio.p317nv.mrz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.ViewGroup;
import com.airbnb.android.airmapview.AirMapInterface;
import com.jumio.commons.utils.DrawingUtil;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.commons.view.SVGImageView;
import com.jumio.core.data.document.DocumentFormat;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.p311nv.NVOverlay;
import com.jumio.p311nv.NVOverlay.NVOverlayConfig;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.gui.TextOverlayView;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;

/* renamed from: jumio.nv.mrz.d */
/* compiled from: MrzOverlay */
public class C4965d extends NVOverlay {

    /* renamed from: a */
    private Paint f4922a;

    /* renamed from: b */
    private Rect f4923b;

    /* renamed from: c */
    private Path f4924c;

    /* renamed from: d */
    private SVGImageView f4925d = null;

    /* renamed from: e */
    private RectF f4926e = null;

    /* renamed from: f */
    private TextOverlayView f4927f = null;

    /* renamed from: g */
    private NVOverlayConfig f4928g = new NVOverlayConfig();

    public C4965d(Context context) {
        super(context);
    }

    public void calculate(DocumentScanMode documentScanMode, int i, int i2) {
        int overlayRatio;
        int i3 = 0;
        super.calculate(documentScanMode, i, i2);
        DocumentFormat format = documentScanMode.getFormat();
        if (documentScanMode == DocumentScanMode.MRP || documentScanMode == DocumentScanMode.MRV) {
            boolean z = ((float) i) / ((float) i2) >= 1.3333334f;
            this.overlayLeftMargin = format.getOverlayLeftInPx(i);
            this.overlayRightMargin = format.getOverlayRightInPx(i);
            this.overlayTopMargin = format.getOverlayTopInPx(i2);
            this.overlayBottomMargin = format.getOverlayBottomInPx(i2);
            if (z) {
                this.topOffset = 0;
                overlayRatio = (i2 - this.overlayTopMargin) - this.overlayBottomMargin;
                this.leftOffset = (i - ((((int) (((double) overlayRatio) * format.getOverlayRatio())) + this.overlayLeftMargin) + this.overlayRightMargin)) / 2;
            } else {
                this.leftOffset = 0;
                overlayRatio = ((int) (((double) ((i - this.overlayLeftMargin) - this.overlayRightMargin)) / format.getOverlayRatio())) + this.overlayTopMargin + this.overlayBottomMargin;
                i3 = (i2 - overlayRatio) / 2;
                this.topOffset = i3;
            }
            this.overlayWidth = i - (this.leftOffset * 2);
            this.overlayHeight = (i2 - this.topOffset) - i3;
            this.overlayRightPixel = (i - this.leftOffset) - this.overlayRightMargin;
            this.overlayBottomPixel = (i2 - i3) - this.overlayBottomMargin;
            this.overlayLeftPixel = this.overlayLeftMargin + this.leftOffset;
            this.overlayTopPixel = this.overlayTopMargin + this.topOffset;
            this.f4923b = new Rect(this.overlayLeftPixel, this.overlayBottomPixel - ((int) (format.getRoiHeight() * ((double) overlayRatio))), this.overlayRightPixel, this.overlayBottomPixel);
            return;
        }
        int roiMarginBottomPx = this.overlayBottomPixel - format.getRoiMarginBottomPx(this.overlayHeight);
        this.f4923b = new Rect(this.overlayLeftPixel, roiMarginBottomPx - format.getRoiHeightInPx(this.overlayHeight), this.overlayRightPixel, roiMarginBottomPx);
    }

    public void addViews(ViewGroup viewGroup) {
        viewGroup.setLayerType(1, null);
    }

    public void prepareDraw(ScanSide scanSide, boolean z, boolean z2) {
        super.prepareDraw(scanSide, z, z2);
        int dipToPx = (int) ScreenUtil.dipToPx(this.mContext, 15.0f);
        int dipToPx2 = (int) ScreenUtil.dipToPx(this.mContext, 2.0f);
        int dipToPx3 = (int) ScreenUtil.dipToPx(this.mContext, 1.916f);
        this.f4922a = new Paint();
        this.f4922a.setColor(this.borderPaint.getColor());
        this.f4922a.setStyle(Style.FILL_AND_STROKE);
        this.f4922a.setDither(true);
        this.f4922a.setAntiAlias(true);
        if (this.scanMode == DocumentScanMode.MRP || this.scanMode == DocumentScanMode.MRV) {
            this.f4925d = new SVGImageView(this.mContext);
            this.f4925d.setPathString("M20 2276 l0 -194 31 -26 c41 -35 129 -60 339 -96 199 -35 272 -53 294 -76 13 -13 16 -42 16 -160 0 -139 -1 -147 -25 -178 -29 -38 -110 -208 -121 -251 -4 -16 -12 -37 -19 -45 -40 -47 -70 -261 -42 -303 13 -21 14 -39 6 -126 -30 -321 80 -490 353 -545 102 -21 150 -20 270 4 119 25 201 63 251 119 78 87 100 174 95 383 -1 80 0 150 5 154 28 28 -17 233 -68 308 -14 20 -25 43 -25 51 0 32 -62 204 -85 239 -24 35 -25 43 -25 191 0 149 1 155 23 169 30 19 103 39 277 76 178 37 261 64 300 97 l30 25 0 189 0 189 -940 0 -940 0 0 -194z");
            this.f4925d.setPaintColor(this.f4922a.getColor());
            this.f4924c = DrawingUtil.createRectWithRoundedCornersAsPath(new Rect(this.overlayLeftPixel + (dipToPx2 / 2), this.f4923b.top, this.overlayRightPixel - (dipToPx2 / 2), this.overlayBottomPixel - (dipToPx2 / 2)), 0, 0, this.f4928g.bottomLeftCornerRadius - (dipToPx2 / 2), this.f4928g.bottomRightCornerRadius - (dipToPx2 / 2));
            int dipToPx4 = (int) ScreenUtil.dipToPx(this.mContext, 43.0f);
            int dipToPx5 = (int) ScreenUtil.dipToPx(this.mContext, 10.0f);
            int dipToPx6 = (int) ScreenUtil.dipToPx(this.mContext, 26.0f);
            int dipToPx7 = (int) ScreenUtil.dipToPx(this.mContext, 100.0f);
            int dipToPx8 = (int) ScreenUtil.dipToPx(this.mContext, 120.0f);
            this.f4927f = new TextOverlayView();
            this.f4927f.setColor(-1);
            this.f4927f.setDropShadow(AirMapInterface.CIRCLE_BORDER_COLOR);
            this.f4927f.setStyle(Style.FILL);
            this.f4927f.setTextSize(ScreenUtil.dipToPx(this.mContext, 20.0f));
            this.f4927f.setTypeface(Typeface.defaultFromStyle(1));
            this.f4927f.setText(NVStrings.getExternalString(this.mContext, this.scanMode == DocumentScanMode.MRV ? NVStrings.OVERLAY_VISA : NVStrings.OVERLAY_PASSPORT));
            if (z) {
                this.f4926e = new RectF((float) ((this.overlayRightPixel - dipToPx5) - dipToPx7), (float) (this.overlayTopPixel + dipToPx4), (float) (this.overlayRightPixel - dipToPx5), (float) (dipToPx4 + dipToPx8 + this.overlayTopPixel));
            } else {
                this.f4926e = new RectF((float) (this.overlayLeftPixel + dipToPx5), (float) (this.overlayTopPixel + dipToPx4), (float) (this.overlayLeftPixel + dipToPx7 + dipToPx5), (float) (dipToPx4 + dipToPx8 + this.overlayTopPixel));
            }
            int i = this.overlayTopPixel + dipToPx6;
            float measureText = this.f4927f.measureText();
            if (measureText <= ((float) (dipToPx5 * 2)) + this.f4926e.width()) {
                this.f4927f.setPosition(this.f4926e.left + ((((float) dipToPx7) - this.f4927f.measureText()) / 2.0f), (float) i);
            } else if (z) {
                this.f4927f.setPosition(this.f4926e.right - measureText, (float) i);
            } else {
                this.f4927f.setPosition(this.f4926e.left, (float) i);
            }
            this.f4925d.setCanvasBounds(this.f4926e);
            return;
        }
        DocumentFormat format = this.scanMode.getFormat();
        int roiMarginBottomPx = this.overlayBottomPixel - format.getRoiMarginBottomPx(this.overlayHeight);
        int roiHeightInPx = roiMarginBottomPx - format.getRoiHeightInPx(this.overlayHeight);
        int i2 = dipToPx2 / 2;
        int i3 = this.scanMode == DocumentScanMode.TD1 ? 3 : 2;
        this.f4924c = DrawingUtil.createRectWithRoundedCornersAsPath(new Rect(this.overlayLeftPixel + i2, (roiHeightInPx - i2) - format.getRoiMarginBottomPx(this.overlayHeight), this.overlayRightPixel - i2, format.getRoiMarginBottomPx(this.overlayHeight) + (roiMarginBottomPx - i2)), 0, 0, this.f4928g.bottomLeftCornerRadius - i2, this.f4928g.bottomRightCornerRadius - i2);
        this.f4924c.setFillType(FillType.EVEN_ODD);
        float f = ((float) (this.f4923b.bottom - this.f4923b.top)) / ((float) i3);
        for (int i4 = 1; i4 <= i3; i4++) {
            float f2 = ((float) this.f4923b.top) + (((float) i4) * f);
            this.f4924c.addRect((float) (this.f4923b.left + dipToPx), f2 - (((float) dipToPx3) / 2.0f), (float) (this.f4923b.right - dipToPx), f2 + (((float) dipToPx3) / 2.0f), Direction.CCW);
        }
    }

    public void doDraw(Canvas canvas) {
        super.doDraw(canvas);
        if (this.mVisibility.get() == 0) {
            canvas.drawPath(this.f4924c, this.f4922a);
            if (this.scanMode == DocumentScanMode.MRP || this.scanMode == DocumentScanMode.MRV) {
                this.f4927f.draw(canvas);
                this.f4925d.drawToCanvas(canvas);
            }
        }
    }

    public void update(ExtractionUpdate extractionUpdate) {
    }

    /* renamed from: a */
    public Rect mo46933a() {
        return this.f4923b;
    }

    public NVOverlayConfig getNetverifyOverlayConfiguration(Context context) {
        this.f4928g.dimBackground = true;
        this.f4928g.drawBrackets = true;
        if (this.scanMode == DocumentScanMode.MRP || this.scanMode == DocumentScanMode.MRV) {
            this.f4928g.topLeftCornerRadius = 0;
            this.f4928g.topRightCornerRadius = 0;
            this.f4928g.bottomLeftCornerRadius = ScreenUtil.dpToPx(context, 5);
            this.f4928g.bottomRightCornerRadius = this.f4928g.bottomLeftCornerRadius;
        } else {
            this.f4928g.topLeftCornerRadius = ScreenUtil.dpToPx(context, 15);
            this.f4928g.topRightCornerRadius = this.f4928g.topLeftCornerRadius;
            this.f4928g.bottomLeftCornerRadius = this.f4928g.topLeftCornerRadius;
            this.f4928g.bottomRightCornerRadius = this.f4928g.topLeftCornerRadius;
        }
        return this.f4928g;
    }
}
