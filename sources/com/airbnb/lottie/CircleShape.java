package com.airbnb.lottie;

import android.graphics.PointF;
import org.json.JSONObject;

class CircleShape {
    private final String name;
    private final AnimatableValue<PointF> position;
    private final AnimatablePointValue size;

    static class Factory {
        static CircleShape newInstance(JSONObject json, LottieComposition composition) {
            return new CircleShape(json.optString("nm"), AnimatablePathValue.createAnimatablePathOrSplitDimensionPath(json.optJSONObject("p"), composition), Factory.newInstance(json.optJSONObject("s"), composition));
        }
    }

    private CircleShape(String name2, AnimatableValue<PointF> position2, AnimatablePointValue size2) {
        this.name = name2;
        this.position = position2;
        this.size = size2;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.name;
    }

    public AnimatableValue<PointF> getPosition() {
        return this.position;
    }

    public AnimatablePointValue getSize() {
        return this.size;
    }
}
