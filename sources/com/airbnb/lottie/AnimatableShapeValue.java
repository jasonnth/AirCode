package com.airbnb.lottie;

import android.graphics.Path;
import java.util.List;
import org.json.JSONObject;

class AnimatableShapeValue extends BaseAnimatableValue<ShapeData, Path> {
    private final Path convertTypePath;

    static final class Factory {
        static AnimatableShapeValue newInstance(JSONObject json, LottieComposition composition) {
            Result<ShapeData> result = AnimatableValueParser.newInstance(json, composition.getDpScale(), composition, Factory.INSTANCE).parseJson();
            return new AnimatableShapeValue(result.keyframes, (ShapeData) result.initialValue);
        }
    }

    private AnimatableShapeValue(List<Keyframe<ShapeData>> keyframes, ShapeData initialValue) {
        super(keyframes, initialValue);
        this.convertTypePath = new Path();
    }

    public BaseKeyframeAnimation<?, Path> createAnimation() {
        if (!hasAnimation()) {
            return new StaticKeyframeAnimation(convertType((ShapeData) this.initialValue));
        }
        return new ShapeKeyframeAnimation(this.keyframes);
    }

    /* access modifiers changed from: 0000 */
    public Path convertType(ShapeData shapeData) {
        this.convertTypePath.reset();
        MiscUtils.getPathFromData(shapeData, this.convertTypePath);
        return this.convertTypePath;
    }
}
