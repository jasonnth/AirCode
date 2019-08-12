package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Point;
import android.graphics.RectF;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RNSVGPathShadowNode extends RNSVGVirtualNode {
    private ArrayList<String> mChangedList;

    /* renamed from: mD */
    private float[] f1386mD;
    public ReadableArray mFill;
    public float mFillOpacity = 1.0f;
    public FillType mFillRule = FillType.WINDING;
    private boolean mFillRuleSet;
    private ArrayList<Object> mOriginProperties;
    protected WritableArray mOwnedPropList = new JavaOnlyArray();
    protected Path mPath;
    protected ReadableArray mPropList = new JavaOnlyArray();
    public ReadableArray mStroke;
    public float[] mStrokeDasharray;
    public float mStrokeDashoffset = 0.0f;
    public Cap mStrokeLinecap = Cap.ROUND;
    public Join mStrokeLinejoin = Join.ROUND;
    public float mStrokeMiterlimit = 4.0f;
    public float mStrokeOpacity = 1.0f;
    public float mStrokeWidth = 1.0f;

    @ReactProp(name = "d")
    public void setPath(ReadableArray shapePath) {
        this.f1386mD = PropHelper.toFloatArray(shapePath);
        setupPath();
        markUpdated();
    }

    @ReactProp(name = "fill")
    public void setFill(ReadableArray fill) {
        this.mFill = fill;
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
    public void setFillOpacity(float fillOpacity) {
        this.mFillOpacity = fillOpacity;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "fillRule")
    public void setFillRule(int fillRule) {
        switch (fillRule) {
            case 0:
                this.mFillRule = FillType.EVEN_ODD;
                break;
            case 1:
                break;
            default:
                throw new JSApplicationIllegalArgumentException("fillRule " + this.mFillRule + " unrecognized");
        }
        this.mFillRuleSet = true;
        setupPath();
        markUpdated();
    }

    @ReactProp(name = "stroke")
    public void setStroke(ReadableArray strokeColors) {
        this.mStroke = strokeColors;
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
    public void setStrokeOpacity(float strokeOpacity) {
        this.mStrokeOpacity = strokeOpacity;
        markUpdated();
    }

    @ReactProp(name = "strokeDasharray")
    public void setStrokeDasharray(ReadableArray strokeDasharray) {
        this.mStrokeDasharray = PropHelper.toFloatArray(strokeDasharray);
        if (this.mStrokeDasharray != null && this.mStrokeDasharray.length > 0) {
            for (int i = 0; i < this.mStrokeDasharray.length; i++) {
                this.mStrokeDasharray[i] = this.mStrokeDasharray[i] * this.mScale;
            }
        }
        markUpdated();
    }

    @ReactProp(defaultFloat = 0.0f, name = "strokeDashoffset")
    public void setStrokeDashoffset(float strokeWidth) {
        this.mStrokeDashoffset = this.mScale * strokeWidth;
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(float strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        markUpdated();
    }

    @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
    public void setStrokeMiterlimit(float strokeMiterlimit) {
        this.mStrokeMiterlimit = strokeMiterlimit;
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeLinecap")
    public void setStrokeLinecap(int strokeLinecap) {
        switch (strokeLinecap) {
            case 0:
                this.mStrokeLinecap = Cap.BUTT;
                break;
            case 1:
                this.mStrokeLinecap = Cap.ROUND;
                break;
            case 2:
                this.mStrokeLinecap = Cap.SQUARE;
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeLinecap " + this.mStrokeLinecap + " unrecognized");
        }
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeLinejoin")
    public void setStrokeLinejoin(int strokeLinejoin) {
        switch (strokeLinejoin) {
            case 0:
                this.mStrokeLinejoin = Join.MITER;
                break;
            case 1:
                this.mStrokeLinejoin = Join.ROUND;
                break;
            case 2:
                this.mStrokeLinejoin = Join.BEVEL;
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeLinejoin " + this.mStrokeLinejoin + " unrecognized");
        }
        markUpdated();
    }

    @ReactProp(name = "propList")
    public void setPropList(ReadableArray propList) {
        WritableArray copy = new JavaOnlyArray();
        if (propList != null) {
            for (int i = 0; i < propList.size(); i++) {
                String fieldName = propertyNameToFieldName(propList.getString(i));
                copy.pushString(fieldName);
                this.mOwnedPropList.pushString(fieldName);
            }
        }
        this.mPropList = copy;
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        float opacity2 = opacity * this.mOpacity;
        if (opacity2 > 0.01f) {
            int count = saveAndSetupCanvas(canvas);
            if (this.mPath == null) {
                throw new JSApplicationIllegalArgumentException("Paths should have a valid path (d) prop");
            }
            clip(canvas, paint);
            if (setupFillPaint(paint, this.mFillOpacity * opacity2, null)) {
                canvas.drawPath(this.mPath, paint);
            }
            if (setupStrokePaint(paint, this.mStrokeOpacity * opacity2, null)) {
                canvas.drawPath(this.mPath, paint);
            }
            restoreCanvas(canvas, count);
            markUpdateSeen();
        }
    }

    private void setupPath() {
        if (this.mFillRuleSet && this.f1386mD != null) {
            this.mPath = new Path();
            this.mPath.setFillType(this.mFillRule);
            super.createPath(this.f1386mD, this.mPath);
        }
    }

    /* access modifiers changed from: protected */
    public boolean setupFillPaint(Paint paint, float opacity, RectF box) {
        if (this.mFill == null || this.mFill.size() <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Style.FILL);
        setupPaint(paint, opacity, this.mFill, box);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean setupStrokePaint(Paint paint, float opacity, RectF box) {
        paint.reset();
        if (this.mStrokeWidth == 0.0f || this.mStroke == null || this.mStroke.size() == 0) {
            return false;
        }
        paint.setFlags(1);
        paint.setStyle(Style.STROKE);
        paint.setStrokeCap(this.mStrokeLinecap);
        paint.setStrokeJoin(this.mStrokeLinejoin);
        paint.setStrokeMiter(this.mStrokeMiterlimit * this.mScale);
        paint.setStrokeWidth(this.mStrokeWidth * this.mScale);
        setupPaint(paint, opacity, this.mStroke, box);
        if (this.mStrokeDasharray == null || this.mStrokeDasharray.length <= 0) {
            return true;
        }
        paint.setPathEffect(new DashPathEffect(this.mStrokeDasharray, this.mStrokeDashoffset));
        return true;
    }

    private void setupPaint(Paint paint, float opacity, ReadableArray colors, RectF box) {
        int colorType = colors.getInt(0);
        if (colorType == 0) {
            paint.setARGB((int) (colors.size() > 4 ? colors.getDouble(4) * ((double) opacity) * 255.0d : (double) (255.0f * opacity)), (int) (colors.getDouble(1) * 255.0d), (int) (colors.getDouble(2) * 255.0d), (int) (colors.getDouble(3) * 255.0d));
        } else if (colorType == 1) {
            if (box == null) {
                box = new RectF();
                this.mPath.computeBounds(box, true);
            }
            RNSVGBrush brush = getSvgShadowNode().getDefinedBrush(colors.getString(1));
            if (brush != null) {
                brush.setupPaint(paint, box, this.mScale, opacity);
            }
        } else {
            FLog.m1847w(ReactConstants.TAG, "RNSVG: Color type " + colorType + " not supported!");
        }
    }

    /* access modifiers changed from: protected */
    public Path getPath(Canvas canvas, Paint paint) {
        return this.mPath;
    }

    public int hitTest(Point point, Matrix matrix) {
        int i = -1;
        Bitmap bitmap = Bitmap.createBitmap(this.mCanvasWidth, this.mCanvasHeight, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (matrix != null) {
            canvas.concat(matrix);
        }
        canvas.concat(this.mMatrix);
        Paint paint = new Paint();
        clip(canvas, paint);
        setHitTestFill(paint);
        canvas.drawPath(this.mPath, paint);
        if (setHitTestStroke(paint)) {
            canvas.drawPath(this.mPath, paint);
        }
        canvas.setBitmap(bitmap);
        try {
            if (bitmap.getPixel(point.x, point.y) != 0) {
                i = getReactTag();
            } else {
                bitmap.recycle();
            }
        } catch (Exception e) {
        } finally {
            bitmap.recycle();
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void setHitTestFill(Paint paint) {
        paint.reset();
        paint.setARGB(255, 0, 0, 0);
        paint.setFlags(1);
        paint.setStyle(Style.FILL);
    }

    /* access modifiers changed from: protected */
    public boolean setHitTestStroke(Paint paint) {
        if (this.mStrokeWidth == 0.0f) {
            return false;
        }
        paint.reset();
        paint.setARGB(255, 0, 0, 0);
        paint.setFlags(1);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(this.mStrokeWidth * this.mScale);
        paint.setStrokeCap(this.mStrokeLinecap);
        paint.setStrokeJoin(this.mStrokeLinejoin);
        return true;
    }

    public void mergeProperties(RNSVGVirtualNode target, ReadableArray mergeList, boolean inherited) {
        if (mergeList.size() != 0) {
            if (!inherited) {
                this.mOriginProperties = new ArrayList<>();
                this.mChangedList = new ArrayList<>();
            }
            WritableArray propList = new JavaOnlyArray();
            for (int i = 0; i < this.mPropList.size(); i++) {
                propList.pushString(this.mPropList.getString(i));
            }
            this.mOwnedPropList = propList;
            int i2 = 0;
            int size = mergeList.size();
            while (i2 < size) {
                try {
                    String fieldName = mergeList.getString(i2);
                    Field field = getClass().getField(fieldName);
                    Object value = field.get(target);
                    if (!inherited) {
                        this.mOriginProperties.add(field.get(this));
                        this.mChangedList.add(fieldName);
                        field.set(this, value);
                    } else if (!hasOwnProperty(fieldName)) {
                        field.set(this, value);
                        propList.pushString(fieldName);
                    }
                    i2++;
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    public void mergeProperties(RNSVGVirtualNode target, ReadableArray mergeList) {
        mergeProperties(target, mergeList, false);
    }

    public void resetProperties() {
        if (this.mChangedList != null) {
            try {
                for (int i = this.mChangedList.size() - 1; i >= 0; i--) {
                    getClass().getField((String) this.mChangedList.get(i)).set(this, this.mOriginProperties.get(i));
                }
                this.mChangedList = null;
                this.mOriginProperties = null;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private String propertyNameToFieldName(String fieldName) {
        Matcher matched = Pattern.compile("^(\\w)").matcher(fieldName);
        StringBuffer sb = new StringBuffer("m");
        while (matched.find()) {
            matched.appendReplacement(sb, matched.group(1).toUpperCase());
        }
        matched.appendTail(sb);
        return sb.toString();
    }

    private boolean hasOwnProperty(String propName) {
        for (int i = this.mOwnedPropList.size() - 1; i >= 0; i--) {
            if (this.mOwnedPropList.getString(i).equals(propName)) {
                return true;
            }
        }
        return false;
    }
}
