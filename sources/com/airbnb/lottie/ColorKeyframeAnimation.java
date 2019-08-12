package com.airbnb.lottie;

import java.util.List;

class ColorKeyframeAnimation extends KeyframeAnimation<Integer> {
    ColorKeyframeAnimation(List<Keyframe<Integer>> keyframes) {
        super(keyframes);
    }

    public Integer getValue(Keyframe<Integer> keyframe, float keyframeProgress) {
        if (keyframe.startValue != null && keyframe.endValue != null) {
            return Integer.valueOf(GammaEvaluator.evaluate(keyframeProgress, ((Integer) keyframe.startValue).intValue(), ((Integer) keyframe.endValue).intValue()));
        }
        throw new IllegalStateException("Missing values for keyframe.");
    }
}
