package com.airbnb.p027n2.primitives.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.p000v4.util.LruCache;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* renamed from: com.airbnb.n2.primitives.fonts.CustomFontSpan */
public class CustomFontSpan extends MetricAffectingSpan {
    private static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(12);
    private int mColor;
    private boolean mIsColorDefined;
    private Typeface mTypeface;

    public CustomFontSpan(Context context, String typefaceName) {
        this.mTypeface = (Typeface) sTypefaceCache.get(typefaceName);
        if (this.mTypeface == null) {
            this.mTypeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(), String.format("%s", new Object[]{typefaceName}));
            sTypefaceCache.put(typefaceName, this.mTypeface);
        }
    }

    public CustomFontSpan(Context context, Font font) {
        this(context, font.mFilename);
    }

    public CustomFontSpan(Context context, Font font, int color) {
        this(context, font);
        this.mColor = color;
        this.mIsColorDefined = true;
    }

    public void updateMeasureState(TextPaint p) {
        p.setTypeface(this.mTypeface);
        p.setFlags(p.getFlags() | 128);
    }

    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(this.mTypeface);
        tp.setFlags(tp.getFlags() | 128);
        if (this.mIsColorDefined) {
            tp.setColor(this.mColor);
        }
    }
}
