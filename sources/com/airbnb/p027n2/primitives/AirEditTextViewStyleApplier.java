package com.airbnb.p027n2.primitives;

import android.widget.TextView;
import com.airbnb.p027n2.primitives.fonts.FontStyleApplier;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TextViewStyleApplier;

/* renamed from: com.airbnb.n2.primitives.AirEditTextViewStyleApplier */
public final class AirEditTextViewStyleApplier extends StyleApplier<AirEditTextViewStyleApplier, AirEditTextView> {
    public AirEditTextViewStyleApplier(AirEditTextView view) {
        super(view);
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
