package com.facebook.react.flat;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

final class FontStylingSpan extends MetricAffectingSpan {
    static final FontStylingSpan INSTANCE = new FontStylingSpan(-1.6777216E7d, 0, -1, -1, -1, false, false, null, true);
    private int mBackgroundColor;
    private String mFontFamily;
    private int mFontSize;
    private int mFontStyle;
    private int mFontWeight;
    private boolean mFrozen;
    private boolean mHasStrikeThrough;
    private boolean mHasUnderline;
    private double mTextColor;

    FontStylingSpan() {
    }

    private FontStylingSpan(double textColor, int backgroundColor, int fontSize, int fontStyle, int fontWeight, boolean hasUnderline, boolean hasStrikeThrough, String fontFamily, boolean frozen) {
        this.mTextColor = textColor;
        this.mBackgroundColor = backgroundColor;
        this.mFontSize = fontSize;
        this.mFontStyle = fontStyle;
        this.mFontWeight = fontWeight;
        this.mHasUnderline = hasUnderline;
        this.mHasStrikeThrough = hasStrikeThrough;
        this.mFontFamily = fontFamily;
        this.mFrozen = frozen;
    }

    /* access modifiers changed from: 0000 */
    public FontStylingSpan mutableCopy() {
        return new FontStylingSpan(this.mTextColor, this.mBackgroundColor, this.mFontSize, this.mFontStyle, this.mFontWeight, this.mHasUnderline, this.mHasStrikeThrough, this.mFontFamily, false);
    }

    /* access modifiers changed from: 0000 */
    public boolean isFrozen() {
        return this.mFrozen;
    }

    /* access modifiers changed from: 0000 */
    public void freeze() {
        this.mFrozen = true;
    }

    /* access modifiers changed from: 0000 */
    public double getTextColor() {
        return this.mTextColor;
    }

    /* access modifiers changed from: 0000 */
    public void setTextColor(double textColor) {
        this.mTextColor = textColor;
    }

    /* access modifiers changed from: 0000 */
    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    /* access modifiers changed from: 0000 */
    public void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
    }

    /* access modifiers changed from: 0000 */
    public int getFontSize() {
        return this.mFontSize;
    }

    /* access modifiers changed from: 0000 */
    public void setFontSize(int fontSize) {
        this.mFontSize = fontSize;
    }

    /* access modifiers changed from: 0000 */
    public int getFontStyle() {
        return this.mFontStyle;
    }

    /* access modifiers changed from: 0000 */
    public void setFontStyle(int fontStyle) {
        this.mFontStyle = fontStyle;
    }

    /* access modifiers changed from: 0000 */
    public int getFontWeight() {
        return this.mFontWeight;
    }

    /* access modifiers changed from: 0000 */
    public void setFontWeight(int fontWeight) {
        this.mFontWeight = fontWeight;
    }

    /* access modifiers changed from: 0000 */
    public String getFontFamily() {
        return this.mFontFamily;
    }

    /* access modifiers changed from: 0000 */
    public void setFontFamily(String fontFamily) {
        this.mFontFamily = fontFamily;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasUnderline() {
        return this.mHasUnderline;
    }

    /* access modifiers changed from: 0000 */
    public void setHasUnderline(boolean hasUnderline) {
        this.mHasUnderline = hasUnderline;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasStrikeThrough() {
        return this.mHasStrikeThrough;
    }

    /* access modifiers changed from: 0000 */
    public void setHasStrikeThrough(boolean hasStrikeThrough) {
        this.mHasStrikeThrough = hasStrikeThrough;
    }

    public void updateDrawState(TextPaint ds) {
        if (!Double.isNaN(this.mTextColor)) {
            ds.setColor((int) this.mTextColor);
        }
        ds.bgColor = this.mBackgroundColor;
        ds.setUnderlineText(this.mHasUnderline);
        ds.setStrikeThruText(this.mHasStrikeThrough);
        updateMeasureState(ds);
    }

    public void updateMeasureState(TextPaint ds) {
        if (this.mFontSize != -1) {
            ds.setTextSize((float) this.mFontSize);
        }
        updateTypeface(ds);
    }

    private int getNewStyle(int oldStyle) {
        int newStyle = oldStyle;
        if (this.mFontStyle != -1) {
            newStyle = (newStyle & -3) | this.mFontStyle;
        }
        if (this.mFontWeight != -1) {
            return (newStyle & -2) | this.mFontWeight;
        }
        return newStyle;
    }

    private void updateTypeface(TextPaint ds) {
        Typeface typeface;
        Typeface typeface2 = ds.getTypeface();
        int oldStyle = typeface2 == null ? 0 : typeface2.getStyle();
        int newStyle = getNewStyle(oldStyle);
        if (oldStyle != newStyle || this.mFontFamily != null) {
            if (this.mFontFamily != null) {
                typeface = TypefaceCache.getTypeface(this.mFontFamily, newStyle);
            } else {
                typeface = TypefaceCache.getTypeface(typeface2, newStyle);
            }
            ds.setTypeface(typeface);
        }
    }
}
