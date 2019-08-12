package com.jumio.commons.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.airbnb.android.airmapview.AirMapInterface;
import java.util.Stack;

public class SVGImageView extends View {
    private Paint mPaint;
    private Matrix matrix;
    private RectF maxBounds;
    int maxHeight;
    int maxWidth;
    private int paintColor = AirMapInterface.CIRCLE_BORDER_COLOR;
    private Stack<Path> pathStack;
    private ScaleToFit scaleToFitMatrix = ScaleToFit.CENTER;
    private RectF screenCanvas;

    public SVGImageView(Context context) {
        super(context);
        init();
    }

    public SVGImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.pathStack = new Stack<>();
        this.matrix = new Matrix();
        this.screenCanvas = new RectF();
        this.maxWidth = 0;
        this.maxHeight = 0;
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setColor(this.paintColor);
        setLayerType(1, this.mPaint);
    }

    public void setPathStringStack(Stack<String> pathStringStack) {
        this.pathStack.clear();
        for (int i = 0; i < pathStringStack.size(); i++) {
            this.pathStack.add(SVGParser.createPathFromSvgString((String) pathStringStack.get(i)));
        }
        this.maxBounds = getMaxBoundsFromPaths();
        invalidate();
    }

    public void setPathString(String pathString) {
        this.pathStack.clear();
        this.pathStack.add(SVGParser.createPathFromSvgString(pathString));
        this.maxBounds = getMaxBoundsFromPaths();
        invalidate();
    }

    public void setPaintColor(int color) {
        this.mPaint.setColor(color);
        invalidate();
    }

    public int getPaintColor() {
        return this.mPaint.getColor();
    }

    public void setScaleMatrixType(ScaleToFit scaleToFitMatrix2) {
        this.scaleToFitMatrix = scaleToFitMatrix2;
        invalidate();
    }

    public ScaleToFit getScaleType() {
        return this.scaleToFitMatrix;
    }

    public void setMaxWidth(int maxWidth2) {
        this.maxWidth = maxWidth2;
    }

    public void setMaxHeight(int maxHeight2) {
        this.maxHeight = maxHeight2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == 1073741824) {
            width = widthSize;
        } else if (widthMode != Integer.MIN_VALUE) {
            width = this.maxWidth;
        } else if (this.maxWidth != 0) {
            width = Math.min(this.maxWidth, widthSize);
        } else {
            width = widthSize;
        }
        if (heightMode == 1073741824) {
            height = heightSize;
        } else if (heightMode != Integer.MIN_VALUE) {
            height = this.maxHeight;
        } else if (this.maxHeight != 0) {
            height = Math.min(this.maxHeight, heightSize);
        } else {
            height = heightSize;
        }
        setMeasuredDimension(width, height);
        this.screenCanvas.left = (float) getPaddingLeft();
        this.screenCanvas.top = (float) getPaddingTop();
        this.screenCanvas.right = (float) (getMeasuredWidth() - getPaddingRight());
        this.screenCanvas.bottom = (float) (getMeasuredHeight() - getPaddingBottom());
        setCanvasBounds(this.screenCanvas);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawToCanvas(canvas);
    }

    public void setCanvasBounds(RectF canvasBounds) {
        if (canvasBounds != null) {
            this.screenCanvas.set(canvasBounds);
        }
        if (this.maxBounds != null && canvasBounds != null) {
            this.matrix.setRectToRect(this.maxBounds, canvasBounds, this.scaleToFitMatrix);
        }
    }

    public void drawToCanvas(Canvas canvas) {
        for (int i = 0; i < this.pathStack.size(); i++) {
            Path transformedPath = new Path();
            ((Path) this.pathStack.get(i)).transform(this.matrix, transformedPath);
            canvas.drawPath(transformedPath, this.mPaint);
        }
    }

    private RectF getMaxBoundsFromPaths() {
        RectF boundsBase = new RectF();
        RectF boundsToCompare = new RectF();
        ((Path) this.pathStack.get(0)).computeBounds(boundsBase, true);
        for (int i = 1; i < this.pathStack.size(); i++) {
            ((Path) this.pathStack.get(i)).computeBounds(boundsToCompare, false);
            boundsBase = mergeRectsToMaxBoundRect(boundsBase, boundsToCompare);
        }
        return boundsBase;
    }

    private RectF mergeRectsToMaxBoundRect(RectF rect1, RectF rect2) {
        if (rect2.left < rect1.left) {
            rect1.left = rect2.left;
        }
        if (rect2.right > rect1.right) {
            rect1.right = rect2.right;
        }
        if (rect2.top < rect1.top) {
            rect1.top = rect2.top;
        }
        if (rect2.bottom > rect1.bottom) {
            rect1.bottom = rect2.bottom;
        }
        return rect1;
    }
}
