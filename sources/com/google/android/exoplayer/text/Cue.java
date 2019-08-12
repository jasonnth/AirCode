package com.google.android.exoplayer.text;

import android.text.Layout.Alignment;

public class Cue {
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final int positionAnchor;
    public final float size;
    public final CharSequence text;
    public final Alignment textAlignment;

    public Cue() {
        this(null);
    }

    public Cue(CharSequence text2) {
        this(text2, null, Float.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE, Integer.MIN_VALUE, Float.MIN_VALUE);
    }

    public Cue(CharSequence text2, Alignment textAlignment2, float line2, int lineType2, int lineAnchor2, float position2, int positionAnchor2, float size2) {
        this.text = text2;
        this.textAlignment = textAlignment2;
        this.line = line2;
        this.lineType = lineType2;
        this.lineAnchor = lineAnchor2;
        this.position = position2;
        this.positionAnchor = positionAnchor2;
        this.size = size2;
    }
}
