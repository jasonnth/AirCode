package com.airbnb.lottie;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class GradientStroke {
    private final LineCapType capType;
    private final AnimatableFloatValue dashOffset;
    private final AnimatablePointValue endPoint;
    private final AnimatableGradientColorValue gradientColor;
    private final GradientType gradientType;
    private final LineJoinType joinType;
    private final List<AnimatableFloatValue> lineDashPattern;
    private final String name;
    private final AnimatableIntegerValue opacity;
    private final AnimatablePointValue startPoint;
    private final AnimatableFloatValue width;

    static class Factory {
        static GradientStroke newInstance(JSONObject json, LottieComposition composition) {
            String name = json.optString("nm");
            JSONObject jsonColor = json.optJSONObject("g");
            if (jsonColor != null) {
                if (jsonColor.has("k")) {
                    jsonColor = jsonColor.optJSONObject("k");
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
            AnimatableFloatValue width = Factory.newInstance(json.optJSONObject("w"), composition);
            LineCapType capType = LineCapType.values()[json.optInt("lc") - 1];
            LineJoinType joinType = LineJoinType.values()[json.optInt("lj") - 1];
            AnimatableFloatValue offset = null;
            List<AnimatableFloatValue> lineDashPattern = new ArrayList<>();
            if (json.has("d")) {
                JSONArray dashesJson = json.optJSONArray("d");
                for (int i = 0; i < dashesJson.length(); i++) {
                    JSONObject dashJson = dashesJson.optJSONObject(i);
                    String n = dashJson.optString("n");
                    if (n.equals("o")) {
                        offset = Factory.newInstance(dashJson.optJSONObject("v"), composition);
                    } else {
                        if (!n.equals("d")) {
                            if (!n.equals("g")) {
                            }
                        }
                        lineDashPattern.add(Factory.newInstance(dashJson.optJSONObject("v"), composition));
                    }
                }
                if (lineDashPattern.size() == 1) {
                    lineDashPattern.add(lineDashPattern.get(0));
                }
            }
            return new GradientStroke(name, gradientType, color, opacity, startPoint, endPoint, width, capType, joinType, lineDashPattern, offset);
        }
    }

    private GradientStroke(String name2, GradientType gradientType2, AnimatableGradientColorValue gradientColor2, AnimatableIntegerValue opacity2, AnimatablePointValue startPoint2, AnimatablePointValue endPoint2, AnimatableFloatValue width2, LineCapType capType2, LineJoinType joinType2, List<AnimatableFloatValue> lineDashPattern2, AnimatableFloatValue dashOffset2) {
        this.name = name2;
        this.gradientType = gradientType2;
        this.gradientColor = gradientColor2;
        this.opacity = opacity2;
        this.startPoint = startPoint2;
        this.endPoint = endPoint2;
        this.width = width2;
        this.capType = capType2;
        this.joinType = joinType2;
        this.lineDashPattern = lineDashPattern2;
        this.dashOffset = dashOffset2;
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

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getWidth() {
        return this.width;
    }

    /* access modifiers changed from: 0000 */
    public LineCapType getCapType() {
        return this.capType;
    }

    /* access modifiers changed from: 0000 */
    public LineJoinType getJoinType() {
        return this.joinType;
    }

    /* access modifiers changed from: 0000 */
    public List<AnimatableFloatValue> getLineDashPattern() {
        return this.lineDashPattern;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getDashOffset() {
        return this.dashOffset;
    }
}
