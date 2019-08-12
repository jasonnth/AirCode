package com.airbnb.p027n2.components;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.FixedActionFooterWithTextStyleApplier */
public final class FixedActionFooterWithTextStyleApplier extends StyleApplier<FixedActionFooterWithTextStyleApplier, FixedActionFooterWithText> {
    public FixedActionFooterWithTextStyleApplier(FixedActionFooterWithText view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_FixedActionFooterWithText;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((FixedActionFooterWithText) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_FixedActionFooterWithText_n2_buttonText)) {
            ((FixedActionFooterWithText) getView()).setButtonText(a.getText(R.styleable.n2_FixedActionFooterWithText_n2_buttonText));
        }
        if (a.hasValue(R.styleable.n2_FixedActionFooterWithText_n2_text)) {
            ((FixedActionFooterWithText) getView()).setTextViewText(a.getText(R.styleable.n2_FixedActionFooterWithText_n2_text));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }
}
