package com.airbnb.lottie;

import android.graphics.Path.FillType;
import org.json.JSONObject;

class ShapeFill {
    private final AnimatableColorValue color;
    private final boolean fillEnabled;
    private final FillType fillType;
    private final String name;
    private final AnimatableIntegerValue opacity;

    static class Factory {
        static ShapeFill newInstance(JSONObject json, LottieComposition composition) {
            AnimatableColorValue color = null;
            AnimatableIntegerValue opacity = null;
            String name = json.optString("nm");
            JSONObject jsonColor = json.optJSONObject("c");
            if (jsonColor != null) {
                color = Factory.newInstance(jsonColor, composition);
            }
            JSONObject jsonOpacity = json.optJSONObject("o");
            if (jsonOpacity != null) {
                opacity = Factory.newInstance(jsonOpacity, composition);
            }
            return new ShapeFill(name, json.optBoolean("fillEnabled"), json.optInt("r", 1) == 1 ? FillType.WINDING : FillType.EVEN_ODD, color, opacity);
        }
    }

    private ShapeFill(String name2, boolean fillEnabled2, FillType fillType2, AnimatableColorValue color2, AnimatableIntegerValue opacity2) {
        this.name = name2;
        this.fillEnabled = fillEnabled2;
        this.fillType = fillType2;
        this.color = color2;
        this.opacity = opacity2;
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
    public FillType getFillType() {
        return this.fillType;
    }

    public String toString() {
        String hexString;
        Object initialValue;
        StringBuilder append = new StringBuilder().append("ShapeFill{color=");
        if (this.color == null) {
            hexString = "null";
        } else {
            hexString = Integer.toHexString(((Integer) this.color.getInitialValue()).intValue());
        }
        StringBuilder append2 = append.append(hexString).append(", fillEnabled=").append(this.fillEnabled).append(", opacity=");
        if (this.opacity == null) {
            initialValue = "null";
        } else {
            initialValue = this.opacity.getInitialValue();
        }
        return append2.append(initialValue).append('}').toString();
    }
}
