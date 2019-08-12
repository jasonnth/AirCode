package com.airbnb.lottie;

import android.graphics.PointF;
import android.support.p000v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class Keyframe<T> {
    /* access modifiers changed from: private */
    public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private final LottieComposition composition;
    Float endFrame;
    final T endValue;
    final Interpolator interpolator;
    final float startFrame;
    final T startValue;

    static class Factory {
        static <T> Keyframe<T> newInstance(JSONObject json, LottieComposition composition, float scale, com.airbnb.lottie.AnimatableValue.Factory<T> valueFactory) {
            Object obj;
            Object obj2;
            Object obj3;
            PointF cp1 = null;
            PointF cp2 = null;
            float startFrame = 0.0f;
            Object obj4 = null;
            Interpolator interpolator = null;
            if (json.has("t")) {
                startFrame = (float) json.optDouble("t", 0.0d);
                Object startValueJson = json.opt("s");
                if (startValueJson != null) {
                    obj3 = valueFactory.valueFromObject(startValueJson, scale);
                } else {
                    obj3 = null;
                }
                Object endValueJson = json.opt("e");
                if (endValueJson != null) {
                    obj4 = valueFactory.valueFromObject(endValueJson, scale);
                }
                JSONObject cp1Json = json.optJSONObject("o");
                JSONObject cp2Json = json.optJSONObject("i");
                if (!(cp1Json == null || cp2Json == null)) {
                    cp1 = JsonUtils.pointFromJsonObject(cp1Json, scale);
                    cp2 = JsonUtils.pointFromJsonObject(cp2Json, scale);
                }
                if (json.optInt("h", 0) == 1) {
                    interpolator = Keyframe.LINEAR_INTERPOLATOR;
                    obj4 = obj3;
                } else if (cp1 != null) {
                    cp1.x = MiscUtils.clamp(cp1.x, -100.0f, 100.0f);
                    cp1.y = MiscUtils.clamp(cp1.y, -100.0f, 100.0f);
                    cp2.x = MiscUtils.clamp(cp2.x, -100.0f, 100.0f);
                    cp2.y = MiscUtils.clamp(cp2.y, -100.0f, 100.0f);
                    interpolator = PathInterpolatorCompat.create(cp1.x / scale, cp1.y / scale, cp2.x / scale, cp2.y / scale);
                } else {
                    interpolator = Keyframe.LINEAR_INTERPOLATOR;
                }
                obj = obj4;
                obj2 = obj3;
            } else {
                T startValue = valueFactory.valueFromObject(json, scale);
                obj = startValue;
                obj2 = startValue;
            }
            return new Keyframe<>(composition, obj2, obj, interpolator, startFrame, null);
        }

        static <T> List<Keyframe<T>> parseKeyframes(JSONArray json, LottieComposition composition, float scale, com.airbnb.lottie.AnimatableValue.Factory<T> valueFactory) {
            int length = json.length();
            if (length == 0) {
                return Collections.emptyList();
            }
            List<Keyframe<T>> keyframes = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                keyframes.add(newInstance(json.optJSONObject(i), composition, scale, valueFactory));
            }
            Keyframe.setEndFrames(keyframes);
            return keyframes;
        }
    }

    static void setEndFrames(List<? extends Keyframe<?>> keyframes) {
        int size = keyframes.size();
        for (int i = 0; i < size - 1; i++) {
            ((Keyframe) keyframes.get(i)).endFrame = Float.valueOf(((Keyframe) keyframes.get(i + 1)).startFrame);
        }
        Keyframe<?> lastKeyframe = (Keyframe) keyframes.get(size - 1);
        if (lastKeyframe.startValue == null) {
            keyframes.remove(lastKeyframe);
        }
    }

    public Keyframe(LottieComposition composition2, T startValue2, T endValue2, Interpolator interpolator2, float startFrame2, Float endFrame2) {
        this.composition = composition2;
        this.startValue = startValue2;
        this.endValue = endValue2;
        this.interpolator = interpolator2;
        this.startFrame = startFrame2;
        this.endFrame = endFrame2;
    }

    /* access modifiers changed from: 0000 */
    public float getStartProgress() {
        return this.startFrame / this.composition.getDurationFrames();
    }

    /* access modifiers changed from: 0000 */
    public float getEndProgress() {
        if (this.endFrame == null) {
            return 1.0f;
        }
        return this.endFrame.floatValue() / this.composition.getDurationFrames();
    }

    /* access modifiers changed from: 0000 */
    public boolean isStatic() {
        return this.interpolator == null;
    }

    /* access modifiers changed from: 0000 */
    public boolean containsProgress(float progress) {
        return progress >= getStartProgress() && progress <= getEndProgress();
    }

    public String toString() {
        return "Keyframe{startValue=" + this.startValue + ", endValue=" + this.endValue + ", startFrame=" + this.startFrame + ", endFrame=" + this.endFrame + ", interpolator=" + this.interpolator + '}';
    }
}
