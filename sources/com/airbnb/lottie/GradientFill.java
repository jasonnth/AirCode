package com.airbnb.lottie;

import android.graphics.Path.FillType;
import org.json.JSONException;
import org.json.JSONObject;

class GradientFill {
    private final AnimatablePointValue endPoint;
    private final FillType fillType;
    private final AnimatableGradientColorValue gradientColor;
    private final GradientType gradientType;
    private final AnimatableFloatValue highlightAngle;
    private final AnimatableFloatValue highlightLength;
    private final String name;
    private final AnimatableIntegerValue opacity;
    private final AnimatablePointValue startPoint;

    static class Factory {
        static GradientFill newInstance(JSONObject json, LottieComposition composition) {
            String name = json.optString("nm");
            JSONObject jsonColor = json.optJSONObject("g");
            if (jsonColor != null && jsonColor.has("k")) {
                int points = jsonColor.optInt("p");
                jsonColor = jsonColor.optJSONObject("k");
                try {
                    jsonColor.put("p", points);
                } catch (JSONException e) {
                }
            }
            AnimatableGradientColorValue color = null;
            if (jsonColor != null) {
                color = Factory.newInstance(jsonColor, composition);
            }
            JSONObject jsonOpacity = json.optJSONObject("o");
            AnimatableIntegerValue opacity = null;
            if (jsonOpacity != null) {
                opacity = Factory.newInstance(jsonOpacity, composition);
            }
            FillType fillType = json.optInt("r", 1) == 1 ? FillType.WINDING : FillType.EVEN_ODD;
            GradientType gradientType = json.optInt("t", 1) == 1 ? GradientType.Linear : GradientType.Radial;
            JSONObject jsonStartPoint = json.optJSONObject("s");
            AnimatablePointValue startPoint = null;
            if (jsonStartPoint != null) {
                startPoint = Factory.newInstance(jsonStartPoint, composition);
            }
            JSONObject jsonEndPoint = json.optJSONObject("e");
            AnimatablePointValue endPoint = null;
            if (jsonEndPoint != null) {
                endPoint = Factory.newInstance(jsonEndPoint, composition);
            }
            return new GradientFill(name, gradientType, fillType, color, opacity, startPoint, endPoint, null, null);
        }
    }

    private GradientFill(String name2, GradientType gradientType2, FillType fillType2, AnimatableGradientColorValue gradientColor2, AnimatableIntegerValue opacity2, AnimatablePointValue startPoint2, AnimatablePointValue endPoint2, AnimatableFloatValue highlightLength2, AnimatableFloatValue highlightAngle2) {
        this.gradientType = gradientType2;
        this.fillType = fillType2;
        this.gradientColor = gradientColor2;
        this.opacity = opacity2;
        this.startPoint = startPoint2;
        this.endPoint = endPoint2;
        this.name = name2;
        this.highlightLength = highlightLength2;
        this.highlightAngle = highlightAngle2;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public GradientType getGradientType() {
        return this.gradientType;
    }

    /* access modifiers changed from: 0000 */
    public FillType getFillType() {
        return this.fillType;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableGradientColorValue getGradientColor() {
        return this.gradientColor;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    /* access modifiers changed from: 0000 */
    public AnimatablePointValue getStartPoint() {
        return this.startPoint;
    }

    /* access modifiers changed from: 0000 */
    public AnimatablePointValue getEndPoint() {
        return this.endPoint;
    }
}
