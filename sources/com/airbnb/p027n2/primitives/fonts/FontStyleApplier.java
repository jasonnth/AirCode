package com.airbnb.p027n2.primitives.fonts;

import android.widget.TextView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.Typefaceable;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.primitives.fonts.FontStyleApplier */
public class FontStyleApplier<T extends TextView & Typefaceable> extends StyleApplier<FontStyleApplier<T>, T> {
    public FontStyleApplier(T view) {
        super(view);
    }

    public FontStyleApplier<T> apply(Style style) {
        int face = 0;
        if (!FontHelper.forceSystemFont()) {
            TypedArrayWrapper a = style.obtainStyledAttributes(((TextView) getView()).getContext(), R.styleable.n2_Typefaceable);
            if (a != null) {
                int fontIndex = a.getInt(R.styleable.n2_Typefaceable_n2_font, Font.Default.ordinal());
                a.recycle();
                if (fontIndex == Font.Default.ordinal()) {
                    TypedArrayWrapper faceArray = style.obtainStyledAttributes(((TextView) getView()).getContext(), new int[]{16842903});
                    if (faceArray != null) {
                        face = faceArray.getInt(0, 0);
                    }
                    switch (face) {
                        case 1:
                            ((Typefaceable) ((TextView) getView())).setFont(Font.CircularBold);
                            break;
                        default:
                            ((Typefaceable) ((TextView) getView())).setFont(Font.CircularBook);
                            break;
                    }
                    if (faceArray != null) {
                        faceArray.recycle();
                    }
                } else {
                    setFontByIndex((Typefaceable) getView(), fontIndex);
                }
            }
        }
        return this;
    }

    private void setFontByIndex(Typefaceable view, int fontIndex) {
        Font font = Font.getFont(fontIndex);
        if (font != null && font != Font.Default) {
            view.setFont(font);
        }
    }
}
