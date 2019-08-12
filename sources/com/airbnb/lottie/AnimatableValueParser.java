package com.airbnb.lottie;

import com.airbnb.lottie.AnimatableValue.Factory;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class AnimatableValueParser<T> {
    private final LottieComposition composition;
    private final JSONObject json;
    private final float scale;
    private final Factory<T> valueFactory;

    static class Result<T> {
        final T initialValue;
        final List<Keyframe<T>> keyframes;

        Result(List<Keyframe<T>> keyframes2, T initialValue2) {
            this.keyframes = keyframes2;
            this.initialValue = initialValue2;
        }
    }

    private AnimatableValueParser(JSONObject json2, float scale2, LottieComposition composition2, Factory<T> valueFactory2) {
        this.json = json2;
        this.scale = scale2;
        this.composition = composition2;
        this.valueFactory = valueFactory2;
    }

    static <T> AnimatableValueParser<T> newInstance(JSONObject json2, float scale2, LottieComposition composition2, Factory<T> valueFactory2) {
        return new AnimatableValueParser<>(json2, scale2, composition2, valueFactory2);
    }

    /* access modifiers changed from: 0000 */
    public Result<T> parseJson() {
        List<Keyframe<T>> keyframes = parseKeyframes();
        return new Result<>(keyframes, parseInitialValue(keyframes));
    }

    private List<Keyframe<T>> parseKeyframes() {
        if (this.json == null) {
            return Collections.emptyList();
        }
        Object k = this.json.opt("k");
        if (hasKeyframes(k)) {
            return Factory.parseKeyframes((JSONArray) k, this.composition, this.scale, this.valueFactory);
        }
        return Collections.emptyList();
    }

    private T parseInitialValue(List<Keyframe<T>> keyframes) {
        if (this.json == null) {
            return null;
        }
        if (!keyframes.isEmpty()) {
            return ((Keyframe) keyframes.get(0)).startValue;
        }
        return this.valueFactory.valueFromObject(this.json.opt("k"), this.scale);
    }

    private static boolean hasKeyframes(Object json2) {
        if (!(json2 instanceof JSONArray)) {
            return false;
        }
        Object firstObject = ((JSONArray) json2).opt(0);
        if (!(firstObject instanceof JSONObject) || !((JSONObject) firstObject).has("t")) {
            return false;
        }
        return true;
    }
}
