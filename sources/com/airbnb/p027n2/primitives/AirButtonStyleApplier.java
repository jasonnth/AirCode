package com.airbnb.p027n2.primitives;

import android.content.res.Resources;
import android.widget.TextView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.fonts.FontStyleApplier;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TextViewStyleApplier;
import com.airbnb.paris.TypedArrayWrapper;

/* renamed from: com.airbnb.n2.primitives.AirButtonStyleApplier */
public final class AirButtonStyleApplier extends StyleApplier<AirButtonStyleApplier, AirButton> {
    public AirButtonStyleApplier(AirButton view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_AirButton;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((AirButton) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_AirButton_n2_underline)) {
            ((AirButton) getView()).underlineText(a.getBoolean(R.styleable.n2_AirButton_n2_underline, false));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new TextViewStyleApplier((TextView) getView()).apply(style);
    }

    /* access modifiers changed from: protected */
    public void applyDependencies(Style style) {
        new FontStyleApplier((TextView) getView()).apply(style);
    }
}
