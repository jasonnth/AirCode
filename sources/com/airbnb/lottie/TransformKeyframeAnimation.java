package com.airbnb.lottie;

import android.graphics.Matrix;
import android.graphics.PointF;

class TransformKeyframeAnimation {
    private final BaseKeyframeAnimation<?, PointF> anchorPoint;
    private final Matrix matrix = new Matrix();
    private final BaseKeyframeAnimation<?, Integer> opacity;
    private final BaseKeyframeAnimation<?, PointF> position;
    private final BaseKeyframeAnimation<?, Float> rotation;
    private final BaseKeyframeAnimation<?, ScaleXY> scale;

    TransformKeyframeAnimation(AnimatableTransform animatableTransform) {
        this.anchorPoint = animatableTransform.getAnchorPoint().createAnimation();
        this.position = animatableTransform.getPosition().createAnimation();
        this.scale = animatableTransform.getScale().createAnimation();
        this.rotation = animatableTransform.getRotation().createAnimation();
        this.opacity = animatableTransform.getOpacity().createAnimation();
    }

    /* access modifiers changed from: 0000 */
    public void addAnimationsToLayer(BaseLayer layer) {
        layer.addAnimation(this.anchorPoint);
        layer.addAnimation(this.position);
        layer.addAnimation(this.scale);
        layer.addAnimation(this.rotation);
        layer.addAnimation(this.opacity);
    }

    /* access modifiers changed from: 0000 */
    public void addListener(AnimationListener listener) {
        this.anchorPoint.addUpdateListener(listener);
        this.position.addUpdateListener(listener);
        this.scale.addUpdateListener(listener);
        this.rotation.addUpdateListener(listener);
        this.opacity.addUpdateListener(listener);
    }

    /* access modifiers changed from: 0000 */
    public BaseKeyframeAnimation<?, Integer> getOpacity() {
        return this.opacity;
    }

    /* access modifiers changed from: 0000 */
    public Matrix getMatrix() {
        this.matrix.reset();
        PointF position2 = (PointF) this.position.getValue();
        if (!(position2.x == 0.0f && position2.y == 0.0f)) {
            this.matrix.preTranslate(position2.x, position2.y);
        }
        float rotation2 = ((Float) this.rotation.getValue()).floatValue();
        if (rotation2 != 0.0f) {
            this.matrix.preRotate(rotation2);
        }
        ScaleXY scaleTransform = (ScaleXY) this.scale.getValue();
        if (!(scaleTransform.getScaleX() == 1.0f && scaleTransform.getScaleY() == 1.0f)) {
            this.matrix.preScale(scaleTransform.getScaleX(), scaleTransform.getScaleY());
        }
        PointF anchorPoint2 = (PointF) this.anchorPoint.getValue();
        if (!(anchorPoint2.x == 0.0f && anchorPoint2.y == 0.0f)) {
            this.matrix.preTranslate(-anchorPoint2.x, -anchorPoint2.y);
        }
        return this.matrix;
    }
}
