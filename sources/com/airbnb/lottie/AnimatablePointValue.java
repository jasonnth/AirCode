package com.airbnb.lottie;

import android.graphics.PointF;
import java.util.List;
import org.json.JSONObject;

class AnimatablePointValue extends BaseAnimatableValue<PointF, PointF> {

    static final class Factory {
        static AnimatablePointValue newInstance(JSONObject json, LottieComposition composition) {
            Result<PointF> result = AnimatableValueParser.newInstance(json, composition.getDpScale(), composition, PointFFactory.INSTANCE).parseJson();
            return new AnimatablePointValue(result.keyframes, (PointF) result.initialValue);
        }
    }

    private AnimatablePointValue(List<Keyframe<PointF>> keyframes, PointF initialValue) {
        super(keyframes, initialValue);
    }

    public KeyframeAnimation<PointF> createAnimation() {
        if (!hasAnimation()) {
            return new StaticKeyframeAnimation(this.initialValue);
        }
        return new PointKeyframeAnimation(this.keyframes);
    }
}
