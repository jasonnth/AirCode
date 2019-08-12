package com.airbnb.lottie;

import java.util.List;

class IntegerKeyframeAnimation extends KeyframeAnimation<Integer> {
    IntegerKeyframeAnimation(List<Keyframe<Integer>> keyframes) {
        super(keyframes);
    }

    /* access modifiers changed from: 0000 */
    public Integer getValue(Keyframe<Integer> keyframe, float keyframeProgress) {
        if (keyframe.startValue != null && keyframe.endValue != null) {
            return Integer.valueOf(MiscUtils.lerp(((Integer) keyframe.startValue).intValue(), ((Integer) keyframe.endValue).intValue(), keyframeProgress));
        }
        throw new IllegalStateException("Missing values for keyframe.");
    }
}
