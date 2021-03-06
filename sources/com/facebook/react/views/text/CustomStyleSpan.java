package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class CustomStyleSpan extends MetricAffectingSpan {
    private final AssetManager mAssetManager;
    private final String mFontFamily;
    private final int mStyle;
    private final int mWeight;

    public CustomStyleSpan(int fontStyle, int fontWeight, String fontFamily, AssetManager assetManager) {
        this.mStyle = fontStyle;
        this.mWeight = fontWeight;
        this.mFontFamily = fontFamily;
        this.mAssetManager = assetManager;
    }

    public void updateDrawState(TextPaint ds) {
        apply(ds, this.mStyle, this.mWeight, this.mFontFamily, this.mAssetManager);
    }

    public void updateMeasureState(TextPaint paint) {
        apply(paint, this.mStyle, this.mWeight, this.mFontFamily, this.mAssetManager);
    }

    public int getStyle() {
        if (this.mStyle == -1) {
            return 0;
        }
        return this.mStyle;
    }

    public int getWeight() {
        if (this.mWeight == -1) {
            return 0;
        }
        return this.mWeight;
    }

    public String getFontFamily() {
        return this.mFontFamily;
    }

    private static void apply(Paint paint, int style, int weight, String family, AssetManager assetManager) {
        int oldStyle;
        Typeface typeface = paint.getTypeface();
        if (typeface == null) {
            oldStyle = 0;
        } else {
            oldStyle = typeface.getStyle();
        }
        int want = 0;
        if (weight == 1 || ((oldStyle & 1) != 0 && weight == -1)) {
            want = 0 | 1;
        }
        if (style == 2 || ((oldStyle & 2) != 0 && style == -1)) {
            want |= 2;
        }
        if (family != null) {
            typeface = ReactFontManager.getInstance().getTypeface(family, want, assetManager);
        } else if (typeface != null) {
            typeface = Typeface.create(typeface, want);
        }
        if (typeface != null) {
            paint.setTypeface(typeface);
        } else {
            paint.setTypeface(Typeface.defaultFromStyle(want));
        }
    }
}
