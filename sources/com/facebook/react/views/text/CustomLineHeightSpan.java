package com.facebook.react.views.text;

import android.graphics.Paint.FontMetricsInt;
import android.text.style.LineHeightSpan;

public class CustomLineHeightSpan implements LineHeightSpan {
    private final int mHeight;

    CustomLineHeightSpan(float height) {
        this.mHeight = (int) Math.ceil((double) height);
    }

    public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, FontMetricsInt fm) {
        if ((-fm.ascent) > this.mHeight) {
            int i = -this.mHeight;
            fm.ascent = i;
            fm.top = i;
            fm.descent = 0;
            fm.bottom = 0;
        } else if ((-fm.ascent) + fm.descent > this.mHeight) {
            fm.top = fm.ascent;
            int i2 = this.mHeight + fm.ascent;
            fm.descent = i2;
            fm.bottom = i2;
        } else if ((-fm.ascent) + fm.bottom > this.mHeight) {
            fm.top = fm.ascent;
            fm.bottom = fm.ascent + this.mHeight;
        } else if ((-fm.top) + fm.bottom > this.mHeight) {
            fm.top = fm.bottom - this.mHeight;
        } else {
            int additional = this.mHeight - ((-fm.top) + fm.bottom);
            fm.top -= additional;
            fm.ascent -= additional;
        }
    }
}
