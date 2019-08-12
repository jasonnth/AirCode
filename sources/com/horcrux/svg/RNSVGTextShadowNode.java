package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNSVGTextShadowNode extends RNSVGPathShadowNode {
    private ReadableMap mFrame;
    private int mTextAlignment = 0;
    private Path mTextPath;

    @ReactProp(name = "frame")
    public void setFrame(ReadableMap frame) {
        this.mFrame = frame;
        markUpdated();
    }

    @ReactProp(defaultInt = 0, name = "alignment")
    public void setAlignment(int alignment) {
        this.mTextAlignment = alignment;
    }

    @ReactProp(name = "path")
    public void setPath(ReadableArray textPath) {
        float[] pathData = PropHelper.toFloatArray(textPath);
        this.mTextPath = new Path();
        super.createPath(pathData, this.mTextPath);
        markUpdated();
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        float opacity2 = opacity * this.mOpacity;
        if (opacity2 > 0.01f) {
            String text = formatText();
            if (text != null) {
                int count = saveAndSetupCanvas(canvas);
                clip(canvas, paint);
                RectF box = getBox(paint, text);
                if (setupStrokePaint(paint, opacity2, box)) {
                    drawText(canvas, paint, text);
                }
                if (setupFillPaint(paint, opacity2, box)) {
                    drawText(canvas, paint, text);
                }
                restoreCanvas(canvas, count);
                markUpdateSeen();
            }
        }
    }

    private void drawText(Canvas canvas, Paint paint, String text) {
        applyTextPropertiesToPaint(paint);
        if (this.mTextPath == null) {
            canvas.drawText(text, 0.0f, -paint.ascent(), paint);
            return;
        }
        Matrix matrix = new Matrix();
        matrix.setTranslate(0.0f, (-paint.getTextSize()) * 1.1f);
        this.mTextPath.transform(matrix);
        canvas.drawTextOnPath(text, this.mTextPath, 0.0f, -paint.ascent(), paint);
    }

    private String formatText() {
        if (this.mFrame == null || !this.mFrame.hasKey("lines")) {
            return null;
        }
        ReadableArray linesProp = this.mFrame.getArray("lines");
        if (linesProp == null || linesProp.size() == 0) {
            return null;
        }
        String[] lines = new String[linesProp.size()];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = linesProp.getString(i);
        }
        return TextUtils.join("\n", lines);
    }

    private RectF getBox(Paint paint, String text) {
        Rect bound = new Rect();
        paint.getTextBounds(text, 0, text.length(), bound);
        return new RectF(bound);
    }

    private void applyTextPropertiesToPaint(Paint paint) {
        boolean isBold;
        boolean isItalic;
        int fontStyle;
        switch (this.mTextAlignment) {
            case 0:
                paint.setTextAlign(Align.LEFT);
                break;
            case 1:
                paint.setTextAlign(Align.RIGHT);
                break;
            case 2:
                paint.setTextAlign(Align.CENTER);
                break;
        }
        if (this.mFrame != null && this.mFrame.hasKey("font")) {
            ReadableMap font = this.mFrame.getMap("font");
            if (font != null) {
                float fontSize = 12.0f;
                if (font.hasKey(ViewProps.FONT_SIZE)) {
                    fontSize = (float) font.getDouble(ViewProps.FONT_SIZE);
                }
                paint.setTextSize(this.mScale * fontSize);
                if (!font.hasKey(ViewProps.FONT_WEIGHT) || !"bold".equals(font.getString(ViewProps.FONT_WEIGHT))) {
                    isBold = false;
                } else {
                    isBold = true;
                }
                if (!font.hasKey(ViewProps.FONT_STYLE) || !"italic".equals(font.getString(ViewProps.FONT_STYLE))) {
                    isItalic = false;
                } else {
                    isItalic = true;
                }
                if (isBold && isItalic) {
                    fontStyle = 3;
                } else if (isBold) {
                    fontStyle = 1;
                } else if (isItalic) {
                    fontStyle = 2;
                } else {
                    fontStyle = 0;
                }
                paint.setTypeface(Typeface.create(font.getString(ViewProps.FONT_FAMILY), fontStyle));
            }
        }
    }

    /* access modifiers changed from: protected */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        String text = formatText();
        if (text != null && setupFillPaint(paint, 1.0f, getBox(paint, text))) {
            applyTextPropertiesToPaint(paint);
            paint.getTextPath(text, 0, text.length(), 0.0f, -paint.ascent(), path);
            path.transform(this.mMatrix);
        }
        return path;
    }

    public int hitTest(Point point, Matrix matrix) {
        int i = -1;
        Bitmap bitmap = Bitmap.createBitmap(this.mCanvasWidth, this.mCanvasHeight, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (matrix != null) {
            canvas.concat(matrix);
        }
        canvas.concat(this.mMatrix);
        String text = formatText();
        if (text != null) {
            Paint paint = new Paint();
            clip(canvas, paint);
            setHitTestFill(paint);
            drawText(canvas, paint, text);
            if (setHitTestStroke(paint)) {
                drawText(canvas, paint, text);
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
        }
        return i;
    }
}
