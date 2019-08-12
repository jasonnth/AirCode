package com.airbnb.p027n2.interfaces;

import android.graphics.Typeface;
import com.airbnb.p027n2.primitives.fonts.Font;

/* renamed from: com.airbnb.n2.interfaces.Typefaceable */
public interface Typefaceable {
    Typeface getTypeface();

    void setFont(Font font);

    void setTypeface(Typeface typeface);

    void setTypeface(Typeface typeface, int i);
}
