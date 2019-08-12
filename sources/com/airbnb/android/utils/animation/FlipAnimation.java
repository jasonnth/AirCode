package com.airbnb.android.utils.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FlipAnimation extends Animation {
    public static final float SCALE_DEFAULT = 0.65f;
    private Camera mCamera;
    private final float mCenterX;
    private final float mCenterY;
    private final float mFromDegrees;
    private final float mToDegrees;
    private final float scale;
    private final ScaleUpDownEnum scaleType;

    public enum ScaleUpDownEnum {
        SCALE_UP,
        SCALE_DOWN,
        SCALE_CYCLE,
        SCALE_NONE;

        public float getScale(float max, float iter) {
            switch (this) {
                case SCALE_UP:
                    return ((1.0f - max) * iter) + max;
                case SCALE_DOWN:
                    return 1.0f - ((1.0f - max) * iter);
                case SCALE_CYCLE:
                    if (((double) iter) > 0.5d) {
                        return ((1.0f - max) * (iter - 0.5f) * 2.0f) + max;
                    }
                    return 1.0f - ((1.0f - max) * (iter * 2.0f));
                default:
                    return 1.0f;
            }
        }
    }

    public FlipAnimation(float fromDegrees, float toDegrees, float centerX, float centerY, float scale2, ScaleUpDownEnum scaleType2) {
        this.mFromDegrees = fromDegrees;
        this.mToDegrees = toDegrees;
        this.mCenterX = centerX;
        this.mCenterY = centerY;
        if (scale2 <= 0.0f || scale2 >= 1.0f) {
            scale2 = 0.65f;
        }
        this.scale = scale2;
        if (scaleType2 == null) {
            scaleType2 = ScaleUpDownEnum.SCALE_CYCLE;
        }
        this.scaleType = scaleType2;
    }

    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.mCamera = new Camera();
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float interpolatedTime, Transformation t) {
        float fromDegrees = this.mFromDegrees;
        float degrees = fromDegrees + ((this.mToDegrees - fromDegrees) * interpolatedTime);
        float centerX = this.mCenterX;
        float centerY = this.mCenterY;
        Camera camera = this.mCamera;
        Matrix matrix = t.getMatrix();
        camera.save();
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        matrix.preScale(this.scaleType.getScale(this.scale, interpolatedTime), this.scaleType.getScale(this.scale, interpolatedTime), centerX, centerY);
    }
}
