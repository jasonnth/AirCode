package com.airbnb.lottie;

import java.util.List;

class FloatKeyframeAnimation extends KeyframeAnimation<Float> {
    FloatKeyframeAnimation(List<Keyframe<Float>> keyframes) {
        super(keyframes);
    }

    /* access modifiers changed from: 0000 */
    public Float getValue(Keyframe<Float> keyframe, float keyframeProgress) {
        if (keyframe.startValue != null && keyframe.endValue != null) {
            return Float.valueOf(MiscUtils.lerp(((Float) keyframe.startValue).floatValue(), ((Float) keyframe.endValue).floatValue(), keyframeProgress));
        }
        throw new IllegalStateException("Missing values for keyframe.");
    }
}
