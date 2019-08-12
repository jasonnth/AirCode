package com.airbnb.p027n2.primitives;

import android.widget.TextView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.fonts.FontStyleApplier;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TextViewStyleApplier;

/* renamed from: com.airbnb.n2.primitives.AirTextViewStyleApplier */
public final class AirTextViewStyleApplier extends StyleApplier<AirTextViewStyleApplier, AirTextView> {
    public AirTextViewStyleApplier(AirTextView view) {
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

    public AirTextViewStyleApplier applyTitle3PlusPlus() {
        return (AirTextViewStyleApplier) apply(R.style.n2_TitleText3_PlusPlus);
    }

    public AirTextViewStyleApplier applyLargeActionable() {
        return (AirTextViewStyleApplier) apply(R.style.n2_LargeText_Actionable);
    }

    public AirTextViewStyleApplier applySmall() {
        return (AirTextViewStyleApplier) apply(R.style.n2_SmallText);
    }
}
