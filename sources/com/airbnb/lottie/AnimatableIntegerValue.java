package com.airbnb.lottie;

import java.util.List;
import org.json.JSONObject;

class AnimatableIntegerValue extends BaseAnimatableValue<Integer, Integer> {

    static final class Factory {
        static AnimatableIntegerValue newInstance() {
            return new AnimatableIntegerValue();
        }

        static AnimatableIntegerValue newInstance(JSONObject json, LottieComposition composition) {
            Result<Integer> result = AnimatableValueParser.newInstance(json, 1.0f, composition, ValueFactory.INSTANCE).parseJson();
            return new AnimatableIntegerValue(result.keyframes, (Integer) result.initialValue);
        }
    }

    private static class ValueFactory implements com.airbnb.lottie.AnimatableValue.Factory<Integer> {
        /* access modifiers changed from: private */
        public static final ValueFactory INSTANCE = new ValueFactory();

        private ValueFactory() {
        }

        public Integer valueFromObject(Object object, float scale) {
            return Integer.valueOf(Math.round(JsonUtils.valueFromObject(object) * scale));
        }
    }

    private AnimatableIntegerValue() {
        super(Integer.valueOf(100));
    }

    AnimatableIntegerValue(List<Keyframe<Integer>> keyframes, Integer initialValue) {
        super(keyframes, initialValue);
    }

    public KeyframeAnimation<Integer> createAnimation() {
        if (!hasAnimation()) {
            return new StaticKeyframeAnimation(this.initialValue);
        }
        return new IntegerKeyframeAnimation(this.keyframes);
    }

    public Integer getInitialValue() {
        return (Integer) this.initialValue;
    }
}
