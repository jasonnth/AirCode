package com.airbnb.lottie;

import android.graphics.PointF;
import org.json.JSONObject;

class PolystarShape {
    private final AnimatableFloatValue innerRadius;
    private final AnimatableFloatValue innerRoundedness;
    private final String name;
    private final AnimatableFloatValue outerRadius;
    private final AnimatableFloatValue outerRoundedness;
    private final AnimatableFloatValue points;
    private final AnimatableValue<PointF> position;
    private final AnimatableFloatValue rotation;
    private final Type type;

    static class Factory {
        static PolystarShape newInstance(JSONObject json, LottieComposition composition) {
            AnimatableFloatValue innerRadius;
            AnimatableFloatValue innerRoundedness;
            String name = json.optString("nm");
            Type type = Type.forValue(json.optInt("sy"));
            AnimatableFloatValue points = Factory.newInstance(json.optJSONObject("pt"), composition, false);
            AnimatableValue<PointF> position = AnimatablePathValue.createAnimatablePathOrSplitDimensionPath(json.optJSONObject("p"), composition);
            AnimatableFloatValue rotation = Factory.newInstance(json.optJSONObject("r"), composition, false);
            AnimatableFloatValue outerRadius = Factory.newInstance(json.optJSONObject("or"), composition);
            AnimatableFloatValue outerRoundedness = Factory.newInstance(json.optJSONObject("os"), composition, false);
            if (type == Type.Star) {
                innerRadius = Factory.newInstance(json.optJSONObject("ir"), composition);
                innerRoundedness = Factory.newInstance(json.optJSONObject("is"), composition, false);
            } else {
                innerRadius = null;
                innerRoundedness = null;
            }
            return new PolystarShape(name, type, points, position, rotation, innerRadius, outerRadius, innerRoundedness, outerRoundedness);
        }
    }

    enum Type {
        Star(1),
        Polygon(2);
        
        private final int value;

        private Type(int value2) {
            this.value = value2;
        }

        static Type forValue(int value2) {
            Type[] values;
            for (Type type : values()) {
                if (type.value == value2) {
                    return type;
                }
            }
            return null;
        }
    }

    private PolystarShape(String name2, Type type2, AnimatableFloatValue points2, AnimatableValue<PointF> position2, AnimatableFloatValue rotation2, AnimatableFloatValue innerRadius2, AnimatableFloatValue outerRadius2, AnimatableFloatValue innerRoundedness2, AnimatableFloatValue outerRoundedness2) {
        this.name = name2;
        this.type = type2;
        this.points = points2;
        this.position = position2;
        this.rotation = rotation2;
        this.innerRadius = innerRadius2;
        this.outerRadius = outerRadius2;
        this.innerRoundedness = innerRoundedness2;
        this.outerRoundedness = outerRoundedness2;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public Type getType() {
        return this.type;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getPoints() {
        return this.points;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableValue<PointF> getPosition() {
        return this.position;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getRotation() {
        return this.rotation;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getInnerRadius() {
        return this.innerRadius;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getOuterRadius() {
        return this.outerRadius;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getInnerRoundedness() {
        return this.innerRoundedness;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getOuterRoundedness() {
        return this.outerRoundedness;
    }
}
