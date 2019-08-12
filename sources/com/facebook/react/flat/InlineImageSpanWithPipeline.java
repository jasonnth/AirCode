package com.facebook.react.flat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.infer.annotation.Assertions;

final class InlineImageSpanWithPipeline extends ReplacementSpan implements AttachDetachListener, BitmapUpdateListener {
    private static final RectF TMP_RECT = new RectF();
    private InvalidateCallback mCallback;
    private boolean mFrozen;
    private float mHeight;
    private PipelineRequestHelper mRequestHelper;
    private float mWidth;

    InlineImageSpanWithPipeline() {
        this(null, Float.NaN, Float.NaN);
    }

    private InlineImageSpanWithPipeline(PipelineRequestHelper requestHelper, float width, float height) {
        this.mRequestHelper = requestHelper;
        this.mWidth = width;
        this.mHeight = height;
    }

    /* access modifiers changed from: 0000 */
    public InlineImageSpanWithPipeline mutableCopy() {
        return new InlineImageSpanWithPipeline(this.mRequestHelper, this.mWidth, this.mHeight);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasImageRequest() {
        return this.mRequestHelper != null;
    }

    /* access modifiers changed from: 0000 */
    public void setImageRequest(ImageRequest imageRequest) {
        if (imageRequest == null) {
            this.mRequestHelper = null;
        } else {
            this.mRequestHelper = new PipelineRequestHelper(imageRequest);
        }
    }

    /* access modifiers changed from: 0000 */
    public float getWidth() {
        return this.mWidth;
    }

    /* access modifiers changed from: 0000 */
    public void setWidth(float width) {
        this.mWidth = width;
    }

    /* access modifiers changed from: 0000 */
    public float getHeight() {
        return this.mHeight;
    }

    /* access modifiers changed from: 0000 */
    public void setHeight(float height) {
        this.mHeight = height;
    }

    /* access modifiers changed from: 0000 */
    public void freeze() {
        this.mFrozen = true;
    }

    /* access modifiers changed from: 0000 */
    public boolean isFrozen() {
        return this.mFrozen;
    }

    public void onSecondaryAttach(Bitmap bitmap) {
        ((InvalidateCallback) Assertions.assumeNotNull(this.mCallback)).invalidate();
    }

    public void onBitmapReady(Bitmap bitmap) {
        ((InvalidateCallback) Assertions.assumeNotNull(this.mCallback)).invalidate();
    }

    public void onImageLoadEvent(int imageLoadEvent) {
    }

    public void onAttached(InvalidateCallback callback) {
        this.mCallback = callback;
        if (this.mRequestHelper != null) {
            this.mRequestHelper.attach(this);
        }
    }

    public void onDetached() {
        if (this.mRequestHelper != null) {
            this.mRequestHelper.detach();
            if (this.mRequestHelper.isDetached()) {
                this.mCallback = null;
            }
        }
    }

    public int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        if (fm != null) {
            fm.ascent = -Math.round(this.mHeight);
            fm.descent = 0;
            fm.top = fm.ascent;
            fm.bottom = 0;
        }
        return Math.round(this.mWidth);
    }

    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        if (this.mRequestHelper != null) {
            Bitmap bitmap = this.mRequestHelper.getBitmap();
            if (bitmap != null) {
                float bottomFloat = ((float) bottom) - ((float) paint.getFontMetricsInt().descent);
                TMP_RECT.set(x, bottomFloat - this.mHeight, this.mWidth + x, bottomFloat);
                canvas.drawBitmap(bitmap, null, TMP_RECT, paint);
            }
        }
    }
}
