package com.airbnb.lottie;

import java.util.List;
import org.json.JSONObject;

class AnimatableColorValue extends BaseAnimatableValue<Integer, Integer> {

    static final class Factory {
        static AnimatableColorValue newInstance(JSONObject json, LottieComposition composition) {
            Result<Integer> result = AnimatableValueParser.newInstance(json, 1.0f, composition, ColorFactory.INSTANCE).parseJson();
            return new AnimatableColorValue(result.keyframes, (Integer) result.initialValue);
        }
    }

    private AnimatableColorValue(List<Keyframe<Integer>> keyframes, Integer initialValue) {
        super(keyframes, initialValue);
    }

    public KeyframeAnimation<Integer> createAnimation() {
        if (!hasAnimation()) {
            return new StaticKeyframeAnimation(this.initialValue);
        }
        return new ColorKeyframeAnimation(this.keyframes);
    }

    public String toString() {
        return "AnimatableColorValue{initialValue=" + this.initialValue + '}';
    }
}
