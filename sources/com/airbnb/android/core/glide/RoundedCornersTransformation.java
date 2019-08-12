package com.airbnb.android.core.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class RoundedCornersTransformation extends BitmapTransformation {
    private final BitmapPool bitmapPool;
    private final int borderColor;
    private final Paint borderPaint;
    private final int borderWidth;
    private final CornerType cornerType;
    private final int diameter;
    private final int radius;
    private final Paint roundRectPaint;

    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    public RoundedCornersTransformation(Context context, int radius2, int borderWidth2, int borderColor2) {
        this(context, radius2, borderWidth2, borderColor2, CornerType.ALL);
    }

    public RoundedCornersTransformation(Context context, int radius2, int borderWidth2, int borderColor2, CornerType cornerType2) {
        this(Glide.get(context).getBitmapPool(), radius2, borderWidth2, borderColor2, cornerType2);
    }

    public RoundedCornersTransformation(BitmapPool pool, int radius2, int borderWidth2, int borderColor2, CornerType cornerType2) {
        super(pool);
        this.borderPaint = new Paint(1);
        this.roundRectPaint = new Paint(1);
        this.diameter = radius2 * 2;
        this.bitmapPool = pool;
        this.radius = radius2;
        this.borderWidth = borderWidth2;
        this.borderColor = borderColor2;
        this.cornerType = cornerType2;
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool pool, Bitmap source, int outWidth, int outHeight) {
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap bitmap = this.bitmapPool.get(width, height, Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        this.borderPaint.setStyle(Style.STROKE);
        this.borderPaint.setColor(this.borderColor);
        this.borderPaint.setStrokeWidth((float) this.borderWidth);
        this.roundRectPaint.setShader(new BitmapShader(source, TileMode.CLAMP, TileMode.CLAMP));
        drawRoundRect(canvas, this.roundRectPaint, (float) width, (float) height);
        drawBorder(canvas, this.borderPaint, width, height);
        return bitmap;
    }

    private void drawBorder(Canvas canvas, Paint paint, int width, int height) {
        canvas.drawRoundRect(new RectF((float) (this.borderWidth / 2), (float) (this.borderWidth / 2), (float) (width - (this.borderWidth / 2)), (float) (height - (this.borderWidth / 2))), (float) this.radius, (float) this.radius, paint);
    }

    private void drawRoundRect(Canvas canvas, Paint paint, float width, float height) {
        float right = width - ((float) this.borderWidth);
        float bottom = height - ((float) this.borderWidth);
        switch (this.cornerType) {
            case ALL:
                canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right, bottom), (float) this.radius, (float) this.radius, paint);
                return;
            case TOP_LEFT:
                drawTopLeftRoundRect(canvas, paint, right, bottom);
                return;
            case TOP_RIGHT:
                drawTopRightRoundRect(canvas, paint, right, bottom);
                return;
            case BOTTOM_LEFT:
                drawBottomLeftRoundRect(canvas, paint, right, bottom);
                return;
            case BOTTOM_RIGHT:
                drawBottomRightRoundRect(canvas, paint, right, bottom);
                return;
            case TOP:
                drawTopRoundRect(canvas, paint, right, bottom);
                return;
            case BOTTOM:
                drawBottomRoundRect(canvas, paint, right, bottom);
                return;
            case LEFT:
                drawLeftRoundRect(canvas, paint, right, bottom);
                return;
            case RIGHT:
                drawRightRoundRect(canvas, paint, right, bottom);
                return;
            case OTHER_TOP_LEFT:
                drawOtherTopLeftRoundRect(canvas, paint, right, bottom);
                return;
            case OTHER_TOP_RIGHT:
                drawOtherTopRightRoundRect(canvas, paint, right, bottom);
                return;
            case OTHER_BOTTOM_LEFT:
                drawOtherBottomLeftRoundRect(canvas, paint, right, bottom);
                return;
            case OTHER_BOTTOM_RIGHT:
                drawOtherBottomRightRoundRect(canvas, paint, right, bottom);
                return;
            case DIAGONAL_FROM_TOP_LEFT:
                drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom);
                return;
            case DIAGONAL_FROM_TOP_RIGHT:
                drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom);
                return;
            default:
                canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right, bottom), (float) this.radius, (float) this.radius, paint);
                return;
        }
    }

    private void drawTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, (float) (this.borderWidth + this.diameter), (float) (this.borderWidth + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) (this.borderWidth + this.radius), (float) (this.borderWidth + this.radius), bottom), paint);
        canvas.drawRect(new RectF((float) (this.borderWidth + this.radius), (float) this.borderWidth, right, bottom), paint);
    }

    private void drawTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - ((float) this.diameter), (float) this.borderWidth, right, (float) (this.borderWidth + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right - ((float) this.radius), bottom), paint);
        canvas.drawRect(new RectF(right - ((float) this.radius), (float) (this.borderWidth + this.radius), right, bottom), paint);
    }

    private void drawBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, bottom - ((float) this.diameter), (float) (this.borderWidth + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) this.borderWidth, (float) (this.borderWidth + this.diameter), bottom - ((float) this.radius)), paint);
        canvas.drawRect(new RectF((float) (this.borderWidth + this.radius), (float) this.borderWidth, right, bottom), paint);
    }

    private void drawBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - ((float) this.diameter), bottom - ((float) this.diameter), right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right - ((float) this.radius), bottom), paint);
        canvas.drawRect(new RectF(right - ((float) this.radius), (float) this.borderWidth, right, bottom - ((float) this.radius)), paint);
    }

    private void drawTopRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right, (float) (this.borderWidth + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) (this.borderWidth + this.radius), right, bottom), paint);
    }

    private void drawBottomRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, bottom - ((float) this.diameter), right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right, bottom - ((float) this.radius)), paint);
    }

    private void drawLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, (float) (this.borderWidth + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.borderWidth + this.radius), (float) this.borderWidth, right, bottom), paint);
    }

    private void drawRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - ((float) this.diameter), (float) this.borderWidth, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right - ((float) this.radius), bottom), paint);
    }

    private void drawOtherTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, bottom - ((float) this.diameter), right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(right - ((float) this.diameter), (float) this.borderWidth, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right - ((float) this.radius), bottom - ((float) this.radius)), paint);
    }

    private void drawOtherTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, (float) (this.borderWidth + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.borderWidth, bottom - ((float) this.diameter), right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.borderWidth + this.radius), (float) this.borderWidth, right, bottom - ((float) this.radius)), paint);
    }

    private void drawOtherBottomLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right, (float) (this.borderWidth + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(right - ((float) this.diameter), (float) this.borderWidth, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) (this.borderWidth + this.radius), right - ((float) this.radius), bottom), paint);
    }

    private void drawOtherBottomRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right, (float) (this.borderWidth + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, (float) (this.borderWidth + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.borderWidth + this.radius), (float) (this.borderWidth + this.radius), right, bottom), paint);
    }

    private void drawDiagonalFromTopLeftRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF((float) this.borderWidth, (float) this.borderWidth, (float) (this.borderWidth + this.diameter), (float) (this.borderWidth + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(right - ((float) this.diameter), bottom - ((float) this.diameter), right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) (this.borderWidth + this.radius), right - ((float) this.diameter), bottom), paint);
        canvas.drawRect(new RectF((float) (this.borderWidth + this.diameter), (float) this.borderWidth, right, bottom - ((float) this.radius)), paint);
    }

    private void drawDiagonalFromTopRightRoundRect(Canvas canvas, Paint paint, float right, float bottom) {
        canvas.drawRoundRect(new RectF(right - ((float) this.diameter), (float) this.borderWidth, right, (float) (this.borderWidth + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.borderWidth, bottom - ((float) this.diameter), (float) (this.borderWidth + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.borderWidth, (float) this.borderWidth, right - ((float) this.radius), bottom - ((float) this.radius)), paint);
        canvas.drawRect(new RectF((float) (this.borderWidth + this.radius), (float) (this.borderWidth + this.radius), right, bottom), paint);
    }

    public String getId() {
        return "RoundedTransformation(radius=" + this.radius + ", margin=" + this.borderWidth + ", diameter=" + this.diameter + ", cornerType=" + this.cornerType.name() + ")";
    }
}
