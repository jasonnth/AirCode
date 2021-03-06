package com.facebook.react.views.text;

import android.text.Spannable;

public class ReactTextUpdate {
    private final boolean mContainsImages;
    private final int mFontSize;
    private final int mJsEventCounter;
    private final float mLetterSpacing;
    private final float mPaddingBottom;
    private final float mPaddingLeft;
    private final float mPaddingRight;
    private final float mPaddingTop;
    private final Spannable mText;
    private final int mTextAlign;
    private final int mTextBreakStrategy;

    @Deprecated
    public ReactTextUpdate(Spannable text, int jsEventCounter, boolean containsImages, float paddingStart, float paddingTop, float paddingEnd, float paddingBottom, int textAlign) {
        this(text, jsEventCounter, containsImages, paddingStart, paddingTop, paddingEnd, paddingBottom, 0.0f, 12, textAlign, 1);
    }

    public ReactTextUpdate(Spannable text, int jsEventCounter, boolean containsImages, float paddingStart, float paddingTop, float paddingEnd, float paddingBottom, float letterSpacing, int fontSize, int textAlign, int textBreakStrategy) {
        this.mText = text;
        this.mJsEventCounter = jsEventCounter;
        this.mContainsImages = containsImages;
        this.mPaddingLeft = paddingStart;
        this.mPaddingTop = paddingTop;
        this.mPaddingRight = paddingEnd;
        this.mPaddingBottom = paddingBottom;
        this.mLetterSpacing = letterSpacing;
        this.mFontSize = fontSize;
        this.mTextAlign = textAlign;
        this.mTextBreakStrategy = textBreakStrategy;
    }

    public Spannable getText() {
        return this.mText;
    }

    public int getJsEventCounter() {
        return this.mJsEventCounter;
    }

    public boolean containsImages() {
        return this.mContainsImages;
    }

    public float getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public float getPaddingTop() {
        return this.mPaddingTop;
    }

    public float getPaddingRight() {
        return this.mPaddingRight;
    }

    public float getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public float getLetterSpacing() {
        return this.mLetterSpacing;
    }

    public int getFontSize() {
        return this.mFontSize;
    }

    public int getTextAlign() {
        return this.mTextAlign;
    }

    public int getTextBreakStrategy() {
        return this.mTextBreakStrategy;
    }
}
