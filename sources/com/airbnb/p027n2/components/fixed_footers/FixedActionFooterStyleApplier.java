package com.airbnb.p027n2.components.fixed_footers;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Paris;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedActionFooterStyleApplier */
public final class FixedActionFooterStyleApplier extends StyleApplier<FixedActionFooterStyleApplier, FixedActionFooter> {
    public FixedActionFooterStyleApplier(FixedActionFooter view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_FixedActionFooter;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources resources = ((FixedActionFooter) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_FixedActionFooter_n2_dividerStyle)) {
            Style subStyle = new Style(a.getResourceId(R.styleable.n2_FixedActionFooter_n2_dividerStyle, -1));
            subStyle.setDebugListener(style.getDebugListener());
            Paris.style(((FixedActionFooter) getView()).divider).apply(subStyle);
        }
        if (a.hasValue(R.styleable.n2_FixedActionFooter_n2_buttonStyle)) {
            Style subStyle2 = new Style(a.getResourceId(R.styleable.n2_FixedActionFooter_n2_buttonStyle, -1));
            subStyle2.setDebugListener(style.getDebugListener());
            Paris.style(((FixedActionFooter) getView()).button).apply(subStyle2);
        }
        if (a.hasValue(R.styleable.n2_FixedActionFooter_n2_buttonText)) {
            ((FixedActionFooter) getView()).setButtonText(a.getText(R.styleable.n2_FixedActionFooter_n2_buttonText));
        }
        if (a.hasValue(R.styleable.n2_FixedActionFooter_n2_text)) {
            ((FixedActionFooter) getView()).setText(a.getText(R.styleable.n2_FixedActionFooter_n2_text));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }

    public FixedActionFooterStyleApplier applyBabu() {
        return (FixedActionFooterStyleApplier) apply(R.style.n2_FixedActionFooter_Babu);
    }

    public FixedActionFooterStyleApplier applyWhite() {
        return (FixedActionFooterStyleApplier) apply(R.style.n2_FixedActionFooter_Jellyfish);
    }
}
