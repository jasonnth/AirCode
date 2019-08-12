package com.airbnb.lottie;

import java.util.List;

class ScaleKeyframeAnimation extends KeyframeAnimation<ScaleXY> {
    ScaleKeyframeAnimation(List<Keyframe<ScaleXY>> keyframes) {
        super(keyframes);
    }

    public ScaleXY getValue(Keyframe<ScaleXY> keyframe, float keyframeProgress) {
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        ScaleXY startTransform = (ScaleXY) keyframe.startValue;
        ScaleXY endTransform = (ScaleXY) keyframe.endValue;
        return new ScaleXY(MiscUtils.lerp(startTransform.getScaleX(), endTransform.getScaleX(), keyframeProgress), MiscUtils.lerp(startTransform.getScaleY(), endTransform.getScaleY(), keyframeProgress));
    }
}
