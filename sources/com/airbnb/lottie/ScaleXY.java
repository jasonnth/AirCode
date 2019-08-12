package com.airbnb.lottie;

import org.json.JSONArray;

class ScaleXY {
    private final float scaleX;
    private final float scaleY;

    static class Factory implements com.airbnb.lottie.AnimatableValue.Factory<ScaleXY> {
        static final Factory INSTANCE = new Factory();

        private Factory() {
        }

        public ScaleXY valueFromObject(Object object, float scale) {
            JSONArray array = (JSONArray) object;
            return new ScaleXY((((float) array.optDouble(0, 1.0d)) / 100.0f) * scale, (((float) array.optDouble(1, 1.0d)) / 100.0f) * scale);
        }
    }

    ScaleXY(float sx, float sy) {
        this.scaleX = sx;
        this.scaleY = sy;
    }

    ScaleXY() {
        this(1.0f, 1.0f);
    }

    /* access modifiers changed from: 0000 */
    public float getScaleX() {
        return this.scaleX;
    }

    /* access modifiers changed from: 0000 */
    public float getScaleY() {
        return this.scaleY;
    }

    public String toString() {
        return getScaleX() + "x" + getScaleY();
    }
}
