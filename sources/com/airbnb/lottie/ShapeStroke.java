package com.airbnb.lottie;

import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class ShapeStroke {
    private final LineCapType capType;
    private final AnimatableColorValue color;
    private final LineJoinType joinType;
    private final List<AnimatableFloatValue> lineDashPattern;
    private final String name;
    private final AnimatableFloatValue offset;
    private final AnimatableIntegerValue opacity;
    private final AnimatableFloatValue width;

    static class Factory {
        static ShapeStroke newInstance(JSONObject json, LottieComposition composition) {
            String name = json.optString("nm");
            List<AnimatableFloatValue> lineDashPattern = new ArrayList<>();
            AnimatableColorValue color = Factory.newInstance(json.optJSONObject("c"), composition);
            AnimatableFloatValue width = Factory.newInstance(json.optJSONObject("w"), composition);
            AnimatableIntegerValue opacity = Factory.newInstance(json.optJSONObject("o"), composition);
            LineCapType capType = LineCapType.values()[json.optInt("lc") - 1];
            LineJoinType joinType = LineJoinType.values()[json.optInt("lj") - 1];
            AnimatableFloatValue offset = null;
            if (json.has("d")) {
                JSONArray dashesJson = json.optJSONArray("d");
                for (int i = 0; i < dashesJson.length(); i++) {
                    JSONObject dashJson = dashesJson.optJSONObject(i);
                    String n = dashJson.optString("n");
                    if (n.equals("o")) {
                        offset = Factory.newInstance(dashJson.optJSONObject("v"), composition);
                    } else if (n.equals("d") || n.equals("g")) {
                        lineDashPattern.add(Factory.newInstance(dashJson.optJSONObject("v"), composition));
                    }
                }
                if (lineDashPattern.size() == 1) {
                    lineDashPattern.add(lineDashPattern.get(0));
                }
            }
            return new ShapeStroke(name, offset, lineDashPattern, color, opacity, width, capType, joinType);
        }
    }

    enum LineCapType {
        Butt,
        Round,
        Unknown;

        /* access modifiers changed from: 0000 */
        public Cap toPaintCap() {
            switch (this) {
                case Butt:
                    return Cap.BUTT;
                case Round:
                    return Cap.ROUND;
                default:
                    return Cap.SQUARE;
            }
        }
    }

    enum LineJoinType {
        Miter,
        Round,
        Bevel;

        /* access modifiers changed from: 0000 */
        public Join toPaintJoin() {
            switch (this) {
                case Bevel:
                    return Join.BEVEL;
                case Miter:
                    return Join.MITER;
                case Round:
                    return Join.ROUND;
                default:
                    return null;
            }
        }
    }

    private ShapeStroke(String name2, AnimatableFloatValue offset2, List<AnimatableFloatValue> lineDashPattern2, AnimatableColorValue color2, AnimatableIntegerValue opacity2, AnimatableFloatValue width2, LineCapType capType2, LineJoinType joinType2) {
        this.name = name2;
        this.offset = offset2;
        this.lineDashPattern = lineDashPattern2;
        this.color = color2;
        this.opacity = opacity2;
        this.width = width2;
        this.capType = capType2;
        this.joinType = joinType2;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableColorValue getColor() {
        return this.color;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getWidth() {
        return this.width;
    }

    /* access modifiers changed from: 0000 */
    public List<AnimatableFloatValue> getLineDashPattern() {
        return this.lineDashPattern;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getDashOffset() {
        return this.offset;
    }

    /* access modifiers changed from: 0000 */
    public LineCapType getCapType() {
        return this.capType;
    }

    /* access modifiers changed from: 0000 */
    public LineJoinType getJoinType() {
        return this.joinType;
    }
}
