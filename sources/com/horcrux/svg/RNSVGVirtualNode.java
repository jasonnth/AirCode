package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region.Op;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.annotations.ReactProp;

public abstract class RNSVGVirtualNode extends LayoutShadowNode {
    private static final float[] sMatrixData = new float[9];
    private static final float[] sRawMatrix = new float[9];
    protected int mCanvasHeight;
    protected int mCanvasWidth;
    protected int mCanvasX;
    protected int mCanvasY;
    private float[] mClipData;
    private boolean mClipDataSet;
    protected Path mClipPath;
    protected String mClipPathRef;
    private int mClipRule;
    private boolean mClipRuleSet;
    protected Matrix mMatrix = new Matrix();
    protected String mName;
    protected float mOpacity = 1.0f;
    protected boolean mResponsible;
    protected final float mScale = DisplayMetricsHolder.getScreenDisplayMetrics().density;
    private RNSVGSvgViewShadowNode mSvgShadowNode;

    protected interface NodeRunnable {
        boolean run(RNSVGVirtualNode rNSVGVirtualNode);
    }

    public abstract void draw(Canvas canvas, Paint paint, float f);

    /* access modifiers changed from: protected */
    public abstract Path getPath(Canvas canvas, Paint paint);

    public abstract int hitTest(Point point, Matrix matrix);

    public abstract void mergeProperties(RNSVGVirtualNode rNSVGVirtualNode, ReadableArray readableArray);

    public abstract void mergeProperties(RNSVGVirtualNode rNSVGVirtualNode, ReadableArray readableArray, boolean z);

    public abstract void resetProperties();

    public boolean isVirtual() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final int saveAndSetupCanvas(Canvas canvas) {
        int count = canvas.save();
        canvas.concat(this.mMatrix);
        return count;
    }

    /* access modifiers changed from: protected */
    public void restoreCanvas(Canvas canvas, int count) {
        canvas.restoreToCount(count);
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(ReadableArray clipPath) {
        this.mClipData = PropHelper.toFloatArray(clipPath);
        this.mClipDataSet = true;
        setupClip();
        markUpdated();
    }

    @ReactProp(name = "name")
    public void setName(String name) {
        this.mName = name;
        markUpdated();
    }

    @ReactProp(name = "clipPathRef")
    public void setClipPathRef(String clipPathRef) {
        this.mClipPathRef = clipPathRef;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "clipRule")
    public void setClipRule(int clipRule) {
        this.mClipRule = clipRule;
        this.mClipRuleSet = true;
        setupClip();
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(float opacity) {
        this.mOpacity = opacity;
        markUpdated();
    }

    @ReactProp(name = "matrix")
    public void setMatrix(ReadableArray matrixArray) {
        if (matrixArray != null) {
            int matrixSize = PropHelper.toFloatArray(matrixArray, sMatrixData);
            if (matrixSize == 6) {
                setupMatrix();
            } else if (matrixSize != -1) {
                throw new JSApplicationIllegalArgumentException("Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix = null;
        }
        markUpdated();
    }

    @ReactProp(defaultBoolean = false, name = "responsible")
    public void setResponsible(boolean responsible) {
        this.mResponsible = responsible;
        markUpdated();
    }

    private void setupClip() {
        if (this.mClipDataSet && this.mClipRuleSet) {
            this.mClipPath = new Path();
            switch (this.mClipRule) {
                case 0:
                    this.mClipPath.setFillType(FillType.EVEN_ODD);
                    break;
                case 1:
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("clipRule " + this.mClipRule + " unrecognized");
            }
            createPath(this.mClipData, this.mClipPath);
        }
    }

    /* access modifiers changed from: protected */
    public void setupMatrix() {
        sRawMatrix[0] = sMatrixData[0];
        sRawMatrix[1] = sMatrixData[2];
        sRawMatrix[2] = sMatrixData[4] * this.mScale;
        sRawMatrix[3] = sMatrixData[1];
        sRawMatrix[4] = sMatrixData[3];
        sRawMatrix[5] = sMatrixData[5] * this.mScale;
        sRawMatrix[6] = 0.0f;
        sRawMatrix[7] = 0.0f;
        sRawMatrix[8] = 1.0f;
        this.mMatrix.setValues(sRawMatrix);
    }

    /* access modifiers changed from: protected */
    public void createPath(float[] data, Path path) {
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
                    path.cubicTo(this.mScale * data[i2], this.mScale * data[i7], this.mScale * data[i8], this.mScale * data[i9], this.mScale * data[i10], this.mScale * data[i11]);
                    i = i12;
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Unrecognized drawing instruction " + type);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Path getClipPath(Canvas canvas, Paint paint) {
        Path clip = this.mClipPath;
        if (clip != null || this.mClipPathRef == null) {
            return clip;
        }
        return getSvgShadowNode().getDefinedClipPath(this.mClipPathRef).getPath(canvas, paint);
    }

    /* access modifiers changed from: protected */
    public void clip(Canvas canvas, Paint paint) {
        Path clip = getClipPath(canvas, paint);
        if (clip != null) {
            canvas.clipPath(clip, Op.REPLACE);
        }
    }

    public int hitTest(Point point) {
        return hitTest(point, null);
    }

    public boolean isResponsible() {
        return this.mResponsible;
    }

    /* access modifiers changed from: protected */
    public RNSVGSvgViewShadowNode getSvgShadowNode() {
        if (this.mSvgShadowNode != null) {
            return this.mSvgShadowNode;
        }
        ReactShadowNode parent = getParent();
        while (!(parent instanceof RNSVGSvgViewShadowNode)) {
            if (parent == null) {
                return null;
            }
            parent = parent.getParent();
        }
        this.mSvgShadowNode = (RNSVGSvgViewShadowNode) parent;
        return this.mSvgShadowNode;
    }

    /* access modifiers changed from: protected */
    public void setupDimensions(Canvas canvas) {
        setupDimensions(canvas.getClipBounds());
    }

    /* access modifiers changed from: protected */
    public void setupDimensions(Rect rect) {
        this.mCanvasX = rect.left;
        this.mCanvasY = rect.top;
        this.mCanvasWidth = rect.width();
        this.mCanvasHeight = rect.height();
    }

    /* access modifiers changed from: protected */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgShadowNode().defineTemplate(this, this.mName);
        }
    }

    /* access modifiers changed from: protected */
    public void traverseChildren(NodeRunnable runner) {
        int i = 0;
        while (i < getChildCount()) {
            ReactShadowNode child = getChildAt(i);
            if (!(child instanceof RNSVGVirtualNode) || runner.run((RNSVGVirtualNode) child)) {
                i++;
            } else {
                return;
            }
        }
    }
}
