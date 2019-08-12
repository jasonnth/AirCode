package com.jumio.p311nv;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.ViewGroup;
import com.airbnb.android.airmapview.AirMapInterface;
import com.jumio.commons.utils.DrawingUtil;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.core.data.document.DocumentFormat;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.overlay.Overlay;
import com.jumio.p311nv.gui.TextOverlayView;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.jumio.nv.NVOverlay */
public abstract class NVOverlay implements Overlay {
    private static final int FONT_ALPHA = 178;
    private static final int TEXT_OVERLAY_SIZE_IN_DP = 18;
    protected static final int TEXT_OVERLAY_TOP_MARGIN_IN_DP = 30;
    protected Paint backgroundPaint;
    private Path backgroundPath;
    protected Paint borderPaint;
    private Path borderPath;

    /* renamed from: h */
    protected int f3231h = 0;
    protected int leftOffset = 0;
    public Context mContext;
    private boolean mInitialized = false;
    public TextOverlayView mTextOverlayView;
    public final AtomicInteger mVisibility = new AtomicInteger();
    protected int overlayBottomMargin = 0;
    public int overlayBottomPixel = 0;
    protected int overlayHeight = 0;
    protected int overlayLeftMargin = 0;
    public int overlayLeftPixel = 0;
    protected int overlayRightMargin = 0;
    public int overlayRightPixel = 0;
    protected int overlayTopMargin = 0;
    public int overlayTopPixel = 0;
    protected int overlayWidth = 0;
    protected DocumentScanMode scanMode = null;
    protected int topOffset = 0;

    /* renamed from: w */
    public int f3232w = 0;

    /* renamed from: com.jumio.nv.NVOverlay$NVOverlayConfig */
    public class NVOverlayConfig {
        public int bottomLeftCornerRadius;
        public int bottomRightCornerRadius;
        public boolean dimBackground;
        public boolean drawBrackets;
        public int topLeftCornerRadius;
        public int topRightCornerRadius;

        public NVOverlayConfig() {
        }
    }

    public abstract NVOverlayConfig getNetverifyOverlayConfiguration(Context context);

    public NVOverlay(Context context) {
        this.mContext = context;
        this.mTextOverlayView = new TextOverlayView();
        this.mTextOverlayView.setColor(-1);
        this.mTextOverlayView.setDropShadow(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.mTextOverlayView.setStyle(Style.FILL);
        this.mTextOverlayView.setAlpha(178);
        this.mTextOverlayView.setTextSize(ScreenUtil.dipToPx(context, 18.0f));
        this.mTextOverlayView.setTypeface(Typeface.defaultFromStyle(3));
    }

    public void calculate(DocumentScanMode documentScanMode, int i, int i2) {
        this.f3232w = i;
        this.f3231h = i2;
        this.scanMode = documentScanMode;
        if (((float) i) / ((float) i2) >= 1.3333334f) {
            this.overlayHeight = i2;
            this.overlayWidth = (this.overlayHeight * 4) / 3;
            this.topOffset = 0;
            this.leftOffset = (i - this.overlayWidth) / 2;
        } else {
            this.overlayWidth = i;
            this.overlayHeight = (this.overlayWidth * 3) / 4;
            this.topOffset = (i2 - this.overlayHeight) / 2;
            this.leftOffset = 0;
        }
        DocumentFormat format = documentScanMode.getFormat();
        this.overlayLeftMargin = format.getOverlayLeftInPx(this.overlayWidth);
        this.overlayRightMargin = format.getOverlayRightInPx(this.overlayWidth);
        this.overlayTopMargin = format.getOverlayTopInPx(this.overlayHeight);
        this.overlayBottomMargin = format.getOverlayBottomInPx(this.overlayHeight);
        this.overlayRightPixel = i - (this.overlayRightMargin + this.leftOffset);
        this.overlayBottomPixel = i2 - (this.overlayBottomMargin + this.topOffset);
        this.overlayLeftPixel = this.overlayLeftMargin + this.leftOffset;
        this.overlayTopPixel = this.overlayTopMargin + this.topOffset;
    }

    public void addViews(ViewGroup viewGroup) {
    }

    public void prepareDraw(ScanSide scanSide, boolean z, boolean z2) {
        NVOverlayConfig netverifyOverlayConfiguration = getNetverifyOverlayConfiguration(this.mContext);
        TypedValue typedValue = new TypedValue();
        Theme theme = this.mContext.getTheme();
        theme.resolveAttribute(C4430R.attr.netverify_scanOverlay, typedValue, true);
        this.borderPaint = new Paint();
        this.borderPaint.setColor(typedValue.data);
        this.borderPaint.setStyle(Style.STROKE);
        this.borderPaint.setDither(true);
        this.borderPaint.setStrokeJoin(Join.ROUND);
        this.borderPaint.setStrokeCap(Cap.ROUND);
        this.borderPaint.setAntiAlias(true);
        this.borderPaint.setStrokeWidth((float) ScreenUtil.dpToPx(this.mContext, 2));
        theme.resolveAttribute(C4430R.attr.netverify_scanBackground, typedValue, true);
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setColor(typedValue.data);
        this.backgroundPaint.setStyle(Style.FILL);
        this.backgroundPaint.setDither(true);
        this.backgroundPaint.setStrokeJoin(Join.ROUND);
        this.backgroundPaint.setStrokeCap(Cap.ROUND);
        this.backgroundPaint.setAntiAlias(true);
        if (netverifyOverlayConfiguration.drawBrackets) {
            this.borderPath = DrawingUtil.createRectWithRoundedCornersAsPath(getOverlayBounds(), netverifyOverlayConfiguration.topLeftCornerRadius, netverifyOverlayConfiguration.topRightCornerRadius, netverifyOverlayConfiguration.bottomLeftCornerRadius, netverifyOverlayConfiguration.bottomRightCornerRadius);
        }
        if (netverifyOverlayConfiguration.dimBackground) {
            this.backgroundPath = new Path();
            this.backgroundPath.setFillType(FillType.EVEN_ODD);
            this.backgroundPath.addRect(0.0f, 0.0f, (float) this.f3232w, (float) this.f3231h, Direction.CW);
            this.backgroundPath.addPath(this.borderPath);
        }
        this.mInitialized = true;
    }

    public Rect getOverlayBounds() {
        return new Rect(this.overlayLeftPixel, this.overlayTopPixel, this.overlayRightPixel, this.overlayBottomPixel);
    }

    public void setVisible(int i) {
        this.mVisibility.set(i);
    }

    public void doDraw(Canvas canvas) {
        if (this.mVisibility.get() == 0 && this.mInitialized) {
            if (this.borderPath != null) {
                canvas.drawPath(this.borderPath, this.borderPaint);
            }
            if (this.backgroundPath != null) {
                canvas.drawPath(this.backgroundPath, this.backgroundPaint);
            }
        }
    }
}
