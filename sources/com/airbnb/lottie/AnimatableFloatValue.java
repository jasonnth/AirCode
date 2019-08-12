package com.airbnb.lottie;

import java.util.List;
import org.json.JSONObject;

class AnimatableFloatValue extends BaseAnimatableValue<Float, Float> {

    static final class Factory {
        static AnimatableFloatValue newInstance() {
            return new AnimatableFloatValue();
        }

        static AnimatableFloatValue newInstance(JSONObject json, LottieComposition composition) {
            return newInstance(json, composition, true);
        }

        static AnimatableFloatValue newInstance(JSONObject json, LottieComposition composition, boolean isDp) {
            Result<Float> result = AnimatableValueParser.newInstance(json, isDp ? composition.getDpScale() : 1.0f, composition, ValueFactory.INSTANCE).parseJson();
            return new AnimatableFloatValue(result.keyframes, (Float) result.initialValue);
        }
    }

    private static class ValueFactory implements com.airbnb.lottie.AnimatableValue.Factory<Float> {
        static final ValueFactory INSTANCE = new ValueFactory();

        private ValueFactory() {
        }

        public Float valueFromObject(Object object, float scale) {
            return Float.valueOf(JsonUtils.valueFromObject(object) * scale);
        }
    }

    private AnimatableFloatValue() {
        super(Float.valueOf(0.0f));
    }

    private AnimatableFloatValue(List<Keyframe<Float>> keyframes, Float initialValue) {
        super(keyframes, initialValue);
    }

    public KeyframeAnimation<Float> createAnimation() {
        if (!hasAnimation()) {
            return new StaticKeyframeAnimation(this.initialValue);
        }
        return new FloatKeyframeAnimation(this.keyframes);
    }

    public Float getInitialValue() {
        return (Float) this.initialValue;
    }
}
