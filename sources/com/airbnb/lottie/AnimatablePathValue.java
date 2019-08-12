package com.airbnb.lottie;

import android.graphics.PointF;
import com.airbnb.lottie.AnimatableValue.Factory;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class AnimatablePathValue implements AnimatableValue<PointF> {
    private PointF initialPoint;
    private final List<PathKeyframe> keyframes;

    private static class ValueFactory implements Factory<PointF> {
        /* access modifiers changed from: private */
        public static final Factory<PointF> INSTANCE = new ValueFactory();

        private ValueFactory() {
        }

        public PointF valueFromObject(Object object, float scale) {
            return JsonUtils.pointFromJsonArray((JSONArray) object, scale);
        }
    }

    static AnimatableValue<PointF> createAnimatablePathOrSplitDimensionPath(JSONObject json, LottieComposition composition) {
        if (json.has("k")) {
            return new AnimatablePathValue(json.opt("k"), composition);
        }
        return new AnimatableSplitDimensionPathValue(Factory.newInstance(json.optJSONObject("x"), composition), Factory.newInstance(json.optJSONObject("y"), composition));
    }

    AnimatablePathValue() {
        this.keyframes = new ArrayList();
        this.initialPoint = new PointF(0.0f, 0.0f);
    }

    AnimatablePathValue(Object json, LottieComposition composition) {
        this.keyframes = new ArrayList();
        if (hasKeyframes(json)) {
            JSONArray jsonArray = (JSONArray) json;
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                this.keyframes.add(Factory.newInstance(jsonArray.optJSONObject(i), composition, ValueFactory.INSTANCE));
            }
            Keyframe.setEndFrames(this.keyframes);
            return;
        }
        this.initialPoint = JsonUtils.pointFromJsonArray((JSONArray) json, composition.getDpScale());
    }

    private boolean hasKeyframes(Object json) {
        if (!(json instanceof JSONArray)) {
            return false;
        }
        Object firstObject = ((JSONArray) json).opt(0);
        if (!(firstObject instanceof JSONObject) || !((JSONObject) firstObject).has("t")) {
            return false;
        }
        return true;
    }

    public BaseKeyframeAnimation<?, PointF> createAnimation() {
        if (!hasAnimation()) {
            return new StaticKeyframeAnimation(this.initialPoint);
        }
        return new PathKeyframeAnimation(this.keyframes);
    }

    public boolean hasAnimation() {
        return !this.keyframes.isEmpty();
    }

    public String toString() {
        return "initialPoint=" + this.initialPoint;
    }
}
