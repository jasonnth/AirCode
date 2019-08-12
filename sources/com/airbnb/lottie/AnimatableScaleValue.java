package com.airbnb.lottie;

import java.util.List;
import org.json.JSONObject;

class AnimatableScaleValue extends BaseAnimatableValue<ScaleXY, ScaleXY> {

    static final class Factory {
        static AnimatableScaleValue newInstance(JSONObject json, LottieComposition composition) {
            Result<ScaleXY> result = AnimatableValueParser.newInstance(json, 1.0f, composition, Factory.INSTANCE).parseJson();
            return new AnimatableScaleValue(result.keyframes, (ScaleXY) result.initialValue);
        }

        static AnimatableScaleValue newInstance() {
            return new AnimatableScaleValue();
        }
    }

    private AnimatableScaleValue() {
        super(new ScaleXY());
    }

    AnimatableScaleValue(List<Keyframe<ScaleXY>> keyframes, ScaleXY initialValue) {
        super(keyframes, initialValue);
    }

    public KeyframeAnimation<ScaleXY> createAnimation() {
        if (!hasAnimation()) {
            return new StaticKeyframeAnimation(this.initialValue);
        }
        return new ScaleKeyframeAnimation(this.keyframes);
    }
}
