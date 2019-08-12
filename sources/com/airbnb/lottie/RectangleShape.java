package com.airbnb.lottie;

import android.graphics.PointF;
import org.json.JSONObject;

class RectangleShape {
    private final AnimatableFloatValue cornerRadius;
    private final String name;
    private final AnimatableValue<PointF> position;
    private final AnimatablePointValue size;

    static class Factory {
        static RectangleShape newInstance(JSONObject json, LottieComposition composition) {
            return new RectangleShape(json.optString("nm"), AnimatablePathValue.createAnimatablePathOrSplitDimensionPath(json.optJSONObject("p"), composition), Factory.newInstance(json.optJSONObject("s"), composition), Factory.newInstance(json.optJSONObject("r"), composition));
        }
    }

    private RectangleShape(String name2, AnimatableValue<PointF> position2, AnimatablePointValue size2, AnimatableFloatValue cornerRadius2) {
        this.name = name2;
        this.position = position2;
        this.size = size2;
        this.cornerRadius = cornerRadius2;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getCornerRadius() {
        return this.cornerRadius;
    }

    /* access modifiers changed from: 0000 */
    public AnimatablePointValue getSize() {
        return this.size;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableValue<PointF> getPosition() {
        return this.position;
    }

    public String toString() {
        return "RectangleShape{cornerRadius=" + this.cornerRadius.getInitialValue() + ", position=" + this.position + ", size=" + this.size + '}';
    }
}
