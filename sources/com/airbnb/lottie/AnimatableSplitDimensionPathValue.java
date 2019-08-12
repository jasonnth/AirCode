package com.airbnb.lottie;

import android.graphics.PointF;

class AnimatableSplitDimensionPathValue implements AnimatableValue<PointF> {
    private final AnimatableFloatValue animatableXDimension;
    private final AnimatableFloatValue animatableYDimension;

    AnimatableSplitDimensionPathValue(AnimatableFloatValue animatableXDimension2, AnimatableFloatValue animatableYDimension2) {
        this.animatableXDimension = animatableXDimension2;
        this.animatableYDimension = animatableYDimension2;
    }

    public KeyframeAnimation<PointF> createAnimation() {
        return new SplitDimensionPathKeyframeAnimation(this.animatableXDimension.createAnimation(), this.animatableYDimension.createAnimation());
    }
}
