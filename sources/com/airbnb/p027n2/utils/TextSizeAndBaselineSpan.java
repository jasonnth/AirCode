package com.airbnb.p027n2.utils;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* renamed from: com.airbnb.n2.utils.TextSizeAndBaselineSpan */
public class TextSizeAndBaselineSpan extends MetricAffectingSpan {
    final float baseline;
    final float proportion;

    public TextSizeAndBaselineSpan(float baseline2, float proportion2) {
        this.baseline = baseline2;
        this.proportion = proportion2;
    }

    public static TextSizeAndBaselineSpan forPricetag() {
        return new TextSizeAndBaselineSpan(0.17f, 0.7f);
    }

    public void updateDrawState(TextPaint paint) {
        paint.baselineShift += (int) (paint.ascent() * this.baseline);
        paint.setTextSize(paint.getTextSize() * this.proportion);
    }

    public void updateMeasureState(TextPaint paint) {
        paint.baselineShift += (int) (paint.ascent() * this.baseline);
        paint.setTextSize(paint.getTextSize() * this.proportion);
    }
}
