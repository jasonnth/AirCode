package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathEffect;
import android.graphics.RectF;
import com.airbnb.android.airmapview.AirMapInterface;

abstract class AbstractDrawBorder extends AbstractDrawCommand {
    private static final int BORDER_PATH_DIRTY = 1;
    private static final Paint PAINT = new Paint(1);
    private static final RectF TMP_RECT = new RectF();
    private int mBorderColor = AirMapInterface.CIRCLE_BORDER_COLOR;
    private float mBorderRadius;
    private float mBorderWidth;
    private Path mPathForBorderRadius;
    private int mSetPropertiesFlag;

    AbstractDrawBorder() {
    }

    static {
        PAINT.setStyle(Style.STROKE);
    }

    public final void setBorderWidth(float borderWidth) {
        this.mBorderWidth = borderWidth;
        setFlag(1);
    }

    public final float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderRadius(float borderRadius) {
        this.mBorderRadius = borderRadius;
        setFlag(1);
    }

    public final float getBorderRadius() {
        return this.mBorderRadius;
    }

    public final void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
    }

    public final int getBorderColor() {
        return this.mBorderColor;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChanged() {
        setFlag(1);
    }

    /* access modifiers changed from: protected */
    public final void drawBorders(Canvas canvas) {
        if (this.mBorderWidth >= 0.5f && this.mBorderColor != 0) {
            PAINT.setColor(this.mBorderColor);
            PAINT.setStrokeWidth(this.mBorderWidth);
            PAINT.setPathEffect(getPathEffectForBorderStyle());
            canvas.drawPath(getPathForBorderRadius(), PAINT);
        }
    }

    /* access modifiers changed from: protected */
    public final void updatePath(Path path, float correction) {
        path.reset();
        TMP_RECT.set(getLeft() + correction, getTop() + correction, getRight() - correction, getBottom() - correction);
        path.addRoundRect(TMP_RECT, this.mBorderRadius, this.mBorderRadius, Direction.CW);
    }

    /* access modifiers changed from: protected */
    public PathEffect getPathEffectForBorderStyle() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean isFlagSet(int mask) {
        return (this.mSetPropertiesFlag & mask) == mask;
    }

    /* access modifiers changed from: protected */
    public final void setFlag(int mask) {
        this.mSetPropertiesFlag |= mask;
    }

    /* access modifiers changed from: protected */
    public final void resetFlag(int mask) {
        this.mSetPropertiesFlag &= mask ^ -1;
    }

    /* access modifiers changed from: protected */
    public final Path getPathForBorderRadius() {
        if (isFlagSet(1)) {
            if (this.mPathForBorderRadius == null) {
                this.mPathForBorderRadius = new Path();
            }
            updatePath(this.mPathForBorderRadius, this.mBorderWidth * 0.5f);
            resetFlag(1);
        }
        return this.mPathForBorderRadius;
    }
}
