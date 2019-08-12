package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ARTShapeShadowNode extends ARTVirtualNode {
    private static final int CAP_BUTT = 0;
    private static final int CAP_ROUND = 1;
    private static final int CAP_SQUARE = 2;
    private static final int JOIN_BEVEL = 2;
    private static final int JOIN_MITER = 0;
    private static final int JOIN_ROUND = 1;
    private static final int PATH_TYPE_ARC = 4;
    private static final int PATH_TYPE_CLOSE = 1;
    private static final int PATH_TYPE_CURVETO = 3;
    private static final int PATH_TYPE_LINETO = 2;
    private static final int PATH_TYPE_MOVETO = 0;
    private float[] mFillColor;
    protected Path mPath;
    private int mStrokeCap = 1;
    private float[] mStrokeColor;
    private float[] mStrokeDash;
    private int mStrokeJoin = 1;
    private float mStrokeWidth = 1.0f;

    @ReactProp(name = "d")
    public void setShapePath(ReadableArray shapePath) {
        this.mPath = createPath(PropHelper.toFloatArray(shapePath));
        markUpdated();
    }

    @ReactProp(name = "stroke")
    public void setStroke(ReadableArray strokeColors) {
        this.mStrokeColor = PropHelper.toFloatArray(strokeColors);
        markUpdated();
    }

    @ReactProp(name = "strokeDash")
    public void setStrokeDash(ReadableArray strokeDash) {
        this.mStrokeDash = PropHelper.toFloatArray(strokeDash);
        markUpdated();
    }

    @ReactProp(name = "fill")
    public void setFill(ReadableArray fillColors) {
        this.mFillColor = PropHelper.toFloatArray(fillColors);
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(float strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeCap")
    public void setStrokeCap(int strokeCap) {
        this.mStrokeCap = strokeCap;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeJoin")
    public void setStrokeJoin(int strokeJoin) {
        this.mStrokeJoin = strokeJoin;
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        float opacity2 = opacity * this.mOpacity;
        if (opacity2 > 0.01f) {
            saveAndSetupCanvas(canvas);
            if (this.mPath == null) {
                throw new JSApplicationIllegalArgumentException("Shapes should have a valid path (d) prop");
            }
            if (setupFillPaint(paint, opacity2)) {
                canvas.drawPath(this.mPath, paint);
            }
            if (setupStrokePaint(paint, opacity2)) {
                canvas.drawPath(this.mPath, paint);
            }
            restoreCanvas(canvas);
        }
        markUpdateSeen();
    }

    /* access modifiers changed from: protected */
    public boolean setupStrokePaint(Paint paint, float opacity) {
        if (this.mStrokeWidth == 0.0f || this.mStrokeColor == null || this.mStrokeColor.length == 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.STROKE);
        switch (this.mStrokeCap) {
            case 0:
                paint.setStrokeCap(Cap.BUTT);
                break;
            case 1:
                paint.setStrokeCap(Cap.ROUND);
                break;
            case 2:
                paint.setStrokeCap(Cap.SQUARE);
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeCap " + this.mStrokeCap + " unrecognized");
        }
        switch (this.mStrokeJoin) {
            case 0:
                paint.setStrokeJoin(Join.MITER);
                break;
            case 1:
                paint.setStrokeJoin(Join.ROUND);
                break;
            case 2:
                paint.setStrokeJoin(Join.BEVEL);
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeJoin " + this.mStrokeJoin + " unrecognized");
        }
        paint.setStrokeWidth(this.mStrokeWidth * this.mScale);
        paint.setARGB((int) (this.mStrokeColor.length > 3 ? this.mStrokeColor[3] * opacity * 255.0f : opacity * 255.0f), (int) (this.mStrokeColor[0] * 255.0f), (int) (this.mStrokeColor[1] * 255.0f), (int) (this.mStrokeColor[2] * 255.0f));
        if (this.mStrokeDash != null && this.mStrokeDash.length > 0) {
            paint.setPathEffect(new DashPathEffect(this.mStrokeDash, 0.0f));
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean setupFillPaint(Paint paint, float opacity) {
        if (this.mFillColor == null || this.mFillColor.length <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.FILL);
        int colorType = (int) this.mFillColor[0];
        switch (colorType) {
            case 0:
                paint.setARGB((int) (this.mFillColor.length > 4 ? this.mFillColor[4] * opacity * 255.0f : opacity * 255.0f), (int) (this.mFillColor[1] * 255.0f), (int) (this.mFillColor[2] * 255.0f), (int) (this.mFillColor[3] * 255.0f));
                break;
            default:
                FLog.m1847w(ReactConstants.TAG, "ART: Color type " + colorType + " not supported!");
                break;
        }
        return true;
    }

    private Path createPath(float[] data) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        int i = 0;
        while (i < data.length) {
            int i2 = i + 1;
            int type = (int) data[i];
            switch (type) {
                case 0:
                    int i3 = i2 + 1;
                    int i4 = i3 + 1;
                    path.moveTo(data[i2] * this.mScale, data[i3] * this.mScale);
                    i = i4;
                    break;
                case 1:
                    path.close();
                    i = i2;
                    break;
                case 2:
                    int i5 = i2 + 1;
                    int i6 = i5 + 1;
                    path.lineTo(data[i2] * this.mScale, data[i5] * this.mScale);
                    i = i6;
                    break;
                case 3:
                    int i7 = i2 + 1;
                    int i8 = i7 + 1;
                    int i9 = i8 + 1;
                    int i10 = i9 + 1;
                    int i11 = i10 + 1;
                    int i12 = i11 + 1;
                    path.cubicTo(data[i2] * this.mScale, data[i7] * this.mScale, data[i8] * this.mScale, data[i9] * this.mScale, data[i10] * this.mScale, data[i11] * this.mScale);
                    i = i12;
                    break;
                case 4:
                    int i13 = i2 + 1;
                    float x = data[i2] * this.mScale;
                    int i14 = i13 + 1;
                    float y = data[i13] * this.mScale;
                    int i15 = i14 + 1;
                    float r = data[i14] * this.mScale;
                    int i16 = i15 + 1;
                    float start = (float) Math.toDegrees((double) data[i15]);
                    int i17 = i16 + 1;
                    float end = (float) Math.toDegrees((double) data[i16]);
                    int i18 = i17 + 1;
                    if (!(data[i17] == 0.0f)) {
                        end = 360.0f - end;
                    }
                    path.addArc(new RectF(x - r, y - r, x + r, y + r), start, start - end);
                    i = i18;
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Unrecognized drawing instruction " + type);
            }
        }
        return path;
    }
}
