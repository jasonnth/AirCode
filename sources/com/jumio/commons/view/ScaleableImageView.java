package com.jumio.commons.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class ScaleableImageView extends View {
    private float borderRadius;
    private RectF drawableRect;
    private int imageHeight = 0;
    private Paint imagePaint;
    private int imageWidth = 0;
    private Matrix matrix;
    private BitmapShader shader;

    public ScaleableImageView(Context context) {
        super(context);
        init();
    }

    public ScaleableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScaleableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.imagePaint = new Paint();
        this.imagePaint.setAntiAlias(true);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean widthVariable;
        boolean heightVariable = false;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == Integer.MIN_VALUE || widthMode == 0) {
            widthVariable = true;
        } else {
            widthVariable = false;
        }
        if (heightMode == Integer.MIN_VALUE || heightMode == 0) {
            heightVariable = true;
        }
        if (widthVariable && this.imageWidth != 0 && widthSize < this.imageWidth) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.imageWidth, 1073741824);
        }
        if (heightVariable && this.imageHeight != 0 && heightSize < this.imageHeight) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.imageHeight, 1073741824);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        setScalingMatrix();
    }

    public void setImageBitmap(Bitmap bitmap) {
        setImageBitmap(bitmap, 0.0f);
    }

    public void setImageBitmap(Bitmap bitmap, float borderRadius2) {
        this.imageWidth = bitmap.getWidth();
        this.imageHeight = bitmap.getHeight();
        this.borderRadius = borderRadius2;
        setScalingMatrix();
        this.shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        this.imagePaint.setShader(this.shader);
        requestLayout();
        invalidate();
    }

    private void setScalingMatrix() {
        this.matrix = new Matrix();
        this.drawableRect = new RectF(0.0f, 0.0f, (float) this.imageWidth, (float) this.imageHeight);
        this.matrix.setRectToRect(this.drawableRect, new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), ScaleToFit.CENTER);
    }

    public void onDraw(Canvas canvas) {
        canvas.save();
        canvas.concat(this.matrix);
        canvas.drawRoundRect(this.drawableRect, this.borderRadius, this.borderRadius, this.imagePaint);
        canvas.restore();
    }
}
