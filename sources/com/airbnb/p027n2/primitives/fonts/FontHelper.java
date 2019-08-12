package com.airbnb.p027n2.primitives.fonts;

import android.graphics.Typeface;
import android.view.View;
import com.airbnb.p027n2.N2Context;
import com.airbnb.p027n2.interfaces.Typefaceable;
import java.util.Locale;

/* renamed from: com.airbnb.n2.primitives.fonts.FontHelper */
public class FontHelper {
    public static boolean forceSystemFont() {
        return Locale.getDefault().getLanguage().equals("el") || N2Context.instance().graph().mo11971n2().shouldOverrideFont();
    }

    public static void setFont(Typefaceable view, Font font) {
        Typeface typeface = FontManager.getTypeface(font, ((View) view).getContext());
        if (typeface == null) {
            return;
        }
        if (view.getTypeface() == null) {
            view.setTypeface(typeface);
        } else {
            view.setTypeface(typeface, view.getTypeface().getStyle());
        }
    }
}
