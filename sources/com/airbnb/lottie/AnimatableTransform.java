package com.airbnb.lottie;

import android.graphics.PointF;
import android.util.Log;
import com.facebook.react.uimanager.ViewProps;
import java.util.Collections;
import org.json.JSONObject;

class AnimatableTransform {
    private final AnimatablePathValue anchorPoint;
    private final AnimatableIntegerValue opacity;
    private final AnimatableValue<PointF> position;
    private final AnimatableFloatValue rotation;
    private final AnimatableScaleValue scale;

    static class Factory {
        static AnimatableTransform newInstance() {
            return new AnimatableTransform(new AnimatablePathValue(), new AnimatablePathValue<>(), Factory.newInstance(), Factory.newInstance(), Factory.newInstance());
        }

        static AnimatableTransform newInstance(JSONObject json, LottieComposition composition) {
            AnimatablePathValue anchorPoint;
            AnimatableScaleValue scale;
            AnimatableIntegerValue opacity;
            AnimatableValue<PointF> position = null;
            AnimatableFloatValue rotation = null;
            JSONObject anchorJson = json.optJSONObject("a");
            if (anchorJson != null) {
                anchorPoint = new AnimatablePathValue(anchorJson.opt("k"), composition);
            } else {
                Log.w("LOTTIE", "Layer has no transform property. You may be using an unsupported layer type such as a camera.");
                anchorPoint = new AnimatablePathValue();
            }
            JSONObject positionJson = json.optJSONObject("p");
            if (positionJson != null) {
                position = AnimatablePathValue.createAnimatablePathOrSplitDimensionPath(positionJson, composition);
            } else {
                throwMissingTransform(ViewProps.POSITION);
            }
            JSONObject scaleJson = json.optJSONObject("s");
            if (scaleJson != null) {
                scale = Factory.newInstance(scaleJson, composition);
            } else {
                scale = new AnimatableScaleValue(Collections.emptyList(), new ScaleXY());
            }
            JSONObject rotationJson = json.optJSONObject("r");
            if (rotationJson == null) {
                rotationJson = json.optJSONObject("rz");
            }
            if (rotationJson != null) {
                rotation = Factory.newInstance(rotationJson, composition, false);
            } else {
                throwMissingTransform("rotation");
            }
            JSONObject opacityJson = json.optJSONObject("o");
            if (opacityJson != null) {
                opacity = Factory.newInstance(opacityJson, composition);
            } else {
                opacity = new AnimatableIntegerValue(Collections.emptyList(), Integer.valueOf(100));
            }
            return new AnimatableTransform(anchorPoint, position, scale, rotation, opacity);
        }

        private static void throwMissingTransform(String missingProperty) {
            throw new IllegalArgumentException("Missing transform for " + missingProperty);
        }
    }

    private AnimatableTransform(AnimatablePathValue anchorPoint2, AnimatableValue<PointF> position2, AnimatableScaleValue scale2, AnimatableFloatValue rotation2, AnimatableIntegerValue opacity2) {
        this.anchorPoint = anchorPoint2;
        this.position = position2;
        this.scale = scale2;
        this.rotation = rotation2;
        this.opacity = opacity2;
    }

    /* access modifiers changed from: 0000 */
    public AnimatablePathValue getAnchorPoint() {
        return this.anchorPoint;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableValue<PointF> getPosition() {
        return this.position;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableScaleValue getScale() {
        return this.scale;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getRotation() {
        return this.rotation;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public TransformKeyframeAnimation createAnimation() {
        return new TransformKeyframeAnimation(this);
    }
}
