package android.support.design.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.support.design.R;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.graphics.drawable.DrawableWrapper;

class ShadowDrawableWrapper extends DrawableWrapper {
    static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    private boolean mAddPaddingForCorners = true;
    final RectF mContentBounds;
    float mCornerRadius;
    final Paint mCornerShadowPaint;
    Path mCornerShadowPath;
    private boolean mDirty = true;
    final Paint mEdgeShadowPaint;
    float mMaxShadowSize;
    private boolean mPrintedShadowClipWarning = false;
    float mRawMaxShadowSize;
    float mRawShadowSize;
    private float mRotation;
    private final int mShadowEndColor;
    private final int mShadowMiddleColor;
    float mShadowSize;
    private final int mShadowStartColor;

    public ShadowDrawableWrapper(Context context, Drawable content, float radius, float shadowSize, float maxShadowSize) {
        super(content);
        this.mShadowStartColor = ContextCompat.getColor(context, R.color.design_fab_shadow_start_color);
        this.mShadowMiddleColor = ContextCompat.getColor(context, R.color.design_fab_shadow_mid_color);
        this.mShadowEndColor = ContextCompat.getColor(context, R.color.design_fab_shadow_end_color);
        this.mCornerShadowPaint = new Paint(5);
        this.mCornerShadowPaint.setStyle(Style.FILL);
        this.mCornerRadius = (float) Math.round(radius);
        this.mContentBounds = new RectF();
        this.mEdgeShadowPaint = new Paint(this.mCornerShadowPaint);
        this.mEdgeShadowPaint.setAntiAlias(false);
        setShadowSize(shadowSize, maxShadowSize);
    }

    private static int toEven(float value) {
        int i = Math.round(value);
        return i % 2 == 1 ? i - 1 : i;
    }

    public void setAddPaddingForCorners(boolean addPaddingForCorners) {
        this.mAddPaddingForCorners = addPaddingForCorners;
        invalidateSelf();
    }

    public void setAlpha(int alpha) {
        super.setAlpha(alpha);
        this.mCornerShadowPaint.setAlpha(alpha);
        this.mEdgeShadowPaint.setAlpha(alpha);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        this.mDirty = true;
    }

    /* access modifiers changed from: 0000 */
    public void setShadowSize(float shadowSize, float maxShadowSize) {
        if (shadowSize < 0.0f || maxShadowSize < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float shadowSize2 = (float) toEven(shadowSize);
        float maxShadowSize2 = (float) toEven(maxShadowSize);
        if (shadowSize2 > maxShadowSize2) {
            shadowSize2 = maxShadowSize2;
            if (!this.mPrintedShadowClipWarning) {
                this.mPrintedShadowClipWarning = true;
            }
        }
        if (this.mRawShadowSize != shadowSize2 || this.mRawMaxShadowSize != maxShadowSize2) {
            this.mRawShadowSize = shadowSize2;
            this.mRawMaxShadowSize = maxShadowSize2;
            this.mShadowSize = (float) Math.round(1.5f * shadowSize2);
            this.mMaxShadowSize = maxShadowSize2;
            this.mDirty = true;
            invalidateSelf();
        }
    }

    public boolean getPadding(Rect padding) {
        int vOffset = (int) Math.ceil((double) calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        int hOffset = (int) Math.ceil((double) calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        padding.set(hOffset, vOffset, hOffset, vOffset);
        return true;
    }

    public static float calculateVerticalPadding(float maxShadowSize, float cornerRadius, boolean addPaddingForCorners) {
        if (addPaddingForCorners) {
            return (float) (((double) (1.5f * maxShadowSize)) + ((1.0d - COS_45) * ((double) cornerRadius)));
        }
        return 1.5f * maxShadowSize;
    }

    public static float calculateHorizontalPadding(float maxShadowSize, float cornerRadius, boolean addPaddingForCorners) {
        if (addPaddingForCorners) {
            return (float) (((double) maxShadowSize) + ((1.0d - COS_45) * ((double) cornerRadius)));
        }
        return maxShadowSize;
    }

    public int getOpacity() {
        return -3;
    }

    public void draw(Canvas canvas) {
        if (this.mDirty) {
            buildComponents(getBounds());
            this.mDirty = false;
        }
        drawShadow(canvas);
        super.draw(canvas);
    }

    /* access modifiers changed from: 0000 */
    public final void setRotation(float rotation) {
        if (this.mRotation != rotation) {
            this.mRotation = rotation;
            invalidateSelf();
        }
    }

    private void drawShadow(Canvas canvas) {
        int rotateSaved = canvas.save();
        canvas.rotate(this.mRotation, this.mContentBounds.centerX(), this.mContentBounds.centerY());
        float edgeShadowTop = (-this.mCornerRadius) - this.mShadowSize;
        float shadowOffset = this.mCornerRadius;
        boolean drawHorizontalEdges = this.mContentBounds.width() - (2.0f * shadowOffset) > 0.0f;
        boolean drawVerticalEdges = this.mContentBounds.height() - (2.0f * shadowOffset) > 0.0f;
        float shadowScaleHorizontal = shadowOffset / (shadowOffset + (this.mRawShadowSize - (this.mRawShadowSize * 0.5f)));
        float shadowScaleTop = shadowOffset / (shadowOffset + (this.mRawShadowSize - (this.mRawShadowSize * 0.25f)));
        float shadowScaleBottom = shadowOffset / (shadowOffset + (this.mRawShadowSize - (this.mRawShadowSize * 1.0f)));
        int saved = canvas.save();
        canvas.translate(this.mContentBounds.left + shadowOffset, this.mContentBounds.top + shadowOffset);
        canvas.scale(shadowScaleHorizontal, shadowScaleTop);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas.scale(1.0f / shadowScaleHorizontal, 1.0f);
            canvas.drawRect(0.0f, edgeShadowTop, this.mContentBounds.width() - (2.0f * shadowOffset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved);
        int saved2 = canvas.save();
        canvas.translate(this.mContentBounds.right - shadowOffset, this.mContentBounds.bottom - shadowOffset);
        canvas.scale(shadowScaleHorizontal, shadowScaleBottom);
        canvas.rotate(180.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawHorizontalEdges) {
            canvas.scale(1.0f / shadowScaleHorizontal, 1.0f);
            canvas.drawRect(0.0f, edgeShadowTop, this.mContentBounds.width() - (2.0f * shadowOffset), this.mShadowSize + (-this.mCornerRadius), this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved2);
        int saved3 = canvas.save();
        canvas.translate(this.mContentBounds.left + shadowOffset, this.mContentBounds.bottom - shadowOffset);
        canvas.scale(shadowScaleHorizontal, shadowScaleBottom);
        canvas.rotate(270.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawVerticalEdges) {
            canvas.scale(1.0f / shadowScaleBottom, 1.0f);
            canvas.drawRect(0.0f, edgeShadowTop, this.mContentBounds.height() - (2.0f * shadowOffset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved3);
        int saved4 = canvas.save();
        canvas.translate(this.mContentBounds.right - shadowOffset, this.mContentBounds.top + shadowOffset);
        canvas.scale(shadowScaleHorizontal, shadowScaleTop);
        canvas.rotate(90.0f);
        canvas.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if (drawVerticalEdges) {
            canvas.scale(1.0f / shadowScaleTop, 1.0f);
            canvas.drawRect(0.0f, edgeShadowTop, this.mContentBounds.height() - (2.0f * shadowOffset), -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas.restoreToCount(saved4);
        canvas.restoreToCount(rotateSaved);
    }

    private void buildShadowCorners() {
        RectF innerBounds = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
        RectF outerBounds = new RectF(innerBounds);
        outerBounds.inset(-this.mShadowSize, -this.mShadowSize);
        if (this.mCornerShadowPath == null) {
            this.mCornerShadowPath = new Path();
        } else {
            this.mCornerShadowPath.reset();
        }
        this.mCornerShadowPath.setFillType(FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(outerBounds, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(innerBounds, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        float shadowRadius = -outerBounds.top;
        if (shadowRadius > 0.0f) {
            float startRatio = this.mCornerRadius / shadowRadius;
            float midRatio = startRatio + ((1.0f - startRatio) / 2.0f);
            this.mCornerShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, shadowRadius, new int[]{0, this.mShadowStartColor, this.mShadowMiddleColor, this.mShadowEndColor}, new float[]{0.0f, startRatio, midRatio, 1.0f}, TileMode.CLAMP));
        }
        this.mEdgeShadowPaint.setShader(new LinearGradient(0.0f, innerBounds.top, 0.0f, outerBounds.top, new int[]{this.mShadowStartColor, this.mShadowMiddleColor, this.mShadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, TileMode.CLAMP));
        this.mEdgeShadowPaint.setAntiAlias(false);
    }

    private void buildComponents(Rect bounds) {
        float verticalOffset = this.mRawMaxShadowSize * 1.5f;
        this.mContentBounds.set(((float) bounds.left) + this.mRawMaxShadowSize, ((float) bounds.top) + verticalOffset, ((float) bounds.right) - this.mRawMaxShadowSize, ((float) bounds.bottom) - verticalOffset);
        getWrappedDrawable().setBounds((int) this.mContentBounds.left, (int) this.mContentBounds.top, (int) this.mContentBounds.right, (int) this.mContentBounds.bottom);
        buildShadowCorners();
    }

    public void setShadowSize(float size) {
        setShadowSize(size, this.mRawMaxShadowSize);
    }

    public float getShadowSize() {
        return this.mRawShadowSize;
    }
}
