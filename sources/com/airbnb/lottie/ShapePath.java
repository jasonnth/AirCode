package com.airbnb.lottie;

import org.json.JSONObject;

class ShapePath {
    private final int index;
    private final String name;
    private final AnimatableShapeValue shapePath;

    static class Factory {
        static ShapePath newInstance(JSONObject json, LottieComposition composition) {
            return new ShapePath(json.optString("nm"), json.optInt("ind"), Factory.newInstance(json.optJSONObject("ks"), composition));
        }
    }

    private ShapePath(String name2, int index2, AnimatableShapeValue shapePath2) {
        this.name = name2;
        this.index = index2;
        this.shapePath = shapePath2;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableShapeValue getShapePath() {
        return this.shapePath;
    }

    public String toString() {
        return "ShapePath{name=" + this.name + ", index=" + this.index + ", hasAnimation=" + this.shapePath.hasAnimation() + '}';
    }
}
