package com.airbnb.lottie;

import android.graphics.PointF;
import java.util.Collections;

class SplitDimensionPathKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PointF point = new PointF();
    private final KeyframeAnimation<Float> xAnimation;
    private final KeyframeAnimation<Float> yAnimation;

    SplitDimensionPathKeyframeAnimation(KeyframeAnimation<Float> xAnimation2, KeyframeAnimation<Float> yAnimation2) {
        super(Collections.emptyList());
        this.xAnimation = xAnimation2;
        this.yAnimation = yAnimation2;
    }

    /* access modifiers changed from: 0000 */
    public void setProgress(float progress) {
        this.xAnimation.setProgress(progress);
        this.yAnimation.setProgress(progress);
        this.point.set(((Float) this.xAnimation.getValue()).floatValue(), ((Float) this.yAnimation.getValue()).floatValue());
        for (int i = 0; i < this.listeners.size(); i++) {
            ((AnimationListener) this.listeners.get(i)).onValueChanged();
        }
    }

    public PointF getValue() {
        return getValue(null, 0.0f);
    }

    /* access modifiers changed from: 0000 */
    public PointF getValue(Keyframe<PointF> keyframe, float keyframeProgress) {
        return this.point;
    }
}
